package p000;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.common.api.Scope;

/* renamed from: eps */
/* compiled from: PG */
public final class eps implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Bundle bundle = null;
        Account account = null;
        ejt[] ejtArr = null;
        ejt[] ejtArr2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case 1:
                    i = abj.m125e(parcel2, readInt);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    i2 = abj.m125e(parcel2, readInt);
                    break;
                case 3:
                    i3 = abj.m125e(parcel2, readInt);
                    break;
                case 4:
                    str = abj.m127g(parcel2, readInt);
                    break;
                case 5:
                    iBinder = abj.m128h(parcel2, readInt);
                    break;
                case 6:
                    scopeArr = (Scope[]) abj.m119b(parcel2, readInt, Scope.CREATOR);
                    break;
                case 7:
                    bundle = abj.m129i(parcel2, readInt);
                    break;
                case 8:
                    account = (Account) abj.m84a(parcel2, readInt, Account.CREATOR);
                    break;
                case 10:
                    ejtArr = (ejt[]) abj.m119b(parcel2, readInt, ejt.CREATOR);
                    break;
                case 11:
                    ejtArr2 = (ejt[]) abj.m119b(parcel2, readInt, ejt.CREATOR);
                    break;
                case 12:
                    z = abj.m124d(parcel2, readInt);
                    break;
                case 13:
                    i4 = abj.m125e(parcel2, readInt);
                    break;
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new epr(i, i2, i3, str, iBinder, scopeArr, bundle, account, ejtArr, ejtArr2, z, i4);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new epr[i];
    }

    /* renamed from: a */
    static void m8010a(epr epr, Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, epr.f8792a);
        abj.m114b(parcel, 2, epr.f8793b);
        abj.m114b(parcel, 3, epr.f8794c);
        abj.m98a(parcel, 4, epr.f8795d);
        abj.m96a(parcel, 5, epr.f8796e);
        abj.m103a(parcel, 6, (Parcelable[]) epr.f8797f, i);
        abj.m95a(parcel, 7, epr.f8798g);
        abj.m97a(parcel, 8, (Parcelable) epr.f8799h, i);
        abj.m103a(parcel, 10, (Parcelable[]) epr.f8800i, i);
        abj.m103a(parcel, 11, (Parcelable[]) epr.f8801j, i);
        abj.m100a(parcel, 12, epr.f8802k);
        abj.m114b(parcel, 13, epr.f8803l);
        abj.m92a(parcel, a);
    }
}
