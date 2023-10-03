package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: gnv */
/* compiled from: PG */
final /* synthetic */ class gnv implements Callable {

    /* renamed from: a */
    private final File f11707a;

    public gnv(File file) {
        this.f11707a = file;
    }

    public final Object call() {
        Iterable<File> iterable;
        File file = this.f11707a;
        if (!file.exists()) {
            return null;
        }
        hyy hyy = iab.f13711a;
        ife.m12898e((Object) hyy);
        hzc hzc = new hzc(hyy);
        ife.m12898e((Object) file);
        hto a = hto.m12120a((Object) file);
        ife.m12898e((Object) a);
        if (!a.isEmpty()) {
            hvr a2 = ((hvo) a).iterator();
            while (a2.hasNext()) {
                hzc.f13676a.mo8281a(a2.next());
            }
            iterable = new hyz(hzc, a);
        } else {
            iterable = hvf.f13465a;
        }
        boolean z = true;
        for (File file2 : iterable) {
            if (!file.equals(file2) && file2.exists()) {
                file2.setWritable(true, true);
                z &= file2.delete();
            }
        }
        if (z && file.setWritable(false, false) && file.list().length == 0) {
            return null;
        }
        file.setWritable(true, true);
        String valueOf = String.valueOf(file);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
        sb.append("Failed to wipe: ");
        sb.append(valueOf);
        throw new RuntimeException(sb.toString());
    }
}
