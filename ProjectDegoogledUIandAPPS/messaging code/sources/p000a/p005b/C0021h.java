package p000a.p005b;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: a.b.h */
final class C0021h implements Iterator {
    boolean mCanRemove = false;
    int mIndex;
    final int mOffset;
    int mSize;
    final /* synthetic */ C0026m this$0;

    C0021h(C0026m mVar, int i) {
        this.this$0 = mVar;
        this.mOffset = i;
        this.mSize = mVar.colGetSize();
    }

    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }

    public Object next() {
        if (this.mIndex < this.mSize) {
            Object colGetEntry = this.this$0.colGetEntry(this.mIndex, this.mOffset);
            this.mIndex++;
            this.mCanRemove = true;
            return colGetEntry;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.mCanRemove) {
            this.mIndex--;
            this.mSize--;
            this.mCanRemove = false;
            this.this$0.colRemoveAt(this.mIndex);
            return;
        }
        throw new IllegalStateException();
    }
}
