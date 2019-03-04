package ie.worldconquest.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import ie.worldconquest.main.WorldConquestApp;
import ie.worldconquest.models.Trip;
import ie.worldconquest.R;

public class EditFragment extends Fragment {

    public boolean currentTrip;
    public Trip aTrip;
    public ImageView editCurrent;
    public EditText name, country;
    public CheckBox milestone1cb, milestone2cb, milestone3cb;
    public WorldConquestApp app;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {

    }

    public static EditFragment newInstance(Bundle tripBundle) {
        EditFragment fragment = new EditFragment();
        fragment.setArguments(tripBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        app = (WorldConquestApp) getActivity().getApplication();

        if(getArguments() != null)
            aTrip = getTripObject(getArguments().getString("tripId"));
    }

    private Trip getTripObject(String id) {

        for (Trip t : app.tripList)
            if (t.tripId.equalsIgnoreCase(id))
                return t;

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        ((TextView)v.findViewById(R.id.editTitleTV)).setText(aTrip.tripName);

        name = v.findViewById(R.id.editNameET);
        country = v.findViewById(R.id.editCountryET);
        milestone1cb = v.findViewById(R.id.editMilestone1CB);
        milestone2cb = v.findViewById(R.id.editMilestone2CB);
        milestone3cb = v.findViewById(R.id.editMilestone3CB);
        editCurrent = v.findViewById(R.id.editCurrent);

        name.setText(aTrip.tripName);
        country.setText(aTrip.country);


            if(milestone1cb.isChecked())
            {
                aTrip.milestone1 = true;
            }
            else if (milestone2cb.isChecked())
            {
                aTrip.milestone2 = true;
            }
            else if (milestone3cb.isChecked())
            {
                aTrip.milestone3 = true;
            }



        if (aTrip.currentTrip == true) {
            editCurrent.setImageResource(R.drawable.ic_card_travel_48px_current);
            currentTrip = true;
        } else {
            editCurrent.setImageResource(R.drawable.ic_card_travel_48px);
            currentTrip = false;
        }
        return v;
    }

    public void saveTrip(View v) {
        if (mListener != null) {
            String tripName = name.getText().toString();
            String tripCountry = country.getText().toString();
            boolean milestone1 = milestone1cb.isChecked();
            boolean milestone2 = milestone2cb.isChecked();
            boolean milestone3 = milestone3cb.isChecked();


            if ((tripName.length() > 0) && (tripCountry.length() > 0)) {
                aTrip.tripName = tripName;
                aTrip.country = tripCountry;
                aTrip.milestone1 = milestone1;
                aTrip.milestone2 = milestone2;
                aTrip.milestone3 = milestone3;


                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                    return;
                }
            }
        } else
            Toast.makeText(getActivity(), "You must Enter Something for Name and Country", Toast.LENGTH_SHORT).show();
    }

    public void toggle(View v) {

        if (currentTrip) {
            aTrip.currentTrip = false;
            Toast.makeText(getActivity(), "Removed From current", Toast.LENGTH_SHORT).show();
            currentTrip = false;
            editCurrent.setImageResource(R.drawable.ic_card_travel_48px);
        } else {
            aTrip.currentTrip = true;
            Toast.makeText(getActivity(), "This is your current holiday", Toast.LENGTH_SHORT).show();
            currentTrip = true;
            editCurrent.setImageResource(R.drawable.ic_card_travel_48px_current);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void toggle(View v);
        void saveTrip(View v);
    }
}
