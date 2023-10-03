package p000;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.apps.photosgo.R;
import java.lang.reflect.Field;

/* renamed from: vi */
/* compiled from: PG */
public class C0582vi extends ListView {

    /* renamed from: a */
    public boolean f16091a;

    /* renamed from: b */
    public C0581vh f16092b;

    /* renamed from: c */
    private final Rect f16093c = new Rect();

    /* renamed from: d */
    private int f16094d = 0;

    /* renamed from: e */
    private int f16095e = 0;

    /* renamed from: f */
    private int f16096f = 0;

    /* renamed from: g */
    private int f16097g = 0;

    /* renamed from: h */
    private int f16098h;

    /* renamed from: i */
    private Field f16099i;

    /* renamed from: j */
    private C0580vg f16100j;

    /* renamed from: k */
    private final boolean f16101k;

    /* renamed from: l */
    private boolean f16102l;

    /* renamed from: m */
    private C0369nl f16103m;

    public C0582vi(Context context, boolean z) {
        super(context, (AttributeSet) null, R.attr.dropDownListViewStyle);
        this.f16101k = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f16099i = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            ifn.m12932a(e);
        }
    }

    /* access modifiers changed from: protected */
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        if (!this.f16093c.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(this.f16093c);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        if (this.f16092b == null) {
            super.drawableStateChanged();
            m15608a(true);
            m15607a();
        }
    }

    public final boolean hasFocus() {
        return this.f16101k || super.hasFocus();
    }

    public final boolean hasWindowFocus() {
        return this.f16101k || super.hasWindowFocus();
    }

    public final boolean isFocused() {
        return this.f16101k || super.isFocused();
    }

    public final boolean isInTouchMode() {
        return (this.f16101k && this.f16091a) || super.isInTouchMode();
    }

    /* renamed from: a */
    public final int mo10376a(int i, int i2) {
        int i3;
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
        int i4 = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        View view = null;
        int i5 = 0;
        int i6 = 0;
        while (i5 < count) {
            int itemViewType = adapter.getItemViewType(i5);
            int i7 = itemViewType == i6 ? i6 : itemViewType;
            if (itemViewType != i6) {
                view = null;
            }
            view = adapter.getView(i5, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            if (layoutParams.height > 0) {
                i3 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            } else {
                i3 = View.MeasureSpec.makeMeasureSpec(0, 0);
            }
            view.measure(i, i3);
            view.forceLayout();
            if (i5 > 0) {
                i4 += dividerHeight;
            }
            i4 += view.getMeasuredHeight();
            if (i4 >= i2) {
                return i2;
            }
            i5++;
            i6 = i7;
        }
        return i4;
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        this.f16092b = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x013c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0165  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo10377a(android.view.MotionEvent r17, int r18) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            int r3 = r17.getActionMasked()
            r4 = 1
            r5 = 0
            if (r3 == r4) goto L_0x001e
            r0 = 2
            if (r3 == r0) goto L_0x001a
            r0 = 3
            if (r3 == r0) goto L_0x0016
        L_0x0012:
            r0 = 1
        L_0x0013:
            r13 = 0
            goto L_0x013a
        L_0x0016:
            r0 = 0
            r13 = 0
            goto L_0x013a
        L_0x001a:
            r0 = 1
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            int r6 = r17.findPointerIndex(r18)
            if (r6 < 0) goto L_0x0137
            float r7 = r2.getX(r6)
            int r7 = (int) r7
            float r6 = r2.getY(r6)
            int r6 = (int) r6
            int r8 = r1.pointToPosition(r7, r6)
            r9 = -1
            if (r8 == r9) goto L_0x0135
            int r0 = r16.getFirstVisiblePosition()
            int r0 = r8 - r0
            android.view.View r10 = r1.getChildAt(r0)
            float r7 = (float) r7
            float r6 = (float) r6
            r1.f16102l = r4
            int r0 = android.os.Build.VERSION.SDK_INT
            r1.drawableHotspotChanged(r7, r6)
            boolean r0 = r16.isPressed()
            if (r0 != 0) goto L_0x0052
            r1.setPressed(r4)
        L_0x0052:
            r16.layoutChildren()
            int r0 = r1.f16098h
            if (r0 == r9) goto L_0x0071
            int r11 = r16.getFirstVisiblePosition()
            int r0 = r0 - r11
            android.view.View r0 = r1.getChildAt(r0)
            if (r0 == 0) goto L_0x0071
            if (r0 == r10) goto L_0x0071
            boolean r11 = r0.isPressed()
            if (r11 != 0) goto L_0x006d
            goto L_0x0071
        L_0x006d:
            r0.setPressed(r5)
        L_0x0071:
            r1.f16098h = r8
            int r0 = r10.getLeft()
            int r11 = r10.getTop()
            int r12 = android.os.Build.VERSION.SDK_INT
            float r0 = (float) r0
            float r0 = r7 - r0
            float r11 = (float) r11
            float r11 = r6 - r11
            r10.drawableHotspotChanged(r0, r11)
            boolean r0 = r10.isPressed()
            if (r0 != 0) goto L_0x008f
            r10.setPressed(r4)
        L_0x008f:
            android.graphics.drawable.Drawable r11 = r16.getSelector()
            if (r11 != 0) goto L_0x0097
            r12 = 0
            goto L_0x009d
        L_0x0097:
            if (r8 != r9) goto L_0x009c
            r12 = 0
            goto L_0x009d
        L_0x009c:
            r12 = 1
        L_0x009d:
            if (r12 != 0) goto L_0x00a0
            goto L_0x00a4
        L_0x00a0:
            r11.setVisible(r5, r5)
        L_0x00a4:
            android.graphics.Rect r0 = r1.f16093c
            int r13 = r10.getLeft()
            int r14 = r10.getTop()
            int r15 = r10.getRight()
            int r5 = r10.getBottom()
            r0.set(r13, r14, r15, r5)
            int r5 = r0.left
            int r13 = r1.f16094d
            int r5 = r5 - r13
            r0.left = r5
            int r5 = r0.top
            int r13 = r1.f16095e
            int r5 = r5 - r13
            r0.top = r5
            int r5 = r0.right
            int r13 = r1.f16096f
            int r5 = r5 + r13
            r0.right = r5
            int r5 = r0.bottom
            int r13 = r1.f16097g
            int r5 = r5 + r13
            r0.bottom = r5
            java.lang.reflect.Field r0 = r1.f16099i     // Catch:{ IllegalAccessException -> 0x00f1 }
            boolean r0 = r0.getBoolean(r1)     // Catch:{ IllegalAccessException -> 0x00f1 }
            boolean r5 = r10.isEnabled()     // Catch:{ IllegalAccessException -> 0x00f1 }
            if (r5 == r0) goto L_0x00f5
            java.lang.reflect.Field r5 = r1.f16099i     // Catch:{ IllegalAccessException -> 0x00f1 }
            r0 = r0 ^ r4
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ IllegalAccessException -> 0x00f1 }
            r5.set(r1, r0)     // Catch:{ IllegalAccessException -> 0x00f1 }
            if (r8 == r9) goto L_0x00f5
            r16.refreshDrawableState()     // Catch:{ IllegalAccessException -> 0x00f1 }
            goto L_0x00f5
        L_0x00f1:
            r0 = move-exception
            p000.ifn.m12932a(r0)
        L_0x00f5:
            if (r12 == 0) goto L_0x0112
            android.graphics.Rect r0 = r1.f16093c
            float r5 = r0.exactCenterX()
            float r0 = r0.exactCenterY()
            int r12 = r16.getVisibility()
            if (r12 != 0) goto L_0x0109
            r12 = 1
            goto L_0x010b
        L_0x0109:
            r12 = 0
        L_0x010b:
            r13 = 0
            r11.setVisible(r12, r13)
            p000.C0257jh.m14472a((android.graphics.drawable.Drawable) r11, (float) r5, (float) r0)
        L_0x0112:
            android.graphics.drawable.Drawable r0 = r16.getSelector()
            if (r0 == 0) goto L_0x011d
            if (r8 == r9) goto L_0x011d
            p000.C0257jh.m14472a((android.graphics.drawable.Drawable) r0, (float) r7, (float) r6)
        L_0x011d:
            r5 = 0
            r1.m15608a(r5)
            r16.refreshDrawableState()
            if (r3 != r4) goto L_0x0132
            long r5 = r1.getItemIdAtPosition(r8)
            r1.performItemClick(r10, r8, r5)
            r0 = 1
            r13 = 0
            goto L_0x013a
        L_0x0132:
            goto L_0x0012
        L_0x0135:
            r13 = 1
            goto L_0x013a
        L_0x0137:
            r0 = 0
            goto L_0x0013
        L_0x013a:
            if (r0 == 0) goto L_0x0141
            if (r13 == 0) goto L_0x013f
            goto L_0x0141
        L_0x013f:
            r3 = 0
            goto L_0x015b
        L_0x0141:
            r3 = 0
            r1.f16102l = r3
            r1.setPressed(r3)
            r16.drawableStateChanged()
            int r5 = r1.f16098h
            int r6 = r16.getFirstVisiblePosition()
            int r5 = r5 - r6
            android.view.View r5 = r1.getChildAt(r5)
            if (r5 == 0) goto L_0x015b
            r5.setPressed(r3)
        L_0x015b:
            if (r0 != 0) goto L_0x0165
            nl r2 = r1.f16103m
            if (r2 == 0) goto L_0x017b
            r2.mo9458a(r3)
            goto L_0x017b
        L_0x0165:
            nl r3 = r1.f16103m
            if (r3 == 0) goto L_0x016a
            goto L_0x0171
        L_0x016a:
            nl r3 = new nl
            r3.<init>(r1)
            r1.f16103m = r3
        L_0x0171:
            nl r3 = r1.f16103m
            r3.mo9458a(r4)
            nl r3 = r1.f16103m
            r3.onTouch(r1, r2)
        L_0x017b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0582vi.mo10377a(android.view.MotionEvent, int):boolean");
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i = Build.VERSION.SDK_INT;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f16092b == null) {
            C0581vh vhVar = new C0581vh(this);
            this.f16092b = vhVar;
            vhVar.f16090a.post(vhVar);
        }
        boolean onHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (!(pointToPosition == -1 || pointToPosition == getSelectedItemPosition())) {
                View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(pointToPosition, childAt.getTop() - getTop());
                }
                m15607a();
            }
        } else {
            setSelection(-1);
        }
        return onHoverEvent;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f16098h = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        C0581vh vhVar = this.f16092b;
        if (vhVar != null) {
            C0582vi viVar = vhVar.f16090a;
            viVar.f16092b = null;
            viVar.removeCallbacks(vhVar);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setSelector(Drawable drawable) {
        C0580vg vgVar;
        if (drawable != null) {
            vgVar = new C0580vg(drawable);
        } else {
            vgVar = null;
        }
        this.f16100j = vgVar;
        super.setSelector(vgVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f16094d = rect.left;
        this.f16095e = rect.top;
        this.f16096f = rect.right;
        this.f16097g = rect.bottom;
    }

    /* renamed from: a */
    private final void m15608a(boolean z) {
        C0580vg vgVar = this.f16100j;
        if (vgVar != null) {
            vgVar.f16089b = z;
        }
    }

    /* renamed from: a */
    private final void m15607a() {
        Drawable selector = getSelector();
        if (selector != null && this.f16102l && isPressed()) {
            selector.setState(getDrawableState());
        }
    }
}
