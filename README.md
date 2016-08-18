# ZzLettersSideBar
A SideBar of letters for Contacts like WeChat(ZzLettersSideBar)

gradle 

```

compile 'me.zhouzhuo.zzletterssidebar:zz-letters-sidebar:1.0.1'

```

<strong>What does it look like ?</strong>

<img src="https://github.com/zhouzhuo810/ZzLettersSideBar/blob/master/zzlettersizebar.gif" width="492" height="871"/>

<strong>How to use it ?</strong>

Step 1. main layout

For ListView

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="none" />
    <!--触摸时显示的字母-->
    <TextView
        android:id="@+id/tv_dialog"
        android:layout_width="100dp"
        android:layout_height="90dp"
        android:layout_centerInParent="true"
        android:background="@drawable/tv_dialog_bg"
        android:gravity="center"
        android:textColor="@android:color/white"
        android:textSize="36sp"
        android:visibility="gone" />
    <!--右侧字母栏-->
    <me.zhouzhuo.zzletterssidebar.ZzLetterSideBar
        android:id="@+id/sidebar"
        android:layout_width="24dp"
        android:layout_height="match_parent"
        android:layout_alignParentRight="true"
        android:layout_marginBottom="50dp"
        android:layout_marginTop="50dp" />

</RelativeLayout>
  
```

For RecyclerView
```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <me.zhouzhuo.zzletterssidebar.widget.ZzRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <TextView
        android:id="@+id/tv_dialog"
        android:layout_width="100dp"
        android:layout_height="90dp"
        android:layout_centerInParent="true"
        android:background="@drawable/tv_dialog_bg"
        android:gravity="center"
        android:textColor="@android:color/white"
        android:textSize="36sp"
        android:visibility="gone" />

    <me.zhouzhuo.zzletterssidebar.ZzLetterSideBar
        android:id="@+id/sidebar"
        android:layout_width="24dp"
        android:layout_height="match_parent"
        android:layout_alignParentRight="true"
        android:layout_marginBottom="50dp"
        android:layout_marginTop="50dp" />

</RelativeLayout>

```

step 2.your java bean must extends SortModel
and you should add @Letter anotation to the field for sortting.
```java
/**
 * Created by zz on 2016/8/15.
 */
public class PersonEntity extends SortModel {
    //the sort field must add this anotation
    @Letter(isSortField = true)
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
```

step 3. the layout of listview item or recyclerview item must add a TextView with id "@id/tv_letter"

```xml
    <TextView
        android:id="@id/tv_letter"
        android:layout_width="match_parent"
        android:layout_height="20dp"
        android:background="#e9e9e9"
        android:gravity="center_vertical"
        android:paddingLeft="20dp"
        android:textColor="#7f7f7f"
        android:textSize="10sp"
        android:visibility="gone" />

```

step 4. 
For ListView
your adapter of listview must extends BaseSortListViewAdapter
and your ViewHolder must extends BaseViewHolder.

```java
/**
 * Created by zz on 2016/8/15.
 */
public class PersonListViewAdapter extends BaseSortListViewAdapter<PersonEntity, PersonListViewAdapter.ViewHolder> {

    public PersonListViewAdapter(Context ctx, List<PersonEntity> datas) {
        super(ctx, datas);
    }

    @Override
    public int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tvName = (TextView) view.findViewById(R.id.list_item_tv_name);
        return viewHolder;
    }

    @Override
    public void bindValues(ViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(mDatas.get(position).getPersonName());
    }

    public static class ViewHolder extends BaseViewHolder {
        protected TextView tvName;
    }

}

```

For RecyclerView
your adapter of recyclerview must extends BaseSortRecyclerViewAdapter
and your ViewHolder must extends BaseRecyclerViewHolder.
```java

package me.zhouzhuo.zzletterssidebardemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.zhouzhuo.zzletterssidebar.adapter.BaseSortRecyclerViewAdapter;
import me.zhouzhuo.zzletterssidebar.viewholder.BaseRecyclerViewHolder;
import me.zhouzhuo.zzletterssidebardemo.R;
import me.zhouzhuo.zzletterssidebardemo.entity.PersonEntity;

/**
 * Created by zz on 2016/8/17.
 */
public class PersonRecyclerViewAdapter extends BaseSortRecyclerViewAdapter<PersonEntity, BaseRecyclerViewHolder> {

    public PersonRecyclerViewAdapter(Context ctx, List<PersonEntity> mDatas) {
        super(ctx, mDatas);
    }

    //return your itemView layoutRes id
    @Override
    public int getItemLayoutId() {
        return R.layout.list_item;
    }

    //add a header ,optional, if no need, return 0
    @Override
    public int getHeaderLayoutId() {
        return R.layout.list_item_head;
    }

    //add a footer, optional, if no need, return 0
    @Override
    public int getFooterLayoutId() {
        return R.layout.list_item_foot;
    }

    @Override
    public BaseRecyclerViewHolder getViewHolder(View itemView, int type) {
        switch (type) {
            case BaseSortRecyclerViewAdapter.TYPE_HEADER:
                return new HeaderHolder(itemView);
            case BaseSortRecyclerViewAdapter.TYPE_FOOT:
                return new FooterHolder(itemView);
        }
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            //must add this
            int mPos = position - getHeadViewSize();
            if (mPos < mDatas.size()) {
                initLetter(holder, mPos);
                ((MyViewHolder) holder).tvName.setText(mDatas.get(mPos).getPersonName());
            }
        } else if (holder instanceof HeaderHolder) {

        } else if (holder instanceof FooterHolder) {
            ((FooterHolder) holder).tvFoot.setText(getContentCount() + "位联系人");
        }

    }

    //custom your ViewHolder here
    public static class MyViewHolder extends BaseRecyclerViewHolder {

        protected TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.list_item_tv_name);
        }
    }

    public static class HeaderHolder extends BaseRecyclerViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterHolder extends BaseRecyclerViewHolder {

        protected TextView tvFoot;

        public FooterHolder(View itemView) {
            super(itemView);
            tvFoot = (TextView) itemView.findViewById(R.id.tv_foot);
        }
    }

}

```


step 5.add Listener

For ListView

```java
    private ListView listView;
    private ZzLetterSideBar sideBar;
    private TextView dialog;
    private PersonListViewAdapter adapter;
    private List<PersonEntity> mDatas;

        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.tv_dialog);
        listView = (ListView) findViewById(R.id.list_view);

        //optional
        View header = LayoutInflater.from(this).inflate(R.layout.list_item_head, null);
        listView.addHeaderView(header);

        //optional
        View footer = LayoutInflater.from(this).inflate(R.layout.list_item_foot, null);
        tvFoot = (TextView) footer.findViewById(R.id.tv_foot);
        listView.addFooterView(footer);

        //set adapter
        mDatas = new ArrayList<>();
        adapter = new PersonListViewAdapter(this, mDatas);
        listView.setAdapter(adapter);
        
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
        adapter.updateListView(mDatas);
        tvFoot.setText(mDatas.size() + "位联系人");
```

```java
        //设置右侧触摸监听
        sideBar.setLetterTouchListener(listView, adapter, dialog, new OnLetterTouchListener() {
            @Override
            public void onLetterTouch(String letter, int position) {
            }

            @Override
            public void onActionUp() {
            }
        });
```

For RecyclerView



```java
    private ZzRecyclerView rv;
    private List<PersonEntity> mDatas;
    private PersonRecyclerViewAdapter adapter;
    private ZzLetterSideBar sideBar;
    private TextView tvDialog;


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


```



