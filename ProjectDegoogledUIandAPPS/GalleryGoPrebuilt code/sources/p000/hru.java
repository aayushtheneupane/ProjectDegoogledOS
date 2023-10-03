package p000;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: hru */
/* compiled from: PG */
final class hru extends AbstractMap implements Serializable, Map {

    /* renamed from: a */
    public static final Object f13322a = new Object();

    /* renamed from: b */
    public transient Object f13323b;

    /* renamed from: c */
    public transient int[] f13324c;

    /* renamed from: d */
    public transient Object[] f13325d;

    /* renamed from: e */
    public transient Object[] f13326e;

    /* renamed from: f */
    public transient int f13327f;

    /* renamed from: g */
    public transient int f13328g;

    /* renamed from: h */
    private transient Set f13329h;

    /* renamed from: i */
    private transient Set f13330i;

    /* renamed from: j */
    private transient Collection f13331j;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo7832a(int i) {
        int i2 = i + 1;
        if (i2 >= this.f13328g) {
            return -1;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo7836b() {
        return this.f13323b == null;
    }

    /* renamed from: c */
    public final int mo7837c() {
        return (1 << (this.f13327f & 31)) - 1;
    }

    public final Object compute(Object obj, BiFunction biFunction) {
        return Map$$CC.compute$$dflt$$(this, obj, biFunction);
    }

    public final Object computeIfAbsent(Object obj, Function function) {
        return Map$$CC.computeIfAbsent$$dflt$$(this, obj, function);
    }

    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map$$CC.computeIfPresent$$dflt$$(this, obj, biFunction);
    }

    public final void forEach(BiConsumer biConsumer) {
        Map$$CC.forEach$$dflt$$(this, biConsumer);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        return Map$$CC.getOrDefault$$dflt$$(this, obj, obj2);
    }

    public final boolean isEmpty() {
        return this.f13328g == 0;
    }

    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map$$CC.merge$$dflt$$(this, obj, obj2, biFunction);
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        return Map$$CC.putIfAbsent$$dflt$$(this, obj, obj2);
    }

    public final boolean remove(Object obj, Object obj2) {
        return Map$$CC.remove$$dflt$$(this, obj, obj2);
    }

