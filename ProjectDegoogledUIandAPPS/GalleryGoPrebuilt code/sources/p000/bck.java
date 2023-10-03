package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: bck */
/* compiled from: PG */
public final class bck {

    /* renamed from: a */
    private final List f2055a = new ArrayList();

    /* renamed from: a */
    public final synchronized bci mo1808a(Class cls, Class cls2) {
        if (!cls2.isAssignableFrom(cls)) {
            List list = this.f2055a;
            int size = list.size();
            int i = 0;
            while (i < size) {
                bcj bcj = (bcj) list.get(i);
                i++;
                if (bcj.mo1807a(cls, cls2)) {
                    return bcj.f2052a;
                }
            }
            String valueOf = String.valueOf(cls);
            String valueOf2 = String.valueOf(cls2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47 + String.valueOf(valueOf2).length());
            sb.append("No transcoder registered to transcode from ");
            sb.append(valueOf);
            sb.append(" to ");
            sb.append(valueOf2);
            throw new IllegalArgumentException(sb.toString());
        }
        return bcl.f2056a;
    }

    /* renamed from: b */
    public final synchronized List mo1810b(Class cls, Class cls2) {
        ArrayList arrayList = new ArrayList();
        if (!cls2.isAssignableFrom(cls)) {
            List list = this.f2055a;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((bcj) list.get(i)).mo1807a(cls, cls2)) {
                    arrayList.add(cls2);
                }
            }
            return arrayList;
        }
        arrayList.add(cls2);
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized void mo1809a(Class cls, Class cls2, bci bci) {
        this.f2055a.add(new bcj(cls, cls2, bci));
    }
}
