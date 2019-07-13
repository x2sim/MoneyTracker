package com.loftschool.alexandrdubkov.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    private List<Item> mItemList = new ArrayList<>();
    private int mPriceColor;

    public ItemsAdapter(int mPriceColor) {
        this.mPriceColor = mPriceColor;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(viewGroup.getContext(), R.layout.item_view, null);
        TextView priceView = itemView.findViewById(R.id.item_price);
        priceView.setTextColor(itemView.getContext().getResources().getColor(mPriceColor));
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemsAdapter.ItemViewHolder viewHolder, final int position) {
     final Item item = mItemList.get(position);
     viewHolder.bindItem(item);
     viewHolder.setListener(item, position);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
    public void addItem(final Item item){
        mItemList.add(item);
        notifyItemInserted(mItemList.size());
    }

    public void clear() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;
        private TextView mPriceView;
            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                mNameView = itemView.findViewById(R.id.item_name);
                mPriceView = itemView.findViewById(R.id.item_price);
        }
        public void bindItem(final Item item){
                mNameView.setText(item.getName());
                mPriceView.setText(mPriceView.getContext().getResources().getString(R.string.template_price, String.valueOf(item.getPrice())));
        }
        public void setListener(Item item, int position){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        itemView.setSelected(!itemView.isSelected());
                        return false;
                    }
                });
        }
    }
}
