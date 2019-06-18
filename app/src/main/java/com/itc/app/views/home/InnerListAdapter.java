package com.itc.app.views.home;

import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.itc.app.R;
import com.itc.app.constants.AppConstants;
import com.itc.app.helper.GlideHelper;
import com.itc.app.models.home.Product;

import java.util.List;

public class InnerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> itemsList;
    private View.OnClickListener mOnClickListener;

    public InnerListAdapter(List<Product> itemsList, View.OnClickListener clickListener) {
        this.itemsList = itemsList;
        mOnClickListener = clickListener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_innner, null);
        ProductHolder mh = new ProductHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ProductHolder) {
            ProductHolder holder = (ProductHolder) viewHolder;
            holder.tvTitle.setText(itemsList.get(viewHolder.getAdapterPosition()).getProductName());
            GlideHelper.Companion.getInstance().loadImage(holder.ivThumb, AppConstants.BASE_URL_IMAGE + itemsList.get(viewHolder.getAdapterPosition()).getProductImage());
            holder.itemView.setTag(R.id.product, itemsList.get(viewHolder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView ivThumb;

        public ProductHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.ivThumb = (ImageView) view.findViewById(R.id.iv_thumb);

            view.setOnClickListener(mOnClickListener);
        }
    }

    public void addProducts(List<Product> products) {
        itemsList.addAll(products);
        notifyItemRangeChanged(itemsList.size() - products.size() - 1, products.size());
    }
}