package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p000a.p005b.C0015b;

/* renamed from: androidx.versionedparcelable.c */
public abstract class C0613c {

    /* renamed from: Du */
    protected final C0015b f692Du;

    /* renamed from: Eu */
    protected final C0015b f693Eu;

    /* renamed from: Fu */
    protected final C0015b f694Fu;

    public C0613c(C0015b bVar, C0015b bVar2, C0015b bVar3) {
        this.f692Du = bVar;
        this.f693Eu = bVar2;
        this.f694Fu = bVar3;
    }

    /* renamed from: bb */
    private Method m962bb(String str) {
        Method method = (Method) this.f692Du.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, C0613c.class.getClassLoader()).getDeclaredMethod("read", new Class[]{C0613c.class});
        this.f692Du.put(str, declaredMethod);
        return declaredMethod;
    }

    /* renamed from: g */
    private Class m963g(Class cls) {
        Class cls2 = (Class) this.f694Fu.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", new Object[]{cls.getPackage().getName(), cls.getSimpleName()}), false, cls.getClassLoader());
        this.f694Fu.put(cls.getName(), cls3);
        return cls3;
    }

    /* renamed from: h */
    private Method m964h(Class cls) {
        Method method = (Method) this.f693Eu.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class g = m963g(cls);
        System.currentTimeMillis();
        Method declaredMethod = g.getDeclaredMethod("write", new Class[]{cls, C0613c.class});
        this.f693Eu.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Z */
    public abstract boolean mo5296Z(int i);

    /* renamed from: a */
    public boolean mo5300a(boolean z, int i) {
        if (!mo5296Z(i)) {
            return z;
        }
        return readBoolean();
    }

    /* access modifiers changed from: protected */
    /* renamed from: aa */
    public abstract void mo5301aa(int i);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo5302b(Parcelable parcelable);

    /* renamed from: b */
    public void mo5306b(boolean z, int i) {
        mo5301aa(i);
        writeBoolean(z);
    }

    /* renamed from: c */
    public byte[] mo5307c(byte[] bArr, int i) {
        if (!mo5296Z(i)) {
            return bArr;
        }
        return readByteArray();
    }

    /* renamed from: d */
    public void mo5309d(byte[] bArr, int i) {
        mo5301aa(i);
        writeByteArray(bArr);
    }

    /* renamed from: e */
    public void mo5310e(String str, int i) {
        mo5301aa(i);
        writeString(str);
    }

    /* renamed from: h */
    public void mo5311h(boolean z, boolean z2) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: od */
    public abstract void mo5312od();

    /* access modifiers changed from: protected */
    /* renamed from: pd */
    public abstract C0613c mo5313pd();

    /* renamed from: q */
    public void mo5314q(int i, int i2) {
        mo5301aa(i2);
        writeInt(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: qd */
    public abstract Parcelable mo5315qd();

    /* access modifiers changed from: protected */
    /* renamed from: rd */
    public C0615e mo5316rd() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        C0613c pd = mo5313pd();
        try {
            return (C0615e) m962bb(readString).invoke((Object) null, new Object[]{pd});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e2.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean readBoolean();

    /* access modifiers changed from: protected */
    public abstract byte[] readByteArray();

    /* access modifiers changed from: protected */
    public abstract CharSequence readCharSequence();

    /* access modifiers changed from: protected */
    public abstract int readInt();

    public int readInt(int i, int i2) {
        if (!mo5296Z(i2)) {
            return i;
        }
        return readInt();
    }

    /* access modifiers changed from: protected */
    public abstract String readString();

    /* access modifiers changed from: protected */
    public abstract void writeBoolean(boolean z);

    /* access modifiers changed from: protected */
    public abstract void writeByteArray(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void writeCharSequence(CharSequence charSequence);

    /* access modifiers changed from: protected */
    public abstract void writeInt(int i);

    public void writeParcelable(Parcelable parcelable, int i) {
        mo5301aa(i);
        mo5302b(parcelable);
    }

    /* access modifiers changed from: protected */
    public abstract void writeString(String str);

    /* renamed from: a */
    public Parcelable mo5297a(Parcelable parcelable, int i) {
        if (!mo5296Z(i)) {
            return parcelable;
        }
        return mo5315qd();
    }

    /* renamed from: b */
    public void mo5305b(CharSequence charSequence, int i) {
        mo5301aa(i);
        writeCharSequence(charSequence);
    }

    /* renamed from: d */
    public String mo5308d(String str, int i) {
        if (!mo5296Z(i)) {
            return str;
        }
        return readString();
    }

    /* renamed from: a */
    public CharSequence mo5299a(CharSequence charSequence, int i) {
        if (!mo5296Z(i)) {
            return charSequence;
        }
        return readCharSequence();
    }

    /* renamed from: b */
    public void mo5304b(C0615e eVar, int i) {
        mo5301aa(i);
        mo5303b(eVar);
    }

    /* renamed from: a */
    public C0615e mo5298a(C0615e eVar, int i) {
        if (!mo5296Z(i)) {
            return eVar;
        }
        return mo5316rd();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo5303b(C0615e eVar) {
        if (eVar == null) {
            writeString((String) null);
            return;
        }
        try {
            writeString(m963g(eVar.getClass()).getName());
            C0613c pd = mo5313pd();
            try {
                m964h(eVar.getClass()).invoke((Object) null, new Object[]{eVar, pd});
                pd.mo5312od();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof RuntimeException) {
                    throw ((RuntimeException) e2.getCause());
                }
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e2);
            } catch (NoSuchMethodException e3) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e3);
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
            }
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException(eVar.getClass().getSimpleName() + " does not have a Parcelizer", e5);
        }
    }
}
