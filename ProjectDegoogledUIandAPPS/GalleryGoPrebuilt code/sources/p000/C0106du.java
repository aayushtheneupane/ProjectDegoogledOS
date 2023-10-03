package p000;

import java.util.Arrays;

/* renamed from: du */
/* compiled from: PG */
public final class C0106du {

    /* renamed from: a */
    public int f7372a = 0;

    /* renamed from: b */
    public final C0108dw f7373b;

    /* renamed from: c */
    public int[] f7374c = new int[8];

    /* renamed from: d */
    public int[] f7375d = new int[8];

    /* renamed from: e */
    public float[] f7376e = new float[8];

    /* renamed from: f */
    public int f7377f = -1;

    /* renamed from: g */
    public int f7378g = -1;

    /* renamed from: h */
    public boolean f7379h = false;

    /* renamed from: i */
    private final C0107dv f7380i;

    /* renamed from: j */
    private int f7381j = 8;

    public C0106du(C0107dv dvVar, C0108dw dwVar) {
        this.f7380i = dvVar;
        this.f7373b = dwVar;
    }

    /* renamed from: b */
    public final void mo4451b(C0114eb ebVar, float f) {
        if (f != 0.0f) {
            int i = this.f7377f;
            if (i != -1) {
                int i2 = 0;
                int i3 = -1;
                while (i != -1 && i2 < this.f7372a) {
                    int i4 = this.f7374c[i];
                    int i5 = ebVar.f7813a;
                    if (i4 == i5) {
                        float[] fArr = this.f7376e;
                        float f2 = fArr[i] + f;
                        fArr[i] = f2;
                        if (f2 == 0.0f) {
                            if (i != this.f7377f) {
                                int[] iArr = this.f7375d;
                                iArr[i3] = iArr[i];
                            } else {
                                this.f7377f = this.f7375d[i];
                            }
                            this.f7373b.f7471c[i4].mo4646a(this.f7380i);
                            if (this.f7379h) {
                                this.f7378g = i;
                            }
                            this.f7372a--;
                            return;
                        }
                        return;
                    }
                    if (i4 < i5) {
                        i3 = i;
                    }
                    i = this.f7375d[i];
                    i2++;
                }
                int i6 = this.f7378g;
                int i7 = i6 + 1;
                if (this.f7379h) {
                    int[] iArr2 = this.f7374c;
                    if (iArr2[i6] != -1) {
                        i6 = iArr2.length;
                    }
                } else {
                    i6 = i7;
                }
                int length = this.f7374c.length;
                if (i6 >= length && this.f7372a < length) {
                    int i8 = 0;
                    while (true) {
                        int[] iArr3 = this.f7374c;
                        if (i8 < iArr3.length) {
                            if (iArr3[i8] == -1) {
                                i6 = i8;
                                break;
                            }
                            i8++;
                        } else {
                            break;
                        }
                    }
                }
                int length2 = this.f7374c.length;
                if (i6 >= length2) {
                    int i9 = this.f7381j;
                    int i10 = i9 + i9;
                    this.f7381j = i10;
                    this.f7379h = false;
                    this.f7378g = length2 - 1;
                    this.f7376e = Arrays.copyOf(this.f7376e, i10);
                    this.f7374c = Arrays.copyOf(this.f7374c, this.f7381j);
                    this.f7375d = Arrays.copyOf(this.f7375d, this.f7381j);
                    i6 = length2;
                }
                int[] iArr4 = this.f7374c;
                iArr4[i6] = ebVar.f7813a;
                this.f7376e[i6] = f;
                if (i3 != -1) {
                    int[] iArr5 = this.f7375d;
                    iArr5[i6] = iArr5[i3];
                    iArr5[i3] = i6;
                } else {
                    this.f7375d[i6] = this.f7377f;
                    this.f7377f = i6;
                }
                this.f7372a++;
                if (!this.f7379h) {
                    this.f7378g++;
                }
                int i11 = this.f7378g;
                int length3 = iArr4.length;
                if (i11 >= length3) {
                    this.f7379h = true;
                    this.f7378g = length3 - 1;
                    return;
                }
                return;
            }
            this.f7377f = 0;
            this.f7376e[0] = f;
            this.f7374c[0] = ebVar.f7813a;
            this.f7375d[0] = -1;
            this.f7372a++;
            if (!this.f7379h) {
                this.f7378g++;
            }
        }
    }

