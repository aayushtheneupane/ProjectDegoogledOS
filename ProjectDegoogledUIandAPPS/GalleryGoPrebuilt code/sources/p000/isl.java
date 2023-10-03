package p000;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

/* renamed from: isl */
/* compiled from: PG */
public final class isl implements Runnable {

    /* renamed from: a */
    public final isw f15011a;

    /* renamed from: b */
    public int f15012b;

    /* renamed from: c */
    public int f15013c;

    /* renamed from: d */
    public final /* synthetic */ isn f15014d;

    public isl(isn isn, Context context) {
        this.f15014d = isn;
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
        this.f15011a = new isv(context);
    }

    public final void run() {
        ImageView c;
        if (!((isu) this.f15011a).f15051a.isFinished() && (c = this.f15014d.mo9095c()) != null && ((isv) this.f15011a).f15051a.computeScrollOffset()) {
            int currX = ((isu) this.f15011a).f15051a.getCurrX();
            int currY = ((isu) this.f15011a).f15051a.getCurrY();
            this.f15014d.f15023i.postTranslate((float) (this.f15012b - currX), (float) (this.f15013c - currY));
            isn isn = this.f15014d;
            isn.mo9091a(isn.mo9098f());
            this.f15012b = currX;
            this.f15013c = currY;
            isw.m14418a(c, this);
        }
    }
}
