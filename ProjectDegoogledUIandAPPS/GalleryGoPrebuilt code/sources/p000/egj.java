package p000;

import android.os.storage.StorageVolume;

/* renamed from: egj */
/* compiled from: PG */
final /* synthetic */ class egj implements hol {

    /* renamed from: a */
    private final egp f8202a;

    /* renamed from: b */
    private final StorageVolume f8203b;

    public egj(egp egp, StorageVolume storageVolume) {
        this.f8202a = egp;
        this.f8203b = storageVolume;
    }

    /* renamed from: a */
    public final hom mo2639a(hoi hoi) {
        egp egp = this.f8202a;
        StorageVolume storageVolume = this.f8203b;
        if (((egd) hoi).f8190a) {
            egp.mo4797a(storageVolume.createOpenDocumentTreeIntent());
        } else {
            egp.mo4798a(false);
        }
        return hom.f13155a;
    }
}
