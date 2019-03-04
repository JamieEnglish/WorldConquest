package ie.worldconquest.main;

import android.app.Application;
import java.util.ArrayList;
import java.util.List;
import ie.worldconquest.models.Trip;

public class WorldConquestApp extends Application
{
    public List <Trip> tripList = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
    }
}
