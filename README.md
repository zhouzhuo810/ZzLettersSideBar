# ZzLettersSideBar
A SideBar of letters for Contacts like WeChat(ZzLettersSideBar)

gradle 

```

compile 'me.zhouzhuo.zzletterssidebar:zz-letters-sidebar:1.0.0'

```

<strong>What does it look like ?</strong>

<img src="https://github.com/zhouzhuo810/ZzLettersSideBar/blob/master/zzlettersizebar.gif" width="492" height="871"/>

<strong>How to use it ?</strong>

Step 1. main layout
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

step 3. the layout of listview item must add a TextView with id "@id/tv_letter"

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

step 4. your adapter of listview must extends BaseSortAdapter
and your ViewHolder must extends BaseViewHolder.

```java
/**
 * Created by zz on 2016/8/15.
 */
public class PersonListAdapter extends BaseSortAdapter<PersonEntity, PersonListAdapter.ViewHolder> {

    public PersonListAdapter(Context ctx, List<PersonEntity> datas) {
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

step 5.add Listener


```java
    private ListView listView;
    private ZzLetterSideBar sideBar;
    private TextView dialog;
    private PersonListAdapter adapter;
    private List<PersonEntity> mDatas;

        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.tv_dialog);
        listView = (ListView) findViewById(R.id.list_view);
        mDatas = new ArrayList<>();
        adapter = new PersonListAdapter(this, mDatas);
        listView.setAdapter(adapter);
```

```java
        //设置右侧触摸监听，必须写
        sideBar.setLetterTouchListener(listView, adapter, dialog, new OnLetterTouchListener() {
            @Override
            public void onLetterTouch(String letter, int position) {
            }

            @Override
            public void onActionUp() {
            }
        });
```



