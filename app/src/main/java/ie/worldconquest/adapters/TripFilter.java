package ie.worldconquest.adapters;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import ie.worldconquest.models.Trip;

public class TripFilter extends Filter
{
    public List<Trip> originalTripList;
    public String filterText;
    public TripListAdapter adapter;

    public TripFilter(List<Trip> originalTripList, String filterText, TripListAdapter adapter)
    {
        super();
        this.originalTripList = originalTripList;
        this.filterText = filterText;
        this.adapter = adapter;
    }

    public void setFilter(String filterText) { this.filterText = filterText;}

    @Override
    protected FilterResults performFiltering(CharSequence prefix) {
        FilterResults results = new FilterResults();

        List<Trip> newTrips;
        String tripName;

        if (prefix == null || prefix.length() == 0) {
            newTrips = new ArrayList<>();
            if (filterText.equals("all")) {
                results.values = originalTripList;
                results.count = originalTripList.size();
            } else {
                if (filterText.equals("favourites")) {
                    for (Trip c : originalTripList)
                        if (c.currentTrip)
                            newTrips.add(c);
                }
                results.values = newTrips;
                results.count = newTrips.size();
            }
        } else {
            String prefixString = prefix.toString().toLowerCase();
            newTrips = new ArrayList<>();

            for (Trip c : originalTripList) {
                tripName = c.tripName.toLowerCase();
                if (tripName.contains(prefixString)) {
                    if (filterText.equals("all")) {
                        newTrips.add(c);
                    } else if (c.currentTrip) {
                        newTrips.add(c);
                    }}}
            results.values = newTrips;
            results.count = newTrips.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence prefix, FilterResults results) {

        adapter.tripList = (ArrayList<Trip>) results.values;

        if (results.count >= 0)
            adapter.notifyDataSetChanged();
        else {
            adapter.notifyDataSetInvalidated();
            adapter.tripList = originalTripList;
        }
    }
}
