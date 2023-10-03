package p000;

/* renamed from: htm */
/* compiled from: PG */
public final class htm extends hsd {

    /* renamed from: d */
    private Object[] f13384d;

    /* renamed from: e */
    private int f13385e;

    public htm() {
        super(4);
    }

    public htm(int i) {
        super(i);
        this.f13384d = new Object[hto.m12128b(i)];
    }

    /* renamed from: c */
    public final void mo7874b(Object obj) {
        int length;
        ife.m12898e(obj);
        if (this.f13384d == null || hto.m12128b(this.f13339b) > (length = this.f13384d.length)) {
            this.f13384d = null;
            super.mo7873a(obj);
            return;
        }
        int i = length - 1;
        int hashCode = obj.hashCode();
        int d = ife.m12892d(hashCode);
        while (true) {
            int i2 = d & i;
            Object[] objArr = this.f13384d;
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                objArr[i2] = obj;
                this.f13385e += hashCode;
                super.mo7873a(obj);
                return;
            } else if (!obj2.equals(obj)) {
                d = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public final void mo7994a(Object... objArr) {
        if (this.f13384d != null) {
            for (Object c : objArr) {
                mo7874b(c);
            }
            return;
        }
        ife.m12859a(objArr);
        int i = this.f13339b;
        int length = objArr.length;
        super.mo7871a(i + length);
        System.arraycopy(objArr, 0, this.f13338a, this.f13339b, length);
        this.f13339b += length;
    }

    /* renamed from: b */
    public final void mo7995b(Iterable iterable) {
        ife.m12898e((Object) iterable);
        if (this.f13384d != null) {
            for (Object c : iterable) {
                mo7874b(c);
            }
            return;
        }
        super.mo7872a(iterable);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: hvf} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: hto} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: hvf} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: hvf} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.hto mo7993a() {
        /*
            r8 = this;
            int r0 = r8.f13339b
            if (r0 == 0) goto L_0x005d
            r1 = 1
            if (r0 == r1) goto L_0x0053
            java.lang.Object[] r2 = r8.f13384d
            if (r2 != 0) goto L_0x000c
            goto L_0x003e
        L_0x000c:
            int r0 = p000.hto.m12128b(r0)
            java.lang.Object[] r2 = r8.f13384d
            int r2 = r2.length
            if (r0 != r2) goto L_0x003e
            int r0 = r8.f13339b
            java.lang.Object[] r2 = r8.f13338a
            int r2 = r2.length
            boolean r0 = p000.hto.m12127a((int) r0, (int) r2)
            if (r0 == 0) goto L_0x002a
            java.lang.Object[] r0 = r8.f13338a
            int r2 = r8.f13339b
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            r3 = r0
            goto L_0x002d
        L_0x002a:
            java.lang.Object[] r0 = r8.f13338a
            r3 = r0
        L_0x002d:
            hvf r0 = new hvf
            int r4 = r8.f13385e
            java.lang.Object[] r5 = r8.f13384d
            int r2 = r5.length
            int r6 = r2 + -1
            int r7 = r8.f13339b
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)
            goto L_0x004c
        L_0x003e:
            int r0 = r8.f13339b
            java.lang.Object[] r2 = r8.f13338a
            hto r0 = p000.hto.m12119a((int) r0, (java.lang.Object[]) r2)
            int r2 = r0.size()
            r8.f13339b = r2
        L_0x004c:
            r8.f13340c = r1
            r1 = 0
            r8.f13384d = r1
            return r0
        L_0x0053:
            java.lang.Object[] r0 = r8.f13338a
            r1 = 0
            r0 = r0[r1]
            hto r0 = p000.hto.m12120a((java.lang.Object) r0)
            return r0
        L_0x005d:
            hvf r0 = p000.hvf.f13465a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.htm.mo7993a():hto");
    }
}
