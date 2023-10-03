package p000;

import java.util.HashMap;

@Deprecated
/* renamed from: fbr */
/* compiled from: PG */
public class fbr implements fbk {

    /* renamed from: a */
    public final eul f9253a;

    public fbr(eul eul) {
        this.f9253a = eul;
        new HashMap();
    }

    /* renamed from: c */
    public byte[] mo5463c() {
        throw null;
    }

    /* renamed from: d */
    public final fbj[] mo5464d() {
        euj[] eujArr = this.f9253a.f9048d;
        if (eujArr == null) {
            return null;
        }
        fbj[] fbjArr = new fbj[eujArr.length];
        for (int i = 0; i < eujArr.length; i++) {
            fbjArr[i] = new fbv(eujArr[i]);
        }
        return fbjArr;
    }

    /* renamed from: a */
    public final String mo5461a() {
        return this.f9253a.f9047c;
    }

    /* renamed from: b */
    public final String mo5462b() {
        return this.f9253a.f9045a;
    }
}
