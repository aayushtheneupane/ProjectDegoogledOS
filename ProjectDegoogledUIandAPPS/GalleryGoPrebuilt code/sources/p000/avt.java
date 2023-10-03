package p000;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: avt */
/* compiled from: PG */
final class avt implements bft {
    /* renamed from: b */
    private static final avu m1772b() {
        try {
            return new avu(MessageDigest.getInstance("SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo1583a() {
        return m1772b();
    }
}
