package com.trivediinfoway.firebasechatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class DemoList extends AppCompatActivity {

    ListView ll;
    String[] array = {"1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20","21"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);

        ll = (ListView) findViewById(R.id.ll);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DemoList.this,android.R.layout.simple_list_item_1,array);
        ll.setAdapter(arrayAdapter);

        ll.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                this.currentScrollState = scrollState;
                this.isScrollCompleted();
                Log.e("onScrollStateChanged","onScrollStateChanged");
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
                Log.e("onScroll",this.totalItem+" :Total Items");
            }
            private void isScrollCompleted() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount
                        && this.currentScrollState == SCROLL_STATE_IDLE) {
                    Log.e("isScrollCompleted","isScrollCompleted");
                    /** To do code here*/
                }
            }
        });
    }
}
