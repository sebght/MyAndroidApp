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
            convertView = inflater.inflate(R.layout.activity_destination, null);
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
        // il faut un get de l'image à partir de l'url ici
        String urlString = destination.getImage();
        ImageView image=(ImageView) convertView.findViewById(R.id.imageView);
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(image);
        holder.tvType.setText(destination.getType());
        holder.tvTitre.setText(destination.getTitre());
        String dist=destination.getDistance().toString();
        holder.tvDistance.setText(dist);
        return convertView;
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
