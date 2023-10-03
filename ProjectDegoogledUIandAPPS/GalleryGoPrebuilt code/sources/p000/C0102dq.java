package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: dq */
/* compiled from: PG */
public final class C0102dq extends ViewGroup.MarginLayoutParams {

    /* renamed from: A */
    public int f7047A = 0;

    /* renamed from: B */
    public int f7048B = 0;

    /* renamed from: C */
    public int f7049C = 0;

    /* renamed from: D */
    public int f7050D = 0;

    /* renamed from: E */
    public int f7051E = 0;

    /* renamed from: F */
    public int f7052F = 0;

    /* renamed from: G */
    public int f7053G = 0;

    /* renamed from: H */
    public int f7054H = -1;

    /* renamed from: I */
    public int f7055I = -1;

    /* renamed from: J */
    public int f7056J = -1;

    /* renamed from: K */
    public boolean f7057K = true;

    /* renamed from: L */
    public boolean f7058L = true;

    /* renamed from: M */
    public boolean f7059M = false;

    /* renamed from: N */
    public boolean f7060N = false;

    /* renamed from: O */
    public int f7061O = -1;

    /* renamed from: P */
    public int f7062P = -1;

    /* renamed from: Q */
    public int f7063Q = -1;

    /* renamed from: R */
    public int f7064R = -1;

    /* renamed from: S */
    public int f7065S = -1;

    /* renamed from: T */
    public int f7066T = -1;

    /* renamed from: U */
    public float f7067U = 0.5f;

    /* renamed from: V */
    public C0116ed f7068V = new C0116ed();

    /* renamed from: W */
    private int f7069W = -1;

    /* renamed from: X */
    private int f7070X = -1;

    /* renamed from: Y */
    private int f7071Y = 1;

    /* renamed from: a */
    public int f7072a = -1;

    /* renamed from: b */
    public int f7073b = -1;

    /* renamed from: c */
    public float f7074c = -1.0f;

    /* renamed from: d */
    public int f7075d = -1;

    /* renamed from: e */
    public int f7076e = -1;

    /* renamed from: f */
    public int f7077f = -1;

    /* renamed from: g */
    public int f7078g = -1;

    /* renamed from: h */
    public int f7079h = -1;

    /* renamed from: i */
    public int f7080i = -1;

    /* renamed from: j */
    public int f7081j = -1;

    /* renamed from: k */
    public int f7082k = -1;

    /* renamed from: l */
    public int f7083l = -1;

    /* renamed from: m */
    public int f7084m = -1;

    /* renamed from: n */
    public int f7085n = -1;

    /* renamed from: o */
    public int f7086o = -1;

    /* renamed from: p */
    public int f7087p = -1;

    /* renamed from: q */
    public int f7088q = -1;

    /* renamed from: r */
    public int f7089r = -1;

    /* renamed from: s */
    public int f7090s = -1;

    /* renamed from: t */
    public int f7091t = -1;

    /* renamed from: u */
    public float f7092u = 0.5f;

    /* renamed from: v */
    public float f7093v = 0.5f;

    /* renamed from: w */
    public String f7094w = null;

    /* renamed from: x */
    public float f7095x = 0.0f;

    /* renamed from: y */
    public float f7096y = 0.0f;

    /* renamed from: z */
    public int f7097z = 0;

    public C0102dq() {
        super(-2, -2);
    }

