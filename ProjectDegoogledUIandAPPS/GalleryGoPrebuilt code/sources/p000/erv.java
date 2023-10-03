package p000;

import android.app.ApplicationErrorReport;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.feedback.ErrorReport;
import java.util.ArrayList;

/* renamed from: erv */
/* compiled from: PG */
public final class erv implements Parcelable.Creator {
    /* renamed from: a */
    private static final ErrorReport m8081a(Parcel parcel) {
        Parcel parcel2 = parcel;
        int c = abj.m120c(parcel);
        ApplicationErrorReport applicationErrorReport = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str14 = null;
        String str15 = null;
        byte[] bArr = null;
        String str16 = null;
        String str17 = null;
        String str18 = null;
        Bundle bundle = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        String str22 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        String str27 = null;
        BitmapTeleporter bitmapTeleporter = null;
        String str28 = null;
        esk[] eskArr = null;
        String[] strArr4 = null;
        String str29 = null;
        eso eso = null;
        esm esm = null;
        String str30 = null;
        Bundle bundle2 = null;
        ArrayList arrayList = null;
        Bitmap bitmap = null;
        String str31 = null;
        ArrayList arrayList2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        int i9 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int i10 = 0;
        while (parcel.dataPosition() < c) {
            int b = abj.m112b(parcel);
            switch (abj.m111b(b)) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    applicationErrorReport = (ApplicationErrorReport) abj.m84a(parcel2, b, ApplicationErrorReport.CREATOR);
                    break;
                case 3:
                    str = abj.m127g(parcel2, b);
                    break;
                case 4:
                    i = abj.m125e(parcel2, b);
                    break;
                case 5:
                    str2 = abj.m127g(parcel2, b);
                    break;
                case 6:
                    str3 = abj.m127g(parcel2, b);
                    break;
                case 7:
                    str4 = abj.m127g(parcel2, b);
                    break;
                case 8:
                    str5 = abj.m127g(parcel2, b);
                    break;
                case 9:
                    str6 = abj.m127g(parcel2, b);
                    break;
                case 10:
                    str7 = abj.m127g(parcel2, b);
                    break;
                case 11:
                    str8 = abj.m127g(parcel2, b);
                    break;
                case 12:
                    i2 = abj.m125e(parcel2, b);
                    break;
                case 13:
                    str9 = abj.m127g(parcel2, b);
                    break;
                case 14:
                    str10 = abj.m127g(parcel2, b);
                    break;
                case 15:
                    str11 = abj.m127g(parcel2, b);
                    break;
                case 16:
                    str12 = abj.m127g(parcel2, b);
                    break;
                case 17:
                    str13 = abj.m127g(parcel2, b);
                    break;
                case 18:
                    strArr = abj.m133m(parcel2, b);
                    break;
                case 19:
                    strArr2 = abj.m133m(parcel2, b);
                    break;
                case 20:
                    strArr3 = abj.m133m(parcel2, b);
                    break;
                case 21:
                    str14 = abj.m127g(parcel2, b);
                    break;
                case 22:
                    str15 = abj.m127g(parcel2, b);
                    break;
                case 23:
                    bArr = abj.m130j(parcel2, b);
                    break;
                case 24:
                    i3 = abj.m125e(parcel2, b);
                    break;
                case 25:
                    i4 = abj.m125e(parcel2, b);
                    break;
                case 26:
                    i5 = abj.m125e(parcel2, b);
                    break;
                case 27:
                    i6 = abj.m125e(parcel2, b);
                    break;
                case 28:
                    str16 = abj.m127g(parcel2, b);
                    break;
                case 29:
                    str17 = abj.m127g(parcel2, b);
                    break;
                case 30:
                    str18 = abj.m127g(parcel2, b);
                    break;
                case 31:
                    bundle = abj.m129i(parcel2, b);
                    break;
                case 32:
                    z = abj.m124d(parcel2, b);
                    break;
                case 33:
                    i7 = abj.m125e(parcel2, b);
                    break;
                case 34:
                    i8 = abj.m125e(parcel2, b);
                    break;
                case 35:
                    z2 = abj.m124d(parcel2, b);
                    break;
                case 36:
                    str19 = abj.m127g(parcel2, b);
                    break;
                case 37:
                    str20 = abj.m127g(parcel2, b);
                    break;
                case 38:
                    i9 = abj.m125e(parcel2, b);
                    break;
                case 39:
                    str21 = abj.m127g(parcel2, b);
                    break;
                case 40:
                    str22 = abj.m127g(parcel2, b);
                    break;
                case 41:
                    str23 = abj.m127g(parcel2, b);
                    break;
                case 42:
                    str24 = abj.m127g(parcel2, b);
                    break;
                case 43:
                    str25 = abj.m127g(parcel2, b);
                    break;
                case 44:
                    str26 = abj.m127g(parcel2, b);
                    break;
                case 45:
                    str27 = abj.m127g(parcel2, b);
                    break;
                case 46:
                    bitmapTeleporter = (BitmapTeleporter) abj.m84a(parcel2, b, BitmapTeleporter.CREATOR);
                    break;
                case 47:
                    str28 = abj.m127g(parcel2, b);
                    break;
                case 48:
                    eskArr = (esk[]) abj.m119b(parcel2, b, esk.CREATOR);
                    break;
                case 49:
                    strArr4 = abj.m133m(parcel2, b);
                    break;
                case 50:
                    z3 = abj.m124d(parcel2, b);
                    break;
                case 51:
                    str29 = abj.m127g(parcel2, b);
                    break;
                case 52:
                    eso = (eso) abj.m84a(parcel2, b, eso.CREATOR);
                    break;
                case 53:
                    esm = (esm) abj.m84a(parcel2, b, esm.CREATOR);
                    break;
                case 54:
                    str30 = abj.m127g(parcel2, b);
                    break;
                case 55:
                    z4 = abj.m124d(parcel2, b);
                    break;
                case 56:
                    bundle2 = abj.m129i(parcel2, b);
                    break;
                case 57:
                    arrayList = abj.m121c(parcel2, b, RectF.CREATOR);
                    break;
                case 58:
                    z5 = abj.m124d(parcel2, b);
                    break;
                case 59:
                    bitmap = (Bitmap) abj.m84a(parcel2, b, Bitmap.CREATOR);
                    break;
                case 60:
                    str31 = abj.m127g(parcel2, b);
                    break;
                case 61:
                    arrayList2 = abj.m134n(parcel2, b);
                    break;
                case 62:
                    i10 = abj.m125e(parcel2, b);
                    break;
                default:
                    abj.m122c(parcel2, b);
                    break;
            }
        }
        abj.m135o(parcel2, c);
        return new ErrorReport(applicationErrorReport, str, i, str2, str3, str4, str5, str6, str7, str8, i2, str9, str10, str11, str12, str13, strArr, strArr2, strArr3, str14, str15, bArr, i3, i4, i5, i6, str16, str17, str18, bundle, z, i7, i8, z2, str19, str20, i9, str21, str22, str23, str24, str25, str26, str27, bitmapTeleporter, str28, eskArr, strArr4, z3, str29, eso, esm, str30, z4, bundle2, arrayList, z5, bitmap, str31, arrayList2, i10);
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m8081a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ErrorReport[i];
    }
}
