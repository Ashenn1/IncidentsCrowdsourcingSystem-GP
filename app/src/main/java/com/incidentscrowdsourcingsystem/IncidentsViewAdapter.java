package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    List<Integer>UpVote;
    List<Integer>DownVote;

    private Context MyContext;

    public IncidentsViewAdapter(List<String> titleReport, List<String> userName,List<String>description,List<String>severity,List<String>category,List<Integer>upVote,List<Integer>downVote ,Context myContext) {
        TitleReport = titleReport;
        UserName = userName;
        MyContext = myContext;
        Description=description;
        Severity=severity;
        Category=category;
        UpVote=upVote;
        DownVote=downVote;
    }


    @NonNull
    @Override
    public IncidentsViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=  LayoutInflater.from(MyContext) ;
        view =inflater.inflate(R.layout.list,parent,false);
        MyViewHolder ViewHolder = new MyViewHolder(view);



        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IncidentsViewAdapter.MyViewHolder holder, int position) {
        holder.TitleIncident.setText(TitleReport.get(position));
        holder.NameUser.setText(UserName.get(position));



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
