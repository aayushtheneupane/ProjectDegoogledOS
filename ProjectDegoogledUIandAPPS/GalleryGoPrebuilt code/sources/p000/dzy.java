package p000;

import android.content.Intent;
import com.google.android.apps.photosgo.picker.ExternalPickerActivity;

/* renamed from: dzy */
/* compiled from: PG */
public final class dzy extends eac implements cng, blo {

    /* renamed from: a */
    public static final String[] f7752a = {"text/uri-list"};

    /* renamed from: b */
    public final ExternalPickerActivity f7753b;

    /* renamed from: c */
    public final cnp f7754c;

    /* renamed from: d */
    public final fee f7755d;

    /* renamed from: a */
    public final cnp mo2832a() {
        return this.f7754c;
    }

    /* renamed from: b */
    public final int mo2565b() {
        return 5;
    }

    public dzy(ExternalPickerActivity externalPickerActivity, cnp cnp, fee fee) {
        this.f7753b = externalPickerActivity;
        this.f7754c = cnp;
        this.f7755d = fee;
    }

    /* renamed from: a */
    public final hom mo4620a(Intent intent) {
        this.f7753b.setResult(-1, intent);
        this.f7753b.finish();
        return hom.f13155a;
    }

    /* renamed from: c */
    public final void mo4621c() {
        if (!this.f7754c.mo3274e()) {
            this.f7753b.setResult(0);
            this.f7753b.finish();
        }
    }
}
