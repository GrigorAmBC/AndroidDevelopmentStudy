package com.example.imageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String[] mImageUrls = new String[]{
            "https://i.pinimg.com/originals/67/02/6d/67026d87945397c8d7f21bb2ede1e81f.jpg",
            "https://i.pinimg.com/originals/c9/a1/dd/c9a1dd4825a78d82a5c379d000bd1dca.jpg",
            "https://imgix.ranker.com/user_node_img/75/1484058/original/lucy-pinder-people-in-tv-photo-u18?w=650&q=50&fm=pjpg&fit=crop&crop=faces",
            "https://s3.ap-southeast-1.amazonaws.com/cdn.deccanchronicle.com/sites/default/files/rosie-h-W_0.jpg",
            "http://eskipaper.com/images/courteney-cox-4.jpg",
            "https://ae01.alicdn.com/kf/HTB1M6vgHVXXXXaIXFXXq6xXFXXXW/2015-Hot-sexy-black-tide-models-factory-direct-piece-swimsuit-biquini-sexy-women-skinny-high-elastic.jpg",
            "https://i0.wp.com/www.teninsider.com/wp-content/uploads/2015/12/Candice-Swanepoel-Pics.jpg",
            "https://www.dhresource.com/600x600/f2/albu/g6/M00/BD/31/rBVaR1uvFNSAdkmpAAQelc0dZH0448.jpg",
            "https://c.76.my/Malaysia/emboidery-sexy-chinese-costume-bra-g-string-lingerie-sleepwear-s318-mysteryoffemale7-1807-28-mysteryoffemale7@25.jpg",
            "https://img.dxcdn.com/productimages/sku_327573_1.jpg",
            "https://previews.123rf.com/images/kiuikson/kiuikson1704/kiuikson170400129/76659474-beautiful-sexy-models.jpg",
            "https://i.etsystatic.com/13255391/r/il/8f0945/1714440997/il_794xN.1714440997_aqtq.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ23qoQO7BTIrz7da4uHTTczafpDes1rhwOBGxHYd-HUk_Qdh5p",
            "https://i.pinimg.com/236x/c8/44/b2/c844b2285bcc112565dd01052a7b31af.jpg"
    };

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Adapter adapter = new Adapter(mImageUrls);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        onWindowFocusChanged(true);
    }
}
