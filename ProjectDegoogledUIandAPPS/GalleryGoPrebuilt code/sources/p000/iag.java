package p000;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* renamed from: iag */
/* compiled from: PG */
public abstract class iag {
    private iag() {
    }

    /* renamed from: a */
    public abstract boolean mo8319a();

    /* renamed from: b */
    public abstract Throwable mo8320b();

    public /* synthetic */ iag(byte[] bArr) {
    }

    /* renamed from: a */
    public static iag m12574a(Future future) {
        Throwable th;
        try {
            return iae.m12569a(future.get());
        } catch (CancellationException e) {
            return iac.m12563a(e);
        } catch (ExecutionException e2) {
            th = e2.getCause();
            return iad.m12566a(th);
        } catch (Throwable th2) {
            th = th2;
            return iad.m12566a(th);
        }
    }

    /* renamed from: a */
    public static iag m12575a(Future future, long j, TimeUnit timeUnit) {
        Throwable th;
        try {
            return iae.m12569a(future.get(j, timeUnit));
        } catch (CancellationException e) {
            return iac.m12563a(e);
        } catch (ExecutionException e2) {
            th = e2.getCause();
            return iad.m12566a(th);
        } catch (Throwable th2) {
            th = th2;
            return iad.m12566a(th);
        }
    }

    /* renamed from: c */
    public final iae mo8329c() {
        if (!mo8319a()) {
            return null;
        }
        return (iae) this;
    }

    /* renamed from: a */
    public static ieh m12576a(ieh ieh) {
        return new iaf((ieh) ife.m12898e((Object) ieh));
    }
}
