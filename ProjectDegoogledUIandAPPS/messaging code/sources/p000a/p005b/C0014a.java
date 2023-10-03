package p000a.p005b;

import java.util.Map;

/* renamed from: a.b.a */
class C0014a extends C0026m {
    final /* synthetic */ C0015b this$0;

    C0014a(C0015b bVar) {
        this.this$0 = bVar;
    }

    /* access modifiers changed from: protected */
    public void colClear() {
        this.this$0.clear();
    }

    /* access modifiers changed from: protected */
    public Object colGetEntry(int i, int i2) {
        return this.this$0.mArray[(i << 1) + i2];
    }

    /* access modifiers changed from: protected */
    public Map colGetMap() {
        return this.this$0;
    }

    /* access modifiers changed from: protected */
    public int colGetSize() {
        return this.this$0.mSize;
    }

    /* access modifiers changed from: protected */
    public int colIndexOfKey(Object obj) {
        return this.this$0.indexOfKey(obj);
    }

    /* access modifiers changed from: protected */
    public int colIndexOfValue(Object obj) {
        return this.this$0.indexOfValue(obj);
    }

    /* access modifiers changed from: protected */
    public void colPut(Object obj, Object obj2) {
        this.this$0.put(obj, obj2);
    }

    /* access modifiers changed from: protected */
    public void colRemoveAt(int i) {
        this.this$0.removeAt(i);
    }

    /* access modifiers changed from: protected */
    public Object colSetValue(int i, Object obj) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.this$0.mArray;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }
}
