package ie.worldconquest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ie.worldconquest.R;
import ie.worldconquest.activities.Home;
import ie.worldconquest.main.WorldConquestApp;
import ie.worldconquest.models.Trip;

public class AddFragment extends Fragment {

    private String tripName, tripCountry;
    private EditText name, country;
    private Button saveButton;
    private WorldConquestApp app;

    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (WorldConquestApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        getActivity().setTitle(R.string.addATripsLbl);
        name = v.findViewById(R.id.addNameET);
        country =  v.findViewById(R.id.addCountryET);
        saveButton = v.findViewById(R.id.addTripBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTrip();
            }
        });

        return v;
    }

    public void addTrip() {

        tripName = name.getText().toString();
        tripCountry = country.getText().toString();



        if ((tripName.length() > 0) && (tripCountry.length() > 0)
                ) {
            Trip t = new Trip(tripName, tripCountry, false,
                    false, false, true);

            app.tripList.add(t);
            startActivity(new Intent(this.getActivity(), Home.class));
        } else
            Toast.makeText(
                    this.getActivity(),
                    "You must Enter Something for "
                            + "\'Name\' and \'Country\'",
                    Toast.LENGTH_SHORT).show();
    }
}
