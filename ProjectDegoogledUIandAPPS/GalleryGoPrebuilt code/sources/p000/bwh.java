package p000;

import android.content.Intent;
import com.google.android.apps.photosgo.editor.ExternalEditorActivity;

/* renamed from: bwh */
/* compiled from: PG */
public final class bwh extends bwl implements blo, cng {

    /* renamed from: a */
    public static final String[] f3765a = {"android.permission.READ_EXTERNAL_STORAGE"};

    /* renamed from: b */
    public final ExternalEditorActivity f3766b;

    /* renamed from: c */
    public final cnp f3767c;

    /* renamed from: d */
    public final fee f3768d;

    /* renamed from: a */
    public final cnp mo2832a() {
        return this.f3767c;
    }

    /* renamed from: b */
    public final int mo2565b() {
        return 3;
    }

    public bwh(ExternalEditorActivity externalEditorActivity, cnp cnp, fee fee) {
        this.f3766b = externalEditorActivity;
        this.f3767c = cnp;
        this.f3768d = fee;
    }

    /* renamed from: c */
    public final void mo2833c() {
        this.f3766b.setResult(0);
        this.f3766b.finish();
    }

    /* renamed from: a */
    public static String m3685a(Intent intent) {
        return (!intent.hasExtra("output") || intent.getParcelableExtra("output") == null) ? "" : ife.m12898e((Object) intent.getParcelableExtra("output")).toString();
    }
}
