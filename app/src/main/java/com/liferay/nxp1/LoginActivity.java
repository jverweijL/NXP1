package com.liferay.nxp1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.liferay.mobile.android.callback.typed.JSONArrayCallback;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.phone.PhoneService;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements LoginListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		LoginScreenlet loginScreenlet = (LoginScreenlet) findViewById(R.id.login);
		loginScreenlet.setListener(this);

		((EditText) findViewById(R.id.liferay_login)).setText("admin@nxp.com");
		((EditText) findViewById(R.id.liferay_password)).setText("liferay");
	}

	@Override
	public void onLoginSuccess(User user) {
		Toast.makeText(LoginActivity.this, "Login succes", Toast.LENGTH_SHORT).show();

		if (!isContactInformationChecked()) {
			getPhoneNumber(user);
		} else {
			startActivity(new Intent(this, MainActivity.class));
		}
	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
	}

	private void getPhoneNumber(User user) {
		Session session = SessionContext.createSessionFromCurrentSession();

		session.setCallback(new JSONArrayCallback() {
			@Override
			public void onFailure(Exception exception) {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}

			@Override
			public void onSuccess(JSONArray result) {
				try {
					if (result.length() > 0) {
						String phoneNumber = result.getJSONObject(0).getString("number");
						showContactCheckDialog(result.getJSONObject(0));
					}
					else {
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		PhoneService phoneService = new PhoneService(session);
		try {
			phoneService.getPhones("com.liferay.portal.kernel.model.Contact",
				Long.parseLong((String) user.getValues().get("contactId")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showContactCheckDialog(final JSONObject phone) throws JSONException {
		new AlertDialog.Builder(this).setTitle("Is this your current mobile phone? ")
			.setMessage(phone.getString("number"))
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					setContactInformationChecked();
					startActivity(new Intent(LoginActivity.this, MainActivity.class));
				}
			})
			.setNegativeButton(R.string.no_phone_changed, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					try {
						showNewPhoneDialog(phone);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			})
			.show();
	}

	private void showNewPhoneDialog(final JSONObject phone) throws JSONException {

		final EditText input = new EditText(this);
		LinearLayout.LayoutParams lp =
			new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		lp.setMargins(10, 0, 10, 0);
		input.setLayoutParams(lp);

		new AlertDialog.Builder(this).setTitle("What is your new phone? ")
			.setView(input)
			.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

					updatePhone(phone, input.getText().toString());
				}
			})
			.setCancelable(false)
			.show();
	}

	private void updatePhone(JSONObject phone, String newPhone) {
		Session session = SessionContext.createSessionFromCurrentSession();

		session.setCallback(new JSONObjectCallback() {
			@Override
			public void onFailure(Exception exception) {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}

			@Override
			public void onSuccess(JSONObject result) {
				setContactInformationChecked();
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
			}
		});

		PhoneService phoneService = new PhoneService(session);
		try {
			phoneService.updatePhone(Long.parseLong(phone.getString("phoneId")), newPhone, "",
				Long.parseLong(phone.getString("typeId")), phone.getBoolean("primary"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isContactInformationChecked() {
		SharedPreferences sharedPreferences =
			getSharedPreferences("contactInformationChecked", MODE_PRIVATE);
		return sharedPreferences.getBoolean("isContactInformationChecked", false);
	}

	public void setContactInformationChecked() {
		SharedPreferences.Editor editor =
			getSharedPreferences("contactInformationChecked", MODE_PRIVATE).edit();
		editor.putBoolean("isContactInformationChecked", true);
		editor.commit();
	}
}
