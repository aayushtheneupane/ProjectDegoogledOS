package p000;

import android.app.WallpaperManager;
import android.content.Intent;
import android.net.Uri;

/* renamed from: ehw */
/* compiled from: PG */
final class ehw implements gvc {

    /* renamed from: a */
    private final /* synthetic */ ehy f8318a;

    public ehw(ehy ehy) {
        this.f8318a = ehy;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "CropAndSetWallpaperFragmentPeer: Cannot create temporary file.", new Object[0]);
        this.f8318a.mo4817a(0);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        egx egx = (egx) obj;
        if (!egx.f8238g.equals(egx) && egx.f8238g.equals(this.f8318a.f8327h)) {
            ehy ehy = this.f8318a;
            C0149fj m = ehy.f8321b.mo5653m();
            if (m != null) {
                ehy.f8327h = egx;
                Uri parse = Uri.parse(ehy.f8320a.f8314b);
                WallpaperManager instance = WallpaperManager.getInstance(m);
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setPackage(m.getPackageName()).setFlags(1).putExtra("outputX", instance.getDesiredMinimumWidth()).putExtra("outputY", instance.getDesiredMinimumHeight()).putExtra("output", egx.f8241b);
                ehu ehu = ehy.f8320a;
                if ((ehu.f8313a & 2) != 0) {
                    intent.setDataAndType(parse, ehu.f8315c);
                } else {
                    intent.setData(parse);
                }
                ehy.f8321b.startActivityForResult(intent, 2);
            }
        }
    }
}
