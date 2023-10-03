package p003j$.util;

import java.util.ConcurrentModificationException;
import java.util.Map;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: j$.util.Map$$CC */
public abstract /* synthetic */ class Map$$CC {
    public static Object getOrDefault$$dflt$$(Map map, Object obj, Object obj2) {
        Object obj3 = map.get(obj);
        return (obj3 != null || map.containsKey(obj)) ? obj3 : obj2;
    }

    public static void forEach$$dflt$$(Map map, BiConsumer biConsumer) {
        biConsumer.getClass();
        for (Map.Entry entry : map.entrySet()) {
            try {
                biConsumer.accept(entry.getKey(), entry.getValue());
            } catch (IllegalStateException e) {
                throw new ConcurrentModificationException(e);
            }
        }
    }

    public static void replaceAll$$dflt$$(Map map, BiFunction biFunction) {
        biFunction.getClass();
        for (Map.Entry entry : map.entrySet()) {
            try {
                try {
                    entry.setValue(biFunction.apply(entry.getKey(), entry.getValue()));
                } catch (IllegalStateException e) {
                    throw new ConcurrentModificationException(e);
                }
            } catch (IllegalStateException e2) {
                throw new ConcurrentModificationException(e2);
            }
        }
    }

    public static Object putIfAbsent$$dflt$$(Map map, Object obj, Object obj2) {
        Object obj3 = map.get(obj);
        return obj3 == null ? map.put(obj, obj2) : obj3;
    }

    public static boolean remove$$dflt$$(Map map, Object obj, Object obj2) {
        Object obj3 = map.get(obj);
        if (!Objects.equals(obj3, obj2)) {
            return false;
        }
        if (obj3 == null && !map.containsKey(obj)) {
            return false;
        }
        map.remove(obj);
        return true;
    }

    public static boolean replace$$dflt$$(Map map, Object obj, Object obj2, Object obj3) {
        Object obj4 = map.get(obj);
        if (!Objects.equals(obj4, obj2)) {
            return false;
        }
        if (obj4 == null && !map.containsKey(obj)) {
            return false;
        }
        map.put(obj, obj3);
        return true;
    }

    public static Object replace$$dflt$$(Map map, Object obj, Object obj2) {
        Object obj3 = map.get(obj);
        return (obj3 != null || map.containsKey(obj)) ? map.put(obj, obj2) : obj3;
    }

    public static Object computeIfAbsent$$dflt$$(Map map, Object obj, Function function) {
        Object apply;
        function.getClass();
        Object obj2 = map.get(obj);
        if (obj2 != null || (apply = function.apply(obj)) == null) {
            return obj2;
        }
        map.put(obj, apply);
        return apply;
    }

    public static Object computeIfPresent$$dflt$$(Map map, Object obj, BiFunction biFunction) {
        biFunction.getClass();
        Object obj2 = map.get(obj);
        if (obj2 != null) {
            Object apply = biFunction.apply(obj, obj2);
            if (apply != null) {
                map.put(obj, apply);
                return apply;
            }
            map.remove(obj);
        }
        return null;
    }

    public static Object compute$$dflt$$(Map map, Object obj, BiFunction biFunction) {
        biFunction.getClass();
        Object obj2 = map.get(obj);
        Object apply = biFunction.apply(obj, obj2);
        if (apply != null) {
            map.put(obj, apply);
            return apply;
        } else if (obj2 == null && !map.containsKey(obj)) {
            return null;
        } else {
            map.remove(obj);
            return null;
        }
    }

    public static Object merge$$dflt$$(Map map, Object obj, Object obj2, BiFunction biFunction) {
        biFunction.getClass();
        obj2.getClass();
        Object obj3 = map.get(obj);
        if (obj3 != null) {
            obj2 = biFunction.apply(obj3, obj2);
        }
        if (obj2 == null) {
            map.remove(obj);
        } else {
            map.put(obj, obj2);
        }
        return obj2;
    }
}
