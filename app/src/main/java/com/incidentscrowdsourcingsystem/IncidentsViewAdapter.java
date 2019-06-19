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
import android.widget.Toast;

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
        this.IncidentDescription = incidentDescription;
        this.Incidenttitle=incidentTitle;
        this.IncidentSeverity=incidentSeverity;
        this.IncidentCategory=incidentCategory;
        this.UserName= username;
        this.UpVote=upvote;
        this.DownVote=downvote;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list,viewGroup,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {

       try {
           holder.Title.setText(Incidenttitle.get(i));
           holder.username.setText(UserName.get(i));
           // holder.upvote.setText(UpVote.get(i));
           //holder.downvote.setText(DownVote.get(i));
           holder.parent_layout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                Intent intent = new Intent(myContext,IncidentReportActivity.class);
                intent.putExtra("Incident-Title",Incidenttitle.get(holder.getAdapterPosition())) ;
                intent.putExtra("Incident-Description",IncidentDescription.get(holder.getAdapterPosition()));
                intent.putExtra("Incident-Category",IncidentCategory.get(holder.getAdapterPosition()));
                intent.putExtra("Incident-Severity",IncidentSeverity.get(holder.getAdapterPosition()));
                intent.putExtra("UserName",UserName.get(holder.getAdapterPosition()));
               // intent.putExtra("UpVote",UpVote.get(i));
                //intent.putExtra("DownVote",DownVote.get(i));
                myContext.startActivity(intent);
               }
           });
       }
       catch (NullPointerException e)
       {
           e.getMessage();
       }


    }

    @Override
    public int getItemCount() {
        return Incidenttitle.size()+1 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView Title, username;
        Button upvote, downvote;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.profileimage);
            Title=itemView.findViewById(R.id.title);
            username=itemView.findViewById(R.id.Username);
            upvote=itemView.findViewById(R.id.like);
            downvote=itemView.findViewById(R.id.dislike);
            parent_layout= itemView.findViewById(R.id.parent_layout);

        }
    }

}
