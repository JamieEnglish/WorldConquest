package ie.worldconquest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import ie.worldconquest.R;
import ie.worldconquest.adapters.TripFilter;

public class SearchFragment extends TripFragment implements AdapterView.OnItemSelectedListener
{
    String selected;
    SearchView searchView;

    public SearchFragment()
    {

    }

    public static SearchFragment newInstance()
    {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().setTitle(R.string.searchTripsLbl);
        listView = v.findViewById(R.id.searchList); //Bind to the list on our Search layout
        setListView(v);


        searchView = v.findViewById(R.id.searchView);
        searchView.setQueryHint("Search your Holidays Here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                tripFilter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tripFilter.filter(newText);
                return false;
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context c) { super.onAttach(c); }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void checkSelected(String selected)
    {
        if (selected != null) {
            if (selected.equals("All Types")) {
                tripFilter.setFilter("all");
            } else if (selected.equals("Currents")) {
                tripFilter.setFilter("Currents");
            }

            String filterText = ((SearchView)activity
                    .findViewById(R.id.searchView)).getQuery().toString();

            if(filterText.length() > 0)
                tripFilter.filter(filterText);
            else
                tripFilter.filter("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected = parent.getItemAtPosition(position).toString();
        checkSelected(selected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void deleteTrips(ActionMode actionMode) {
        super.deleteTrips(actionMode);
        checkSelected(selected);
    }
}
