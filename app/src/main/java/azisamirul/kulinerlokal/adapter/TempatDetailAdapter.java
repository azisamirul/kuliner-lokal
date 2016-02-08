package azisamirul.kulinerlokal.adapter;

import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.controller.MySingleton;
import azisamirul.kulinerlokal.model.TempatDetailModel;
import azisamirul.kulinerlokal.model.VenuePhotoModel;

/**
 * Created by azisamirul on 29/09/2015.
 */
public class TempatDetailAdapter extends RecyclerView.Adapter<TempatDetailAdapter.TempatDetailViewHolder> {

    public static ArrayList<TempatDetailModel> tempatDetailModels;
    private static ImageLoader imageLoader;

    public TempatDetailAdapter(ArrayList<TempatDetailModel> tempatDetailModels) {
        this.tempatDetailModels = tempatDetailModels;
    }

    @Override
    public int getItemCount() {
        return tempatDetailModels.size();
    }

    @Override
    public TempatDetailAdapter.TempatDetailViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        Log.d("Oncreate ", "viewholder");
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat_detail, parent,false);
        TempatDetailViewHolder viewHolder = new TempatDetailViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TempatDetailViewHolder viewHolder, final int position) {


//        for (VenuePhotoModel venuePhotoModel : tempatDetailModels.get(position).getVenuePhotoModels()) {
//            viewHolder.venue_detail_image.setImageUrl(venuePhotoModel.getVenue_photo_url(), imageLoader);
//        }
//            viewHolder.venue_detail_name.setText(tempatDetailModels.get(position).getName());
//            viewHolder.venue_detail_address.setText(tempatDetailModels.get(position).getAddress());


    }

    public static class TempatDetailViewHolder extends RecyclerView.ViewHolder {
        TextView venue_detail_name;
        TextView venue_detail_address;
        TextView venue_detail_phone;
        RatingBar venue_detail_rating;
        NetworkImageView venue_detail_image;
        NetworkImageView user_image;

        public TempatDetailViewHolder(View itemLayoutView) {


            super(itemLayoutView);
            Log.d("extends", "cek");
            imageLoader = MySingleton.getInstance(itemLayoutView.getContext()).getmImageLoader();
            venue_detail_name = (TextView) itemLayoutView.findViewById(R.id.venue_detail_name);
            venue_detail_address = (TextView) itemLayoutView.findViewById(R.id.venue_detail_address);
//            venue_detail_phone = (TextView) itemLayoutView.findViewById(R.id.venue_detail_phone);
//            venue_detail_rating = (RatingBar) itemLayoutView.findViewById(R.id.venue_detail_rating);
//            venue_detail_image = (NetworkImageView) itemLayoutView.findViewById(R.id.venue_detail_image);
//            user_image = (NetworkImageView) itemLayoutView.findViewById(R.id.user_image);


        }
    }

}
