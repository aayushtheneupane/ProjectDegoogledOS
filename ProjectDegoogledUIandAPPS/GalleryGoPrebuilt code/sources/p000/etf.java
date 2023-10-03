package p000;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.googlehelp.GoogleHelp;

/* renamed from: etf */
/* compiled from: PG */
public final class etf implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int c = abj.m120c(parcel);
        GoogleHelp googleHelp = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case 1:
                    googleHelp = (GoogleHelp) abj.m84a(parcel, readInt, GoogleHelp.CREATOR);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel, readInt);
                    break;
                case 3:
                    str2 = abj.m127g(parcel, readInt);
                    break;
                case 4:
                    i = abj.m125e(parcel, readInt);
                    break;
                case 5:
                    str3 = abj.m127g(parcel, readInt);
                    break;
                case 6:
                    i2 = abj.m125e(parcel, readInt);
                    break;
                default:
                    abj.m122c(parcel, readInt);
                    break;
            }
        }
        abj.m135o(parcel, c);
        return new ete(googleHelp, str, str2, i, str3, i2);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ete[i];
    }

    /* renamed from: a */
    public static void m8141a(ete ete, Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m97a(parcel, 1, (Parcelable) ete.f8978a, i);
        abj.m98a(parcel, 2, ete.f8979b);
        abj.m98a(parcel, 3, ete.f8980c);
        abj.m114b(parcel, 4, ete.f8981d);
        abj.m98a(parcel, 5, ete.f8982e);
        abj.m114b(parcel, 6, ete.f8983f);
        abj.m92a(parcel, a);
    }
}
