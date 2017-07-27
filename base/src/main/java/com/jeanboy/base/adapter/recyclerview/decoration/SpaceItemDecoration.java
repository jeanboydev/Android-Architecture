package com.jeanboy.base.adapter.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jeanboy on 2017/3/8.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int headerSpace, footerSpace, otherSpace;

    public SpaceItemDecoration(int space) {
        this.headerSpace = space;
        this.footerSpace = space;
        this.otherSpace = space;
    }

    public SpaceItemDecoration(int headerSpace, int footerSpace, int otherSpace) {
        this.headerSpace = headerSpace;
        this.footerSpace = footerSpace;
        this.otherSpace = otherSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position <= (parent.getAdapter().getItemCount() - 1)) {
            if (position == 0 && headerSpace != 0) {
                outRect.top = headerSpace;
            }
            outRect.bottom = otherSpace;
        } else if (footerSpace != 0) {
            outRect.bottom = footerSpace;
        }
    }
}
