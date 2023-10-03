package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry;

/* renamed from: com.google.common.collect.S */
public class C1614S {
    ImmutableMapEntry.TerminalEntry[] entries = new ImmutableMapEntry.TerminalEntry[4];
    int size = 0;

    public ImmutableMap build() {
        int i = this.size;
        if (i == 0) {
            return ImmutableMap.m4215ql();
        }
        if (i != 1) {
            return new RegularImmutableMap(i, this.entries);
        }
        return ImmutableMap.m4214g(this.entries[0].getKey(), this.entries[0].getValue());
    }

    public C1614S put(Object obj, Object obj2) {
        int i = this.size + 1;
        ImmutableMapEntry.TerminalEntry[] terminalEntryArr = this.entries;
        if (i > terminalEntryArr.length) {
            this.entries = (ImmutableMapEntry.TerminalEntry[]) C1638_a.m4553a(terminalEntryArr, C1602N.m4410L(terminalEntryArr.length, i));
        }
        ImmutableMapEntry.TerminalEntry f = ImmutableMap.m4213f(obj, obj2);
        ImmutableMapEntry.TerminalEntry[] terminalEntryArr2 = this.entries;
        int i2 = this.size;
        this.size = i2 + 1;
        terminalEntryArr2[i2] = f;
        return this;
    }
}
