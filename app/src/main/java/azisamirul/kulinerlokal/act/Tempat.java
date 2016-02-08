package azisamirul.kulinerlokal.act;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.adapter.TempatAdapter;
import azisamirul.kulinerlokal.config.AppConfig;
import azisamirul.kulinerlokal.controller.MySingleton;
import azisamirul.kulinerlokal.model.TempatModel;

public class Tempat extends MainActivity {
    private final static String TAG = Tempat.class.getSimpleName();
    ArrayList listTempat = new ArrayList();


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_tempat);
        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_tempat,null,false);
        mDrawerLayout.addView(contentView, 0);
        super.setupDrawerToggle();

        Intent intent = getIntent();
        String c = intent.getStringExtra("categories_name");

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        pDialog.setMessage("Memuat daftar tepat...");

        showDialog();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tempat);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getLoadVenue(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.menu_tempat, menu);

        return true;
    }

    private void getLoadVenue(String c) {

        String url = AppConfig.venueSearch + "ll=" + "-7.771361,110.377077" + "&client_id=" + AppConfig.Client_ID + "&client_secret=" + AppConfig.Client_Secret + "&v=" + AppConfig.v + "&m=" + AppConfig.m + "&query=" + c;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    TempatModel t;
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("response");
                    JSONArray jsonArray = jsonObject1.getJSONArray("venues");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        t = new TempatModel();
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        JSONObject venues_location = jsonObject2.getJSONObject("location");

                      String venues_id = jsonObject2.getString("id");
                        t.setVenues_id(venues_id);

                        String venues_name = jsonObject2.getString("name");
                        t.setVenues_name(venues_name);
                        Log.d(TAG, "venues name:" + venues_name);


                   //  String venues_address = venues_location.getString("address");
                       // String venues_city=venues_location.getString("city");
                     //   t.setAddress(venues_address);

//                        int venues_distance=venues_location.getInt("distance");
//                        t.setDistance(venues_distance);

                   //     Log.d(TAG, "venues addresssss:" + venues_address);


                        listTempat.add(t);
                        Log.d(TAG, "list added");

                    }

                    mAdapter = new TempatAdapter(listTempat);
                    mRecyclerView.setAdapter(mAdapter);


                    Log.d(TAG, "Recycler view set adapter");
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
