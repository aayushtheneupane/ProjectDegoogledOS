package p000;

import android.content.Context;
import android.content.Intent;
import androidx.work.impl.foreground.SystemForegroundService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: akh */
/* compiled from: PG */
public final class akh implements ajk, ahw {

    /* renamed from: a */
    public static final String f675a = iol.m14236b("SystemFgDispatcher");

    /* renamed from: b */
    public final aip f676b;

    /* renamed from: c */
    public final amz f677c;

    /* renamed from: d */
    public final Object f678d = new Object();

    /* renamed from: e */
    public final Map f679e;

    /* renamed from: f */
    public final Set f680f;

    /* renamed from: g */
    public final ajl f681g;

    /* renamed from: h */
    public akg f682h;

    /* renamed from: i */
    private final Context f683i;

    /* renamed from: a */
    public final void mo531a(List list) {
    }

    public akh(Context context) {
        this.f683i = context;
        aip a = aip.m549a(this.f683i);
        this.f676b = a;
        this.f677c = a.f555d;
        this.f680f = new HashSet();
        this.f679e = new HashMap();
        this.f681g = new ajl(this.f683i, this.f677c, this);
        this.f676b.f557f.mo506a(this);
    }

    /* renamed from: a */
    public static Intent m654a(Context context) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }

    /* renamed from: b */
    public final void mo532b(List list) {
        if (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String str = (String) list.get(i);
                iol.m14231a();
                String.format("Constraints unmet for WorkSpec %s", new Object[]{str});
                aip aip = this.f676b;
                aip.f555d.mo668a(new amh(aip, str, true));
            }
        }
    }

    /* renamed from: a */
    public final void mo573a() {
        this.f682h = null;
        this.f681g.mo551a();
        this.f676b.f557f.mo508b(this);
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        boolean z2;
        synchronized (this.f678d) {
            alg alg = (alg) this.f679e.remove(str);
            if (alg != null) {
                z2 = this.f680f.remove(alg);
            } else {
                z2 = false;
            }
        }
        if (z2) {
            this.f681g.mo552a((Iterable) this.f680f);
        }
    }
}
