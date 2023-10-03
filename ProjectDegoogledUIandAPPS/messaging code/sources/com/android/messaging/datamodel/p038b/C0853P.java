package com.android.messaging.datamodel.p038b;

import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.P */
public class C0853P extends C0846I {

    /* renamed from: zl */
    private final List f1107zl;

    public C0853P(String str, List list) {
        super(str);
        this.f1107zl = list;
    }

    /* renamed from: Wh */
    public List mo6125Wh() {
        return this.f1107zl;
    }

    /* access modifiers changed from: protected */
    public void close() {
        for (C0858V close : this.f1107zl) {
            close.close();
        }
    }

    public int getMediaSize() {
        return 0;
    }
}
