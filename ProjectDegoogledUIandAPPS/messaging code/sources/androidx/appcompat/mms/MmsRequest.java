package androidx.appcompat.mms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.mms.ApnSettingsLoader;
import androidx.appcompat.mms.pdu.C0189f;
import androidx.appcompat.mms.pdu.C0196m;
import androidx.appcompat.mms.pdu.C0201r;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class MmsRequest implements Parcelable {
    protected static final int TASK_TIMEOUT_MS = 30000;
    private static final Integer TYPE_MOBILE_MMS = 2;
    protected final String mLocationUrl;
    protected final ExecutorService mPduTransferExecutor = Executors.newCachedThreadPool();
    protected final Uri mPduUri;
    protected final PendingIntent mPendingIntent;
    private boolean mUseWakeLock;

    protected MmsRequest(String str, Uri uri, PendingIntent pendingIntent) {
        this.mLocationUrl = str;
        this.mPduUri = uri;
        this.mPendingIntent = pendingIntent;
        this.mUseWakeLock = true;
    }

    private static int inetAddressToInt(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        return (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
    }

    static boolean isWrongApnResponse(byte[] bArr, Bundle bundle) {
        if (bArr != null && bArr.length > 0) {
            try {
                C0189f parse = new C0196m(bArr, bundle.getBoolean(CarrierConfigValuesLoader.CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION, true)).parse();
                if (parse != null && (parse instanceof C0201r)) {
                    int hc = ((C0201r) parse).mo1270hc();
                    if (hc == 227 || hc == 132) {
                        return true;
                    }
                    return false;
                }
            } catch (RuntimeException e) {
                Log.w("MmsLib", "Parsing response failed", e);
            }
        }
        return false;
    }

    private static void requestRoute(ConnectivityManager connectivityManager, ApnSettingsLoader.Apn apn, String str) {
        String mmsProxy = apn.getMmsProxy();
        if (TextUtils.isEmpty(mmsProxy)) {
            mmsProxy = Uri.parse(str).getHost();
        }
        try {
            boolean z = false;
            for (InetAddress inetAddress : InetAddress.getAllByName(mmsProxy)) {
                if (requestRouteToHostAddress(connectivityManager, inetAddress)) {
                    Log.i("MmsLib", "Requested route to " + inetAddress);
                    z = true;
                } else {
                    Log.i("MmsLib", "Could not requested route to " + inetAddress);
                }
            }
            if (!z) {
                throw new MmsHttpException(0, "No route requested");
            }
        } catch (UnknownHostException unused) {
            Log.w("MmsLib", "Unknown host " + mmsProxy);
            throw new MmsHttpException(0, "Unknown host");
        }
    }

    private static boolean requestRouteToHostAddress(ConnectivityManager connectivityManager, InetAddress inetAddress) {
        try {
            Method method = connectivityManager.getClass().getMethod("requestRouteToHostAddress", new Class[]{Integer.TYPE, InetAddress.class});
            if (method != null) {
                return ((Boolean) method.invoke(connectivityManager, new Object[]{TYPE_MOBILE_MMS, inetAddress})).booleanValue();
            }
        } catch (Exception e) {
            Log.w("MmsLib", "ConnectivityManager.requestRouteToHostAddress failed " + e);
        }
        if (inetAddress instanceof Inet4Address) {
            try {
                Method method2 = connectivityManager.getClass().getMethod("requestRouteToHost", new Class[]{Integer.TYPE, Integer.TYPE});
                if (method2 != null) {
                    return ((Boolean) method2.invoke(connectivityManager, new Object[]{TYPE_MOBILE_MMS, Integer.valueOf(inetAddressToInt(inetAddress))})).booleanValue();
                }
            } catch (Exception e2) {
                Log.w("MmsLib", "ConnectivityManager.requestRouteToHost failed " + e2);
            }
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract byte[] doHttp(Context context, MmsNetworkManager mmsNetworkManager, ApnSettingsLoader.Apn apn, Bundle bundle, String str, String str2);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: byte[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00f3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0105, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0109, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0115, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0117, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0118, code lost:
        r18 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        android.util.Log.e("MmsLib", "MmsRequest: unexpected failure", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0120, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0121, code lost:
        r18 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0130, code lost:
        r18 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0132, code lost:
        android.util.Log.e("MmsLib", "MmsRequest: MMS network acquiring failure", r0);
        r14 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0139, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x013a, code lost:
        r18 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x013c, code lost:
        android.util.Log.e("MmsLib", "MmsRequest: APN failure", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0141, code lost:
        r14 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x014c, code lost:
        r22.releaseNetwork();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014f, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0105 A[Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117, all -> 0x0115 }, ExcHandler: Exception (e java.lang.Exception), PHI: r18 
      PHI: (r18v14 byte[]) = (r18v10 byte[]), (r18v10 byte[]), (r18v15 byte[]), (r18v15 byte[]) binds: [B:11:0x007f, B:15:0x00c1, B:27:0x00f6, B:28:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:11:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0109 A[Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117, all -> 0x0115 }, ExcHandler: MmsNetworkException (e androidx.appcompat.mms.MmsNetworkException), PHI: r18 
      PHI: (r18v12 byte[]) = (r18v10 byte[]), (r18v10 byte[]), (r18v15 byte[]), (r18v15 byte[]) binds: [B:11:0x007f, B:15:0x00c1, B:27:0x00f6, B:28:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:11:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010b A[Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117, all -> 0x0115 }, ExcHandler: ApnException (e androidx.appcompat.mms.ApnException), PHI: r18 
      PHI: (r18v11 byte[]) = (r18v10 byte[]), (r18v10 byte[]), (r18v15 byte[]), (r18v15 byte[]) binds: [B:11:0x007f, B:15:0x00c1, B:27:0x00f6, B:28:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:11:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0117 A[ExcHandler: Exception (e java.lang.Exception), PHI: r1 
      PHI: (r1v12 byte[]) = (r1v2 byte[]), (r1v27 byte[]), (r1v31 byte[]), (r1v36 byte[]) binds: [B:6:0x0040, B:11:0x007f, B:32:0x0104, B:17:0x00dd] A[DONT_GENERATE, DONT_INLINE], Splitter:B:6:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x012f A[Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117, all -> 0x0115 }, ExcHandler: MmsNetworkException (e androidx.appcompat.mms.MmsNetworkException), PHI: r1 
      PHI: (r1v8 byte[]) = (r1v2 byte[]), (r1v25 byte[]), (r1v29 byte[]), (r1v35 byte[]) binds: [B:6:0x0040, B:11:0x007f, B:32:0x0104, B:17:0x00dd] A[DONT_GENERATE, DONT_INLINE], Splitter:B:6:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0139 A[Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117, all -> 0x0115 }, ExcHandler: ApnException (e androidx.appcompat.mms.ApnException), PHI: r1 
      PHI: (r1v6 byte[]) = (r1v2 byte[]), (r1v24 byte[]), (r1v28 byte[]), (r1v34 byte[]) binds: [B:6:0x0040, B:11:0x007f, B:32:0x0104, B:17:0x00dd] A[DONT_GENERATE, DONT_INLINE], Splitter:B:6:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(android.content.Context r21, androidx.appcompat.mms.MmsNetworkManager r22, androidx.appcompat.mms.ApnSettingsLoader r23, androidx.appcompat.mms.CarrierConfigValuesLoader r24, androidx.appcompat.mms.UserAgentInfoLoader r25) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            java.lang.String r0 = "Execute "
            java.lang.StringBuilder r0 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r0)
            java.lang.Class r1 = r20.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r10 = "MmsLib"
            android.util.Log.i(r10, r0)
            r11 = -1
            r0 = r24
            android.os.Bundle r12 = r0.get(r11)
            r1 = 0
            r13 = 0
            r14 = 1
            if (r12 != 0) goto L_0x0032
            java.lang.String r0 = "Failed to load carrier configuration values"
            android.util.Log.e(r10, r0)
            r0 = 7
            goto L_0x0148
        L_0x0032:
            boolean r0 = r8.loadRequest(r9, r12)
            if (r0 != 0) goto L_0x0040
            java.lang.String r0 = "Failed to load PDU"
            android.util.Log.e(r10, r0)
            r0 = 5
            goto L_0x0148
        L_0x0040:
            r22.acquireNetwork()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r0 = r22.getApnName()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            r2 = r23
            java.util.List r0 = r2.get(r0)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            int r2 = r0.size()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            if (r2 < r14) goto L_0x010d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            r2.<init>()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r3 = "Trying "
            r2.append(r3)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            int r3 = r0.size()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            r2.append(r3)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r3 = " APNs"
            r2.append(r3)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r2 = r2.toString()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            android.util.Log.d(r10, r2)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r15 = r25.getUserAgent()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r16 = r25.getUAProfUrl()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.util.Iterator r17 = r0.iterator()     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            r0 = r1
            r18 = r0
        L_0x007f:
            boolean r1 = r17.hasNext()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            if (r1 == 0) goto L_0x00fa
            java.lang.Object r1 = r17.next()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            r7 = r1
            androidx.appcompat.mms.ApnSettingsLoader$Apn r7 = (androidx.appcompat.mms.ApnSettingsLoader.Apn) r7     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            r1.<init>()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = "Using APN [MMSC="
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = r7.getMmsc()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = ", PROXY="
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = r7.getMmsProxy()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = ", PORT="
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            int r2 = r7.getMmsProxyPort()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r2 = "]"
            r1.append(r2)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r1 = r1.toString()     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            android.util.Log.i(r10, r1)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            java.lang.String r1 = r8.getHttpRequestUrl(r7)     // Catch:{ MmsHttpException -> 0x00f3, ApnException -> 0x010b, MmsNetworkException -> 0x0109, Exception -> 0x0105 }
            android.net.ConnectivityManager r2 = r22.getConnectivityManager()     // Catch:{ MmsHttpException -> 0x00f3, ApnException -> 0x010b, MmsNetworkException -> 0x0109, Exception -> 0x0105 }
            requestRoute(r2, r7, r1)     // Catch:{ MmsHttpException -> 0x00f3, ApnException -> 0x010b, MmsNetworkException -> 0x0109, Exception -> 0x0105 }
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r7
            r5 = r12
            r6 = r15
            r19 = r7
            r7 = r16
            byte[] r1 = r1.doHttp(r2, r3, r4, r5, r6, r7)     // Catch:{ MmsHttpException -> 0x00f3, ApnException -> 0x010b, MmsNetworkException -> 0x0109, Exception -> 0x0105 }
            boolean r2 = isWrongApnResponse(r1, r12)     // Catch:{ MmsHttpException -> 0x00ef, ApnException -> 0x0139, MmsNetworkException -> 0x012f, Exception -> 0x0117 }
            if (r2 != 0) goto L_0x00e7
            r19.setSuccess()     // Catch:{ MmsHttpException -> 0x00ef, ApnException -> 0x0139, MmsNetworkException -> 0x012f, Exception -> 0x0117 }
            goto L_0x00fd
        L_0x00e7:
            androidx.appcompat.mms.MmsHttpException r0 = new androidx.appcompat.mms.MmsHttpException     // Catch:{ MmsHttpException -> 0x00ef, ApnException -> 0x0139, MmsNetworkException -> 0x012f, Exception -> 0x0117 }
            java.lang.String r2 = "Invalid sending address"
            r0.<init>((int) r13, (java.lang.String) r2)     // Catch:{ MmsHttpException -> 0x00ef, ApnException -> 0x0139, MmsNetworkException -> 0x012f, Exception -> 0x0117 }
            throw r0     // Catch:{ MmsHttpException -> 0x00ef, ApnException -> 0x0139, MmsNetworkException -> 0x012f, Exception -> 0x0117 }
        L_0x00ef:
            r0 = move-exception
            r18 = r1
            goto L_0x00f4
        L_0x00f3:
            r0 = move-exception
        L_0x00f4:
            java.lang.String r1 = "HTTP or network failure"
            android.util.Log.w(r10, r1, r0)     // Catch:{ ApnException -> 0x010b, MmsNetworkException -> 0x0109, MmsHttpException -> 0x0107, Exception -> 0x0105 }
            goto L_0x007f
        L_0x00fa:
            r11 = r14
            r1 = r18
        L_0x00fd:
            if (r0 != 0) goto L_0x0104
            r22.releaseNetwork()
            r0 = r11
            goto L_0x0148
        L_0x0104:
            throw r0     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
        L_0x0105:
            r0 = move-exception
            goto L_0x011a
        L_0x0107:
            r0 = move-exception
            goto L_0x0123
        L_0x0109:
            r0 = move-exception
            goto L_0x0132
        L_0x010b:
            r0 = move-exception
            goto L_0x013c
        L_0x010d:
            androidx.appcompat.mms.ApnException r0 = new androidx.appcompat.mms.ApnException     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            java.lang.String r2 = "No valid APN"
            r0.<init>((java.lang.String) r2)     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
            throw r0     // Catch:{ ApnException -> 0x0139, MmsNetworkException -> 0x012f, MmsHttpException -> 0x0120, Exception -> 0x0117 }
        L_0x0115:
            r0 = move-exception
            goto L_0x014c
        L_0x0117:
            r0 = move-exception
            r18 = r1
        L_0x011a:
            java.lang.String r1 = "MmsRequest: unexpected failure"
            android.util.Log.e(r10, r1, r0)     // Catch:{ all -> 0x0115 }
            goto L_0x0142
        L_0x0120:
            r0 = move-exception
            r18 = r1
        L_0x0123:
            java.lang.String r1 = "MmsRequest: HTTP or network I/O failure"
            android.util.Log.e(r10, r1, r0)     // Catch:{ all -> 0x0115 }
            r14 = 4
            int r0 = r0.getStatusCode()     // Catch:{ all -> 0x0115 }
            r13 = r0
            goto L_0x0142
        L_0x012f:
            r0 = move-exception
            r18 = r1
        L_0x0132:
            java.lang.String r1 = "MmsRequest: MMS network acquiring failure"
            android.util.Log.e(r10, r1, r0)     // Catch:{ all -> 0x0115 }
            r14 = 3
            goto L_0x0142
        L_0x0139:
            r0 = move-exception
            r18 = r1
        L_0x013c:
            java.lang.String r1 = "MmsRequest: APN failure"
            android.util.Log.e(r10, r1, r0)     // Catch:{ all -> 0x0115 }
            r14 = 2
        L_0x0142:
            r0 = r14
            r1 = r18
            r22.releaseNetwork()
        L_0x0148:
            r8.returnResult(r9, r0, r1, r13)
            return
        L_0x014c:
            r22.releaseNetwork()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.MmsRequest.execute(android.content.Context, androidx.appcompat.mms.MmsNetworkManager, androidx.appcompat.mms.ApnSettingsLoader, androidx.appcompat.mms.CarrierConfigValuesLoader, androidx.appcompat.mms.UserAgentInfoLoader):void");
    }

    /* access modifiers changed from: protected */
    public abstract String getHttpRequestUrl(ApnSettingsLoader.Apn apn);

    /* access modifiers changed from: package-private */
    public boolean getUseWakeLock() {
        return this.mUseWakeLock;
    }

    /* access modifiers changed from: protected */
    public abstract boolean loadRequest(Context context, Bundle bundle);

    /* access modifiers changed from: package-private */
    public void returnResult(Context context, int i, byte[] bArr, int i2) {
        if (this.mPendingIntent != null) {
            Intent intent = new Intent();
            if (bArr != null && !transferResponse(context, intent, bArr)) {
                i = 5;
            }
            if (i == 4 && i2 != 0) {
                intent.putExtra("android.telephony.extra.MMS_HTTP_STATUS", i2);
            }
            try {
                this.mPendingIntent.send(context, i, intent);
            } catch (PendingIntent.CanceledException e) {
                Log.e("MmsLib", "Sending pending intent canceled", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setUseWakeLock(boolean z) {
        this.mUseWakeLock = z;
    }

    /* access modifiers changed from: protected */
    public abstract boolean transferResponse(Context context, Intent intent, byte[] bArr);

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mUseWakeLock ? (byte) 1 : 0);
        parcel.writeString(this.mLocationUrl);
        parcel.writeParcelable(this.mPduUri, 0);
        parcel.writeParcelable(this.mPendingIntent, 0);
    }

    protected MmsRequest(Parcel parcel) {
        ClassLoader classLoader = MmsRequest.class.getClassLoader();
        this.mUseWakeLock = parcel.readByte() != 0;
        this.mLocationUrl = parcel.readString();
        this.mPduUri = (Uri) parcel.readParcelable(classLoader);
        this.mPendingIntent = (PendingIntent) parcel.readParcelable(classLoader);
    }
}
