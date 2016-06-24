package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.widget.Button;
import android.content.Context;

import com.udacity.gradle.builditbigger.ApiHelper.JokeListener;
import com.udacity.gradle.builditbigger.ApiHelper.GceHelper;

import android.content.Intent;

import com.hardikgoswami.jokeandroidlib.MainActivityLibrary;

import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String JOKE_TAG = "JOKE";
    Context mContext;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity().getApplicationContext();
        Button button = (Button) root.findViewById(R.id.btn_jokefetch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayJoke();
            }
        });
        return root;
    }

    private void displayJoke() {
        new GceHelper().execute(new JokeListener() {
            @Override
            public void onJokeFetched(String Joke) {
                if (Joke != null) {
                    Intent intent = new Intent(mContext, MainActivityLibrary.class);
                    intent.putExtra(JOKE_TAG, Joke);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Could Not Fetch Joke , Network Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
