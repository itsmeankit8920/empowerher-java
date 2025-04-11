package com.ankit.empowerher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class PopularProductsAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public PopularProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = products.get(position);

        TextView nameTextView = convertView.findViewById(R.id.product_name);
        ImageView imageView = convertView.findViewById(R.id.product_image);

        nameTextView.setText(product.getName());
        imageView.setImageResource(product.getImageResourceId());

        return convertView;
    }
}
