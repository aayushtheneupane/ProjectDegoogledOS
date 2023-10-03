package com.android.messaging.datamodel;

import com.android.messaging.util.C1424b;
import java.util.Comparator;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.datamodel.q */
class C0956q implements Comparator {

    /* renamed from: Px */
    final /* synthetic */ C0027n f1375Px;

    C0956q(C0957r rVar, C0027n nVar) {
        this.f1375Px = nVar;
    }

    public int compare(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        Object[] objArr2 = (Object[]) obj2;
        String str = (String) objArr[6];
        String str2 = (String) objArr2[6];
        int i = 0;
        C1424b.m3592ia(this.f1375Px.containsKey(str) && this.f1375Px.containsKey(str2));
        int intValue = ((Integer) this.f1375Px.get(str)).intValue();
        int intValue2 = ((Integer) this.f1375Px.get(str2)).intValue();
        if (intValue < intValue2) {
            return -1;
        }
        if (intValue <= intValue2) {
            int intValue3 = ((Integer) objArr[4]).intValue();
            int intValue4 = ((Integer) objArr2[4]).intValue();
            if (!(intValue3 == 2 && intValue4 == 2)) {
                if (intValue3 == 2) {
                    return -1;
                }
                if (intValue4 != 2) {
                    if (intValue3 < intValue4) {
                        i = -1;
                    } else if (intValue3 != intValue4) {
                        i = 1;
                    }
                }
            }
            return i;
        }
        return 1;
    }
}
