package p000;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

/* renamed from: ila */
/* compiled from: PG */
final class ila {

    /* renamed from: a */
    public static final imu f14428a = m13910a(false);

    /* renamed from: b */
    public static final imu f14429b = m13910a(true);

    /* renamed from: c */
    public static final imu f14430c = new imu((byte[]) null);

    /* renamed from: d */
    private static final Class f14431d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable th) {
            cls = null;
        }
        f14431d = cls;
    }

    /* renamed from: c */
    static int m13926c(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return size * iie.m13431k(i);
        }
        return 0;
    }

    /* renamed from: j */
    static int m13949j(List list) {
        return list.size();
    }

    /* renamed from: b */
    static int m13919b(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int f = size * iie.m13420f(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            f += iie.m13409b((ihw) list.get(i2));
        }
        return f;
    }

    /* renamed from: d */
    static int m13930d(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13931d(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: d */
    static int m13931d(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            int i2 = 0;
            while (i < size) {
                i2 += iie.m13424g(iiy.mo8800c(i));
                i++;
            }
            return i2;
        }
        int i3 = 0;
        while (i < size) {
            i3 += iie.m13424g(((Integer) list.get(i)).intValue());
            i++;
        }
        return i3;
    }

    /* renamed from: e */
    static int m13933e(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return size * iie.m13433m(i);
        }
        return 0;
    }

    /* renamed from: h */
    static int m13943h(List list) {
        return list.size() << 2;
    }

    /* renamed from: f */
    static int m13936f(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return size * iie.m13434n(i);
        }
        return 0;
    }

    /* renamed from: i */
    static int m13946i(List list) {
        return list.size() << 3;
    }

    /* renamed from: b */
    static int m13920b(int i, List list, iky iky) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += iie.m13407b(i, (ikf) list.get(i3), iky);
        }
        return i2;
    }

    /* renamed from: g */
    static int m13939g(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13934e(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: e */
    static int m13934e(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            i = 0;
            while (i2 < size) {
                i += iie.m13424g(iiy.mo8800c(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + iie.m13424g(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* renamed from: h */
    static int m13942h(int i, List list) {
        if (list.size() != 0) {
            return m13909a(list) + (list.size() * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: a */
    static int m13909a(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            int i2 = 0;
            while (i < size) {
                i2 += iie.m13416d(ijs.mo8827c(i));
                i++;
            }
            return i2;
        }
        int i3 = 0;
        while (i < size) {
            i3 += iie.m13416d(((Long) list.get(i)).longValue());
            i++;
        }
        return i3;
    }

    /* renamed from: a */
    static int m13906a(int i, Object obj, iky iky) {
        if (obj instanceof ijm) {
            return iie.m13402a(i, (ijm) obj);
        }
        return iie.m13420f(i) + iie.m13404a((ikf) obj, iky);
    }

    /* renamed from: a */
    static int m13908a(int i, List list, iky iky) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int f = iie.m13420f(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof ijm) {
                i2 = iie.m13403a((ijm) obj);
            } else {
                i2 = iie.m13404a((ikf) obj, iky);
            }
            f += i2;
        }
        return f;
    }

    /* renamed from: i */
    static int m13945i(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13940g(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: g */
    static int m13940g(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            i = 0;
            while (i2 < size) {
                i += iie.m13428i(iiy.mo8800c(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + iie.m13428i(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* renamed from: j */
    static int m13948j(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13927c(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: c */
    static int m13927c(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            i = 0;
            while (i2 < size) {
                i += iie.m13419e(ijs.mo8827c(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + iie.m13419e(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* renamed from: a */
    static int m13907a(int i, List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int f = iie.m13420f(i) * size;
        if (!(list instanceof ijo)) {
            while (i3 < size) {
                Object obj = list.get(i3);
                if (obj instanceof ihw) {
                    i2 = iie.m13409b((ihw) obj);
                } else {
                    i2 = iie.m13411b((String) obj);
                }
                f += i2;
                i3++;
            }
        } else {
            ijo ijo = (ijo) list;
            while (i3 < size) {
                Object c = ijo.mo8819c(i3);
                f += c instanceof ihw ? iie.m13409b((ihw) c) : iie.m13411b((String) c);
                i3++;
            }
        }
        return f;
    }

    /* renamed from: k */
    static int m13951k(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13937f(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: f */
    static int m13937f(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof iiy) {
            iiy iiy = (iiy) list;
            i = 0;
            while (i2 < size) {
                i += iie.m13426h(iiy.mo8800c(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + iie.m13426h(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    /* renamed from: l */
    static int m13953l(int i, List list) {
        int size = list.size();
        if (size != 0) {
            return m13921b(list) + (size * iie.m13420f(i));
        }
        return 0;
    }

    /* renamed from: b */
    static int m13921b(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof ijs) {
            ijs ijs = (ijs) list;
            i = 0;
            while (i2 < size) {
                i += iie.m13416d(ijs.mo8827c(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + iie.m13416d(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    /* renamed from: a */
    static Object m13912a(int i, List list, ijb ijb, Object obj) {
        if (ijb == null) {
            return obj;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue = ((Integer) it.next()).intValue();
                if (!ijb.mo2351a(intValue)) {
                    obj = m13911a(i, intValue, obj);
                    it.remove();
                }
            }
        } else {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = ((Integer) list.get(i3)).intValue();
                if (!ijb.mo2351a(intValue2)) {
                    obj = m13911a(i, intValue2, obj);
                } else {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj;
            }
        }
        return obj;
    }

    /* renamed from: a */
    private static imu m13910a(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            cls = null;
        }
        if (cls != null) {
            try {
                return (imu) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
            } catch (Throwable th2) {
            }
        }
        return null;
    }

    /* renamed from: b */
    static void m13925b(Object obj, Object obj2) {
        iim a = imi.m14098a(obj2);
        if (!a.mo8725a()) {
            iim b = imi.m14114b(obj);
            for (int i = 0; i < a.f14255a.mo8916a(); i++) {
                b.mo8724a(a.f14255a.mo8919b(i));
            }
            for (Map.Entry a2 : a.f14255a.mo8918b()) {
                b.mo8724a(a2);
            }
        }
    }

    /* renamed from: a */
    static void m13916a(ijz ijz, Object obj, Object obj2, long j) {
        ilv.m14036a(obj, j, ijz.mo8853a(ilv.m14048f(obj, j), ilv.m14048f(obj2, j)));
    }

    /* renamed from: c */
    static void m13929c(Object obj, Object obj2) {
        ilm a = imu.m14135a(obj);
        ilm a2 = imu.m14135a(obj2);
        if (!a2.equals(ilm.f14449a)) {
            a = ilm.m13975a(a, a2);
        }
        imu.m14139a(obj, a);
    }

    /* renamed from: a */
    public static void m13917a(Class cls) {
        Class cls2;
        if (!iix.class.isAssignableFrom(cls) && (cls2 = f14431d) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* renamed from: a */
    static boolean m13918a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    static Object m13911a(int i, int i2, Object obj) {
        if (obj == null) {
            obj = ilm.m13974a();
        }
        ((ilm) obj).mo8943a(imd.m14073a(i, 0), (Object) Long.valueOf((long) i2));
        return obj;
    }

    /* renamed from: n */
    public static void m13956n(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Boolean) list.get(i3)).booleanValue();
                    i4++;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8653a(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8661a(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
        }
    }

    /* renamed from: b */
    public static void m13922b(int i, List list, ime ime) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((iif) ime).f14238a.mo8657a(i, (ihw) list.get(i2));
            }
        }
    }

    /* renamed from: a */
    public static void m13915a(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Double) list.get(i3)).doubleValue();
                    i4 += 8;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8676a(((Double) list.get(i2)).doubleValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8678a(i, ((Double) list.get(i2)).doubleValue());
                i2++;
            }
        }
    }

    /* renamed from: m */
    public static void m13955m(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13424g(((Integer) list.get(i4)).intValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8654a(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8669b(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: k */
    public static void m13952k(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Integer) list.get(i3)).intValue();
                    i4 += 4;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8673c(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8675d(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: f */
    public static void m13938f(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Long) list.get(i3)).longValue();
                    i4 += 8;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8672b(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8670b(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    /* renamed from: b */
    public static void m13924b(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Float) list.get(i3)).floatValue();
                    i4 += 4;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8677a(((Float) list.get(i2)).floatValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8679a(i, ((Float) list.get(i2)).floatValue());
                i2++;
            }
        }
    }

    /* renamed from: b */
    public static void m13923b(int i, List list, ime ime, iky iky) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((iif) ime).mo8698b(i, list.get(i2), iky);
            }
        }
    }

    /* renamed from: h */
    public static void m13944h(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13424g(((Integer) list.get(i4)).intValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8654a(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8669b(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: c */
    public static void m13928c(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13416d(((Long) list.get(i4)).longValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8662a(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8656a(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    /* renamed from: a */
    public static void m13914a(int i, List list, ime ime, iky iky) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((iif) ime).mo8693a(i, list.get(i2), iky);
            }
        }
    }

    /* renamed from: l */
    public static void m13954l(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Integer) list.get(i3)).intValue();
                    i4 += 4;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8673c(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8675d(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: g */
    public static void m13941g(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    ((Long) list.get(i3)).longValue();
                    i4 += 8;
                    i3++;
                    boolean z2 = iie.f14235a;
                }
                iif.f14238a.mo8668b(i4);
                while (i2 < list.size()) {
                    iif.f14238a.mo8672b(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8670b(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    /* renamed from: j */
    public static void m13950j(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13428i(((Integer) list.get(i4)).intValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8685e(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8686e(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: e */
    public static void m13935e(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13419e(((Long) list.get(i4)).longValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8683c(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8682c(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    /* renamed from: a */
    public static void m13913a(int i, List list, ime ime) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof ijo) {
                ijo ijo = (ijo) list;
                while (i2 < list.size()) {
                    Object c = ijo.mo8819c(i2);
                    if (c instanceof String) {
                        ((iif) ime).f14238a.mo8660a(i, (String) c);
                    } else {
                        ((iif) ime).f14238a.mo8657a(i, (ihw) c);
                    }
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8660a(i, (String) list.get(i2));
                i2++;
            }
        }
    }

    /* renamed from: i */
    public static void m13947i(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13426h(((Integer) list.get(i4)).intValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8668b(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8674c(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    /* renamed from: d */
    public static void m13932d(int i, List list, ime ime, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (z) {
                iif iif = (iif) ime;
                iif.f14238a.mo8655a(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += iie.m13416d(((Long) list.get(i4)).longValue());
                }
                iif.f14238a.mo8668b(i3);
                while (i2 < list.size()) {
                    iif.f14238a.mo8662a(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((iif) ime).f14238a.mo8656a(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
