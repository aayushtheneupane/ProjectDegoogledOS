package p000;

import android.content.Context;
import android.os.StatFs;
import android.text.format.Formatter;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: cmi */
/* compiled from: PG */
final /* synthetic */ class cmi implements hpr {

    /* renamed from: a */
    private final cmk f4674a;

    public cmi(cmk cmk) {
        this.f4674a = cmk;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        StatFs statFs;
        cmk cmk = this.f4674a;
        hsj j = hso.m12048j();
        hvs i = ((hso) obj).listIterator();
        while (i.hasNext()) {
            cjv cjv = (cjv) i.next();
            try {
                if (!cml.f4678a.equals(cjv.mo3177b())) {
                    statFs = new StatFs(ckx.m4481a(cjv.mo3177b()));
                } else {
                    statFs = new StatFs(cjv.mo3178c());
                }
                cju f = cjv.mo3182f();
                Context context = cmk.f4677b.f4679b;
                f.mo3196d(context.getString(R.string.volume_available_bytes_caption, new Object[]{Formatter.formatShortFileSize(context, statFs.getAvailableBytes())}));
                j.mo7908c(f.mo3190a());
            } catch (IllegalArgumentException e) {
                cwn.m5511a((Throwable) e, "StorageVolumeDataService: Failed to get available bytes for %s", cjv.mo3178c());
                j.mo7908c(cjv);
            }
        }
        hso a = j.mo7905a();
        cmk.f4676a.set(Optional.m16285of(a));
        return a;
    }
}
