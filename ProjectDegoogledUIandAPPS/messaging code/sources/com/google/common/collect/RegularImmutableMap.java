package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;

final class RegularImmutableMap extends ImmutableMap {
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry[] table;

    class EntrySet extends ImmutableMapEntrySet {
        /* synthetic */ EntrySet(C1653eb ebVar) {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: Pl */
        public ImmutableList mo8702Pl() {
            return new RegularImmutableAsList((ImmutableCollection) this, (Object[]) RegularImmutableMap.this.entries);
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap map() {
            return RegularImmutableMap.this;
        }

        public C1692rb iterator() {
            return mo8648Ol().iterator();
        }
    }

    RegularImmutableMap(int i, ImmutableMapEntry.TerminalEntry[] terminalEntryArr) {
        this.entries = new ImmutableMapEntry[i];
        int a = C1578K.m4254a(i, 1.2d);
        this.table = new ImmutableMapEntry[a];
        this.mask = a - 1;
        for (int i2 = 0; i2 < i; i2++) {
            ImmutableMapEntry immutableMapEntry = terminalEntryArr[i2];
            Object key = immutableMapEntry.getKey();
            int cb = C1578K.m4255cb(key.hashCode()) & this.mask;
            ImmutableMapEntry immutableMapEntry2 = this.table[cb];
            if (immutableMapEntry2 != null) {
                immutableMapEntry = new NonTerminalMapEntry(immutableMapEntry, immutableMapEntry2);
            }
            this.table[cb] = immutableMapEntry;
            this.entries[i2] = immutableMapEntry;
            m4455a(key, immutableMapEntry, immutableMapEntry2);
        }
    }

    public Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry immutableMapEntry = this.table[C1578K.m4255cb(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.mo8746_k()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ol */
    public ImmutableSet mo8644ol() {
        return new EntrySet((C1653eb) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8645pl() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }

    final class NonTerminalMapEntry extends ImmutableMapEntry {
        private final ImmutableMapEntry nextInKeyBucket;

        NonTerminalMapEntry(Object obj, Object obj2, ImmutableMapEntry immutableMapEntry) {
            super(obj, obj2);
            this.nextInKeyBucket = immutableMapEntry;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: _k */
        public ImmutableMapEntry mo8746_k() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: al */
        public ImmutableMapEntry mo8747al() {
            return null;
        }

        NonTerminalMapEntry(ImmutableMapEntry immutableMapEntry, ImmutableMapEntry immutableMapEntry2) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
        }
    }

    /* renamed from: a */
    private void m4455a(Object obj, ImmutableMapEntry immutableMapEntry, ImmutableMapEntry immutableMapEntry2) {
        while (immutableMapEntry2 != null) {
            ImmutableMap.m4211a(!obj.equals(immutableMapEntry2.getKey()), "key", immutableMapEntry, immutableMapEntry2);
            immutableMapEntry2 = immutableMapEntry2.mo8746_k();
        }
    }

    RegularImmutableMap(Map.Entry[] entryArr) {
        ImmutableMapEntry immutableMapEntry;
        int length = entryArr.length;
        this.entries = new ImmutableMapEntry[length];
        int a = C1578K.m4254a(length, 1.2d);
        this.table = new ImmutableMapEntry[a];
        this.mask = a - 1;
        for (int i = 0; i < length; i++) {
            Map.Entry entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            C1630W.m4537h(key, value);
            int cb = C1578K.m4255cb(key.hashCode()) & this.mask;
            ImmutableMapEntry immutableMapEntry2 = this.table[cb];
            if (immutableMapEntry2 == null) {
                immutableMapEntry = new ImmutableMapEntry.TerminalEntry(key, value);
            } else {
                immutableMapEntry = new NonTerminalMapEntry(key, value, immutableMapEntry2);
            }
            this.table[cb] = immutableMapEntry;
            this.entries[i] = immutableMapEntry;
            m4455a(key, immutableMapEntry, immutableMapEntry2);
        }
    }
}
