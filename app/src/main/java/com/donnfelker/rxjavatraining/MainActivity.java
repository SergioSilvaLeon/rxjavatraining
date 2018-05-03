package com.donnfelker.rxjavatraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable
                .fromCallable(() -> {
                    wait(5 * 1000);
                    return "Hello World!";
                })
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .doOnNext(s -> saveToDb(s))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                );


    }

    private void saveToDb(String s) {
        // Save to Database
    }
}
