package com.ankit.empowerher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RecentUpdatesAdapter extends ArrayAdapter<String> {

    public RecentUpdatesAdapter(Context context, List<String> updates) {
        super(context, 0, updates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String update = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(update);

        return convertView;
    }
}
