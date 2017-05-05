package com.liferay.nxp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaySlipFragment extends Fragment {

	public PaySlipFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_pay_slip, container, false);
	}
}
