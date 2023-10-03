package p000;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import p003j$.util.Optional;

/* renamed from: djb */
/* compiled from: PG */
final /* synthetic */ class djb implements cvl {

    /* renamed from: a */
    private final djd f6649a;

    /* renamed from: b */
    private final AtomicBoolean f6650b;

    public djb(djd djd, AtomicBoolean atomicBoolean) {
        this.f6649a = djd;
        this.f6650b = atomicBoolean;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        Optional empty;
        int i;
        djd djd = this.f6649a;
        AtomicBoolean atomicBoolean = this.f6650b;
        cyg cyg = (cyg) obj;
        div div = djd.f6656d;
        long b = cyg.mo3908b();
        new Object[1][0] = Long.valueOf(b);
        hvr a = div.f6633a.mo4133a().iterator();
        while (true) {
            if (!a.hasNext()) {
                empty = Optional.empty();
                break;
            }
            String str = (String) a.next();
            try {
                Cursor query = div.f6634b.query(new Uri.Builder().scheme("content").authority(str).appendPath("type").appendPath(String.valueOf(b)).build(), (String[]) null, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            String string = query.getString(query.getColumnIndexOrThrow("special_type_id"));
                            if (!TextUtils.isEmpty(string)) {
                                Object[] objArr = new Object[3];
                                objArr[0] = string;
                                objArr[1] = Long.valueOf(b);
                                i = 2;
                                try {
                                    objArr[2] = str;
                                    empty = Optional.m16285of(string);
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    try {
                                        query.close();
                                        break;
                                    } catch (Throwable th3) {
                                        ifn.m12935a(th2, th3);
                                        break;
                                    }
                                    throw th2;
                                }
                                try {
                                    query.close();
                                    break;
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        i = 2;
                        Throwable th22 = th;
                        query.close();
                        break;
                        throw th22;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                i = 2;
                div.f6635c.mo3869a(38);
                Object[] objArr2 = new Object[i];
                objArr2[0] = Long.valueOf(b);
                objArr2[1] = str;
            }
        }
        empty.ifPresent(new diz(djd, atomicBoolean));
        djh djh = djd.f6654b;
        cyf M = cyg.mo3906M();
        M.mo3984g((String) empty.orElse("_"));
        return djh.f6664a.mo2656a(new djg(M.mo3966c()));
    }
}
