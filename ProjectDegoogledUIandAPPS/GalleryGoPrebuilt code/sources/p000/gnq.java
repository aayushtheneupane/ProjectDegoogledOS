package p000;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

/* renamed from: gnq */
/* compiled from: PG */
final /* synthetic */ class gnq implements FilenameFilter {

    /* renamed from: a */
    private final Set f11699a;

    public gnq(Set set) {
        this.f11699a = set;
    }

    public final boolean accept(File file, String str) {
        Set set = this.f11699a;
        File file2 = new File(file, str);
        try {
            return !set.contains(Integer.valueOf(str)) && file2.isDirectory() && file2.canWrite();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
