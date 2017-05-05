package com.liferay.nxp1.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.liferay.nxp1.MainFragment;
import com.liferay.nxp1.NotificationsFragment;
import com.liferay.nxp1.R;
import com.liferay.nxp1.RatingFragment;


/**
 * @author Víctor Galán Grande
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

	private final Context context;

	public PagerAdapter(FragmentManager fm, Context context) {
		super(fm);

		this.context = context;
	}

	@Override
	public Fragment getItem(int position) {
		if (position == 0) {
			return new MainFragment();
		} else {
			return new NotificationsFragment();
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		if (position == 0) {

			return context.getString(R.string.news);
		} else {
			return context.getString(R.string.notifications);
		}
	}
}
