package p000;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

/* renamed from: eki */
/* compiled from: PG */
public final class eki {

    /* renamed from: a */
    public static eki f8472a;

    public eki(Context context) {
        context.getApplicationContext();
    }

    /* renamed from: a */
    public static final boolean m7674a(PackageInfo packageInfo) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            ejy[] ejyArr = ekd.f8465a;
            ejy ejy = null;
            if (packageInfo.signatures != null) {
                if (packageInfo.signatures.length == 1) {
                    ejz ejz = new ejz(packageInfo.signatures[0].toByteArray());
                    int i = 0;
                    while (true) {
                        if (i < 2) {
                            if (ejyArr[i].equals(ejz)) {
                                ejy = ejyArr[i];
                                break;
                            }
                            i++;
                        } else {
                            break;
                        }
                    }
                } else {
                    Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
                }
            }
            if (ejy != null) {
                return true;
            }
        }
        return false;
    }

    public eki() {
    }

    public eki(byte[] bArr) {
    }
}
