package com.android.messaging.util.exif;

import android.util.Log;
import java.io.InputStream;

/* renamed from: com.android.messaging.util.exif.j */
class C1441j {
    private final C1435d mInterface;

    C1441j(C1435d dVar) {
        this.mInterface = dVar;
    }

    /* access modifiers changed from: protected */
    public C1433b read(InputStream inputStream) {
        C1440i a = C1440i.m3680a(inputStream, this.mInterface);
        C1433b bVar = new C1433b(a.mo8118sk());
        for (int next = a.next(); next != 5; next = a.next()) {
            if (next == 0) {
                bVar.mo8082a(new C1443l(a.mo8108Ak()));
            } else if (next == 1) {
                C1442k tag = a.getTag();
                if (!tag.hasValue()) {
                    a.mo8113d(tag);
                } else {
                    bVar.mo8078Qa(tag.mo8121Ek()).mo8150b(tag);
                }
            } else if (next == 2) {
                C1442k tag2 = a.getTag();
                if (tag2.getDataType() == 7) {
                    a.mo8112c(tag2);
                }
                bVar.mo8078Qa(tag2.mo8121Ek()).mo8150b(tag2);
            } else if (next == 3) {
                byte[] bArr = new byte[a.mo8119zk()];
                if (bArr.length == a.read(bArr)) {
                    bVar.mo8086p(bArr);
                } else {
                    Log.w("MessagingApp", "Failed to read the compressed thumbnail");
                }
            } else if (next == 4) {
                byte[] bArr2 = new byte[a.mo8110Ck()];
                if (bArr2.length == a.read(bArr2)) {
                    bVar.mo8083b(a.mo8109Bk(), bArr2);
                } else {
                    Log.w("MessagingApp", "Failed to read the strip bytes");
                }
            }
        }
        return bVar;
    }
}
