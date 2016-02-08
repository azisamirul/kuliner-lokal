package azisamirul.kulinerlokal.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.act.BacaRekomendasi;
import azisamirul.kulinerlokal.act.Rekomendasi;
import azisamirul.kulinerlokal.controller.MySingleton;
import azisamirul.kulinerlokal.model.RekomendasiModel;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by azisamirul on 06/11/2015.
 */
public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewHolder> {

    public static ArrayList<RekomendasiModel> rekomendasiModels;
    Context context;

    public RekomendasiAdapter(ArrayList<RekomendasiModel> rekomendasiModels, Context context) {

        this.context = context;
        this.rekomendasiModels = rekomendasiModels;
    }

    @Override
    public RekomendasiAdapter.RekomendasiViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekomendasi, parent, false);
        RekomendasiViewHolder viewHolder = new RekomendasiViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return rekomendasiModels.size();
    }

    @Override
    public void onBindViewHolder(final RekomendasiViewHolder viewHolder, final int position) {
        viewHolder.txt_nama_rekomendasi.setText(rekomendasiModels.get(position).getNama());
        viewHolder.txt_deskripsi_rekomendasi.setText(rekomendasiModels.get(position).getDeskripsi());

        viewHolder.imageLoader = MySingleton.getInstance(context).getmImageLoader();
        viewHolder.img_list_rekomendasi.setImageUrl(rekomendasiModels.get(position).getGambar(), viewHolder.imageLoader);

        viewHolder.imageLoader2 = MySingleton.getInstance(context).getmImageLoader();
        viewHolder.imageLoader2.get(rekomendasiModels.get(position).getImg_logo(), ImageLoader.getImageListener(viewHolder.img_logos, R.id.cat_icon, R.id.food_icon));

        viewHolder.card_rekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BacaRekomendasi.class);
                i.putExtra("url", rekomendasiModels.get(position).getUrl());
                v.getContext().startActivity(i);
            }
        });
    }

    public static class RekomendasiViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView img_list_rekomendasi;
        public TextView txt_nama_rekomendasi;
        public TextView txt_deskripsi_rekomendasi;
        public CardView card_rekomendasi;
        public ImageLoader imageLoader2;
        public ImageView img_logos;
        public ImageLoader imageLoader;

        public RekomendasiViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            img_list_rekomendasi = (NetworkImageView) itemLayoutView.findViewById(R.id.img_list_rekomendasi);
            txt_nama_rekomendasi = (TextView) itemLayoutView.findViewById(R.id.txt_nama_rekomendasi);
            txt_deskripsi_rekomendasi = (TextView) itemLayoutView.findViewById(R.id.txt_deskripsi_rekomendasi);
            card_rekomendasi = (CardView) itemLayoutView.findViewById(R.id.card_rekomendasi);
            img_logos = (ImageView) itemLayoutView.findViewById(R.id.img_logo);
        }
    }


}
