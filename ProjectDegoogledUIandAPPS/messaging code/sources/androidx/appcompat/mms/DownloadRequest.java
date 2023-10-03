package androidx.appcompat.mms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.appcompat.mms.ApnSettingsLoader;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class DownloadRequest extends MmsRequest {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DownloadRequest createFromParcel(Parcel parcel) {
            return new DownloadRequest(parcel, (C01761) null);
        }

        public DownloadRequest[] newArray(int i) {
            return new DownloadRequest[i];
        }
    };

    DownloadRequest(String str, Uri uri, PendingIntent pendingIntent) {
        super(str, uri, pendingIntent);
    }

    /* access modifiers changed from: protected */
    public byte[] doHttp(Context context, MmsNetworkManager mmsNetworkManager, ApnSettingsLoader.Apn apn, Bundle bundle, String str, String str2) {
        ApnSettingsLoader.Apn apn2 = apn;
        return mmsNetworkManager.getHttpClient().execute(getHttpRequestUrl(apn), (byte[]) null, "GET", !TextUtils.isEmpty(apn.getMmsProxy()), apn.getMmsProxy(), apn.getMmsProxyPort(), bundle, str, str2);
    }

    /* access modifiers changed from: protected */
    public String getHttpRequestUrl(ApnSettingsLoader.Apn apn) {
        return this.mLocationUrl;
    }

    /* access modifiers changed from: protected */
    public boolean loadRequest(Context context, Bundle bundle) {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean transferResponse(Context context, Intent intent, byte[] bArr) {
        return writePduToContentUri(context, this.mPduUri, bArr);
    }

    public boolean writePduToContentUri(final Context context, final Uri uri, final byte[] bArr) {
        if (!(uri == null || bArr == null)) {
            Future submit = this.mPduTransferExecutor.submit(new Callable() {
                /* JADX WARNING: Removed duplicated region for block: B:19:0x0039 A[SYNTHETIC, Splitter:B:19:0x0039] */
                /* JADX WARNING: Removed duplicated region for block: B:24:0x003f A[SYNTHETIC, Splitter:B:24:0x003f] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Boolean call() {
                    /*
                        r4 = this;
                        r0 = 0
                        android.content.Context r1 = r3     // Catch:{ IOException -> 0x002a }
                        android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ IOException -> 0x002a }
                        android.net.Uri r2 = r4     // Catch:{ IOException -> 0x002a }
                        java.lang.String r3 = "w"
                        android.os.ParcelFileDescriptor r1 = r1.openFileDescriptor(r2, r3)     // Catch:{ IOException -> 0x002a }
                        android.os.ParcelFileDescriptor$AutoCloseOutputStream r2 = new android.os.ParcelFileDescriptor$AutoCloseOutputStream     // Catch:{ IOException -> 0x002a }
                        r2.<init>(r1)     // Catch:{ IOException -> 0x002a }
                        byte[] r4 = r5     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
                        r2.write(r4)     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
                        r4 = 1
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
                        r2.close()     // Catch:{ IOException -> 0x0021 }
                    L_0x0021:
                        return r4
                    L_0x0022:
                        r4 = move-exception
                        r0 = r2
                        goto L_0x003d
                    L_0x0025:
                        r4 = move-exception
                        r0 = r2
                        goto L_0x002b
                    L_0x0028:
                        r4 = move-exception
                        goto L_0x003d
                    L_0x002a:
                        r4 = move-exception
                    L_0x002b:
                        java.lang.String r1 = "MmsLib"
                        java.lang.String r2 = "Writing PDU to downloader: IO exception"
                        android.util.Log.e(r1, r2, r4)     // Catch:{ all -> 0x0028 }
                        r4 = 0
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0028 }
                        if (r0 == 0) goto L_0x003c
                        r0.close()     // Catch:{ IOException -> 0x003c }
                    L_0x003c:
                        return r4
                    L_0x003d:
                        if (r0 == 0) goto L_0x0042
                        r0.close()     // Catch:{ IOException -> 0x0042 }
                    L_0x0042:
                        throw r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.DownloadRequest.C01761.call():java.lang.Boolean");
                }
            });
            try {
                return ((Boolean) submit.get(30000, TimeUnit.MILLISECONDS)).booleanValue();
            } catch (Exception unused) {
                submit.cancel(true);
            }
        }
        return false;
    }

    private DownloadRequest(Parcel parcel) {
        super(parcel);
    }

    /* synthetic */ DownloadRequest(Parcel parcel, C01761 r2) {
        super(parcel);
    }
}
