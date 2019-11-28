package com.val.techberries.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.val.techberries.R;
import com.val.techberries.entities.ItemToUserCart;
import com.val.techberries.interfacies.OnCartRecyclerViewItemClick;
import com.val.techberries.interfacies.OnRecyclerViewItemClick;

public class CartRecyclerViewAdaptor extends ListAdapter<ItemToUserCart, CartRecyclerViewAdaptor.ViewHolder> {

    private Context context;
    private OnCartRecyclerViewItemClick listener;

    public CartRecyclerViewAdaptor(Context context) {
        super(DIFFS_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<ItemToUserCart> DIFFS_CALLBACK = new DiffUtil.ItemCallback<ItemToUserCart>() {

        @Override
        public boolean areItemsTheSame(@NonNull ItemToUserCart oldItem, @NonNull ItemToUserCart newItem) {
            return oldItem.getId() == newItem.getId();
        }

        //сть в будущем добавяться обязательные для сравления атрибуты то их тут нужно прописать!
        @Override
        public boolean areContentsTheSame(@NonNull ItemToUserCart oldItem, @NonNull ItemToUserCart newItem) {
            return oldItem.getCost() == newItem.getCost() && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getItemImage() == newItem.getItemImage();
        }
    };

    @NonNull
    @Override
    public CartRecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_in_cart_for_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdaptor.ViewHolder holder, int position) {
        int imageId= getItem(position).getItemImage();
        int cost=getItem(position).getCost();
        String description=getItem(position).getDescription();
        String name=getItem(position).getItemName();

        holder.image.setImageResource(imageId);
        holder.cost.append(String.valueOf(cost));
        holder.description.append(description);
        holder.name.append(name);
    }

        public  ItemToUserCart getItemInCartAt(int position){
            return getItem(position);
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView description;
        private TextView cost;
        private TextView name;
        private Button deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.itemImage);
            description = itemView.findViewById(R.id.itemDescription);
            cost = itemView.findViewById(R.id.itemCost);
            name = itemView.findViewById(R.id.itemName);
            deleteBtn=itemView.findViewById(R.id.deleteItemFromCart_btn);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onClick(getItem(position));
                    }
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "Удалена одна запись", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void setOnItemClickListener(OnCartRecyclerViewItemClick listener) {
        this.listener = listener;
    }
}