package com.reshadi.nima.kidocodeimagetranslator.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.reshadi.nima.kidocodeimagetranslator.Model.Database.Model.SavedText;
import com.reshadi.nima.kidocodeimagetranslator.R;

import java.util.List;


/**
 * Created by n.reshadi on 3/15/2016.
 */
public class SavedTextAdapter extends ArrayAdapter<SavedText> {
    private final Context context;
    private final List<SavedText> savedTextList;

    public SavedTextAdapter(Context context, List<SavedText> savedTextList) {
        super(context, R.layout.saved_text_row, R.id.saved_text_view, savedTextList);
        this.context = context;
        this.savedTextList = savedTextList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.saved_text_row, parent, false);
        final int index = position;
        //init views
        TextView savedTextView = (TextView) rowView.findViewById(R.id.saved_text_view);


        //init values
        savedTextView.setText(savedTextList.get(position).getContent());

        return rowView;
    }


}