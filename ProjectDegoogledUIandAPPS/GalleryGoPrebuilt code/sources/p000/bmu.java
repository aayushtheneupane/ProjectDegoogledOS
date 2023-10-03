package p000;

import android.text.TextUtils;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;

/* renamed from: bmu */
/* compiled from: PG */
final /* synthetic */ class bmu implements cvl {

    /* renamed from: a */
    private final bmy f3161a;

    /* renamed from: b */
    private final AtomicBoolean f3162b;

    public bmu(bmy bmy, AtomicBoolean atomicBoolean) {
        this.f3161a = bmy;
        this.f3162b = atomicBoolean;
    }

    /* renamed from: a */
    public final ieh mo2590a(Object obj) {
        cyg cyg;
        Integer num;
        bmy bmy = this.f3161a;
        AtomicBoolean atomicBoolean = this.f3162b;
        cyg cyg2 = (cyg) obj;
        bme bme = bmy.f3172f;
        if (cyg2.mo3909c() != 1) {
            cyg = cyg2.mo3906M().mo3954a().mo3966c();
        } else {
            String name = new File(cyg2.mo3923o()).getName();
            hvs i = bme.f3139a.listIterator();
            String str = null;
            boolean z = false;
            while (i.hasNext()) {
                bmd bmd = (bmd) i.next();
                ige ige = bmd.f3137a;
                Matcher matcher = bmd.f3138b.matcher(name);
                if (!(!matcher.matches() || (num = ige.f14076d) == null || matcher.group(num.intValue()) == null)) {
                    str = matcher.group(ige.f14075c.intValue());
                    Integer num2 = ige.f14077e;
                    if (num2 != null) {
                        z = !TextUtils.isEmpty(matcher.group(num2.intValue()));
                    }
                }
            }
            if (z) {
                cyf M = cyg2.mo3906M();
                M.mo3973d(str);
                M.mo3975e(2);
                cyg = M.mo3966c();
            } else {
                cyf M2 = cyg2.mo3906M();
                M2.mo3973d(str);
                cyg = M2.mo3954a().mo3966c();
            }
        }
        ((cwy) cyg).f5868p.ifPresent(new bms(atomicBoolean));
        return bmy.f3167a.mo2586a(cyg);
    }
}
