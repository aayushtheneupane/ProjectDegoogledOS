package android.support.p016v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

/* renamed from: android.support.v4.media.q */
public final class C0087q {
    private CharSequence mDescription;
    private Bundle mExtras;
    private Bitmap mIcon;
    private CharSequence mSubtitle;
    private CharSequence mTitle;

    /* renamed from: me */
    private String f99me;

    /* renamed from: ne */
    private Uri f100ne;

    /* renamed from: oe */
    private Uri f101oe;

    public MediaDescriptionCompat build() {
        return new MediaDescriptionCompat(this.f99me, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.f100ne, this.mExtras, this.f101oe);
    }

    public C0087q setDescription(CharSequence charSequence) {
        this.mDescription = charSequence;
        return this;
    }

    public C0087q setExtras(Bundle bundle) {
        this.mExtras = bundle;
        return this;
    }

    public C0087q setIconBitmap(Bitmap bitmap) {
        this.mIcon = bitmap;
        return this;
    }

    public C0087q setIconUri(Uri uri) {
        this.f100ne = uri;
        return this;
    }

    public C0087q setMediaId(String str) {
        this.f99me = str;
        return this;
    }

    public C0087q setMediaUri(Uri uri) {
        this.f101oe = uri;
        return this;
    }

    public C0087q setSubtitle(CharSequence charSequence) {
        this.mSubtitle = charSequence;
        return this;
    }

    public C0087q setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }
}
