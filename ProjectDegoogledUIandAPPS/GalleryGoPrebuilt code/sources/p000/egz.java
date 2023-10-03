package p000;

import java.io.File;
import java.util.UUID;

/* renamed from: egz */
/* compiled from: PG */
public final /* synthetic */ class egz implements hpr {

    /* renamed from: a */
    private final String f8252a;

    /* renamed from: b */
    private final String f8253b;

    public egz(String str, String str2) {
        this.f8252a = str;
        this.f8253b = str2;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f8252a;
        String str2 = this.f8253b;
        String valueOf = String.valueOf(UUID.randomUUID());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + str.length());
        sb.append(valueOf);
        sb.append(str);
        return ehb.m7465a((File) obj, sb.toString(), str2, 0, 0, 0);
    }
}
