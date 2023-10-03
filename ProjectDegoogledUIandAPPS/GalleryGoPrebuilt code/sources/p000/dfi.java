package p000;

import android.content.Context;
import android.provider.MediaStore;
import java.util.concurrent.Callable;

/* renamed from: dfi */
/* compiled from: PG */
final /* synthetic */ class dfi implements Callable {

    /* renamed from: a */
    private final Context f6439a;

    public dfi(Context context) {
        this.f6439a = context;
    }

    public final Object call() {
        Context context = this.f6439a;
        int i = dfj.f6440a;
        return MediaStore.getVersion(context);
    }
}
