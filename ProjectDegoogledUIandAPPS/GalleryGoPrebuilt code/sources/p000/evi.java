package p000;

import android.os.Parcel;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.common.api.Status;

/* renamed from: evi */
/* compiled from: PG */
public abstract class evi extends bim implements evj {
    public evi() {
        super("com.google.android.gms.phenotype.internal.IPhenotypeCallbacks");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo2042a(int i, Parcel parcel, Parcel parcel2) {
        switch (i) {
            case 1:
                mo5308a((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case RecyclerView.SCROLL_STATE_SETTLING:
                mo5315b((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 3:
                mo5319d((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 4:
                mo5310a((Status) bin.m2617a(parcel, Status.CREATOR), (eul) bin.m2617a(parcel, eul.CREATOR));
                return true;
            case 5:
                mo5320e((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 6:
                mo5312a((Status) bin.m2617a(parcel, Status.CREATOR), (eup) bin.m2617a(parcel, eup.CREATOR));
                return true;
            case 7:
                mo5311a((Status) bin.m2617a(parcel, Status.CREATOR), (eun) bin.m2617a(parcel, eun.CREATOR));
                return true;
            case 8:
                mo5321f((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 9:
                mo5313a((Status) bin.m2617a(parcel, Status.CREATOR), (eus) bin.m2617a(parcel, eus.CREATOR));
                return true;
            case 10:
                mo5317b((Status) bin.m2617a(parcel, Status.CREATOR), (eul) bin.m2617a(parcel, eul.CREATOR));
                return true;
            case 11:
                mo5309a((Status) bin.m2617a(parcel, Status.CREATOR), parcel.readLong());
                return true;
            case 12:
                mo5322g((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 13:
                mo5314a((Status) bin.m2617a(parcel, Status.CREATOR), (euw) bin.m2617a(parcel, euw.CREATOR));
                return true;
            case 14:
                mo5318c((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 15:
                mo5323h((Status) bin.m2617a(parcel, Status.CREATOR));
                return true;
            case 16:
                mo5316b((Status) bin.m2617a(parcel, Status.CREATOR), parcel.readLong());
                return true;
            default:
                return false;
        }
    }
}
