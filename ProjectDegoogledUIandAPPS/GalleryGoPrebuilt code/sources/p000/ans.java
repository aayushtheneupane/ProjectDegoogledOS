package p000;

/* renamed from: ans */
/* compiled from: PG */
public final class ans {

    /* renamed from: a */
    public final String f1209a;

    public ans(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.f1209a = str.substring(0, indexOf);
            str.substring(indexOf + 1);
            return;
        }
        this.f1209a = "";
    }
}
