package p000;

/* renamed from: ash */
/* compiled from: PG */
final class ash implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ask f1517a;

    public ash(ask ask) {
        this.f1517a = ask;
    }

    public final void run() {
        ask ask = this.f1517a;
        while (true) {
            boolean z = ask.f1520b;
            try {
                ask.mo1548a((asj) ask.f1519a.remove());
                asi asi = ask.f1521c;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
