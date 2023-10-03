package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.ArrayList;
import java.util.List;

final class ProtobufArrayList<E> extends AbstractProtobufList<E> {
    private static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList<>(new ArrayList(10));
    private final List<E> list;

    static {
        EMPTY_LIST.makeImmutable();
    }

    private ProtobufArrayList(List<E> list2) {
        this.list = list2;
    }

    public static <E> ProtobufArrayList<E> emptyList() {
        return EMPTY_LIST;
    }

    public void add(int i, E e) {
        ensureIsMutable();
        this.list.add(i, e);
        this.modCount++;
    }

    public E get(int i) {
        return this.list.get(i);
    }

    public Internal.ProtobufList mutableCopyWithCapacity(int i) {
        if (i >= this.list.size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.list);
            return new ProtobufArrayList(arrayList);
        }
        throw new IllegalArgumentException();
    }

    public E remove(int i) {
        ensureIsMutable();
        E remove = this.list.remove(i);
        this.modCount++;
        return remove;
    }

    public E set(int i, E e) {
        ensureIsMutable();
        E e2 = this.list.set(i, e);
        this.modCount++;
        return e2;
    }

    public int size() {
        return this.list.size();
    }
}
