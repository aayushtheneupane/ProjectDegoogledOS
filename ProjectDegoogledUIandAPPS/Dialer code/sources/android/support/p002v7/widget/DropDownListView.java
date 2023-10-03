package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.widget.ListViewAutoScrollHelper;
import android.support.p002v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.dialer.R;
import java.lang.reflect.Field;

/* renamed from: android.support.v7.widget.DropDownListView */
class DropDownListView extends ListView {
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private Field mIsChildViewEnabled;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    /* access modifiers changed from: private */
    public ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding = 0;
    private int mSelectionLeftPadding = 0;
    private int mSelectionRightPadding = 0;
    private int mSelectionTopPadding = 0;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect = new Rect();

    /* renamed from: android.support.v7.widget.DropDownListView$GateKeeperDrawable */
    private static class GateKeeperDrawable extends DrawableWrapper {
        private boolean mEnabled = true;

        GateKeeperDrawable(Drawable drawable) {
            super(drawable);
        }

        public void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        /* access modifiers changed from: package-private */
        public void setEnabled(boolean z) {
            this.mEnabled = z;
        }

        public void setHotspot(float f, float f2) {
            if (this.mEnabled) {
                super.setHotspot(f, f2);
            }
        }

        public void setHotspotBounds(int i, int i2, int i3, int i4) {
            if (this.mEnabled) {
                super.setHotspotBounds(i, i2, i3, i4);
            }
        }

        public boolean setState(int[] iArr) {
            if (this.mEnabled) {
                return super.setState(iArr);
            }
            return false;
        }

