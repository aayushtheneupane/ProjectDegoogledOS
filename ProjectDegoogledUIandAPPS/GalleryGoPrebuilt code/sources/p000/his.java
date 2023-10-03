package p000;

import java.io.IOException;

/* renamed from: his */
/* compiled from: PG */
final /* synthetic */ class his implements hpr {

    /* renamed from: a */
    private final hiz f12825a;

    public his(hiz hiz) {
        this.f12825a = hiz;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hiz hiz = this.f12825a;
        Long l = (Long) obj;
        C0290kn knVar = new C0290kn();
        hjy hjy = hjy.f12891f;
        try {
            ije ije = hiz.mo7490c().f12895c;
            int size = ije.size();
            for (int i = 0; i < size; i++) {
                hjx hjx = (hjx) ije.get(i);
                long j = hjx.f12889d;
                hka hka = hjx.f12887b;
                if (hka == null) {
                    hka = hka.f12906d;
                }
                hjh a = hjh.m11577a(hka);
                if (j <= 0) {
                    j = l.longValue();
                }
                knVar.put(a, Long.valueOf(j));
            }
        } catch (IOException e) {
            hiz.mo7488a((Throwable) e);
        }
        return knVar;
    }
}
