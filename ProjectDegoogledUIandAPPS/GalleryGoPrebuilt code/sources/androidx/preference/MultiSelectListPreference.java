package androidx.preference;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
public class MultiSelectListPreference extends DialogPreference {

    /* renamed from: g */
    public CharSequence[] f1085g;

    /* renamed from: h */
    public CharSequence[] f1086h;

    /* renamed from: i */
    public Set f1087i = new HashSet();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MultiSelectListPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130968839(0x7f040107, float:1.7546343E38)
            r1 = 16842897(0x1010091, float:2.3693964E-38)
            int r0 = p000.C0071co.m4652a((android.content.Context) r4, (int) r0, (int) r1)
            r3.<init>(r4, r5, r0)
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            r3.f1087i = r1
            int[] r1 = p000.adz.f255f
            r2 = 0
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r1, r0, r2)
            r5 = 2
            java.lang.CharSequence[] r5 = p000.C0071co.m4674c(r4, r5, r2)
            r3.f1085g = r5
            r5 = 3
            r0 = 1
            java.lang.CharSequence[] r5 = p000.C0071co.m4674c(r4, r5, r0)
            r3.f1086h = r5
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.MultiSelectListPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo1158a(TypedArray typedArray, int i) {
        CharSequence[] textArray = typedArray.getTextArray(i);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1159a(Parcelable parcelable) {
        if (!parcelable.getClass().equals(acs.class)) {
            super.mo1159a(parcelable);
            return;
        }
        acs acs = (acs) parcelable;
        super.mo1159a(acs.getSuperState());
        mo1168a(acs.f190a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final Parcelable mo1163d() {
        Parcelable d = super.mo1163d();
        if (this.f1123v) {
            return d;
        }
        acs acs = new acs(d);
        acs.f190a = this.f1087i;
        return acs;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1160a(Object obj) {
        mo1168a(mo1175b((Set) obj));
    }

    /* renamed from: a */
    public final void mo1168a(Set set) {
        this.f1087i.clear();
        this.f1087i.addAll(set);
        if (mo1191j() && !set.equals(mo1175b((Set) null))) {
            SharedPreferences.Editor c = this.f1112k.mo231c();
            c.putStringSet(this.f1119r, set);
            Preference.m1014a(c);
        }
        mo1157b();
    }
}
