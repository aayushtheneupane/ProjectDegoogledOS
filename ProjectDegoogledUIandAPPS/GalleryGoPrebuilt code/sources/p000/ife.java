package p000;

import android.support.p002v7.widget.RecyclerView;
import com.google.common.flogger.backend.google.GooglePlatform;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ife */
/* compiled from: PG */
public class ife {
    /* renamed from: a */
    public static int m12803a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 5;
        }
        return 4;
    }

    /* renamed from: a */
    public static int m12804a(int i, int i2) {
        return i & (i2 ^ -1);
    }

    /* renamed from: a */
    public static int m12805a(int i, int i2, int i3) {
        return (i & (i3 ^ -1)) | (i2 & i3);
    }

    /* renamed from: a */
    public static ieh m12821a(Runnable runnable, long j, TimeUnit timeUnit, exm exm, iel iel) {
        TimeUnit timeUnit2 = timeUnit;
        iev f = iev.m12774f();
        hpf hpf = new hpf(f);
        long c = exm.mo5372c() + TimeUnit.MILLISECONDS.convert(0, timeUnit2);
        iel iel2 = iel;
        hpf.mo7641a(iel2.mo5935a((Runnable) new hpg(f, runnable, hpf, iel, c, TimeUnit.MILLISECONDS.convert(j, timeUnit2), exm), 0, timeUnit2));
        return hpf;
    }

    /* renamed from: a */
    public static boolean m12852a(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        return c >= 'A' && c <= 'Z';
    }

    /* renamed from: b */
    public static int m12861b(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 5;
        }
        return 4;
    }

    /* renamed from: b */
    public static int m12862b(long j) {
        if (j <= 2147483647L) {
            return j < -2147483648L ? RecyclerView.UNDEFINED_DURATION : (int) j;
        }
        return Integer.MAX_VALUE;
    }

    /* renamed from: b */
    public static boolean m12880b(char c) {
        return c >= 'A' && c <= 'Z';
    }

    /* renamed from: c */
    private static int m12881c(char c) {
        return (char) ((c | ' ') - 'a');
    }

    /* renamed from: c */
    private static long m12882c(long j) {
        return j ^ Long.MIN_VALUE;
    }

    /* renamed from: d */
    public static hxg m12894d() {
        try {
            return hxu.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException e) {
            try {
                return GooglePlatform.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException e2) {
                try {
                    return hye.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException e3) {
                    return null;
                }
            }
        }
    }

    /* renamed from: g */
    public static int m12905g(int i) {
        return (i < 32 ? 4 : 2) * (i + 1);
    }

    /* renamed from: a */
    public byte[] mo8317a() {
        throw null;
    }

    /* renamed from: a */
    public static boolean m12854a(CharSequence charSequence, CharSequence charSequence2) {
        int c;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            char charAt2 = charSequence2.charAt(i);
            if (charAt != charAt2 && ((c = m12881c(charAt)) >= 26 || c != m12881c(charAt2))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: g */
    public static hpr m12906g(Object obj) {
        return new hpt(obj);
    }

    /* renamed from: f */
    public static hpx m12901f(Object obj) {
        return new hpx(obj.getClass().getSimpleName());
    }

    /* renamed from: c */
    public static boolean m12891c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    private static String m12833a(int i, int i2, String str) {
        if (i < 0) {
            return m12834a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return m12834a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            StringBuilder sb = new StringBuilder(26);
            sb.append("negative size: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: c */
    public static void m12890c(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    public static void m12845a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    /* renamed from: a */
    public static void m12846a(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, Integer.valueOf(i)));
        }
    }

    /* renamed from: a */
    public static void m12847a(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    /* renamed from: a */
    public static void m12848a(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, Long.valueOf(j)));
        }
    }

    /* renamed from: a */
    public static void m12849a(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, obj));
        }
    }

    /* renamed from: a */
    public static void m12850a(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, obj, obj2));
        }
    }

    /* renamed from: a */
    public static void m12851a(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(m12834a(str, obj, obj2, obj3));
        }
    }

    /* renamed from: b */
    public static void m12873b(int i, int i2) {
        String str;
        if (i < 0 || i >= i2) {
            if (i < 0) {
                str = m12834a("%s (%s) must not be negative", "index", Integer.valueOf(i));
            } else if (i2 < 0) {
                StringBuilder sb = new StringBuilder(26);
                sb.append("negative size: ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            } else {
                str = m12834a("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    /* renamed from: e */
    public static Object m12898e(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw null;
    }

    /* renamed from: b */
    public static Object m12869b(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    /* renamed from: a */
    public static Object m12828a(Object obj, String str, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(m12834a(str, Integer.valueOf(i)));
    }

    /* renamed from: a */
    public static Object m12829a(Object obj, String str, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(m12834a(str, obj2));
    }

    /* renamed from: a */
    public static Object m12830a(Object obj, String str, Object obj2, Object obj3) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(m12834a(str, obj2, obj3));
    }

    /* renamed from: c */
    public static void m12888c(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(m12833a(i, i2, "index"));
        }
    }

    /* renamed from: b */
    public static void m12874b(int i, int i2, int i3) {
        String str;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                str = m12833a(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                str = m12833a(i2, i3, "end index");
            } else {
                str = m12834a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    /* renamed from: d */
    public static void m12896d(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: b */
    public static void m12876b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    /* renamed from: b */
    public static void m12877b(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(m12834a(str, Integer.valueOf(i)));
        }
    }

    /* renamed from: b */
    public static void m12878b(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(m12834a(str, obj));
        }
    }

    /* renamed from: b */
    public static void m12879b(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalStateException(m12834a(str, obj, obj2));
        }
    }

    /* renamed from: a */
    public static String m12834a(String str, Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String str2;
        int i = 0;
        int i2 = 0;
        while (true) {
            length = objArr.length;
            if (i2 >= length) {
                break;
            }
            Object obj = objArr[i2];
            try {
                str2 = String.valueOf(obj);
            } catch (Exception e) {
                String name = obj.getClass().getName();
                String hexString = Integer.toHexString(System.identityHashCode(obj));
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
                sb.append(name);
                sb.append('@');
                sb.append(hexString);
                String sb2 = sb.toString();
                Logger logger = Logger.getLogger("com.google.common.base.Strings");
                Level level = Level.WARNING;
                String valueOf = String.valueOf(sb2);
                logger.logp(level, "com.google.common.base.Strings", "lenientToString", valueOf.length() == 0 ? new String("Exception during lenientFormat for ") : "Exception during lenientFormat for ".concat(valueOf), e);
                String name2 = e.getClass().getName();
                StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 9 + String.valueOf(name2).length());
                sb3.append("<");
                sb3.append(sb2);
                sb3.append(" threw ");
                sb3.append(name2);
                sb3.append(">");
                str2 = sb3.toString();
            }
            objArr[i2] = str2;
            i2++;
        }
        StringBuilder sb4 = new StringBuilder(str.length() + (length << 4));
        int i3 = 0;
        while (true) {
            length2 = objArr.length;
            if (i >= length2 || (indexOf = str.indexOf("%s", i3)) == -1) {
                sb4.append(str, i3, str.length());
            } else {
                sb4.append(str, i3, indexOf);
                sb4.append(objArr[i]);
                i3 = indexOf + 2;
                i++;
            }
        }
        sb4.append(str, i3, str.length());
        if (i < length2) {
            sb4.append(" [");
            sb4.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb4.append(", ");
                sb4.append(objArr[i4]);
            }
            sb4.append(']');
        }
        return sb4.toString();
    }

    /* renamed from: a */
    public static hqk m12811a(hqk hqk) {
        if ((hqk instanceof hqm) || (hqk instanceof hql)) {
            return hqk;
        }
        if (hqk instanceof Serializable) {
            return new hql(hqk);
        }
        return new hqm(hqk);
    }

    /* renamed from: d */
    public static hqk m12893d(Object obj) {
        return new hqn(obj);
    }

    /* renamed from: c */
    public static Object m12885c(Object obj) {
        Object[] objArr = new Object[0];
        if (obj != null) {
            return obj;
        }
        throw new hqp(m12834a("expected a non-null reference", objArr));
    }

    /* renamed from: a */
    public static void m12843a(Object obj, Object obj2) {
        if (obj == null) {
            String valueOf = String.valueOf(obj2);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
            sb.append("null key in entry: null=");
            sb.append(valueOf);
            throw new NullPointerException(sb.toString());
        } else if (obj2 == null) {
            String valueOf2 = String.valueOf(obj);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 26);
            sb2.append("null value in entry: ");
            sb2.append(valueOf2);
            sb2.append("=null");
            throw new NullPointerException(sb2.toString());
        }
    }

    /* renamed from: a */
    public static void m12839a(int i, String str) {
        if (i < 0) {
            StringBuilder sb = new StringBuilder(str.length() + 40);
            sb.append(str);
            sb.append(" cannot be negative but was: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: b */
    public static void m12875b(boolean z) {
        m12876b(z, (Object) "no calls to next() since the last call to remove()");
    }

    /* renamed from: f */
    public static Object m12902f(int i) {
        if (i < 2 || i > 1073741824 || Integer.highestOneBit(i) != i) {
            StringBuilder sb = new StringBuilder(52);
            sb.append("must be power of 2 between 2^1 and 2^30: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (i <= 256) {
            return new byte[i];
        } else {
            if (i > 65536) {
                return new int[i];
            }
            return new short[i];
        }
    }

    /* renamed from: a */
    public static int m12808a(Object obj, Object obj2, int i, Object obj3, int[] iArr, Object[] objArr, Object[] objArr2) {
        int i2;
        int i3;
        int b = m12863b(obj);
        int i4 = b & i;
        int b2 = m12864b(obj3, i4);
        if (b2 == 0) {
            return -1;
        }
        int a = m12804a(b, i);
        int i5 = -1;
        while (true) {
            i2 = b2 - 1;
            i3 = iArr[i2];
            if (m12804a(i3, i) != a || !m12891c(obj, objArr[i2]) || (objArr2 != null && !m12891c(obj2, objArr2[i2]))) {
                int i6 = i3 & i;
                if (i6 == 0) {
                    return -1;
                }
                int i7 = i6;
                i5 = i2;
                b2 = i7;
            }
        }
        int i8 = i3 & i;
        if (i5 != -1) {
            iArr[i5] = m12805a(iArr[i5], i8, i);
        } else {
            m12842a(obj3, i4, i8);
        }
        return i2;
    }

    /* renamed from: b */
    public static int m12864b(Object obj, int i) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i] & 255;
        }
        if (obj instanceof short[]) {
            return (char) ((short[]) obj)[i];
        }
        return ((int[]) obj)[i];
    }

    /* renamed from: a */
    public static void m12842a(Object obj, int i, int i2) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i] = (byte) i2;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i] = (short) i2;
        } else {
            ((int[]) obj)[i] = i2;
        }
    }

    /* renamed from: e */
    public static int m12897e(int i) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) ((double) highestOneBit)) || (highestOneBit = highestOneBit + highestOneBit) > 0) {
            return highestOneBit;
        }
        return 1073741824;
    }

    /* renamed from: d */
    public static int m12892d(int i) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i) * -862048943), 15)) * 461845907);
    }

    /* renamed from: b */
    public static int m12863b(Object obj) {
        return m12892d(obj != null ? obj.hashCode() : 0);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.List, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object m12907g(java.lang.Iterable r2) {
        /*
            boolean r0 = r2 instanceof java.util.List
            if (r0 == 0) goto L_0x001b
            boolean r0 = r2.isEmpty()
            if (r0 != 0) goto L_0x0015
            int r0 = r2.size()
            int r0 = r0 + -1
            java.lang.Object r2 = r2.get(r0)
            return r2
        L_0x0015:
            java.util.NoSuchElementException r2 = new java.util.NoSuchElementException
            r2.<init>()
            throw r2
        L_0x001b:
            java.util.Iterator r2 = r2.iterator()
        L_0x001f:
            java.lang.Object r0 = r2.next()
            boolean r1 = r2.hasNext()
            if (r1 != 0) goto L_0x001f
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ife.m12907g(java.lang.Iterable):java.lang.Object");
    }

    /* renamed from: f */
    public static Object m12903f(Iterable iterable) {
        return m12870b(iterable.iterator());
    }

    /* renamed from: a */
    public static boolean m12855a(Collection collection, Iterator it) {
        m12898e((Object) collection);
        m12898e((Object) it);
        boolean z = false;
        while (it.hasNext()) {
            z |= collection.add(it.next());
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public static void m12889c(Iterator it) {
        m12898e((Object) it);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /* renamed from: b */
    public static Object m12870b(Iterator it) {
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            sb.append(", ");
            sb.append(it.next());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m12856a(java.util.List r6, java.lang.Object r7) {
        /*
            java.lang.Object r0 = m12898e((java.lang.Object) r6)
            r1 = 1
            if (r7 == r0) goto L_0x005e
            boolean r0 = r7 instanceof java.util.List
            r2 = 0
            if (r0 == 0) goto L_0x005d
            java.util.List r7 = (java.util.List) r7
            int r0 = r6.size()
            int r3 = r7.size()
            if (r0 != r3) goto L_0x005c
            boolean r3 = r7 instanceof java.util.RandomAccess
            if (r3 == 0) goto L_0x0032
            r3 = 0
        L_0x001d:
            if (r3 >= r0) goto L_0x0031
            java.lang.Object r4 = r6.get(r3)
            java.lang.Object r5 = r7.get(r3)
            boolean r4 = m12891c((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0030
            int r3 = r3 + 1
            goto L_0x001d
        L_0x0030:
            return r2
        L_0x0031:
            return r1
        L_0x0032:
            java.util.Iterator r6 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L_0x003a:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0055
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x0054
            java.lang.Object r0 = r6.next()
            java.lang.Object r3 = r7.next()
            boolean r0 = m12891c((java.lang.Object) r0, (java.lang.Object) r3)
            if (r0 != 0) goto L_0x003a
        L_0x0054:
            goto L_0x005b
        L_0x0055:
            boolean r6 = r7.hasNext()
            r2 = r6 ^ 1
        L_0x005b:
            return r2
        L_0x005c:
        L_0x005d:
            return r2
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ife.m12856a(java.util.List, java.lang.Object):boolean");
    }

    /* renamed from: f */
    public static ArrayList m12904f() {
        return new ArrayList();
    }

    /* renamed from: e */
    public static ArrayList m12899e(Iterable iterable) {
        m12898e((Object) iterable);
        if (!(iterable instanceof Collection)) {
            return m12835a(iterable.iterator());
        }
        return new ArrayList((Collection) iterable);
    }

    /* renamed from: a */
    public static ArrayList m12835a(Iterator it) {
        ArrayList f = m12904f();
        m12855a((Collection) f, it);
        return f;
    }

    @SafeVarargs
    /* renamed from: b */
    public static ArrayList m12872b(Object... objArr) {
        m12898e((Object) objArr);
        m12839a(1, "arraySize");
        ArrayList arrayList = new ArrayList(m12862b(6));
        Collections.addAll(arrayList, objArr);
        return arrayList;
    }

    /* renamed from: c */
    public static ArrayList m12886c(int i) {
        m12839a(i, "initialArraySize");
        return new ArrayList(i);
    }

    /* renamed from: a */
    public static List m12836a(List list) {
        if (list instanceof hso) {
            return ((hso) list).mo7910e();
        }
        if (list instanceof hud) {
            return ((hud) list).f13410a;
        }
        if (list instanceof RandomAccess) {
            return new hub(list);
        }
        return new hud(list);
    }

    /* renamed from: a */
    public static Object m12831a(Map map, Object obj) {
        m12898e((Object) map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static hsu m12812a(Iterable iterable, hpr hpr) {
        m12898e((Object) hpr);
        hsq g = hsu.m12070g();
        for (Object next : iterable) {
            g.mo7932a(hpr.mo1484a(next), next);
        }
        try {
            return g.mo7930a();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.valueOf(e.getMessage()).concat(". To index multiple values under a key, use Multimaps.index."));
        }
    }

    /* renamed from: a */
    public static boolean m12853a(huo huo, Object obj) {
        if (obj == huo) {
            return true;
        }
        if (obj instanceof huo) {
            huo huo2 = (huo) obj;
            if (huo.size() == huo2.size() && huo.mo7796f().size() == huo2.mo7796f().size()) {
                for (hun hun : huo2.mo7796f()) {
                    if (huo.mo7769a(hun.mo8079a()) != hun.mo8080b()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static Object m12826a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }

    /* renamed from: a */
    public static Object[] m12859a(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            m12826a(objArr[i], i);
        }
        return objArr;
    }

    /* renamed from: a */
    public static Object[] m12860a(Object[] objArr, Object[] objArr2, Class cls) {
        int length = objArr.length;
        int length2 = objArr2.length;
        Object[] objArr3 = (Object[]) Array.newInstance(cls, length + length2);
        System.arraycopy(objArr, 0, objArr3, 0, length);
        System.arraycopy(objArr2, 0, objArr3, length, length2);
        return objArr3;
    }

    /* renamed from: a */
    public static hvh m12813a(Class cls, String str) {
        try {
            return new hvh(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public static void m12840a(hum hum, ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(hum.mo7787e().size());
        for (Map.Entry entry : hum.mo7787e().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((Collection) entry.getValue()).size());
            for (Object writeObject : (Collection) entry.getValue()) {
                objectOutputStream.writeObject(writeObject);
            }
        }
    }

    /* renamed from: a */
    public static hvn m12814a(Set set, Set set2) {
        m12869b((Object) set, (Object) "set1");
        m12869b((Object) set2, (Object) "set2");
        return new hvl(set, set2);
    }

    /* renamed from: a */
    public static int m12809a(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }

    /* renamed from: e */
    public static HashSet m12900e() {
        return new HashSet();
    }

    /* renamed from: a */
    public static boolean m12857a(Set set, Collection collection) {
        m12898e((Object) collection);
        if (collection instanceof huo) {
            collection = ((huo) collection).mo7794e();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return m12858a(set, collection.iterator());
        }
        Iterator it = set.iterator();
        m12898e((Object) collection);
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m12858a(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static Object m12827a(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str.concat(" must not be null"));
    }

    protected ife() {
    }

    /* renamed from: b */
    public static iaq m12865b(Throwable th) {
        StackTraceElement[] stackTraceElementArr;
        iaq iaq = (iaq) iat.f13757d.mo8793g();
        String name = th.getClass().getName();
        if (iaq.f14319c) {
            iaq.mo8751b();
            iaq.f14319c = false;
        }
        iat iat = (iat) iaq.f14318b;
        name.getClass();
        iat.f13759a |= 1;
        iat.f13760b = name;
        try {
            stackTraceElementArr = th.getStackTrace();
        } catch (NullPointerException e) {
            stackTraceElementArr = null;
        }
        if (stackTraceElementArr != null) {
            for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                iar iar = (iar) ias.f13749f.mo8793g();
                if (stackTraceElement != null) {
                    String className = stackTraceElement.getClassName();
                    if (iar.f14319c) {
                        iar.mo8751b();
                        iar.f14319c = false;
                    }
                    ias ias = (ias) iar.f14318b;
                    className.getClass();
                    ias.f13751a |= 1;
                    ias.f13752b = className;
                    String methodName = stackTraceElement.getMethodName();
                    if (iar.f14319c) {
                        iar.mo8751b();
                        iar.f14319c = false;
                    }
                    ias ias2 = (ias) iar.f14318b;
                    methodName.getClass();
                    ias2.f13751a |= 2;
                    ias2.f13753c = methodName;
                    int lineNumber = stackTraceElement.getLineNumber();
                    if (iar.f14319c) {
                        iar.mo8751b();
                        iar.f14319c = false;
                    }
                    ias ias3 = (ias) iar.f14318b;
                    ias3.f13751a |= 8;
                    ias3.f13755e = lineNumber;
                    if (stackTraceElement.getFileName() != null) {
                        String fileName = stackTraceElement.getFileName();
                        if (iar.f14319c) {
                            iar.mo8751b();
                            iar.f14319c = false;
                        }
                        ias ias4 = (ias) iar.f14318b;
                        fileName.getClass();
                        ias4.f13751a |= 4;
                        ias4.f13754d = fileName;
                    }
                }
                if (iaq.f14319c) {
                    iaq.mo8751b();
                    iaq.f14319c = false;
                }
                iat iat2 = (iat) iaq.f14318b;
                ias ias5 = (ias) iar.mo8770g();
                ias5.getClass();
                if (!iat2.f13761c.mo8521a()) {
                    iat2.f13761c = iix.m13608a(iat2.f13761c);
                }
                iat2.f13761c.add(ias5);
            }
        }
        return iaq;
    }

    /* renamed from: a */
    public static void m12844a(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    /* renamed from: a */
    public static float m12802a(float f, float f2) {
        m12850a(f2 >= 0.0f, "min (%s) must be less than or equal to max (%s)", (Object) Float.valueOf(0.0f), (Object) Float.valueOf(f2));
        return Math.min(Math.max(f, 0.0f), f2);
    }

    /* renamed from: a */
    public static int m12806a(long j) {
        int i = (int) j;
        m12848a(((long) i) == j, "Out of range: %s", j);
        return i;
    }

    /* renamed from: a */
    public static int m12807a(long j, long j2) {
        long c = m12882c(j);
        long c2 = m12882c(j2);
        if (c >= c2) {
            return c <= c2 ? 0 : 1;
        }
        return -1;
    }

    /* renamed from: a */
    public static long m12810a(String str) {
        m12898e((Object) str);
        if (str.length() != 0) {
            int i = iba.f13823a[16] - 1;
            long j = 0;
            int i2 = 0;
            while (i2 < str.length()) {
                int digit = Character.digit(str.charAt(i2), 16);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i2 > i && iba.m12598a(j, digit)) {
                    String valueOf = String.valueOf(str);
                    throw new NumberFormatException(valueOf.length() == 0 ? new String("Too large for unsigned long: ") : "Too large for unsigned long: ".concat(valueOf));
                } else {
                    j = (j << 4) + ((long) digit);
                    i2++;
                }
            }
            return j;
        }
        throw new NumberFormatException("empty string");
    }

    /* renamed from: c */
    public static Callable m12887c() {
        return new icg();
    }

    /* renamed from: a */
    public static void m12841a(ieh ieh, idw idw, Executor executor) {
        m12898e((Object) idw);
        ieh.mo53a(new idy(ieh, idw), executor);
    }

    /* renamed from: a */
    public static ieh m12819a(Iterable iterable) {
        return new idc(hso.m12032a(iterable), true);
    }

    @SafeVarargs
    /* renamed from: a */
    public static ieh m12823a(ieh... iehArr) {
        return new idc(hso.m12042a((Object[]) iehArr), true);
    }

    /* renamed from: b */
    public static Object m12871b(Future future) {
        m12878b(future.isDone(), "Future was expected to be done: %s", (Object) future);
        return m12832a(future);
    }

    /* renamed from: b */
    public static ieh m12868b() {
        return new ied();
    }

    /* renamed from: a */
    public static ieh m12822a(Throwable th) {
        m12898e((Object) th);
        return new ied(th);
    }

    /* renamed from: a */
    public static ieh m12820a(Object obj) {
        return obj != null ? new iee(obj) : iee.f13951a;
    }

    /* renamed from: a */
    public static ieh m12817a(ieh ieh) {
        if (ieh.isDone()) {
            return ieh;
        }
        ieb ieb = new ieb(ieh);
        ieh.mo53a(ieb, idh.f13918a);
        return ieb;
    }

    /* renamed from: a */
    public static ieh m12815a(ice ice, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        ifd a = ifd.m12797a(ice);
        a.mo53a((Runnable) new idx(scheduledExecutorService.schedule(a, j, timeUnit)), (Executor) idh.f13918a);
        return a;
    }

    /* renamed from: a */
    public static ieh m12816a(ice ice, Executor executor) {
        ifd a = ifd.m12797a(ice);
        executor.execute(a);
        return a;
    }

    /* renamed from: d */
    public static ieh m12895d(Iterable iterable) {
        return new idc(hso.m12032a(iterable), false);
    }

    /* renamed from: b */
    public static iea m12866b(Iterable iterable) {
        return new iea(false, hso.m12032a(iterable));
    }

    @SafeVarargs
    /* renamed from: b */
    public static iea m12867b(ieh... iehArr) {
        return new iea(false, hso.m12042a((Object[]) iehArr));
    }

    /* renamed from: c */
    public static iea m12883c(Iterable iterable) {
        return new iea(true, hso.m12032a(iterable));
    }

    @SafeVarargs
    /* renamed from: c */
    public static iea m12884c(ieh... iehArr) {
        return new iea(true, hso.m12042a((Object[]) iehArr));
    }

    /* renamed from: a */
    public static ieh m12818a(ieh ieh, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        if (ieh.isDone()) {
            return ieh;
        }
        ifa ifa = new ifa(ieh);
        iey iey = new iey(ifa);
        ifa.f13989f = scheduledExecutorService.schedule(iey, j, timeUnit);
        ieh.mo53a(iey, idh.f13918a);
        return ifa;
    }

    /* renamed from: a */
    public static iek m12824a(ExecutorService executorService) {
        if (executorService instanceof iek) {
            return (iek) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            return new ier((ScheduledExecutorService) executorService);
        }
        return new ieo(executorService);
    }

    /* renamed from: a */
    public static iel m12825a(ScheduledExecutorService scheduledExecutorService) {
        if (!(scheduledExecutorService instanceof iel)) {
            return new ier(scheduledExecutorService);
        }
        return (iel) scheduledExecutorService;
    }

    /* renamed from: a */
    public static Executor m12837a(Executor executor) {
        return new ieu(executor);
    }

    /* renamed from: a */
    static Executor m12838a(Executor executor, ibr ibr) {
        m12898e((Object) executor);
        m12898e((Object) ibr);
        return executor != idh.f13918a ? new ien(executor, ibr) : executor;
    }

    /* renamed from: a */
    public static Object m12832a(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException e) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }
}
