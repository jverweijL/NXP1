package com.liferay.nxp1.notification;

import android.support.annotation.NonNull;
import com.liferay.mobile.screens.push.AbstractPushReceiver;

/**
 * @author Víctor Galán Grande
 */

public class PushReceiver extends AbstractPushReceiver {
	@NonNull
	@Override
	protected Class getPushServiceClass() {
		return PushService.class;
	}
}
