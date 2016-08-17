package me.zhouzhuo.zzletterssidebar.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.zhouzhuo.zzletterssidebar.R;

/**
 * Created by zz on 2016/8/17.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tvLetter;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        tvLetter = (TextView) itemView.findViewById(R.id.tv_letter);
    }
}
