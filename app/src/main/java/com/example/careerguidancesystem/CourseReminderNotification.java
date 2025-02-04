package com.example.careerguidancesystem;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;

public class CourseReminderNotification extends BroadcastReceiver {

    private static final String CHANNEL_ID = "course_reminder_channel";
    private static final int NOTIFICATION_ID = 1;
    private static final int REMINDER_REQUEST_CODE = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context);
    }

    // Schedule the daily notification
    public static void scheduleDailyReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Set up the time for daily reminder (e.g., 9:00 AM)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9); // 9 AM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // If the set time is before the current time, schedule for the next day
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        Intent intent = new Intent(context, CourseReminderNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REMINDER_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Schedule the repeating alarm
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    // Create and send the notification
    private void sendNotification(Context context) {
        // Set up the Notification Channel (necessary for Android 8.0+)
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Course Reminder", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Reminds user to select a course");
            notificationManager.createNotificationChannel(channel);
        }

        // Set up the intent for tapping on the notification
        Intent notificationIntent = new Intent(context, MainActivity.class); // Change to the activity you want to open
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(notificationIntent);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.guidance) // Replace with your notification icon
                .setContentTitle("Course Selection Reminder")
                .setContentText("It's time to select a course!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

        // Send the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

