package p000;

/* renamed from: ags */
/* compiled from: PG */
class ags extends ggf {

    /* renamed from: l */
    public C0240ir[] f419l = null;

    /* renamed from: m */
    public String f420m;

    /* renamed from: n */
    public int f421n = 0;

    public ags() {
        super((byte[]) null);
    }

    /* renamed from: a */
    public boolean mo376a() {
        return false;
    }

    public C0240ir[] getPathData() {
        return this.f419l;
    }

    public String getPathName() {
        return this.f420m;
    }

    public ags(ags ags) {
        super((byte[]) null);
        this.f420m = ags.f420m;
        this.f419l = C0257jh.m14485a(ags.f419l);
    }

    public void setPathData(C0240ir[] irVarArr) {
        C0240ir[] irVarArr2 = this.f419l;
        if (!(irVarArr2 == null || irVarArr == null || irVarArr2.length != irVarArr.length)) {
            int i = 0;
            while (i < irVarArr2.length) {
                C0240ir irVar = irVarArr2[i];
                char c = irVar.f14812a;
                C0240ir irVar2 = irVarArr[i];
                if (c == irVar2.f14812a && irVar.f14813b.length == irVar2.f14813b.length) {
                    i++;
                }
            }
            C0240ir[] irVarArr3 = this.f419l;
            for (int i2 = 0; i2 < irVarArr.length; i2++) {
                irVarArr3[i2].f14812a = irVarArr[i2].f14812a;
                int i3 = 0;
                while (true) {
                    float[] fArr = irVarArr[i2].f14813b;
                    if (i3 >= fArr.length) {
                        break;
                    }
                    irVarArr3[i2].f14813b[i3] = fArr[i3];
                    i3++;
                }
            }
            return;
        }
        this.f419l = C0257jh.m14485a(irVarArr);
    }
}
