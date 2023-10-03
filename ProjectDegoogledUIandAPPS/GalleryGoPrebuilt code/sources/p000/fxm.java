package p000;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: fxm */
/* compiled from: PG */
public final class fxm extends fyj {

    /* renamed from: a */
    private final List f10668a = new ArrayList();

    public fxm(List list, InputStream inputStream) {
        super(inputStream);
        fym.m9880a(inputStream != null, "Input was null", new Object[0]);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            fyt a = ((fyv) list.get(i)).mo6343a();
            if (a != null) {
                this.f10668a.add(a);
            }
        }
    }

    public final void close() {
        List list = this.f10668a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                ((fyt) list.get(i)).close();
            } catch (Throwable th) {
            }
        }
        super.close();
    }

    public final int read() {
        int read = this.in.read();
        if (read != -1) {
            List list = this.f10668a;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((fyt) list.get(i)).mo6339a();
            }
        }
        return read;
    }

    public final int read(byte[] bArr) {
        int read = this.in.read(bArr);
        if (read != -1) {
            List list = this.f10668a;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((fyt) list.get(i)).mo6339a();
            }
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            List list = this.f10668a;
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((fyt) list.get(i3)).mo6339a();
            }
        }
        return read;
    }
}
