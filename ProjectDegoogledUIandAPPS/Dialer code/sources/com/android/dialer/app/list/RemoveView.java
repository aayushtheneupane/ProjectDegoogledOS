package com.android.dialer.app.list;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;

public class RemoveView extends FrameLayout {
    DragDropController dragDropController;
    int highlightedColor;
    ImageView removeIcon;
    TextView removeText;
    int unhighlightedColor;

    public RemoveView(Context context) {
        super(context);
    }

    public boolean onDragEvent(DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action == 2) {
            DragDropController dragDropController2 = this.dragDropController;
            if (dragDropController2 != null) {
                dragDropController2.handleDragHovered(this, (int) dragEvent.getX(), (int) dragEvent.getY());
            }
        } else if (action == 3) {
            sendAccessibilityEvent(16384);
            DragDropController dragDropController3 = this.dragDropController;
            if (dragDropController3 != null) {
                dragDropController3.handleDragFinished((int) dragEvent.getX(), (int) dragEvent.getY(), true);
            }
            this.removeText.setTextColor(this.unhighlightedColor);
            this.removeIcon.setColorFilter(this.unhighlightedColor);
            invalidate();
        } else if (action == 5) {
            sendAccessibilityEvent(16384);
            this.removeText.setTextColor(this.highlightedColor);
            this.removeIcon.setColorFilter(this.highlightedColor);
            invalidate();
        } else if (action == 6) {
            this.removeText.setTextColor(this.unhighlightedColor);
            this.removeIcon.setColorFilter(this.unhighlightedColor);
            invalidate();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.removeText = (TextView) findViewById(R.id.remove_view_text);
        this.removeIcon = (ImageView) findViewById(R.id.remove_view_icon);
        Resources resources = getResources();
        this.unhighlightedColor = resources.getColor(17170443);
        this.highlightedColor = resources.getColor(R.color.remove_highlighted_text_color);
        resources.getDrawable(R.drawable.ic_remove);
    }

    public void setDragDropController(DragDropController dragDropController2) {
        this.dragDropController = dragDropController2;
    }

    public RemoveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public RemoveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
