package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* renamed from: com.google.common.collect.l */
class C1672l extends AbstractCollection {

    /* renamed from: FP */
    final C1672l f2535FP;

    /* renamed from: GP */
    final Collection f2536GP;
    Collection delegate;
    final Object key;
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    C1672l(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, Collection collection, C1672l lVar) {
        Collection collection2;
        this.this$0 = abstractMapBasedMultimap;
        this.key = obj;
        this.delegate = collection;
        this.f2535FP = lVar;
        if (lVar == null) {
            collection2 = null;
        } else {
            collection2 = lVar.delegate;
        }
        this.f2536GP = collection2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ll */
    public void mo9196Ll() {
        C1672l lVar = this.f2535FP;
        if (lVar != null) {
            lVar.mo9196Ll();
        } else {
            this.this$0.map.put(this.key, this.delegate);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ml */
    public void mo9197Ml() {
        Collection collection;
        C1672l lVar = this.f2535FP;
        if (lVar != null) {
            lVar.mo9197Ml();
            if (this.f2535FP.delegate != this.f2536GP) {
                throw new ConcurrentModificationException();
            }
        } else if (this.delegate.isEmpty() && (collection = (Collection) this.this$0.map.get(this.key)) != null) {
            this.delegate = collection;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Nl */
    public void mo9198Nl() {
        C1672l lVar = this.f2535FP;
        if (lVar != null) {
            lVar.mo9198Nl();
        } else if (this.delegate.isEmpty()) {
            this.this$0.map.remove(this.key);
        }
    }

    public boolean add(Object obj) {
        mo9197Ml();
        boolean isEmpty = this.delegate.isEmpty();
        boolean add = this.delegate.add(obj);
        if (add) {
            AbstractMapBasedMultimap.m4059b(this.this$0);
            if (isEmpty) {
                mo9196Ll();
            }
        }
        return add;
    }

    public boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.delegate.addAll(collection);
        if (addAll) {
            AbstractMapBasedMultimap.m4053a(this.this$0, this.delegate.size() - size);
            if (size == 0) {
                mo9196Ll();
            }
        }
        return addAll;
    }

    public void clear() {
        int size = size();
        if (size != 0) {
            this.delegate.clear();
            AbstractMapBasedMultimap.m4060b(this.this$0, size);
            mo9198Nl();
        }
    }

    public boolean contains(Object obj) {
        mo9197Ml();
        return this.delegate.contains(obj);
    }

    public boolean containsAll(Collection collection) {
        mo9197Ml();
        return this.delegate.containsAll(collection);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        mo9197Ml();
        return this.delegate.equals(obj);
    }

    public int hashCode() {
        mo9197Ml();
        return this.delegate.hashCode();
    }

    public Iterator iterator() {
        mo9197Ml();
        return new C1669k(this);
    }

    public boolean remove(Object obj) {
        mo9197Ml();
        boolean remove = this.delegate.remove(obj);
        if (remove) {
            AbstractMapBasedMultimap.m4061c(this.this$0);
            mo9198Nl();
        }
        return remove;
    }

    public boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.delegate.removeAll(collection);
        if (removeAll) {
            AbstractMapBasedMultimap.m4053a(this.this$0, this.delegate.size() - size);
            mo9198Nl();
        }
        return removeAll;
    }

    public boolean retainAll(Collection collection) {
        if (collection != null) {
            int size = size();
            boolean retainAll = this.delegate.retainAll(collection);
            if (retainAll) {
                AbstractMapBasedMultimap.m4053a(this.this$0, this.delegate.size() - size);
                mo9198Nl();
            }
            return retainAll;
        }
        throw new NullPointerException();
    }

    public int size() {
        mo9197Ml();
        return this.delegate.size();
    }

    public String toString() {
        mo9197Ml();
        return this.delegate.toString();
    }
}
