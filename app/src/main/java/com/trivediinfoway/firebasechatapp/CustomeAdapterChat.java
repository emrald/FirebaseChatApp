package com.trivediinfoway.firebasechatapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by TI A1 on 26-12-2017.
 */

public class CustomeAdapterChat extends BaseAdapter {

    ArrayList<chat_class> arrayList;
    Activity activity;
    LayoutInflater inflater = null;

    public CustomeAdapterChat(Activity activity, ArrayList<chat_class> arrayList) {
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
        TextView textView;
        ImageView imageview;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.row_chat, null);
            viewHolder.textView = (TextView)view.findViewById(R.id.textView);
            viewHolder.imageview = (ImageView)view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        chat_class data = arrayList.get(i);

        if(!data.getTxt_message().equals("")) {
            if (data.getTxt_message().contains("https://firebasestorage.googleapis.com")) {
                viewHolder.imageview.setVisibility(View.VISIBLE);
                viewHolder.textView.setVisibility(View.GONE);
                //Glide.with(activity).load(data.getTxt_message()).dontAnimate().into(viewHolder.imageview);
                Picasso.with(activity).load(data.getTxt_message()).rotate(90f).fit().centerInside().placeholder(activity.getResources().getDrawable(R.mipmap.ic_launcher)).error(activity.getResources().getDrawable(R.mipmap.ic_launcher)).into(viewHolder.imageview);
            } else {
                viewHolder.imageview.setVisibility(View.GONE);
                viewHolder.textView.setVisibility(View.VISIBLE);
                viewHolder.textView.setText(data.getTxt_message());
            }
        }
        /*if(!data.getIamge().equals("")) {
            viewHolder.imageview.setVisibility(View.VISIBLE);
            Bitmap image = null;
            try {
                image = decodeFromFirebaseBase64(data.getIamge());
            } catch (IOException e) {
                e.printStackTrace();
            }
            viewHolder.imageview.setImageBitmap(image);*/
          //  Picasso.with(activity).load(data.getIamge()).fit().centerInside().placeholder(activity.getResources().getDrawable(R.mipmap.ic_launcher)).error(activity.getResources().getDrawable(R.mipmap.ic_launcher)).into(viewHolder.imageview);

           /* Picasso picasso = new Picasso.Builder(activity).listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                    e.printStackTrace();
                }
            }).build();
            picasso.setLoggingEnabled(true);
            picasso.load(data.getIamge()).placeholder(R.mipmap.ic_launcher).fit().into(viewHolder.imageview);
*/
      /*      Log.e("url...",data.getIamge()+" : url");
            Glide.with(activity).load(data.getIamge()).dontAnimate().into(viewHolder.imageview);*/
           /* viewHolder.imageview.setVisibility(View.VISIBLE);
            byte[] imageBytes = Base64.decode(data.getIamge(), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            viewHolder.imageview.setImageBitmap(decodedImage);*/
        /*}
        else
        {
            viewHolder.imageview.setVisibility(View.GONE);
        }*/

        /*viewHolder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        return view;
    }
    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
