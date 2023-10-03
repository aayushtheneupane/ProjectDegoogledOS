package p000;

import android.content.res.AssetManager;
import java.io.IOException;

/* renamed from: arf */
/* compiled from: PG */
public abstract class arf implements ari {

    /* renamed from: a */
    private final String f1479a;

    /* renamed from: b */
    private final AssetManager f1480b;

    /* renamed from: c */
    private Object f1481c;

    public arf(AssetManager assetManager, String str) {
        this.f1480b = assetManager;
        this.f1479a = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo1513a(AssetManager assetManager, String str);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo1515a(Object obj);

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: b */
    public final void mo1516b() {
        Object obj = this.f1481c;
        if (obj != null) {
            try {
                mo1515a(obj);
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            Object a = mo1513a(this.f1480b, this.f1479a);
            this.f1481c = a;
            arh.mo1525a(a);
        } catch (IOException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
