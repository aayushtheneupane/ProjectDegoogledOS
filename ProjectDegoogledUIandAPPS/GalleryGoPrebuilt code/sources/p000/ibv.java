package p000;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* renamed from: ibv */
/* compiled from: PG */
public abstract class ibv extends idp implements Runnable {

    /* renamed from: a */
    private ieh f13856a;

    /* renamed from: f */
    private Object f13857f;

    public ibv(ieh ieh, Object obj) {
        this.f13856a = (ieh) ife.m12898e((Object) ieh);
        this.f13857f = ife.m12898e(obj);
    }

    /* renamed from: a */
    public abstract Object mo8359a(Object obj, Object obj2);

    /* renamed from: a */
    public abstract void mo8334a(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        mo8345a((Future) this.f13856a);
        this.f13856a = null;
        this.f13857f = null;
    }

    /* renamed from: a */
    public static ieh m12657a(ieh ieh, hpr hpr, Executor executor) {
        ife.m12898e((Object) hpr);
        ibu ibu = new ibu(ieh, hpr);
        ieh.mo53a(ibu, ife.m12838a(executor, (ibr) ibu));
        return ibu;
    }

    /* renamed from: a */
    public static ieh m12658a(ieh ieh, icf icf, Executor executor) {
        ife.m12898e((Object) executor);
        ibt ibt = new ibt(ieh, icf);
        ieh.mo53a(ibt, ife.m12838a(executor, (ibr) ibt));
        return ibt;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        String str;
        ieh ieh = this.f13856a;
        Object obj = this.f13857f;
        String a = super.mo6386a();
        if (ieh != null) {
            String valueOf = String.valueOf(ieh);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (obj != null) {
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(valueOf2).length());
            sb2.append(str);
            sb2.append("function=[");
            sb2.append(valueOf2);
            sb2.append("]");
            return sb2.toString();
        } else if (a == null) {
            return null;
        } else {
            String valueOf3 = String.valueOf(str);
            return a.length() == 0 ? new String(valueOf3) : valueOf3.concat(a);
        }
    }

    public final void run() {
        ieh ieh = this.f13856a;
        Object obj = this.f13857f;
        boolean z = true;
        boolean isCancelled = isCancelled() | (ieh == null);
        if (obj != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.f13856a = null;
            if (ieh.isCancelled()) {
                mo6946a(ieh);
                return;
            }
            try {
                try {
                    Object a = mo8359a(obj, ife.m12871b((Future) ieh));
                    this.f13857f = null;
                    mo8334a(a);
                } catch (Throwable th) {
                    this.f13857f = null;
                    throw th;
                }
            } catch (CancellationException e) {
                cancel(false);
            } catch (ExecutionException e2) {
                mo6952a(e2.getCause());
            } catch (RuntimeException e3) {
                mo6952a((Throwable) e3);
            } catch (Error e4) {
                mo6952a((Throwable) e4);
            }
        }
    }
}