        public boolean setVisible(boolean z, boolean z2) {
            if (this.mEnabled) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.DropDownListView$ResolveHoverRunnable */
    private class ResolveHoverRunnable implements Runnable {
        /* synthetic */ ResolveHoverRunnable(C02041 r2) {
        }

        public void run() {
            ResolveHoverRunnable unused = DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.drawableStateChanged();
        }
    }

    DropDownListView(Context context, boolean z) {
        super(context, (AttributeSet) null, R.attr.dropDownListViewStyle);
        this.mHijackFocus = z;
        setCacheColorHint(0);
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z) {
        GateKeeperDrawable gateKeeperDrawable = this.mSelector;
        if (gateKeeperDrawable != null) {
            gateKeeperDrawable.setEnabled(z);
        }
    }

    private void updateSelectorStateCompat() {
        Drawable selector = getSelector();
        if (selector != null && this.mDrawsInPressedState && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        Drawable selector;
        if (!this.mSelectorRect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.mSelectorRect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (this.mResolveHoverRunnable == null) {
            super.drawableStateChanged();
            GateKeeperDrawable gateKeeperDrawable = this.mSelector;
            if (gateKeeperDrawable != null) {
                gateKeeperDrawable.setEnabled(true);
            }
            updateSelectorStateCompat();
        }
    }

    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    public int lookForSelectablePosition(int i, boolean z) {
        int i2;
        ListAdapter adapter = getAdapter();
        if (adapter != null && !isInTouchMode()) {
            int count = adapter.getCount();
            if (!getAdapter().areAllItemsEnabled()) {
                if (z) {
                    i2 = Math.max(0, i);
                    while (i2 < count && !adapter.isEnabled(i2)) {
                        i2++;
                    }
                } else {
                    int min = Math.min(i, count - 1);
                    while (i2 >= 0 && !adapter.isEnabled(i2)) {
                        min = i2 - 1;
                    }
                }
                if (i2 < 0 || i2 >= count) {
                    return -1;
                }
                return i2;
            } else if (i < 0 || i >= count) {
                return -1;
            } else {
                return i;
            }
        }
        return -1;
    }

    public int measureHeightOfChildrenCompat(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        getListPaddingLeft();
        getListPaddingRight();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int i7 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i8 = i7;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        View view = null;
        while (i9 < count) {
            int itemViewType = adapter.getItemViewType(i9);
            if (itemViewType != i10) {
                view = null;
                i10 = itemViewType;
            }
            view = adapter.getView(i9, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i12 = layoutParams.height;
            if (i12 > 0) {
                i6 = View.MeasureSpec.makeMeasureSpec(i12, 1073741824);
            } else {
                i6 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, i6);
            view.forceLayout();
            if (i9 > 0) {
                i8 += dividerHeight;
            }
            i8 += view.getMeasuredHeight();
            if (i8 >= i4) {
                return (i5 < 0 || i9 <= i5 || i11 <= 0 || i8 == i4) ? i4 : i11;
            }
            if (i5 >= 0 && i9 >= i5) {
                i11 = i8;
            }
            i9++;
        }
        return i8;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0148  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x016e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onForwardedEvent(android.view.MotionEvent r17, int r18) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            int r3 = r17.getActionMasked()
            r4 = 0
            r5 = 1
            if (r3 == r5) goto L_0x001c
            r0 = 2
            if (r3 == r0) goto L_0x001a
            r0 = 3
            if (r3 == r0) goto L_0x0016
            r0 = r5
            r5 = r4
            goto L_0x012f
        L_0x0016:
            r0 = r4
            r5 = r0
            goto L_0x012f
        L_0x001a:
            r0 = r5
            goto L_0x001d
        L_0x001c:
            r0 = r4
        L_0x001d:
            int r6 = r17.findPointerIndex(r18)
            if (r6 >= 0) goto L_0x0024
            goto L_0x0016
        L_0x0024:
            float r7 = r2.getX(r6)
            int r7 = (int) r7
            float r6 = r2.getY(r6)
            int r6 = (int) r6
            int r8 = r1.pointToPosition(r7, r6)
            r9 = -1
            if (r8 != r9) goto L_0x0037
            goto L_0x012f
        L_0x0037:
            int r0 = r16.getFirstVisiblePosition()
            int r0 = r8 - r0
            android.view.View r10 = r1.getChildAt(r0)
            float r7 = (float) r7
            float r6 = (float) r6
            r1.mDrawsInPressedState = r5
            int r0 = android.os.Build.VERSION.SDK_INT
            r1.drawableHotspotChanged(r7, r6)
            boolean r0 = r16.isPressed()
            if (r0 != 0) goto L_0x0053
            r1.setPressed(r5)
        L_0x0053:
            r16.layoutChildren()
            int r0 = r1.mMotionPosition
            if (r0 == r9) goto L_0x0070
            int r11 = r16.getFirstVisiblePosition()
            int r0 = r0 - r11
            android.view.View r0 = r1.getChildAt(r0)
            if (r0 == 0) goto L_0x0070
            if (r0 == r10) goto L_0x0070
            boolean r11 = r0.isPressed()
            if (r11 == 0) goto L_0x0070
            r0.setPressed(r4)
        L_0x0070:
            r1.mMotionPosition = r8
            int r0 = r10.getLeft()
            float r0 = (float) r0
            float r0 = r7 - r0
            int r11 = r10.getTop()
            float r11 = (float) r11
            float r11 = r6 - r11
            int r12 = android.os.Build.VERSION.SDK_INT
            r10.drawableHotspotChanged(r0, r11)
            boolean r0 = r10.isPressed()
            if (r0 != 0) goto L_0x008e
            r10.setPressed(r5)
        L_0x008e:
            android.graphics.drawable.Drawable r11 = r16.getSelector()
            if (r11 == 0) goto L_0x0098
            if (r8 == r9) goto L_0x0098
            r12 = r5
            goto L_0x0099
        L_0x0098:
            r12 = r4
        L_0x0099:
            if (r12 == 0) goto L_0x009e
            r11.setVisible(r4, r4)
        L_0x009e:
            android.graphics.Rect r0 = r1.mSelectorRect
            int r13 = r10.getLeft()
            int r14 = r10.getTop()
            int r15 = r10.getRight()
            int r5 = r10.getBottom()
            r0.set(r13, r14, r15, r5)
            int r5 = r0.left
            int r13 = r1.mSelectionLeftPadding
            int r5 = r5 - r13
            r0.left = r5
            int r5 = r0.top
            int r13 = r1.mSelectionTopPadding
            int r5 = r5 - r13
            r0.top = r5
            int r5 = r0.right
            int r13 = r1.mSelectionRightPadding
            int r5 = r5 + r13
            r0.right = r5
            int r5 = r0.bottom
            int r13 = r1.mSelectionBottomPadding
            int r5 = r5 + r13
            r0.bottom = r5
            java.lang.reflect.Field r0 = r1.mIsChildViewEnabled     // Catch:{ IllegalAccessException -> 0x00ef }
            boolean r0 = r0.getBoolean(r1)     // Catch:{ IllegalAccessException -> 0x00ef }
            boolean r5 = r10.isEnabled()     // Catch:{ IllegalAccessException -> 0x00ef }
            if (r5 == r0) goto L_0x00f3
            java.lang.reflect.Field r5 = r1.mIsChildViewEnabled     // Catch:{ IllegalAccessException -> 0x00ef }
            if (r0 != 0) goto L_0x00e1
            r0 = 1
            goto L_0x00e2
        L_0x00e1:
            r0 = r4
        L_0x00e2:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ IllegalAccessException -> 0x00ef }
            r5.set(r1, r0)     // Catch:{ IllegalAccessException -> 0x00ef }
            if (r8 == r9) goto L_0x00f3
            r16.refreshDrawableState()     // Catch:{ IllegalAccessException -> 0x00ef }
            goto L_0x00f3
        L_0x00ef:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00f3:
            if (r12 == 0) goto L_0x0110
            android.graphics.Rect r0 = r1.mSelectorRect
            float r5 = r0.exactCenterX()
            float r0 = r0.exactCenterY()
            int r12 = r16.getVisibility()
            if (r12 != 0) goto L_0x0107
            r12 = 1
            goto L_0x0108
        L_0x0107:
            r12 = r4
        L_0x0108:
            r11.setVisible(r12, r4)
            int r12 = android.os.Build.VERSION.SDK_INT
            r11.setHotspot(r5, r0)
        L_0x0110:
            android.graphics.drawable.Drawable r0 = r16.getSelector()
            if (r0 == 0) goto L_0x011d
            if (r8 == r9) goto L_0x011d
            int r5 = android.os.Build.VERSION.SDK_INT
            r0.setHotspot(r7, r6)
        L_0x011d:
            r1.setSelectorEnabled(r4)
            r16.refreshDrawableState()
            r5 = 1
            if (r3 != r5) goto L_0x012d
            long r5 = r1.getItemIdAtPosition(r8)
            r1.performItemClick(r10, r8, r5)
        L_0x012d:
            r5 = r4
            r0 = 1
        L_0x012f:
            if (r0 == 0) goto L_0x0133
            if (r5 == 0) goto L_0x0155
        L_0x0133:
            r1.mDrawsInPressedState = r4
            r1.setPressed(r4)
            r16.drawableStateChanged()
            int r3 = r1.mMotionPosition
            int r5 = r16.getFirstVisiblePosition()
            int r3 = r3 - r5
            android.view.View r3 = r1.getChildAt(r3)
            if (r3 == 0) goto L_0x014b
            r3.setPressed(r4)
        L_0x014b:
            android.support.v4.view.ViewPropertyAnimatorCompat r3 = r1.mClickAnimation
            if (r3 == 0) goto L_0x0155
            r3.cancel()
            r3 = 0
            r1.mClickAnimation = r3
        L_0x0155:
            if (r0 == 0) goto L_0x016e
            android.support.v4.widget.ListViewAutoScrollHelper r3 = r1.mScrollHelper
            if (r3 != 0) goto L_0x0162
            android.support.v4.widget.ListViewAutoScrollHelper r3 = new android.support.v4.widget.ListViewAutoScrollHelper
            r3.<init>(r1)
            r1.mScrollHelper = r3
        L_0x0162:
            android.support.v4.widget.ListViewAutoScrollHelper r3 = r1.mScrollHelper
            r4 = 1
            r3.setEnabled(r4)
            android.support.v4.widget.ListViewAutoScrollHelper r3 = r1.mScrollHelper
            r3.onTouch(r1, r2)
            goto L_0x0175
        L_0x016e:
            android.support.v4.widget.ListViewAutoScrollHelper r1 = r1.mScrollHelper
            if (r1 == 0) goto L_0x0175
            r1.setEnabled(r4)
        L_0x0175:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.DropDownListView.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i = Build.VERSION.SDK_INT;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.mResolveHoverRunnable == null) {
            this.mResolveHoverRunnable = new ResolveHoverRunnable((C02041) null);
            ResolveHoverRunnable resolveHoverRunnable = this.mResolveHoverRunnable;
            DropDownListView.this.post(resolveHoverRunnable);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                updateSelectorStateCompat();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mMotionPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        ResolveHoverRunnable resolveHoverRunnable = this.mResolveHoverRunnable;
        if (resolveHoverRunnable != null) {
            ResolveHoverRunnable unused = DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.removeCallbacks(resolveHoverRunnable);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: package-private */
    public void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    public void setSelector(Drawable drawable) {
        this.mSelector = drawable != null ? new GateKeeperDrawable(drawable) : null;
        super.setSelector(this.mSelector);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.mSelectionLeftPadding = rect.left;
        this.mSelectionTopPadding = rect.top;
        this.mSelectionRightPadding = rect.right;
        this.mSelectionBottomPadding = rect.bottom;
    }
}
