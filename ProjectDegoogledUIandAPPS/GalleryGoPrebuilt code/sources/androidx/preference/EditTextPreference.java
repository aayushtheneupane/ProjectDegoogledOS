package androidx.preference;

import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;

/* compiled from: PG */
public class EditTextPreference extends DialogPreference {

    /* renamed from: g */
    public String f1079g;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EditTextPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130968869(0x7f040125, float:1.7546404E38)
            r1 = 16842898(0x1010092, float:2.3693967E-38)
            int r0 = p000.C0071co.m4652a((android.content.Context) r4, (int) r0, (int) r1)
            r3.<init>(r4, r5, r0)
            int[] r1 = p000.adz.f253d
            r2 = 0
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r1, r0, r2)
            boolean r5 = p000.C0071co.m4666a((android.content.res.TypedArray) r4, (int) r2, (int) r2, (boolean) r2)
            if (r5 == 0) goto L_0x002a
            acj r5 = p000.acj.f180a
            if (r5 != 0) goto L_0x0025
            acj r5 = new acj
            r5.<init>()
            p000.acj.f180a = r5
        L_0x0025:
            acj r5 = p000.acj.f180a
            r3.mo1170a((p000.adc) r5)
        L_0x002a:
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.EditTextPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo1158a(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(aci.class)) {
            super.mo1159a(parcelable);
            return;
        }
        aci aci = (aci) parcelable;
        super.mo1159a(aci.getSuperState());
        mo1161a(aci.f179a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        Parcelable d = super.mo1163d();
        if (this.f1123v) {
            return d;
        }
        aci aci = new aci(d);
        aci.f179a = this.f1079g;
        return aci;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1160a(Object obj) {
        mo1161a(mo1185d((String) obj));
    }

    /* renamed from: a */
    public final void mo1161a(String str) {
        boolean c = mo1162c();
        this.f1079g = str;
        mo1188e(str);
        boolean c2 = mo1162c();
        if (c2 != c) {
            mo1174a(c2);
        }
        mo1157b();
    }

    /* renamed from: c */
    public final boolean mo1162c() {
        return TextUtils.isEmpty(this.f1079g) || super.mo1162c();
    }
}
