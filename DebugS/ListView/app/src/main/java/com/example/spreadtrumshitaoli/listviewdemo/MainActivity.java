package com.example.spreadtrumshitaoli.listviewdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity implements AbsListView.OnScrollListener {

    private int mNumElements=1000;
    private String[] mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
        mItems = new String[mNumElements];
        for (int i = 0; i < mNumElements; i++) {
            mItems[i] = Integer.toString(i);
            if(i%10 == 0) {
                Log.d("lstdemo","i: "+i);
            }
        }
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mItems));
        ListView view = getListView();
        view.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
