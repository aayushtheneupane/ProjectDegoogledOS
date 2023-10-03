package p000;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: igz */
/* compiled from: PG */
public abstract class igz implements ike {
    /* renamed from: a */
    public abstract igz clone();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract igz mo8503a(iha iha);

    /* renamed from: a */
    public igz mo8504a(byte[] bArr, int i, iij iij) {
        throw null;
    }

    /* renamed from: a */
    public abstract void mo8508a(ihz ihz, iij iij);

    /* renamed from: a */
    public static void m12986a(Iterable iterable, List list) {
        ijf.m13650a((Object) iterable);
        if (iterable instanceof ijo) {
            List d = ((ijo) iterable).mo8820d();
            ijo ijo = (ijo) list;
            int size = list.size();
            for (Object next : d) {
                if (next == null) {
                    int size2 = ijo.size();
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2 - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size3 = ijo.size() - 1; size3 >= size; size3--) {
                        ijo.remove(size3);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof ihw) {
                    ijo.mo8817a((ihw) next);
                } else {
                    ijo.add((String) next);
                }
            }
        } else if (!(iterable instanceof iko)) {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size4 = list.size();
            for (Object next2 : iterable) {
                if (next2 != null) {
                    list.add(next2);
                } else {
                    int size5 = list.size();
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(size5 - size4);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size6 = list.size() - 1; size6 >= size4; size6--) {
                        list.remove(size6);
                    }
                    throw new NullPointerException(sb4);
                }
            }
        } else {
            list.addAll((Collection) iterable);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ike mo8505a(ihw ihw) {
        try {
            ihz g = ihw.mo8615g();
            mo8508a(g, iij.m13501a());
            g.mo8629a(0);
            return this;
        } catch (ijh e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 70);
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ByteString threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ike mo8506a(ikf ikf) {
        if (mo8774h().getClass().isInstance(ikf)) {
            return mo8503a((iha) ikf);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ike mo8507a(byte[] bArr, iij iij) {
        return mo8504a(bArr, bArr.length, iij);
    }
}
