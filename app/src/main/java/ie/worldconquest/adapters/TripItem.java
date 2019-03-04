package ie.worldconquest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import ie.worldconquest.R;
import ie.worldconquest.models.Trip;

public class TripItem {
    public View view;

    public TripItem(Context context, ViewGroup parent, View.OnClickListener deleteListener, Trip trip)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.tripcard, parent, false);
        view.setTag(trip.tripId);

        updateControls(trip);

        ImageView imgDelete = view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(trip);
        imgDelete.setOnClickListener(deleteListener);
   }

   private void updateControls(Trip trip) {
       ((TextView) view.findViewById(R.id.rowTripName)).setText(trip.tripName);
       ((TextView) view.findViewById(R.id.rowTripCountry)).setText(trip.country);


       /* currenttrip, image, if else */
   }
}
