package android.support.p000v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.SessionToken2;
import android.support.p000v4.media.session.IMediaSession;

/* renamed from: android.support.v4.media.session.MediaSessionCompat$Token */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new Parcelable.Creator<MediaSessionCompat$Token>() {
        public Object createFromParcel(Parcel parcel) {
            int i = Build.VERSION.SDK_INT;
            return new MediaSessionCompat$Token(parcel.readParcelable((ClassLoader) null), (IMediaSession) null, (SessionToken2) null);
        }

        public Object[] newArray(int i) {
            return new MediaSessionCompat$Token[i];
        }
    };
    private IMediaSession mExtraBinder;
    private final Object mInner;

    MediaSessionCompat$Token(Object obj, IMediaSession iMediaSession, SessionToken2 sessionToken2) {
        this.mInner = obj;
        this.mExtraBinder = iMediaSession;
    }

    public static MediaSessionCompat$Token fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        IMediaSession asInterface = IMediaSession.Stub.asInterface(BundleCompat.getBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
        SessionToken2 fromBundle = SessionToken2.fromBundle(bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2"));
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) bundle.getParcelable("android.support.v4.media.session.TOKEN");
        if (mediaSessionCompat$Token == null) {
            return null;
        }
        return new MediaSessionCompat$Token(mediaSessionCompat$Token.mInner, asInterface, fromBundle);
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
        Object obj2 = this.mInner;
        if (obj2 != null) {
            Object obj3 = mediaSessionCompat$Token.mInner;
            if (obj3 == null) {
                return false;
            }
            return obj2.equals(obj3);
        } else if (mediaSessionCompat$Token.mInner == null) {
            return true;
        } else {
            return false;
        }
    }

    public IMediaSession getExtraBinder() {
        return this.mExtraBinder;
    }

    public int hashCode() {
        Object obj = this.mInner;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public void setExtraBinder(IMediaSession iMediaSession) {
        this.mExtraBinder = iMediaSession;
    }

    public void setSessionToken2(SessionToken2 sessionToken2) {
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = Build.VERSION.SDK_INT;
        parcel.writeParcelable((Parcelable) this.mInner, i);
    }
}
