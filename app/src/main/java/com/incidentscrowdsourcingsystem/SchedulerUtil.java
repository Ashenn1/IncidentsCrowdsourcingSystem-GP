package com.incidentscrowdsourcingsystem;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

public class SchedulerUtil {

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, NotificationJobService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //Schedule to run NotificationJobService every 8 ~ 25 minutes
            JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
            builder.setMinimumLatency(500 * 1000); // Wait at least 8~ minutes
            builder.setOverrideDeadline(1500 * 1000); // Maximum delay 25~ minutes

            JobScheduler jobScheduler = (JobScheduler)context.getSystemService(context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(builder.build());
        }

    }
}
