package com.android.messaging.util;

import java.util.ArrayList;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.Q */
public class C1413Q {

    /* renamed from: dK */
    private final List f2217dK = new ArrayList();

    private C1413Q(C1412P p) {
        this.f2217dK.add(p);
    }

    /* renamed from: a */
    public static C1413Q m3556a(C1412P p) {
        return new C1413Q(p);
    }

    /* renamed from: b */
    public C1413Q mo8045b(C1412P p) {
        C1424b.m3591ha(this.f2217dK.isEmpty());
        this.f2217dK.add(p);
        return this;
    }

    public Object execute(Object obj) {
        String str;
        int size = this.f2217dK.size();
        int i = 0;
        while (i < size) {
            C1412P p = (C1412P) this.f2217dK.get(i);
            try {
                return p.execute(obj);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Exceptions occured when executing strategy ");
                sb.append(p);
                if (i < size - 1) {
                    StringBuilder Pa = C0632a.m1011Pa(", attempting fallback ");
                    Pa.append(this.f2217dK.get(i + 1));
                    str = Pa.toString();
                } else {
                    str = ", and running out of fallbacks.";
                }
                sb.append(str);
                C1430e.m3623e("MessagingApp", sb.toString(), e);
                i++;
            }
        }
        return null;
    }
}
