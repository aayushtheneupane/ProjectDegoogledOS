package androidx.media;

import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;

/* renamed from: androidx.media.N */
class C0493N {
    private String mPackageName;
    private int mPid;
    private int mUid;

    C0493N(String str, int i, int i2) {
        this.mPackageName = str;
        this.mPid = i;
        this.mUid = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0493N)) {
            return false;
        }
        C0493N n = (C0493N) obj;
        if (this.mPid == -1 || n.mPid == -1) {
            if (!TextUtils.equals(this.mPackageName, n.mPackageName) || this.mUid != n.mUid) {
                return false;
            }
            return true;
        } else if (TextUtils.equals(this.mPackageName, n.mPackageName) && this.mPid == n.mPid && this.mUid == n.mUid) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.mPackageName, Integer.valueOf(this.mUid));
    }
}
