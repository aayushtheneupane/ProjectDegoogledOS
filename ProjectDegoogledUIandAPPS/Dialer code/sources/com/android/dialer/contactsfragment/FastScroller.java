package com.android.dialer.contactsfragment;

import android.content.Context;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.dialer.R;

public class FastScroller extends RelativeLayout {
    private ContactsAdapter adapter;
    private TextView container;
    private boolean dragStarted;
    private LinearLayoutManager layoutManager;
    private View scrollBar;
    private final int touchTargetWidth;

    public FastScroller(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.touchTargetWidth = context.getResources().getDimensionPixelSize(R.dimen.fast_scroller_touch_target_width);
    }

    private int getValueInRange(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    private void setContainerAndScrollBarPosition(float f) {
        int height = this.scrollBar.getHeight();
        int height2 = this.container.getHeight();
        View view = this.scrollBar;
        int height3 = getHeight() - height;
        int i = height / 2;
        view.setY((float) getValueInRange(0, height3, (int) (f - ((float) i))));
        this.container.setY((float) getValueInRange(0, (getHeight() - height2) - i, (int) (f - ((float) height2))));
    }

    public boolean isDragStarted() {
        return this.dragStarted;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.container = (TextView) findViewById(R.id.fast_scroller_container);
        this.scrollBar = findViewById(R.id.fast_scroller_scroll_bar);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f = 0.0f;
        if (!this.dragStarted && ((float) (getWidth() - this.touchTargetWidth)) - motionEvent.getX() > 0.0f) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return super.onTouchEvent(motionEvent);
                    }
                }
            }
            this.dragStarted = false;
            this.container.setVisibility(4);
            this.scrollBar.setSelected(false);
            return true;
        }
        this.dragStarted = true;
        this.container.setVisibility(0);
        this.scrollBar.setSelected(true);
        setContainerAndScrollBarPosition(motionEvent.getY());
        float y = motionEvent.getY();
        int itemCount = this.adapter.getItemCount();
        if (this.scrollBar.getY() != 0.0f) {
            if (this.scrollBar.getY() + ((float) this.scrollBar.getHeight()) >= ((float) getHeight())) {
                f = 1.0f;
            } else {
                f = y / ((float) getHeight());
            }
        }
        int valueInRange = getValueInRange(0, itemCount - 1, (int) (f * ((float) itemCount)));
        this.layoutManager.scrollToPositionWithOffset(valueInRange, 0);
        this.container.setText(this.adapter.getHeaderString(valueInRange));
        this.adapter.refreshHeaders();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setup(ContactsAdapter contactsAdapter, LinearLayoutManager linearLayoutManager) {
        this.adapter = contactsAdapter;
        this.layoutManager = linearLayoutManager;
        setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public void updateContainerAndScrollBarPosition(RecyclerView recyclerView) {
        if (!this.scrollBar.isSelected()) {
            setContainerAndScrollBarPosition(((float) getHeight()) * (((float) recyclerView.computeVerticalScrollOffset()) / (((float) recyclerView.computeVerticalScrollRange()) - ((float) getHeight()))));
        }
    }
}
