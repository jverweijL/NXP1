package com.liferay.nxp1.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Víctor Galán Grande
 */

public class NotificationRepository {

	private static NotificationRepository instance = null;

	private List<ServerNotification> notificationList;

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
	}
}
