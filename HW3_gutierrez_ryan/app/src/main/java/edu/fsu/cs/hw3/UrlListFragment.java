package edu.fsu.cs.hw3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.String;

public class UrlListFragment extends Fragment {

    private static final String TAG = UrlListFragment.class.getCanonicalName();
    ListView listView;

    public UrlListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Handle new url if received in SmsReceiver

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_url_list, container, false);

        // Set up url ListView
        listView = (ListView) view.findViewById(R.id.ListView_urlList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = listView.getItemAtPosition(i).toString();
                // Toast.makeText(view.getContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
