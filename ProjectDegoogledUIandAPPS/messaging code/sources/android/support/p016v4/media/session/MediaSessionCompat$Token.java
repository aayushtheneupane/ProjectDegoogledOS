package android.support.p016v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.C0615e;

@SuppressLint({"BanParcelableUsage"})
/* renamed from: android.support.v4.media.session.MediaSessionCompat$Token */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0106p();

    /* renamed from: ye */
    private final Object f103ye;

    /* renamed from: ze */
    private C0095e f104ze;

    MediaSessionCompat$Token(Object obj, C0095e eVar, C0615e eVar2) {
        this.f103ye = obj;
        this.f104ze = eVar;
    }

    /* renamed from: a */
    public static MediaSessionCompat$Token m98a(Object obj, C0095e eVar) {
        if (obj == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        if (obj instanceof MediaSession.Token) {
            return new MediaSessionCompat$Token(obj, eVar, (C0615e) null);
        }
        throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
    }

    /* renamed from: Ab */
    public C0095e mo526Ab() {
        return this.f104ze;
    }

    /* renamed from: a */
    public void mo528a(C0615e eVar) {
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Object obj2 = this.f103ye;
        if (obj2 != null) {
            Object obj3 = mediaSessionCompat$Token.f103ye;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        } else if (mediaSessionCompat$Token.f103ye == null) {
            return true;
        } else {
            return false;
        }
    }

    public Object getToken() {
        return this.f103ye;
    }

    public int hashCode() {
        Object obj = this.f103ye;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = Build.VERSION.SDK_INT;
        parcel.writeParcelable((Parcelable) this.f103ye, i);
    }

    /* renamed from: a */
    public void mo527a(C0095e eVar) {
        this.f104ze = eVar;
    }
}
