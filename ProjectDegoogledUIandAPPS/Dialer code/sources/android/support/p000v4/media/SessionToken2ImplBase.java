package android.support.p000v4.media;

import android.content.ComponentName;
import android.support.p000v4.media.SessionToken2;
import android.text.TextUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

/* renamed from: android.support.v4.media.SessionToken2ImplBase */
final class SessionToken2ImplBase implements SessionToken2.SupportLibraryImpl {
    private final IMediaSession2 mISession2;
    private final String mPackageName;
    private final String mServiceName;
    private final String mSessionId;
    private final int mType;
    private final int mUid;

    SessionToken2ImplBase(int i, int i2, String str, String str2, String str3, IMediaSession2 iMediaSession2) {
        this.mUid = i;
        this.mType = i2;
        this.mPackageName = str;
        this.mServiceName = str2;
        if (this.mType != 0) {
            new ComponentName(str, str2);
        }
        this.mSessionId = str3;
        this.mISession2 = iMediaSession2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2ImplBase)) {
            return false;
        }
        SessionToken2ImplBase sessionToken2ImplBase = (SessionToken2ImplBase) obj;
        if (this.mUid != sessionToken2ImplBase.mUid || !TextUtils.equals(this.mPackageName, sessionToken2ImplBase.mPackageName) || !TextUtils.equals(this.mServiceName, sessionToken2ImplBase.mServiceName) || !TextUtils.equals(this.mSessionId, sessionToken2ImplBase.mSessionId) || this.mType != sessionToken2ImplBase.mType) {
            return false;
        }
        IMediaSession2 iMediaSession2 = this.mISession2;
        IMediaSession2 iMediaSession22 = sessionToken2ImplBase.mISession2;
        if ((iMediaSession2 == null || iMediaSession22 == null) ? iMediaSession2 == iMediaSession22 : iMediaSession2.asBinder().equals(iMediaSession22.asBinder())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.mType;
        int i2 = this.mUid;
        int hashCode = this.mPackageName.hashCode();
        int hashCode2 = this.mSessionId.hashCode();
        String str = this.mServiceName;
        return ((((((((str != null ? str.hashCode() : 0) * 31) + hashCode2) * 31) + hashCode) * 31) + i2) * 31) + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SessionToken {pkg=");
        outline13.append(this.mPackageName);
        outline13.append(" id=");
        outline13.append(this.mSessionId);
        outline13.append(" type=");
        outline13.append(this.mType);
        outline13.append(" service=");
        outline13.append(this.mServiceName);
        outline13.append(" IMediaSession2=");
        return GeneratedOutlineSupport.outline11(outline13, this.mISession2, "}");
    }
}
