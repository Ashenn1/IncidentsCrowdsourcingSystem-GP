package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification> {

    private Context mContext;
    private List<Notification> notificationList = new ArrayList<>();

    public NotificationAdapter(@NonNull Context context, ArrayList<Notification> list) {
        super(context, 0 , list);
        mContext = context;
        notificationList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_notification_list_fragement,parent,false);

        Notification currentNotification = notificationList.get(position);


        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(currentNotification.getTitle());

        TextView Description = (TextView) listItem.findViewById(R.id.Description);
        Description.setText(currentNotification.getDescription());

        return listItem;
    }
}