    /* renamed from: b */
    public final float mo4450b(C0114eb ebVar) {
        int i = this.f7377f;
        int i2 = 0;
        while (i != -1 && i2 < this.f7372a) {
            if (this.f7374c[i] == ebVar.f7813a) {
                return this.f7376e[i];
            }
            i = this.f7375d[i];
            i2++;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final C0114eb mo4446a(int i) {
        int i2 = this.f7377f;
        int i3 = 0;
        while (i2 != -1 && i3 < this.f7372a) {
            if (i3 == i) {
                return this.f7373b.f7471c[this.f7374c[i2]];
            }
            i2 = this.f7375d[i2];
            i3++;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final float mo4449b(int i) {
        int i2 = this.f7377f;
        int i3 = 0;
        while (i2 != -1 && i3 < this.f7372a) {
            if (i3 == i) {
                return this.f7376e[i2];
            }
            i2 = this.f7375d[i2];
            i3++;
        }
        return 0.0f;
    }

    /* renamed from: a */
    public final void mo4448a(C0114eb ebVar, float f) {
        if (f != 0.0f) {
            int i = this.f7377f;
            if (i != -1) {
                int i2 = 0;
                int i3 = -1;
                while (i != -1 && i2 < this.f7372a) {
                    int i4 = this.f7374c[i];
                    int i5 = ebVar.f7813a;
                    if (i4 == i5) {
                        this.f7376e[i] = f;
                        return;
                    }
                    if (i4 < i5) {
                        i3 = i;
                    }
                    i = this.f7375d[i];
                    i2++;
                }
                int i6 = this.f7378g;
                int i7 = i6 + 1;
                if (this.f7379h) {
                    int[] iArr = this.f7374c;
                    if (iArr[i6] != -1) {
                        i6 = iArr.length;
                    }
                } else {
                    i6 = i7;
                }
                int length = this.f7374c.length;
                if (i6 >= length && this.f7372a < length) {
                    int i8 = 0;
                    while (true) {
                        int[] iArr2 = this.f7374c;
                        if (i8 < iArr2.length) {
                            if (iArr2[i8] == -1) {
                                i6 = i8;
                                break;
                            }
                            i8++;
                        } else {
                            break;
                        }
                    }
                }
                int length2 = this.f7374c.length;
                if (i6 >= length2) {
                    int i9 = this.f7381j;
                    int i10 = i9 + i9;
                    this.f7381j = i10;
                    this.f7379h = false;
                    this.f7378g = length2 - 1;
                    this.f7376e = Arrays.copyOf(this.f7376e, i10);
                    this.f7374c = Arrays.copyOf(this.f7374c, this.f7381j);
                    this.f7375d = Arrays.copyOf(this.f7375d, this.f7381j);
                    i6 = length2;
                }
                int[] iArr3 = this.f7374c;
                iArr3[i6] = ebVar.f7813a;
                this.f7376e[i6] = f;
                if (i3 != -1) {
                    int[] iArr4 = this.f7375d;
                    iArr4[i6] = iArr4[i3];
                    iArr4[i3] = i6;
                } else {
                    this.f7375d[i6] = this.f7377f;
                    this.f7377f = i6;
                }
                int i11 = this.f7372a + 1;
                this.f7372a = i11;
                if (!this.f7379h) {
                    this.f7378g++;
                }
                if (i11 >= iArr3.length) {
                    this.f7379h = true;
                    return;
                }
                return;
            }
            this.f7377f = 0;
            this.f7376e[0] = f;
            this.f7374c[0] = ebVar.f7813a;
            this.f7375d[0] = -1;
            this.f7372a++;
            if (!this.f7379h) {
                this.f7378g++;
                return;
            }
            return;
        }
        mo4445a(ebVar);
    }

    /* renamed from: a */
    public final float mo4445a(C0114eb ebVar) {
        int i = this.f7377f;
        if (i != -1) {
            int i2 = 0;
            int i3 = -1;
            while (i != -1 && i2 < this.f7372a) {
                int i4 = this.f7374c[i];
                if (i4 != ebVar.f7813a) {
                    i2++;
                    i3 = i;
                    i = this.f7375d[i];
                } else {
                    if (i != this.f7377f) {
                        int[] iArr = this.f7375d;
                        iArr[i3] = iArr[i];
                    } else {
                        this.f7377f = this.f7375d[i];
                    }
                    this.f7373b.f7471c[i4].mo4646a(this.f7380i);
                    this.f7372a--;
                    this.f7374c[i] = -1;
                    if (this.f7379h) {
                        this.f7378g = i;
                    }
                    return this.f7376e[i];
                }
            }
        }
        return 0.0f;
    }

    public final String toString() {
        int i = this.f7377f;
        String str = "";
        int i2 = 0;
        while (i != -1 && i2 < this.f7372a) {
            str = ((str + " -> ") + this.f7376e[i] + " : ") + this.f7373b.f7471c[this.f7374c[i]];
            i = this.f7375d[i];
            i2++;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4447a(C0107dv dvVar, C0107dv dvVar2) {
        int i = this.f7377f;
        int i2 = 0;
        while (i != -1 && i2 < this.f7372a) {
            int i3 = this.f7374c[i];
            C0114eb ebVar = dvVar2.f7438a;
            if (i3 == ebVar.f7813a) {
                float f = this.f7376e[i];
                mo4445a(ebVar);
                C0106du duVar = dvVar2.f7441d;
                int i4 = duVar.f7377f;
                int i5 = 0;
                while (i4 != -1 && i5 < duVar.f7372a) {
                    mo4451b(this.f7373b.f7471c[duVar.f7374c[i4]], duVar.f7376e[i4] * f);
                    i4 = duVar.f7375d[i4];
                    i5++;
                }
                dvVar.f7439b += dvVar2.f7439b * f;
                dvVar2.f7438a.mo4646a(dvVar);
                i = this.f7377f;
                i2 = 0;
            } else {
                i = this.f7375d[i];
                i2++;
            }
        }
    }
}
