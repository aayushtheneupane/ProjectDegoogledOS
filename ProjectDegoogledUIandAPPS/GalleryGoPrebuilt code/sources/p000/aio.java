package p000;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: aio */
/* compiled from: PG */
public final class aio extends C0054by {

    /* renamed from: a */
    public final /* synthetic */ WorkDatabase_Impl f548a;

    public aio(WorkDatabase_Impl workDatabase_Impl) {
        this.f548a = workDatabase_Impl;
    }

    /* renamed from: a */
    public final void mo520a(C0028az azVar) {
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `period_start_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `required_network_type` INTEGER, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB, PRIMARY KEY(`id`))");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_WorkSpec_period_start_time` ON `WorkSpec` (`period_start_time`)");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        azVar.mo1736c("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'cf029002fffdcadf079e8d0a1c9a70ac')");
    }

    /* renamed from: b */
    public final C0055bz mo521b(C0028az azVar) {
        C0028az azVar2 = azVar;
        HashMap hashMap = new HashMap(2);
        hashMap.put("work_spec_id", new C0063cg("work_spec_id", "TEXT", true, 1, (String) null, 1));
        hashMap.put("prerequisite_id", new C0063cg("prerequisite_id", "TEXT", true, 2, (String) null, 1));
        HashSet hashSet = new HashSet(2);
        hashSet.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
        hashSet.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"})));
        HashSet hashSet2 = new HashSet(2);
        hashSet2.add(new C0066cj("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
        hashSet2.add(new C0066cj("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"})));
        C0067ck ckVar = new C0067ck("Dependency", hashMap, hashSet, hashSet2);
        C0067ck a = C0067ck.m4436a(azVar2, "Dependency");
        if (!ckVar.equals(a)) {
            return new C0055bz(false, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + ckVar + "\n Found:\n" + a);
        }
        HashMap hashMap2 = new HashMap(24);
        hashMap2.put("id", new C0063cg("id", "TEXT", true, 1, (String) null, 1));
        hashMap2.put("state", new C0063cg("state", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("worker_class_name", new C0063cg("worker_class_name", "TEXT", true, 0, (String) null, 1));
        hashMap2.put("input_merger_class_name", new C0063cg("input_merger_class_name", "TEXT", false, 0, (String) null, 1));
        hashMap2.put("input", new C0063cg("input", "BLOB", true, 0, (String) null, 1));
        hashMap2.put("output", new C0063cg("output", "BLOB", true, 0, (String) null, 1));
        hashMap2.put("initial_delay", new C0063cg("initial_delay", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("interval_duration", new C0063cg("interval_duration", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("flex_duration", new C0063cg("flex_duration", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("run_attempt_count", new C0063cg("run_attempt_count", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("backoff_policy", new C0063cg("backoff_policy", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("backoff_delay_duration", new C0063cg("backoff_delay_duration", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("period_start_time", new C0063cg("period_start_time", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("minimum_retention_duration", new C0063cg("minimum_retention_duration", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("schedule_requested_at", new C0063cg("schedule_requested_at", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("run_in_foreground", new C0063cg("run_in_foreground", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("required_network_type", new C0063cg("required_network_type", "INTEGER", false, 0, (String) null, 1));
        hashMap2.put("requires_charging", new C0063cg("requires_charging", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("requires_device_idle", new C0063cg("requires_device_idle", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("requires_battery_not_low", new C0063cg("requires_battery_not_low", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("requires_storage_not_low", new C0063cg("requires_storage_not_low", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("trigger_content_update_delay", new C0063cg("trigger_content_update_delay", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("trigger_max_content_delay", new C0063cg("trigger_max_content_delay", "INTEGER", true, 0, (String) null, 1));
        hashMap2.put("content_uri_triggers", new C0063cg("content_uri_triggers", "BLOB", false, 0, (String) null, 1));
        HashSet hashSet3 = new HashSet(0);
        HashSet hashSet4 = new HashSet(2);
        hashSet4.add(new C0066cj("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"})));
        hashSet4.add(new C0066cj("index_WorkSpec_period_start_time", false, Arrays.asList(new String[]{"period_start_time"})));
        C0067ck ckVar2 = new C0067ck("WorkSpec", hashMap2, hashSet3, hashSet4);
        C0067ck a2 = C0067ck.m4436a(azVar2, "WorkSpec");
        if (!ckVar2.equals(a2)) {
            return new C0055bz(false, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + ckVar2 + "\n Found:\n" + a2);
        }
        HashMap hashMap3 = new HashMap(2);
        hashMap3.put("tag", new C0063cg("tag", "TEXT", true, 1, (String) null, 1));
        hashMap3.put("work_spec_id", new C0063cg("work_spec_id", "TEXT", true, 2, (String) null, 1));
        HashSet hashSet5 = new HashSet(1);
        hashSet5.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
        HashSet hashSet6 = new HashSet(1);
        hashSet6.add(new C0066cj("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
        C0067ck ckVar3 = new C0067ck("WorkTag", hashMap3, hashSet5, hashSet6);
        C0067ck a3 = C0067ck.m4436a(azVar2, "WorkTag");
        if (!ckVar3.equals(a3)) {
            return new C0055bz(false, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + ckVar3 + "\n Found:\n" + a3);
        }
        HashMap hashMap4 = new HashMap(2);
        hashMap4.put("work_spec_id", new C0063cg("work_spec_id", "TEXT", true, 1, (String) null, 1));
        hashMap4.put("system_id", new C0063cg("system_id", "INTEGER", true, 0, (String) null, 1));
        HashSet hashSet7 = new HashSet(1);
        hashSet7.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
        C0067ck ckVar4 = new C0067ck("SystemIdInfo", hashMap4, hashSet7, new HashSet(0));
        C0067ck a4 = C0067ck.m4436a(azVar2, "SystemIdInfo");
        if (!ckVar4.equals(a4)) {
            return new C0055bz(false, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + ckVar4 + "\n Found:\n" + a4);
        }
        HashMap hashMap5 = new HashMap(2);
        hashMap5.put("name", new C0063cg("name", "TEXT", true, 1, (String) null, 1));
        hashMap5.put("work_spec_id", new C0063cg("work_spec_id", "TEXT", true, 2, (String) null, 1));
        HashSet hashSet8 = new HashSet(1);
        hashSet8.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
        HashSet hashSet9 = new HashSet(1);
        hashSet9.add(new C0066cj("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"})));
        C0067ck ckVar5 = new C0067ck("WorkName", hashMap5, hashSet8, hashSet9);
        C0067ck a5 = C0067ck.m4436a(azVar2, "WorkName");
        if (!ckVar5.equals(a5)) {
            return new C0055bz(false, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + ckVar5 + "\n Found:\n" + a5);
        }
        HashMap hashMap6 = new HashMap(2);
        hashMap6.put("work_spec_id", new C0063cg("work_spec_id", "TEXT", true, 1, (String) null, 1));
        hashMap6.put("progress", new C0063cg("progress", "BLOB", true, 0, (String) null, 1));
        HashSet hashSet10 = new HashSet(1);
        hashSet10.add(new C0064ch("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
        C0067ck ckVar6 = new C0067ck("WorkProgress", hashMap6, hashSet10, new HashSet(0));
        C0067ck a6 = C0067ck.m4436a(azVar2, "WorkProgress");
        if (!ckVar6.equals(a6)) {
            return new C0055bz(false, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + ckVar6 + "\n Found:\n" + a6);
        }
        HashMap hashMap7 = new HashMap(2);
        hashMap7.put("key", new C0063cg("key", "TEXT", true, 1, (String) null, 1));
        hashMap7.put("long_value", new C0063cg("long_value", "INTEGER", false, 0, (String) null, 1));
        C0067ck ckVar7 = new C0067ck("Preference", hashMap7, new HashSet(0), new HashSet(0));
        C0067ck a7 = C0067ck.m4436a(azVar2, "Preference");
        if (ckVar7.equals(a7)) {
            return new C0055bz(true, (String) null);
        }
        return new C0055bz(false, "Preference(androidx.work.impl.model.Preference).\n Expected:\n" + ckVar7 + "\n Found:\n" + a7);
    }
}
