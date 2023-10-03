package p000;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: fan */
/* compiled from: PG */
final class fan extends esv {

    /* renamed from: b */
    private final /* synthetic */ ezw f9244b;

    public fan(ezw ezw) {
        this.f9244b = ezw;
    }

    /* renamed from: b */
    public final List mo5222b() {
        hek hek = (hek) this.f9244b;
        ArrayList arrayList = new ArrayList(hek.f12583a.size());
        for (Map.Entry entry : hek.f12583a.entrySet()) {
            gwk gwk = (gwk) entry.getValue();
            if (gwk.mo7132b() == gwi.TEXT) {
                arrayList.add(hek.f12584b.f12592h.mo5446a(gwk.mo7131a().mo8625j(), "text/plain", (String) entry.getKey()));
            }
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList2.add(((fav) arrayList.get(i)).f9248a);
        }
        return arrayList2;
    }

    /* renamed from: a */
    public final List mo5221a() {
        hek hek = (hek) this.f9244b;
        ArrayList arrayList = new ArrayList(hek.f12583a.size());
        for (Map.Entry entry : hek.f12583a.entrySet()) {
            if (((gwk) entry.getValue()).mo7132b() == gwi.KEY_VALUE) {
                arrayList.add(new Pair((String) entry.getKey(), ((gwk) entry.getValue()).mo7131a().toString()));
            }
        }
        return arrayList;
    }
}
