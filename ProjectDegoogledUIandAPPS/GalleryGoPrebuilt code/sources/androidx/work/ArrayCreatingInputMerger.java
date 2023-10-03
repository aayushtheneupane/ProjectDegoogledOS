package androidx.work;

import java.lang.reflect.Array;

/* compiled from: PG */
public final class ArrayCreatingInputMerger extends ahg {
    /* renamed from: a */
    private static final Object m1102a(Object obj, Object obj2) {
        int length = Array.getLength(obj);
        Object newInstance = Array.newInstance(obj2.getClass(), length + 1);
        System.arraycopy(obj, 0, newInstance, 0, length);
        Array.set(newInstance, length, obj2);
        return newInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d4, code lost:
        r4 = r6;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.ahf mo474a(java.util.List r14) {
        /*
            r13 = this;
            ahe r0 = new ahe
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            int r2 = r14.size()
            r3 = 0
            r4 = 0
        L_0x0010:
            if (r4 >= r2) goto L_0x00d7
            java.lang.Object r5 = r14.get(r4)
            ahf r5 = (p000.ahf) r5
            java.util.Map r5 = r5.mo470a()
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0024:
            int r6 = r4 + 1
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x00d4
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.getValue()
            java.lang.Class r8 = r6.getClass()
            java.lang.Object r9 = r1.get(r7)
            r10 = 1
            if (r9 != 0) goto L_0x005e
            boolean r8 = r8.isArray()
            if (r8 == 0) goto L_0x004f
            goto L_0x00c9
        L_0x004f:
            java.lang.Class r8 = r6.getClass()
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r10)
            java.lang.reflect.Array.set(r8, r3, r6)
            r6 = r8
            goto L_0x00c9
        L_0x005e:
            java.lang.Class r11 = r9.getClass()
            boolean r12 = r11.equals(r8)
            if (r12 == 0) goto L_0x009f
            boolean r8 = r11.isArray()
            if (r8 == 0) goto L_0x008d
            int r8 = java.lang.reflect.Array.getLength(r9)
            int r10 = java.lang.reflect.Array.getLength(r6)
            java.lang.Class r11 = r9.getClass()
            java.lang.Class r11 = r11.getComponentType()
            int r12 = r8 + r10
            java.lang.Object r11 = java.lang.reflect.Array.newInstance(r11, r12)
            java.lang.System.arraycopy(r9, r3, r11, r3, r8)
            java.lang.System.arraycopy(r6, r3, r11, r8, r10)
            r6 = r11
            goto L_0x00c9
        L_0x008d:
            java.lang.Class r8 = r9.getClass()
            r11 = 2
            java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r11)
            java.lang.reflect.Array.set(r8, r3, r9)
            java.lang.reflect.Array.set(r8, r10, r6)
            r6 = r8
            goto L_0x00c9
        L_0x009f:
            boolean r10 = r11.isArray()
            if (r10 == 0) goto L_0x00b5
            java.lang.Class r10 = r11.getComponentType()
            boolean r10 = r10.equals(r8)
            if (r10 != 0) goto L_0x00b0
            goto L_0x00b5
        L_0x00b0:
            java.lang.Object r6 = m1102a(r9, r6)
            goto L_0x00c9
        L_0x00b5:
            boolean r10 = r8.isArray()
            if (r10 == 0) goto L_0x00ce
            java.lang.Class r8 = r8.getComponentType()
            boolean r8 = r8.equals(r11)
            if (r8 == 0) goto L_0x00ce
            java.lang.Object r6 = m1102a(r6, r9)
        L_0x00c9:
            r1.put(r7, r6)
            goto L_0x0024
        L_0x00ce:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            r14.<init>()
            throw r14
        L_0x00d4:
            r4 = r6
            goto L_0x0010
        L_0x00d7:
            r0.mo469a(r1)
            ahf r14 = r0.mo468a()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.ArrayCreatingInputMerger.mo474a(java.util.List):ahf");
    }
}
