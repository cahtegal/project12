package com.sevenwin.cariweton;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.sevenwin.cariweton.Hasil.isJodoh;
import static com.sevenwin.cariweton.Hasil.jodoh;

public class FrJodoh extends Fragment {
    TextView tHasilJodoh;
    AdView mAdView;
    LinearLayout layLoading;
    ScrollView scrollView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_jodoh, container, false);
        tHasilJodoh = v.findViewById(R.id.textHasilJodoh);
        mAdView = v.findViewById(R.id.adView);
        layLoading = v.findViewById(R.id.layLoading);
        scrollView = v.findViewById(R.id.scrollView);
        scrollView.setVisibility(View.GONE);
        layLoading.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
        if (isJodoh) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tHasilJodoh.setText(jodoh);
                    scrollView.setVisibility(View.VISIBLE);
                    layLoading.setVisibility(View.GONE);
                    isJodoh = false;
                }
            }, 2000);
        } else {
            tHasilJodoh.setText(jodoh);
            scrollView.setVisibility(View.VISIBLE);
            layLoading.setVisibility(View.GONE);
        }
        return v;
    }
    @Override
    public void onPause() {
        // This method should be called in the parent Activity's onPause() method.
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // This method should be called in the parent Activity's onResume() method.
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        // This method should be called in the parent Activity's onDestroy() method.
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
