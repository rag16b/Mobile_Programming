package edu.fsu.cs.hw3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.String;

public class UrlListFragment extends Fragment {

    private static final String TAG = UrlListFragment.class.getCanonicalName();

    public UrlListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Handle new url if received in SmsReceiver

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_url_list, container, false);

        // TODO: Set up url ListView

        return view;
    }
}
