package edu.fsu.cs.hw5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewEmployeeFragment extends Fragment {
    private OnUserFragmentInteractionListener mListener;
    public static final String ARG_URI = "uri";

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
        // TODO: Get arguments
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        // TODO: setup UI

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
