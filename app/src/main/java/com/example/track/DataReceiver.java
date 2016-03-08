package com.example.track;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

public class DataReceiver extends BroadcastReceiver{
	
	GPSTracker gpsTrackerObject;
	Location location;
	LocationService locationService;
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Service", "started");
		AlarmManager manager=(AlarmManager)context.getSystemService(context.ALARM_SERVICE);
		Intent intent_for_serviceclass=new Intent(context,LocationService.class);
		context.startService(intent_for_serviceclass);
		
		/*gpsTrackerObject=new GPSTracker(context);
		
		location=gpsTrackerObject.location;
		
		locationService=new LocationService();
		locationService.onLocationChanged(location);*/
		PendingIntent intent_for_alarmManager=PendingIntent.getBroadcast(context, 0,intent_for_serviceclass,PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.SECOND, 10);
		manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 30000, intent_for_alarmManager);
	}

}
