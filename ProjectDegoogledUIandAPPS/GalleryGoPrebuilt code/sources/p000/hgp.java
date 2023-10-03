package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: hgp */
/* compiled from: PG */
final /* synthetic */ class hgp implements Callable {

    /* renamed from: a */
    private final List f12702a;

    public hgp(List list) {
        this.f12702a = list;
    }

    public final Object call() {
        List list = this.f12702a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                ife.m12871b((Future) (ieh) list.get(i));
            } catch (ExecutionException e) {
                ((hvv) ((hvv) ((hvv) hgq.f12703a.mo8178a()).mo8202a(e.getCause())).mo8201a("com/google/apps/tiktok/storage/wipeout/WipeoutSynclet", "lambda$sync$0", 52, "WipeoutSynclet.java")).mo8203a("Wipeout task failed.");
            }
        }
        return null;
    }
}
