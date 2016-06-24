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

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity(mJoke);
                loadInterstitialAd();
            }
        });


        loadInterstitialAd();

        Button button = (Button) root.findViewById(R.id.btn_jokefetch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });


        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }


    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void startJokeActivity(String joke) {
        Intent mIntent = new Intent(getActivity(), MainActivityLibrary.class);
        mIntent.putExtra("JOKE", joke);
        startActivity(mIntent);
    }

    private void fetchJoke() {

        new GceHelper().execute(new JokeListener() {
            @Override
            public void onJokeFetched(String Joke) {
                mJoke = Joke;
                if (Joke != null) {

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        startJokeActivity(mJoke);
                    }
                }
            }
        });
    }


}
