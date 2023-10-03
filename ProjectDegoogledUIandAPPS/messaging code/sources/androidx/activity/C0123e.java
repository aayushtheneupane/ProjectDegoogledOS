package androidx.activity;

/* renamed from: androidx.activity.e */
class C0123e implements C0119a {

    /* renamed from: Il */
    private final C0122d f127Il;
    final /* synthetic */ C0124f this$0;

    C0123e(C0124f fVar, C0122d dVar) {
        this.this$0 = fVar;
        this.f127Il = dVar;
    }

    public void cancel() {
        this.this$0.f129Ll.remove(this.f127Il);
        this.f127Il.mo615b(this);
    }
}
