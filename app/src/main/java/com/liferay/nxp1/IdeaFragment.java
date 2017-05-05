package com.liferay.nxp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liferay.mobile.screens.ddl.form.DDLFormListener;
import com.liferay.mobile.screens.ddl.form.DDLFormScreenlet;
import com.liferay.mobile.screens.ddl.model.DocumentField;
import com.liferay.mobile.screens.ddl.model.Record;

import org.json.JSONObject;

import java.util.Map;


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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IdeaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdeaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IdeaFragment newInstance(String param1, String param2) {
        IdeaFragment fragment = new IdeaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_idea, container, false);

        //TODO we can make this more flexible by setting recordsetid and structureid programmatically
        DDLFormScreenlet form = (DDLFormScreenlet) view.findViewById(R.id.ideaform);
        form.setListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        MainFragment mainfragment = new MainFragment();
        ft.replace(R.id.fragment_container,mainfragment);
        //ft.addToBackStack("tag");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
