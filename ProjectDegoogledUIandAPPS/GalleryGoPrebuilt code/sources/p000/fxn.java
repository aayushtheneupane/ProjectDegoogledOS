package p000;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: fxn */
/* compiled from: PG */
public final class fxn extends fyk {

    /* renamed from: a */
    public final List f10669a = new ArrayList();

    public fxn(OutputStream outputStream) {
        super(outputStream);
        fym.m9880a(outputStream != null, "Output was null", new Object[0]);
    }

    public final void close() {
        List list = this.f10669a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                ((fyu) list.get(i)).close();
            } catch (Throwable th) {
            }
        }
        super.close();
    }

    public final void write(int i) {
        this.out.write(i);
        List list = this.f10669a;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((fyu) list.get(i2)).mo6341a();
        }
    }

    public final void write(byte[] bArr) {
        this.out.write(bArr);
        List list = this.f10669a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((fyu) list.get(i)).mo6341a();
        }
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        List list = this.f10669a;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((fyu) list.get(i3)).mo6341a();
        }
    }
}
