package com.example.flea;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private Context mContext;
    private MainActivity mActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAudioTextView;
        LinearLayout mLayout;

        public ViewHolder(View v) {
            super(v);
            mAudioTextView = v.findViewById(R.id.audioDisplayNameTextView);
            mLayout = v.findViewById(R.id.audio_item_layout);
        }
    }

    public Adapter(Context context, MainActivity activity) {
        mContext = context;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.audio_item_layout, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final @NonNull ViewHolder holder, final int position) {
        holder.mAudioTextView.setText(MainPresenter.getAudioTrackByPosition(position).title);

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.playSong(view , position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MainPresenter.getAudioTracks().size();
    }
}
