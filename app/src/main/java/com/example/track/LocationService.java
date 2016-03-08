package com.example.track;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service implements LocationListener
{
	
	@Override
	public void onCreate()
	{
		new SendData().execute("Send Data");
		Toast.makeText(this, "Service Started1", Toast.LENGTH_LONG).show();
		
		super.onCreate();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) 
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void onLocationChanged(Location location)
	{
		Log.v("LOCATIONSERVICE", "In onlocationchanged method!!");
		new SendData().execute("data");
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
}
