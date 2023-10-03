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

class SendRequest extends MmsRequest {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SendRequest createFromParcel(Parcel parcel) {
            return new SendRequest(parcel, (C01821) null);
        }

        public SendRequest[] newArray(int i) {
            return new SendRequest[i];
        }
    };
    private static final int MAX_SEND_RESPONSE_SIZE = 1024000;
    private byte[] mPduData;

    SendRequest(String str, Uri uri, PendingIntent pendingIntent) {
        super(str, uri, pendingIntent);
    }

    /* access modifiers changed from: protected */
    public byte[] doHttp(Context context, MmsNetworkManager mmsNetworkManager, ApnSettingsLoader.Apn apn, Bundle bundle, String str, String str2) {
        ApnSettingsLoader.Apn apn2 = apn;
        return mmsNetworkManager.getHttpClient().execute(getHttpRequestUrl(apn), this.mPduData, "POST", !TextUtils.isEmpty(apn.getMmsProxy()), apn.getMmsProxy(), apn.getMmsProxyPort(), bundle, str, str2);
    }

    /* access modifiers changed from: protected */
    public String getHttpRequestUrl(ApnSettingsLoader.Apn apn) {
        return !TextUtils.isEmpty(this.mLocationUrl) ? this.mLocationUrl : apn.getMmsc();
    }

    /* access modifiers changed from: protected */
    public boolean loadRequest(Context context, Bundle bundle) {
        this.mPduData = readPduFromContentUri(context, this.mPduUri, bundle.getInt(CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_SIZE, CarrierConfigValuesLoader.CONFIG_MAX_MESSAGE_SIZE_DEFAULT));
        return this.mPduData != null;
    }

    public byte[] readPduFromContentUri(final Context context, final Uri uri, final int i) {
        if (uri == null) {
            return null;
        }
        Future submit = this.mPduTransferExecutor.submit(new Callable() {
            /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A[SYNTHETIC, Splitter:B:34:0x0054] */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x005b A[SYNTHETIC, Splitter:B:40:0x005b] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public byte[] call() {
                /*
                    r6 = this;
                    java.lang.String r0 = "MmsLib"
                    r1 = 0
                    android.content.Context r2 = r3     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    android.net.Uri r3 = r4     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    java.lang.String r4 = "r"
                    android.os.ParcelFileDescriptor r2 = r2.openFileDescriptor(r3, r4)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    android.os.ParcelFileDescriptor$AutoCloseInputStream r3 = new android.os.ParcelFileDescriptor$AutoCloseInputStream     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    r3.<init>(r2)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
                    int r2 = r5     // Catch:{ IOException -> 0x0046 }
                    int r2 = r2 + 1
                    byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0046 }
                    int r4 = r5     // Catch:{ IOException -> 0x0046 }
                    int r4 = r4 + 1
                    r5 = 0
                    int r4 = r3.read(r2, r5, r4)     // Catch:{ IOException -> 0x0046 }
                    if (r4 > 0) goto L_0x0030
                    java.lang.String r6 = "Reading PDU from sender: empty PDU"
                    android.util.Log.e(r0, r6)     // Catch:{ IOException -> 0x0046 }
                    r3.close()     // Catch:{ IOException -> 0x002f }
                L_0x002f:
                    return r1
                L_0x0030:
                    int r6 = r5     // Catch:{ IOException -> 0x0046 }
                    if (r4 <= r6) goto L_0x003d
                    java.lang.String r6 = "Reading PDU from sender: PDU too large"
                    android.util.Log.e(r0, r6)     // Catch:{ IOException -> 0x0046 }
                    r3.close()     // Catch:{ IOException -> 0x003c }
                L_0x003c:
                    return r1
                L_0x003d:
                    byte[] r6 = new byte[r4]     // Catch:{ IOException -> 0x0046 }
                    java.lang.System.arraycopy(r2, r5, r6, r5, r4)     // Catch:{ IOException -> 0x0046 }
                    r3.close()     // Catch:{ IOException -> 0x0045 }
                L_0x0045:
                    return r6
                L_0x0046:
                    r6 = move-exception
                    goto L_0x004d
                L_0x0048:
                    r6 = move-exception
                    r3 = r1
                    goto L_0x0059
                L_0x004b:
                    r6 = move-exception
                    r3 = r1
                L_0x004d:
                    java.lang.String r2 = "Reading PDU from sender: IO exception"
                    android.util.Log.e(r0, r2, r6)     // Catch:{ all -> 0x0058 }
                    if (r3 == 0) goto L_0x0057
                    r3.close()     // Catch:{ IOException -> 0x0057 }
                L_0x0057:
                    return r1
                L_0x0058:
                    r6 = move-exception
                L_0x0059:
                    if (r3 == 0) goto L_0x005e
                    r3.close()     // Catch:{ IOException -> 0x005e }
                L_0x005e:
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.mms.SendRequest.C01821.call():byte[]");
            }
        });
        try {
            return (byte[]) submit.get(30000, TimeUnit.MILLISECONDS);
        } catch (Exception unused) {
            submit.cancel(true);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean transferResponse(Context context, Intent intent, byte[] bArr) {
        if (bArr == null || intent == null) {
            return true;
        }
        if (bArr.length > MAX_SEND_RESPONSE_SIZE) {
            return false;
        }
        intent.putExtra("android.telephony.extra.MMS_DATA", bArr);
        return true;
    }

    private SendRequest(Parcel parcel) {
        super(parcel);
    }

    /* synthetic */ SendRequest(Parcel parcel, C01821 r2) {
        super(parcel);
    }
}
