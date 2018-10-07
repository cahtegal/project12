package com.sevenwin.cariweton;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.GregorianCalendar;

/** Created by faozi on 05/12/17.
 */

public class MenuUtama extends AppCompatActivity{

    TextView textTglSekarang,teksPasaran;
    Calendar myCalendar = Calendar.getInstance();
    ImageView imgSound,imgShare;
    Button btnTglLahir;
    boolean isSound = true;
    private AdView mAdView;
    public static MediaPlayer mpSound = new MediaPlayer();
    public static MediaPlayer mpSound1 = new MediaPlayer();

    public static int hitHari = 0,pasaran = 0;
    public static String namaHari = "",dates = "";
    public static int Hari = 0,Bulan = 0, Tahun = 0, ltahun = 0;
    public static String namaHariIni = "";
    public static String[] pasaran1900 = {"Kliwon","Legi","Pahing","Pon","Wage"};
    public static int [] kurangPasaran = {-4,-4,-2,-2,-3,-3,-4,-4,-4,-5,-5,-1};
    public static int [] kurangPasaranKabisat = {-5,-5,-2,-2,-3,-3,-4,-4,-4,-5,-5,-1};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);
        deklarasi();
        action();

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

    private void deklarasi() {
        imgShare = findViewById(R.id.imgShare);
        teksPasaran = findViewById(R.id.teksWeton);
        textTglSekarang = findViewById(R.id.textTglSkrng);
        btnTglLahir = findViewById(R.id.btnTglLahir);
        mAdView = findViewById(R.id.adView);
        imgSound = findViewById(R.id.imgSound);
        mpSound = MediaPlayer.create(MenuUtama.this,R.raw.maskumambang_plnem);
        mpSound1 = MediaPlayer.create(MenuUtama.this,R.raw.lirilir_kyai_kanjeng);
        mpSound.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isSound) {
                    mpSound1.start();
                } else {
                    mpSound1.pause();
                }
            }
        },100000); // 1000 = 1 detik
    }

    private void action() {
        tglHariIni();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int tahun, int bulan,
                                  int hari) {

                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, tahun);
                myCalendar.set(Calendar.MONTH, bulan);
                myCalendar.set(Calendar.DAY_OF_WEEK, hari);
                String namaBulan="";
                Calendar calendar = new GregorianCalendar(tahun, bulan, hari);

                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
                    namaHari = "Senin";
                    hitHari = 4;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
                    namaHari = "Selasa";
                    hitHari = 3;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
                    namaHari = "Rabu";
                    hitHari = 7;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
                    namaHari = "Kamis";
                    hitHari = 8;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                    namaHari = "Jum'at";
                    hitHari = 6;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    namaHari = "Sabtu";
                    hitHari = 9;
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    namaHari = "Minggu";
                    hitHari = 5;
                }

                if (bulan == 0) {
                    namaBulan = "Januari";
                } else if (bulan == 1) {
                    namaBulan = "Februari";
                } else if (bulan == 2) {
                    namaBulan = "Maret";
                } else if (bulan == 3) {
                    namaBulan = "April";
                } else if (bulan == 4) {
                    namaBulan = "Mei";
                } else if (bulan == 5) {
                    namaBulan = "Juni";
                } else if (bulan == 6) {
                    namaBulan = "Juli";
                } else if (bulan == 7) {
                    namaBulan = "Agustus";
                } else if (bulan == 8) {
                    namaBulan = "September";
                } else if (bulan == 9) {
                    namaBulan = "Oktober";
                } else if (bulan ==10) {
                    namaBulan = "November";
                } else if (bulan == 11) {
                    namaBulan = "Desember";
                }
                Hari = hari;
                Bulan = bulan;
                Tahun = tahun;
                dates = namaHari+", "+hari + " " + namaBulan + " "+ tahun;
                String lastTahun = String.valueOf(tahun).substring(String.valueOf(tahun).length()-2);
                ltahun = Integer.parseInt(lastTahun);
                startActivity(new Intent(MenuUtama.this,Hasil.class));
            }
        };

        btnTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog mDate = new DatePickerDialog(MenuUtama.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate.show();
            }
        });
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSound) {
                    imgSound.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
                    if (mpSound.isPlaying()) {
                        mpSound.pause();
                    }
                    if (mpSound1.isPlaying()) {
                        mpSound1.pause();
                    }
                    isSound = false;
                } else {
                    imgSound.setImageDrawable(getResources().getDrawable(R.drawable.sound_on));
                    if (!mpSound.isPlaying()) {
                        mpSound.start();
                    }
                    isSound = true;
                }
            }
        });

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent;
                sendIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Pengin tau weton jawa Anda ? cek sekarang dan download di \n\nhttps://play.google.com/store/apps/details?id=com.primbon.wetonjawa");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Bagikan weton jawa ini ke teman Anda"));
            }
        });
    }

    private void tglHariIni() {
        Calendar calendar = Calendar.getInstance();
        int tgl = calendar.get(Calendar.DAY_OF_MONTH);
        int tglHari = calendar.get(Calendar.DAY_OF_WEEK);
        int bulan = calendar.get(Calendar.MONTH)+1;
        int tahun = calendar.get(Calendar.YEAR);
        String namaBulan = "";

        if (tglHari == Calendar.MONDAY) {
            namaHariIni = "Senin";
        } else if (tglHari == Calendar.TUESDAY) {
            namaHariIni = "Selasa";
        } else if (tglHari == Calendar.WEDNESDAY) {
            namaHariIni = "Rabu";
        } else if (tglHari == Calendar.THURSDAY) {
            namaHariIni = "Kamis";
        } else if (tglHari == Calendar.FRIDAY) {
            namaHariIni = "Jum'at";
        } else if (tglHari == Calendar.SATURDAY) {
            namaHariIni = "Sabtu";
        } else if (tglHari == Calendar.SUNDAY) {
            namaHariIni = "Minggu";
        }

        if (bulan==1) {
            namaBulan = "Januari";
        } else if (bulan==2) {
            namaBulan = "Februari";
        } else if (bulan==3) {
            namaBulan = "Maret";
        } else if (bulan==4) {
            namaBulan = "April";
        } else if (bulan==5) {
            namaBulan = "Mei";
        } else if (bulan==6) {
            namaBulan = "Juni";
        } else if (bulan==7) {
            namaBulan = "Juli";
        } else if (bulan==8) {
            namaBulan = "Agustus";
        } else if (bulan==9) {
            namaBulan = "September";
        } else if (bulan==10) {
            namaBulan = "Oktober";
        } else if (bulan==11) {
            namaBulan = "November";
        } else if (bulan==12) {
            namaBulan = "Desember";
        }

        String lastTahun = String.valueOf(tahun).substring(String.valueOf(tahun).length()-2);
        int ltahun = Integer.parseInt(lastTahun);

        cariPasaranHariIni(tahun,ltahun,bulan-1,tgl,namaHariIni,namaBulan);
    }

    @SuppressLint("SetTextI18n")
    private void cariPasaranHariIni(int tahun, int lastTahun, int bulan, int tgl, String namaHari, String namaBulan) {
        int jumlah = (lastTahun/4)+bulan+tgl;
        int hasil;
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

        String dates = tgl+" - "+namaBulan+" - "+tahun;
        String weton = namaHari+"("+pasaran1900[hasil]+")";
        textTglSekarang.setText(dates);
        teksPasaran.setText(weton);
    }
}
