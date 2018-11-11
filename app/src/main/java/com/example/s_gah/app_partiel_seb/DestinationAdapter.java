package com.example.s_gah.app_partiel_seb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DestinationAdapter extends BaseAdapter {
    List<Destination> biblio;
    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;
    Context context;
    private static final int TYPE_ITEM_PAIR = 0;
    private static final int TYPE_ITEM_IMPAIR = 1;
    private static final int TYPE_MAX_COUNT = 2;

    private class ViewHolder {
        ImageView ivPic;
        TextView tvType;
        TextView tvTitre;
        TextView tvDistance;
    }

    public DestinationAdapter(Context context, List<Destination> objects) {
        inflater = LayoutInflater.from(context);
        this.biblio = objects;
    }

    /** * Génère la vue pour un objet */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            if (getItemViewType(position)==TYPE_ITEM_PAIR){
                convertView = inflater.inflate(R.layout.activity_destination, null);
            }
            else {
                convertView = inflater.inflate(R.layout.activity_destination2, null);
            }
            holder.ivPic = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tvType = (TextView) convertView.findViewById(R.id.type);
            holder.tvTitre = (TextView) convertView.findViewById(R.id.title);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.dist);
            convertView.setTag(holder);
        }
        else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }

        Destination destination = biblio.get(position);
        String urlString = destination.getImage();
        ImageView image=(ImageView) convertView.findViewById(R.id.imageView);
        Picasso.get().load(urlString).into(image);

        holder.tvType.setText(destination.getType());
        holder.tvTitre.setText(destination.getTitre());
        String dist=destination.getDistance().toString();
        holder.tvDistance.setText(dist);
        return convertView;
    }
    /** * Retourne la vue à appliquer à l'item */
    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return TYPE_ITEM_PAIR;
        }
        else return TYPE_ITEM_IMPAIR;
    }
    /** * Retourne le nombre de layouts différents */
    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }
    /** * Retourne le nombre d'éléments */
    @Override
    public int getCount() {
        return biblio.size();
    }
    /** * Retourne l'item à la position */
    @Override
    public Destination getItem(int position) {
        return biblio.get(position);
    }
    /** * Retourne la position de l'item */
    @Override
    public long getItemId(int position) {
        return position;
    }
}
