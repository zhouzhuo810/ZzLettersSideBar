package me.zhouzhuo.zzletterssidebardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo.zzletterssidebar.ZzLetterSideBar;
import me.zhouzhuo.zzletterssidebar.interf.OnLetterTouchListener;
import me.zhouzhuo.zzletterssidebardemo.adapter.PersonListAdapter;
import me.zhouzhuo.zzletterssidebardemo.entity.PersonEntity;


/**
 * Created by zz on 2016/5/12.
 */
public class ListViewActivity extends AppCompatActivity {

    private ListView sortListView;
    private ZzLetterSideBar sideBar;
    private TextView dialog;
    private PersonListAdapter adapter;

    private List<PersonEntity> sourceDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initView() {


        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.tv_dialog);
        sortListView = (ListView) findViewById(R.id.list_view);

        View header = LayoutInflater.from(this).inflate(R.layout.list_item_head, null);
        sortListView.addHeaderView(header);

        sourceDateList = new ArrayList<>();
        adapter = new PersonListAdapter(this, sourceDateList);
        sortListView.setAdapter(adapter);
    }

    public void initData() {
        String[] personNames = getResources().getStringArray(R.array.persons);
        List<PersonEntity> personEntities = new ArrayList<>();
        for (String name : personNames) {
            PersonEntity entity = new PersonEntity();
            entity.setPersonName(name);
            personEntities.add(entity);
        }
        sourceDateList = personEntities;
        adapter.updateListView(sourceDateList);

    }

    public void initEvent() {

        //设置右侧触摸监听
        sideBar.setLetterTouchListener(sortListView, adapter, dialog, new OnLetterTouchListener() {
            @Override
            public void onLetterTouch(String letter, int position) {
            }

            @Override
            public void onActionUp() {
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= sortListView.getHeaderViewsCount()) {
                    TextView tvName = (TextView) view.findViewById(R.id.list_item_tv_name);
                    String name = tvName.getText().toString().trim();
                    Toast.makeText(ListViewActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
