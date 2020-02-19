package edu.fsu.cs.hw3;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//import androidx.annotation.Nullable;
//import androidx.lifecycle.ViewModelProvider;

import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UrlListFragment extends Fragment {

    private static final String TAG = UrlListFragment.class.getCanonicalName();
    private ListView listView;
    //private SharedViewModel viewModel;
    private UrlListFragListener listener;

    List<String> urlList;
    ArrayAdapter<String> arrayAdapter;

    public UrlListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        Bundle bundle = getArguments();
        String newElement;
        if (bundle != null) {
            if (bundle.containsKey("message")) {
                newElement = bundle.getString("message");
                urlList.add(newElement);
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    public interface UrlListFragListener {
        void onUrlSelect(String input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_url_list, container, false);

        // Set up url ListView
        listView = (ListView) view.findViewById(R.id.ListView_urlList);
        String[] urls = new String[] {
                "http://www.google.com",
                "http://www.netflix.com",
                "hulu.com"
        };
        urlList = new ArrayList<String>(Arrays.asList(urls));
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, urlList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = listView.getItemAtPosition(i).toString();
                listener.onUrlSelect(s);
                //viewModel.setData(s);
                //Toast.makeText(view.getContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UrlListFragListener)
            listener = (UrlListFragListener)context;
        else
            throw new RuntimeException(context.toString() + " must implement UrlListFragListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
