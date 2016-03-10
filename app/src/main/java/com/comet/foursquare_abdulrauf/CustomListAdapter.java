package com.comet.foursquare_abdulrauf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abdul on 11/10/15.
 */
public class CustomListAdapter extends ArrayAdapter<CustomListItem> {

    Context context;

    TextView title;
    TextView description;
    TextView rating;
    TextView place;
    ImageView imageView;
    CustomListItem singleItem;

    public CustomListAdapter(Context context) {
        super(context, R.layout.singlelistitem);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.singlelistitem,parent,false);
        }

        title = (TextView) convertView.findViewById(R.id.titleTextView);
        description = (TextView) convertView.findViewById(R.id.descriptionTextView);
        rating = (TextView) convertView.findViewById(R.id.ratingTextView);
        place = (TextView) convertView.findViewById(R.id.placeTextView);
        imageView = (ImageView) convertView.findViewById(R.id.imageView);

        singleItem = getItem(position);

        title.setText(singleItem.getTitle());
        description.setText(singleItem.getDescription());
        rating.setText(String.valueOf(singleItem.getRating()));
        place.setText(singleItem.getPlace());
        imageView.setImageBitmap(singleItem.getBitmap());

        return convertView;
    }
}
