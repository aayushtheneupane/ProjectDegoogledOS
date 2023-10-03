package p000;

import android.view.View;
import java.util.Collections;

/* renamed from: ckx */
/* compiled from: PG */
public final /* synthetic */ class ckx {
    public ckx() {
        Collections.emptyList();
        Collections.emptyList();
    }

    /* renamed from: b */
    public static void m4483b(String str) {
        if (str.length() == 0) {
            throw new ang("Empty array name", 4);
        }
    }

    /* renamed from: a */
    public static void m4482a(Object obj) {
        if (obj == null) {
            throw new ang("Parameter must not be null", 4);
        } else if ((obj instanceof String) && ((String) obj).length() == 0) {
            throw new ang("Parameter must not be null or empty", 4);
        }
    }

    /* renamed from: c */
    public static void m4484c(String str) {
        if (str == null || str.length() == 0) {
            throw new ang("Empty property name", 4);
        }
    }

    /* renamed from: d */
    public static void m4485d(String str) {
        if (str == null || str.length() == 0) {
            throw new ang("Empty schema namespace URI", 4);
        }
    }

    /* renamed from: a */
    public static String m4481a(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() == 0 ? new String("/storage/") : "/storage/".concat(valueOf);
    }

    /* renamed from: a */
    public static ckz m4480a(View view) {
        return new cky(view);
    }
}
