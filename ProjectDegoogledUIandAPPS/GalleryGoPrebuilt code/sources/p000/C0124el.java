package p000;

import java.io.File;
import java.io.FileFilter;

/* renamed from: el */
/* compiled from: PG */
public final class C0124el implements FileFilter {

    /* renamed from: a */
    private final /* synthetic */ String f8504a;

    public C0124el(String str) {
        this.f8504a = str;
    }

    public final boolean accept(File file) {
        String name = file.getName();
        return !name.startsWith(this.f8504a) && !name.equals("MultiDex.lock");
    }
}
