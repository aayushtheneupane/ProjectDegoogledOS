package android.support.p000v4.media;

import android.support.p000v4.media.SessionToken2;
import android.support.p000v4.media.session.MediaSessionCompat$Token;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.media.SessionToken2ImplLegacy */
final class SessionToken2ImplLegacy implements SessionToken2.SupportLibraryImpl {
    private final MediaSessionCompat$Token mLegacyToken;

    SessionToken2ImplLegacy(MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.mLegacyToken = mediaSessionCompat$Token;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2ImplLegacy)) {
            return false;
        }
        return this.mLegacyToken.equals(((SessionToken2ImplLegacy) obj).mLegacyToken);
    }

    public int hashCode() {
        return this.mLegacyToken.hashCode();
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("SessionToken2 {legacyToken="), this.mLegacyToken, "}");
    }
}
