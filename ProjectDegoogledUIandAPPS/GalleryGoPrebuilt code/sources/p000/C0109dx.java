package p000;

import java.util.ArrayList;

/* renamed from: dx */
/* compiled from: PG */
public final class C0109dx {

    /* renamed from: a */
    public final ArrayList f7549a = new ArrayList();

    public final String toString() {
        int size = this.f7549a.size();
        String str = "Goal: ";
        for (int i = 0; i < size; i++) {
            C0114eb ebVar = (C0114eb) this.f7549a.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = ebVar + "[";
            for (int i2 = 0; i2 < ebVar.f7817e.length; i2++) {
                String str3 = str2 + ebVar.f7817e[i2];
                if (i2 < ebVar.f7817e.length - 1) {
                    str2 = str3 + ", ";
                } else {
                    str2 = str3 + "] ";
                }
            }
            sb.append(str2);
            str = sb.toString();
        }
        return str;
    }

    /* renamed from: a */
    public final void mo4534a(C0110dy dyVar) {
        C0110dy dyVar2 = dyVar;
        this.f7549a.clear();
        for (int i = 1; i < dyVar2.f7624d; i++) {
            C0114eb ebVar = dyVar2.f7626f.f7471c[i];
            for (int i2 = 0; i2 < 6; i2++) {
                ebVar.f7817e[i2] = 0.0f;
            }
            ebVar.f7817e[ebVar.f7815c] = 1.0f;
            if (ebVar.f7820h == 4) {
                this.f7549a.add(ebVar);
            }
        }
        int size = this.f7549a.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0114eb ebVar2 = (C0114eb) this.f7549a.get(i3);
            int i4 = ebVar2.f7814b;
            if (i4 != -1) {
                C0106du duVar = dyVar2.f7622b[i4].f7441d;
                int i5 = duVar.f7372a;
                for (int i6 = 0; i6 < i5; i6++) {
                    C0114eb a = duVar.mo4446a(i6);
                    if (a != null) {
                        float b = duVar.mo4449b(i6);
                        for (int i7 = 0; i7 < 6; i7++) {
                            float[] fArr = a.f7817e;
                            fArr[i7] = fArr[i7] + (ebVar2.f7817e[i7] * b);
                        }
                        if (!this.f7549a.contains(a)) {
                            this.f7549a.add(a);
                        }
                    }
                }
                for (int i8 = 0; i8 < 6; i8++) {
                    ebVar2.f7817e[i8] = 0.0f;
                }
            }
        }
    }
}
