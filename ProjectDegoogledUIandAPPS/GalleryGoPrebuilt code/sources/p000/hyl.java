package p000;

import java.util.Calendar;
import java.util.Date;

/* renamed from: hyl */
/* compiled from: PG */
public final class hyl extends hym {

    /* renamed from: c */
    private final hyk f13653c;

    public hyl(hwv hwv, int i, hyk hyk) {
        super(hwv, i);
        char c;
        this.f13653c = hyk;
        StringBuilder a = hwv.mo8232a(new StringBuilder("%"));
        if (!hwv.mo8235b()) {
            c = 't';
        } else {
            c = 'T';
        }
        a.append(c);
        a.append(hyk.f13652b);
        a.toString();
    }

    /* renamed from: a */
    public final void mo8271a(hyn hyn, Object obj) {
        char c;
        hyk hyk = this.f13653c;
        hwv hwv = this.f13655b;
        if (!(obj instanceof Date) && !(obj instanceof Calendar) && !(obj instanceof Long)) {
            StringBuilder sb = ((hxi) hyn).f13579c;
            char c2 = hyk.f13652b;
            StringBuilder sb2 = new StringBuilder(3);
            sb2.append("%t");
            sb2.append(c2);
            hxi.m12398a(sb, obj, sb2.toString());
            return;
        }
        StringBuilder a = hwv.mo8232a(new StringBuilder("%"));
        if (!hwv.mo8235b()) {
            c = 't';
        } else {
            c = 'T';
        }
        a.append(c);
        a.append(hyk.f13652b);
        String sb3 = a.toString();
        ((hxi) hyn).f13579c.append(String.format(hxi.f13577a, sb3, new Object[]{obj}));
    }
}
