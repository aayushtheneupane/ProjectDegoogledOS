package p000a.p005b;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* renamed from: a.b.k */
final class C0024k implements Iterator, Map.Entry {
    int mEnd;
    boolean mEntryValid = false;
    int mIndex;
    final /* synthetic */ C0026m this$0;

    C0024k(C0026m mVar) {
        this.this$0 = mVar;
        this.mEnd = mVar.colGetSize() - 1;
        this.mIndex = -1;
    }

    public boolean equals(Object obj) {
        if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        } else if (!(obj instanceof Map.Entry)) {
            return false;
        } else {
            Map.Entry entry = (Map.Entry) obj;
            if (!C0018e.m26b(entry.getKey(), this.this$0.colGetEntry(this.mIndex, 0)) || !C0018e.m26b(entry.getValue(), this.this$0.colGetEntry(this.mIndex, 1))) {
                return false;
            }
            return true;
        }
    }

    public Object getKey() {
        if (this.mEntryValid) {
            return this.this$0.colGetEntry(this.mIndex, 0);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public Object getValue() {
        if (this.mEntryValid) {
            return this.this$0.colGetEntry(this.mIndex, 1);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public boolean hasNext() {
        return this.mIndex < this.mEnd;
    }

    public int hashCode() {
        int i;
        if (this.mEntryValid) {
            int i2 = 0;
            Object colGetEntry = this.this$0.colGetEntry(this.mIndex, 0);
            Object colGetEntry2 = this.this$0.colGetEntry(this.mIndex, 1);
            if (colGetEntry == null) {
                i = 0;
            } else {
                i = colGetEntry.hashCode();
            }
            if (colGetEntry2 != null) {
                i2 = colGetEntry2.hashCode();
            }
            return i ^ i2;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public Object next() {
        if (this.mIndex < this.mEnd) {
            this.mIndex++;
            this.mEntryValid = true;
            return this;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.mEntryValid) {
            this.this$0.colRemoveAt(this.mIndex);
            this.mIndex--;
            this.mEnd--;
            this.mEntryValid = false;
            return;
        }
        throw new IllegalStateException();
    }

    public Object setValue(Object obj) {
        if (this.mEntryValid) {
            return this.this$0.colSetValue(this.mIndex, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.mEntryValid) {
            sb.append(this.this$0.colGetEntry(this.mIndex, 0));
            sb.append("=");
            if (this.mEntryValid) {
                sb.append(this.this$0.colGetEntry(this.mIndex, 1));
                return sb.toString();
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }
}
