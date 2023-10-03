package p000;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com */
/* compiled from: PG */
final class C0072com implements ari {

    /* renamed from: a */
    private final ebh f4777a;

    /* renamed from: b */
    private final Context f4778b;

    /* renamed from: c */
    private InputStream f4779c;

    public C0072com(Context context, ebh ebh) {
        this.f4778b = context;
        this.f4777a = ebh;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return InputStream.class;
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        InputStream inputStream = this.f4779c;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            InputStream a = fra.m9440a(this.f4778b, this.f4777a.mo4656a());
            this.f4779c = a;
            arh.mo1525a((Object) a);
        } catch (IOException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
