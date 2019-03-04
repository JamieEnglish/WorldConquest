package ie.worldconquest.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import ie.worldconquest.R;
import ie.worldconquest.activities.Base;
import ie.worldconquest.adapters.TripFilter;
import ie.worldconquest.adapters.TripListAdapter;
import ie.worldconquest.models.Trip;

public class TripFragment extends Fragment implements
        AdapterView.OnItemClickListener,
        View.OnClickListener,
        AbsListView.MultiChoiceModeListener

{
    public Base activity;
    public static TripListAdapter listAdapter;
    public ListView listView;
    public TripFilter tripFilter;
    public boolean currentTrip = false;

    public TripFragment () {};

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("tripId", (String) view.getTag());

        Fragment fragment = EditFragment.newInstance(activityInfo);
        getActivity().setTitle(R.string.editTripsLbl);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.homeFrame, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static TripFragment newInstance() {
        TripFragment fragment = new TripFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, parent, false);
        getActivity().setTitle(R.string.recentlyViewedLbl);
        listAdapter = new TripListAdapter(activity, this, activity.app.tripList);
        tripFilter = new TripFilter(activity.app.tripList,"all",listAdapter);

        if (currentTrip) {
            getActivity().setTitle(R.string.currentTripsLbl);
            tripFilter.setFilter("current"); // Set the filter text field from 'all' to 'favourites'
            tripFilter.filter(null); // Filter the data, but don't use any prefix
            listAdapter.notifyDataSetChanged(); // Update the adapter
        }
        // setRandomCoffee();

        listView = v.findViewById(R.id.homeList);

        setListView(v);

        return v;
    }

    public void setListView(View view)
    {
        listView.setAdapter (listAdapter);
        listView.setOnItemClickListener(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        listView.setEmptyView(view.findViewById(R.id.emptyList));
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
        if (view.getTag() instanceof Trip)
        {
            onTripDelete ((Trip) view.getTag());
        }
    }

    public void onTripDelete(final Trip trip)
    {
        String stringName = trip.tripName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Trip\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                activity.app.tripList.remove(trip); // remove from our list
                listAdapter.tripList.remove(trip); // update adapters data

                listAdapter.notifyDataSetChanged(); // refresh adapter
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //multi

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
    {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.menu_item_delete_trip:
                deleteTrips(actionMode);
                return true;
            default:
                return false;
        }
    }

    public void deleteTrips(ActionMode actionMode)
    {
        for (int i = listAdapter.getCount() -1 ; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                activity.app.tripList.remove(listAdapter.getItem(i));
                if (currentTrip)
                    listAdapter.tripList.remove(listAdapter.getItem(i));
            }
        }

        listAdapter.notifyDataSetChanged(); // refresh adapter

        actionMode.finish();
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode)
    {}

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
    }
}
