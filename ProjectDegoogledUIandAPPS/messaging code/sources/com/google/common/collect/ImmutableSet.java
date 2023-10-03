package com.google.common.collect;

import com.google.common.base.C1508E;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public abstract class ImmutableSet extends ImmutableCollection implements Set {

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.m4233g(this.elements);
        }
    }

    ImmutableSet() {
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static ImmutableSet m4232b(int i, Object... objArr) {
        if (i == 0) {
            return EmptyImmutableSet.INSTANCE;
        }
        if (i == 1) {
            return new SingletonImmutableSet(objArr[0]);
        }
        int chooseTableSize = chooseTableSize(i);
        Object[] objArr2 = new Object[chooseTableSize];
        int i2 = chooseTableSize - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            C1638_a.m4555c(obj, i5);
            int hashCode = obj.hashCode();
            int cb = C1578K.m4255cb(hashCode);
            while (true) {
                int i6 = cb & i2;
                Object obj2 = objArr2[i6];
                if (obj2 == null) {
                    objArr[i3] = obj;
                    objArr2[i6] = obj;
                    i4 += hashCode;
                    i3++;
                    break;
                } else if (obj2.equals(obj)) {
                    break;
                } else {
                    cb++;
                }
            }
        }
        Arrays.fill(objArr, i3, i, (Object) null);
        if (i3 == 1) {
            return new SingletonImmutableSet(objArr[0], i4);
        }
        if (chooseTableSize != chooseTableSize(i3)) {
            return m4232b(i3, objArr);
        }
        if (i3 < objArr.length) {
            objArr = C1638_a.m4553a(objArr, i3);
        }
        return new RegularImmutableSet(objArr, i4, objArr2, i2);
    }

    public static C1624T builder() {
        return new C1624T();
    }

    static int chooseTableSize(int i) {
        boolean z = true;
        if (i < 751619276) {
            int highestOneBit = Integer.highestOneBit(i - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) i)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (i >= 1073741824) {
            z = false;
        }
        C1508E.checkArgument(z, "collection too large");
        return 1073741824;
    }

    /* renamed from: g */
    public static ImmutableSet m4233g(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return EmptyImmutableSet.INSTANCE;
        }
        if (length != 1) {
            return m4232b(objArr.length, (Object[]) objArr.clone());
        }
        return new SingletonImmutableSet(objArr[0]);
    }

    /* renamed from: of */
    public static ImmutableSet m4234of(Object obj) {
        return new SingletonImmutableSet(obj);
    }

    /* renamed from: ql */
    public static ImmutableSet m4235ql() {
        return EmptyImmutableSet.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Rl */
    public boolean mo8649Rl() {
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !mo8649Rl() || !((ImmutableSet) obj).mo8649Rl() || hashCode() == obj.hashCode()) {
            return C1630W.m4532a((Set) this, obj);
        }
        return false;
    }

    public int hashCode() {
        return C1630W.m4535c(this);
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
