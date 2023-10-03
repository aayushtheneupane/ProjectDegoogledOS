package p000;

import android.system.Os;
import java.util.concurrent.Callable;

/* renamed from: fre */
/* compiled from: PG */
final /* synthetic */ class fre implements Callable {

    /* renamed from: a */
    private final String f10308a;

    public fre(String str) {
        this.f10308a = str;
    }

    public final Object call() {
        return Os.lstat(this.f10308a);
    }
}
