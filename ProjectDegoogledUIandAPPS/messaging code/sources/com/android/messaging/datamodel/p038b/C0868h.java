package com.android.messaging.datamodel.p038b;

import android.content.Context;
import com.google.common.base.C1504A;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.h */
public abstract class C0868h extends C0880t {
    protected final List mDescriptors;
    private final String mKey;

    public C0868h(List list, int i, int i2) {
        super(i, i2, -1, -1, false, false, 0, 0);
        this.mDescriptors = list;
        String[] strArr = new String[list.size()];
        for (int i3 = 0; i3 < list.size(); i3++) {
            strArr[i3] = ((C0880t) list.get(i3)).getKey();
        }
        this.mKey = C1504A.m3943Ua(",").mo8510Vk().mo8516c(strArr);
    }

    public String getKey() {
        return this.mKey;
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        return new C0867g(context, (C0862b) this);
    }
}
