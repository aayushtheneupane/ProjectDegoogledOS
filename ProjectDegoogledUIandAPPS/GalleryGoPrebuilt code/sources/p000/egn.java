package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: egn */
/* compiled from: PG */
final class egn implements gry {

    /* renamed from: a */
    private final /* synthetic */ egp f8208a;

    public egn(egp egp) {
        this.f8208a = egp;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2651a(Object obj, Throwable th) {
        Void voidR = (Void) obj;
        cwn.m5515b(th, "StoragePermissionsMixin: Failed to get storage volumes.", new Object[0]);
        this.f8208a.f8212c.ifPresent(new egm(th));
        this.f8208a.f8212c = Optional.empty();
        this.f8208a.f8213d = Optional.empty();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2649a(Object obj) {
        Void voidR = (Void) obj;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2650a(Object obj, Object obj2) {
        Void voidR = (Void) obj;
        List list = (List) obj2;
        new Object[1][0] = list;
        egp egp = this.f8208a;
        egp.f8213d = Optional.m16285of(list);
        egp.mo4799d();
    }
}
