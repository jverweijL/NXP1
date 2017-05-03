package com.liferay.nxp1.notification;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Víctor Galán Grande
 */

public class ServerNotification implements Parcelable {
	private final String type;
	private final String content;

	public ServerNotification(String type, String content) {
		this.type = type;
		this.content = content;
	}

	protected ServerNotification(Parcel in) {
		type = in.readString();
		content = in.readString();
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(type);
		dest.writeString(content);
	}

	public static final Creator<ServerNotification> CREATOR = new Creator<ServerNotification>() {
		@Override
		public ServerNotification createFromParcel(Parcel in) {
			return new ServerNotification(in);
		}

		@Override
		public ServerNotification[] newArray(int size) {
			return new ServerNotification[size];
		}
	};
}
