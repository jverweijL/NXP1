package com.liferay.nxp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RatingFragment extends Fragment {

	public RatingFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_rating, container, false);
	}
}
