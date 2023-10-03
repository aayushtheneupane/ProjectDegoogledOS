package p000;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: cuu */
/* compiled from: PG */
final /* synthetic */ class cuu implements icf {

    /* renamed from: a */
    private final cva f5696a;

    public cuu(cva cva) {
        this.f5696a = cva;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        cva cva = this.f5696a;
        hqk hqk = (hqk) obj;
        ArrayList arrayList = new ArrayList();
        for (cup cup : cup.values()) {
            cut cut = (cut) cva.f5706a.get(cup);
            if (cut != null) {
                arrayList.add(cut);
            }
        }
        Callable[] callableArr = {new cuv(cva, hqk), new cuw(cva), new cux(cva)};
        cue[] cueArr = new cue[3];
        for (int i = 0; i < 3; i++) {
            cueArr[i] = cun.m5442a(callableArr[i]);
        }
        return fxk.m9820a(gte.m10770a(cun.m5446a((Iterable) arrayList, (cue) new cud(cun.m5444a(cueArr)), (Executor) cva.f5707b), cuy.f5701a, (Executor) cva.f5707b), (Closeable) new cuz(cva), (Executor) cva.f5707b);
    }
}
