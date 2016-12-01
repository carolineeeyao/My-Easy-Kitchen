package com.myeasykitchen.myeasykitchen.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.myeasykitchen.myeasykitchen.R;
import com.myeasykitchen.myeasykitchen.activities.MainActivity;

/**
 * Created by sonal on 11/12/16.
 */
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        int uniqueReqCode = intent.getIntExtra("unique_id", 0);
        String title = intent.getStringExtra("title");
        String text = intent.getStringExtra("text");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, uniqueReqCode, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true);

        notificationManager.notify(uniqueReqCode, builder.build());


    }

}
