package p000;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;

/* renamed from: epr */
/* compiled from: PG */
public final class epr extends eqv {
    public static final Parcelable.Creator CREATOR = new eps();

    /* renamed from: a */
    public final int f8792a;

    /* renamed from: b */
    public final int f8793b;

    /* renamed from: c */
    public final int f8794c;

    /* renamed from: d */
    public String f8795d;

    /* renamed from: e */
    public IBinder f8796e;

    /* renamed from: f */
    public Scope[] f8797f;

    /* renamed from: g */
    public Bundle f8798g;

    /* renamed from: h */
    public Account f8799h;

    /* renamed from: i */
    public ejt[] f8800i;

    /* renamed from: j */
    public ejt[] f8801j;

    /* renamed from: k */
    public final boolean f8802k;

    /* renamed from: l */
    public int f8803l;

    public epr(int i) {
        this.f8792a = 4;
        this.f8794c = ejx.f8457c;
        this.f8793b = i;
        this.f8802k = true;
    }

    public epr(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, ejt[] ejtArr, ejt[] ejtArr2, boolean z, int i4) {
        eqg eqg;
        this.f8792a = i;
        this.f8793b = i2;
        this.f8794c = i3;
        if ("com.google.android.gms".equals(str)) {
            this.f8795d = "com.google.android.gms";
        } else {
            this.f8795d = str;
        }
        if (i >= 2) {
            this.f8796e = iBinder;
            this.f8799h = account;
        } else {
            Account account2 = null;
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface instanceof eqg) {
                    eqg = (eqg) queryLocalInterface;
                } else {
                    eqg = new eqf(iBinder);
                }
                if (eqg != null) {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        account2 = eqg.mo5160b();
                    } catch (RemoteException e) {
                        Log.w("AccountAccessor", "Remote account accessor probably died");
                    } catch (Throwable th) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        throw th;
                    }
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
            this.f8799h = account2;
        }
        this.f8797f = scopeArr;
        this.f8798g = bundle;
        this.f8800i = ejtArr;
        this.f8801j = ejtArr2;
        this.f8802k = z;
        this.f8803l = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        eps.m8010a(this, parcel, i);
    }
}
