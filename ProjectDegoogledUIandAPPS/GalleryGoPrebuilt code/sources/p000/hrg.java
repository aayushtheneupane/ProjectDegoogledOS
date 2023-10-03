package p000;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: hrg */
/* compiled from: PG */
abstract class hrg extends hrj implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    public transient hut f13302a;

    /* renamed from: b */
    public transient long f13303b;

    public hrg() {
        mo7777d();
    }

    /* renamed from: c */
    public final int mo7774c() {
        return this.f13302a.f13431c;
    }

    /* renamed from: d */
    public abstract void mo7777d();

    /* renamed from: a */
    public final int mo7770a(Object obj, int i) {
        if (i == 0) {
            return mo7769a(obj);
        }
        boolean z = true;
        ife.m12846a(i > 0, "occurrences cannot be negative: %s", i);
        int a = this.f13302a.mo8102a(obj);
        if (a == -1) {
            this.f13302a.mo8108b(obj, i);
            this.f13303b += (long) i;
            return 0;
        }
        int c = this.f13302a.mo8109c(a);
        long j = (long) i;
        long j2 = ((long) c) + j;
        if (j2 > 2147483647L) {
            z = false;
        }
        ife.m12848a(z, "too many occurrences: %s", j2);
        this.f13302a.mo8104a(a, (int) j2);
        this.f13303b += j;
        return c;
    }

    public final void clear() {
        hut hut = this.f13302a;
        hut.f13432d++;
        Arrays.fill(hut.f13429a, 0, hut.f13431c, (Object) null);
        Arrays.fill(hut.f13430b, 0, hut.f13431c, 0);
        Arrays.fill(hut.f13433e, -1);
        Arrays.fill(hut.f13434f, -1);
        hut.f13431c = 0;
        this.f13303b = 0;
    }

    /* renamed from: a */
    public final int mo7769a(Object obj) {
        return this.f13302a.mo8106b(obj);
    }

    /* renamed from: a */
    public final Iterator mo7771a() {
        return new hrd(this);
    }

    /* renamed from: b */
    public final Iterator mo7773b() {
        return new hre(this);
    }

    public final Iterator iterator() {
        return new hus(this, mo7796f().iterator());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        mo7777d();
        for (int i = 0; i < readInt; i++) {
            mo7770a(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    /* renamed from: b */
    public final int mo7772b(Object obj, int i) {
        ife.m12846a(true, "occurrences cannot be negative: %s", i);
        int a = this.f13302a.mo8102a(obj);
        if (a == -1) {
            return 0;
        }
        int c = this.f13302a.mo8109c(a);
        if (c > i) {
            this.f13302a.mo8104a(a, c - i);
        } else {
            this.f13302a.mo8113g(a);
            i = c;
        }
        this.f13303b -= (long) i;
        return c;
    }

    /* renamed from: c */
    public final boolean mo7775c(Object obj, int i) {
        ife.m12839a(i, "oldCount");
        ife.m12839a(0, "newCount");
        int a = this.f13302a.mo8102a(obj);
        if (a != -1) {
            if (this.f13302a.mo8109c(a) != i) {
                return false;
            }
            this.f13302a.mo8113g(a);
            this.f13303b -= (long) i;
            return true;
        } else if (i != 0) {
            return false;
        } else {
            return true;
        }
    }

    public final int size() {
        return ife.m12862b(this.f13303b);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(mo7796f().size());
        for (hun hun : mo7796f()) {
            objectOutputStream.writeObject(hun.mo8079a());
            objectOutputStream.writeInt(hun.mo8080b());
        }
    }
}
