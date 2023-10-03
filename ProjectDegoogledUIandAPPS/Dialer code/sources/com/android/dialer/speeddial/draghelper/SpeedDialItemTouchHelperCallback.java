package com.android.dialer.speeddial.draghelper;

import android.content.Context;
import android.graphics.Canvas;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.speeddial.SpeedDialAdapter;

public class SpeedDialItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter adapter;
    private final Context context;
    private boolean inRemoveView;
    private boolean movedOverRemoveView;

    public interface ItemTouchHelperAdapter {
    }

    public SpeedDialItemTouchHelperCallback(Context context2, ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.context = context2;
        this.adapter = itemTouchHelperAdapter;
    }

    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return ((SpeedDialAdapter) this.adapter).canDropOver(viewHolder2);
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return !((SpeedDialAdapter) this.adapter).canDropOver(viewHolder) ? 0 : 3342387;
    }

    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public boolean isLongPressDragEnabled() {
        return false;
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if (this.inRemoveView) {
            if (!z) {
                this.inRemoveView = false;
                ((SpeedDialAdapter) this.adapter).dropOnRemoveView(viewHolder);
            }
            if (!this.movedOverRemoveView) {
                this.inRemoveView = false;
                ((SpeedDialAdapter) this.adapter).leaveRemoveView();
            }
        }
        this.movedOverRemoveView = false;
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder2.getItemViewType() == 0) {
            this.movedOverRemoveView = true;
            if (!this.inRemoveView) {
                ((SpeedDialAdapter) this.adapter).enterRemoveView();
                this.inRemoveView = true;
            }
            return false;
        }
        if (this.inRemoveView) {
            this.inRemoveView = false;
            this.movedOverRemoveView = false;
            ((SpeedDialAdapter) this.adapter).leaveRemoveView();
        }
        ((SpeedDialAdapter) this.adapter).onItemMove(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.FAVORITE_MOVE_FAVORITE_BY_DRAG_AND_DROP);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof ItemTouchHelper.ViewDropHandler) {
            ((ItemTouchHelper.ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i3, i4);
            return;
        }
        if (layoutManager.canScrollHorizontally()) {
            if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                recyclerView.scrollToPosition(i2);
            }
            if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                recyclerView.scrollToPosition(i2);
            }
        }
        if (layoutManager.canScrollVertically()) {
            if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                recyclerView.scrollToPosition(i2);
            }
            if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                recyclerView.scrollToPosition(i2);
            }
        }
    }

    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        super.onSelectedChanged(viewHolder, i);
        ((SpeedDialAdapter) this.adapter).onSelectedChanged(viewHolder, i);
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }
}
