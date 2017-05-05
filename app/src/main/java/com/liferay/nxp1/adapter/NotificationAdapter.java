package com.liferay.nxp1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.liferay.nxp1.notification.ServerNotification;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Text;

/**
 * @author Víctor Galán Grande
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

	private List<ServerNotification> serverNotifications;

	public NotificationAdapter(List<ServerNotification> serverNotifications) {
		this.serverNotifications = serverNotifications;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		ServerNotification notification = serverNotifications.get(position);
		String notificationString = notification.getType() + " - " + notification.getContent();

		holder.bind(notificationString);
	}

	@Override
	public int getItemCount() {
		return serverNotifications.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		private TextView textView;

		ViewHolder(View itemView) {
			super(itemView);

			textView = (TextView) itemView.findViewById(android.R.id.text1);
		}

		void bind(String notificationText) {
			textView.setText(notificationText);
		}
	}
}
