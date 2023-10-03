package p000;

import android.os.Parcel;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.TogglingData;

/* renamed from: etw */
/* compiled from: PG */
public class etw extends bim implements etx {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        switch (i) {
            case 1:
                mo5239a((GoogleHelp) bin.m2617a(parcel, GoogleHelp.CREATOR));
                parcel2.writeNoException();
                return true;
            case RecyclerView.SCROLL_STATE_SETTLING:
                TogglingData togglingData = (TogglingData) bin.m2617a(parcel, TogglingData.CREATOR);
                throw new UnsupportedOperationException();
            case 3:
                throw new UnsupportedOperationException();
            case 4:
                throw new UnsupportedOperationException();
            case 5:
                throw new UnsupportedOperationException();
            case 6:
                throw new UnsupportedOperationException();
            case 7:
                mo5236a();
                return true;
            case 8:
                mo5238b();
                return true;
            case 9:
                parcel.readInt();
                throw new UnsupportedOperationException();
            case 10:
                throw new UnsupportedOperationException();
            case 11:
                throw new UnsupportedOperationException();
            case 12:
                throw new UnsupportedOperationException();
            case 13:
                parcel.createByteArray();
                throw new UnsupportedOperationException();
            case 14:
                throw new UnsupportedOperationException();
            case 15:
                parcel.createByteArray();
                throw new UnsupportedOperationException();
            case 16:
                throw new UnsupportedOperationException();
            case 17:
                ete ete = (ete) bin.m2617a(parcel, ete.CREATOR);
                throw new UnsupportedOperationException();
            case 18:
                parcel.createByteArray();
                throw new UnsupportedOperationException();
            case 19:
                throw new UnsupportedOperationException();
            default:
                return false;
        }
    }

    public etw() {
        super("com.google.android.gms.googlehelp.internal.common.IGoogleHelpCallbacks");
    }

    /* renamed from: b */
    public void mo5238b() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public void mo5236a() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public void mo5239a(GoogleHelp googleHelp) {
        throw new UnsupportedOperationException();
    }
}
