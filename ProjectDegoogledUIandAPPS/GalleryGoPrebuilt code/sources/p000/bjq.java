package p000;

import p003j$.util.Optional;

/* renamed from: bjq */
/* compiled from: PG */
final class bjq implements iqk {

    /* renamed from: a */
    private final int f2644a;

    /* renamed from: b */
    private final /* synthetic */ bkl f2645b;

    public bjq(bkl bkl, int i) {
        this.f2645b = bkl;
        this.f2644a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        if (this.f2644a != 0) {
            return this.f2645b.mo2425c();
        }
        bkp bkp = this.f2645b.f3032b.f3006c;
        hsq a = hsu.m12065a(8);
        cup cup = cup.IMAGE_COMPRESSION_JOB;
        iqk iqk = bkp.f3062c;
        if (iqk == null) {
            iqk = new bjr(bkp, 1);
            bkp.f3062c = iqk;
        }
        a.mo7932a(cup, boy.m3316a(iog.m14218a(iqk)));
        a.mo7932a(cup.FACE_DETECTION_JOB, bkp.f3063d.mo2191aM());
        a.mo7932a(cup.FACE_EMBEDDING_JOB, bkp.f3063d.mo2195aQ());
        a.mo7932a(cup.FACE_CLUSTERING_JOB, bkp.f3063d.mo2197aS());
        a.mo7932a(cup.FACE_THUMBNAILING_JOB, bkp.f3063d.mo2200aV());
        a.mo7932a(cup.FACE_CLUSTERING_WIPEOUT_JOB, bkp.f3063d.mo2203aY());
        a.mo7932a(cup.IMAGE_LABELING_JOB, bkp.f3063d.mo2274bq());
        a.mo7932a(cup.THUMBNAIL_JOB, bkp.f3063d.mo2275br());
        return Optional.m16285of(cwe.m5497a(a.mo7930a(), bkp.f3063d.mo2247bP(), bkp.f3063d.mo2221aq(), bkp.f3063d.mo2314cn(), bkp.f3063d.mo2214aj(), bkp.f3063d.mo2198aT(), bkp.f3063d.mo2201aW(), iog.m14218a(bkp.f3063d.mo2239bH()), iog.m14218a(bkp.f3063d.mo2240bI()), iog.m14218a(bkp.f3063d.mo2188aJ())));
    }
}
