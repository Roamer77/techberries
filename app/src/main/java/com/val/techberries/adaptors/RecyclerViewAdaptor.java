package com.val.techberries.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.Entities.Item;
import com.val.techberries.R;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;

import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>{

    private OnRecyclerViewItemClick  listener;
    private Context context;
    private List<Item> data;
    private int layoutForRecyclerViewItem;
    public RecyclerViewAdaptor(List<Item>items, Context context, int layoutForRecyclerViewItem) {
        data=items;
        this.context=context;
        this.layoutForRecyclerViewItem=layoutForRecyclerViewItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layoutForRecyclerViewItem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int imageId=data.get(position).getItemImage();
        holder.textView.setText(data.get(position).getItemName());
        holder.itemImage.setImageDrawable(context.getResources().getDrawable(imageId));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private   int getImageIdByName(String name){
        String  pkgName=context.getPackageName();
        int resId=context.getResources().getIdentifier(name,"mipmap",pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resId);
        return resId;
    }

    private Item getItemAtPosition(int position){
        return data.get(position);
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
