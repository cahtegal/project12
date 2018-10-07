package com.sevenwin.cariweton;

import android.os.Handler;
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

import static com.sevenwin.cariweton.Hasil.isWatak;
import static com.sevenwin.cariweton.Hasil.watak;

public class FrWatak extends Fragment {
    TextView tHasilWatak;
    LinearLayout layLoading;
    ScrollView scrollView;
    AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_watak, container, false);
        tHasilWatak = v.findViewById(R.id.textHasilWatak);
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
        if (isWatak) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tHasilWatak.setText(watak);
                    scrollView.setVisibility(View.VISIBLE);
                    layLoading.setVisibility(View.GONE);
                    isWatak = false;
                }
            }, 2000);
        } else {
            tHasilWatak.setText(watak);
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
