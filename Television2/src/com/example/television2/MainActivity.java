package com.example.television2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
	TraktAPI api;
	private JSONObject data, object;
	private JSONArray data2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageViewFromURL image = new ImageViewFromURL ("http://slurm.trakt.us/images/posters/10257.45.jpg"); 
		
		ImageView im = (ImageView)findViewById(R.id.imageView1);
		try {
			im.setImageBitmap(image.getImage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		
	
		
		try {
			Log.e("TEST", getTvShowJSON("Revenge").getJSONObject("ratings").getString("percentage"));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	


	
		
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
   
	
	
	public JSONObject getTvShowJSON(String string) throws InterruptedException, ExecutionException{
		api = new TraktAPI (this.getApplicationContext());
		DataGrabber e = new DataGrabber(this,string);
		e.execute();
		return e.get();
		
	}
	
	
	
	
	
	
	
	private class DataGrabber extends AsyncTask<String,Void,JSONObject> {
		private ProgressDialog progressdialog;
		private Context parent;
		private String id;
		
		public DataGrabber(Context parent, String id) {
			this.parent = parent;
			this.id = id;
		}

		@Override
		protected void onPreExecute() {
		   // progressdialog = ProgressDialog.show(parent,"", "Retrieving data ...", true);
		}
    	
		@Override
		protected JSONObject doInBackground(String... params) {
			data = api.getDataObjectFromJSON("show/summary.json/361cd031c2473b06997c87c25ec9c057/" + id,true); 
			
			
			//data2 = api.getDataArrayFromJSON("show/season.json/%k/revenge/3", true);
		
		return data;
		
		}
		
		
		
		
		
		
		
		
	}}
	
	
	
	



