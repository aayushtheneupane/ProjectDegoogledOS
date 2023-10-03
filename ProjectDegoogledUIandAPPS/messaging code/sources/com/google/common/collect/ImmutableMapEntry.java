package com.google.common.collect;

abstract class ImmutableMapEntry extends ImmutableEntry {

    final class TerminalEntry extends ImmutableMapEntry {
        TerminalEntry(Object obj, Object obj2) {
            super(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: _k */
        public ImmutableMapEntry mo8746_k() {
            return null;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: al */
        public ImmutableMapEntry mo8747al() {
            return null;
        }
    }

    ImmutableMapEntry(Object obj, Object obj2) {
        super(obj, obj2);
        C1630W.m4537h(obj, obj2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: _k */
    public abstract ImmutableMapEntry mo8746_k();

    /* access modifiers changed from: package-private */
    /* renamed from: al */
    public abstract ImmutableMapEntry mo8747al();

    ImmutableMapEntry(ImmutableMapEntry immutableMapEntry) {
        super(immutableMapEntry.getKey(), immutableMapEntry.getValue());
    }
}
