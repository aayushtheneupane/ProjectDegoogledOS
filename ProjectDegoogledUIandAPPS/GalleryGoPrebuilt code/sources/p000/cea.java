package p000;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: cea */
/* compiled from: PG */
public final class cea extends View.AccessibilityDelegate {

    /* renamed from: a */
    private View f4150a = null;

    /* renamed from: b */
    private int f4151b;

    /* renamed from: c */
    private float f4152c;

    /* renamed from: d */
    private final /* synthetic */ cef f4153d;

    public /* synthetic */ cea(cef cef) {
        this.f4153d = cef;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setItemCount(100);
        accessibilityEvent.setCurrentItemIndex(Math.round(this.f4153d.mo3062a(view) * 100.0f));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0120, code lost:
        if (r0.mo3072b() == false) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0131, code lost:
        if (r0.mo3072b() == false) goto L_0x0125;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onInitializeAccessibilityNodeInfo(android.view.View r8, android.view.accessibility.AccessibilityNodeInfo r9) {
        /*
            r7 = this;
            super.onInitializeAccessibilityNodeInfo(r8, r9)
            java.lang.Class<android.widget.SeekBar> r0 = android.widget.SeekBar.class
            java.lang.String r0 = r0.getName()
            r9.setClassName(r0)
            cef r0 = r7.f4153d
            android.widget.FrameLayout r1 = r0.f4168c
            int r1 = r1.getWidth()
            float r1 = (float) r1
            cec r2 = new cec
            r3 = 0
            r2.<init>(r3)
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4171f
            r4 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
            if (r8 != r3) goto L_0x0051
            boolean r3 = r0.mo3072b()
            if (r3 == 0) goto L_0x003c
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4172g
            float r3 = r3.getX()
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4171f
            int r6 = r6.getWidth()
            float r6 = (float) r6
            float r3 = r3 - r6
            float r3 = r3 / r1
            r2.f4155a = r3
            r2.f4157c = r5
            goto L_0x009d
        L_0x003c:
            r2.f4155a = r4
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4172g
            float r3 = r3.getX()
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4171f
            int r6 = r6.getWidth()
            float r6 = (float) r6
            float r3 = r3 - r6
            float r3 = r3 / r1
            r2.f4157c = r3
            goto L_0x009d
        L_0x0051:
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4172g
            if (r8 != r6) goto L_0x0083
            boolean r3 = r0.mo3072b()
            if (r3 == 0) goto L_0x006f
            r2.f4155a = r4
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4171f
            float r3 = r3.getX()
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4171f
            int r6 = r6.getWidth()
            float r6 = (float) r6
            float r3 = r3 + r6
            float r3 = r3 / r1
            r2.f4157c = r3
            goto L_0x009d
        L_0x006f:
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4171f
            float r3 = r3.getX()
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r6 = r0.f4171f
            int r6 = r6.getWidth()
            float r6 = (float) r6
            float r3 = r3 + r6
            float r3 = r3 / r1
            r2.f4155a = r3
            r2.f4157c = r5
            goto L_0x009d
        L_0x0083:
            float r3 = r3.getX()
            float r3 = r3 / r1
            int r3 = java.lang.Math.round(r3)
            float r3 = (float) r3
            r2.f4155a = r3
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r3 = r0.f4172g
            float r3 = r3.getX()
            float r3 = r3 / r1
            int r1 = java.lang.Math.round(r3)
            float r1 = (float) r1
            r2.f4157c = r1
        L_0x009d:
            float r1 = r0.mo3062a((android.view.View) r8)
            r2.f4156b = r1
            boolean r0 = r0.mo3072b()
            if (r0 == 0) goto L_0x00af
            float r0 = r2.f4156b
            float r0 = r5 - r0
            r2.f4156b = r0
        L_0x00af:
            float r0 = r2.f4155a
            float r0 = java.lang.Math.max(r0, r4)
            r2.f4155a = r0
            float r0 = r2.f4156b
            float r0 = p000.ife.m12802a((float) r0, (float) r5)
            r2.f4156b = r0
            float r0 = r2.f4155a
            float r0 = java.lang.Math.min(r0, r5)
            r2.f4157c = r0
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            float r3 = r2.f4155a
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            r0[r1] = r3
            r1 = 1
            float r3 = r2.f4156b
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            r0[r1] = r3
            float r1 = r2.f4157c
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r3 = 2
            r0[r3] = r1
            float r0 = r2.f4155a
            r1 = 1120403456(0x42c80000, float:100.0)
            float r0 = r0 * r1
            float r0 = p000.ife.m12802a((float) r0, (float) r1)
            float r4 = r2.f4157c
            float r4 = r4 * r1
            float r4 = p000.ife.m12802a((float) r4, (float) r1)
            float r2 = r2.f4156b
            float r2 = r2 * r1
            float r1 = p000.ife.m12802a((float) r2, (float) r1)
            android.view.accessibility.AccessibilityNodeInfo$RangeInfo r0 = android.view.accessibility.AccessibilityNodeInfo.RangeInfo.obtain(r3, r0, r4, r1)
            r9.setRangeInfo(r0)
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r0 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD
            r9.addAction(r0)
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r0 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD
            r9.addAction(r0)
            cef r0 = r7.f4153d
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r1 = r0.f4171f
            r2 = 2131886370(0x7f120122, float:1.9407317E38)
            r3 = 2131886371(0x7f120123, float:1.9407319E38)
            if (r8 != r1) goto L_0x0129
            boolean r8 = r0.mo3072b()
            if (r8 != 0) goto L_0x0123
            goto L_0x0137
        L_0x0123:
        L_0x0125:
            r2 = 2131886371(0x7f120123, float:1.9407319E38)
            goto L_0x0137
        L_0x0129:
            com.google.android.apps.photosgo.editor.videotrimming.fragment.TrimHandleView r1 = r0.f4172g
            if (r8 != r1) goto L_0x0134
            boolean r8 = r0.mo3072b()
            if (r8 != 0) goto L_0x0137
            goto L_0x0125
        L_0x0134:
            r2 = 2131886372(0x7f120124, float:1.940732E38)
        L_0x0137:
            cef r8 = r7.f4153d
            com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView r8 = r8.f4166a
            android.content.Context r8 = r8.getContext()
            java.lang.String r8 = r8.getString(r2)
            r9.setContentDescription(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cea.onInitializeAccessibilityNodeInfo(android.view.View, android.view.accessibility.AccessibilityNodeInfo):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        if (r6.f4153d.mo3072b() != false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        if (r6.f4153d.mo3072b() != false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        r2 = -r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r6.f4150a == r7) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean performAccessibilityAction(android.view.View r7, int r8, android.os.Bundle r9) {
        /*
            r6 = this;
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)
            r3 = 0
            r1[r3] = r2
            float r1 = r6.f4152c
            r2 = 4096(0x1000, float:5.74E-42)
            if (r8 != r2) goto L_0x0011
            goto L_0x0020
        L_0x0011:
            r2 = 8192(0x2000, float:1.14794E-41)
            if (r8 == r2) goto L_0x0020
            boolean r9 = super.performAccessibilityAction(r7, r8, r9)     // Catch:{ all -> 0x00a7 }
            r6.f4152c = r1
            r6.f4151b = r8
            r6.f4150a = r7
            return r9
        L_0x0020:
            cef r9 = r6.f4153d     // Catch:{ all -> 0x00a7 }
            android.widget.FrameLayout r9 = r9.f4168c     // Catch:{ all -> 0x00a7 }
            int r9 = r9.getWidth()     // Catch:{ all -> 0x00a7 }
            float r9 = (float) r9     // Catch:{ all -> 0x00a7 }
            r2 = 1120403456(0x42c80000, float:100.0)
            float r2 = r9 / r2
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r4 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD     // Catch:{ all -> 0x00a7 }
            int r4 = r4.getId()     // Catch:{ all -> 0x00a7 }
            if (r8 == r4) goto L_0x0036
            goto L_0x003e
        L_0x0036:
            cef r4 = r6.f4153d     // Catch:{ all -> 0x00a7 }
            boolean r4 = r4.mo3072b()     // Catch:{ all -> 0x00a7 }
            if (r4 == 0) goto L_0x004f
        L_0x003e:
            android.view.accessibility.AccessibilityNodeInfo$AccessibilityAction r4 = android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD     // Catch:{ all -> 0x00a7 }
            int r4 = r4.getId()     // Catch:{ all -> 0x00a7 }
            if (r8 == r4) goto L_0x0047
            goto L_0x0050
        L_0x0047:
            cef r4 = r6.f4153d     // Catch:{ all -> 0x00a7 }
            boolean r4 = r4.mo3072b()     // Catch:{ all -> 0x00a7 }
            if (r4 == 0) goto L_0x0050
        L_0x004f:
            float r2 = -r2
        L_0x0050:
            cef r4 = r6.f4153d     // Catch:{ all -> 0x00a7 }
            com.google.android.apps.photosgo.editor.videotrimming.fragment.PlayheadView r4 = r4.f4170e     // Catch:{ all -> 0x00a7 }
            if (r7 != r4) goto L_0x005b
            float r4 = r4.getTranslationX()     // Catch:{ all -> 0x00a7 }
            goto L_0x005f
        L_0x005b:
            float r4 = r7.getX()     // Catch:{ all -> 0x00a7 }
        L_0x005f:
            int r5 = r6.f4151b     // Catch:{ all -> 0x00a7 }
            if (r5 == r8) goto L_0x0064
            goto L_0x0069
        L_0x0064:
            android.view.View r5 = r6.f4150a     // Catch:{ all -> 0x00a7 }
            if (r5 != r7) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r1 = r4
        L_0x006a:
            float r1 = r1 + r2
            cef r2 = r6.f4153d     // Catch:{ all -> 0x00a5 }
            r4 = 0
            r2.mo3066a(r7, r1, r9, r4)     // Catch:{ all -> 0x00a5 }
            cef r2 = r6.f4153d     // Catch:{ all -> 0x00a5 }
            dtl r2 = r2.f4178m     // Catch:{ all -> 0x00a5 }
            if (r2 == 0) goto L_0x009e
            boolean r4 = r2.mo4413h()     // Catch:{ all -> 0x00a5 }
            if (r4 == 0) goto L_0x009e
            boolean r4 = r2.mo4415j()     // Catch:{ all -> 0x00a5 }
            if (r4 != 0) goto L_0x0084
            goto L_0x0087
        L_0x0084:
            r2.mo4416k()     // Catch:{ all -> 0x00a5 }
        L_0x0087:
            cef r4 = r6.f4153d     // Catch:{ all -> 0x00a5 }
            r4.mo3068a((boolean) r0)     // Catch:{ all -> 0x00a5 }
            cef r4 = r6.f4153d     // Catch:{ all -> 0x00a5 }
            float r9 = r4.mo3063a((android.view.View) r7, (float) r9)     // Catch:{ all -> 0x00a5 }
            int r9 = r4.mo3064a((float) r9)     // Catch:{ all -> 0x00a5 }
            r2.mo4403a((int) r9)     // Catch:{ all -> 0x00a5 }
            cef r9 = r6.f4153d     // Catch:{ all -> 0x00a5 }
            r9.mo3068a((boolean) r3)     // Catch:{ all -> 0x00a5 }
        L_0x009e:
            r6.f4152c = r1
            r6.f4151b = r8
            r6.f4150a = r7
            return r0
        L_0x00a5:
            r9 = move-exception
            goto L_0x00a8
        L_0x00a7:
            r9 = move-exception
        L_0x00a8:
            r6.f4152c = r1
            r6.f4151b = r8
            r6.f4150a = r7
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cea.performAccessibilityAction(android.view.View, int, android.os.Bundle):boolean");
    }

    /* renamed from: a */
    public final void mo3054a() {
        this.f4150a = null;
        this.f4151b = 0;
        this.f4152c = 0.0f;
    }
}
