package com.android.messaging.sms;

import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.sms.B */
public class C1003B {

    /* renamed from: eF */
    private int f1458eF;

    /* renamed from: fF */
    private int f1459fF;

    public C1003B(int i) {
        C1424b.m3592ia(i > 0);
        this.f1458eF = i;
        this.f1459fF = 0;
    }

    /* renamed from: Ha */
    public void mo6782Ha(int i) {
        int i2 = 1;
        this.f1458eF--;
        if (i == -1) {
            i2 = 0;
        } else if (i != 4) {
            if (!(i == 1 || i == 2)) {
                C1430e.m3622e("MessagingApp", "SmsSender: Unexpected sent intent resultCode = " + i);
            }
            i2 = 2;
        }
        if (i2 > this.f1459fF) {
            this.f1459fF = i2;
        }
    }

    /* renamed from: Ni */
    public int mo6783Ni() {
        return this.f1459fF;
    }

    /* renamed from: Oi */
    public boolean mo6784Oi() {
        return this.f1458eF > 0;
    }

    public String toString() {
        return "SendResult:" + "Pending=" + this.f1458eF + "," + "HighestFailureLevel=" + this.f1459fF;
    }
}
