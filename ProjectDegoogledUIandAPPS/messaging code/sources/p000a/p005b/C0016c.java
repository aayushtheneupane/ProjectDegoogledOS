package p000a.p005b;

import java.util.Map;

/* renamed from: a.b.c */
class C0016c extends C0026m {
    final /* synthetic */ C0017d this$0;

    C0016c(C0017d dVar) {
        this.this$0 = dVar;
    }

    /* access modifiers changed from: protected */
    public void colClear() {
        this.this$0.clear();
    }

    /* access modifiers changed from: protected */
    public Object colGetEntry(int i, int i2) {
        return this.this$0.mArray[i];
    }

    /* access modifiers changed from: protected */
    public Map colGetMap() {
        throw new UnsupportedOperationException("not a map");
    }

    /* access modifiers changed from: protected */
    public int colGetSize() {
        return this.this$0.mSize;
    }

    /* access modifiers changed from: protected */
    public int colIndexOfKey(Object obj) {
        return this.this$0.indexOf(obj);
    }

    /* access modifiers changed from: protected */
    public int colIndexOfValue(Object obj) {
        return this.this$0.indexOf(obj);
    }

    /* access modifiers changed from: protected */
    public void colPut(Object obj, Object obj2) {
        this.this$0.add(obj);
    }

    /* access modifiers changed from: protected */
    public void colRemoveAt(int i) {
        this.this$0.removeAt(i);
    }

    /* access modifiers changed from: protected */
    public Object colSetValue(int i, Object obj) {
        throw new UnsupportedOperationException("not a map");
    }
}
