package p000;

import android.content.Context;
import android.content.IntentFilter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dih */
/* compiled from: PG */
public final class dih implements dil {

    /* renamed from: a */
    public final Map f6595a = Collections.synchronizedMap(new HashMap());

    /* renamed from: b */
    private final dio f6596b;

    public dih(Context context, dio dio) {
        this.f6596b = dio;
        context.registerReceiver(new dig(this), new IntentFilter("android.intent.action.LOCALE_CHANGED"));
    }

    /* renamed from: a */
    public final ieh mo4154a(String str) {
        if (this.f6595a.containsKey(str)) {
            return ife.m12820a((Object) (Optional) this.f6595a.get(str));
        }
        return gte.m10770a(this.f6596b.mo4154a(str), (hpr) new dif(this, str), (Executor) idh.f13918a);
    }
}
