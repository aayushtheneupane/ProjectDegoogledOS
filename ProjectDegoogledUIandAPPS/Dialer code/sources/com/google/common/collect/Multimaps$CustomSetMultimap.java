package com.google.common.collect;

import com.google.common.base.Supplier;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

class Multimaps$CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
    private static final long serialVersionUID = 0;
    transient Supplier<? extends Set<V>> factory;

    Multimaps$CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
        super(map);
        if (supplier != null) {
            this.factory = supplier;
            return;
        }
        throw new NullPointerException();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.factory = (Supplier) objectInputStream.readObject();
        setMap((Map) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.factory);
        objectOutputStream.writeObject(backingMap());
    }

    /* access modifiers changed from: protected */
    public Set<V> createCollection() {
        return (Set) this.factory.get();
    }
}
