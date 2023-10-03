package p000;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: eqf */
/* compiled from: PG */
public final class eqf extends bil implements eqg {
    public eqf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
    }

    /* renamed from: b */
    public final Account mo5160b() {
        Parcel a = mo2086a(2, mo2085a());
        Account account = (Account) bin.m2617a(a, Account.CREATOR);
        a.recycle();
        return account;
    }
}
