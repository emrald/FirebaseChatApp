package com.trivediinfoway.firebasechatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TI A1 on 26-12-2017.
 */

public class CustomeAdapterRoomAvatar extends BaseAdapter {

    ArrayList<room_class> arrayList;
    Activity activity;
    LayoutInflater inflater = null;

    public CustomeAdapterRoomAvatar(Activity activity, ArrayList<room_class> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        TextView tvmessage;
        ImageView imgprofile;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.row_room, null);
            viewHolder.tvmessage = (TextView)view.findViewById(R.id.tvmessage);
            viewHolder.imgprofile = (ImageView)view.findViewById(R.id.imgprofile);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        room_class data = arrayList.get(i);
        viewHolder.tvmessage.setText(data.getName());
        viewHolder.imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
