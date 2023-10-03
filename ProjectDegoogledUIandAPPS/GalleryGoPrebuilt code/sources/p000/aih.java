package p000;

/* renamed from: aih */
/* compiled from: PG */
final class aih extends C0062cf {
    public aih() {
        super(6, 7);
    }

    /* renamed from: a */
    public final void mo519a(C0028az azVar) {
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
    }
}
