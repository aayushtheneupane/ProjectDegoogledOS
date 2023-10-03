package p000;

import java.util.concurrent.Executor;

/* renamed from: aba */
/* compiled from: PG */
public final class aba {

    /* renamed from: a */
    public Object f69a;

    /* renamed from: b */
    public abe f70b;

    /* renamed from: c */
    public abf f71c = new abf();

    /* renamed from: d */
    private boolean f72d;

    /* renamed from: a */
    public final void mo67a(Runnable runnable, Executor executor) {
        abf abf = this.f71c;
        if (abf != null) {
            abf.mo53a(runnable, executor);
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() {
        abf abf;
        abe abe = this.f70b;
        if (abe != null && !abe.isDone()) {
            abe.mo72a(new abb("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f69a));
        }
        if (!this.f72d && (abf = this.f71c) != null) {
            abf.mo54a((Object) null);
        }
    }

    /* renamed from: a */
    public final void mo66a(Object obj) {
        this.f72d = true;
        abe abe = this.f70b;
        if (abe != null && abe.f75b.mo54a(obj)) {
            m71a();
        }
    }

    /* renamed from: a */
    private final void m71a() {
        this.f69a = null;
        this.f70b = null;
        this.f71c = null;
    }

    /* renamed from: a */
    public final void mo68a(Throwable th) {
        this.f72d = true;
        abe abe = this.f70b;
        if (abe != null && abe.mo72a(th)) {
            m71a();
        }
    }
}
