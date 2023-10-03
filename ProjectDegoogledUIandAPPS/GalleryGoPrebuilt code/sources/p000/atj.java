package p000;

/* renamed from: atj */
/* compiled from: PG */
public final class atj implements atp, avn, atr {

    /* renamed from: a */
    public final atw f1616a;

    /* renamed from: b */
    public final avo f1617b;

    /* renamed from: c */
    public final atg f1618c;

    /* renamed from: d */
    public final aue f1619d;

    /* renamed from: e */
    public final ath f1620e;

    /* renamed from: f */
    public final ate f1621f;

    /* renamed from: g */
    public final ask f1622g;

    public atj(avo avo, ava ava, avz avz, avz avz2, avz avz3, avz avz4) {
        this.f1617b = avo;
        this.f1620e = new ath(ava);
        ask ask = new ask();
        this.f1622g = ask;
        synchronized (this) {
            synchronized (ask) {
            }
        }
        this.f1616a = new atw();
        this.f1618c = new atg(avz, avz2, avz4, this, this);
        this.f1621f = new ate(this.f1620e);
        this.f1619d = new aue();
        ((avm) avo).f1767a = this;
    }

    /* renamed from: a */
    public final synchronized void mo1585a(ato ato, aqu aqu) {
        this.f1616a.mo1623a(aqu, ato);
    }

    /* renamed from: a */
    public final synchronized void mo1586a(ato ato, aqu aqu, ats ats) {
        if (ats != null) {
            if (ats.f1660a) {
                this.f1622g.mo1547a(aqu, ats);
            }
        }
        this.f1616a.mo1623a(aqu, ato);
    }

    /* renamed from: a */
    public final void mo1584a(aqu aqu, ats ats) {
        this.f1622g.mo1546a(aqu);
        if (ats.f1660a) {
            this.f1617b.mo1672a(aqu, ats);
        } else {
            this.f1619d.mo1628a(ats);
        }
    }

    /* renamed from: a */
    public static final void m1620a(aua aua) {
        if (aua instanceof ats) {
            ((ats) aua).mo1609f();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }
}
