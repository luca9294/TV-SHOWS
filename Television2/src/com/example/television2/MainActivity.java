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
	private JSONObject data;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		api = new TraktAPI (this.getApplicationContext());
		DataGrabber e = new DataGrabber(this);
		e.execute();
		

	//JSONArray array = api.getDataArrayFromJSON("http://api.trakt.tv/search/shows.json/361cd031c2473b06997c87c25ec9c057/o.c.");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    private class DataGrabber extends AsyncTask<String,Void,Boolean> {
		private ProgressDialog progressdialog;
		private Context parent;
		
		public DataGrabber(Context parent) {
			this.parent = parent;
		}

		@Override
		protected void onPreExecute() {
		   // progressdialog = ProgressDialog.show(parent,"", "Retrieving data ...", true);
		}
    	
		@Override
		protected Boolean doInBackground(String... params) {
			//data = api.getDataObjectFromJSON("show/summary.json/%k/revenge"+"/extended",true); 
			data2 = api.getDataArrayFromJSON("show/season.json/%k/revenge/3", true);
			
			Log.e("TEST", data2.toString());
			if (data==null) {
				return false;
			}
			return true;
		}
		
		
		
		
		
		
		
		
	}}
	
	
	
	



