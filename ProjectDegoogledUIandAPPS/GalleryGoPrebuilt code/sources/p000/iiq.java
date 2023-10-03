package p000;

/* renamed from: iiq */
/* compiled from: PG */
final class iiq implements ikd {

    /* renamed from: a */
    public static final iiq f14316a = new iiq();

    private iiq() {
    }

    /* renamed from: a */
    public final boolean mo8741a(Class cls) {
        return iix.class.isAssignableFrom(cls);
    }

    /* renamed from: b */
    public final ikc mo8742b(Class cls) {
        if (!iix.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unsupported message type: ") : "Unsupported message type: ".concat(valueOf));
        }
        try {
            Class<? extends U> asSubclass = cls.asSubclass(iix.class);
            iix iix = (iix) iix.f14324A.get(asSubclass);
            if (iix == null) {
                Class.forName(asSubclass.getName(), true, asSubclass.getClassLoader());
                iix = (iix) iix.f14324A.get(asSubclass);
            }
            if (iix == null) {
                iix = (iix) ((iix) ilv.m14028a(asSubclass)).mo8790b(6);
                if (iix != null) {
                    iix.f14324A.put(asSubclass, iix);
                } else {
                    throw new IllegalStateException();
                }
            }
            return (ikc) iix.mo8790b(3);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class initialization cannot fail.", e);
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() == 0 ? new String("Unable to get message info for ") : "Unable to get message info for ".concat(valueOf2), e2);
        }
    }
}
