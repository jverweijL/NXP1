package com.liferay.nxp1;

import android.content.Context;
import android.util.AttributeSet;
import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;
import com.liferay.mobile.screens.webcontent.display.interactor.WebContentDisplayEvent;
import java.util.Locale;

/**
 * @author Víctor Galán Grande
 */

public class WebContentCustomScreenlet extends WebContentDisplayScreenlet {
	public WebContentCustomScreenlet(Context context) {
		super(context);
	}

	public WebContentCustomScreenlet(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WebContentCustomScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public WebContentCustomScreenlet(Context context, AttributeSet attrs, int defStyleAttr,
		int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	public Locale getLocale() {
		if (Locale.getDefault().getLanguage().equals("zh")) {
			return Locale.SIMPLIFIED_CHINESE;
		}
		else {
			return super.getLocale();
		}
	}
}
