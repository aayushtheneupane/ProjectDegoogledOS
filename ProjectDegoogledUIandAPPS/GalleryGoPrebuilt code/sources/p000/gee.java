package p000;

import android.os.Build;
import android.widget.BaseAdapter;
import java.util.Calendar;

/* renamed from: gee */
/* compiled from: PG */
final class gee extends BaseAdapter {

    /* renamed from: a */
    private final Calendar f11101a;

    /* renamed from: b */
    private final int f11102b;

    /* renamed from: c */
    private final int f11103c = this.f11101a.getFirstDayOfWeek();

    static {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    private final int m10154a(int i) {
        int i2 = i + this.f11103c;
        int i3 = this.f11102b;
        return i2 > i3 ? i2 - i3 : i2;
    }

    public final int getCount() {
        return this.f11102b;
    }

    public final long getItemId(int i) {
        return 0;
    }

    public gee() {
        Calendar e = ggf.m10260e();
        this.f11101a = e;
        this.f11102b = e.getMaximum(7);
    }

    public final /* bridge */ /* synthetic */ Object getItem(int i) {
        if (i < this.f11102b) {
            return Integer.valueOf(m10154a(i));
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r7v8, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L_0x0019
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            r0 = 2131558495(0x7f0d005f, float:1.8742307E38)
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            goto L_0x001a
        L_0x0019:
        L_0x001a:
            java.util.Calendar r7 = r5.f11101a
            int r6 = r5.m10154a(r6)
            r2 = 7
            r7.set(r2, r6)
            java.util.Calendar r6 = r5.f11101a
            r7 = 4
            java.util.Locale r3 = java.util.Locale.getDefault()
            java.lang.String r6 = r6.getDisplayName(r2, r7, r3)
            r0.setText(r6)
            android.content.Context r6 = r8.getContext()
            r7 = 2131886285(0x7f1200cd, float:1.9407145E38)
            java.lang.String r6 = r6.getString(r7)
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.util.Calendar r8 = r5.f11101a
            r3 = 2
            java.util.Locale r4 = java.util.Locale.getDefault()
            java.lang.String r8 = r8.getDisplayName(r2, r3, r4)
            r7[r1] = r8
            java.lang.String r6 = java.lang.String.format(r6, r7)
            r0.setContentDescription(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gee.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
