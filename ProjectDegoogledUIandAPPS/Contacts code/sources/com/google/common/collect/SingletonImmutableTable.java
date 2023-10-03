package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Table;
import java.util.Map;

class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    final C singleColumnKey;
    final R singleRowKey;
    final V singleValue;

    public int size() {
        return 1;
    }

    SingletonImmutableTable(R r, C c, V v) {
        Preconditions.checkNotNull(r);
        this.singleRowKey = r;
        Preconditions.checkNotNull(c);
        this.singleColumnKey = c;
        Preconditions.checkNotNull(v);
        this.singleValue = v;
    }

    SingletonImmutableTable(Table.Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }

    public ImmutableMap<R, V> column(C c) {
        Preconditions.checkNotNull(c);
        if (containsColumn(c)) {
            return ImmutableMap.m39of(this.singleRowKey, this.singleValue);
        }
        return ImmutableMap.m38of();
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.m39of(this.singleColumnKey, ImmutableMap.m39of(this.singleRowKey, this.singleValue));
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.m39of(this.singleRowKey, ImmutableMap.m39of(this.singleColumnKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return ImmutableSet.m62of(ImmutableTable.cellOf(this.singleRowKey, this.singleColumnKey, this.singleValue));
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        return ImmutableSet.m62of(this.singleValue);
    }
}
