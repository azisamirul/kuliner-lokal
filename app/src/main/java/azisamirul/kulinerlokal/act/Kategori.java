package azisamirul.kulinerlokal.act;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import azisamirul.kulinerlokal.adapter.CategoryAdapter;
import azisamirul.kulinerlokal.model.Category;
import azisamirul.kulinerlokal.R;

public class Kategori extends MainActivity {
    Category item;
    ArrayList kat = new ArrayList<Category>();
    Toolbar toolbar;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private final static String TAG = Kategori.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_kategori, null, false);
        mDrawerLayout.addView(contentView, 0);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_categories);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getCategory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kategori, menu);
        return true;
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

    private void getCategory() {
        String json = null;
        try {
            InputStream is = getAssets().open("category.json");
            Log.d(TAG, "category get");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                item = new Category();
                JSONObject cat = jsonArray.getJSONObject(i);
                String categories = cat.getString("categories_name");
                String pics = cat.getString("pics");
                Log.d("Kategori", pics);
                item.setCategoryName(categories);
                item.setImg(pics);
                kat.add(item);

            }
            mAdapter = new CategoryAdapter(kat);
            mRecyclerView.setAdapter(mAdapter);


        } catch (JSONException ex) {
            ex.printStackTrace();

        }
    }
}
