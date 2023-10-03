package p000;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: ail */
/* compiled from: PG */
public final class ail extends C0062cf {

    /* renamed from: c */
    private final Context f539c;

    public ail(Context context) {
        super(9, 10);
        this.f539c = context;
    }

    /* renamed from: a */
    public final void mo519a(C0028az azVar) {
        azVar.mo1736c("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        SharedPreferences sharedPreferences = this.f539c.getSharedPreferences("androidx.work.util.preferences", 0);
        if (sharedPreferences.contains("reschedule_needed") || sharedPreferences.contains("last_cancel_all_time_ms")) {
            long j = 0;
            long j2 = sharedPreferences.getLong("last_cancel_all_time_ms", 0);
            if (sharedPreferences.getBoolean("reschedule_needed", false)) {
                j = 1;
            }
            azVar.mo1731a();
            try {
                azVar.mo1732a("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"last_cancel_all_time_ms", Long.valueOf(j2)});
                azVar.mo1732a("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", Long.valueOf(j)});
                sharedPreferences.edit().clear().apply();
                azVar.mo1735c();
            } finally {
                azVar.mo1734b();
            }
        }
        SharedPreferences sharedPreferences2 = this.f539c.getSharedPreferences("androidx.work.util.id", 0);
        if (sharedPreferences2.contains("next_job_scheduler_id") || sharedPreferences2.contains("next_job_scheduler_id")) {
            int i = sharedPreferences2.getInt("next_job_scheduler_id", 0);
            int i2 = sharedPreferences2.getInt("next_alarm_manager_id", 0);
            azVar.mo1731a();
            try {
                azVar.mo1732a("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_job_scheduler_id", Integer.valueOf(i)});
                azVar.mo1732a("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_alarm_manager_id", Integer.valueOf(i2)});
                sharedPreferences2.edit().clear().apply();
                azVar.mo1735c();
            } finally {
                azVar.mo1734b();
            }
        }
    }
}
