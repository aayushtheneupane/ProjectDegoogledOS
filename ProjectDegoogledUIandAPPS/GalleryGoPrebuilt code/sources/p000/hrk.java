package p000;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: hrk */
/* compiled from: PG */
public final class hrk extends hqr {
    public static final long serialVersionUID = 0;

    /* renamed from: c */
    private transient int f13308c;

    private hrk() {
        this(12, 3);
    }

    public hrk(int i, int i2) {
        super(new hru(i));
        ife.m12839a(i2, "expectedValuesPerKey");
        this.f13308c = i2;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Collection mo7691a() {
        return new ArrayList(this.f13308c);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f13308c = 3;
        int readInt = objectInputStream.readInt();
        hru a = hru.m11983a();
        this.f13294a = a;
        this.f13295b = 0;
        for (Collection collection : a.values()) {
            ife.m12890c(!collection.isEmpty());
            this.f13295b += collection.size();
        }
        for (int i = 0; i < readInt; i++) {
            List a2 = mo7693a(objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            for (int i2 = 0; i2 < readInt2; i2++) {
                a2.add(objectInputStream.readObject());
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        ife.m12840a((hum) this, objectOutputStream);
    }
}
