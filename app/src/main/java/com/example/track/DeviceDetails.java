package com.example.track;

import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceDetails {
	Context context;
	public DeviceDetails(Context context){
		this.context=context;
	}

	public String devicemanufacturer(){
		
		return android.os.Build.MANUFACTURER;
		}
	public String devicemodel(){
		
		return android.os.Build.MODEL;
		}
	public String IMEI(){
		TelephonyManager manager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
		return manager.getDeviceId();
	}

}
