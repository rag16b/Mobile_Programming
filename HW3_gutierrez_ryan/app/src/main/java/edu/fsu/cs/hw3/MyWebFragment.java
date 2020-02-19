package edu.fsu.cs.hw3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebFragment extends Fragment {

    private WebView webView;

    public MyWebFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Handle url if clicked in UrlListFragment

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web, container, false);

        // Set up WebView
        webView = (WebView) view.findViewById(R.id.WebView_webView);
        webView.setWebViewClient(new WebViewClient());

        return view;
    }

    // public method called in MainActivity to send data here from the other fragment
    public void updateUrl(String input) {
        // if the string is missing http:// then append it
        if (input.indexOf("http://") != 0)
            input = "http://" + input;
        webView.loadUrl(input);
    }
}
