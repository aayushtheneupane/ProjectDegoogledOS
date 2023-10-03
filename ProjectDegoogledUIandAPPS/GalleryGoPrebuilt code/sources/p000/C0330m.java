package p000;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: m */
/* compiled from: PG */
final class C0330m {

    /* renamed from: a */
    public final Map f15220a = new HashMap();

    /* renamed from: b */
    public final Map f15221b;

    public C0330m(Map map) {
        this.f15221b = map;
        for (Map.Entry entry : map.entrySet()) {
            C0546u uVar = (C0546u) entry.getValue();
            List list = (List) this.f15220a.get(uVar);
            if (list == null) {
                list = new ArrayList();
                this.f15220a.put(uVar, list);
            }
            list.add(entry.getKey());
        }
    }

    /* renamed from: a */
    public static void m14660a(List list, C0681z zVar, C0546u uVar, Object obj) {
        if (list != null) {
            int size = list.size() - 1;
            while (size >= 0) {
                C0357n nVar = (C0357n) list.get(size);
                try {
                    int i = nVar.f15261a;
                    if (i == 0) {
                        nVar.f15262b.invoke(obj, new Object[0]);
                    } else if (i != 1) {
                        nVar.f15262b.invoke(obj, new Object[]{zVar, uVar});
                    } else {
                        nVar.f15262b.invoke(obj, new Object[]{zVar});
                    }
                    size--;
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Failed to call observer method", e.getCause());
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }
}
