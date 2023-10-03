package p000;

import android.os.Build;
import android.widget.CompoundButton;

/* renamed from: th */
/* compiled from: PG */
final class C0527th {

    /* renamed from: a */
    private final CompoundButton f15926a;

    /* renamed from: b */
    private boolean f15927b;

    public C0527th(CompoundButton compoundButton) {
        this.f15926a = compoundButton;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004a A[Catch:{ all -> 0x006f }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005a A[Catch:{ all -> 0x006f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo10130a(android.util.AttributeSet r4, int r5) {
        /*
            r3 = this;
            android.widget.CompoundButton r0 = r3.f15926a
            android.content.Context r0 = r0.getContext()
            int[] r1 = p000.C0435px.f15584l
            r2 = 0
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r1, r5, r2)
            r5 = 1
            boolean r0 = r4.hasValue(r5)     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x0029
            int r5 = r4.getResourceId(r5, r2)     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x0029
            android.widget.CompoundButton r0 = r3.f15926a     // Catch:{ NotFoundException -> 0x0028 }
            android.content.Context r1 = r0.getContext()     // Catch:{ NotFoundException -> 0x0028 }
            android.graphics.drawable.Drawable r5 = p000.C0436py.m15105b(r1, r5)     // Catch:{ NotFoundException -> 0x0028 }
            r0.setButtonDrawable(r5)     // Catch:{ NotFoundException -> 0x0028 }
            goto L_0x0043
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            boolean r5 = r4.hasValue(r2)     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x0043
            int r5 = r4.getResourceId(r2, r2)     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x0043
            android.widget.CompoundButton r0 = r3.f15926a     // Catch:{ all -> 0x006f }
            android.content.Context r1 = r0.getContext()     // Catch:{ all -> 0x006f }
            android.graphics.drawable.Drawable r5 = p000.C0436py.m15105b(r1, r5)     // Catch:{ all -> 0x006f }
            r0.setButtonDrawable(r5)     // Catch:{ all -> 0x006f }
        L_0x0043:
            r5 = 2
            boolean r0 = r4.hasValue(r5)     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x0053
            android.widget.CompoundButton r0 = r3.f15926a     // Catch:{ all -> 0x006f }
            android.content.res.ColorStateList r5 = r4.getColorStateList(r5)     // Catch:{ all -> 0x006f }
            p000.cya.m5634a(r0, r5)     // Catch:{ all -> 0x006f }
        L_0x0053:
            r5 = 3
            boolean r0 = r4.hasValue(r5)     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x006b
            android.widget.CompoundButton r0 = r3.f15926a     // Catch:{ all -> 0x006f }
            r1 = -1
            int r5 = r4.getInt(r5, r1)     // Catch:{ all -> 0x006f }
            r1 = 0
            android.graphics.PorterDuff$Mode r5 = p000.C0579vf.m15603a(r5, r1)     // Catch:{ all -> 0x006f }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x006f }
            r0.setButtonTintMode(r5)     // Catch:{ all -> 0x006f }
        L_0x006b:
            r4.recycle()
            return
        L_0x006f:
            r5 = move-exception
            r4.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0527th.mo10130a(android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10129a() {
        if (!this.f15927b) {
            this.f15927b = true;
            CompoundButton compoundButton = this.f15926a;
            int i = Build.VERSION.SDK_INT;
            compoundButton.getButtonDrawable();
            return;
        }
        this.f15927b = false;
    }
}
