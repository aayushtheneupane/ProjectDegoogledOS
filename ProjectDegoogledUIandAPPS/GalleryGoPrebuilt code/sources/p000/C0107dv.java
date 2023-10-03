package p000;

import java.util.Arrays;

/* renamed from: dv */
/* compiled from: PG */
public final class C0107dv {

    /* renamed from: a */
    public C0114eb f7438a = null;

    /* renamed from: b */
    public float f7439b = 0.0f;

    /* renamed from: c */
    public boolean f7440c = false;

    /* renamed from: d */
    public final C0106du f7441d;

    /* renamed from: e */
    public boolean f7442e = false;

    public C0107dv(C0108dw dwVar) {
        this.f7441d = new C0106du(this, dwVar);
    }

    /* renamed from: a */
    public final void mo4470a(C0114eb ebVar, C0114eb ebVar2) {
        this.f7441d.mo4448a(ebVar, 1.0f);
        this.f7441d.mo4448a(ebVar2, -1.0f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4472a(C0114eb ebVar, C0114eb ebVar2, int i, float f, C0114eb ebVar3, C0114eb ebVar4, int i2) {
        if (ebVar2 == ebVar3) {
            this.f7441d.mo4448a(ebVar, 1.0f);
            this.f7441d.mo4448a(ebVar4, 1.0f);
            this.f7441d.mo4448a(ebVar2, -2.0f);
        } else if (f == 0.5f) {
            this.f7441d.mo4448a(ebVar, 1.0f);
            this.f7441d.mo4448a(ebVar2, -1.0f);
            this.f7441d.mo4448a(ebVar3, -1.0f);
            this.f7441d.mo4448a(ebVar4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.f7439b = (float) ((-i) + i2);
            }
        } else if (f <= 0.0f) {
            this.f7441d.mo4448a(ebVar, -1.0f);
            this.f7441d.mo4448a(ebVar2, 1.0f);
            this.f7439b = (float) i;
        } else if (f < 1.0f) {
            float f2 = 1.0f - f;
            this.f7441d.mo4448a(ebVar, f2);
            this.f7441d.mo4448a(ebVar2, -f2);
            this.f7441d.mo4448a(ebVar3, -f);
            this.f7441d.mo4448a(ebVar4, f);
            if (i > 0 || i2 > 0) {
                this.f7439b = (((float) (-i)) * f2) + (((float) i2) * f);
            }
        } else {
            this.f7441d.mo4448a(ebVar3, -1.0f);
            this.f7441d.mo4448a(ebVar4, 1.0f);
            this.f7439b = (float) i2;
        }
    }

    /* renamed from: a */
    public final void mo4474a(C0114eb ebVar, C0114eb ebVar2, C0114eb ebVar3, C0114eb ebVar4, float f) {
        this.f7441d.mo4448a(ebVar, -1.0f);
        this.f7441d.mo4448a(ebVar2, 1.0f);
        this.f7441d.mo4448a(ebVar3, f);
        this.f7441d.mo4448a(ebVar4, -f);
    }

    /* renamed from: a */
    public final void mo4466a(float f, float f2, float f3, C0114eb ebVar, int i, C0114eb ebVar2, int i2, C0114eb ebVar3, int i3, C0114eb ebVar4, int i4) {
        if (f2 == 0.0f || f == f3) {
            this.f7439b = (float) (((-i) - i2) + i3 + i4);
            this.f7441d.mo4448a(ebVar, 1.0f);
            this.f7441d.mo4448a(ebVar2, -1.0f);
            this.f7441d.mo4448a(ebVar4, 1.0f);
            this.f7441d.mo4448a(ebVar3, -1.0f);
            return;
        }
        float f4 = (f / f2) / (f3 / f2);
        this.f7439b = ((float) ((-i) - i2)) + (((float) i3) * f4) + (((float) i4) * f4);
        this.f7441d.mo4448a(ebVar, 1.0f);
        this.f7441d.mo4448a(ebVar2, -1.0f);
        this.f7441d.mo4448a(ebVar4, f4);
        this.f7441d.mo4448a(ebVar3, -f4);
    }

    /* renamed from: a */
    public final void mo4469a(C0114eb ebVar, int i) {
        if (i < 0) {
            this.f7439b = (float) (-i);
            this.f7441d.mo4448a(ebVar, 1.0f);
            return;
        }
        this.f7439b = (float) i;
        this.f7441d.mo4448a(ebVar, -1.0f);
    }

    /* renamed from: a */
    public final void mo4471a(C0114eb ebVar, C0114eb ebVar2, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.f7439b = (float) i;
            if (z) {
                this.f7441d.mo4448a(ebVar, 1.0f);
                this.f7441d.mo4448a(ebVar2, -1.0f);
                return;
            }
        }
        this.f7441d.mo4448a(ebVar, -1.0f);
        this.f7441d.mo4448a(ebVar2, 1.0f);
    }

    /* renamed from: a */
    public final void mo4473a(C0114eb ebVar, C0114eb ebVar2, C0114eb ebVar3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.f7439b = (float) i;
            if (z) {
                this.f7441d.mo4448a(ebVar, 1.0f);
                this.f7441d.mo4448a(ebVar2, -1.0f);
                this.f7441d.mo4448a(ebVar3, -1.0f);
                return;
            }
        }
        this.f7441d.mo4448a(ebVar, -1.0f);
        this.f7441d.mo4448a(ebVar2, 1.0f);
        this.f7441d.mo4448a(ebVar3, 1.0f);
    }

