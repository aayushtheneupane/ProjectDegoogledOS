package com.android.messaging.p041ui.appsettings;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.datamodel.ParticipantRefresh;
import com.android.messaging.util.C1451h;

/* renamed from: com.android.messaging.ui.appsettings.q */
public class C1101q extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: Aa */
    private PhoneNumberPreference f1752Aa;

    /* renamed from: Ba */
    private Preference f1753Ba;

    /* renamed from: Ca */
    private String f1754Ca;

    /* renamed from: Da */
    private String f1755Da;
    /* access modifiers changed from: private */
    public int mSubId;

    /* renamed from: xm */
    private void m2721xm() {
        this.f1753Ba.setSummary(getPreferenceScreen().getSharedPreferences().getBoolean(this.f1754Ca, getResources().getBoolean(R.bool.group_mms_pref_default)) ? R.string.enable_group_mms : R.string.disable_group_mms);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            android.app.Activity r6 = r5.getActivity()
            android.content.Intent r6 = r6.getIntent()
            com.android.messaging.util.C1424b.m3594t(r6)
            r0 = -1
            if (r6 == 0) goto L_0x0017
            java.lang.String r1 = "sub_id"
            int r0 = r6.getIntExtra(r1, r0)
        L_0x0017:
            r5.mSubId = r0
            com.android.messaging.f r6 = com.android.messaging.C0967f.get()
            int r0 = r5.mSubId
            com.android.messaging.util.h r6 = r6.mo6661ha(r0)
            android.preference.PreferenceManager r0 = r5.getPreferenceManager()
            java.lang.String r6 = r6.getSharedPreferencesName()
            r0.setSharedPreferencesName(r6)
            r6 = 2132082693(0x7f150005, float:1.9805507E38)
            r5.addPreferencesFromResource(r6)
            r6 = 2131820856(0x7f110138, float:1.9274439E38)
            java.lang.String r6 = r5.getString(r6)
            r5.f1755Da = r6
            java.lang.String r6 = r5.f1755Da
            android.preference.Preference r6 = r5.findPreference(r6)
            com.android.messaging.ui.appsettings.PhoneNumberPreference r6 = (com.android.messaging.p041ui.appsettings.PhoneNumberPreference) r6
            r5.f1752Aa = r6
            r6 = 2131820598(0x7f110036, float:1.9273915E38)
            java.lang.String r6 = r5.getString(r6)
            android.preference.Preference r6 = r5.findPreference(r6)
            android.preference.PreferenceCategory r6 = (android.preference.PreferenceCategory) r6
            r0 = 2131820854(0x7f110136, float:1.9274435E38)
            java.lang.String r0 = r5.getString(r0)
            android.preference.Preference r0 = r5.findPreference(r0)
            android.preference.PreferenceCategory r0 = (android.preference.PreferenceCategory) r0
            com.android.messaging.ui.appsettings.PhoneNumberPreference r1 = r5.f1752Aa
            int r2 = r5.mSubId
            com.android.messaging.util.sa r2 = com.android.messaging.util.C1474sa.get(r2)
            r3 = 0
            java.lang.String r2 = r2.mo8229la(r3)
            int r4 = r5.mSubId
            r1.mo7141b(r2, r4)
            r1 = 2131820776(0x7f1100e8, float:1.9274276E38)
            java.lang.String r1 = r5.getString(r1)
            r5.f1754Ca = r1
            java.lang.String r1 = r5.f1754Ca
            android.preference.Preference r1 = r5.findPreference(r1)
            r5.f1753Ba = r1
            int r1 = r5.mSubId
            com.android.messaging.sms.t r1 = com.android.messaging.sms.C1024t.get(r1)
            boolean r1 = r1.mo6841li()
            if (r1 != 0) goto L_0x0096
            android.preference.Preference r1 = r5.f1753Ba
            r0.removePreference(r1)
            goto L_0x00a3
        L_0x0096:
            android.preference.Preference r0 = r5.f1753Ba
            com.android.messaging.ui.appsettings.o r1 = new com.android.messaging.ui.appsettings.o
            r1.<init>(r5)
            r0.setOnPreferenceClickListener(r1)
            r5.m2721xm()
        L_0x00a3:
            int r0 = r5.mSubId
            com.android.messaging.sms.t r0 = com.android.messaging.sms.C1024t.get(r0)
            boolean r0 = r0.mo6849ui()
            r1 = 2131820731(0x7f1100bb, float:1.9274185E38)
            if (r0 != 0) goto L_0x00bd
            java.lang.String r0 = r5.getString(r1)
            android.preference.Preference r0 = r5.findPreference(r0)
            r6.removePreference(r0)
        L_0x00bd:
            r0 = 2131821025(0x7f1101e1, float:1.9274782E38)
            java.lang.String r0 = r5.getString(r0)
            android.preference.Preference r0 = r5.findPreference(r0)
            int r2 = r5.mSubId
            com.android.messaging.sms.t r2 = com.android.messaging.sms.C1024t.get(r2)
            boolean r2 = r2.mo6851wi()
            if (r2 != 0) goto L_0x00d5
            goto L_0x00e8
        L_0x00d5:
            android.app.Activity r2 = r5.getActivity()     // Catch:{ IllegalArgumentException -> 0x00e8 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ IllegalArgumentException -> 0x00e8 }
            java.lang.String r4 = "com.android.cellbroadcastreceiver"
            int r2 = r2.getApplicationEnabledSetting(r4)     // Catch:{ IllegalArgumentException -> 0x00e8 }
            r4 = 2
            if (r2 == r4) goto L_0x00e8
            r2 = 1
            goto L_0x00e9
        L_0x00e8:
            r2 = r3
        L_0x00e9:
            if (r2 != 0) goto L_0x00ef
            r6.removePreference(r0)
            goto L_0x00f7
        L_0x00ef:
            com.android.messaging.ui.appsettings.p r2 = new com.android.messaging.ui.appsettings.p
            r2.<init>(r5)
            r0.setOnPreferenceClickListener(r2)
        L_0x00f7:
            r0 = 2131820970(0x7f1101aa, float:1.927467E38)
            java.lang.String r0 = r5.getString(r0)
            android.preference.Preference r0 = r5.findPreference(r0)
            android.preference.PreferenceScreen r0 = (android.preference.PreferenceScreen) r0
            boolean r2 = androidx.appcompat.mms.MmsManager.shouldUseLegacyMms()
            if (r2 == 0) goto L_0x012d
            boolean r2 = com.android.messaging.sms.C1029y.m2413Li()
            if (r2 == 0) goto L_0x0117
            boolean r2 = com.android.messaging.sms.C1006b.m2348nb()
            if (r2 != 0) goto L_0x0117
            goto L_0x012d
        L_0x0117:
            com.android.messaging.ui.Ea r6 = com.android.messaging.p041ui.C1040Ea.get()
            android.preference.PreferenceScreen r2 = r5.getPreferenceScreen()
            android.content.Context r2 = r2.getContext()
            int r4 = r5.mSubId
            android.content.Intent r6 = r6.mo6971d((android.content.Context) r2, (int) r4)
            r0.setIntent(r6)
            goto L_0x0130
        L_0x012d:
            r6.removePreference(r0)
        L_0x0130:
            com.android.messaging.util.sa r6 = com.android.messaging.util.C1474sa.getDefault()
            boolean r6 = r6.mo8228kk()
            if (r6 != 0) goto L_0x015a
            android.preference.Preference r6 = r5.f1753Ba
            r6.setEnabled(r3)
            r6 = 2131820639(0x7f11005f, float:1.9273999E38)
            java.lang.String r6 = r5.getString(r6)
            android.preference.Preference r6 = r5.findPreference(r6)
            r6.setEnabled(r3)
            java.lang.String r6 = r5.getString(r1)
            android.preference.Preference r5 = r5.findPreference(r6)
            if (r5 == 0) goto L_0x015a
            r5.setEnabled(r3)
        L_0x015a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.appsettings.C1101q.onCreate(android.os.Bundle):void");
    }

    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals(this.f1754Ca)) {
            m2721xm();
        } else if (str.equals(this.f1755Da)) {
            String text = this.f1752Aa.getText();
            C1451h ha = C1451h.m3725ha(this.mSubId);
            if (TextUtils.isEmpty(text)) {
                ha.remove(this.f1755Da);
            } else {
                ha.putString(getString(R.string.mms_phone_number_pref_key), text);
            }
            ParticipantRefresh.m1288pe();
        }
    }
}
