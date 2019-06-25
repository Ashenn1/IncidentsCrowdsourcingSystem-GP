package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IncidentsViewAdapter extends RecyclerView.Adapter<IncidentsViewAdapter.MyViewHolder> {
    private List<String> TitleReport;
    private List<String>UserName;
    private  List<String>Description;
    private  List<String>Severity;
    private  List<String>Category;
    private  List<String>Date;
    private  List<Integer>UpVote;
    private  List<Integer>DownVote;
    private  List<Integer>IncidentId;
    private  List<String>IncidentImage ;

    private Context MyContext;

    public IncidentsViewAdapter(List<String> titleReport, List<String> userName, List<String>description, List<String>severity, List<String>category, List<String>date, List<Integer>upVote, List<Integer>downVote, List<Integer>incidentId, List<String>incidentImage , Context myContext) {
        TitleReport = titleReport;
        UserName = userName;
        MyContext = myContext;
        Description=description;
        Severity=severity;
        Category=category;
        UpVote=upVote;
        DownVote=downVote;
        IncidentId= incidentId;
        Date=date;
        IncidentImage = incidentImage;
    }


    @NonNull
    @Override
    public IncidentsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view;
        LayoutInflater inflater=  LayoutInflater.from(MyContext) ;
        view =inflater.inflate(R.layout.list,parent,false);
        final MyViewHolder ViewHolder = new MyViewHolder(view);
        final int position= ViewHolder.getAdapterPosition();

        ViewHolder.Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent (MyContext,IncidentReportActivity.class);
                Log.d("7amada","on click error " + ViewHolder.getAdapterPosition());
                i.putExtra("IncidentDescription", Description.get(ViewHolder.getAdapterPosition()));
                i.putExtra("IncidentTitle",TitleReport.get(ViewHolder.getAdapterPosition()));
                i.putExtra("IncidentSeverity",Severity.get(ViewHolder.getAdapterPosition()));
                i.putExtra("IncidentCategory",Category.get(ViewHolder.getAdapterPosition()));
                i.putExtra("UserName",UserName.get(ViewHolder.getAdapterPosition()));
                i.putExtra("IncidentDate",Date.get(ViewHolder.getAdapterPosition()));
                i.putExtra("UpVoteNum",UpVote.get(ViewHolder.getAdapterPosition()));
                i.putExtra("DownVoteNum",DownVote.get(ViewHolder.getAdapterPosition()));
                i.putExtra("IncidentId",IncidentId.get(ViewHolder.getAdapterPosition()));
                //i.putExtra("IncidentImage",IncidentImage.get(ViewHolder.getAdapterPosition()));
                MyContext.startActivity(i);
            }
        });



        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IncidentsViewAdapter.MyViewHolder holder, final int position) {
        holder.TitleIncident.setText(TitleReport.get(position));
        holder.NameUser.setText(UserName.get(position));
        final int numOfClick = 0;


    }


    @Override
    public int getItemCount() {
        return TitleReport.size() ;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView TitleIncident;
        TextView NameUser;
        LinearLayout Container;
        //Button UpVoteBtn;
        //Button DownVoteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleIncident = itemView.findViewById(R.id.Title);
            NameUser = itemView.findViewById(R.id.userName);
            Container = itemView.findViewById(R.id.container);
        }
    }

}

