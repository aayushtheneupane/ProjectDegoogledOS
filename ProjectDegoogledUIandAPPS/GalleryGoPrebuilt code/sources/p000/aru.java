package p000;

import android.content.ContentResolver;
import android.net.Uri;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: aru */
/* compiled from: PG */
public abstract class aru implements ari {

    /* renamed from: a */
    private final Uri f1499a;

    /* renamed from: b */
    private final ContentResolver f1500b;

    /* renamed from: c */
    private Object f1501c;

    public aru(ContentResolver contentResolver, Uri uri) {
        this.f1500b = contentResolver;
        this.f1499a = uri;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo1511a(Uri uri, ContentResolver contentResolver);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1512a(Object obj);

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        Object obj = this.f1501c;
        if (obj != null) {
            try {
                mo1512a(obj);
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            Object a = mo1511a(this.f1499a, this.f1500b);
            this.f1501c = a;
            arh.mo1525a(a);
        } catch (FileNotFoundException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
