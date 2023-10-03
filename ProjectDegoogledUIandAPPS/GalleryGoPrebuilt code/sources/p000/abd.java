package p000;

/* renamed from: abd */
/* compiled from: PG */
final class abd extends aaz {

    /* renamed from: e */
    private final /* synthetic */ abe f73e;

    public abd(abe abe) {
        this.f73e = abe;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo52a() {
        aba aba = (aba) this.f73e.f74a.get();
        if (aba == null) {
            return "Completer object has been garbage collected, future will fail soon";
        }
        return "tag=[" + aba.f69a + "]";
    }
}
