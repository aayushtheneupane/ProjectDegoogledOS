package p000;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* renamed from: hsp */
/* compiled from: PG */
public class hsp extends htf implements hum {
    public static final long serialVersionUID = 0;

    public hsp(hsu hsu) {
        super(hsu);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            hsq g = hsu.m12070g();
            int i = 0;
            int i2 = 0;
            while (i < readInt) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    hsj j = hso.m12048j();
                    for (int i3 = 0; i3 < readInt2; i3++) {
                        j.mo7908c(objectInputStream.readObject());
                    }
                    g.mo7932a(readObject, j.mo7905a());
                    i2 += readInt2;
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder(31);
                    sb.append("Invalid value count ");
                    sb.append(readInt2);
                    throw new InvalidObjectException(sb.toString());
                }
            }
            try {
                try {
                    hte.f13371a.f13472a.set(this, g.mo7930a());
                    try {
                        hte.f13372b.f13472a.set(this, Integer.valueOf(i2));
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    }
                } catch (IllegalAccessException e2) {
                    throw new AssertionError(e2);
                }
            } catch (IllegalArgumentException e3) {
                throw ((InvalidObjectException) new InvalidObjectException(e3.getMessage()).initCause(e3));
            }
        } else {
            StringBuilder sb2 = new StringBuilder(29);
            sb2.append("Invalid key count ");
            sb2.append(readInt);
            throw new InvalidObjectException(sb2.toString());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        ife.m12840a((hum) this, objectOutputStream);
    }
}
