package com.example.instagramclone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private List<String> mUsers;
    private Context mContext;
    //private List<ParseUser> mUsers;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.userNameTextView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    //TODO: change String type to ParseUser
    public UserListAdapter(List<String> users, Context context) {
        mContext = context;
        mUsers = new ArrayList<>();
        for (String user : users) {
            mUsers.add(user);
        }
    }

    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, final int position) {
        //holder.textView.setText(mUsers.get(position).getUsername());
        holder.textView.setText(mUsers.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "GRIGOR CLICK", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, UserFeedActivity.class);
                intent.putExtra("username", mUsers.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.cardView.findViewById(R.id.conLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "GRIGOR CLICK", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, UserFeedActivity.class);
                intent.putExtra("username", mUsers.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
