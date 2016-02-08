package azisamirul.kulinerlokal.act;

import android.app.ProgressDialog;
import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.adapter.RekomendasiAdapter;
import azisamirul.kulinerlokal.config.AppConfig;
import azisamirul.kulinerlokal.controller.MySingleton;
import azisamirul.kulinerlokal.model.RekomendasiModel;

public class Rekomendasi extends MainActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RekomendasiModel rekomendasiModel;
    private ProgressDialog pDialog;

    ArrayList arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rekomendasi);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_rekomendasi, null, false);
        mDrawerLayout.addView(contentView, 0);

        getSupportActionBar().setTitle("Rekomendasi Kuliner");
        super.setupDrawerToggle();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerRekomendasi);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat data..");
        scrapData();
    }

    private void scrapData() {
        showDialog();
        String url = "http://ceritaperut.com/toprekomendasi/kota/yogyakarta";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements tempatmakan = document.select("#main > div.container > div > div.col-sm-8 > div.pagetoprekomendasi > div.itempgtoprekomend > section");
                String img_logo = AppConfig.base_url_ceritaperut + document.select("#top > div > div > div.col-sm-3 > a > img").attr("src");


                for (int i = 0; i < tempatmakan.size(); i++) {
                    rekomendasiModel = new RekomendasiModel();

                    String nama_tempat = tempatmakan.get(i).select("div > div.col-sm-6 > a > h2").text();
                    Log.d("Nama tempat makan ", nama_tempat);
                    rekomendasiModel.setNama(nama_tempat);

                    String deskripsi_singkat = tempatmakan.get(i).select("div > div.col-sm-6 > a > article").text();
                    Log.d("Deskripsi singkat ", deskripsi_singkat);
                    rekomendasiModel.setDeskripsi(deskripsi_singkat);

                    String tempat_profil = tempatmakan.get(i).select("div > div:nth-child(3) > a.btn.btn-block.btn-danger.btn-xs").attr("href");
                    Log.d("Link profil tempat ", tempat_profil);
                    rekomendasiModel.setUrl(AppConfig.base_url_ceritaperut + tempat_profil);

                    String img_tempat = tempatmakan.get(i).select("div > div:nth-child(1) > a > div > div").attr("style");
                    int awal = img_tempat.indexOf("(");
                    int akhir = img_tempat.indexOf(")");
                    String gbr_tempat = AppConfig.base_url_ceritaperut + img_tempat.substring(awal + 1, akhir);
                    rekomendasiModel.setGambar(gbr_tempat);
                    rekomendasiModel.setImg_logo(img_logo);
                    Log.d("Gambar tempat: ", gbr_tempat);
                    arrayList.add(rekomendasiModel);


                }
                adapter = new RekomendasiAdapter(arrayList, Rekomendasi.this);
                recyclerView.setAdapter(adapter);
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<String, String>();
                header.put(AppConfig.user_agent, AppConfig.user_agent_type);
                return header;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
