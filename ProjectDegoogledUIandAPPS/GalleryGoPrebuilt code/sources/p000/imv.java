package p000;

/* renamed from: imv */
/* compiled from: PG */
public final class imv implements ina {

    /* renamed from: a */
    private final hso f14544a;

    public imv(hso hso) {
        this.f14544a = hso;
    }

    /* renamed from: d */
    public final void mo9010d(imu imu, long j) {
        hvs i = this.f14544a.listIterator();
        while (i.hasNext()) {
            ((ina) i.next()).mo9010d(imu, j);
        }
    }

    /* renamed from: a */
    public final void mo9007a(imu imu, Throwable th, long j) {
        hvs i = this.f14544a.listIterator();
        while (i.hasNext()) {
            ((ina) i.next()).mo9007a(imu, th, j);
        }
    }

    /* renamed from: b */
    public final void mo9008b(imu imu, long j) {
        hvs i = this.f14544a.listIterator();
        while (i.hasNext()) {
            ((ina) i.next()).mo9008b(imu, j);
        }
    }

    /* renamed from: a */
    public final void mo9006a(imu imu, long j) {
        hvs i = this.f14544a.listIterator();
        while (i.hasNext()) {
            ((ina) i.next()).mo9006a(imu, j);
        }
    }

    /* renamed from: c */
    public final void mo9009c(imu imu, long j) {
        hvs i = this.f14544a.listIterator();
        while (i.hasNext()) {
            ((ina) i.next()).mo9009c(imu, j);
        }
    }
}
