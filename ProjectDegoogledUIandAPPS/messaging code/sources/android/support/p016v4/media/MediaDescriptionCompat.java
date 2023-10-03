package android.support.p016v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.MediaDescriptionCompat */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0086p();
    private final CharSequence mDescription;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    /* renamed from: me */
    private final String f79me;

    /* renamed from: ne */
    private final Uri f80ne;

    /* renamed from: oe */
    private final Uri f81oe;

    /* renamed from: pe */
    private MediaDescription f82pe;

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f79me = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.f80ne = uri;
        this.mExtras = bundle;
        this.f81oe = uri2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006b  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.p016v4.media.MediaDescriptionCompat m83b(java.lang.Object r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x007a
            int r1 = android.os.Build.VERSION.SDK_INT
            android.support.v4.media.q r1 = new android.support.v4.media.q
            r1.<init>()
            android.media.MediaDescription r8 = (android.media.MediaDescription) r8
            java.lang.String r2 = r8.getMediaId()
            r1.setMediaId(r2)
            java.lang.CharSequence r2 = r8.getTitle()
            r1.setTitle(r2)
            java.lang.CharSequence r2 = r8.getSubtitle()
            r1.setSubtitle(r2)
            java.lang.CharSequence r2 = r8.getDescription()
            r1.setDescription(r2)
            android.graphics.Bitmap r2 = r8.getIconBitmap()
            r1.setIconBitmap(r2)
            android.net.Uri r2 = r8.getIconUri()
            r1.setIconUri(r2)
            android.os.Bundle r2 = r8.getExtras()
            java.lang.String r3 = "android.support.v4.media.description.MEDIA_URI"
            if (r2 == 0) goto L_0x0048
            android.support.p016v4.media.session.C0107q.m130b(r2)
            android.os.Parcelable r4 = r2.getParcelable(r3)
            android.net.Uri r4 = (android.net.Uri) r4
            goto L_0x0049
        L_0x0048:
            r4 = r0
        L_0x0049:
            if (r4 == 0) goto L_0x0061
            java.lang.String r5 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r6 = r2.containsKey(r5)
            if (r6 == 0) goto L_0x005b
            int r6 = r2.size()
            r7 = 2
            if (r6 != r7) goto L_0x005b
            goto L_0x0062
        L_0x005b:
            r2.remove(r3)
            r2.remove(r5)
        L_0x0061:
            r0 = r2
        L_0x0062:
            r1.setExtras(r0)
            if (r4 == 0) goto L_0x006b
            r1.setMediaUri(r4)
            goto L_0x0074
        L_0x006b:
            int r0 = android.os.Build.VERSION.SDK_INT
            android.net.Uri r0 = r8.getMediaUri()
            r1.setMediaUri(r0)
        L_0x0074:
            android.support.v4.media.MediaDescriptionCompat r0 = r1.build()
            r0.f82pe = r8
        L_0x007a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p016v4.media.MediaDescriptionCompat.m83b(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public int describeContents() {
        return 0;
    }

    public String getMediaId() {
        return this.f79me;
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = Build.VERSION.SDK_INT;
        ((MediaDescription) mo464yb()).writeToParcel(parcel, i);
    }

    /* renamed from: yb */
    public Object mo464yb() {
        MediaDescription mediaDescription = this.f82pe;
        if (mediaDescription != null) {
            return mediaDescription;
        }
        int i = Build.VERSION.SDK_INT;
        MediaDescription.Builder builder = new MediaDescription.Builder();
        builder.setMediaId(this.f79me);
        builder.setTitle(this.mTitle);
        builder.setSubtitle(this.mSubtitle);
        builder.setDescription(this.mDescription);
        builder.setIconBitmap(this.mIcon);
        builder.setIconUri(this.f80ne);
        Bundle bundle = this.mExtras;
        int i2 = Build.VERSION.SDK_INT;
        builder.setExtras(bundle);
        int i3 = Build.VERSION.SDK_INT;
        builder.setMediaUri(this.f81oe);
        this.f82pe = builder.build();
        return this.f82pe;
    }
}
