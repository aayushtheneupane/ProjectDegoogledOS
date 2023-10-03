package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.base.z */
public final class C1551z {

    /* renamed from: QM */
    private final C1504A f2419QM;

    /* renamed from: RM */
    private final String f2420RM;

    /* synthetic */ C1551z(C1504A a, String str, C1548w wVar) {
        this.f2419QM = a;
        if (str != null) {
            this.f2420RM = str;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public StringBuilder mo8565a(StringBuilder sb, Map map) {
        try {
            mo8564a((Appendable) sb, map.entrySet().iterator());
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public Appendable mo8564a(Appendable appendable, Iterator it) {
        if (appendable != null) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                appendable.append(this.f2419QM.toString(entry.getKey()));
                appendable.append(this.f2420RM);
                appendable.append(this.f2419QM.toString(entry.getValue()));
                while (it.hasNext()) {
                    appendable.append(this.f2419QM.separator);
                    Map.Entry entry2 = (Map.Entry) it.next();
                    appendable.append(this.f2419QM.toString(entry2.getKey()));
                    appendable.append(this.f2420RM);
                    appendable.append(this.f2419QM.toString(entry2.getValue()));
                }
            }
            return appendable;
        }
        throw new NullPointerException();
    }
}
