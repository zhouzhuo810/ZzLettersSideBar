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
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder) {
            //must add this
            final int mPos = position - getHeadViewSize();
            if (mPos < mDatas.size()) {
                initLetter(holder, mPos);
                ((MyViewHolder) holder).tvName.setText(mDatas.get(mPos).getPersonName());
                //add click event optional
                initClickListener(holder, mPos);
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
