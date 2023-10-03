package kotlin.jvm.internal;

import java.util.Set;
import kotlin.jvm.internal.markers.KMappedMarker;

public class TypeIntrinsics {
    private static <T extends Throwable> T sanitizeStackTrace(T t) {
        Intrinsics.sanitizeStackTrace(t, TypeIntrinsics.class.getName());
        return t;
    }

    public static void throwCce(Object obj, String str) {
        String name = obj == null ? "null" : obj.getClass().getName();
        throwCce(name + " cannot be cast to " + str);
        throw null;
    }

    public static void throwCce(String str) {
        throwCce(new ClassCastException(str));
        throw null;
    }

    public static ClassCastException throwCce(ClassCastException classCastException) {
        sanitizeStackTrace(classCastException);
        throw classCastException;
    }

    public static Iterable asMutableIterable(Object obj) {
        if (!(obj instanceof KMappedMarker)) {
            return castToIterable(obj);
        }
        throwCce(obj, "kotlin.collections.MutableIterable");
        throw null;
    }

    public static Iterable castToIterable(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e) {
            throwCce(e);
            throw null;
        }
    }

    public static Set asMutableSet(Object obj) {
        if (!(obj instanceof KMappedMarker)) {
            return castToSet(obj);
        }
        throwCce(obj, "kotlin.collections.MutableSet");
        throw null;
    }

    public static Set castToSet(Object obj) {
        try {
            return (Set) obj;
        } catch (ClassCastException e) {
            throwCce(e);
            throw null;
        }
    }
}
