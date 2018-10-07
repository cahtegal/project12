package com.sevenwin.cariweton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import static com.sevenwin.cariweton.MenuUtama.Bulan;
import static com.sevenwin.cariweton.MenuUtama.Hari;
import static com.sevenwin.cariweton.MenuUtama.Tahun;
import static com.sevenwin.cariweton.MenuUtama.dates;
import static com.sevenwin.cariweton.MenuUtama.hitHari;
import static com.sevenwin.cariweton.MenuUtama.ltahun;
import static com.sevenwin.cariweton.MenuUtama.namaHari;

@SuppressLint("StaticFieldLeak")
public class Hasil extends AppCompatActivity {

    public static TextView tTgl,teksNeptu,teksWeton;
    public static int pasaran = 0;
    public static String[] pasaran1900 = {"Kliwon","Legi","Pahing","Pon","Wage"};
    public static int [] kurangPasaran = {-4,-4,-2,-2,-3,-3,-4,-4,-4,-5,-5,-1};
    public static int [] kurangPasaranKabisat = {-5,-5,-2,-2,-3,-3,-4,-4,-4,-5,-5,-1};
    public static Activity activity;
    private InterstitialAd mInterstitialAd;
    static int hasil;
    private int[] imageResId = {
            R.drawable.ic_watak,
            R.drawable.ic_pekerjaan,
            R.drawable.ic_rejeki,
            R.drawable.ic_love
    };

