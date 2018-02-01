package com.trivediinfoway.firebasechatapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ChangePasswordActivity extends AppCompatActivity {

    TextView textView2,textView3,textView4,textView5,textView6;
    Point p;
    int x1,y1;
    int x6,y6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (p != null)
                    showPopup(ChangePasswordActivity.this, p,x1,y1);*/
                showDialog(x1,y1);
            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p != null)
                    showPopup(ChangePasswordActivity.this, p,x6,y6);
            }
        });

        textView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x1 = (int)motionEvent.getX();
                y1 = (int)motionEvent.getY();
                Log.e("x...",x1+"\n"+y1);
              //  showDialog((int)x1,(int)y1);
                return false;
            }
        });
        textView6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x6 = (int)motionEvent.getX();
                y6 = (int)motionEvent.getY();
                Log.e("x...",x6+"\n"+y6);
         //       showDialog6((int)x6,(int)y6);
                return false;
            }
        });
        textView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.e("x...",x+"\n"+y);
                showDialog((int)x,(int)y);
                return false;
            }
        });
        textView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.e("x...",x+"\n"+y);
                showDialog((int)x,(int)y);
                return false;
            }
        });
        textView5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.e("x...",x+"\n"+y);
                showDialog((int)x,(int)y);
                return false;
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] location = new int[2];
        TextView button = (TextView)findViewById(R.id.textView2);
        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);
        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }

    private void showPopup(final Activity context, Point p,int x,int y) {
        int popupWidth = 200;
        int popupHeight = 150;
        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);
        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = x;
        int OFFSET_Y = y;
        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());
        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, x + OFFSET_X, y+100+ OFFSET_Y);
        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    public void showDialog(int x,int y)
    {
      /*  AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        Dialog dialog = builder.create();
        dialog.setTitle("my dialog");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
      //  wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = x;   //x position
        wmlp.y = y;   //y position
        dialog.show();*/
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        Dialog dialog = builder.create();
        dialog.setTitle("my dialog");
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        //  wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.getAttributes().x = x;
        window.getAttributes().y = y;
        dialog.getWindow().setAttributes(wlp);
        dialog.show();
    }
    public void showDialog6(int x,int y)
    {
       /* AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        Dialog dialog = builder.create();
        dialog.setTitle("my dialog");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
     //   wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = x;   //x position
        wmlp.y = y;   //y position
        dialog.show();*/
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
        Dialog dialog = builder.create();
        dialog.setTitle("my dialog");
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

      //  wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        window.getAttributes().x = x;
        window.getAttributes().y = y;
        dialog.show();
    }
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                showDialog((int)x,(int)y);  // display dialog
                break;
            case MotionEvent.ACTION_MOVE:
               *//* if(dialog!=null)
                    dialog.dismiss();*//*
                // do something
                break;
            case MotionEvent.ACTION_UP:
                // do somethig
                break;
        }
        return true;
    }*/
}
