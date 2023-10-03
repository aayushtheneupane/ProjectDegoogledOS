package p000a.p005b;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: a.b.l */
final class C0025l implements Collection {
    final /* synthetic */ C0026m this$0;

    C0025l(C0026m mVar) {
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
        return this.this$0.colIndexOfValue(obj) >= 0;
    }

    public boolean containsAll(Collection collection) {
        boolean z;
        Iterator it = collection.iterator();
        do {
            z = true;
            if (!it.hasNext()) {
                return true;
            }
            if (this.this$0.colIndexOfValue(it.next()) < 0) {
                z = false;
                continue;
            }
        } while (z);
        return false;
    }

    public boolean isEmpty() {
        return this.this$0.colGetSize() == 0;
    }

    public Iterator iterator() {
        return new C0021h(this.this$0, 1);
    }

    public boolean remove(Object obj) {
        int colIndexOfValue = this.this$0.colIndexOfValue(obj);
        if (colIndexOfValue < 0) {
            return false;
        }
        this.this$0.colRemoveAt(colIndexOfValue);
        return true;
    }

    public boolean removeAll(Collection collection) {
        int colGetSize = this.this$0.colGetSize();
        int i = 0;
        boolean z = false;
        while (i < colGetSize) {
            if (collection.contains(this.this$0.colGetEntry(i, 1))) {
                this.this$0.colRemoveAt(i);
                i--;
                colGetSize--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public boolean retainAll(Collection collection) {
        int colGetSize = this.this$0.colGetSize();
        int i = 0;
        boolean z = false;
        while (i < colGetSize) {
            if (!collection.contains(this.this$0.colGetEntry(i, 1))) {
                this.this$0.colRemoveAt(i);
                i--;
                colGetSize--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public int size() {
        return this.this$0.colGetSize();
    }

    public Object[] toArray() {
        return this.this$0.toArrayHelper(1);
    }

    public Object[] toArray(Object[] objArr) {
        return this.this$0.toArrayHelper(objArr, 1);
    }
}
