package com.liferay.nxp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liferay.mobile.screens.ddl.form.DDLFormListener;
import com.liferay.mobile.screens.ddl.model.DocumentField;
import com.liferay.mobile.screens.ddl.model.Record;

import org.json.JSONObject;

import java.util.Map;


public class ShiftChangeFragment extends Fragment implements DDLFormListener {

    public ShiftChangeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shift_change, container, false);
    }

    @Override
    public void onDDLFormLoaded(Record record) {

    }

    @Override
    public void onDDLFormRecordLoaded(Record record, Map<String, Object> valuesAndAttributes) {

    }

    @Override
    public void onDDLFormRecordAdded(Record record) {
        Toast.makeText(getActivity(),"Your request is received!",Toast.LENGTH_SHORT).show();
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
