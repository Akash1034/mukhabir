package com.example.track;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSTracker extends Service implements LocationListener{
	
	Context context;
	boolean isGPSEnabled=false;
 
    boolean isNetworkEnabled=false;
 
    boolean canGetLocation=false;
 
    Location location;
    double latitude; 
    double longitude;
 
    long min_distance=1;
    
    long min_time=1000;
 
    LocationManager locationmanager;
    
    public GPSTracker(Context context)
    {
        this.context = context;
        getLocation();
    }
    
    public Location getLocation()
    {
		try
		{
			locationmanager=(LocationManager)context.getSystemService(LOCATION_SERVICE);
			
			isGPSEnabled=locationmanager.isProviderEnabled(locationmanager.GPS_PROVIDER);
			
			isNetworkEnabled=locationmanager.isProviderEnabled(locationmanager.NETWORK_PROVIDER);
			
			if(!isGPSEnabled && !isNetworkEnabled)
			{
				Toast.makeText(getApplicationContext(), "No Provider Available", Toast.LENGTH_SHORT).show();
			}
			else
			{
				this.canGetLocation=true;
				if(isNetworkEnabled)
				{
					locationmanager.requestLocationUpdates(locationmanager.NETWORK_PROVIDER,min_time, min_distance, this);
					if(locationmanager!=null)
					{
						location=locationmanager.getLastKnownLocation(locationmanager.NETWORK_PROVIDER);
					if(location!=null)
					{
						latitude=location.getLatitude();
						longitude=location.getLongitude();
						}
					}
				}
				if(isGPSEnabled)
				{
					locationmanager.requestLocationUpdates(locationmanager.GPS_PROVIDER,min_time,min_distance, this);
					if(locationmanager!=null)
					{
						location=locationmanager.getLastKnownLocation(locationmanager.GPS_PROVIDER);
						if(location!=null)
						{
							latitude=location.getLatitude();
							longitude=location.getLongitude();
						}
					}
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.e("Latitude", "device does not support GPS or Network");
		}
		return location;
	}
    
    public double getLatitude()
    {
        if(location != null)
        {
            latitude = location.getLatitude();
        }
        return latitude;
    }
    public double getLongitude()
    {
        if(location != null)
        {
            longitude = location.getLongitude();
        }
        return longitude;
    }
    
    public boolean canGetLocation()
    {
        return this.canGetLocation;
    }
     

	@Override
	public void onLocationChanged(Location location)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
