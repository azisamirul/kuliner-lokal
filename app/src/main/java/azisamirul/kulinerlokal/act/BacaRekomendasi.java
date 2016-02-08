package azisamirul.kulinerlokal.act;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.config.AppConfig;
import azisamirul.kulinerlokal.controller.MySingleton;

public class BacaRekomendasi extends MainActivity {
    ImageView rekomendasi_detail_img;
    TextView rekomendasi_detail_name,rekomendasi_detail_address,rekomendasi_detail_telepon,rekomendasi_detail_deskripsi;
    Context ctx;

    ProgressDialog pDialog;
String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_baca_rekomendasi, null, false);
        mDrawerLayout.addView(contentView, 0);
        Bundle extra = getIntent().getExtras();
        String url = extra.getString("url");
        rekomendasi_detail_img = (ImageView) findViewById(R.id.rekomnendasi_detail_img);
        rekomendasi_detail_name = (TextView) findViewById(R.id.rekomendasi_detail_name);
        rekomendasi_detail_address=(TextView) findViewById(R.id.rekomendasi_detail_address);
        rekomendasi_detail_telepon=(TextView) findViewById(R.id.rekomendasi_detail_telepon);
        rekomendasi_detail_deskripsi=(TextView) findViewById(R.id.rekomendasi_detail_deskripsi);

        ctx = getApplicationContext();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat daftar tepat...");

        scrapData(url);
    }


    private void scrapData(String url) {

        showDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                String judul = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(1) > a > h1").text();
                Log.d("Judul : ", judul);

                rekomendasi_detail_name.setText(judul);
                String deskripsi = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(8) > div > div:nth-child(2) > article").html();
                Log.d("Deskripsi : ", deskripsi);

                rekomendasi_detail_deskripsi.setText(Html.fromHtml(deskripsi));

                String gambar = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(8) > div > div:nth-child(1) > a > img").attr("src");
                Log.d("Gambar : ", gambar);

                Picasso.with(ctx).load(gambar).into(rekomendasi_detail_img);
                 phone = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(7) > article:nth-child(2)").html();
                Log.d("Telepon : ", phone);
                rekomendasi_detail_telepon.setText(phone);

                String alamat=document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(5) > article").html();
                Log.d("alamat rekomendasi ",alamat);
                rekomendasi_detail_address.setText(Html.fromHtml(alamat));


                String harga = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(7) > article:nth-child(4)").text();
                Log.d("harga : ", harga);

                String menu_spesial = document.select("#main > div.container > div > div.col-sm-8 > div.profile-detail > section:nth-child(8) > div > div:nth-child(2) > h3").text();
                Log.d("menu spesial : ", menu_spesial);
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
