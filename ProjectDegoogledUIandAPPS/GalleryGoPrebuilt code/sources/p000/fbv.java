package p000;

@Deprecated
/* renamed from: fbv */
/* compiled from: PG */
public final class fbv implements fbj {

    /* renamed from: a */
    private final euj f9256a;

    public fbv(euj euj) {
        this.f9256a = euj;
    }

    /* renamed from: b */
    public final String[] mo5460b() {
        return this.f9256a.f9043c;
    }

    /* renamed from: a */
    public final fbl[] mo5459a() {
        int length = this.f9256a.f9042b.length;
        fbx[] fbxArr = new fbx[length];
        for (int i = 0; i < length; i++) {
            fbxArr[i] = new fbx(this.f9256a.f9042b[i]);
        }
        return fbxArr;
    }
}
