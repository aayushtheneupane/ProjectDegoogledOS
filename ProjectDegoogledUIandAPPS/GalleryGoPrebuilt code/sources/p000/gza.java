package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: gza */
/* compiled from: PG */
final /* synthetic */ class gza implements Callable {

    /* renamed from: a */
    private final gzn f12338a;

    /* renamed from: b */
    private final gwx f12339b;

    public gza(gzn gzn, gwx gwx) {
        this.f12338a = gzn;
        this.f12339b = gwx;
    }

    public final Object call() {
        int i;
        boolean z;
        Object obj;
        long j;
        boolean z2;
        double d;
        String str;
        ihw ihw;
        ihw ihw2;
        gzn gzn = this.f12338a;
        gwx gwx = this.f12339b;
        gwy a = gwy.m10983a(gwx.f12226b, gwx.f12227c, (gwx.f12225a & 4) != 0 ? gwx.f12228d : null, gwx.f12232h);
        C0290kn knVar = new C0290kn();
        ije ije = gwx.f12229e;
        int size = ije.size();
        for (int i2 = 0; i2 < size; i2++) {
            gxa gxa = (gxa) ije.get(i2);
            gxc gxc = (gxc) gzn.f12356b.get(gxa.f12239d);
            if (gxc != null) {
                int b = gxc.mo7165b();
                switch (gxa.f12237b) {
                    case 0:
                        i = 7;
                        break;
                    case 1:
                        i = 1;
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        i = 2;
                        break;
                    case 3:
                        i = 3;
                        break;
                    case 4:
                        i = 4;
                        break;
                    case 5:
                        i = 5;
                        break;
                    case 6:
                        i = 6;
                        break;
                    default:
                        i = 0;
                        break;
                }
                if (b == i) {
                    z = true;
                } else {
                    z = false;
                }
                ife.m12896d(z);
                int b2 = gxc.mo7165b();
                int i3 = b2 - 1;
                if (b2 != 0) {
                    if (i3 == 0) {
                        if (gxa.f12237b == 1) {
                            j = ((Long) gxa.f12238c).longValue();
                        } else {
                            j = 0;
                        }
                        obj = gxc.m10993a(j);
                    } else if (i3 == 1) {
                        if (gxa.f12237b == 2) {
                            z2 = ((Boolean) gxa.f12238c).booleanValue();
                        } else {
                            z2 = false;
                        }
                        obj = gxc.m10995a(z2);
                    } else if (i3 == 2) {
                        if (gxa.f12237b == 3) {
                            d = ((Double) gxa.f12238c).doubleValue();
                        } else {
                            d = 0.0d;
                        }
                        obj = new gwv(Double.valueOf(d), 3);
                    } else if (i3 == 3) {
                        if (gxa.f12237b == 4) {
                            str = (String) gxa.f12238c;
                        } else {
                            str = "";
                        }
                        obj = gxc.m10994a(str);
                    } else if (i3 == 4) {
                        if (gxa.f12237b == 5) {
                            ihw = (ihw) gxa.f12238c;
                        } else {
                            ihw = ihw.f14203b;
                        }
                        obj = new gwv(ihw, 5);
                    } else if (i3 == 5) {
                        ike n = ((ikf) gxc.mo7164a()).mo8797n();
                        if (gxa.f12237b == 6) {
                            ihw2 = (ihw) gxa.f12238c;
                        } else {
                            ihw2 = ihw.f14203b;
                        }
                        obj = new gwv(n.mo8505a(ihw2).mo8770g(), 6);
                    } else {
                        throw new IllegalStateException("No known flag type");
                    }
                    knVar.put(gxa.f12239d, obj);
                } else {
                    throw null;
                }
            }
        }
        for (String str2 : ((hsu) gzn.f12356b).keySet()) {
            if (!knVar.containsKey(str2)) {
                knVar.put(str2, (gxc) gzn.f12356b.get(str2));
            }
        }
        return gwz.m10988a(a, hsu.m12069a((Map) knVar));
    }
}
