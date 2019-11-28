package com.val.techberries.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.val.techberries.entities.Item;
import com.val.techberries.R;

import java.util.ArrayList;

public class GridViewAdaptor extends BaseAdapter {

    private Context context;
    private ArrayList<Item>  data;

    public GridViewAdaptor(Context context, ArrayList<Item> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.grid_view_item,parent,false);
        }else {
            view=(View) convertView;
        }
        ImageView productImage=view.findViewById(R.id.itemImage);
        TextView description=view.findViewById(R.id.prodctListGridView_description);
        TextView cost=view.findViewById(R.id.prodctListGridView_productCost);

        Button likeBtn=view.findViewById(R.id.prodctListGridView_likeBtn);
        final Button addToCartBtn=view.findViewById(R.id.prodctListGridView_addToCartBtn);

        productImage.setImageResource(data.get(position).getItemImage());
        description.setText(data.get(position).getItemName());
       // cost.setText(data.get(position).getCost());

        likeBtn.setOnClickListener(v->doLike(position));
        addToCartBtn.setOnClickListener(v -> addToCart(position));

        return view;
    }

    private  void doLike(int position){
        Toast.makeText(context.getApplicationContext(),"Like From "+position,Toast.LENGTH_LONG).show();
    }
    private void addToCart(int position){
        Toast.makeText(context.getApplicationContext(),"Add to Cart From "+position,Toast.LENGTH_LONG).show();
    }
}
