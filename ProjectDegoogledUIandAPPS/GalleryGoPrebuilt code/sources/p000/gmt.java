package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: gmt */
/* compiled from: PG */
final /* synthetic */ class gmt implements Callable {

    /* renamed from: a */
    private final gmy f11635a;

    public gmt(gmy gmy) {
        this.f11635a = gmy;
    }

    public final Object call() {
        File file = new File(this.f11635a.f11641b.f11662a.getApplicationInfo().dataDir, "shared_prefs/accounts.xml");
        if (!file.exists()) {
            file = new File(String.valueOf(file.getPath()).concat(".bak"));
        }
        return Boolean.valueOf(file.exists());
    }
}
