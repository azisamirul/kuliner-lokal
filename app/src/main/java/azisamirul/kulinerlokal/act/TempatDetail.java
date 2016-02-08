package azisamirul.kulinerlokal.act;

import android.app.ActionBar;
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
import azisamirul.kulinerlokal.adapter.TempatDetailAdapter;
import azisamirul.kulinerlokal.config.AppConfig;
import azisamirul.kulinerlokal.controller.MySingleton;
import azisamirul.kulinerlokal.model.TempatDetailModel;
import azisamirul.kulinerlokal.model.UserTips;
import azisamirul.kulinerlokal.model.VenuePhotoModel;

public class TempatDetail extends MainActivity{
    TempatDetailModel tempatDetailModel;
    ArrayList arrayList = new ArrayList<TempatDetailModel>();


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;


    private final static String TAG = TempatDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_tempat_detail);

//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_tempatdetail);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//

        LayoutInflater inflater=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_tempat_detail,null,false);
        mDrawerLayout.addView(contentView,0);
        super.setupDrawerToggle();;

        Log.d(TAG,"prepare recycler view");
      Bundle extra=getIntent().getExtras();

        String venues_id = extra.getString("venue_id");
        Log.d("Venues ID ",venues_id);
        getTempatDetail(venues_id);
        getSupportActionBar().setTitle("Rincian Tempat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tempat_detail, menu);
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

    private void getTempatDetail(String venues_id) {
//      String url= AppConfig.venueDetail+venues_id+"?client_id="+AppConfig.Client_ID+"&client_secret="+AppConfig.Client_Secret+"&v="+AppConfig.v;
//       // String url = "https://api.foursquare.com/v2/venues/4cb91f56f50e224bb2caebfb?client_id=OSMSTHQFQEKWYDOLCCWWVD5CE2YLQHBDLFEQJNFAAXLSYKBA&client_secret=CGNLZUMAEUQMHARN3SPXVG31XQZKPFHCHFO0NJG5DGNVNZ0C&v=20140806";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "response " + response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONObject respon = jsonObject.getJSONObject("response");
//                    JSONObject ven = respon.getJSONObject("venue");
//
//                    JSONObject con = ven.getJSONObject("contact");
//                    JSONObject loc = ven.getJSONObject("location");
//                    JSONObject stat = ven.getJSONObject("stats");
//
//                    JSONObject photo = ven.getJSONObject("photos");
//                    ///Detail
//
//
//                    tempatDetailModel = new TempatDetailModel();
//
//                    String venue_name = ven.getString("name");
//                    Log.d(TAG, "venue name :" + venue_name);
//                    tempatDetailModel.setName(venue_name);
//
//                    String venue_phone = con.getString("phone");
//
//                    String venue_address = loc.getString("address");
//                    tempatDetailModel.setAddress(venue_address);
//
//                    Double venue_lat = loc.getDouble("lat");
//                    Double venue_lng = loc.getDouble("lng");
//                    String venue_city = loc.getString("city");
//
//
//                    int venue_checkIn = stat.getInt("checkinsCount");
//
//                    Double rating = ven.getDouble("rating");
//
//
//                    JSONArray photogroup = photo.getJSONArray("groups");
//                    for (int i = 0; i < photogroup.length(); i++) {
//                        tempatDetailModel = new TempatDetailModel();
//                        JSONObject photo_groups = photogroup.getJSONObject(i);
//
//                        JSONArray photoitems = photo_groups.getJSONArray("items");
//                        VenuePhotoModel venuePhotoModel[] = new VenuePhotoModel[3];
//                        for (int j = 0; j < 3; j++) {
//                            JSONObject photo_items = photoitems.getJSONObject(j);
//                            venuePhotoModel[j] = new VenuePhotoModel();
//
//                            String photo_prefix = photo_items.getString("prefix");
//                            String photo_suffix = photo_items.getString("suffix");
//
//                            String venue_photo_url = photo_prefix + AppConfig.venue_size_photo + photo_suffix;
//                            venuePhotoModel[j].setVenue_photo_url(venue_photo_url);
//                            Log.d(TAG, "venue photo : " + venue_photo_url);
//// JSONObject user=photo_items.getJSONObject("user");
////                            String user_firstname=user.getString("firstName");
////
////                            JSONObject user_photo=user.getJSONObject("photo");
////                            String user_prefix=user_photo.getString("prefix");
////                            String user_suffix=user_photo.getString("suffix");
//                            tempatDetailModel.setVenuePhotoModels(venuePhotoModel);
//                        }
//
//                        ////Tips
//                        JSONObject tip = ven.getJSONObject("tips");
//                        JSONArray group = tip.getJSONArray("groups");
//                        for (int k = 0; k < group.length(); k++) {
//
//
//                            JSONObject groups = group.getJSONObject(k);
//
//                            JSONArray item = groups.getJSONArray("items");
//
//                            UserTips[] userTipses = new UserTips[5];
//
//                            for (int l = 0; l < 5; l++) {
//                                userTipses[l] = new UserTips();
//
//                                JSONObject items = item.getJSONObject(l);
//                                String items_text = items.getString("text");
//                                userTipses[l].setText(items_text);
//
//                                JSONObject user = items.getJSONObject("user");
//                                String user_firstname = user.getString("firstName");
//                                userTipses[l].setFirstName(user_firstname);
//
//                                JSONObject user_photo = user.getJSONObject("photo");
//                                String user_prefix = user_photo.getString("prefix");
//                                String user_suffix = user_photo.getString("suffix");
//
//                                String imgUrl = user_prefix + AppConfig.venue_size_photo + user_suffix;
//                                userTipses[l].setPhoto_url(imgUrl);
//                                tempatDetailModel.setUserTipses(userTipses);
//
//                            }
//
//
//                        }
//
//                      //  arrayList.add(tempatDetailModel);
//
//
//               }
//
//                    mAdapter = new TempatDetailAdapter(arrayList);
//                    mRecyclerView.setAdapter(mAdapter);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


}
