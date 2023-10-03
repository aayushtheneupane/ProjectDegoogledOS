package p000;

import android.os.Build;
import android.os.LocaleList;
import java.util.Locale;

/* renamed from: jk */
/* compiled from: PG */
public final class C0260jk {

    /* renamed from: a */
    public final C0261jl f15082a;

    static {
        int i = Build.VERSION.SDK_INT;
        m14489a(new LocaleList(new Locale[0]));
    }

    private C0260jk(C0261jl jlVar) {
        this.f15082a = jlVar;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0260jk) && this.f15082a.equals(((C0260jk) obj).f15082a);
    }

    public final int hashCode() {
        return this.f15082a.hashCode();
    }

    public final String toString() {
        return this.f15082a.toString();
    }

    /* renamed from: a */
    public static C0260jk m14489a(LocaleList localeList) {
        return new C0260jk(new C0262jm(localeList));
    }
}
