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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IdeaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IdeaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdeaFragment extends Fragment
        implements DDLFormListener {

    public IdeaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_idea, container, false);


        DDLFormScreenlet form = (DDLFormScreenlet) view.findViewById(R.id.ideaform);
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
        Toast.makeText(getActivity(),"Information received!",Toast.LENGTH_SHORT).show();
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
