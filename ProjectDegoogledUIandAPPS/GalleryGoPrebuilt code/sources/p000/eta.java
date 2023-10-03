package p000;

import android.accounts.Account;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.util.ArrayList;

/* renamed from: eta */
/* compiled from: PG */
public final class eta implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        String str = null;
        Account account = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        Bitmap bitmap = null;
        ArrayList arrayList = null;
        Bundle bundle2 = null;
        Bitmap bitmap2 = null;
        byte[] bArr = null;
        String str4 = null;
        Uri uri = null;
        ArrayList arrayList2 = null;
        eso eso = null;
        ArrayList arrayList3 = null;
        ErrorReport errorReport = null;
        TogglingData togglingData = null;
        PendingIntent pendingIntent = null;
        String str5 = null;
        String str6 = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z4 = false;
        boolean z5 = false;
        int i7 = 0;
        boolean z6 = false;
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
                    account = (Account) abj.m84a(parcel2, readInt, Account.CREATOR);
                    break;
                case 4:
                    bundle = abj.m129i(parcel2, readInt);
                    break;
                case 5:
                    z = abj.m124d(parcel2, readInt);
                    break;
                case 6:
                    z2 = abj.m124d(parcel2, readInt);
                    break;
                case 7:
                    arrayList = abj.m134n(parcel2, readInt);
                    break;
                case 10:
                    bundle2 = abj.m129i(parcel2, readInt);
                    break;
                case 11:
                    bitmap2 = (Bitmap) abj.m84a(parcel2, readInt, Bitmap.CREATOR);
                    break;
                case 14:
                    str4 = abj.m127g(parcel2, readInt);
                    break;
                case 15:
                    uri = (Uri) abj.m84a(parcel2, readInt, Uri.CREATOR);
                    break;
                case 16:
                    arrayList2 = abj.m121c(parcel2, readInt, eua.CREATOR);
                    break;
                case 17:
                    i4 = abj.m125e(parcel2, readInt);
                    break;
                case 18:
                    arrayList3 = abj.m121c(parcel2, readInt, etg.CREATOR);
                    break;
                case 19:
                    bArr = abj.m130j(parcel2, readInt);
                    break;
                case 20:
                    i2 = abj.m125e(parcel2, readInt);
                    break;
                case 21:
                    i3 = abj.m125e(parcel2, readInt);
                    break;
                case 22:
                    z3 = abj.m124d(parcel2, readInt);
                    break;
                case 23:
                    errorReport = (ErrorReport) abj.m84a(parcel2, readInt, ErrorReport.CREATOR);
                    break;
                case 25:
                    eso = (eso) abj.m84a(parcel2, readInt, eso.CREATOR);
                    break;
                case 28:
                    str2 = abj.m127g(parcel2, readInt);
                    break;
                case 31:
                    togglingData = (TogglingData) abj.m84a(parcel2, readInt, TogglingData.CREATOR);
                    break;
                case 32:
                    i5 = abj.m125e(parcel2, readInt);
                    break;
                case 33:
                    pendingIntent = (PendingIntent) abj.m84a(parcel2, readInt, PendingIntent.CREATOR);
                    break;
                case 34:
                    str3 = abj.m127g(parcel2, readInt);
                    break;
                case 35:
                    bitmap = (Bitmap) abj.m84a(parcel2, readInt, Bitmap.CREATOR);
                    break;
                case 36:
                    i6 = abj.m125e(parcel2, readInt);
                    break;
                case 37:
                    z4 = abj.m124d(parcel2, readInt);
                    break;
                case 38:
                    z5 = abj.m124d(parcel2, readInt);
                    break;
                case 39:
                    i7 = abj.m125e(parcel2, readInt);
                    break;
                case 40:
                    str5 = abj.m127g(parcel2, readInt);
                    break;
                case 41:
                    z6 = abj.m124d(parcel2, readInt);
                    break;
                case 42:
                    str6 = abj.m127g(parcel2, readInt);
                    break;
                default:
                    abj.m122c(parcel2, readInt);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new GoogleHelp(i, str, account, bundle, str2, str3, bitmap, z, z2, arrayList, bundle2, bitmap2, bArr, i2, i3, str4, uri, arrayList2, i4, eso, arrayList3, z3, errorReport, togglingData, i5, pendingIntent, i6, z4, z5, i7, str5, z6, str6);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new GoogleHelp[i];
    }
}
