package com.liferay.nxp1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.liferay.mobile.screens.ddl.form.DDLFormListener;
import com.liferay.mobile.screens.ddl.form.DDLFormScreenlet;
import com.liferay.mobile.screens.ddl.model.DocumentField;
import com.liferay.mobile.screens.ddl.model.Record;
import java.util.Map;
import org.json.JSONObject;

public class LeaveRequestFragment extends Fragment implements DDLFormListener {

	public LeaveRequestFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_leave_request, container, false);

		DDLFormScreenlet form = (DDLFormScreenlet) view.findViewById(R.id.leaverequestform);
		form.setListener(this);

		return view;
	}

	@Override
	public void onDDLFormLoaded(Record record) {

	}

	@Override
	public void onDDLFormRecordLoaded(Record record, Map<String, Object> valuesAndAttributes) {

	}

	@Override
	public void onDDLFormRecordAdded(Record record) {
		Toast.makeText(getActivity(), "Your request is received!", Toast.LENGTH_SHORT).show();
		getActivity().findViewById(R.id.navigation_home).performClick();
	}

	@Override
	public void onDDLFormRecordUpdated(Record record) {

	}

	@Override
	public void onDDLFormDocumentUploaded(DocumentField documentField, JSONObject jsonObject) {

	}

	@Override
	public void onDDLFormDocumentUploadFailed(DocumentField documentField, Exception e) {

	}

	@Override
	public void error(Exception e, String userAction) {

	}
}
