package com.incidentscrowdsourcingsystem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class IncidentsViewAdapter extends RecyclerView.Adapter<IncidentsViewAdapter.ViewHolder> {

    private ArrayList<String> IncidentDescription;
    private ArrayList<String> Incidenttitle =new ArrayList<>();
    private ArrayList<String> IncidentSeverity =new ArrayList<>();
    private ArrayList<String> IncidentCategory=new ArrayList<>();
    private ArrayList<String> UserName =new ArrayList<>();
    private ArrayList <Integer> UpVote = new ArrayList <>();
    private ArrayList <Integer >DownVote =new ArrayList <>();
    private ArrayList<Date>IncidentDate= new ArrayList<>();
    private Context myContext;

    public IncidentsViewAdapter(Context myContext, ArrayList<String> incidentDescription, ArrayList<String> incidentTitle, ArrayList<String> incidentSeverity, ArrayList<String> incidentCategory, ArrayList<String> username,ArrayList<Integer> upvote, ArrayList<Integer> downvote) {
        this.myContext = myContext;
        IncidentDescription = incidentDescription;
        Incidenttitle=incidentTitle;
        IncidentSeverity=incidentSeverity;
        IncidentCategory=incidentCategory;
        UserName= username;
        UpVote=upvote;
        DownVote=downvote;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int i) {


        holder.Title.setText(Incidenttitle.get(i));
        holder.username.setText(UserName.get(i));
       // holder.upvote.setText(UpVote.get(i));
        //holder.downvote.setText(DownVote.get(i));
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext,IncidentReportActivity.class);
                intent.putExtra("Incident-Title",Incidenttitle.get(i));
                intent.putExtra("Incident-Description",IncidentDescription.get(i));
                intent.putExtra("Incident-Category",IncidentCategory.get(i));
                intent.putExtra("Incident-Severity",IncidentSeverity.get(i));
                intent.putExtra("UserName",UserName.get(i));
               // intent.putExtra("UpVote",UpVote.get(i));
                //intent.putExtra("DownVote",DownVote.get(i));
                myContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Incidenttitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView Title, username;
        Button upvote, downvote;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.profile_image);
            Title=itemView.findViewById(R.id.title);
            username=itemView.findViewById(R.id.Username);
            upvote=itemView.findViewById(R.id.like);
            downvote=itemView.findViewById(R.id.dislike);
            parent_layout= itemView.findViewById(R.id.parent_layout);

        }
    }

}
