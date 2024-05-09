package com.example.cupcake.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cupcake.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<ProductClass> productList;

    public ProductAdapter(ArrayList<ProductClass> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductClass product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productDescription;
        public TextView productQuantity;
        public TextView productPrice;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.TV_ProductName);
            productDescription = itemView.findViewById(R.id.TV_ProductDescription);
            productQuantity = itemView.findViewById(R.id.TV_ProductQuantity);
            productPrice = itemView.findViewById(R.id.RV_ProductPrice);
        }

        void bind(ProductClass product) {
            productName.setText(product.getProductName());
            productDescription.setText(product.getProductDescription());
            productQuantity.setText("Quantity:- " + String.valueOf(product.getProductQuantity()));
            productPrice.setText("Rs."+String.valueOf(product.getProductPrice()));
        }
    }
}

130