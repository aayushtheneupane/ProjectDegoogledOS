package p000a.p005b;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: a.b.j */
final class C0023j implements Set {
    final /* synthetic */ C0026m this$0;

    C0023j(C0026m mVar) {
        this.this$0 = mVar;
    }

    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        this.this$0.colClear();
    }

    public boolean contains(Object obj) {
        return this.this$0.colIndexOfKey(obj) >= 0;
    }

    public boolean containsAll(Collection collection) {
        Map colGetMap = this.this$0.colGetMap();
        for (Object containsKey : collection) {
            if (!colGetMap.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        return C0026m.equalsSetHelper(this, obj);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (int colGetSize = this.this$0.colGetSize() - 1; colGetSize >= 0; colGetSize--) {
            Object colGetEntry = this.this$0.colGetEntry(colGetSize, 0);
            if (colGetEntry == null) {
                i = 0;
            } else {
                i = colGetEntry.hashCode();
            }
            i2 += i;
        }
        return i2;
    }

    public boolean isEmpty() {
        return this.this$0.colGetSize() == 0;
    }

    public Iterator iterator() {
        return new C0021h(this.this$0, 0);
    }

    public boolean remove(Object obj) {
        int colIndexOfKey = this.this$0.colIndexOfKey(obj);
        if (colIndexOfKey < 0) {
            return false;
        }
        this.this$0.colRemoveAt(colIndexOfKey);
        return true;
    }

    public boolean removeAll(Collection collection) {
        Map colGetMap = this.this$0.colGetMap();
        int size = colGetMap.size();
        for (Object remove : collection) {
            colGetMap.remove(remove);
        }
        return size != colGetMap.size();
    }

    public boolean retainAll(Collection collection) {
        return C0026m.retainAllHelper(this.this$0.colGetMap(), collection);
    }

    public int size() {
        return this.this$0.colGetSize();
    }

    public Object[] toArray() {
        return this.this$0.toArrayHelper(0);
    }

    public Object[] toArray(Object[] objArr) {
        return this.this$0.toArrayHelper(objArr, 0);
    }
}
