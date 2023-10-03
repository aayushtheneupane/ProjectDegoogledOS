package p000;

import android.content.Context;
import android.os.Build;
import java.io.File;

/* renamed from: exq */
/* compiled from: PG */
public final class exq {

    /* renamed from: a */
    private final Context f9190a;

    /* renamed from: b */
    private final Object f9191b = new Object();

    /* renamed from: c */
    private File f9192c;

    public exq(Context context) {
        this.f9190a = context.getApplicationContext();
    }

    /* renamed from: a */
    public final File mo5375a() {
        File file;
        synchronized (this.f9191b) {
            if (this.f9192c == null) {
                int i = Build.VERSION.SDK_INT;
                this.f9192c = this.f9190a.getDataDir();
            }
            file = this.f9192c;
        }
        return file;
    }
}
