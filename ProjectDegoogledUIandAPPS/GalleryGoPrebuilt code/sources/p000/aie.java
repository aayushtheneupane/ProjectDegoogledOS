package p000;

/* renamed from: aie */
/* compiled from: PG */
final class aie extends C0062cf {
    public aie() {
        super(1, 2);
    }

    /* renamed from: a */
    public final void mo519a(C0028az azVar) {
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("INSERT INTO SystemIdInfo(work_spec_id, system_id) SELECT work_spec_id, alarm_id AS system_id FROM alarmInfo");
        azVar.mo1736c("DROP TABLE IF EXISTS alarmInfo");
        azVar.mo1736c("INSERT OR IGNORE INTO worktag(tag, work_spec_id) SELECT worker_class_name AS tag, id AS work_spec_id FROM workspec");
    }
}
