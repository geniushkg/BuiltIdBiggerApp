package com.hardikgoswami.jokeandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityLibrary extends AppCompatActivity {
public static final String TAG_JOKE = "JOKE";
    private TextView textViewJoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_library);
        textViewJoke = (TextView)findViewById(R.id.tv_joke);
        if(getIntent() != null){
            String jokeFetched = getIntent().getExtras().getString(TAG_JOKE);
            textViewJoke.setText(jokeFetched);
        }
    }
}
