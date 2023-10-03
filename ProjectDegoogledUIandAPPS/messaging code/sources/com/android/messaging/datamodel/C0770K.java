package com.android.messaging.datamodel;

import android.telephony.SmsMessage;
import com.android.messaging.sms.C1024t;

/* renamed from: com.android.messaging.datamodel.K */
public class C0770K {

    /* renamed from: Ux */
    private int f1010Ux;

    /* renamed from: ky */
    private boolean f1011ky;

    /* renamed from: ly */
    private int f1012ly = Integer.MAX_VALUE;

    /* renamed from: c */
    public void mo5883c(int i, String str) {
        boolean z = false;
        int[] calculateLength = SmsMessage.calculateLength(str, false);
        this.f1010Ux = calculateLength[0];
        this.f1012ly = calculateLength[2];
        C1024t tVar = C1024t.get(i);
        if (tVar.mo6846ri() || tVar.mo6850vi()) {
            int yi = tVar.mo6853yi();
            if (yi > 0 && this.f1010Ux > yi) {
                z = true;
            }
            this.f1011ky = z;
        } else {
            if (this.f1010Ux > 1) {
                z = true;
            }
            this.f1011ky = z;
        }
        int xi = tVar.mo6852xi();
        if (xi > 0) {
            int i2 = calculateLength[1];
            if (this.f1012ly + i2 < 140) {
                xi /= 2;
            }
            if (i2 > xi) {
                this.f1011ky = true;
            }
        }
    }

    /* renamed from: he */
    public int mo5884he() {
        return this.f1012ly;
    }

    /* renamed from: ie */
    public boolean mo5885ie() {
        return this.f1011ky;
    }

    /* renamed from: je */
    public int mo5886je() {
        return this.f1010Ux;
    }
}