    public C0102dq(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0105dt.f7309a);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == 24) {
                int resourceId = obtainStyledAttributes.getResourceId(24, this.f7075d);
                this.f7075d = resourceId;
                if (resourceId == -1) {
                    this.f7075d = obtainStyledAttributes.getInt(24, -1);
                }
            } else if (index == 25) {
                int resourceId2 = obtainStyledAttributes.getResourceId(25, this.f7076e);
                this.f7076e = resourceId2;
                if (resourceId2 == -1) {
                    this.f7076e = obtainStyledAttributes.getInt(25, -1);
                }
            } else if (index == 27) {
                int resourceId3 = obtainStyledAttributes.getResourceId(27, this.f7077f);
                this.f7077f = resourceId3;
                if (resourceId3 == -1) {
                    this.f7077f = obtainStyledAttributes.getInt(27, -1);
                }
            } else if (index == 28) {
                int resourceId4 = obtainStyledAttributes.getResourceId(28, this.f7078g);
                this.f7078g = resourceId4;
                if (resourceId4 == -1) {
                    this.f7078g = obtainStyledAttributes.getInt(28, -1);
                }
            } else if (index == 33) {
                int resourceId5 = obtainStyledAttributes.getResourceId(33, this.f7079h);
                this.f7079h = resourceId5;
                if (resourceId5 == -1) {
                    this.f7079h = obtainStyledAttributes.getInt(33, -1);
                }
            } else if (index == 32) {
                int resourceId6 = obtainStyledAttributes.getResourceId(32, this.f7080i);
                this.f7080i = resourceId6;
                if (resourceId6 == -1) {
                    this.f7080i = obtainStyledAttributes.getInt(32, -1);
                }
            } else if (index == 10) {
                int resourceId7 = obtainStyledAttributes.getResourceId(10, this.f7081j);
                this.f7081j = resourceId7;
                if (resourceId7 == -1) {
                    this.f7081j = obtainStyledAttributes.getInt(10, -1);
                }
            } else if (index == 9) {
                int resourceId8 = obtainStyledAttributes.getResourceId(9, this.f7082k);
                this.f7082k = resourceId8;
                if (resourceId8 == -1) {
                    this.f7082k = obtainStyledAttributes.getInt(9, -1);
                }
            } else if (index == 7) {
                int resourceId9 = obtainStyledAttributes.getResourceId(7, this.f7083l);
                this.f7083l = resourceId9;
                if (resourceId9 == -1) {
                    this.f7083l = obtainStyledAttributes.getInt(7, -1);
                }
            } else if (index == 40) {
                this.f7054H = obtainStyledAttributes.getDimensionPixelOffset(40, this.f7054H);
            } else if (index == 41) {
                this.f7055I = obtainStyledAttributes.getDimensionPixelOffset(41, this.f7055I);
            } else if (index == 14) {
                this.f7072a = obtainStyledAttributes.getDimensionPixelOffset(14, this.f7072a);
            } else if (index == 15) {
                this.f7073b = obtainStyledAttributes.getDimensionPixelOffset(15, this.f7073b);
            } else if (index == 16) {
                this.f7074c = obtainStyledAttributes.getFloat(16, this.f7074c);
            } else if (index == 0) {
                this.f7056J = obtainStyledAttributes.getInt(0, this.f7056J);
            } else if (index == 29) {
                int resourceId10 = obtainStyledAttributes.getResourceId(29, this.f7084m);
                this.f7084m = resourceId10;
                if (resourceId10 == -1) {
                    this.f7084m = obtainStyledAttributes.getInt(29, -1);
                }
            } else if (index == 30) {
                int resourceId11 = obtainStyledAttributes.getResourceId(30, this.f7085n);
                this.f7085n = resourceId11;
                if (resourceId11 == -1) {
                    this.f7085n = obtainStyledAttributes.getInt(30, -1);
                }
            } else if (index == 13) {
                int resourceId12 = obtainStyledAttributes.getResourceId(13, this.f7086o);
                this.f7086o = resourceId12;
                if (resourceId12 == -1) {
                    this.f7086o = obtainStyledAttributes.getInt(13, -1);
                }
            } else if (index == 12) {
                int resourceId13 = obtainStyledAttributes.getResourceId(12, this.f7087p);
                this.f7087p = resourceId13;
                if (resourceId13 == -1) {
                    this.f7087p = obtainStyledAttributes.getInt(12, -1);
                }
            } else if (index == 44) {
                this.f7069W = obtainStyledAttributes.getDimensionPixelSize(44, this.f7069W);
            } else if (index == 47) {
                this.f7088q = obtainStyledAttributes.getDimensionPixelSize(47, this.f7088q);
            } else if (index == 45) {
                this.f7070X = obtainStyledAttributes.getDimensionPixelSize(45, this.f7070X);
            } else if (index == 42) {
                this.f7089r = obtainStyledAttributes.getDimensionPixelSize(42, this.f7089r);
            } else if (index == 46) {
                this.f7090s = obtainStyledAttributes.getDimensionPixelSize(46, this.f7090s);
            } else if (index == 43) {
                this.f7091t = obtainStyledAttributes.getDimensionPixelSize(43, this.f7091t);
            } else if (index == 20) {
                this.f7092u = obtainStyledAttributes.getFloat(20, this.f7092u);
            } else if (index == 34) {
                this.f7093v = obtainStyledAttributes.getFloat(34, this.f7093v);
            } else if (index == 11) {
                String string = obtainStyledAttributes.getString(11);
                this.f7094w = string;
                this.f7071Y = -1;
                if (string != null) {
                    int length = string.length();
                    int indexOf = this.f7094w.indexOf(44);
                    if (indexOf <= 0 || indexOf >= length - 1) {
                        i = 0;
                    } else {
                        String substring = this.f7094w.substring(0, indexOf);
                        if (substring.equalsIgnoreCase("W")) {
                            this.f7071Y = 0;
                        } else if (substring.equalsIgnoreCase("H")) {
                            this.f7071Y = 1;
                        }
                        i = indexOf + 1;
                    }
                    int indexOf2 = this.f7094w.indexOf(58);
                    if (indexOf2 >= 0 && indexOf2 < length - 1) {
                        String substring2 = this.f7094w.substring(i, indexOf2);
                        String substring3 = this.f7094w.substring(indexOf2 + 1);
                        if (substring2.length() > 0 && substring3.length() > 0) {
                            try {
                                float parseFloat = Float.parseFloat(substring2);
                                float parseFloat2 = Float.parseFloat(substring3);
                                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                                    if (this.f7071Y != 1) {
                                        Math.abs(parseFloat / parseFloat2);
                                    } else {
                                        Math.abs(parseFloat2 / parseFloat);
                                    }
                                }
                            } catch (NumberFormatException e) {
                            }
                        }
                    } else {
                        String substring4 = this.f7094w.substring(i);
                        if (substring4.length() > 0) {
                            Float.parseFloat(substring4);
                        }
                    }
                }
            } else if (index == 22) {
                this.f7095x = obtainStyledAttributes.getFloat(22, 0.0f);
            } else if (index == 36) {
                this.f7096y = obtainStyledAttributes.getFloat(36, 0.0f);
            } else if (index == 21) {
                this.f7097z = obtainStyledAttributes.getInt(21, 0);
            } else if (index == 35) {
                this.f7047A = obtainStyledAttributes.getInt(35, 0);
            } else if (index == 37) {
                this.f7048B = obtainStyledAttributes.getInt(37, 0);
            } else if (index == 17) {
                this.f7049C = obtainStyledAttributes.getInt(17, 0);
            } else if (index == 39) {
                this.f7050D = obtainStyledAttributes.getDimensionPixelSize(39, this.f7050D);
            } else if (index == 38) {
                this.f7052F = obtainStyledAttributes.getDimensionPixelSize(38, this.f7052F);
            } else if (index == 19) {
                this.f7051E = obtainStyledAttributes.getDimensionPixelSize(19, this.f7051E);
            } else if (index == 18) {
                this.f7053G = obtainStyledAttributes.getDimensionPixelSize(18, this.f7053G);
            }
        }
        obtainStyledAttributes.recycle();
        mo4338a();
    }

    public C0102dq(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public final void resolveLayoutDirection(int i) {
        super.resolveLayoutDirection(i);
        this.f7063Q = -1;
        this.f7064R = -1;
        this.f7061O = -1;
        this.f7062P = -1;
        this.f7065S = this.f7069W;
        this.f7066T = this.f7070X;
        this.f7067U = this.f7092u;
        if (getLayoutDirection() != 1) {
            int i2 = this.f7084m;
            if (i2 != -1) {
                this.f7062P = i2;
            }
            int i3 = this.f7085n;
            if (i3 != -1) {
                this.f7061O = i3;
            }
            int i4 = this.f7086o;
            if (i4 != -1) {
                this.f7063Q = i4;
            }
            int i5 = this.f7087p;
            if (i5 != -1) {
                this.f7064R = i5;
            }
            int i6 = this.f7090s;
            if (i6 != -1) {
                this.f7065S = i6;
            }
            int i7 = this.f7091t;
            if (i7 != -1) {
                this.f7066T = i7;
            }
        } else {
            int i8 = this.f7084m;
            if (i8 == -1) {
                int i9 = this.f7085n;
                if (i9 != -1) {
                    this.f7064R = i9;
                }
            } else {
                this.f7063Q = i8;
            }
            int i10 = this.f7086o;
            if (i10 != -1) {
                this.f7062P = i10;
            }
            int i11 = this.f7087p;
            if (i11 != -1) {
                this.f7061O = i11;
            }
            int i12 = this.f7090s;
            if (i12 != -1) {
                this.f7066T = i12;
            }
            int i13 = this.f7091t;
            if (i13 != -1) {
                this.f7065S = i13;
            }
            this.f7067U = 1.0f - this.f7092u;
        }
        if (this.f7086o == -1 && this.f7087p == -1) {
            int i14 = this.f7077f;
            if (i14 == -1) {
                int i15 = this.f7078g;
                if (i15 != -1) {
                    this.f7064R = i15;
                }
            } else {
                this.f7063Q = i14;
            }
        }
        if (this.f7085n == -1 && this.f7084m == -1) {
            int i16 = this.f7075d;
            if (i16 == -1) {
                int i17 = this.f7076e;
                if (i17 != -1) {
                    this.f7062P = i17;
                    return;
                }
                return;
            }
            this.f7061O = i16;
        }
    }

    /* renamed from: a */
    public final void mo4338a() {
        this.f7060N = false;
        this.f7057K = true;
        this.f7058L = true;
        if (this.width == 0 || this.width == -1) {
            this.f7057K = false;
        }
        if (this.height == 0 || this.height == -1) {
            this.f7058L = false;
        }
        if (this.f7074c != -1.0f || this.f7072a != -1 || this.f7073b != -1) {
            this.f7060N = true;
            this.f7057K = true;
            this.f7058L = true;
            if (!(this.f7068V instanceof C0118ef)) {
                this.f7068V = new C0118ef();
            }
            ((C0118ef) this.f7068V).mo4761h(this.f7056J);
        }
    }
}
