package p000;

import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.photosgo.wallpaper.CropAndSetWallpaperActivity;
import com.google.android.apps.photosgo.wallpaper.SetWallpaperActivity;
import p003j$.util.Optional;

/* renamed from: eib */
/* compiled from: PG */
public final class eib extends eic implements blo {

    /* renamed from: a */
    public final SetWallpaperActivity f8331a;

    /* renamed from: b */
    public final cjo f8332b;

    public eib(SetWallpaperActivity setWallpaperActivity, cjo cjo) {
        this.f8331a = setWallpaperActivity;
        this.f8332b = cjo;
    }

    /* renamed from: b */
    public final int mo2565b() {
        return 7;
    }

    /* renamed from: a */
    public final void mo4820a(Uri uri, Optional optional) {
        if (Uri.EMPTY.equals(uri)) {
            mo4819a(0);
            cwn.m5514b("SetWallpaperActivityPeer: failed to process non-shareable Uri", new Object[0]);
        }
        Intent intent = new Intent(this.f8331a, CropAndSetWallpaperActivity.class);
        intent.setAction("android.service.wallpaper.CROP_AND_SET_WALLPAPER");
        if (optional.isPresent()) {
            intent.setDataAndType(uri, (String) optional.get());
        } else {
            intent.setData(uri);
        }
        intent.setFlags(1);
        this.f8331a.startActivityForResult(intent, 2);
    }

    /* renamed from: a */
    public final void mo4819a(int i) {
        this.f8331a.setResult(i);
        this.f8331a.finish();
    }
}
