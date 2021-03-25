package com.example.onlineshapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    CardView card;
    private List<Product>productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.list_layout,null);
        ProductViewHolder holder= new ProductViewHolder(view );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.prodName.setText(product.getProdName());
        holder.prodDetails.setText(product.getProdDetails());
        holder.prodPrice.setText(String.valueOf(product.getProdPrice()));

        holder.prodImg.setImageResource(product.getProdImg());

        updateBtnAddRemoveCart(product, holder);
        
        holder.btnAddRemoveCart.setOnClickListener(v -> {
            product.setAddedToCart(!product.isAddedToCart());
            updateBtnAddRemoveCart(product, holder);
        });

        card.setOnClickListener(view -> {
            Intent details = new Intent(view.getContext(), ProductDetails.class);
            details.putExtra("_prodImage", product.getProdImg());
            details.putExtra("_prodName", product.getProdName());
            details.putExtra("_prodPrice", product.getProdPrice());
            view.getContext().startActivity(details);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView prodName, prodDetails, prodPrice;
        ImageView prodImg;
        Button btnAddRemoveCart;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.prodName);
            prodDetails = itemView.findViewById(R.id.prodDetails);
            prodPrice = itemView.findViewById(R.id.prodPrice);
            prodImg = itemView.findViewById(R.id.prodImg);
            btnAddRemoveCart = itemView.findViewById(R.id.btnAddRemoveCart);
            card=itemView.findViewById(R.id.card);
        }
    }

    private void updateBtnAddRemoveCart(Product product, ProductViewHolder holder) {
        if (!product.isAddedToCart()) {
            holder.btnAddRemoveCart.setText("Add to Cart");
        } else {
            holder.btnAddRemoveCart.setText("Remove from Cart");
        }
    }

}
