package ie.worldconquest.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import ie.worldconquest.R;
import ie.worldconquest.models.Trip;

public class TripListAdapter extends ArrayAdapter<Trip>
{
    private Context context;
    private View.OnClickListener deleteListener;
    public List<Trip> tripList;

    public TripListAdapter(Context context, View.OnClickListener deleteListener, List<Trip> tripList)
    {
        super(context, R.layout.triprow, tripList);

        this.context = context;
        this.deleteListener = deleteListener;
        this.tripList = tripList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TripItem item = new TripItem(context, parent, deleteListener, tripList.get(position));
        return item.view;
    }

    @Override
    public int getCount()
    {
        return tripList.size();
    }

    @Override
    public Trip getItem(int position)
    {
        return tripList.get(position);
    }
}
