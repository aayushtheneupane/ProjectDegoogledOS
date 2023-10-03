package com.android.dialer.speeddial.draghelper;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.dialer.common.Assert;

public class SpeedDialFavoritesViewHolderOnTouchListener implements View.OnTouchListener {
    private final ViewConfiguration configuration;
    private boolean hasPerformedLongClick;
    private final ItemTouchHelper itemTouchHelper;
    private final OnTouchFinishCallback onTouchFinishCallback;
    private float startX;
    private float startY;
    private final RecyclerView.ViewHolder viewHolder;

    public interface OnTouchFinishCallback {
        void onTouchFinished(boolean z);
    }

    public SpeedDialFavoritesViewHolderOnTouchListener(ViewConfiguration viewConfiguration, ItemTouchHelper itemTouchHelper2, RecyclerView.ViewHolder viewHolder2, OnTouchFinishCallback onTouchFinishCallback2) {
        this.configuration = viewConfiguration;
        this.itemTouchHelper = itemTouchHelper2;
        this.viewHolder = viewHolder2;
        this.onTouchFinishCallback = onTouchFinishCallback2;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            boolean z = false;
            if (action != 1) {
                if (action == 2) {
                    if (motionEvent.getEventTime() - motionEvent.getDownTime() > ((long) ViewConfiguration.getLongPressTimeout())) {
                        if (!this.hasPerformedLongClick) {
                            view.performLongClick();
                            view.performHapticFeedback(0);
                            this.hasPerformedLongClick = true;
                        } else {
                            Assert.checkArgument(motionEvent.getAction() == 2);
                            if (motionEvent.getHistorySize() > 0 && (Math.abs(this.startX - motionEvent.getX()) > ((float) this.configuration.getScaledTouchSlop()) || Math.abs(this.startY - motionEvent.getY()) > ((float) this.configuration.getScaledTouchSlop()))) {
                                z = true;
                            }
                            if (z) {
                                this.itemTouchHelper.startDrag(this.viewHolder);
                                this.onTouchFinishCallback.onTouchFinished(true);
                            }
                        }
                    }
                    return true;
                } else if (action != 3) {
                    return false;
                }
            } else if (motionEvent.getEventTime() - motionEvent.getDownTime() < ((long) ViewConfiguration.getLongPressTimeout())) {
                view.performClick();
            }
            this.hasPerformedLongClick = false;
            this.onTouchFinishCallback.onTouchFinished(false);
            return true;
        }
        this.startX = motionEvent.getX();
        this.startY = motionEvent.getY();
        return true;
    }
}
