package p000;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/* renamed from: alr */
/* compiled from: PG */
public final class alr implements alh {

    /* renamed from: a */
    private final C0053bx f730a;

    /* renamed from: b */
    private final C0047br f731b;

    /* renamed from: c */
    private final C0059cc f732c;

    /* renamed from: d */
    private final C0059cc f733d;

    /* renamed from: e */
    private final C0059cc f734e;

    /* renamed from: f */
    private final C0059cc f735f;

    /* renamed from: g */
    private final C0059cc f736g;

    /* renamed from: h */
    private final C0059cc f737h;

    /* renamed from: i */
    private final C0059cc f738i;

    public alr(C0053bx bxVar) {
        this.f730a = bxVar;
        this.f731b = new ali(bxVar);
        this.f732c = new alj(bxVar);
        this.f733d = new alk(bxVar);
        this.f734e = new all(bxVar);
        this.f735f = new alm(bxVar);
        this.f736g = new aln(bxVar);
        this.f737h = new alo(bxVar);
        this.f738i = new alp(bxVar);
        new alq(bxVar);
    }

    /* renamed from: a */
    public final void mo605a(String str) {
        this.f730a.mo2844d();
        C0037bh b = this.f732c.mo3016b();
        if (str == null) {
            b.mo1920a(1);
        } else {
            b.mo1922a(1, str);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f732c.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final List mo602a() {
        C0057ca a = C0057ca.m3923a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: e */
    public final List mo615e() {
        C0057ca caVar;
        boolean z;
        boolean z2;
        boolean z3;
        C0057ca a = C0057ca.m3923a("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground` FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))", 1);
        a.f3951b[1] = 2;
        a.f3950a[1] = 20;
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "required_network_type");
            int a4 = C0350mt.m14757a(a2, "requires_charging");
            int a5 = C0350mt.m14757a(a2, "requires_device_idle");
            int a6 = C0350mt.m14757a(a2, "requires_battery_not_low");
            int a7 = C0350mt.m14757a(a2, "requires_storage_not_low");
            int a8 = C0350mt.m14757a(a2, "trigger_content_update_delay");
            int a9 = C0350mt.m14757a(a2, "trigger_max_content_delay");
            int a10 = C0350mt.m14757a(a2, "content_uri_triggers");
            int a11 = C0350mt.m14757a(a2, "id");
            int a12 = C0350mt.m14757a(a2, "state");
            int a13 = C0350mt.m14757a(a2, "worker_class_name");
            int a14 = C0350mt.m14757a(a2, "input_merger_class_name");
            int a15 = C0350mt.m14757a(a2, "input");
            int a16 = C0350mt.m14757a(a2, "output");
            caVar = a;
            try {
                int a17 = C0350mt.m14757a(a2, "initial_delay");
                int a18 = C0350mt.m14757a(a2, "interval_duration");
                int a19 = C0350mt.m14757a(a2, "flex_duration");
                int a20 = C0350mt.m14757a(a2, "run_attempt_count");
                int a21 = C0350mt.m14757a(a2, "backoff_policy");
                int a22 = C0350mt.m14757a(a2, "backoff_delay_duration");
                int a23 = C0350mt.m14757a(a2, "period_start_time");
                int a24 = C0350mt.m14757a(a2, "minimum_retention_duration");
                int a25 = C0350mt.m14757a(a2, "schedule_requested_at");
                int a26 = C0350mt.m14757a(a2, "run_in_foreground");
                int i = a16;
                ArrayList arrayList = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    String string = a2.getString(a11);
                    int i2 = a11;
                    String string2 = a2.getString(a13);
                    int i3 = a13;
                    ahb ahb = new ahb();
                    int i4 = a3;
                    ahb.f482i = ihg.m13058f(a2.getInt(a3));
                    if (a2.getInt(a4) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ahb.f475b = z;
                    ahb.f476c = a2.getInt(a5) != 0;
                    ahb.f477d = a2.getInt(a6) != 0;
                    if (a2.getInt(a7) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    ahb.f478e = z2;
                    int i5 = a4;
                    int i6 = a5;
                    ahb.f479f = a2.getLong(a8);
                    ahb.f480g = a2.getLong(a9);
                    ahb.f481h = ihg.m13025a(a2.getBlob(a10));
                    alg alg = new alg(string, string2);
                    alg.f728q = ihg.m13054d(a2.getInt(a12));
                    alg.f715d = a2.getString(a14);
                    alg.f716e = ahf.m482a(a2.getBlob(a15));
                    int i7 = i;
                    alg.f717f = ahf.m482a(a2.getBlob(i7));
                    int i8 = i5;
                    i = i7;
                    int i9 = a17;
                    alg.f718g = a2.getLong(i9);
                    int i10 = a15;
                    int i11 = a18;
                    alg.f719h = a2.getLong(i11);
                    int i12 = i9;
                    int i13 = a6;
                    int i14 = a19;
                    alg.f720i = a2.getLong(i14);
                    int i15 = a20;
                    alg.f722k = a2.getInt(i15);
                    int i16 = a21;
                    int i17 = i11;
                    alg.f729r = ihg.m13056e(a2.getInt(i16));
                    a19 = i14;
                    int i18 = i13;
                    int i19 = a22;
                    alg.f723l = a2.getLong(i19);
                    int i20 = i15;
                    int i21 = i16;
                    int i22 = a23;
                    alg.f724m = a2.getLong(i22);
                    int i23 = i19;
                    a23 = i22;
                    int i24 = a24;
                    alg.f725n = a2.getLong(i24);
                    int i25 = i20;
                    int i26 = a25;
                    alg.f726o = a2.getLong(i26);
                    int i27 = a26;
                    if (a2.getInt(i27) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    alg.f727p = z3;
                    alg.f721j = ahb;
                    arrayList.add(alg);
                    a25 = i26;
                    a26 = i27;
                    a4 = i8;
                    a15 = i10;
                    a17 = i12;
                    a18 = i17;
                    a20 = i25;
                    a11 = i2;
                    a13 = i3;
                    a3 = i4;
                    a24 = i24;
                    a5 = i6;
                    int i28 = i21;
                    a22 = i23;
                    a6 = i18;
                    a21 = i28;
                }
                a2.close();
                caVar.mo2953b();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                a2.close();
                caVar.mo2953b();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            caVar = a;
            a2.close();
            caVar.mo2953b();
            throw th;
        }
    }

    /* renamed from: d */
    public final List mo613d(String str) {
        C0057ca a = C0057ca.m3923a("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(ahf.m482a(a2.getBlob(0)));
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: c */
    public final List mo611c() {
        C0057ca caVar;
        boolean z;
        boolean z2;
        C0057ca a = C0057ca.m3923a("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground` FROM workspec WHERE state=1", 0);
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "required_network_type");
            int a4 = C0350mt.m14757a(a2, "requires_charging");
            int a5 = C0350mt.m14757a(a2, "requires_device_idle");
            int a6 = C0350mt.m14757a(a2, "requires_battery_not_low");
            int a7 = C0350mt.m14757a(a2, "requires_storage_not_low");
            int a8 = C0350mt.m14757a(a2, "trigger_content_update_delay");
            int a9 = C0350mt.m14757a(a2, "trigger_max_content_delay");
            int a10 = C0350mt.m14757a(a2, "content_uri_triggers");
            int a11 = C0350mt.m14757a(a2, "id");
            int a12 = C0350mt.m14757a(a2, "state");
            int a13 = C0350mt.m14757a(a2, "worker_class_name");
            int a14 = C0350mt.m14757a(a2, "input_merger_class_name");
            int a15 = C0350mt.m14757a(a2, "input");
            int a16 = C0350mt.m14757a(a2, "output");
            caVar = a;
            try {
                int a17 = C0350mt.m14757a(a2, "initial_delay");
                int a18 = C0350mt.m14757a(a2, "interval_duration");
                int a19 = C0350mt.m14757a(a2, "flex_duration");
                int a20 = C0350mt.m14757a(a2, "run_attempt_count");
                int a21 = C0350mt.m14757a(a2, "backoff_policy");
                int a22 = C0350mt.m14757a(a2, "backoff_delay_duration");
                int a23 = C0350mt.m14757a(a2, "period_start_time");
                int a24 = C0350mt.m14757a(a2, "minimum_retention_duration");
                int a25 = C0350mt.m14757a(a2, "schedule_requested_at");
                int a26 = C0350mt.m14757a(a2, "run_in_foreground");
                int i = a16;
                ArrayList arrayList = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    String string = a2.getString(a11);
                    int i2 = a11;
                    String string2 = a2.getString(a13);
                    int i3 = a13;
                    ahb ahb = new ahb();
                    int i4 = a3;
                    ahb.f482i = ihg.m13058f(a2.getInt(a3));
                    ahb.f475b = a2.getInt(a4) != 0;
                    ahb.f476c = a2.getInt(a5) != 0;
                    ahb.f477d = a2.getInt(a6) != 0;
                    if (a2.getInt(a7) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ahb.f478e = z;
                    int i5 = a4;
                    int i6 = a5;
                    ahb.f479f = a2.getLong(a8);
                    ahb.f480g = a2.getLong(a9);
                    ahb.f481h = ihg.m13025a(a2.getBlob(a10));
                    alg alg = new alg(string, string2);
                    alg.f728q = ihg.m13054d(a2.getInt(a12));
                    alg.f715d = a2.getString(a14);
                    alg.f716e = ahf.m482a(a2.getBlob(a15));
                    int i7 = i;
                    alg.f717f = ahf.m482a(a2.getBlob(i7));
                    int i8 = i5;
                    i = i7;
                    int i9 = a17;
                    alg.f718g = a2.getLong(i9);
                    int i10 = a15;
                    int i11 = a18;
                    alg.f719h = a2.getLong(i11);
                    int i12 = i9;
                    int i13 = a6;
                    int i14 = a19;
                    alg.f720i = a2.getLong(i14);
                    int i15 = a20;
                    alg.f722k = a2.getInt(i15);
                    int i16 = a21;
                    int i17 = i11;
                    alg.f729r = ihg.m13056e(a2.getInt(i16));
                    a19 = i14;
                    int i18 = i13;
                    int i19 = a22;
                    alg.f723l = a2.getLong(i19);
                    int i20 = i15;
                    int i21 = i16;
                    int i22 = a23;
                    alg.f724m = a2.getLong(i22);
                    int i23 = i19;
                    a23 = i22;
                    int i24 = a24;
                    alg.f725n = a2.getLong(i24);
                    int i25 = i20;
                    int i26 = a25;
                    alg.f726o = a2.getLong(i26);
                    int i27 = a26;
                    if (a2.getInt(i27) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    alg.f727p = z2;
                    alg.f721j = ahb;
                    arrayList.add(alg);
                    a25 = i26;
                    a26 = i27;
                    a4 = i8;
                    a15 = i10;
                    a17 = i12;
                    a18 = i17;
                    a20 = i25;
                    a11 = i2;
                    a13 = i3;
                    a3 = i4;
                    a24 = i24;
                    a5 = i6;
                    int i28 = i21;
                    a22 = i23;
                    a6 = i18;
                    a21 = i28;
                }
                a2.close();
                caVar.mo2953b();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                a2.close();
                caVar.mo2953b();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            caVar = a;
            a2.close();
            caVar.mo2953b();
            throw th;
        }
    }

    /* renamed from: b */
    public final List mo609b() {
        C0057ca caVar;
        boolean z;
        boolean z2;
        C0057ca a = C0057ca.m3923a("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground` FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "required_network_type");
            int a4 = C0350mt.m14757a(a2, "requires_charging");
            int a5 = C0350mt.m14757a(a2, "requires_device_idle");
            int a6 = C0350mt.m14757a(a2, "requires_battery_not_low");
            int a7 = C0350mt.m14757a(a2, "requires_storage_not_low");
            int a8 = C0350mt.m14757a(a2, "trigger_content_update_delay");
            int a9 = C0350mt.m14757a(a2, "trigger_max_content_delay");
            int a10 = C0350mt.m14757a(a2, "content_uri_triggers");
            int a11 = C0350mt.m14757a(a2, "id");
            int a12 = C0350mt.m14757a(a2, "state");
            int a13 = C0350mt.m14757a(a2, "worker_class_name");
            int a14 = C0350mt.m14757a(a2, "input_merger_class_name");
            int a15 = C0350mt.m14757a(a2, "input");
            int a16 = C0350mt.m14757a(a2, "output");
            caVar = a;
            try {
                int a17 = C0350mt.m14757a(a2, "initial_delay");
                int a18 = C0350mt.m14757a(a2, "interval_duration");
                int a19 = C0350mt.m14757a(a2, "flex_duration");
                int a20 = C0350mt.m14757a(a2, "run_attempt_count");
                int a21 = C0350mt.m14757a(a2, "backoff_policy");
                int a22 = C0350mt.m14757a(a2, "backoff_delay_duration");
                int a23 = C0350mt.m14757a(a2, "period_start_time");
                int a24 = C0350mt.m14757a(a2, "minimum_retention_duration");
                int a25 = C0350mt.m14757a(a2, "schedule_requested_at");
                int a26 = C0350mt.m14757a(a2, "run_in_foreground");
                int i = a16;
                ArrayList arrayList = new ArrayList(a2.getCount());
                while (a2.moveToNext()) {
                    String string = a2.getString(a11);
                    int i2 = a11;
                    String string2 = a2.getString(a13);
                    int i3 = a13;
                    ahb ahb = new ahb();
                    int i4 = a3;
                    ahb.f482i = ihg.m13058f(a2.getInt(a3));
                    ahb.f475b = a2.getInt(a4) != 0;
                    ahb.f476c = a2.getInt(a5) != 0;
                    ahb.f477d = a2.getInt(a6) != 0;
                    if (a2.getInt(a7) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ahb.f478e = z;
                    int i5 = a4;
                    int i6 = a5;
                    ahb.f479f = a2.getLong(a8);
                    ahb.f480g = a2.getLong(a9);
                    ahb.f481h = ihg.m13025a(a2.getBlob(a10));
                    alg alg = new alg(string, string2);
                    alg.f728q = ihg.m13054d(a2.getInt(a12));
                    alg.f715d = a2.getString(a14);
                    alg.f716e = ahf.m482a(a2.getBlob(a15));
                    int i7 = i;
                    alg.f717f = ahf.m482a(a2.getBlob(i7));
                    int i8 = i5;
                    i = i7;
                    int i9 = a17;
                    alg.f718g = a2.getLong(i9);
                    int i10 = a15;
                    int i11 = a18;
                    alg.f719h = a2.getLong(i11);
                    int i12 = i9;
                    int i13 = a6;
                    int i14 = a19;
                    alg.f720i = a2.getLong(i14);
                    int i15 = a20;
                    alg.f722k = a2.getInt(i15);
                    int i16 = a21;
                    int i17 = i11;
                    alg.f729r = ihg.m13056e(a2.getInt(i16));
                    a19 = i14;
                    int i18 = i13;
                    int i19 = a22;
                    alg.f723l = a2.getLong(i19);
                    int i20 = i15;
                    int i21 = i16;
                    int i22 = a23;
                    alg.f724m = a2.getLong(i22);
                    int i23 = i19;
                    a23 = i22;
                    int i24 = a24;
                    alg.f725n = a2.getLong(i24);
                    int i25 = i20;
                    int i26 = a25;
                    alg.f726o = a2.getLong(i26);
                    int i27 = a26;
                    if (a2.getInt(i27) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    alg.f727p = z2;
                    alg.f721j = ahb;
                    arrayList.add(alg);
                    a25 = i26;
                    a26 = i27;
                    a4 = i8;
                    a15 = i10;
                    a17 = i12;
                    a18 = i17;
                    a20 = i25;
                    a11 = i2;
                    a13 = i3;
                    a3 = i4;
                    a24 = i24;
                    a5 = i6;
                    int i28 = i21;
                    a22 = i23;
                    a6 = i18;
                    a21 = i28;
                }
                a2.close();
                caVar.mo2953b();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                a2.close();
                caVar.mo2953b();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            caVar = a;
            a2.close();
            caVar.mo2953b();
            throw th;
        }
    }

    /* renamed from: f */
    public final int mo617f(String str) {
        C0057ca a = C0057ca.m3923a("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int i = 0;
            if (a2.moveToFirst()) {
                i = ihg.m13054d(a2.getInt(0));
            }
            return i;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: e */
    public final List mo616e(String str) {
        C0057ca a = C0057ca.m3923a("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                arrayList.add(a2.getString(0));
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: b */
    public final alg mo608b(String str) {
        C0057ca caVar;
        alg alg;
        boolean z;
        boolean z2;
        boolean z3;
        String str2 = str;
        C0057ca a = C0057ca.m3923a("SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground` FROM workspec WHERE id=?", 1);
        if (str2 == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str2);
        }
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "required_network_type");
            int a4 = C0350mt.m14757a(a2, "requires_charging");
            int a5 = C0350mt.m14757a(a2, "requires_device_idle");
            int a6 = C0350mt.m14757a(a2, "requires_battery_not_low");
            int a7 = C0350mt.m14757a(a2, "requires_storage_not_low");
            int a8 = C0350mt.m14757a(a2, "trigger_content_update_delay");
            int a9 = C0350mt.m14757a(a2, "trigger_max_content_delay");
            int a10 = C0350mt.m14757a(a2, "content_uri_triggers");
            int a11 = C0350mt.m14757a(a2, "id");
            int a12 = C0350mt.m14757a(a2, "state");
            int a13 = C0350mt.m14757a(a2, "worker_class_name");
            int a14 = C0350mt.m14757a(a2, "input_merger_class_name");
            int a15 = C0350mt.m14757a(a2, "input");
            int a16 = C0350mt.m14757a(a2, "output");
            caVar = a;
            try {
                int a17 = C0350mt.m14757a(a2, "initial_delay");
                int a18 = C0350mt.m14757a(a2, "interval_duration");
                int a19 = C0350mt.m14757a(a2, "flex_duration");
                int a20 = C0350mt.m14757a(a2, "run_attempt_count");
                int a21 = C0350mt.m14757a(a2, "backoff_policy");
                int a22 = C0350mt.m14757a(a2, "backoff_delay_duration");
                int a23 = C0350mt.m14757a(a2, "period_start_time");
                int a24 = C0350mt.m14757a(a2, "minimum_retention_duration");
                int a25 = C0350mt.m14757a(a2, "schedule_requested_at");
                int a26 = C0350mt.m14757a(a2, "run_in_foreground");
                if (a2.moveToFirst()) {
                    String string = a2.getString(a11);
                    String string2 = a2.getString(a13);
                    int i = a26;
                    ahb ahb = new ahb();
                    ahb.f482i = ihg.m13058f(a2.getInt(a3));
                    if (a2.getInt(a4) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ahb.f475b = z;
                    ahb.f476c = a2.getInt(a5) != 0;
                    ahb.f477d = a2.getInt(a6) != 0;
                    if (a2.getInt(a7) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    ahb.f478e = z2;
                    ahb.f479f = a2.getLong(a8);
                    ahb.f480g = a2.getLong(a9);
                    ahb.f481h = ihg.m13025a(a2.getBlob(a10));
                    alg = new alg(string, string2);
                    alg.f728q = ihg.m13054d(a2.getInt(a12));
                    alg.f715d = a2.getString(a14);
                    alg.f716e = ahf.m482a(a2.getBlob(a15));
                    alg.f717f = ahf.m482a(a2.getBlob(a16));
                    alg.f718g = a2.getLong(a17);
                    alg.f719h = a2.getLong(a18);
                    alg.f720i = a2.getLong(a19);
                    alg.f722k = a2.getInt(a20);
                    alg.f729r = ihg.m13056e(a2.getInt(a21));
                    alg.f723l = a2.getLong(a22);
                    alg.f724m = a2.getLong(a23);
                    alg.f725n = a2.getLong(a24);
                    alg.f726o = a2.getLong(a25);
                    if (a2.getInt(i) != 0) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    alg.f727p = z3;
                    alg.f721j = ahb;
                } else {
                    alg = null;
                }
                a2.close();
                caVar.mo2953b();
                return alg;
            } catch (Throwable th) {
                th = th;
                a2.close();
                caVar.mo2953b();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            caVar = a;
            a2.close();
            caVar.mo2953b();
            throw th;
        }
    }

    /* renamed from: c */
    public final List mo612c(String str) {
        C0057ca a = C0057ca.m3923a("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            a.mo1920a(1);
        } else {
            a.mo1922a(1, str);
        }
        this.f730a.mo2844d();
        Cursor a2 = this.f730a.mo2840a((C0036bg) a);
        try {
            int a3 = C0350mt.m14757a(a2, "id");
            int a4 = C0350mt.m14757a(a2, "state");
            ArrayList arrayList = new ArrayList(a2.getCount());
            while (a2.moveToNext()) {
                alf alf = new alf();
                alf.f710a = a2.getString(a3);
                alf.f711b = ihg.m13054d(a2.getInt(a4));
                arrayList.add(alf);
            }
            return arrayList;
        } finally {
            a2.close();
            a.mo2953b();
        }
    }

    /* renamed from: g */
    public final void mo618g(String str) {
        this.f730a.mo2844d();
        C0037bh b = this.f735f.mo3016b();
        if (str == null) {
            b.mo1920a(1);
        } else {
            b.mo1922a(1, str);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f735f.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final void mo604a(alg alg) {
        this.f730a.mo2844d();
        this.f730a.mo2845e();
        try {
            this.f731b.mo2686a(alg);
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
        }
    }

    /* renamed from: b */
    public final void mo610b(String str, long j) {
        this.f730a.mo2844d();
        C0037bh b = this.f737h.mo3016b();
        b.mo1921a(1, j);
        if (str == null) {
            b.mo1920a(2);
        } else {
            b.mo1922a(2, str);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f737h.mo3015a(b);
        }
    }

    /* renamed from: d */
    public final void mo614d() {
        this.f730a.mo2844d();
        C0037bh b = this.f738i.mo3016b();
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f738i.mo3015a(b);
        }
    }

    /* renamed from: h */
    public final void mo619h(String str) {
        this.f730a.mo2844d();
        C0037bh b = this.f736g.mo3016b();
        if (str == null) {
            b.mo1920a(1);
        } else {
            b.mo1922a(1, str);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f736g.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final void mo607a(String str, ahf ahf) {
        this.f730a.mo2844d();
        C0037bh b = this.f733d.mo3016b();
        byte[] a = ahf.m483a(ahf);
        if (a == null) {
            b.mo1920a(1);
        } else {
            b.mo1923a(1, a);
        }
        if (str != null) {
            b.mo1922a(2, str);
        } else {
            b.mo1920a(2);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f733d.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final void mo606a(String str, long j) {
        this.f730a.mo2844d();
        C0037bh b = this.f734e.mo3016b();
        b.mo1921a(1, j);
        if (str == null) {
            b.mo1920a(2);
        } else {
            b.mo1922a(2, str);
        }
        this.f730a.mo2845e();
        try {
            b.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
            this.f734e.mo3015a(b);
        }
    }

    /* renamed from: a */
    public final void mo603a(int i, String... strArr) {
        this.f730a.mo2844d();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE workspec SET state=? WHERE id IN (");
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            sb.append("?");
            if (i2 < length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        C0037bh a = this.f730a.mo2841a(sb.toString());
        a.mo1921a(1, (long) ihg.m13050c(i));
        int i3 = 2;
        for (String str : strArr) {
            if (str == null) {
                a.mo1920a(i3);
            } else {
                a.mo1922a(i3, str);
            }
            i3++;
        }
        this.f730a.mo2845e();
        try {
            a.mo2033b();
            this.f730a.mo2847g();
        } finally {
            this.f730a.mo2846f();
        }
    }
}
