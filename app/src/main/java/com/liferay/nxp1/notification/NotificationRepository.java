package com.liferay.nxp1.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Víctor Galán Grande
 */

public class NotificationRepository {

	public interface Listener {
		void onNotificationReceived(List<ServerNotification> notifications);
	}

	private static NotificationRepository instance = null;

	private List<ServerNotification> notificationList;
	private Listener listener;

	private NotificationRepository(){
		notificationList = new ArrayList<>();
	}

	public static NotificationRepository getInstance(){
		if(instance == null) {
			instance = new NotificationRepository();
		}
		return instance;
	}

	public List<ServerNotification> getNotificationList() {
		return notificationList;
	}

	public void saveNotification(ServerNotification notification) {
		notificationList.add(0, notification);

		if (listener != null) {
			listener.onNotificationReceived(notificationList);
		}
	}

	public void setListener(Listener listener) {
		this.listener = listener;
		listener.onNotificationReceived(notificationList);
	}

	public void removeListener() {
		this.listener = null;
	}
}
