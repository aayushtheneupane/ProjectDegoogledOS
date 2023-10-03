package com.android.settings.localepicker;

import android.content.Context;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.havoc.config.center.C1715R;

public class LocaleLinearLayoutManager extends LinearLayoutManager {
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveBottom = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(C1715R.C1718id.action_drag_move_bottom, this.mContext.getString(C1715R.string.action_drag_label_move_bottom));
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveDown = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(C1715R.C1718id.action_drag_move_down, this.mContext.getString(C1715R.string.action_drag_label_move_down));
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveTop = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(C1715R.C1718id.action_drag_move_top, this.mContext.getString(C1715R.string.action_drag_label_move_top));
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionMoveUp = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(C1715R.C1718id.action_drag_move_up, this.mContext.getString(C1715R.string.action_drag_label_move_up));
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat mActionRemove = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(C1715R.C1718id.action_drag_remove, this.mContext.getString(C1715R.string.action_drag_label_remove));
    private final LocaleDragAndDropAdapter mAdapter;
    private final Context mContext;

    public LocaleLinearLayoutManager(Context context, LocaleDragAndDropAdapter localeDragAndDropAdapter) {
        super(context);
        this.mContext = context;
        this.mAdapter = localeDragAndDropAdapter;
    }

    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfoForItem(recycler, state, view, accessibilityNodeInfoCompat);
        int itemCount = getItemCount();
        int position = getPosition(view);
        StringBuilder sb = new StringBuilder();
        int i = position + 1;
        sb.append(i);
        sb.append(", ");
        sb.append(((LocaleDragCell) view).getCheckbox().getContentDescription());
        accessibilityNodeInfoCompat.setContentDescription(sb.toString());
        if (!this.mAdapter.isRemoveMode()) {
            if (position > 0) {
                accessibilityNodeInfoCompat.addAction(this.mActionMoveUp);
                accessibilityNodeInfoCompat.addAction(this.mActionMoveTop);
            }
            if (i < itemCount) {
                accessibilityNodeInfoCompat.addAction(this.mActionMoveDown);
                accessibilityNodeInfoCompat.addAction(this.mActionMoveBottom);
            }
            if (itemCount > 1) {
                accessibilityNodeInfoCompat.addAction(this.mActionRemove);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        r3 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performAccessibilityActionForItem(androidx.recyclerview.widget.RecyclerView.Recycler r5, androidx.recyclerview.widget.RecyclerView.State r6, android.view.View r7, int r8, android.os.Bundle r9) {
        /*
            r4 = this;
            int r0 = r4.getItemCount()
            int r1 = r4.getPosition(r7)
            r2 = 0
            r3 = 1
            switch(r8) {
                case 2131361854: goto L_0x0036;
                case 2131361855: goto L_0x002c;
                case 2131361856: goto L_0x0024;
                case 2131361857: goto L_0x001a;
                case 2131361858: goto L_0x0012;
                default: goto L_0x000d;
            }
        L_0x000d:
            boolean r4 = super.performAccessibilityActionForItem(r5, r6, r7, r8, r9)
            return r4
        L_0x0012:
            if (r0 <= r3) goto L_0x003f
            com.android.settings.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.removeItem(r1)
            goto L_0x0040
        L_0x001a:
            if (r1 <= 0) goto L_0x003f
            com.android.settings.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            int r6 = r1 + -1
            r5.onItemMove(r1, r6)
            goto L_0x0040
        L_0x0024:
            if (r1 == 0) goto L_0x003f
            com.android.settings.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.onItemMove(r1, r2)
            goto L_0x0040
        L_0x002c:
            int r5 = r1 + 1
            if (r5 >= r0) goto L_0x003f
            com.android.settings.localepicker.LocaleDragAndDropAdapter r6 = r4.mAdapter
            r6.onItemMove(r1, r5)
            goto L_0x0040
        L_0x0036:
            int r0 = r0 - r3
            if (r1 == r0) goto L_0x003f
            com.android.settings.localepicker.LocaleDragAndDropAdapter r5 = r4.mAdapter
            r5.onItemMove(r1, r0)
            goto L_0x0040
        L_0x003f:
            r3 = r2
        L_0x0040:
            if (r3 == 0) goto L_0x0047
            com.android.settings.localepicker.LocaleDragAndDropAdapter r4 = r4.mAdapter
            r4.doTheUpdate()
        L_0x0047:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.localepicker.LocaleLinearLayoutManager.performAccessibilityActionForItem(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, android.view.View, int, android.os.Bundle):boolean");
    }
}
