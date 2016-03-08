package com.example.track;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
	Button devicedetails_button;
	Button address_button;
	GPSTracker gps;
	double latitude;
	double longitude;
	String manufacturer;
	String model;
	String IMEI;
	String device_name=null;
	int battery_level;
	StringBuilder address;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(getApplicationContext(),LocationService.class));
		
		gps=new GPSTracker(MainActivity.this);
		if(gps.canGetLocation())
		{
			latitude=gps.getLatitude();
			longitude=gps.getLongitude();
		}
		else
		{
			Log.e("No location Access", "Latitide not retrieved");
		}
		
		if(fetchdetails())
		{
			Log.i("Phone Details", "Phone Details Fetched");
		}
		
		if(getMyLocationAddress())
		{
			Log.e("Address", "Address not retrieved,No internet connection");
		}
	}
	public boolean fetchdetails()
	{
		
		DeviceDetails details=new DeviceDetails(MainActivity.this);
		manufacturer=details.devicemanufacturer();
		model=details.devicemodel();
		IMEI=details.IMEI();
		device_name=manufacturer.concat(model);
		Intent intent=registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		battery_level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		return true;
	}
	
	public boolean getMyLocationAddress()
	{
        Geocoder geocoder= new Geocoder(this, Locale.ENGLISH);
        try
        {
              List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
              if(addresses != null)
              {
                  Address fetchedAddress = addresses.get(0);
                  address= new StringBuilder();
                  for(int i=0; i<fetchedAddress.getMaxAddressLineIndex(); i++) {
                       address.append(fetchedAddress.getAddressLine(i)).append("\n");
                  }      
              }
              return true;
        }catch (IOException e)
        {
                 e.printStackTrace();
                 Log.v("No Internet", "Internet Not connected");
                 return false;
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
