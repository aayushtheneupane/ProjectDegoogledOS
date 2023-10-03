package p000;

import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import com.google.android.apps.photosgo.R;
import java.util.List;
import java.util.concurrent.Callable;
import p003j$.util.Optional;

/* renamed from: cmj */
/* compiled from: PG */
final /* synthetic */ class cmj implements Callable {

    /* renamed from: a */
    private final cmk f4675a;

    public cmj(cmk cmk) {
        this.f4675a = cmk;
    }

    public final Object call() {
        String str;
        String str2;
        cmk cmk = this.f4675a;
        List<StorageVolume> storageVolumes = ((StorageManager) cmk.f4677b.f4679b.getSystemService("storage")).getStorageVolumes();
        hsj j = hso.m12048j();
        for (StorageVolume next : storageVolumes) {
            if (next.getState().equals("mounted")) {
                if (!next.isPrimary()) {
                    str = (String) ife.m12898e((Object) next.getUuid());
                } else {
                    str = cml.f4678a;
                }
                if (next.isPrimary()) {
                    cju h = cjv.m4424h();
                    h.mo3193b();
                    h.mo3192a(str);
                    if (Environment.isExternalStorageRemovable()) {
                        str2 = System.getenv("SECONDARY_STORAGE");
                    } else {
                        str2 = Environment.getExternalStorageDirectory().getPath();
                    }
                    h.mo3195c((String) ife.m12898e((Object) str2));
                    h.mo3191a((int) R.drawable.quantum_gm_ic_phone_android_vd_theme_24);
                    h.mo3194b(cmk.f4677b.f4679b.getString(R.string.internal_storage_title));
                    j.mo7908c(h.mo3190a());
                } else {
                    cju h2 = cjv.m4424h();
                    h2.mo3193b();
                    h2.mo3192a(str);
                    h2.mo3195c(ckx.m4481a(str));
                    h2.mo3191a((int) R.drawable.quantum_gm_ic_sd_storage_vd_theme_24);
                    h2.mo3194b(next.getDescription(cmk.f4677b.f4679b));
                    j.mo7908c(h2.mo3190a());
                }
            }
        }
        hso a = j.mo7905a();
        cmk.f4676a.set(Optional.m16285of(a));
        return a;
    }
}
