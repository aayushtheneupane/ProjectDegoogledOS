package android.support.p000v4.media;

import android.os.Bundle;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.IMediaSession2;
import android.support.p000v4.media.session.MediaSessionCompat$Token;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.media.SessionToken2 */
public final class SessionToken2 {
    private final SupportLibraryImpl mImpl;

    /* renamed from: android.support.v4.media.SessionToken2$SupportLibraryImpl */
    interface SupportLibraryImpl {
    }

    SessionToken2(SupportLibraryImpl supportLibraryImpl) {
        this.mImpl = supportLibraryImpl;
    }

    public static SessionToken2 fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt("android.media.token.type", -1) == 100) {
            return new SessionToken2(new SessionToken2ImplLegacy(MediaSessionCompat$Token.fromBundle(bundle.getBundle("android.media.token.LEGACY"))));
        }
        int i = bundle.getInt("android.media.token.uid");
        int i2 = bundle.getInt("android.media.token.type", -1);
        String string = bundle.getString("android.media.token.package_name");
        String string2 = bundle.getString("android.media.token.service_name");
        String string3 = bundle.getString("android.media.token.session_id");
        IMediaSession2 asInterface = IMediaSession2.Stub.asInterface(BundleCompat.getBinder(bundle, "android.media.token.session_binder"));
        if (i2 != 0) {
            if (i2 != 1 && i2 != 2) {
                throw new IllegalArgumentException("Invalid type");
            } else if (TextUtils.isEmpty(string2)) {
                throw new IllegalArgumentException("Session service needs service name");
            }
        } else if (asInterface == null) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unexpected token for session, binder=", asInterface));
        }
        if (!TextUtils.isEmpty(string) && string3 != null) {
            return new SessionToken2(new SessionToken2ImplBase(i, i2, string, string2, string3, asInterface));
        }
        throw new IllegalArgumentException("Package name nor ID cannot be null.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2)) {
            return false;
        }
        return this.mImpl.equals(((SessionToken2) obj).mImpl);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
