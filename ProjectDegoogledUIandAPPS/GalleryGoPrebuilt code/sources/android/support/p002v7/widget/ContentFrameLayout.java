package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

/* renamed from: android.support.v7.widget.ContentFrameLayout */
/* compiled from: PG */
public class ContentFrameLayout extends FrameLayout {

    /* renamed from: a */
    public TypedValue f949a;

    /* renamed from: b */
    public TypedValue f950b;

    /* renamed from: c */
    public TypedValue f951c;

    /* renamed from: d */
    public TypedValue f952d;

    /* renamed from: e */
    public TypedValue f953e;

    /* renamed from: f */
    public TypedValue f954f;

    /* renamed from: g */
    public final Rect f955g;

    /* renamed from: h */
    public C0564ur f956h;

    public ContentFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f955g = new Rect();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        C0472rg rgVar;
        super.onDetachedFromWindow();
        C0564ur urVar = this.f956h;
        if (urVar != null) {
            C0416pe peVar = ((C0400op) urVar).f15420a;
            C0565us usVar = peVar.f15488g;
            if (usVar != null) {
                usVar.mo828h();
            }
            if (peVar.f15491j != null) {
                peVar.f15486e.getDecorView().removeCallbacks(peVar.f15492k);
                if (peVar.f15491j.isShowing()) {
                    try {
                        peVar.f15491j.dismiss();
                    } catch (IllegalArgumentException e) {
                    }
                }
                peVar.f15491j = null;
            }
            peVar.mo9616s();
            C0414pc g = peVar.mo9610g(0);
            if (g != null && (rgVar = g.f15445h) != null) {
                rgVar.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r17, int r18) {
        /*
            r16 = this;
            r0 = r16
            android.content.Context r1 = r16.getContext()
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            int r2 = r1.widthPixels
            int r3 = r1.heightPixels
            int r4 = android.view.View.MeasureSpec.getMode(r17)
            int r5 = android.view.View.MeasureSpec.getMode(r18)
            r6 = 1
            r7 = 6
            r8 = 5
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = 1073741824(0x40000000, float:2.0)
            r11 = 0
            if (r4 == r9) goto L_0x0028
        L_0x0024:
            r12 = r17
            r13 = 0
            goto L_0x006d
        L_0x0028:
            if (r2 < r3) goto L_0x002d
            android.util.TypedValue r12 = r0.f951c
            goto L_0x002f
        L_0x002d:
            android.util.TypedValue r12 = r0.f952d
        L_0x002f:
            if (r12 == 0) goto L_0x0024
            int r13 = r12.type
            if (r13 == 0) goto L_0x006c
            int r13 = r12.type
            if (r13 != r8) goto L_0x003f
            float r12 = r12.getDimension(r1)
            int r12 = (int) r12
            goto L_0x0050
        L_0x003f:
            int r13 = r12.type
            if (r13 == r7) goto L_0x0045
            r12 = 0
            goto L_0x0050
        L_0x0045:
            int r13 = r1.widthPixels
            float r13 = (float) r13
            int r14 = r1.widthPixels
            float r14 = (float) r14
            float r12 = r12.getFraction(r13, r14)
            int r12 = (int) r12
        L_0x0050:
            if (r12 <= 0) goto L_0x006b
            android.graphics.Rect r13 = r0.f955g
            int r13 = r13.left
            android.graphics.Rect r14 = r0.f955g
            int r14 = r14.right
            int r13 = r13 + r14
            int r12 = r12 - r13
            int r13 = android.view.View.MeasureSpec.getSize(r17)
            int r12 = java.lang.Math.min(r12, r13)
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r10)
            r13 = 1
            goto L_0x006d
        L_0x006b:
            goto L_0x0024
        L_0x006c:
            goto L_0x0024
        L_0x006d:
            if (r5 == r9) goto L_0x0070
            goto L_0x00b1
        L_0x0070:
            if (r2 < r3) goto L_0x0075
            android.util.TypedValue r5 = r0.f954f
            goto L_0x0077
        L_0x0075:
            android.util.TypedValue r5 = r0.f953e
        L_0x0077:
            if (r5 == 0) goto L_0x00b1
            int r14 = r5.type
            if (r14 == 0) goto L_0x00b1
            int r14 = r5.type
            if (r14 != r8) goto L_0x0087
            float r5 = r5.getDimension(r1)
            int r5 = (int) r5
            goto L_0x0098
        L_0x0087:
            int r14 = r5.type
            if (r14 == r7) goto L_0x008d
            r5 = 0
            goto L_0x0098
        L_0x008d:
            int r14 = r1.heightPixels
            float r14 = (float) r14
            int r15 = r1.heightPixels
            float r15 = (float) r15
            float r5 = r5.getFraction(r14, r15)
            int r5 = (int) r5
        L_0x0098:
            if (r5 <= 0) goto L_0x00b1
            android.graphics.Rect r14 = r0.f955g
            int r14 = r14.top
            android.graphics.Rect r15 = r0.f955g
            int r15 = r15.bottom
            int r14 = r14 + r15
            int r5 = r5 - r14
            int r14 = android.view.View.MeasureSpec.getSize(r18)
            int r5 = java.lang.Math.min(r5, r14)
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r10)
            goto L_0x00b3
        L_0x00b1:
            r5 = r18
        L_0x00b3:
            super.onMeasure(r12, r5)
            int r12 = r16.getMeasuredWidth()
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r10)
            if (r13 == 0) goto L_0x00c2
        L_0x00c0:
            r6 = 0
            goto L_0x0100
        L_0x00c2:
            if (r4 != r9) goto L_0x00c0
            if (r2 < r3) goto L_0x00c9
            android.util.TypedValue r2 = r0.f949a
            goto L_0x00cb
        L_0x00c9:
            android.util.TypedValue r2 = r0.f950b
        L_0x00cb:
            if (r2 == 0) goto L_0x00c0
            int r3 = r2.type
            if (r3 == 0) goto L_0x00c0
            int r3 = r2.type
            if (r3 != r8) goto L_0x00db
            float r1 = r2.getDimension(r1)
            int r1 = (int) r1
            goto L_0x00ec
        L_0x00db:
            int r3 = r2.type
            if (r3 == r7) goto L_0x00e1
            r1 = 0
            goto L_0x00ec
        L_0x00e1:
            int r3 = r1.widthPixels
            float r3 = (float) r3
            int r1 = r1.widthPixels
            float r1 = (float) r1
            float r1 = r2.getFraction(r3, r1)
            int r1 = (int) r1
        L_0x00ec:
            if (r1 <= 0) goto L_0x00f8
            android.graphics.Rect r2 = r0.f955g
            int r2 = r2.left
            android.graphics.Rect r3 = r0.f955g
            int r3 = r3.right
            int r2 = r2 + r3
            int r1 = r1 - r2
        L_0x00f8:
            if (r12 >= r1) goto L_0x00c0
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r10)
        L_0x0100:
            if (r6 == 0) goto L_0x0105
            super.onMeasure(r14, r5)
        L_0x0105:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.ContentFrameLayout.onMeasure(int, int):void");
    }
}
