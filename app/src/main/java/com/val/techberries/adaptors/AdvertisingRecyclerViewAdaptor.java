package com.val.techberries.adaptors;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.R;
import com.val.techberries.entities.Advertising;
import com.val.techberries.entities.Item;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;

public class AdvertisingRecyclerViewAdaptor extends ListAdapter<Advertising,AdvertisingRecyclerViewAdaptor.ViewHolder> {
    private OnRecyclerViewItemClick listener;
    private Context context;

    private int layoutForRecyclerViewItem;

    public AdvertisingRecyclerViewAdaptor(int layoutForRecyclerViewItem,Context context) {
        super(DIFFS_CALLBACK);
        this.layoutForRecyclerViewItem=layoutForRecyclerViewItem;
        this.context=context;
    }

    private static final  DiffUtil.ItemCallback<Advertising>DIFFS_CALLBACK=new DiffUtil.ItemCallback<Advertising>() {
        @Override
        public boolean areItemsTheSame(@NonNull Advertising oldItem, @NonNull Advertising newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Advertising oldItem, @NonNull Advertising newItem) {
            return false;
        }


    };

    @NonNull
    @Override
    public AdvertisingRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layoutForRecyclerViewItem,parent,false);
        return new AdvertisingRecyclerViewAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bitmap image=getItem(position).getImage();
        holder.textView.setText("");
        holder.itemImage.setImageBitmap(image);
    }


    private Advertising getItemAtPosition(int position){
        return getItem(position);
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView textView;
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
