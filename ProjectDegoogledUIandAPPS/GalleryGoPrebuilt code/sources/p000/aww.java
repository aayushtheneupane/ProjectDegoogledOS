package p000;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: aww */
/* compiled from: PG */
final class aww implements ari {

    /* renamed from: a */
    private final File f1803a;

    /* renamed from: b */
    private final awx f1804b;

    /* renamed from: c */
    private Object f1805c;

    public aww(File file, awx awx) {
        this.f1803a = file;
        this.f1804b = awx;
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
        Object obj = this.f1805c;
        if (obj != null) {
            try {
                this.f1804b.mo1703a(obj);
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return this.f1804b.mo1701a();
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            Object a = this.f1804b.mo1702a(this.f1803a);
            this.f1805c = a;
            arh.mo1525a(a);
        } catch (FileNotFoundException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
