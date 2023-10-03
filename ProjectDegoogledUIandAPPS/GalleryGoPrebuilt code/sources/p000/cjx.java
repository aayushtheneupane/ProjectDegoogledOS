package p000;

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: cjx */
/* compiled from: PG */
public final /* synthetic */ class cjx implements Callable {

    /* renamed from: a */
    private final File f4528a;

    /* renamed from: b */
    private final String f4529b;

    public cjx(File file, String str) {
        this.f4528a = file;
        this.f4529b = str;
    }

    public final Object call() {
        File file = this.f4528a;
        String str = this.f4529b;
        if (file.canWrite()) {
            File file2 = new File(file, Environment.DIRECTORY_DCIM);
            if (!file2.exists() && !file2.mkdir()) {
                throw new IOException("Unable to create folder");
            }
            File file3 = new File(file2, str);
            if (file3.exists() || file3.mkdir()) {
                return file3.getPath();
            }
            throw new IOException("Unable to create folder");
        }
        throw new SecurityException("No permissions to create folder in the specified location");
    }
}
