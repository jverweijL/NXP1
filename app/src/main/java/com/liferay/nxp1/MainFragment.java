package com.liferay.nxp1;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.asset.AssetEntry;
import com.liferay.mobile.screens.asset.display.AssetDisplayListener;
import com.liferay.mobile.screens.asset.list.AssetListScreenlet;
import com.liferay.mobile.screens.base.list.BaseListListener;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.dlfile.display.video.VideoDisplayScreenlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment
	implements AssetDisplayListener, MediaPlayer.OnCompletionListener {

	private VideoDisplayScreenlet videoDisplayScreenlet;
	private TextView textView;
	private AssetListScreenlet news;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_main, container, false);

		videoDisplayScreenlet =
			(VideoDisplayScreenlet) view.findViewById(R.id.asset_display_screenlet);
		textView = (TextView) view.findViewById(R.id.no_video_textview);
		news = (AssetListScreenlet) view.findViewById(R.id.newslist);

		if (isMandatoryVideoWatched()) {
			videoDisplayScreenlet.setVisibility(View.GONE);
			textView.setVisibility(View.VISIBLE);
		} else {
			videoDisplayScreenlet.setVisibility(View.VISIBLE);
			textView.setVisibility(View.GONE);

			videoDisplayScreenlet.load();
			videoDisplayScreenlet.setListener(this);
		}

		if (SessionContext.isLoggedIn()) {
			//Session currentSession = SessionContext.createSessionFromCurrentSession();



			//name			tagId
			//department1	42336
			//global		42316
			//news			41156
			//required		36875
			//team2			42322

			ArrayList<Long> tagIds = new ArrayList<Long>();
			tagIds.add(42316L);

			User user = SessionContext.getCurrentUser();
			if (user.getEmail().toLowerCase().equals("admin@nxp.com")) {
				tagIds.add(42336L);
				tagIds.add(42322L);
			} else if (user.getEmail().toLowerCase().equals("user1@nxp.com")){
				tagIds.add(42336L);
			} else if (user.getEmail().toLowerCase().equals("user2@nxp.com")){
				tagIds.add(42322L);
			} else if (user.getEmail().toLowerCase().equals("manager1@nxp.com")){
				tagIds.add(42336L);
			} else if (user.getEmail().toLowerCase().equals("manager2@nxp.com")){
				tagIds.add(42322L);
			}
			

			// set proper query to display news items
			HashMap<String,Object> tags = new HashMap<>();

			//check this for field/object names com/liferay/portlet/asset/service/persistence/AssetEntryQuery.html
			tags.put("anyTagIds",tagIds.toArray());
			news.setCustomEntryQuery(tags);
			news.loadPage(0);
		}

		return view;
	}

	public void onListPageReceived() {

	}

	@Override
	public void onRetrieveAssetSuccess(AssetEntry assetEntry) {
		VideoView videoView = (VideoView) getView().findViewById(R.id.liferay_video_asset);
		videoView.setOnCompletionListener(this);
	}

	@Override
	public void error(Exception e, String userAction) {
		Log.d("", "");
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		videoDisplayScreenlet.setVisibility(View.GONE);
		textView.setVisibility(View.VISIBLE);

		setMandatoryVideoWatched();
	}

	public boolean isMandatoryVideoWatched() {
		SharedPreferences sharedPreferences =
			getActivity().getSharedPreferences("videoWatched", MODE_PRIVATE);
		return sharedPreferences.getBoolean("isVideoWatched", false);
	}

	public void setMandatoryVideoWatched() {
		SharedPreferences.Editor editor =
			getActivity().getSharedPreferences("videoWatched", MODE_PRIVATE).edit();
		editor.putBoolean("isVideoWatched", true);
		editor.commit();
	}
}
