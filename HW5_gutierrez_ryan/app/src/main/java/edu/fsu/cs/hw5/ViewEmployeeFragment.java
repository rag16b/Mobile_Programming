package edu.fsu.cs.hw5;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ViewEmployeeFragment extends Fragment {
    private OnUserFragmentInteractionListener mListener;
    public static final String ARG_URI = "uri";

    private Cursor mCursor;

    private Button logoutButton;
    private Button delAccButton;

    private View mRootView;

    private TextView empid, name, email, gender, dept;

    public static ViewEmployeeFragment getInstance(Uri uri) {
        ViewEmployeeFragment fragment = new ViewEmployeeFragment();
        Bundle data = new Bundle();
        data.putParcelable(ARG_URI, uri);
        fragment.setArguments(data);
        return fragment;
    }

    public ViewEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        logoutButton = (Button) rootView.findViewById(R.id.button_logout);
        delAccButton = (Button) rootView.findViewById(R.id.button_deleteAcc);

        empid = (TextView) rootView.findViewById(R.id.textView_empid);
        name = (TextView) rootView.findViewById(R.id.textView_name);
        email = (TextView) rootView.findViewById(R.id.textView_email);
        gender = (TextView) rootView.findViewById(R.id.textView_gender);
        dept = (TextView) rootView.findViewById(R.id.textView_dept);

        mCursor = getActivity().getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
        mCursor.moveToLast();
        empid.setText(mCursor.getString(mCursor.getColumnIndex("Empid")));
        name.setText(mCursor.getString(mCursor.getColumnIndex("Name")));
        email.setText(mCursor.getString(mCursor.getColumnIndex("Email")));
        gender.setText(mCursor.getString(mCursor.getColumnIndex("Gender")));
        dept.setText(mCursor.getString(mCursor.getColumnIndex("Department")));

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnUserFragmentInteractionListener fragListener = (OnUserFragmentInteractionListener) getActivity();
                fragListener.onLoggedOut();
            }
        });
        /*delAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnUserFragmentInteractionListener fragListener = (OnUserFragmentInteractionListener) getActivity();
                fragListener.onUserDeleted();
            }
        });*/

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserFragmentInteractionListener) {
            mListener = (OnUserFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnUserFragmentInteractionListener {
        void onLoggedOut();
        void onUserDeleted(int res);
    }
}
