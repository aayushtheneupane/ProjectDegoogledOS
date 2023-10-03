package p000;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;

/* renamed from: bv */
/* compiled from: PG */
public final class C0051bv {

    /* renamed from: a */
    public final Class f3653a;

    /* renamed from: b */
    public final String f3654b;

    /* renamed from: c */
    public final Context f3655c;

    /* renamed from: d */
    public ArrayList f3656d;

    /* renamed from: e */
    public Executor f3657e;

    /* renamed from: f */
    public Executor f3658f;

    /* renamed from: g */
    public C0033bd f3659g;

    /* renamed from: h */
    public boolean f3660h;

    /* renamed from: i */
    public boolean f3661i = true;

    /* renamed from: j */
    public boolean f3662j;

    /* renamed from: k */
    public final C0052bw f3663k = new C0052bw();

    /* renamed from: l */
    private Set f3664l;

    public C0051bv(Context context, Class cls, String str) {
        this.f3655c = context;
        this.f3653a = cls;
        this.f3654b = str;
    }

    /* renamed from: a */
    public final void mo2786a(C0062cf... cfVarArr) {
        if (this.f3664l == null) {
            this.f3664l = new HashSet();
        }
        C0062cf cfVar = cfVarArr[0];
        this.f3664l.add(Integer.valueOf(cfVar.f4220a));
        this.f3664l.add(Integer.valueOf(cfVar.f4221b));
        C0052bw bwVar = this.f3663k;
        C0062cf cfVar2 = cfVarArr[0];
        int i = cfVar2.f4220a;
        int i2 = cfVar2.f4221b;
        HashMap hashMap = bwVar.f3752a;
        Integer valueOf = Integer.valueOf(i);
        TreeMap treeMap = (TreeMap) hashMap.get(valueOf);
        if (treeMap == null) {
            treeMap = new TreeMap();
            bwVar.f3752a.put(valueOf, treeMap);
        }
        Integer valueOf2 = Integer.valueOf(i2);
        C0062cf cfVar3 = (C0062cf) treeMap.get(valueOf2);
        if (cfVar3 != null) {
            Log.w("ROOM", "Overriding migration " + cfVar3 + " with " + cfVar2);
        }
        treeMap.put(valueOf2, cfVar2);
    }
}
