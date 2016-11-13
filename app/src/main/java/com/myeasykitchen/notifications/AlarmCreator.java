package com.myeasykitchen.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by sonal on 11/13/16.
 */
public class AlarmCreator {

    public static void create(Context context, Calendar calendar, int uniqueId, String title, String text)
    {
//        Calendar calendar = Calendar.getInstance();
//
//        int minute = 46;
//        calendar.set(Calendar.HOUR_OF_DAY,19);
//        calendar.set(Calendar.MINUTE, minute);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("unique_id", uniqueId);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}
