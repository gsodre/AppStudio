package com.example.sodr.appstudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sodré on 10/12/2017.
 */

public class PhotosAdapter extends BaseAdapter {

    private Context context;
    private List<Photo> photos;
    private String pathPicture;

    public PhotosAdapter(Context context, List<Photo> photos, String pathPicture) {
        this.context = context;
        this.photos = photos;
        this.pathPicture = pathPicture;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = photos.get(position);

        View item = LayoutInflater.from(context).inflate(R.layout.item_photo, null);

        ImageView imagePhoto = (ImageView) item.findViewById(R.id.imagePhoto);
        TextView textAuthor = (TextView) item.findViewById(R.id.textAuthor);
        TextView textTitle = (TextView) item.findViewById(R.id.textTitle);
        TextView textLocal = (TextView) item.findViewById(R.id.textLocal);
        TextView textVotes = (TextView) item.findViewById(R.id.textVotes);

        Picasso.with(context).load(pathPicture + photo.getId() + ".jpg").into(imagePhoto);
        textAuthor.setText("Autor: " + photo.getAuthor());
        textTitle.setText("Título: " + photo.getTitle());
        textLocal.setText("Local: " + photo.getLocal());
        textVotes.setText("Votos: " + String.valueOf(photo.getVotes()));

        return item;
    }
}
