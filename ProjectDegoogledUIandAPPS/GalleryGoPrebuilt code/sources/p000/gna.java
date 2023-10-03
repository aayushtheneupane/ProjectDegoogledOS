package p000;

import android.content.SharedPreferences;
import java.util.ArrayList;

/* renamed from: gna */
/* compiled from: PG */
public final class gna {

    /* renamed from: a */
    public final SharedPreferences f11660a;

    /* renamed from: b */
    public final ArrayList f11661b = new ArrayList();

    public gna(SharedPreferences sharedPreferences) {
        this.f11660a = sharedPreferences;
    }

    /* renamed from: c */
    public final boolean mo6857c(gkn gkn, String str) {
        return this.f11660a.getBoolean(m10536b(gkn, str), false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo6855a() {
        return this.f11660a.getInt("count", 0);
    }

    /* renamed from: a */
    public final String mo6856a(gkn gkn, String str) {
        return this.f11660a.getString(m10536b(gkn, str), (String) null);
    }

    /* renamed from: a */
    public static String m10535a(int i, String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(i);
        sb.append(".");
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: b */
    public static String m10536b(gkn gkn, String str) {
        return m10535a(gkn.mo6807a(), str);
    }
}
