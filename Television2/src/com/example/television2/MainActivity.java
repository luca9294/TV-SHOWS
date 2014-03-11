package com.example.television2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;
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
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	TraktAPI api;
	private JSONObject data, object;
	private JSONArray data2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.sample);
		CustomAdapter customAdapter;
		try {

			final Vector<Vector<String>> vector = getList();
	
			int mean = vector.size() / 2;
			customAdapter = new CustomAdapter(vector.subList(0, mean),
					vector.subList(mean, vector.size()),this);
			listView.setAdapter(customAdapter);
			listView.setOnItemClickListener(new OnItemClickListener(){

				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent intent = new Intent(getApplicationContext(),TVShowActivity.class);
					intent.putExtra("toSearch", vector.get(arg2).get(1));
					startActivity(intent);
					
				}
				
				
				
				
			});
			
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e) {
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

	public Vector<Vector<String>> getList() throws InterruptedException,
			ExecutionException, JSONException {
		api = new TraktAPI(this.getApplicationContext());
		DataGrabber e = new DataGrabber(this);
		e.execute();
		JSONArray array = e.get();
		Vector<Vector<String>> list = new Vector<Vector<String>>();

		for (int i = 0; i < 50; i++) {
			JSONObject object = array.getJSONObject(i);
			String URL = object.getString("poster");
			URL = URL.replace(".jpg", "-300.jpg");
			String title = object.getString("title");
			Vector<String> singola = new Vector<String>();
			singola.add(URL);
			singola.add(title);
			list.add(singola);

		}

		return list;

	}

	private class DataGrabber extends AsyncTask<String, Void, JSONArray> {
		private ProgressDialog progressdialog;
		private Context parent;
		private String id;

		public DataGrabber(Context parent) {
			this.parent = parent;

		}

		@Override
		protected void onPreExecute() {
			// progressdialog = ProgressDialog.show(parent,"",
			// "Retrieving data ...", true);
		}

		@Override
		protected JSONArray doInBackground(String... params) {
			data2 = api.getDataArrayFromJSON(
					"shows/trending.json/361cd031c2473b06997c87c25ec9c057",
					true);

			// data2 = api.getDataArrayFromJSON("show/season.json/%k/revenge/3",
			// true);

			return data2;

		}

	}
}
