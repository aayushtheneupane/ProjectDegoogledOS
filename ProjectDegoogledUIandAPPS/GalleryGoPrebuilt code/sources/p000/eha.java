package p000;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: eha */
/* compiled from: PG */
final /* synthetic */ class eha implements Callable {

    /* renamed from: a */
    private final Context f8259a;

    public eha(Context context) {
        this.f8259a = context;
    }

    public final Object call() {
        File file = new File(this.f8259a.getCacheDir(), "tmp");
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("Unable to getTemporaryFileMetadata media directory");
        } else if (file.isDirectory()) {
            return file;
        } else {
            throw new IOException("Unable to getTemporaryFileMetadata media directory, wasnt a dir");
        }
    }
}
