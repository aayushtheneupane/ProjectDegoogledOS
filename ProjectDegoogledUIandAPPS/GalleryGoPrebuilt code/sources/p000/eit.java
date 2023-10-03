package p000;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

/* renamed from: eit */
/* compiled from: PG */
public final class eit implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        ArrayList arrayList = null;
        String str7 = null;
        String str8 = null;
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int readInt = parcel.readInt();
            switch (abj.m111b(readInt)) {
                case 1:
                    i = abj.m125e(parcel2, readInt);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    str = abj.m127g(parcel2, readInt);
                    break;
                case 3:
                    str2 = abj.m127g(parcel2, readInt);
                    break;
                case 4:
                    str3 = abj.m127g(parcel2, readInt);
                    break;
                case 5:
                    str4 = abj.m127g(parcel2, readInt);
                    break;
                case 6:
                    uri = (Uri) abj.m84a(parcel2, readInt, Uri.CREATOR);
                    break;
                case 7:
                    str5 = abj.m127g(parcel2, readInt);
                    break;
                case 8:
                    j = abj.m126f(parcel2, readInt);
                    break;
                case 9:
                    str6 = abj.m127g(parcel2, readInt);
                    break;
                case 10:
                    arrayList = abj.m121c(parcel2, readInt, Scope.CREATOR);
                    break;
                case 11:
                    str7 = abj.m127g(parcel2, readInt);
                    break;
                case 12:
                    str8 = abj.m127g(parcel2, readInt);
                    break;
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
