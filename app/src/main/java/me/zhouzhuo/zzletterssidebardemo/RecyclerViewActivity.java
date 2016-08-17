package me.zhouzhuo.zzletterssidebardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzletterssidebar.ZzLetterSideBar;
import me.zhouzhuo.zzletterssidebar.interf.OnLetterTouchListener;
import me.zhouzhuo.zzletterssidebar.widget.ZzRecyclerView;
import me.zhouzhuo.zzletterssidebardemo.adapter.PersonRecyclerViewAdapter;
import me.zhouzhuo.zzletterssidebardemo.entity.PersonEntity;

/**
 * Created by zz on 2016/8/17.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private ZzRecyclerView rv;
    private List<PersonEntity> mDatas;
    private PersonRecyclerViewAdapter adapter;
    private ZzLetterSideBar sideBar;
    private TextView tvDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //findView
        tvDialog = (TextView) findViewById(R.id.tv_dialog);
        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        rv = (ZzRecyclerView) findViewById(R.id.rv);

        //set adapter
        mDatas = new ArrayList<>();
        adapter = new PersonRecyclerViewAdapter(this, mDatas);
        rv.setAdapter(adapter);

        //init data
        String[] personNames = getResources().getStringArray(R.array.persons);
        List<PersonEntity> personEntities = new ArrayList<>();
        for (String name : personNames) {
            PersonEntity entity = new PersonEntity();
            entity.setPersonName(name);
            personEntities.add(entity);
        }

        //update data
        mDatas = personEntities;
        adapter.updateRecyclerView(mDatas);
        adapter.notifyDataSetChanged();

        //set touch event, must add
        sideBar.setLetterTouchListener(rv, adapter, tvDialog, new OnLetterTouchListener() {
            @Override
            public void onLetterTouch(String letter, int position) {
            }

            @Override
            public void onActionUp() {
            }
        });

    }
}
