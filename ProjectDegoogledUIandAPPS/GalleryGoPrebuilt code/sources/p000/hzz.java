package p000;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

/* renamed from: hzz */
/* compiled from: PG */
final class hzz implements hyy {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Iterable mo8281a(Object obj) {
        File[] listFiles;
        File file = (File) obj;
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(listFiles));
    }
}
