package com.val.techberries.adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.entities.Item;
import com.val.techberries.R;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;

import java.util.List;

public class RecyclerViewAdaptor extends ListAdapter<Item,RecyclerViewAdaptor.ViewHolder> {

    private OnRecyclerViewItemClick  listener;
    private Context context;

    private int layoutForRecyclerViewItem;

    public  RecyclerViewAdaptor (int layoutForRecyclerViewItem,Context context){
        super(DIFFS_CALLBACK);
        this.layoutForRecyclerViewItem=layoutForRecyclerViewItem;
        this.context=context;
    }


     private static final  DiffUtil.ItemCallback<Item>DIFFS_CALLBACK =new DiffUtil.ItemCallback<Item>() {
         @Override
         public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
             return oldItem.getId()==newItem.getId();
         }

         @Override
         public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
             return oldItem.getCost()==newItem.getCost() && oldItem.getDescription().equals(newItem.getDescription());
         }
     };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layoutForRecyclerViewItem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bitmap image=getItem(position).getItemImage();
        holder.textView.setText(getItem(position).getItemName());
        holder.itemImage.setImageBitmap(image);
    }


    private Item getItemAtPosition(int position){
        return getItem(position);
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private  TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.firstTextVIew);
            itemImage=itemView.findViewById(R.id.recycler_item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if(listener !=null&& position !=RecyclerView.NO_POSITION){
                        listener.onClick(getItemAtPosition(position));
                    }
                }
            });
        }
    }


    public void setItemClickListener(OnRecyclerViewItemClick listener) {
        this.listener = listener;
    }
}
