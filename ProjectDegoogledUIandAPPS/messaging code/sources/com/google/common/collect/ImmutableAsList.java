package com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

abstract class ImmutableAsList extends ImmutableList {

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableCollection collection;

        SerializedForm(ImmutableCollection immutableCollection) {
            this.collection = immutableCollection;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.collection.mo8648Ol();
        }
    }

    ImmutableAsList() {
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ql */
    public abstract ImmutableCollection mo8695Ql();

    public boolean contains(Object obj) {
        return mo8695Ql().contains(obj);
    }

    public boolean isEmpty() {
        return mo8695Ql().isEmpty();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return mo8695Ql().mo8636pl();
    }

    public int size() {
        return mo8695Ql().size();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(mo8695Ql());
    }
}
