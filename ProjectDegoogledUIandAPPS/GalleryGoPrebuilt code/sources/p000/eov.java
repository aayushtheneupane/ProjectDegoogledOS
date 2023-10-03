package p000;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: eov */
/* compiled from: PG */
public class eov {
    public eov() {
    }

    @Deprecated
    /* renamed from: a */
    public ekm mo4862a(Context context, Looper looper, epk epk, Object obj, ekt ekt, eku eku) {
        throw null;
    }

    /* renamed from: a */
    public static ekx m7948a(Status status) {
        abj.m86a((Object) status, (Object) "Result must not be null");
        eol eol = new eol(Looper.getMainLooper());
        eol.mo3507a((ela) status);
        return eol;
    }

    /* renamed from: a */
    public static void m7949a(Status status, exe exe) {
        m7950a(status, (Object) null, exe);
    }

    /* renamed from: a */
    public static void m7950a(Status status, Object obj, exe exe) {
        if (!status.mo3499b()) {
            exe.mo5364a((Exception) new eko(status));
        } else {
            exe.mo5365a(obj);
        }
    }

    public eov(String[] strArr) {
        String[] strArr2 = (String[]) abj.m85a((Object) strArr);
        new ArrayList();
        new HashMap();
    }
}
