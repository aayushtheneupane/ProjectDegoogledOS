package p000;

import java.util.HashMap;
import java.util.Map;

/* renamed from: avg */
/* compiled from: PG */
final class avg {

    /* renamed from: a */
    public final Map f1756a = new HashMap();

    /* renamed from: b */
    public final avf f1757b = new avf();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1669a(String str) {
        ave ave;
        synchronized (this) {
            ave = (ave) cns.m4632a((Object) (ave) this.f1756a.get(str));
            int i = ave.f1754b;
            if (i > 0) {
                int i2 = i - 1;
                ave.f1754b = i2;
                if (i2 == 0) {
                    ave ave2 = (ave) this.f1756a.remove(str);
                    if (ave2.equals(ave)) {
                        avf avf = this.f1757b;
                        synchronized (avf.f1755a) {
                            if (avf.f1755a.size() < 10) {
                                avf.f1755a.offer(ave2);
                            }
                        }
                    } else {
                        String valueOf = String.valueOf(ave);
                        String valueOf2 = String.valueOf(ave2);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 79 + String.valueOf(valueOf2).length() + str.length());
                        sb.append("Removed the wrong lock, expected to remove: ");
                        sb.append(valueOf);
                        sb.append(", but actually removed: ");
                        sb.append(valueOf2);
                        sb.append(", safeKey: ");
                        sb.append(str);
                        throw new IllegalStateException(sb.toString());
                    }
                }
            } else {
                StringBuilder sb2 = new StringBuilder(str.length() + 81);
                sb2.append("Cannot release a lock that is not held, safeKey: ");
                sb2.append(str);
                sb2.append(", interestedThreads: ");
                sb2.append(i);
                throw new IllegalStateException(sb2.toString());
            }
        }
        ave.f1753a.unlock();
    }
}
