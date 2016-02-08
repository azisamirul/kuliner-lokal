package azisamirul.kulinerlokal.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.act.TempatDetail;
import azisamirul.kulinerlokal.model.TempatModel;

/**
 * Created by azisamirul on 29/09/2015.
 */
public class TempatAdapter extends RecyclerView.Adapter<TempatAdapter.TempatViewHolder> {

public static ArrayList<TempatModel>models;
    public TempatAdapter(ArrayList<TempatModel> models){
        this.models=models;
    }


    @Override
    public TempatAdapter.TempatViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){

        View itemLayoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat,parent,false);
        TempatViewHolder viewHolder=new TempatViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public int getItemCount(){
        return models.size();
    }

    @Override
    public void onBindViewHolder(TempatViewHolder viewHolder,final int position){
        viewHolder.venue_name.setText(models.get(position).getVenues_name());
      viewHolder.venue_address.setText(models.get(position).getAddress());
        String j= String.valueOf(models.get(position).getDistance());
        Double jarak=Double.parseDouble(j)/1000;

       // viewHolder.venue_distance.setText("Jarak : "+jarak.toString()+ " KM");
        viewHolder.card_tempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),TempatDetail.class);
                i.putExtra("venue_id",models.get(position).getVenues_id());
                v.getContext().startActivity(i);
            }
        });

    }
    public static class TempatViewHolder extends RecyclerView.ViewHolder{
        public TextView venue_name;
        public TextView venue_address;
        public  TextView venue_distance;
        public CardView card_tempat;

        public TempatViewHolder(View itemLayoutView){
            super(itemLayoutView);
            venue_name=(TextView) itemLayoutView.findViewById(R.id.venue_name);
            venue_address=(TextView) itemLayoutView.findViewById(R.id.venue_address);
            venue_distance=(TextView) itemLayoutView.findViewById(R.id.venue_distance);
            card_tempat=(CardView) itemLayoutView.findViewById(R.id.card_tempat);
        }
    }
}
