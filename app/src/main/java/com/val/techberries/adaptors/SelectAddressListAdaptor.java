package com.val.techberries.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.val.techberries.R;

import java.util.List;

public class SelectAddressListAdaptor extends BaseAdapter {
    private List<String> data;
    private Context context;
    private LayoutInflater layoutInflater;
   private  int oldPosition=-1;

    public SelectAddressListAdaptor(List<String> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.select_addres_list_item,null,true);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView) convertView.findViewById(R.id.address_tv);
            viewHolder.checkBox=(CheckBox) convertView.findViewById(R.id.address_cb);
            convertView.setTag(viewHolder);
        }else {
           viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.textView.setText(data.get(position));
        viewHolder.checkBox.setTag(R.integer.btnplusview,convertView);
        viewHolder.checkBox.setTag(position);
        viewHolder.checkBox.setChecked(position==oldPosition);


        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View tempview = (View) viewHolder.checkBox.getTag(R.integer.btnplusview);
                Integer pos = (Integer)  viewHolder.checkBox.getTag();
                Toast.makeText(context, "Checkbox "+pos+" clicked!", Toast.LENGTH_SHORT).show();
                if(isChecked){
                    oldPosition=position;
                }else oldPosition=-1;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder{
        TextView textView;
        CheckBox checkBox;
    }

    public List<String> getData() {
        return data;
    }
}
