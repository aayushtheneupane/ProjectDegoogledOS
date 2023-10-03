package p000;

import android.os.Parcel;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* renamed from: ejj */
/* compiled from: PG */
public final class ejj extends bim implements ejk {

    /* renamed from: a */
    private final /* synthetic */ ejg f8425a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ejj(ejg ejg) {
        super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
        this.f8425a = ejg;
    }

    /* renamed from: a */
    public final void mo4886a(Status status) {
        this.f8425a.mo3507a((ela) status);
    }

    public ejj() {
        super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        switch (i) {
            case 1:
                mo4886a((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case RecyclerView.SCROLL_STATE_SETTLING:
                Status status = (Status) bin.m2617a(parcel, Status.CREATOR);
                throw new UnsupportedOperationException();
            case 3:
                Status status2 = (Status) bin.m2617a(parcel, Status.CREATOR);
                parcel.readLong();
                throw new UnsupportedOperationException();
            case 4:
                Status status3 = (Status) bin.m2617a(parcel, Status.CREATOR);
                throw new UnsupportedOperationException();
            case 5:
                Status status4 = (Status) bin.m2617a(parcel, Status.CREATOR);
                parcel.readLong();
                throw new UnsupportedOperationException();
            case 6:
                Status status5 = (Status) bin.m2617a(parcel, Status.CREATOR);
                eje[] ejeArr = (eje[]) parcel.createTypedArray(eje.CREATOR);
                throw new UnsupportedOperationException();
            case 7:
                DataHolder dataHolder = (DataHolder) bin.m2617a(parcel, DataHolder.CREATOR);
                throw new UnsupportedOperationException();
            case 8:
                Status status6 = (Status) bin.m2617a(parcel, Status.CREATOR);
                ejc ejc = (ejc) bin.m2617a(parcel, ejc.CREATOR);
                throw new UnsupportedOperationException();
            case 9:
                Status status7 = (Status) bin.m2617a(parcel, Status.CREATOR);
                ejc ejc2 = (ejc) bin.m2617a(parcel, ejc.CREATOR);
                throw new UnsupportedOperationException();
            default:
                return false;
        }
    }
}
