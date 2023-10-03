package p000;

import android.content.Context;
import android.os.Build;
import java.io.File;

/* renamed from: ain */
/* compiled from: PG */
public final class ain {

    /* renamed from: a */
    public static final String f546a = iol.m14236b("WrkDbPathHelper");

    /* renamed from: b */
    public static final String[] f547b = {"-journal", "-shm", "-wal"};

    /* renamed from: b */
    public static File m546b(Context context) {
        int i = Build.VERSION.SDK_INT;
        return new File(context.getNoBackupFilesDir(), "androidx.work.workdb");
    }

    /* renamed from: a */
    public static File m545a(Context context) {
        return context.getDatabasePath("androidx.work.workdb");
    }
}
