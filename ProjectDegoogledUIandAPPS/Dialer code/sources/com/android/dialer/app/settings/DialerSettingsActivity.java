package com.android.dialer.app.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberCompat;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public class DialerSettingsActivity extends AppCompatPreferenceActivity {
    private List<PreferenceActivity.Header> headers;
    private boolean migrationStatusOnBuildHeaders;

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return true;
    }

    public void onBackPressed() {
        if (isSafeToCommitTransactions()) {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01bd, code lost:
        if ((r0 != null && r0.isHearingAidCompatibilitySupported()) != false) goto L_0x01bf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBuildHeaders(java.util.List<android.preference.PreferenceActivity.Header> r15) {
        /*
            r14 = this;
            r14.headers = r15
            android.content.res.Resources r0 = r14.getResources()
            r1 = 2131034121(0x7f050009, float:1.767875E38)
            boolean r0 = r0.getBoolean(r1)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0020
            android.content.res.Resources r0 = r14.getResources()
            r3 = 2131034123(0x7f05000b, float:1.7678755E38)
            boolean r0 = r0.getBoolean(r3)
            if (r0 == 0) goto L_0x0020
            r0 = r1
            goto L_0x0021
        L_0x0020:
            r0 = r2
        L_0x0021:
            if (r0 == 0) goto L_0x0038
            android.preference.PreferenceActivity$Header r0 = new android.preference.PreferenceActivity$Header
            r0.<init>()
            r3 = 2131820939(0x7f11018b, float:1.9274607E38)
            r0.titleRes = r3
            java.lang.Class<com.android.dialer.app.settings.DisplayOptionsSettingsFragment> r3 = com.android.dialer.app.settings.DisplayOptionsSettingsFragment.class
            java.lang.String r3 = r3.getName()
            r0.fragment = r3
            r15.add(r0)
        L_0x0038:
            android.preference.PreferenceActivity$Header r0 = new android.preference.PreferenceActivity$Header
            r0.<init>()
            r3 = 2131821334(0x7f110316, float:1.9275408E38)
            r0.titleRes = r3
            java.lang.Class<com.android.dialer.app.settings.SoundSettingsFragment> r3 = com.android.dialer.app.settings.SoundSettingsFragment.class
            java.lang.String r3 = r3.getName()
            r0.fragment = r3
            r3 = 2131296870(0x7f090266, double:1.0530005646E-314)
            r0.id = r3
            r15.add(r0)
            android.preference.PreferenceActivity$Header r0 = new android.preference.PreferenceActivity$Header
            r0.<init>()
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r4 = "android.telecom.action.SHOW_RESPOND_VIA_SMS_SETTINGS"
            r3.<init>(r4)
            r4 = 2131821261(0x7f1102cd, float:1.927526E38)
            r0.titleRes = r4
            r0.intent = r3
            r15.add(r0)
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r14.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.Class<android.os.UserManager> r3 = android.os.UserManager.class
            java.lang.Object r3 = r14.getSystemService(r3)
            android.os.UserManager r3 = (android.os.UserManager) r3
            boolean r3 = r3.isSystemUser()
            r4 = 67108864(0x4000000, float:1.5046328E-36)
            if (r3 == 0) goto L_0x00a0
            int r5 = com.android.dialer.compat.telephony.TelephonyManagerCompat.getPhoneCount(r0)
            if (r5 > r1) goto L_0x00a0
            android.preference.PreferenceActivity$Header r1 = new android.preference.PreferenceActivity$Header
            r1.<init>()
            android.content.Intent r5 = new android.content.Intent
            java.lang.String r6 = "android.telecom.action.SHOW_CALL_SETTINGS"
            r5.<init>(r6)
            r5.setFlags(r4)
            r4 = 2131820759(0x7f1100d7, float:1.9274242E38)
            r1.titleRes = r4
            r1.intent = r5
            r15.add(r1)
            goto L_0x00b9
        L_0x00a0:
            android.preference.PreferenceActivity$Header r1 = new android.preference.PreferenceActivity$Header
            r1.<init>()
            android.content.Intent r5 = new android.content.Intent
            java.lang.String r6 = "android.telecom.action.CHANGE_PHONE_ACCOUNTS"
            r5.<init>(r6)
            r5.setFlags(r4)
            r4 = 2131821218(0x7f1102a2, float:1.9275173E38)
            r1.titleRes = r4
            r1.intent = r5
            r15.add(r1)
        L_0x00b9:
            boolean r1 = com.android.dialer.blocking.FilteredNumberCompat.canCurrentUserOpenBlockSettings(r14)
            if (r1 == 0) goto L_0x00d8
            android.preference.PreferenceActivity$Header r1 = new android.preference.PreferenceActivity$Header
            r1.<init>()
            r4 = 2131821089(0x7f110221, float:1.9274911E38)
            r1.titleRes = r4
            android.content.Intent r4 = com.android.dialer.blocking.FilteredNumberCompat.createManageBlockedNumbersIntent(r14)
            r1.intent = r4
            r15.add(r1)
            boolean r1 = com.android.dialer.blocking.FilteredNumberCompat.hasMigratedToNewBlocking(r14)
            r14.migrationStatusOnBuildHeaders = r1
        L_0x00d8:
            java.lang.String r1 = "DialerSettingsActivity.addVoicemailSettings"
            if (r3 != 0) goto L_0x00e5
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "user not primary user"
            com.android.dialer.common.LogUtil.m9i(r1, r5, r4)
            goto L_0x01aa
        L_0x00e5:
            int r4 = android.os.Build.VERSION.SDK_INT
            boolean r4 = com.android.dialer.util.PermissionsUtil.hasReadPhoneStatePermissions(r14)
            if (r4 != 0) goto L_0x00f6
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "Missing READ_PHONE_STATE"
            com.android.dialer.common.LogUtil.m9i(r1, r5, r4)
            goto L_0x01aa
        L_0x00f6:
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.String r5 = "adding voicemail settings"
            com.android.dialer.common.LogUtil.m9i(r1, r5, r4)
            android.preference.PreferenceActivity$Header r4 = new android.preference.PreferenceActivity$Header
            r4.<init>()
            r5 = 2131821574(0x7f110406, float:1.9275895E38)
            r4.titleRes = r5
            java.lang.Class<android.telecom.TelecomManager> r6 = android.telecom.TelecomManager.class
            java.lang.Object r6 = r14.getSystemService(r6)
            android.telecom.TelecomManager r6 = (android.telecom.TelecomManager) r6
            java.util.List r7 = r6.getCallCapablePhoneAccounts()
            java.util.Iterator r7 = r7.iterator()
            r8 = 0
            r9 = r8
        L_0x0119:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x0150
            java.lang.Object r10 = r7.next()
            android.telecom.PhoneAccountHandle r10 = (android.telecom.PhoneAccountHandle) r10
            android.telecom.PhoneAccount r11 = r6.getPhoneAccount(r10)
            if (r11 != 0) goto L_0x012c
            goto L_0x0119
        L_0x012c:
            r12 = 4
            boolean r11 = r11.hasCapabilities(r12)
            if (r11 == 0) goto L_0x0119
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            java.lang.String r12 = " is a SIM account"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            java.lang.Object[] r12 = new java.lang.Object[r2]
            java.lang.String r13 = "DialerSettingsActivity.getSoleSimAccount"
            com.android.dialer.common.LogUtil.m9i(r13, r11, r12)
            if (r9 == 0) goto L_0x014e
            goto L_0x0151
        L_0x014e:
            r9 = r10
            goto L_0x0119
        L_0x0150:
            r8 = r9
        L_0x0151:
            java.lang.String r6 = "phone_account_handle"
            if (r8 != 0) goto L_0x018e
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r8 = "showing multi-SIM voicemail settings"
            com.android.dialer.common.LogUtil.m9i(r1, r8, r7)
            java.lang.Class<com.android.dialer.app.settings.PhoneAccountSelectionFragment> r1 = com.android.dialer.app.settings.PhoneAccountSelectionFragment.class
            java.lang.String r1 = r1.getName()
            r4.fragment = r1
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.Class<com.android.dialer.voicemail.settings.VoicemailSettingsFragment> r7 = com.android.dialer.voicemail.settings.VoicemailSettingsFragment.class
            java.lang.String r7 = r7.getName()
            java.lang.String r8 = "target_fragment"
            r1.putString(r8, r7)
            java.lang.String r7 = "phone_account_handle_key"
            r1.putString(r7, r6)
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            java.lang.String r7 = "arguments"
            r1.putBundle(r7, r6)
            java.lang.String r6 = "target_title_res"
            r1.putInt(r6, r5)
            r4.fragmentArguments = r1
            r15.add(r4)
            goto L_0x01aa
        L_0x018e:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r7 = "showing single-SIM voicemail settings"
            com.android.dialer.common.LogUtil.m9i(r1, r7, r5)
            java.lang.Class<com.android.dialer.voicemail.settings.VoicemailSettingsFragment> r1 = com.android.dialer.voicemail.settings.VoicemailSettingsFragment.class
            java.lang.String r1 = r1.getName()
            r4.fragment = r1
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r1.putParcelable(r6, r8)
            r4.fragmentArguments = r1
            r15.add(r4)
        L_0x01aa:
            if (r3 == 0) goto L_0x01d5
            boolean r1 = com.android.dialer.compat.telephony.TelephonyManagerCompat.isTtyModeSupported(r0)
            if (r1 != 0) goto L_0x01bf
            if (r0 == 0) goto L_0x01bc
            boolean r0 = r0.isHearingAidCompatibilitySupported()
            if (r0 == 0) goto L_0x01bc
            r0 = 1
            goto L_0x01bd
        L_0x01bc:
            r0 = r2
        L_0x01bd:
            if (r0 == 0) goto L_0x01d5
        L_0x01bf:
            android.preference.PreferenceActivity$Header r0 = new android.preference.PreferenceActivity$Header
            r0.<init>()
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r3 = "android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS"
            r1.<init>(r3)
            r3 = 2131820611(0x7f110043, float:1.9273942E38)
            r0.titleRes = r3
            r0.intent = r1
            r15.add(r0)
        L_0x01d5:
            android.content.Context r0 = r14.getApplicationContext()
            com.android.dialer.configprovider.ConfigProviderComponent r0 = com.android.dialer.configprovider.ConfigProviderComponent.get(r0)
            com.android.dialer.configprovider.ConfigProvider r0 = r0.getConfigProvider()
            boolean r0 = com.android.dialer.assisteddialing.ConcreteCreator.isAssistedDialingEnabled(r0)
            java.lang.String r1 = "showing assisted dialing header: "
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline10(r1, r0)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "DialerSettingsActivity.onBuildHeaders"
            com.android.dialer.common.LogUtil.m9i(r3, r1, r2)
            if (r0 == 0) goto L_0x020a
            android.preference.PreferenceActivity$Header r0 = new android.preference.PreferenceActivity$Header
            r0.<init>()
            r1 = 2131820642(0x7f110062, float:1.9274005E38)
            r0.titleRes = r1
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "com.android.dialer.app.settings.SHOW_ASSISTED_DIALING_SETTINGS"
            r1.<init>(r2)
            r0.intent = r1
            r15.add(r0)
        L_0x020a:
            boolean r14 = r14.showAbout()
            if (r14 == 0) goto L_0x0225
            android.preference.PreferenceActivity$Header r14 = new android.preference.PreferenceActivity$Header
            r14.<init>()
            r0 = 2131820610(0x7f110042, float:1.927394E38)
            r14.titleRes = r0
            java.lang.Class<com.android.dialer.about.AboutPhoneFragment> r0 = com.android.dialer.about.AboutPhoneFragment.class
            java.lang.String r0 = r0.getName()
            r14.fragment = r0
            r15.add(r14)
        L_0x0225:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.settings.DialerSettingsActivity.onBuildHeaders(java.util.List):void");
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String schemeSpecificPart;
        List<PreferenceActivity.Header> list;
        LogUtil.enterBlock("DialerSettingsActivity.onCreate");
        super.onCreate(bundle);
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Uri data = getIntent().getData();
        if (data != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null && (list = this.headers) != null) {
            for (PreferenceActivity.Header next : list) {
                if (schemeSpecificPart.equals(next.fragment)) {
                    LogUtil.m9i("DialerSettingsActivity.onCreate", GeneratedOutlineSupport.outline8("switching to header: ", schemeSpecificPart), new Object[0]);
                    switchToHeader(next);
                    return;
                }
            }
        }
    }

    public void onHeaderClick(PreferenceActivity.Header header, int i) {
        if (header.id != 2131296870 || Settings.System.canWrite(this)) {
            super.onHeaderClick(header, i);
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.toast_cannot_write_system_settings), 0).show();
        startActivity(new Intent("android.settings.SOUND_SETTINGS"));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.migrationStatusOnBuildHeaders != FilteredNumberCompat.hasMigratedToNewBlocking(this)) {
            invalidateHeaders();
        }
    }

    public boolean showAbout() {
        return true;
    }
}