    /* renamed from: b */
    public final void mo4475b(C0114eb ebVar, C0114eb ebVar2, C0114eb ebVar3, int i) {
        boolean z;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            } else {
                z = false;
            }
            this.f7439b = (float) i;
            if (z) {
                this.f7441d.mo4448a(ebVar, 1.0f);
                this.f7441d.mo4448a(ebVar2, -1.0f);
                this.f7441d.mo4448a(ebVar3, 1.0f);
                return;
            }
        }
        this.f7441d.mo4448a(ebVar, -1.0f);
        this.f7441d.mo4448a(ebVar2, 1.0f);
        this.f7441d.mo4448a(ebVar3, -1.0f);
    }

    /* renamed from: a */
    public final void mo4468a(C0114eb ebVar) {
        C0114eb ebVar2 = this.f7438a;
        if (ebVar2 != null) {
            this.f7441d.mo4448a(ebVar2, -1.0f);
            this.f7438a = null;
        }
        float f = -this.f7441d.mo4445a(ebVar);
        this.f7438a = ebVar;
        if (f != 1.0f) {
            this.f7439b /= f;
            C0106du duVar = this.f7441d;
            int i = duVar.f7377f;
            int i2 = 0;
            while (i != -1 && i2 < duVar.f7372a) {
                float[] fArr = duVar.f7376e;
                fArr[i] = fArr[i] / f;
                i = duVar.f7375d[i];
                i2++;
            }
        }
    }

    public final String toString() {
        String str;
        boolean z;
        String str2;
        if (this.f7438a != null) {
            str = "" + this.f7438a;
        } else {
            str = "0";
        }
        String str3 = str + " = ";
        if (this.f7439b != 0.0f) {
            str3 = str3 + this.f7439b;
            z = true;
        } else {
            z = false;
        }
        int i = this.f7441d.f7372a;
        for (int i2 = 0; i2 < i; i2++) {
            C0114eb a = this.f7441d.mo4446a(i2);
            if (a != null) {
                float b = this.f7441d.mo4449b(i2);
                String ebVar = a.toString();
                if (z) {
                    if (b <= 0.0f) {
                        str2 = str2 + " - ";
                        b = -b;
                    } else {
                        str2 = str2 + " + ";
                    }
                } else if (b < 0.0f) {
                    str2 = str2 + "- ";
                    b = -b;
                }
                if (b != 1.0f) {
                    str2 = str2 + b + " " + ebVar;
                } else {
                    str2 = str2 + ebVar;
                }
                z = true;
            }
        }
        if (z) {
            return str2;
        }
        return str2 + "0.0";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4465a() {
        C0106du duVar = this.f7441d;
        int i = duVar.f7377f;
        int i2 = 0;
        while (i != -1 && i2 < duVar.f7372a) {
            C0114eb ebVar = duVar.f7373b.f7471c[duVar.f7374c[i]];
            int i3 = 0;
            while (true) {
                int i4 = ebVar.f7819g;
                if (i3 < i4) {
                    if (ebVar.f7818f[i3] == this) {
                        break;
                    }
                    i3++;
                } else {
                    C0107dv[] dvVarArr = ebVar.f7818f;
                    int length = dvVarArr.length;
                    if (i4 >= length) {
                        ebVar.f7818f = (C0107dv[]) Arrays.copyOf(dvVarArr, length + length);
                    }
                    C0107dv[] dvVarArr2 = ebVar.f7818f;
                    int i5 = ebVar.f7819g;
                    dvVarArr2[i5] = this;
                    ebVar.f7819g = i5 + 1;
                }
            }
            i = duVar.f7375d[i];
            i2++;
        }
    }

    /* renamed from: a */
    public final void mo4467a(C0107dv dvVar) {
        this.f7441d.mo4447a(this, dvVar);
    }
}
