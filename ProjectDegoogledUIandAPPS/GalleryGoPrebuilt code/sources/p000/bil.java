package p000;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: bil */
/* compiled from: PG */
public class bil implements IInterface {

    /* renamed from: a */
    private final IBinder f2454a;

    /* renamed from: b */
    private final String f2455b;

    protected bil(IBinder iBinder, String str) {
        this.f2454a = iBinder;
        this.f2455b = str;
    }

    public final IBinder asBinder() {
        return this.f2454a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo2085a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.f2455b);
        return obtain;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Parcel mo2086a(int i, Parcel parcel) {
        parcel = Parcel.obtain();
        try {
            this.f2454a.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo2088b(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.f2454a.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo2089c(int i, Parcel parcel) {
        try {
            this.f2454a.transact(i, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