    public final Object replace(Object obj, Object obj2) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2);
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2, obj3);
    }

    public final void replaceAll(BiFunction biFunction) {
        Map$$CC.replaceAll$$dflt$$(this, biFunction);
    }

    public final int size() {
        return this.f13328g;
    }

    public hru() {
        m11984b(3);
    }

    public hru(int i) {
        m11984b(i);
    }

    public final void clear() {
        if (!mo7836b()) {
            mo7844d();
            Arrays.fill(this.f13325d, 0, this.f13328g, (Object) null);
            Arrays.fill(this.f13326e, 0, this.f13328g, (Object) null);
            Object obj = this.f13323b;
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(this.f13324c, 0, this.f13328g, 0);
            this.f13328g = 0;
        }
    }

    public final boolean containsKey(Object obj) {
        return mo7833a(obj) != -1;
    }

    public final boolean containsValue(Object obj) {
        for (int i = 0; i < this.f13328g; i++) {
            if (ife.m12891c(obj, this.f13326e[i])) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static hru m11983a() {
        return new hru();
    }

    public final Set entrySet() {
        Set set = this.f13330i;
        if (set != null) {
            return set;
        }
        hrp hrp = new hrp(this);
        this.f13330i = hrp;
        return hrp;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final int mo7845e() {
        return isEmpty() ? -1 : 0;
    }

    public final Object get(Object obj) {
        int a = mo7833a(obj);
        if (a != -1) {
            return this.f13326e[a];
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo7844d() {
        this.f13327f += 32;
    }

    /* renamed from: a */
    public final int mo7833a(Object obj) {
        if (!mo7836b()) {
            int b = ife.m12863b(obj);
            int c = mo7837c();
            int b2 = ife.m12864b(this.f13323b, b & c);
            if (b2 != 0) {
                int a = ife.m12804a(b, c);
                do {
                    int i = b2 - 1;
                    int i2 = this.f13324c[i];
                    if (ife.m12804a(i2, c) == a && ife.m12891c(obj, this.f13325d[i])) {
                        return i;
                    }
                    b2 = i2 & c;
                } while (b2 != 0);
            }
        }
        return -1;
    }

    /* renamed from: b */
    private final void m11984b(int i) {
        ife.m12845a(i >= 0, (Object) "Expected size must be >= 0");
        this.f13327f = Math.max(1, Math.min(1073741823, i));
    }

    public final Set keySet() {
        Set set = this.f13329h;
        if (set != null) {
            return set;
        }
        hrr hrr = new hrr(this);
        this.f13329h = hrr;
        return hrr;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7834a(int i, int i2) {
        int i3 = this.f13328g - 1;
        if (i < i3) {
            Object[] objArr = this.f13325d;
            Object obj = objArr[i3];
            objArr[i] = obj;
            Object[] objArr2 = this.f13326e;
            objArr2[i] = objArr2[i3];
            objArr[i3] = null;
            objArr2[i3] = null;
            int[] iArr = this.f13324c;
            iArr[i] = iArr[i3];
            iArr[i3] = 0;
            int b = ife.m12863b(obj) & i2;
            int b2 = ife.m12864b(this.f13323b, b);
            int i4 = i3 + 1;
            if (b2 != i4) {
                while (true) {
                    int i5 = b2 - 1;
                    int[] iArr2 = this.f13324c;
                    int i6 = iArr2[i5];
                    int i7 = i6 & i2;
                    if (i7 != i4) {
                        b2 = i7;
                    } else {
                        iArr2[i5] = ife.m12805a(i6, i + 1, i2);
                        return;
                    }
                }
            } else {
                ife.m12842a(this.f13323b, b, i + 1);
            }
        } else {
            this.f13325d[i] = null;
            this.f13326e[i] = null;
            this.f13324c[i] = 0;
        }
    }

    public final Object put(Object obj, Object obj2) {
        int min;
        if (mo7836b()) {
            ife.m12876b(mo7836b(), (Object) "Arrays already allocated");
            int i = this.f13327f;
            int max = Math.max(4, ife.m12897e(i + 1));
            this.f13323b = ife.m12902f(max);
            m11985c(max - 1);
            this.f13324c = new int[i];
            this.f13325d = new Object[i];
            this.f13326e = new Object[i];
        }
        int[] iArr = this.f13324c;
        Object[] objArr = this.f13325d;
        Object[] objArr2 = this.f13326e;
        int i2 = this.f13328g;
        int i3 = i2 + 1;
        int b = ife.m12863b(obj);
        int c = mo7837c();
        int i4 = b & c;
        int b2 = ife.m12864b(this.f13323b, i4);
        if (b2 != 0) {
            int a = ife.m12804a(b, c);
            while (true) {
                int i5 = b2 - 1;
                int i6 = iArr[i5];
                if (ife.m12804a(i6, c) != a || !ife.m12891c(obj, objArr[i5])) {
                    int i7 = i6 & c;
                    if (i7 != 0) {
                        b2 = i7;
                    } else if (i3 > c) {
                        c = m11982a(c, ife.m12905g(c), b, i2);
                    } else {
                        iArr[i5] = ife.m12805a(i6, i3, c);
                    }
                } else {
                    Object obj3 = objArr2[i5];
                    objArr2[i5] = obj2;
                    return obj3;
                }
            }
        } else if (i3 > c) {
            c = m11982a(c, ife.m12905g(c), b, i2);
        } else {
            ife.m12842a(this.f13323b, i4, i3);
        }
        int length = this.f13324c.length;
        if (i3 > length && (min = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.f13324c = Arrays.copyOf(this.f13324c, min);
            this.f13325d = Arrays.copyOf(this.f13325d, min);
            this.f13326e = Arrays.copyOf(this.f13326e, min);
        }
        this.f13324c[i2] = ife.m12805a(b, 0, c);
        this.f13325d[i2] = obj;
        this.f13326e[i2] = obj2;
        this.f13328g = i3;
        mo7844d();
        return null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            m11984b(readInt);
            for (int i = 0; i < readInt; i++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        StringBuilder sb = new StringBuilder(25);
        sb.append("Invalid size: ");
        sb.append(readInt);
        throw new InvalidObjectException(sb.toString());
    }

    public final Object remove(Object obj) {
        Object b = mo7835b(obj);
        if (b == f13322a) {
            return null;
        }
        return b;
    }

    /* renamed from: b */
    public final Object mo7835b(Object obj) {
        int c;
        int a;
        if (mo7836b() || (a = ife.m12808a(obj, (Object) null, c, this.f13323b, this.f13324c, this.f13325d, (Object[]) null)) == -1) {
            return f13322a;
        }
        Object obj2 = this.f13326e[a];
        mo7834a(a, (c = mo7837c()));
        this.f13328g--;
        mo7844d();
        return obj2;
    }

    /* renamed from: a */
    private final int m11982a(int i, int i2, int i3, int i4) {
        Object f = ife.m12902f(i2);
        int i5 = i2 - 1;
        if (i4 != 0) {
            ife.m12842a(f, i3 & i5, i4 + 1);
        }
        Object obj = this.f13323b;
        int[] iArr = this.f13324c;
        for (int i6 = 0; i6 <= i; i6++) {
            int b = ife.m12864b(obj, i6);
            while (b != 0) {
                int i7 = b - 1;
                int i8 = iArr[i7];
                int a = ife.m12804a(i8, i) | i6;
                int i9 = a & i5;
                int b2 = ife.m12864b(f, i9);
                ife.m12842a(f, i9, b);
                iArr[i7] = ife.m12805a(a, b2, i5);
                b = i8 & i;
            }
        }
        this.f13323b = f;
        m11985c(i5);
        return i5;
    }

    /* renamed from: c */
    private final void m11985c(int i) {
        this.f13327f = ife.m12805a(this.f13327f, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    public final Collection values() {
        Collection collection = this.f13331j;
        if (collection != null) {
            return collection;
        }
        hrt hrt = new hrt(this);
        this.f13331j = hrt;
        return hrt;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.f13328g);
        int e = mo7845e();
        while (e >= 0) {
            objectOutputStream.writeObject(this.f13325d[e]);
            objectOutputStream.writeObject(this.f13326e[e]);
            e = mo7832a(e);
        }
    }
}
