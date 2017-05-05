package com.liferay.nxp1.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import com.liferay.mobile.push.bus.BusUtil;
import com.liferay.mobile.screens.push.AbstractPushService;
import com.liferay.nxp1.R;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Víctor Galán Grande
 */

public class PushService extends AbstractPushService {

	@Override
	protected void processJSONNotification(final JSONObject json) throws JSONException {
		if (json.has("type")) {
			Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Notification notification = null;
			
			if (json.getString("type").equals("calendar")) {

				String title = json.getString("title");
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(json.getLong("startTime"));

				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hours = calendar.get(Calendar.HOUR);
				int minute = calendar.get(Calendar.MINUTE);

				String contentText =
					String.format(Locale.UK, "%s/%s at %d:%02d", month, day, hours, minute);

				notification =
					new NotificationCompat.Builder(this).setContentTitle("New event: " + title)
						.setContentText(contentText)
						.setAutoCancel(true)
						.setSound(uri)
						.setVibrate(new long[] { 2000, 1000, 2000, 1000 })
						.setSmallIcon(R.mipmap.ic_launcher)
						.build();

				saveNotification(new ServerNotification("Calendar", title + " " + contentText));
			} else if (json.getString("type").equals("teamMessage")) {
				String author = json.getString("author");
				String message = json.getString("message");

				notification = new NotificationCompat.Builder(this).setContentTitle(
					"Team message from " + author)
					.setContentText(message)
					.setAutoCancel(true)
					.setSound(uri)
					.setVibrate(new long[] { 2000, 1000, 2000, 1000 })
					.setSmallIcon(R.mipmap.ic_launcher)
					.build();

				saveNotification(new ServerNotification("Team message", author + ": " + message));
			}

			NotificationManager notificationManager =
				(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
			notificationManager.notify((int) System.currentTimeMillis() / 10000, notification);
		} else {
			BusUtil.post(json);
		}
	}

	private void saveNotification(final ServerNotification notification) {

		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				NotificationRepository.getInstance().saveNotification(notification);
			}
		});
	}
}
