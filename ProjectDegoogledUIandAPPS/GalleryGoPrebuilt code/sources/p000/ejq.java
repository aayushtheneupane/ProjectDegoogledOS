package p000;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import java.util.Arrays;

/* renamed from: ejq */
/* compiled from: PG */
public final class ejq extends eqv {
    public static final Parcelable.Creator CREATOR = new ejr();

    /* renamed from: a */
    public static final ejq f8442a = new ejq(0);

    /* renamed from: b */
    public final int f8443b;

    /* renamed from: c */
    public final PendingIntent f8444c;

    /* renamed from: d */
    public final String f8445d;

    /* renamed from: e */
    private final int f8446e;

    /* renamed from: a */
    public final boolean mo4894a() {
        return (this.f8443b == 0 || this.f8444c == null) ? false : true;
    }

    /* renamed from: b */
    public final boolean mo4895b() {
        return this.f8443b == 0;
    }

    public ejq(int i) {
        this(i, (PendingIntent) null, (String) null);
    }

    public ejq(int i, int i2, PendingIntent pendingIntent, String str) {
        this.f8446e = i;
        this.f8443b = i2;
        this.f8444c = pendingIntent;
        this.f8445d = str;
    }

    public ejq(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, (String) null);
    }

    public ejq(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ejq) {
            ejq ejq = (ejq) obj;
            return this.f8443b == ejq.f8443b && C0652xy.m16068a((Object) this.f8444c, (Object) ejq.f8444c) && C0652xy.m16068a((Object) this.f8445d, (Object) ejq.f8445d);
        }
    }

    /* renamed from: a */
    static String m7633a(int i) {
        if (i == 99) {
            return "UNFINISHED";
        }
        if (i == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case RecyclerView.SCROLL_STATE_SETTLING:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case 17:
                        return "SIGN_IN_FAILED";
                    case 18:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case 21:
                        return "API_VERSION_UPDATE_REQUIRED";
                    default:
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("UNKNOWN_ERROR_CODE(");
                        sb.append(i);
                        sb.append(")");
                        return sb.toString();
                }
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f8443b), this.f8444c, this.f8445d});
    }

    public final String toString() {
        eqo a = C0652xy.m16063a((Object) this);
        a.mo5163a("statusCode", m7633a(this.f8443b));
        a.mo5163a("resolution", this.f8444c);
        a.mo5163a("message", this.f8445d);
        return a.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int a = abj.m82a(parcel);
        abj.m114b(parcel, 1, this.f8446e);
        abj.m114b(parcel, 2, this.f8443b);
        abj.m97a(parcel, 3, (Parcelable) this.f8444c, i);
        abj.m98a(parcel, 4, this.f8445d);
        abj.m92a(parcel, a);
    }
}
