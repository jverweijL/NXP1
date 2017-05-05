package com.liferay.nxp1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.liferay.nxp1.adapter.PagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPager extends Fragment {

	public FragmentPager() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_pager, container, false);

		ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
		PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),
				getContext().getApplicationContext());
		viewPager.setAdapter(pagerAdapter);

		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

		return view;
	}
}
