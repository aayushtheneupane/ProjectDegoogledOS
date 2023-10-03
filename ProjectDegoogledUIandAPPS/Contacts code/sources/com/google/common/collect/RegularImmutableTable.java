package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    /* access modifiers changed from: package-private */
    public abstract Table.Cell<R, C, V> getCell(int i);

    /* access modifiers changed from: package-private */
    public abstract V getValue(int i);

    RegularImmutableTable() {
    }

    /* access modifiers changed from: package-private */
    public final ImmutableSet<Table.Cell<R, C, V>> createCellSet() {
        return isEmpty() ? ImmutableSet.m61of() : new CellSet();
    }

    private final class CellSet extends ImmutableSet<Table.Cell<R, C, V>> {
        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }

        private CellSet() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        public UnmodifiableIterator<Table.Cell<R, C, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Table.Cell<R, C, V>> createAsList() {
            return new ImmutableAsList<Table.Cell<R, C, V>>() {
                public Table.Cell<R, C, V> get(int i) {
                    return RegularImmutableTable.this.getCell(i);
                }

                /* access modifiers changed from: package-private */
                public ImmutableCollection<Table.Cell<R, C, V>> delegateCollection() {
                    return CellSet.this;
                }
            };
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Object obj2 = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
            if (obj2 == null || !obj2.equals(cell.getValue())) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final ImmutableCollection<V> createValues() {
        return isEmpty() ? ImmutableList.m19of() : new Values();
    }

    private final class Values extends ImmutableList<V> {
        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        private Values() {
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }

        public V get(int i) {
            return RegularImmutableTable.this.getValue(i);
        }
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(List<Table.Cell<R, C, V>> list, final Comparator<? super R> comparator, final Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(list);
        if (!(comparator == null && comparator2 == null)) {
            Collections.sort(list, new Comparator<Table.Cell<R, C, V>>() {
                public int compare(Table.Cell<R, C, V> cell, Table.Cell<R, C, V> cell2) {
                    int i;
                    Comparator comparator = comparator;
                    if (comparator == null) {
                        i = 0;
                    } else {
                        i = comparator.compare(cell.getRowKey(), cell2.getRowKey());
                    }
                    if (i != 0) {
                        return i;
                    }
                    Comparator comparator2 = comparator2;
                    if (comparator2 == null) {
                        return 0;
                    }
                    return comparator2.compare(cell.getColumnKey(), cell2.getColumnKey());
                }
            });
        }
        return forCellsInternal(list, comparator, comparator2);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> forCells(Iterable<Table.Cell<R, C, V>> iterable) {
        return forCellsInternal(iterable, (Comparator) null, (Comparator) null);
    }

    private static final <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> iterable, Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        ImmutableSet.Builder builder2 = ImmutableSet.builder();
        ImmutableList<E> copyOf = ImmutableList.copyOf(iterable);
        UnmodifiableIterator<E> it = copyOf.iterator();
        while (it.hasNext()) {
            Table.Cell cell = (Table.Cell) it.next();
            builder.add(cell.getRowKey());
            builder2.add(cell.getColumnKey());
        }
        ImmutableSet build = builder.build();
        if (comparator != null) {
            ArrayList newArrayList = Lists.newArrayList(build);
            Collections.sort(newArrayList, comparator);
            build = ImmutableSet.copyOf(newArrayList);
        }
        ImmutableSet build2 = builder2.build();
        if (comparator2 != null) {
            ArrayList newArrayList2 = Lists.newArrayList(build2);
            Collections.sort(newArrayList2, comparator2);
            build2 = ImmutableSet.copyOf(newArrayList2);
        }
        if (((long) copyOf.size()) > (((long) build.size()) * ((long) build2.size())) / 2) {
            return new DenseImmutableTable(copyOf, build, build2);
        }
        return new SparseImmutableTable(copyOf, build, build2);
    }
}
