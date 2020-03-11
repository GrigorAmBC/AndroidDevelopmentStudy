package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.trello.rxlifecycle3.RxLifecycle;
import com.trello.rxlifecycle3.android.RxLifecycleAndroid;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends RxAppCompatActivity {

    Observable<Integer> observable = Observable.range(0,25);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        EditText editText = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView);


        // see these operators
        RxView.clicks(button).debounce(500, TimeUnit.MILLISECONDS).subscribe(aVoid -> {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        });

        RxTextView.textChanges(editText).subscribe(charSequence -> {
            textView.setText(charSequence);
        });

        // bounce(); publish() ,connect();
/*
        The RxLifecycle library, developed by Trello, provides lifecycle handling APIs that you can use to limit the lifespan of an Observable to the lifecycle of an Activity or Fragment.*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        observable.compose(RxLifecycleAndroid.bindActivity(lifecycle()))
                .subscribe();
    }
}
