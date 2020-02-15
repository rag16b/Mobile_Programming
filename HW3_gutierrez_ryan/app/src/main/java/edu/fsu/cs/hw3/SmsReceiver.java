package edu.fsu.cs.hw3;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    public SmsReceiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an SMS broadcast.

        // TODO: Extract url from sms and add to UrlListFragment
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String extractUrlFromIntent(Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        String url = "";
        for(int i = 0; i < messages.length; i++) {
            String messagebody = messages[i].getMessageBody();
            url += messagebody;
        }

        return url;
    }

}