    TabLayout tabLayout;
    ViewPager viewPager;
    public static String watak,pekerjaan,rejeki,jodoh;
    public static boolean isWatak = true,isPekerjaan = true,isJodoh = true,isRejeki = true;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        deklarasi();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        cariPasaran(Tahun,ltahun,Bulan,Hari);
        hasilWeton();
        setupTabIcons();
    }
    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments [] = {"","","",""};

        CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FrWatak();
                case 1:
                    return new FrPekerjaan();
                case 2:
                    return new FrRejeki();
                case 3:
                    return new FrJodoh();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }
    private void deklarasi() {
        activity = Hasil.this;
        teksWeton = findViewById(R.id.teksWeton);
        tTgl = findViewById(R.id.tTgl);
        teksNeptu = findViewById(R.id.teksNeptu);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(imageResId[0]);
        tabLayout.getTabAt(1).setIcon(imageResId[1]);
        tabLayout.getTabAt(2).setIcon(imageResId[2]);
        tabLayout.getTabAt(3).setIcon(imageResId[3]);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("SetTextI18n")
    public static void cariPasaran(int tahun, int lastTahun, int bulan, int tgl) {
        tTgl.setText("Tanggal lahir : "+dates);
        int jumlah = (lastTahun/4)+bulan+tgl;
        if (tahun%4==0) {
            hasil = (jumlah + kurangPasaranKabisat[bulan]) % 5;
        } else {
            hasil = (jumlah + kurangPasaran[bulan]) % 5;
        }
        switch (hasil) {
            case 0:
                pasaran = 8;
                break;
            case 1:
                pasaran = 5;
                break;
            case 2:
                pasaran = 9;
                break;
            case 3:
                pasaran = 7;
                break;
            default:
                pasaran = 4;
                break;
        }

        int neptu = hitHari+pasaran;
        teksNeptu.setText("Hari "+namaHari + " = "+hitHari+", Weton "+pasaran1900[hasil]+" = "+pasaran+", Neptu = "+neptu+" ("+hitHari+"+"+pasaran+")");
        teksWeton.setText(namaHari+"("+pasaran1900[hasil]+")");
    }
    @Override
    public void onBackPressed() {
        isWatak = true;
        isJodoh = true;
        isPekerjaan = true;
        isRejeki = true;
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4165439494743762/1302765899");
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
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
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        finish();
    }
    
    private void hasilWeton() {
        if (namaHari.equals("Minggu") && pasaran1900[hasil].equals("Kliwon")) { //============================== Minggu ==========================
            watak = activity.getResources().getString(R.string.mingguKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.mingguKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.mingguKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.mingguKliwonJodoh);

        } else if (namaHari.equals("Minggu") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.mingguLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.mingguLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.mingguLegiRejeki);
            jodoh = activity.getResources().getString(R.string.mingguLegiJodoh);

        } else if (namaHari.equals("Minggu") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.mingguPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.mingguPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.mingguPahingRejeki);
            jodoh = activity.getResources().getString(R.string.mingguPahingJodoh);

        } else if (namaHari.equals("Minggu") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.mingguPonWatak);
            pekerjaan = activity.getResources().getString(R.string.mingguPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.mingguPonRejeki);
            jodoh = activity.getResources().getString(R.string.mingguPonJodoh);

        } else if (namaHari.equals("Minggu") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.mingguWageWatak);
            pekerjaan = activity.getResources().getString(R.string.mingguWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.mingguWageRejeki);
            jodoh = activity.getResources().getString(R.string.mingguWageJodoh);

        } else if (namaHari.equals("Senin") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Senin ==========================
            watak = activity.getResources().getString(R.string.seninKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.seninKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.seninKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.seninKliwonJodoh);

        } else if (namaHari.equals("Senin") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.seninLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.seninLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.seninLegiRejeki);
            jodoh = activity.getResources().getString(R.string.seninLegiJodoh);

        } else if (namaHari.equals("Senin") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.seninPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.seninPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.seninPahingRejeki);
            jodoh = activity.getResources().getString(R.string.seninPahingJodoh);

        } else if (namaHari.equals("Senin") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.seninPonWatak);
            pekerjaan = activity.getResources().getString(R.string.seninPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.seninPonRejeki);
            jodoh = activity.getResources().getString(R.string.seninPonJodoh);

        } else if (namaHari.equals("Senin") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.seninWageWatak);
            pekerjaan = activity.getResources().getString(R.string.seninWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.seninWageRejeki);
            jodoh = activity.getResources().getString(R.string.seninWageJodoh);

        } else if (namaHari.equals("Selasa") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Selasa ==========================
            watak = activity.getResources().getString(R.string.selasaKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.selasaKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.selasaKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.selasaKliwonJodoh);

        } else if (namaHari.equals("Selasa") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.selasaLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.selasaLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.selasaLegiRejeki);
            jodoh = activity.getResources().getString(R.string.selasaLegiJodoh);

        } else if (namaHari.equals("Selasa") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.selasaPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.selasaPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.selasaPahingRejeki);
            jodoh = activity.getResources().getString(R.string.selasaPahingJodoh);

        } else if (namaHari.equals("Selasa") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.selasaPonWatak);
            pekerjaan = activity.getResources().getString(R.string.selasaPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.selasaPonRejeki);
            jodoh = activity.getResources().getString(R.string.selasaPonJodoh);

        } else if (namaHari.equals("Selasa") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.selasaWageWatak);
            pekerjaan = activity.getResources().getString(R.string.selasaWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.selasaWageRejeki);
            jodoh = activity.getResources().getString(R.string.selasaWageJodoh);

        } else if (namaHari.equals("Rabu") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Rabu ==========================
            watak = activity.getResources().getString(R.string.rabuKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.rabuKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.rabuKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.rabuKliwonJodoh);

        } else if (namaHari.equals("Rabu") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.rabuLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.rabuLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.rabuLegiRejeki);
            jodoh = activity.getResources().getString(R.string.rabuLegiJodoh);

        } else if (namaHari.equals("Rabu") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.rabuPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.rabuPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.rabuPahingRejeki);
            jodoh = activity.getResources().getString(R.string.rabuPahingJodoh);

        } else if (namaHari.equals("Rabu") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.rabuPonWatak);
            pekerjaan = activity.getResources().getString(R.string.rabuPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.rabuPonRejeki);
            jodoh = activity.getResources().getString(R.string.rabuPonJodoh);

        } else if (namaHari.equals("Rabu") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.rabuWageWatak);
            pekerjaan = activity.getResources().getString(R.string.rabuWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.rabuWageRejeki);
            jodoh = activity.getResources().getString(R.string.rabuWageJodoh);

        } else if (namaHari.equals("Kamis") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Kamis ==========================
            watak = activity.getResources().getString(R.string.kamisKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.kamisKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.kamisKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.kamisKliwonJodoh);

        } else if (namaHari.equals("Kamis") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.kamisLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.kamisLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.kamisLegiRejeki);
            jodoh = activity.getResources().getString(R.string.kamisLegiJodoh);

        } else if (namaHari.equals("Kamis") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.kamisPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.kamisPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.kamisPahingRejeki);
            jodoh = activity.getResources().getString(R.string.kamisPahingJodoh);

        } else if (namaHari.equals("Kamis") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.kamisPonWatak);
            pekerjaan = activity.getResources().getString(R.string.kamisPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.kamisPonRejeki);
            jodoh = activity.getResources().getString(R.string.kamisPonJodoh);

        } else if (namaHari.equals("Kamis") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.kamisWageWatak);
            pekerjaan = activity.getResources().getString(R.string.kamisWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.kamisWageRejeki);
            jodoh = activity.getResources().getString(R.string.kamisWageJodoh);

        } else if (namaHari.equals("Jum'at") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Jumat ==========================
            watak = activity.getResources().getString(R.string.jumatKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.jumatKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.jumatKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.jumatKliwonJodoh);

        } else if (namaHari.equals("Jum'at") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.jumatLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.jumatLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.jumatLegiRejeki);
            jodoh = activity.getResources().getString(R.string.jumatLegiJodoh);

        } else if (namaHari.equals("Jum'at") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.jumatPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.jumatPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.jumatPahingRejeki);
            jodoh = activity.getResources().getString(R.string.jumatPahingJodoh);

        } else if (namaHari.equals("Jum'at") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.jumatPonWatak);
            pekerjaan = activity.getResources().getString(R.string.jumatPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.jumatPonRejeki);
            jodoh = activity.getResources().getString(R.string.jumatPonJodoh);

        } else if (namaHari.equals("Jum'at") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.jumatWageWatak);
            pekerjaan = activity.getResources().getString(R.string.jumatWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.jumatWageRejeki);
            jodoh = activity.getResources().getString(R.string.jumatWageJodoh);

        }  else if (namaHari.equals("Sabtu") && pasaran1900[hasil].equals("Kliwon")) { //=============================== Sabtu ==========================
            watak = activity.getResources().getString(R.string.sabtuKliwonWatak);
            pekerjaan = activity.getResources().getString(R.string.sabtuKliwonPekerjaan);
            rejeki = activity.getResources().getString(R.string.sabtuKliwonRejeki);
            jodoh = activity.getResources().getString(R.string.sabtuKliwonJodoh);

        } else if (namaHari.equals("Sabtu") && pasaran1900[hasil].equals("Legi")) {
            watak = activity.getResources().getString(R.string.sabtuLegiWatak);
            pekerjaan = activity.getResources().getString(R.string.sabtuLegiPekerjaan);
            rejeki = activity.getResources().getString(R.string.sabtuLegiRejeki);
            jodoh = activity.getResources().getString(R.string.sabtuLegiJodoh);

        } else if (namaHari.equals("Sabtu") && pasaran1900[hasil].equals("Pahing")) {
            watak = activity.getResources().getString(R.string.sabtuPahingWatak);
            pekerjaan = activity.getResources().getString(R.string.sabtuPahingPekerjaan);
            rejeki = activity.getResources().getString(R.string.sabtuPahingRejeki);
            jodoh = activity.getResources().getString(R.string.sabtuPahingJodoh);

        } else if (namaHari.equals("Sabtu") && pasaran1900[hasil].equals("Pon")) {
            watak = activity.getResources().getString(R.string.sabtuPonWatak);
            pekerjaan = activity.getResources().getString(R.string.sabtuPonPekerjaan);
            rejeki = activity.getResources().getString(R.string.sabtuPonRejeki);
            jodoh = activity.getResources().getString(R.string.sabtuPonJodoh);

        } else if (namaHari.equals("Sabtu") && pasaran1900[hasil].equals("Wage")) {
            watak = activity.getResources().getString(R.string.sabtuWageWatak);
            pekerjaan = activity.getResources().getString(R.string.sabtuWagePekerjaan);
            rejeki = activity.getResources().getString(R.string.sabtuWageRejeki);
            jodoh = activity.getResources().getString(R.string.sabtuWageJodoh);
        }
    }
}
