package com.liferay.nxp1.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import com.liferay.mobile.push.bus.BusUtil;
import com.liferay.mobile.screens.push.AbstractPushService;
import com.liferay.nxp1.LoginActivity;
import com.liferay.nxp1.R;
import java.io.BufferedInputStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Víctor Galán Grande
 */

public class PushService extends AbstractPushService {

	@Override
	protected void processJSONNotification(final JSONObject json) throws JSONException {
		BusUtil.post(json);
	}
}
