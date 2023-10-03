package p000;

import android.content.Context;
import android.content.Intent;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import java.util.HashMap;
import java.util.Map;

/* renamed from: aiw */
/* compiled from: PG */
public final class aiw implements ahw {

    /* renamed from: a */
    public static final String f599a = iol.m14236b("CommandHandler");

    /* renamed from: b */
    public final Context f600b;

    /* renamed from: c */
    public final Map f601c = new HashMap();

    /* renamed from: d */
    public final Object f602d = new Object();

    public aiw(Context context) {
        this.f600b = context;
    }

    /* renamed from: a */
    static Intent m575a(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    /* renamed from: b */
    static Intent m578b(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    /* renamed from: a */
    static Intent m577a(Context context, String str, boolean z) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return intent;
    }

    /* renamed from: a */
    static Intent m576a(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    /* renamed from: c */
    static Intent m579c(Context context, String str) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        intent.putExtra("KEY_WORKSPEC_ID", str);
        return intent;
    }

    /* renamed from: a */
    public final void mo503a(String str, boolean z) {
        synchronized (this.f602d) {
            ahw ahw = (ahw) this.f601c.remove(str);
            if (ahw != null) {
                ahw.mo503a(str, z);
            }
        }
    }
}
