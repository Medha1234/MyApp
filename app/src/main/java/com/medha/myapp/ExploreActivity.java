package com.medha.myapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ExploreActivity extends AppCompatActivity implements MyListener {

	private static final String TAG = ExploreActivity.class.getSimpleName();
	private FragmentManager manager;
	private String num1;
	private String num2;
    private myFirstReciever myFirstReciever;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore);

		TextView textView = (TextView) findViewById(R.id.textView);

		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String nameo = b.getString("place_name","Vacation Spot");

		textView.setText(nameo);

		manager = getFragmentManager();
		addFragmentA();
		addFragmentB();

		myFirstReciever = new myFirstReciever();

	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(myFirstReciever, filter);

	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(myFirstReciever);
	}

	public static class myReciever extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {

			Toast.makeText(context, "Airplane mode changed" , Toast.LENGTH_LONG).show();

		}
	}

	public class myFirstReciever extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {

			Toast.makeText(context, "Battery mode Changed" , Toast.LENGTH_LONG).show();

		}
	}
	public void addFragmentA() {

		FragmentA fragmentA = new FragmentA();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.containerFragmentA, fragmentA, "fragA");
		transaction.commit();
	}

	private void addFragmentB() {

		FragmentB fragmentB = new FragmentB();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.containerFragmentB, fragmentB, "fragB");
		transaction.commit();
	}

	@Override
	public void addTwoNumbers(String num1, String num2) {

		this.num1 = num1;
		this.num2 = num2;

		Toast.makeText(this, "Strings Received in Activity : " + num1 + " , " + num2, Toast.LENGTH_LONG).show();
	}


	public void loadAccountData(View view) {

		SharedPreferences sharedpreferences = getPreferences(Context.MODE_PRIVATE);
		String name= sharedpreferences.getString("name","Not Available");
		String comments= sharedpreferences.getString("comment","Not Available");
		Toast.makeText(this, "Retrieved from shared preference "+ name + " commented "+ comments , Toast.LENGTH_LONG).show();
		FragmentB fragmentB = (FragmentB) manager.findFragmentByTag("fragB");

		if (fragmentB != null) {
			fragmentB.addTwoNumbers(num1, num2);
		}

	}

	public void saveToFile(View view) {
		SharedPreferences sharedpreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor= sharedpreferences.edit();

		editor.putString("name", num1);
		editor.putString("comment", num2);
		editor.apply();
		Toast.makeText(this, "Saved in Shared Preferences" , Toast.LENGTH_LONG).show();
	}

}
