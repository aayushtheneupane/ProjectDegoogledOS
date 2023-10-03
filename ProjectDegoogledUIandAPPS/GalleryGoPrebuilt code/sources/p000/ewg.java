package p000;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;

/* renamed from: ewg */
/* compiled from: PG */
public class ewg extends bim implements ewh {
    public ewg() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    /* renamed from: a */
    public void mo5020a(ewp ewp) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        switch (i) {
            case 3:
                ejq ejq = (ejq) bin.m2617a(parcel, ejq.CREATOR);
                ewe ewe = (ewe) bin.m2617a(parcel, ewe.CREATOR);
                break;
            case 4:
                Status status = (Status) bin.m2617a(parcel, Status.CREATOR);
                break;
            case 6:
                Status status2 = (Status) bin.m2617a(parcel, Status.CREATOR);
                break;
            case 7:
                Status status3 = (Status) bin.m2617a(parcel, Status.CREATOR);
                GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) bin.m2617a(parcel, GoogleSignInAccount.CREATOR);
                break;
            case 8:
                mo5020a((ewp) bin.m2617a(parcel, ewp.CREATOR));
                break;
            case 9:
                ewk ewk = (ewk) bin.m2617a(parcel, ewk.CREATOR);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
