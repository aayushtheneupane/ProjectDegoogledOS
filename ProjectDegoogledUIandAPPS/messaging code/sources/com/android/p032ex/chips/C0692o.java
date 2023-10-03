package com.android.p032ex.chips;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.android.ex.chips.o */
class C0692o extends AsyncTask {

    /* renamed from: td */
    final /* synthetic */ Uri f809td;
    final /* synthetic */ C0696q this$0;
    final /* synthetic */ C0706x val$callback;
    final /* synthetic */ C0699ra val$entry;

    C0692o(C0696q qVar, Uri uri, C0699ra raVar, C0706x xVar) {
        this.this$0 = qVar;
        this.f809td = uri;
        this.val$entry = raVar;
        this.val$callback = xVar;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        InputStream openInputStream;
        Void[] voidArr = (Void[]) objArr;
        Cursor query = this.this$0.mContentResolver.query(this.f809td, C0694p.f810Wu, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getBlob(0);
                }
                query.close();
            } finally {
                query.close();
            }
        } else {
            try {
                openInputStream = this.this$0.mContentResolver.openInputStream(this.f809td);
                if (openInputStream != null) {
                    byte[] bArr = new byte[16384];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            openInputStream.close();
                            return byteArrayOutputStream.toByteArray();
                        }
                    }
                }
            } catch (IOException unused) {
            } catch (Throwable th) {
                openInputStream.close();
                throw th;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        byte[] bArr = (byte[]) obj;
        this.val$entry.mo5658j(bArr);
        if (bArr != null) {
            this.this$0.f811fv.put(this.f809td, bArr);
            C0706x xVar = this.val$callback;
            if (xVar != null) {
                xVar.onPhotoBytesAsynchronouslyPopulated();
                return;
            }
            return;
        }
        C0706x xVar2 = this.val$callback;
        if (xVar2 != null) {
            xVar2.onPhotoBytesAsyncLoadFailed();
        }
    }
}
