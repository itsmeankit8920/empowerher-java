package com.ankit.empowerher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class communityUpdatesAdapter extends BaseAdapter {
    private Context context;
    private List<communityUpdate> updatesList;

    public communityUpdatesAdapter(Context context, List<communityUpdate> updatesList) {
        this.context = context;
        this.updatesList = updatesList;
    }

    @Override
    public int getCount() {
        return updatesList.size();
    }

    @Override
    public Object getItem(int position) {
        return updatesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView updateTitle = convertView.findViewById(R.id.update_title);
        TextView updateDescription = convertView.findViewById(R.id.update_description);

        communityUpdate update = updatesList.get(position);
        updateTitle.setText(update.getTitle());
        updateDescription.setText(update.getDescription());

        return convertView;
    }
}
