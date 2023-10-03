package com.google.android.apps.photosgo.editor.videotrimming.fragment;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import p003j$.util.Optional;

/* compiled from: PG */
public final class VideoTrimView extends ccj implements hbf {

    /* renamed from: a */
    private cef f4850a;

    @Deprecated
    public VideoTrimView(Context context) {
        super(context);
        m4782c();
    }

    public VideoTrimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoTrimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VideoTrimView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public VideoTrimView(hbl hbl) {
        super(hbl);
        m4782c();
    }

    /* renamed from: c */
    private final void m4782c() {
        if (this.f4850a == null) {
            try {
                this.f4850a = ((ceg) mo2453b()).mo2513k();
                Context context = getContext();
                while ((context instanceof ContextWrapper) && !(context instanceof ioe) && !(context instanceof fts) && !(context instanceof hcf)) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (!(context instanceof hbs)) {
                    String cls = getClass().toString();
                    StringBuilder sb = new StringBuilder(String.valueOf(cls).length() + 57);
                    sb.append("TikTok View ");
                    sb.append(cls);
                    sb.append(", cannot be attached to a non-TikTok Fragment");
                    throw new IllegalStateException(sb.toString());
                }
            } catch (ClassCastException e) {
                throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
            }
        }
    }

    /* renamed from: d */
    private final cef m4783d() {
        m4782c();
        return this.f4850a;
    }

    /* access modifiers changed from: protected */
    public final void onFinishInflate() {
        super.onFinishInflate();
        m4782c();
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        m4783d();
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        cef d = m4783d();
        d.f4171f.layout(d.f4168c.getLeft() - d.f4175j, d.f4168c.getTop(), d.f4168c.getLeft() + d.f4175j, d.f4168c.getBottom());
        d.f4172g.layout(d.f4168c.getRight() - d.f4175j, d.f4168c.getTop(), d.f4168c.getRight() + d.f4175j, d.f4168c.getBottom());
        d.f4170e.layout(d.f4168c.getLeft(), d.f4168c.getTop(), d.f4168c.getLeft() + d.f4176k, d.f4168c.getBottom());
        if (d.f4179n.isPresent()) {
            float a = d.mo3061a(((Integer) d.f4179n.get()).intValue());
            if (d.mo3072b()) {
                d.mo3074c(a);
            } else {
                d.mo3069b(a);
            }
            d.mo3065a();
            d.f4179n = Optional.empty();
        }
        if (d.f4180o.isPresent()) {
            float a2 = d.mo3061a(((Integer) d.f4180o.get()).intValue());
            if (d.mo3072b()) {
                d.mo3069b(a2);
            } else {
                d.mo3074c(a2);
            }
            d.mo3065a();
            d.f4180o = Optional.empty();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
        if (r1 != 6) goto L_0x00fb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r13) {
        /*
            r12 = this;
            super.onTouchEvent(r13)
            cef r0 = r12.m4783d()
            r0.mo3076e()
            cea r1 = r0.f4174i
            r1.mo3054a()
            int r1 = r13.getActionMasked()
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x003b
            if (r1 == r4) goto L_0x002e
            r5 = 2
            if (r1 == r5) goto L_0x0025
            r5 = 3
            if (r1 == r5) goto L_0x002e
            r5 = 6
            if (r1 == r5) goto L_0x002e
            goto L_0x00fa
        L_0x0025:
            cee r0 = r0.f4181p
            if (r0 == 0) goto L_0x00fa
            boolean r13 = r0.mo3060a(r13)
            return r13
        L_0x002e:
            cee r1 = r0.f4181p
            if (r1 == 0) goto L_0x00fa
            r1.mo3060a(r13)
            r0.f4181p = r2
            r0.mo3068a((boolean) r3)
            return r4
        L_0x003b:
            float r1 = r13.getX()
            float r5 = r13.getY()
            android.graphics.RectF r6 = r0.f4185t
            android.view.View r7 = r0.f4167b
            float r7 = r7.getX()
            android.view.View r8 = r0.f4167b
            float r8 = r8.getY()
            android.view.View r9 = r0.f4167b
            float r9 = r9.getX()
            android.view.View r10 = r0.f4167b
            int r10 = r10.getWidth()
            float r10 = (float) r10
            float r9 = r9 + r10
            android.view.View r10 = r0.f4167b
            float r10 = r10.getY()
            android.view.View r11 = r0.f4167b
            int r11 = r11.getHeight()
            float r11 = (float) r11
            float r10 = r10 + r11
            r6.set(r7, r8, r9, r10)
            int r1 = (int) r1
            float r1 = (float) r1
            int r5 = (int) r5
            float r5 = (float) r5
            android.graphics.RectF r6 = r0.f4185t
            boolean r6 = r6.contains(r1, r5)
            if (r6 == 0) goto L_0x00e2
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4171f
            android.graphics.RectF r7 = r0.f4182q
            r0.mo3071b((android.view.View) r6, (android.graphics.RectF) r7)
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4172g
            android.graphics.RectF r7 = r0.f4183r
            r0.mo3071b((android.view.View) r6, (android.graphics.RectF) r7)
            android.graphics.RectF r6 = r0.f4182q
            android.graphics.RectF r7 = r0.f4183r
            boolean r6 = android.graphics.RectF.intersects(r6, r7)
            if (r6 == 0) goto L_0x00ba
            android.graphics.RectF r6 = r0.f4184s
            android.graphics.RectF r7 = r0.f4182q
            r6.set(r7)
            android.graphics.RectF r6 = r0.f4184s
            android.graphics.RectF r7 = r0.f4183r
            r6.union(r7)
            android.graphics.RectF r6 = r0.f4184s
            boolean r6 = r6.contains(r1, r5)
            if (r6 == 0) goto L_0x00ba
            android.graphics.RectF r2 = r0.f4184s
            float r2 = r2.centerX()
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x00b7
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r2 = r0.f4171f
            goto L_0x00e4
        L_0x00b7:
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r2 = r0.f4172g
            goto L_0x00e4
        L_0x00ba:
            android.graphics.RectF r6 = r0.f4182q
            boolean r6 = r6.contains(r1, r5)
            if (r6 == 0) goto L_0x00c5
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r2 = r0.f4171f
            goto L_0x00e4
        L_0x00c5:
            android.graphics.RectF r6 = r0.f4183r
            boolean r6 = r6.contains(r1, r5)
            if (r6 != 0) goto L_0x00df
            android.widget.FrameLayout r6 = r0.f4168c
            android.graphics.RectF r7 = r0.f4186u
            r0.mo3067a((android.view.View) r6, (android.graphics.RectF) r7)
            android.graphics.RectF r6 = r0.f4186u
            boolean r1 = r6.contains(r1, r5)
            if (r1 == 0) goto L_0x00e2
            com.google.android.apps.photosgo.editor.videotrimming.fragment.PlayheadView r2 = r0.f4170e
            goto L_0x00e4
        L_0x00df:
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r2 = r0.f4172g
            goto L_0x00e4
        L_0x00e2:
        L_0x00e4:
            if (r2 == 0) goto L_0x00fa
            r2.setVisibility(r3)
            r0.mo3068a((boolean) r4)
            cee r1 = new cee
            r1.<init>(r0, r2)
            r0.f4181p = r1
            cee r0 = r0.f4181p
            boolean r13 = r0.mo3060a(r13)
            return r13
        L_0x00fa:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* renamed from: a */
    public final cef mo2635n() {
        cef cef = this.f4850a;
        if (cef != null) {
            return cef;
        }
        throw new IllegalStateException("peer() called before initialized.");
    }
}
