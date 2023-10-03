package p000;

import android.net.Uri;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import p003j$.util.Optional;

/* renamed from: ces */
/* compiled from: PG */
final /* synthetic */ class ces implements Callable {

    /* renamed from: a */
    private final Uri f4207a;

    /* renamed from: b */
    private final ieh f4208b;

    /* renamed from: c */
    private final ieh f4209c;

    public ces(Uri uri, ieh ieh, ieh ieh2) {
        this.f4207a = uri;
        this.f4208b = ieh;
        this.f4209c = ieh2;
    }

    public final Object call() {
        Uri uri = this.f4207a;
        ieh ieh = this.f4208b;
        ieh ieh2 = this.f4209c;
        try {
            Optional optional = (Optional) ife.m12871b((Future) ieh);
            Optional optional2 = (Optional) ife.m12871b((Future) ieh2);
            if (optional2.isPresent() && optional.isPresent()) {
                return optional.map(new ceu(uri, optional2));
            }
            if (!((Boolean) optional.map(cev.f4213a).orElse(false)).booleanValue()) {
                return optional;
            }
            cwn.m5510a("ExternalIntentHelper: Unknown MediaType for media[%s].", uri);
            return Optional.empty();
        } catch (ExecutionException e) {
            cwn.m5511a((Throwable) e, "ExternalIntentHelper: Failure building media for %s", uri);
            return Optional.empty();
        }
    }
}
