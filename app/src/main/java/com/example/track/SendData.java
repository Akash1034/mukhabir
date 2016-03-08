package com.example.track;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendData extends AsyncTask<String, Void, String>{
	InputStream is=null;
	MainActivity activity;
	@Override
	protected String doInBackground(String... params)
	{
		// TODO Auto-generated method stub
		//activity=new MainActivity();
		JSONObject jsonObject=new JSONObject();
		
		try{
			
			HttpClient server_client=new DefaultHttpClient();
			HttpPost httpPost_request=new HttpPost("http://23.94.21.18:8808/postMobileData");
			jsonObject.put("deviceId","aaa");
			jsonObject.put("latitude","aaa");
			jsonObject.put("longitude","aaa");
			jsonObject.put("address","aaa");
			jsonObject.put("battery",12);
			String json=jsonObject.toString();
			/*List<NameValuePair> param=new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("data", json));
			UrlEncodedFormEntity entities=new UrlEncodedFormEntity(param);
		    entities.setContentEncoding(HTTP.UTF_8);*/
			httpPost_request.setEntity(new StringEntity(json, "UTF8"));
			httpPost_request.setHeader("Content-type", "application/json");
			HttpResponse response=server_client.execute(httpPost_request);
			HttpEntity entity = response.getEntity();
	        is = entity.getContent();
			}catch(Exception e){
				e.printStackTrace();
				Log.e("Data", "Data Not sent");
			}
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                is, "iso-8859-1"), 8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	        is.close();
	        Log.v("RESPOEEEEEEEEEEEEEEEEEE", sb.toString());
	    } catch (Exception e) {
	        Log.e("log_tag", "Error converting result " + e.toString());
	    }
	return "123";
		
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
