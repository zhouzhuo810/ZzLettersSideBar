package me.zhouzhuo.zzletterssidebar.utils;

import java.util.Comparator;

import me.zhouzhuo.zzletterssidebar.entity.SortModel;

public class PinyinComparator implements Comparator<SortModel> {

    @Override
    public int compare(SortModel lhs, SortModel rhs) {
        if (lhs.getSortLetters().equals("@")
                || rhs.getSortLetters().equals("#")) {
            return -1;
        } else if (lhs.getSortLetters().equals("#")
                || rhs.getSortLetters().equals("@")) {
            return 1;
        } else {
            return lhs.getSortLetters().compareTo(rhs.getSortLetters());
        }
    }

}
