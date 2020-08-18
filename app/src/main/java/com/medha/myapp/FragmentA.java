package com.medha.myapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentA extends Fragment {

	private static final String TAG = FragmentA.class.getSimpleName();

	private Button btnSend;
	private EditText etFirstNumber, etSecondNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_a, container, false);

		etFirstNumber = (EditText) view.findViewById(R.id.etFirstNumber);
		etSecondNumber = (EditText) view.findViewById(R.id.etSecondNumber);

		btnSend = (Button) view.findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sendData();
			}
		});

		return view;
	}

	private void sendData() {

		 String firstNum = etFirstNumber.getText().toString();
		String secondNum = etSecondNumber.getText().toString();

		MyListener myListener = (MyListener) getActivity();
		myListener.addTwoNumbers(firstNum, secondNum);
	}
}
