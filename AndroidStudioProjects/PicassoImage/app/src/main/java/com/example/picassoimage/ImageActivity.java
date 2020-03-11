package com.example.picassoimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    public static String mImageUri = "";
    public static Integer mUriIndex = 0;
    public static boolean wasClosed = false;
    private ImageView mContentImageView;
    private String[] mImageUrls = MainActivity.imageUrls;
    private float mScaleFactor = 1.0f;

    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_image);

        mContentImageView = findViewById(R.id.contentImageView);

        gestureDetectorCompat =
                new GestureDetectorCompat(this, this);

        if (!mImageUri.equals(""))
            Picasso.get().load(mImageUri).into(mContentImageView);

        ScaleGestureDetector scaleGestureDetector =
                new ScaleGestureDetector(this,
                        new ScaleListener());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hide();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        hide();

    }

    private void hide() {
        getWindow().getDecorView().setSystemUiVisibility(
                 View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            mContentImageView.setScaleX(mScaleFactor);
            mContentImageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    public void onTurnImage(View view) {
        switch (view.getId()) {
            case R.id.viewTurnLeft:
                if (mUriIndex == 0)
                    mUriIndex = mImageUrls.length - 1;
                else
                    mUriIndex--;
                mImageUri = mImageUrls[mUriIndex];
                Picasso.get().load(mImageUri).into(mContentImageView);
                break;
            case R.id.viewTurnRight:
                if (mUriIndex == mImageUrls.length - 1)
                    mUriIndex =  0;
                else
                    mUriIndex++;
                mImageUri = mImageUrls[mUriIndex];
                Picasso.get().load(mImageUri).into(mContentImageView);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasClosed) {
            startActivity(new Intent(this, PasswordActivity.class));
        }
        mContentImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (wasClosed) {
            startActivity(new Intent(this, PasswordActivity.class));
        }
        mContentImageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContentImageView.setVisibility(View.GONE);
        wasClosed = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mContentImageView.setVisibility(View.GONE);
        wasClosed = true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.wasClosed = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i("gesture", "scroll");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {

        return false;
    }

}
