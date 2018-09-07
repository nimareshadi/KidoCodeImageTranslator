package com.reshadi.nima.kidocodeimagetranslator.View.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reshadi.nima.kidocodeimagetranslator.Model.TargetLanguage;
import com.reshadi.nima.kidocodeimagetranslator.R;

import java.util.List;

public class TargetLanguageAdapter extends ArrayAdapter<TargetLanguage> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<TargetLanguage> items;
    private final int mResource;

    public TargetLanguageAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.language_name_text_view);


        TargetLanguage targetLanguage = items.get(position);

        offTypeTv.setText(targetLanguage.getName());


        return view;
    }

    public List<TargetLanguage> getItems() {
        return items;
    }
}
