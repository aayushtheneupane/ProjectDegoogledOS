package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: eod */
/* compiled from: PG */
public final class eod {
    private eod() {
    }

    /* renamed from: a */
    public static String m7920a(int i) {
        switch (i) {
            case -1:
                return "SUCCESS_CACHE";
            case 0:
                return "SUCCESS";
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
            case 10:
                return "DEVELOPER_ERROR";
            case 13:
                return "ERROR";
            case 14:
                return "INTERRUPTED";
            case 15:
                return "TIMEOUT";
            case 16:
                return "CANCELED";
            case 17:
                return "API_NOT_CONNECTED";
            case 18:
                return "DEAD_CLIENT";
            default:
                StringBuilder sb = new StringBuilder(32);
                sb.append("unknown status code: ");
                sb.append(i);
                return sb.toString();
        }
    }
}
