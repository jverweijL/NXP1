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
import com.liferay.mobile.screens.asset.AssetEntry;
import com.liferay.mobile.screens.asset.display.AssetDisplayListener;
import com.liferay.mobile.screens.dlfile.display.video.VideoDisplayScreenlet;

import static android.content.Context.MODE_PRIVATE;

public class MainFragment extends Fragment
	implements AssetDisplayListener, MediaPlayer.OnCompletionListener {

	private VideoDisplayScreenlet videoDisplayScreenlet;
	private TextView textView;

	public MainFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_main, container, false);

		videoDisplayScreenlet =
			(VideoDisplayScreenlet) view.findViewById(R.id.asset_display_screenlet);
		textView = (TextView) view.findViewById(R.id.no_video_textview);

		if (isMandatoryVideoWatched()) {
			videoDisplayScreenlet.setVisibility(View.GONE);
			textView.setVisibility(View.VISIBLE);
		} else {
			videoDisplayScreenlet.setVisibility(View.VISIBLE);
			textView.setVisibility(View.GONE);

			videoDisplayScreenlet.load();
			videoDisplayScreenlet.setListener(this);
		}

		return view;
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
