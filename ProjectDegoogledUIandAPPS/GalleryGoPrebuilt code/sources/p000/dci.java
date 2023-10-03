package p000;

import android.content.Context;
import p003j$.util.Optional;

/* renamed from: dci */
/* compiled from: PG */
final /* synthetic */ class dci implements abc {

    /* renamed from: a */
    private final dbo f6253a;

    /* renamed from: b */
    private final dee f6254b;

    /* renamed from: c */
    private final Context f6255c;

    /* renamed from: d */
    private final Optional f6256d;

    public dci(dbo dbo, dee dee, Context context, Optional optional) {
        this.f6253a = dbo;
        this.f6254b = dee;
        this.f6255c = context;
        this.f6256d = optional;
    }

    /* renamed from: a */
    public final Object mo71a(aba aba) {
        dbo dbo = this.f6253a;
        dee dee = this.f6254b;
        Context context = this.f6255c;
        Optional optional = this.f6256d;
        String[] strArr = {dbo.mo3205b()};
        dca dca = new dca(aba);
        dee.mo4083a(context, new String[]{(String) optional.get()}, strArr, dca);
        return dca;
    }
}
