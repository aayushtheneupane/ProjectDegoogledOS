package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import p003j$.util.function.Consumer;

/* renamed from: ebf */
/* compiled from: PG */
public final class ebf extends C0652xy {

    /* renamed from: a */
    public final Set f7839a = new C0292kp();

    /* renamed from: b */
    public Future f7840b = ife.m12820a((Object) null);

    /* renamed from: c */
    public ebd f7841c = ebd.STOPPED;

    /* renamed from: d */
    private final ScheduledExecutorService f7842d;

    /* renamed from: e */
    private ebd f7843e = ebd.STOPPED;

    /* renamed from: f */
    private int f7844f = 0;

    public ebf(iel iel) {
        this.f7842d = iel;
    }

    /* renamed from: a */
    public final void mo4639a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.f7841c != ebd.STOPPED) {
            this.f7840b.cancel(true);
            mo4655a(ebd.STOPPED);
        }
    }

    /* renamed from: a */
    public final void mo4654a(RecyclerView recyclerView, int i, int i2) {
        int abs = Math.abs(i2);
        ebd[] ebdArr = ebd.f7835d;
        int length = ebdArr.length;
        int i3 = 0;
        while (i3 < length) {
            ebd ebd = ebdArr[i3];
            if (abs > ebd.f7837e) {
                i3++;
            } else {
                if (ebd == this.f7843e) {
                    int i4 = this.f7844f + 1;
                    this.f7844f = i4;
                    if (i4 >= 5 && ebd != this.f7841c) {
                        mo4655a(ebd);
                    }
                } else {
                    this.f7844f = 1;
                    this.f7843e = ebd;
                }
                this.f7840b.cancel(true);
                this.f7840b = this.f7842d.schedule(new ebe(this), 50, TimeUnit.MILLISECONDS);
                return;
            }
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append("Failed to obtain a speed for ");
        sb.append(abs);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    public final void mo4655a(ebd ebd) {
        this.f7841c = ebd;
        for (Consumer accept : this.f7839a) {
            accept.accept(ebd);
        }
        new Object[1][0] = ebd;
    }
}
