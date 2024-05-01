
package com.enset.tp_synthese.ui.bookconsulting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.enset.tp_synthese.R;
import com.enset.tp_synthese.ui.bookconsulting.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.book_item,parent,false);
        }

        Book book=getItem(position);
        ImageView imageView= convertView.findViewById(R.id.image_id);
        TextView title=convertView.findViewById(R.id.book_title);
        TextView Authors=convertView.findViewById(R.id.book_authors);
        TextView Description=convertView.findViewById(R.id.book_description);

        title.setText(book.getVolumeinfo().getTitle());
        Authors.setText(book.getVolumeinfo().getAuthors().toString());
        Description.setText(book.getVolumeinfo().getDescription());
        Picasso.get().load(book.getVolumeinfo().getImagelink().getSmallThumbnail().replace("http://","https://")).into(imageView) ;

        return convertView;
    }
}

