package azisamirul.kulinerlokal.adapter;

import azisamirul.kulinerlokal.act.Tempat;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.model.Category;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by azisamirul on 28/09/2015.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    public static ArrayList<Category> categories;
Context ctx;
    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent,false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(itemLayoutView);
        ctx=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder viewHolder, final int position) {
        viewHolder.category_name.setText(categories.get(position).getCategoryName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Tempat.class);
                intent.putExtra("categories_name", categories.get(position).getCategoryName());
                v.getContext().startActivity(intent);
            }
        });


        viewHolder.cat_icon.setImageBitmap(getBitmapAsset(categories.get(position).getImg()));
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView category_name;
        public CardView cardView;
        public CircleImageView cat_icon;
        public CategoryViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            cat_icon=(CircleImageView) itemLayoutView.findViewById(R.id.cat_icon);
            category_name = (TextView) itemLayoutView.findViewById(R.id.categories_name);
            cardView = (CardView) itemLayoutView.findViewById(R.id.card_categories);
        }
    }
    private Bitmap getBitmapAsset(String bmp){
        AssetManager assetManager=ctx.getAssets();
        InputStream inputStream;
        Bitmap bitmap=null;
        try{
            inputStream=assetManager.open(bmp);
            bitmap= BitmapFactory.decodeStream(inputStream);
        }catch (Exception e){

        }
        return bitmap;
    }

}
