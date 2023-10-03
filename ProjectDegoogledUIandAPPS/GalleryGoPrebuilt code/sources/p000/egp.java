package p000;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.storage.StorageVolume;
import android.provider.DocumentsContract;
import com.google.android.apps.photosgo.R;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: egp */
/* compiled from: PG */
public final class egp implements frj, C0438q {

    /* renamed from: a */
    public final grx f8210a;

    /* renamed from: b */
    public final egb f8211b;

    /* renamed from: c */
    public Optional f8212c = Optional.empty();

    /* renamed from: d */
    public Optional f8213d = Optional.empty();

    /* renamed from: e */
    public final gry f8214e = new ego(this);

    /* renamed from: f */
    private final C0147fh f8215f;

    /* renamed from: g */
    private final ContentResolver f8216g;

    /* renamed from: h */
    private final frm f8217h;

    /* renamed from: i */
    private final dcz f8218i;

    /* renamed from: j */
    private Optional f8219j = Optional.empty();

    /* renamed from: k */
    private final efu f8220k;

    /* renamed from: l */
    private final cjr f8221l;

    /* renamed from: m */
    private final gry f8222m = new egn(this);

    public egp(C0147fh fhVar, ContentResolver contentResolver, frm frm, grx grx, dcz dcz, egb egb, efu efu, cjr cjr) {
        this.f8215f = fhVar;
        this.f8216g = contentResolver;
        this.f8217h = frm;
        this.f8210a = grx;
        this.f8218i = dcz;
        this.f8211b = egb;
        this.f8220k = efu;
        this.f8221l = cjr;
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
    }

    /* renamed from: b */
    public final void mo2558b() {
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
    }

    /* renamed from: c */
    public final void mo2560c() {
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
    }

    /* renamed from: d */
    public final void mo4799d() {
        ife.m12890c(this.f8213d.isPresent());
        if (!((List) this.f8213d.get()).isEmpty()) {
            StorageVolume storageVolume = (StorageVolume) ((List) this.f8213d.get()).remove(0);
            if (!this.f8221l.mo3175a()) {
                Optional ofNullable = Optional.ofNullable(storageVolume.getUuid());
                this.f8219j = ofNullable;
                if (ofNullable.isPresent()) {
                    mo4797a(storageVolume.createAccessIntent((String) null));
                } else {
                    mo4798a(false);
                }
            } else if (Build.VERSION.SDK_INT < 29) {
                mo4797a(storageVolume.createAccessIntent((String) null));
            } else {
                C0147fh fhVar = this.f8215f;
                egj egj = new egj(this, storageVolume);
                ege ege = new ege();
                ftr.m9611b(ege);
                ftr.m9610a((C0147fh) ege);
                ege.mo5429b(fhVar.mo5659r(), "storage_permission_dialog");
                ihg.m13037a((C0140fa) ege, egd.class, (hol) egj);
            }
        } else {
            mo4798a(true);
        }
    }

    /* renamed from: a */
    public final ieh mo4796a(List list, List list2) {
        iev e = m7431e();
        this.f8210a.mo6987a(grw.m10687b(this.f8218i.mo4059a(list, list2)), this.f8222m);
        return e;
    }

    /* renamed from: a */
    public final ieh mo4795a(List list) {
        iev e = m7431e();
        this.f8210a.mo6987a(grw.m10687b(this.f8218i.mo4059a(list, hso.m12047f())), this.f8222m);
        return e;
    }

    /* renamed from: e */
    private final iev m7431e() {
        ife.m12845a(!this.f8212c.isPresent(), (Object) "Permissions request is already in progress");
        iev f = iev.m12774f();
        this.f8212c = Optional.m16285of(f);
        return f;
    }

    /* renamed from: a */
    public final void mo4183a(int i, Intent intent) {
        Uri data;
        if (i != -1 || intent == null || (data = intent.getData()) == null) {
            mo4798a(false);
            return;
        }
        int flags = intent.getFlags() & 3;
        if (!this.f8221l.mo3175a()) {
            this.f8216g.takePersistableUriPermission(data, flags);
            this.f8219j.ifPresent(new egl(this, data));
        } else {
            efu efu = this.f8220k;
            ife.m12896d(DocumentsContract.isTreeUri(data));
            efu.f8173a.getContentResolver().takePersistableUriPermission(data, flags);
        }
        if (this.f8221l.mo3175a()) {
            mo4799d();
        }
    }

    /* renamed from: a */
    public final void mo2556a() {
        this.f8217h.mo6068a((int) R.id.storage_request_code_volume_access, (frj) this);
        this.f8210a.mo6988a(this.f8222m);
        this.f8210a.mo6988a(this.f8214e);
    }

    /* renamed from: a */
    public final void mo4798a(boolean z) {
        this.f8212c.ifPresent(new egk(z));
        this.f8212c = Optional.empty();
        this.f8213d = Optional.empty();
    }

    /* renamed from: a */
    public final void mo4797a(Intent intent) {
        if (intent == null || this.f8215f.mo2634k() == null || intent.resolveActivity(this.f8215f.mo2634k().getPackageManager()) == null) {
            mo4798a(false);
        } else {
            this.f8217h.mo6067a((int) R.id.storage_request_code_volume_access, intent);
        }
    }
}
