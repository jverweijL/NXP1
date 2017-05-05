package com.liferay.nxp1;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.liferay.nxp1.adapter.NotificationAdapter;
import com.liferay.nxp1.notification.NotificationRepository;
import com.liferay.nxp1.notification.ServerNotification;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment implements NotificationRepository.Listener {

	private List<ServerNotification> serverNotifications = new ArrayList<>();
	private NotificationAdapter adapter;

	public NotificationsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.fragment_notifications, container, false);

		adapter = new NotificationAdapter(serverNotifications);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notification_list);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.HORIZONTAL));
		recyclerView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		NotificationRepository.getInstance().setListener(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		NotificationRepository.getInstance().removeListener();
	}

	@Override
	public void onNotificationReceived(List<ServerNotification> notifications) {
		serverNotifications.clear();
		serverNotifications.addAll(notifications);
		adapter.notifyDataSetChanged();
	}
}
