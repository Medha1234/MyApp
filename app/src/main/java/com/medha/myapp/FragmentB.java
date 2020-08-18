package com.medha.myapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {

	private static final String TAG = FragmentB.class.getSimpleName();

	private TextView txvResult;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_b, container, false);

		txvResult = (TextView) view.findViewById(R.id.txvResult);

		return view;
	}

	public void addTwoNumbers(String x, String y) {

		String result = x +" commented " + y;
		txvResult.setText(result);
	}
}
