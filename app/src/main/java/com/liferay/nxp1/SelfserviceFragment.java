package com.liferay.nxp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SelfserviceFragment extends Fragment {
	public FragmentTransaction ft;

	public SelfserviceFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_selfservice, container, false);

		final Button button1 = (Button) view.findViewById(R.id.leaverequestbutton);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getActivity(), "show leaverequest", Toast.LENGTH_SHORT).show();
			}
		});

		final Button button2 = (Button) view.findViewById(R.id.shiftchangebutton);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(getActivity(),"show shiftchange",Toast.LENGTH_SHORT).show();
				ft = getFragmentManager().beginTransaction();
				ShiftChangeFragment shiftchange = new ShiftChangeFragment();
				ft.replace(R.id.fragment_container, shiftchange);
				ft.addToBackStack("tag");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		final Button button3 = (Button) view.findViewById(R.id.payslipbutton);
		button3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Toast.makeText(getActivity(),"show payslip",Toast.LENGTH_SHORT).show();
				ft = getFragmentManager().beginTransaction();
				PaySlipFragment payslip = new PaySlipFragment();
				ft.replace(R.id.fragment_container, payslip);
				ft.addToBackStack("tag");
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				ft.commit();
			}
		});

		return view;
	}
}
