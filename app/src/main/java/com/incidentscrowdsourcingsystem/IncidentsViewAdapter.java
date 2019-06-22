package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
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
    List<String>Description;
    List<String>Severity;
    List<String>Category;
    List<String>Date;
    List<Integer>UpVote;
    List<Integer>DownVote;
    List<Integer>IncidentId;

    private Context MyContext;

    public IncidentsViewAdapter(List<String> titleReport, List<String> userName,List<String>description,List<String>severity,List<String>category,List<String>date,List<Integer>upVote,List<Integer>downVote,List<Integer>incidentId ,Context myContext) {
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
    }


    @NonNull
    @Override
    public IncidentsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view;
        LayoutInflater inflater=  LayoutInflater.from(MyContext) ;
        Toast.makeText(MyContext, "Position of Item Clicked !", Toast.LENGTH_SHORT).show();
        view =inflater.inflate(R.layout.list,parent,false);
        final MyViewHolder ViewHolder = new MyViewHolder(view);
        ViewHolder.Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i=new Intent (MyContext,IncidentReportActivity.class);
             Log.d("7amada","on click error " + ViewHolder.getAdapterPosition());
//                Toast.makeText(MyContext, "Position of Item Clicked !"+ViewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
               i.putExtra("IncidentDescription", "Desc");
               i.putExtra("IncidentTitle",TitleReport.get(0));
               i.putExtra("IncidentSeverity",Severity.get(0));
               i.putExtra("IncidentCategory",Category.get(0));
               i.putExtra("UserName",UserName.get(0));
               i.putExtra("IncidentDate",Date.get(0));
               i.putExtra("UpVoteNum",UpVote.get(0));
               i.putExtra("DownVoteNum",DownVote.get(0));
               i.putExtra("IncidentId",IncidentId.get(0));
               MyContext.startActivity(i);

            }
        });
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IncidentsViewAdapter.MyViewHolder holder, final int position) {
        holder.TitleIncident.setText(TitleReport.get(position));
        holder.NameUser.setText(UserName.get(position));
        holder.DownVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vote;
                String type="DownVote";
                if( holder.DownVoteBtn.getText()==type)
                {
                     vote =DownVote.get(position)+ 1;
                    holder.DownVoteBtn.setText(vote);
                }
                else {

                    vote = DownVote.get(position)-1;
                    holder.DownVoteBtn.setText(type);
                }
            }});
        holder.UpVoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int vote;
                String type ="UpVote";
                if( holder.UpVoteBtn.getText()==type)
                {
                    vote =UpVote.get(position)+ 1;
                    holder.UpVoteBtn.setText(vote);
                }
                else {
                    vote = UpVote.get(position)-1;
                    holder.UpVoteBtn.setText(type);
                }
            }});

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
        Button UpVoteBtn;
        Button DownVoteBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleIncident = itemView.findViewById(R.id.Title);
            NameUser = itemView.findViewById(R.id.userName);
            Container = itemView.findViewById(R.id.container);
            UpVoteBtn= itemView.findViewById(R.id.upvote);
            DownVoteBtn=itemView.findViewById(R.id.downvote);
        }
    }

}

