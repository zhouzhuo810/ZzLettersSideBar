package me.zhouzhuo.zzletterssidebardemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import me.zhouzhuo.zzletterssidebar.adapter.BaseSortListViewAdapter;
import me.zhouzhuo.zzletterssidebardemo.R;
import me.zhouzhuo.zzletterssidebardemo.entity.PersonEntity;
import me.zhouzhuo.zzletterssidebar.viewholder.BaseViewHolder;

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
