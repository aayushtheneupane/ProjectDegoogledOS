package com.android.messaging.datamodel.p038b;

import android.net.Uri;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1488za;
import com.android.vcard.VCardEntry;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.android.messaging.datamodel.b.M */
class C0850M implements C0870j {

    /* renamed from: XC */
    final CountDownLatch f1103XC;
    final /* synthetic */ C0851N this$0;

    public C0850M(C0851N n, CountDownLatch countDownLatch) {
        this.this$0 = n;
        this.f1103XC = countDownLatch;
    }

    /* renamed from: a */
    public void mo6118a(C0869i iVar) {
        Uri uri;
        List emailList;
        C1424b.m3584Gj();
        String displayName = iVar.getDisplayName();
        List photoList = iVar.getPhotoList();
        if (photoList == null || photoList.size() <= 0) {
            uri = null;
        } else {
            Iterator it = photoList.iterator();
            uri = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                byte[] bytes = ((VCardEntry.PhotoData) it.next()).getBytes();
                if (bytes != null) {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                    try {
                        Uri c = C1488za.m3872c(byteArrayInputStream);
                        if (c != null) {
                            uri = c;
                            break;
                        } else {
                            try {
                                byteArrayInputStream.close();
                            } catch (IOException unused) {
                            }
                            uri = c;
                        }
                    } finally {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
        }
        if (uri == null) {
            List phoneList = iVar.getPhoneList();
            String Ka = (phoneList == null || phoneList.size() <= 0) ? null : C1474sa.getDefault().mo8222Ka(((VCardEntry.PhoneData) phoneList.get(0)).getNumber());
            if (Ka == null && (emailList = iVar.getEmailList()) != null && emailList.size() > 0) {
                Ka = ((VCardEntry.EmailData) emailList.get(0)).getAddress();
            }
            uri = C1426c.m3598a((Uri) null, (CharSequence) displayName, Ka, (String) null);
        }
        this.this$0.f1104YC.add(new C0858V(iVar, uri));
    }

    public void onStart() {
    }
}
