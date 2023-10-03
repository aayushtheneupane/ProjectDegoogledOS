package p003j$.util;

import java.util.Map;

/* renamed from: j$.util.Map$$Dispatch */
public abstract /* synthetic */ class Map$$Dispatch {
    public static Object putIfAbsent(Map map, Object obj, Object obj2) {
        return map instanceof Map ? ((Map) map).putIfAbsent(obj, obj2) : Map$$CC.putIfAbsent$$dflt$$(map, obj, obj2);
    }
}
