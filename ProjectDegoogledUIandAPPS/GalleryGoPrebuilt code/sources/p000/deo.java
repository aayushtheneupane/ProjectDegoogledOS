package p000;

import android.content.ContentResolver;

/* renamed from: deo */
/* compiled from: PG */
public final class deo {

    /* renamed from: a */
    public final ContentResolver f6396a;

    /* renamed from: b */
    public final inw f6397b;

    /* renamed from: c */
    public final gto f6398c;

    /* renamed from: d */
    public final gto f6399d;

    /* renamed from: e */
    public final gto f6400e;

    /* renamed from: f */
    public final iel f6401f;

    /* renamed from: g */
    public boolean f6402g = false;

    /* renamed from: h */
    public boolean f6403h = false;

    public deo(ContentResolver contentResolver, inw inw, hlz hlz, iel iel) {
        this.f6396a = contentResolver;
        this.f6397b = inw;
        this.f6398c = new gto(this, hlz, "ImageContentObserver", iel);
        this.f6399d = new gto(this, hlz, "VideoContentObserver", iel);
        this.f6400e = new gto(this, hlz, "FileContentObserver", iel);
        this.f6401f = gte.m10774a(iel);
    }

    /* renamed from: a */
    public final void mo4100a() {
        this.f6403h = true;
    }

    /* renamed from: b */
    public final void mo4101b() {
        this.f6403h = false;
    }
}
