package p000;

import android.content.Context;
import android.media.MediaExtractor;
import android.util.SparseIntArray;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: dgo */
/* compiled from: PG */
public final class dgo implements ccd {

    /* renamed from: a */
    public final Context f6508a;

    /* renamed from: b */
    public final ehb f6509b;

    /* renamed from: c */
    public final dbs f6510c;

    /* renamed from: d */
    public final iek f6511d;

    /* renamed from: e */
    public final AtomicBoolean f6512e = new AtomicBoolean(false);

    public dgo(Context context, ehb ehb, dbs dbs, iek iek) {
        this.f6508a = context;
        this.f6509b = ehb;
        this.f6510c = dbs;
        this.f6511d = iek;
    }

    /* renamed from: a */
    public static boolean m6089a(MediaExtractor mediaExtractor) {
        return (mediaExtractor.getSampleFlags() & 1) != 0;
    }

    /* renamed from: a */
    public final void mo4121a(MediaExtractor mediaExtractor, SparseIntArray sparseIntArray) {
        int trackCount = mediaExtractor.getTrackCount();
        new Object[1][0] = Integer.valueOf(trackCount);
        int i = 0;
        while (i < trackCount) {
            if (this.f6512e.compareAndSet(true, false)) {
                throw cca.m4026a(new InterruptedException());
            } else if (!Thread.interrupted()) {
                String string = mediaExtractor.getTrackFormat(i).getString("mime");
                if (string == null || (!string.startsWith("audio/") && !string.startsWith("video/"))) {
                    new Object[1][0] = string;
                } else {
                    mediaExtractor.selectTrack(i);
                    sparseIntArray.append(i, 0);
                }
                i++;
            } else {
                throw new InterruptedException();
            }
        }
    }

    /* renamed from: a */
    public final ieh mo3023a(ccc ccc) {
        this.f6512e.set(false);
        return gte.m10771a(this.f6511d.mo5933a(hmq.m11749a((Callable) new dgh(ccc))), (icf) new dgi(this), (Executor) this.f6511d);
    }
}
