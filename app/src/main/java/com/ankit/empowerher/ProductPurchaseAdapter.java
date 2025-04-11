package com.ankit.empowerher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductPurchaseAdapter extends RecyclerView.Adapter<ProductPurchaseAdapter.ProductViewHolder> {

    private List<ProductPurchase> products;

    public ProductPurchaseAdapter(List<ProductPurchase> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_purchase, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductPurchase product = products.get(position);

        holder.tvProductName.setText(product.getName());
        holder.tvProductDescription.setText(product.getDescription());
        holder.tvProductPrice.setText(String.valueOf(product.getCost()));
        holder.ivProductImage.setImageResource(product.getImageResId());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateProducts(List<ProductPurchase> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName;
        TextView tvProductDescription;
        TextView tvProductPrice;
        ImageView ivProductImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductDescription = itemView.findViewById(R.id.tv_product_description);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            ivProductImage = itemView.findViewById(R.id.iv_product_image);
        }
    }
}
