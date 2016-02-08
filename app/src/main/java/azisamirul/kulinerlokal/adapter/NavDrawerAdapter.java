package azisamirul.kulinerlokal.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import azisamirul.kulinerlokal.R;
import azisamirul.kulinerlokal.act.Favorit;
import azisamirul.kulinerlokal.act.Kategori;
import azisamirul.kulinerlokal.act.MainActivity;
import azisamirul.kulinerlokal.act.Rekomendasi;

/**
 * Created by azisamirul on 05/11/2015.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.ViewHolder> {

    String[] title;
    int[] icon;
    Context context;

    public NavDrawerAdapter(String[] title, int[] icon, Context context) {
        this.title = title;
        this.icon = icon;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView navText;
        ImageView navIcon;
        Context context;

        public ViewHolder(View drawerItem, int itemType, Context context) {
            super(drawerItem);
            this.context = context;
            drawerItem.setOnClickListener(this);
            if (itemType == 1) {
                navText = (TextView) itemView.findViewById(R.id.navText);
                navIcon = (ImageView) itemView.findViewById(R.id.nav_icon);
            }
        }

        @Override
        public void onClick(View v) {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.mDrawerLayout.closeDrawers();
            switch (getPosition()) {
                case 1:
                    Intent intent = new Intent(context, Kategori.class);
                    context.startActivity(intent);
                    break;

                case 2:
                    Intent intent2=new Intent(context, Rekomendasi.class);
                    context.startActivity(intent2);
                    break;

            }
        }
    }

    @Override
    public NavDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = layoutInflater.inflate(R.layout.drawer_item, null);
        return new ViewHolder(itemLayout, viewType, context);
    }

    @Override
    public int getItemCount() {
        return title.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }

    @Override
    public void onBindViewHolder(NavDrawerAdapter.ViewHolder holder, int position) {
        if (position != 0) {
            holder.navText.setText(title[position - 1]);
            holder.navIcon.setImageResource(icon[position - 1]);
        }
    }
}
