package p000;

import java.io.InvalidObjectException;
import java.text.Format;

/* renamed from: ct */
/* compiled from: PG */
public final class C0078ct extends Format.Field {

    /* renamed from: a */
    public static final C0078ct f5616a = new C0078ct("message argument field");
    public static final long serialVersionUID = 7510380454602616157L;

    private C0078ct(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        if (getClass() != C0078ct.class) {
            throw new InvalidObjectException("A subclass of MessageFormat.Field must implement readResolve.");
        } else if (getName().equals(f5616a.getName())) {
            return f5616a;
        } else {
            throw new InvalidObjectException("Unknown attribute name.");
        }
    }
}
