package edu.fsu.cs.hw4;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.fsu.cs.hw4.R;

public class DownloadDialog extends AppCompatDialogFragment {
    private TextView et_url;
    private Button bt_download;
    private DownloadDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.download_dialog, null);

        builder.setView(view);

        et_url = (TextView) view.findViewById(R.id.url);
        bt_download = (Button) view.findViewById(R.id.button_download);

        bt_download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = et_url.getText().toString();
                listener.applyURL(url);
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (DownloadDialogListener) context;
    }

    public interface DownloadDialogListener {
        void applyURL(String url);
    }
}
