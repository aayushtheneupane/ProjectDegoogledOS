package p000;

import android.content.Context;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: ddc */
/* compiled from: PG */
final /* synthetic */ class ddc implements Callable {

    /* renamed from: a */
    private final Context f6320a;

    /* renamed from: b */
    private final List f6321b;

    /* renamed from: c */
    private final List f6322c;

    /* renamed from: d */
    private final cjr f6323d;

    /* renamed from: e */
    private final efu f6324e;

    public ddc(Context context, List list, List list2, cjr cjr, efu efu) {
        this.f6320a = context;
        this.f6321b = list;
        this.f6322c = list2;
        this.f6323d = cjr;
        this.f6324e = efu;
    }

    public final Object call() {
        StorageVolume storageVolume;
        Context context = this.f6320a;
        List list = this.f6321b;
        List list2 = this.f6322c;
        cjr cjr = this.f6323d;
        efu efu = this.f6324e;
        int i = ddf.f6327a;
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        HashSet e = ife.m12900e();
        htm j = hto.m12130j();
        j.mo7995b((Iterable) list);
        j.mo7995b((Iterable) list2);
        for (File file : j.mo7993a()) {
            if ((!file.exists() || !file.canWrite()) && (storageVolume = storageManager.getStorageVolume(file)) != null && !storageVolume.isPrimary()) {
                if (!cjr.mo3175a() || efu.mo4788a(file) == null) {
                    e.add(storageVolume);
                }
            }
        }
        return ife.m12899e((Iterable) e);
    }
}
