package com.example.picassoimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public static boolean wasClosed = false;
    public static String[] imageUrls = new String[] {
            "https://avatars.mds.yandex.net/get-pdb/1816426/da1753f4-ae86-46a4-8f24-046a53bddbaa/s1200?webp=false",
            "https://thumb-p0.xhcdn.com/a/Z8HSIlDB0ZuzE2ls43-KjQ/000/089/114/000_1000.jpg",
            "http://goodsexporn.org/media/galleries/541cbb7615339/10.jpg",
            "http://devstvennici-porno.ru/uploads/posts/2016-09/1473549851_image-2922.jpg",
            "https://img.sexpornpages.com/images01/image-146834.jpg",
            "https://s9v7j7a4.ssl.hwcdn.net/galleries/full/7b/b6/20/7bb620ff74eaab42c6d070539ef7022f/10.jpg",
            "https://tetki.info/uploads/posts/2018-03/thumbs/1521731076_totally-shaved-pale-mature-shaved-milf-kimberly-costa-with-big-naturals-wearing-pa13.jpg",
            "http://www.zenfield.pro/images/sexypussypic.com/galleries/0/262/7_785.jpg",
            "https://cdn.pornpics.com/pics1/2014-12-30/286630_10big.jpg",
            "https://cdn1.images.lesbianpornvideos.com/videos/0864/77816/main.jpg",
            "https://efappy.com/wp-content/uploads/Lesbian-Porn.jpg",
            "http://qpornx.com/xxx/lipstick-lesbians-nude.jpg"
    };
    GridView imageGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_main);

        imageGridView = findViewById(R.id.gridview_images);
        imageGridView.setAdapter(new ImageAdapterGridView(this));
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageActivity.mImageUri = imageUrls[i];
                ImageActivity.mUriIndex = i;
                ImageActivity.wasClosed = false;
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @Override
    protected void onStart() {
        super.onStart();


        if (wasClosed) {
            startActivity(new Intent(this, PasswordActivity.class));
        }
        wasClosed = false;
        imageGridView.setVisibility(View.VISIBLE);
    }

    public void onHideStatusBarAgain(View v) {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasClosed = true;
        imageGridView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DataManager.isFirstOpened(true);
    }

    public class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;

        public ImageAdapterGridView(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;

            if (view == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView
                        .LayoutParams(350, 350));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            } else {
                imageView = (ImageView) view;
            }

            Picasso.get().load(imageUrls[i]).into(imageView);
           // imageView.setImageResource(R.drawable.ic_launcher_background);
            return imageView;
        }
    }
}
