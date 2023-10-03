package p000;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/* renamed from: eqq */
/* compiled from: PG */
public final class eqq extends eqv {
    public static final Parcelable.Creator CREATOR = new eqr();

    /* renamed from: a */
    private final int f8848a;

    /* renamed from: b */
    private final Account f8849b;

    /* renamed from: c */
    private final int f8850c;

    /* renamed from: d */
    private final GoogleSignInAccount f8851d;

    public eqq(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.f8848a = i;
        this.f8849b = account;
        this.f8850c = i2;
        this.f8851d = googleSignInAccount;
    }

    public eqq(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f8848a);
        abj.m97a(parcel, 2, (Parcelable) this.f8849b, i);
        abj.m114b(parcel, 3, this.f8850c);
        abj.m97a(parcel, 4, (Parcelable) this.f8851d, i);
        abj.m92a(parcel, a);
    }
}
