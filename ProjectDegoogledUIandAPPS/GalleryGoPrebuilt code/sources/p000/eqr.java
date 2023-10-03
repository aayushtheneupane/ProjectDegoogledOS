package p000;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/* renamed from: eqr */
/* compiled from: PG */
public final class eqr implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        Account account = null;
        GoogleSignInAccount googleSignInAccount = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            int b = abj.m111b(readInt);
            if (b == 1) {
                i = abj.m125e(parcel, readInt);
            } else if (b == 2) {
                account = (Account) abj.m84a(parcel, readInt, Account.CREATOR);
            } else if (b == 3) {
                i2 = abj.m125e(parcel, readInt);
            } else if (b != 4) {
                abj.m122c(parcel, readInt);
            } else {
                googleSignInAccount = (GoogleSignInAccount) abj.m84a(parcel, readInt, GoogleSignInAccount.CREATOR);
            }
        }
        abj.m135o(parcel, c);
        return new eqq(i, account, i2, googleSignInAccount);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new eqq[i];
    }
}
