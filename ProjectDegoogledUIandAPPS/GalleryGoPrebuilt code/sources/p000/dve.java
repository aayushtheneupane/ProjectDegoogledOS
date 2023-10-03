package p000;

import java.util.ArrayList;

/* renamed from: dve */
/* compiled from: PG */
final /* synthetic */ class dve implements hpr {

    /* renamed from: a */
    public static final hpr f7449a = new dve();

    private dve() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        ArrayList arrayList = new ArrayList();
        hvs i = ((hso) obj).listIterator();
        while (i.hasNext()) {
            arrayList.add(ede.m7256a((cia) i.next()));
        }
        arrayList.add(ede.m7257a(new ede()));
        return arrayList;
    }
}
