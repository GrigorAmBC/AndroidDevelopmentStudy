package com.example.imageapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.glideimagevideogifapp.R;

import java.util.concurrent.ExecutionException;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] mImageUrls;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageViewItem);
        }
    }

    public Adapter(String[] imageUrls) {
        mImageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // load in background
        ImageView imageView = holder.imageView;
        FutureTarget<Bitmap> futureTarget =
                Glide.with(imageView.getContext())
                        .asBitmap()
                        .load(mImageUrls[position])
                        .submit();
        Bitmap bitmap;
        try {
            bitmap = futureTarget.get();
            imageView.setImageBitmap(bitmap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        // or another way
//        Glide.with(imageView.getContext()).load(mImageUrls[position]).into(new Target<Bitmap>() {
//           ...


    }

    @Override
    public int getItemCount() {
        return mImageUrls.length;
    }
}