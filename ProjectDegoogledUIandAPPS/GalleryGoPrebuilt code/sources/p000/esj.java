package p000;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.common.data.BitmapTeleporter;
import java.util.ArrayList;

/* renamed from: esj */
/* compiled from: PG */
public final class esj implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        Bundle bundle = null;
        String str2 = null;
        ApplicationErrorReport applicationErrorReport = null;
        String str3 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str4 = null;
        ArrayList arrayList = null;
        eso eso = null;
        esm esm = null;
        Bitmap bitmap = null;
        String str5 = null;
        long j = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel2, readInt);
                    break;
                case 3:
                    bundle = abj.m129i(parcel2, readInt);
                    break;
                case 5:
                    str2 = abj.m127g(parcel2, readInt);
                    break;
                case 6:
                    applicationErrorReport = (ApplicationErrorReport) abj.m84a(parcel2, readInt, ApplicationErrorReport.CREATOR);
                    break;
                case 7:
                    str3 = abj.m127g(parcel2, readInt);
                    break;
                case 8:
                    bitmapTeleporter = (BitmapTeleporter) abj.m84a(parcel2, readInt, BitmapTeleporter.CREATOR);
                    break;
                case 9:
                    str4 = abj.m127g(parcel2, readInt);
                    break;
                case 10:
                    arrayList = abj.m121c(parcel2, readInt, esk.CREATOR);
                    break;
                case 11:
                    z = abj.m124d(parcel2, readInt);
                    break;
                case 12:
                    eso = (eso) abj.m84a(parcel2, readInt, eso.CREATOR);
                    break;
                case 13:
                    esm = (esm) abj.m84a(parcel2, readInt, esm.CREATOR);
                    break;
                case 14:
                    z2 = abj.m124d(parcel2, readInt);
                    break;
                case 15:
                    bitmap = (Bitmap) abj.m84a(parcel2, readInt, Bitmap.CREATOR);
                    break;
                case 16:
                    str5 = abj.m127g(parcel2, readInt);
                    break;
                case 17:
                    z3 = abj.m124d(parcel2, readInt);
                    break;
                case 18:
                    j = abj.m126f(parcel2, readInt);
                    break;
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new esi(str, bundle, str2, applicationErrorReport, str3, bitmapTeleporter, str4, arrayList, z, eso, esm, z2, bitmap, str5, z3, j);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new esi[i];
    }

    /* renamed from: a */
    public static void m8099a(esi esi, Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m98a(parcel, 2, esi.f8924a);
        abj.m95a(parcel, 3, esi.f8925b);
        abj.m98a(parcel, 5, esi.f8926c);
        abj.m97a(parcel, 6, (Parcelable) esi.f8927d, i);
        abj.m98a(parcel, 7, esi.f8928e);
        abj.m97a(parcel, 8, (Parcelable) esi.f8929f, i);
        abj.m98a(parcel, 9, esi.f8930g);
        abj.m115b(parcel, 10, esi.f8931h);
        abj.m100a(parcel, 11, esi.f8932i);
        abj.m97a(parcel, 12, (Parcelable) esi.f8933j, i);
        abj.m97a(parcel, 13, (Parcelable) esi.f8934k, i);
        abj.m100a(parcel, 14, esi.f8935l);
        abj.m97a(parcel, 15, (Parcelable) esi.f8936m, i);
        abj.m98a(parcel, 16, esi.f8937n);
        abj.m100a(parcel, 17, esi.f8938o);
        abj.m94a(parcel, 18, esi.f8939p);
        abj.m92a(parcel, a);
    }
}
