package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.io.Serializable;
import java.util.Map;

class RegularImmutableBiMap extends ImmutableBiMap {
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry[] entries;
    /* access modifiers changed from: private */
    public final transient int mask;

    /* renamed from: qN */
    private final transient ImmutableMapEntry[] f2477qN;
    /* access modifiers changed from: private */

    /* renamed from: rN */
    public final transient ImmutableMapEntry[] f2478rN;
    /* access modifiers changed from: private */

    /* renamed from: sN */
    public final transient int f2479sN;

    /* renamed from: tN */
    private transient ImmutableBiMap f2480tN;

    final class Inverse extends ImmutableBiMap {

        final class InverseEntrySet extends ImmutableMapEntrySet {
            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            /* renamed from: Pl */
            public ImmutableList mo8702Pl() {
                return new ImmutableAsList() {
                    /* access modifiers changed from: package-private */
                    /* renamed from: Ql */
                    public ImmutableCollection mo8695Ql() {
                        return InverseEntrySet.this;
                    }

                    public Map.Entry get(int i) {
                        ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.entries[i];
                        return C1633Xa.m4547i(immutableMapEntry.getValue(), immutableMapEntry.getKey());
                    }
                };
            }

            /* access modifiers changed from: package-private */
            /* renamed from: Rl */
            public boolean mo8649Rl() {
                return true;
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.f2479sN;
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap map() {
                return Inverse.this;
            }

            public C1692rb iterator() {
                return mo8648Ol().iterator();
            }
        }

        /* synthetic */ Inverse(C16111 r2) {
        }

        public Object get(Object obj) {
            if (obj == null) {
                return null;
            }
            for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.f2478rN[C1578K.m4255cb(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.mo8747al()) {
                if (obj.equals(immutableMapEntry.getValue())) {
                    return immutableMapEntry.getKey();
                }
            }
            return null;
        }

        public ImmutableBiMap inverse() {
            return RegularImmutableBiMap.this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: ol */
        public ImmutableSet mo8644ol() {
            return new InverseEntrySet();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: pl */
        public boolean mo8645pl() {
            return false;
        }

        public int size() {
            return inverse().size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    class InverseSerializedForm implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap forward;

        InverseSerializedForm(ImmutableBiMap immutableBiMap) {
            this.forward = immutableBiMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.forward.inverse();
        }
    }

    final class NonTerminalBiMapEntry extends ImmutableMapEntry {
        private final ImmutableMapEntry nextInKeyBucket;
        private final ImmutableMapEntry nextInValueBucket;

        NonTerminalBiMapEntry(ImmutableMapEntry immutableMapEntry, ImmutableMapEntry immutableMapEntry2, ImmutableMapEntry immutableMapEntry3) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
            this.nextInValueBucket = immutableMapEntry3;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: _k */
        public ImmutableMapEntry mo8746_k() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: al */
        public ImmutableMapEntry mo8747al() {
            return this.nextInValueBucket;
        }
    }

    RegularImmutableBiMap(int i, ImmutableMapEntry.TerminalEntry[] terminalEntryArr) {
        int i2 = i;
        int a = C1578K.m4254a(i2, 1.2d);
        this.mask = a - 1;
        ImmutableMapEntry[] immutableMapEntryArr = new ImmutableMapEntry[a];
        ImmutableMapEntry[] immutableMapEntryArr2 = new ImmutableMapEntry[a];
        ImmutableMapEntry[] immutableMapEntryArr3 = new ImmutableMapEntry[i2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            ImmutableMapEntry immutableMapEntry = terminalEntryArr[i3];
            Object key = immutableMapEntry.getKey();
            Object value = immutableMapEntry.getValue();
            int hashCode = key.hashCode();
            int hashCode2 = value.hashCode();
            int cb = C1578K.m4255cb(hashCode) & this.mask;
            int cb2 = C1578K.m4255cb(hashCode2) & this.mask;
            ImmutableMapEntry immutableMapEntry2 = immutableMapEntryArr[cb];
            ImmutableMapEntry immutableMapEntry3 = immutableMapEntry2;
            while (immutableMapEntry3 != null) {
                ImmutableMap.m4211a(!key.equals(immutableMapEntry3.getKey()), "key", immutableMapEntry, immutableMapEntry3);
                immutableMapEntry3 = immutableMapEntry3.mo8746_k();
                int i5 = i;
                key = key;
            }
            ImmutableMapEntry immutableMapEntry4 = immutableMapEntryArr2[cb2];
            ImmutableMapEntry immutableMapEntry5 = immutableMapEntry4;
            while (immutableMapEntry5 != null) {
                ImmutableMap.m4211a(!value.equals(immutableMapEntry5.getValue()), "value", immutableMapEntry, immutableMapEntry5);
                immutableMapEntry5 = immutableMapEntry5.mo8747al();
                value = value;
            }
            if (immutableMapEntry2 != null || immutableMapEntry4 != null) {
                immutableMapEntry = new NonTerminalBiMapEntry(immutableMapEntry, immutableMapEntry2, immutableMapEntry4);
            }
            immutableMapEntryArr[cb] = immutableMapEntry;
            immutableMapEntryArr2[cb2] = immutableMapEntry;
            immutableMapEntryArr3[i3] = immutableMapEntry;
            i4 += hashCode ^ hashCode2;
            i3++;
            i2 = i;
        }
        this.f2477qN = immutableMapEntryArr;
        this.f2478rN = immutableMapEntryArr2;
        this.entries = immutableMapEntryArr3;
        this.f2479sN = i4;
    }

    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry immutableMapEntry = this.f2477qN[C1578K.m4255cb(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.mo8746_k()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    public ImmutableBiMap inverse() {
        ImmutableBiMap immutableBiMap = this.f2480tN;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        Inverse inverse = new Inverse((C16111) null);
        this.f2480tN = inverse;
        return inverse;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        return new ImmutableMapEntrySet() {
            /* access modifiers changed from: package-private */
            /* renamed from: Pl */
            public ImmutableList mo8702Pl() {
                return new RegularImmutableAsList((ImmutableCollection) this, (Object[]) RegularImmutableBiMap.this.entries);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: Rl */
            public boolean mo8649Rl() {
                return true;
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.f2479sN;
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap map() {
                return RegularImmutableBiMap.this;
            }

            public C1692rb iterator() {
                return mo8648Ol().iterator();
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }
}
