package p000;

import android.database.Cursor;
import android.net.Uri;
import p003j$.util.Optional;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: cex */
/* compiled from: PG */
final /* synthetic */ class cex implements Consumer {

    /* renamed from: a */
    private final cfb f4215a;

    /* renamed from: b */
    private final Uri f4216b;

    /* renamed from: c */
    private final iir f4217c;

    public cex(cfb cfb, iir iir, Uri uri) {
        this.f4215a = cfb;
        this.f4217c = iir;
        this.f4216b = uri;
    }

    public final void accept(Object obj) {
        Optional optional;
        Cursor query;
        cfb cfb = this.f4215a;
        iir iir = this.f4217c;
        Uri uri = this.f4216b;
        String str = (String) obj;
        cxh a = dgt.m6095a(str);
        if (iir.f14319c) {
            iir.mo8751b();
            iir.f14319c = false;
        }
        cxi cxi = (cxi) iir.f14318b;
        cxi cxi2 = cxi.f5908x;
        cxi.f5913e = a.f5906d;
        int i = cxi.f5909a | 8;
        cxi.f5909a = i;
        str.getClass();
        cxi.f5909a = i | 16;
        cxi.f5914f = str;
        if (dgt.m6098c(str)) {
            if (!"content".equals(uri.getScheme())) {
                optional = Optional.empty();
            } else {
                try {
                    query = cfb.f4224b.query(uri, new String[]{"duration"}, (String) null, (String[]) null, (String) null);
                    if (query != null) {
                        if (query.moveToFirst()) {
                            Optional of = Optional.m16285of(Integer.valueOf(query.getInt(0)));
                            query.close();
                            optional = of;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e) {
                    cwn.m5511a((Throwable) e, "ExternalIntentHelper: Failed to get video duration.", new Object[0]);
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
                optional = Optional.empty();
            }
            iir.getClass();
            optional.ifPresent(new cez(iir));
            return;
        }
        return;
        throw th;
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
