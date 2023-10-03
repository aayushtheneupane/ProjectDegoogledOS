package com.android.settings.network;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.util.ArrayUtils;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApnEditor extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, View.OnKeyListener {
    static final int APN_INDEX = 2;
    static final int CARRIER_ENABLED_INDEX = 17;
    static final int MCC_INDEX = 9;
    static final int MNC_INDEX = 10;
    static final int NAME_INDEX = 1;
    private static final String TAG = "ApnEditor";
    static final int TYPE_INDEX = 15;
    static String sNotSet;
    private static final String[] sProjection = {"_id", "name", "apn", "proxy", "port", "user", "server", "password", "mmsc", "mcc", "mnc", "numeric", "mmsproxy", "mmsport", "authtype", "type", "protocol", "carrier_enabled", "bearer", "bearer_bitmask", "roaming_protocol", "mvno_type", "mvno_match_data", "edited", "user_editable"};
    EditTextPreference mApn;
    ApnData mApnData;
    EditTextPreference mApnType;
    ListPreference mAuthType;
    private int mBearerInitialVal = 0;
    MultiSelectListPreference mBearerMulti;
    SwitchPreference mCarrierEnabled;
    private Uri mCarrierUri;
    private String mCurMcc;
    private String mCurMnc;
    String[] mDefaultApnTypes;
    EditTextPreference mMcc;
    EditTextPreference mMmsPort;
    EditTextPreference mMmsProxy;
    EditTextPreference mMmsc;
    EditTextPreference mMnc;
    EditTextPreference mMvnoMatchData;
    private String mMvnoMatchDataStr;
    ListPreference mMvnoType;
    private String mMvnoTypeStr;
    EditTextPreference mName;
    private boolean mNewApn;
    EditTextPreference mPassword;
    EditTextPreference mPort;
    ListPreference mProtocol;
    EditTextPreference mProxy;
    private boolean mReadOnlyApn;
    private String[] mReadOnlyApnFields;
    String[] mReadOnlyApnTypes;
    ListPreference mRoamingProtocol;
    EditTextPreference mServer;
    private int mSubId;
    private TelephonyManager mTelephonyManager;
    EditTextPreference mUser;

    public int getMetricsCategory() {
        return 13;
    }

    public void onCreate(Bundle bundle) {
        PersistableBundle configForSubId;
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.apn_editor);
        sNotSet = getResources().getString(C1715R.string.apn_not_set);
        this.mName = (EditTextPreference) findPreference("apn_name");
        this.mApn = (EditTextPreference) findPreference("apn_apn");
        this.mProxy = (EditTextPreference) findPreference("apn_http_proxy");
        this.mPort = (EditTextPreference) findPreference("apn_http_port");
        this.mUser = (EditTextPreference) findPreference("apn_user");
        this.mServer = (EditTextPreference) findPreference("apn_server");
        this.mPassword = (EditTextPreference) findPreference("apn_password");
        this.mMmsProxy = (EditTextPreference) findPreference("apn_mms_proxy");
        this.mMmsPort = (EditTextPreference) findPreference("apn_mms_port");
        this.mMmsc = (EditTextPreference) findPreference("apn_mmsc");
        this.mMcc = (EditTextPreference) findPreference("apn_mcc");
        this.mMnc = (EditTextPreference) findPreference("apn_mnc");
        this.mApnType = (EditTextPreference) findPreference("apn_type");
        this.mAuthType = (ListPreference) findPreference("auth_type");
        this.mProtocol = (ListPreference) findPreference("apn_protocol");
        this.mRoamingProtocol = (ListPreference) findPreference("apn_roaming_protocol");
        this.mCarrierEnabled = (SwitchPreference) findPreference("carrier_enabled");
        this.mBearerMulti = (MultiSelectListPreference) findPreference("bearer_multi");
        this.mMvnoType = (ListPreference) findPreference("mvno_type");
        this.mMvnoMatchData = (EditTextPreference) findPreference("mvno_match_data");
        Intent intent = getIntent();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            finish();
            return;
        }
        this.mSubId = intent.getIntExtra("sub_id", -1);
        this.mReadOnlyApn = false;
        Uri uri = null;
        this.mReadOnlyApnTypes = null;
        this.mReadOnlyApnFields = null;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getSystemService("carrier_config");
        if (!(carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(this.mSubId)) == null)) {
            this.mReadOnlyApnTypes = configForSubId.getStringArray("read_only_apn_types_string_array");
            if (!ArrayUtils.isEmpty(this.mReadOnlyApnTypes)) {
                String str = TAG;
                Log.d(str, "onCreate: read only APN type: " + Arrays.toString(this.mReadOnlyApnTypes));
            }
            this.mReadOnlyApnFields = configForSubId.getStringArray("read_only_apn_fields_string_array");
            this.mDefaultApnTypes = configForSubId.getStringArray("apn_settings_default_apn_types_string_array");
            if (!ArrayUtils.isEmpty(this.mDefaultApnTypes)) {
                String str2 = TAG;
                Log.d(str2, "onCreate: default apn types: " + Arrays.toString(this.mDefaultApnTypes));
            }
        }
        if (action.equals("android.intent.action.EDIT")) {
            uri = intent.getData();
            if (!uri.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                String str3 = TAG;
                Log.e(str3, "Edit request not for carrier table. Uri: " + uri);
                finish();
                return;
            }
        } else if (action.equals("android.intent.action.INSERT")) {
            this.mCarrierUri = intent.getData();
            if (!this.mCarrierUri.isPathPrefixMatch(Telephony.Carriers.CONTENT_URI)) {
                String str4 = TAG;
                Log.e(str4, "Insert request not for carrier table. Uri: " + this.mCarrierUri);
                finish();
                return;
            }
            this.mNewApn = true;
            this.mMvnoTypeStr = intent.getStringExtra("mvno_type");
            this.mMvnoMatchDataStr = intent.getStringExtra("mvno_match_data");
        } else {
            finish();
            return;
        }
        if (uri != null) {
            this.mApnData = getApnDataFromUri(uri);
        } else {
            this.mApnData = new ApnData(sProjection.length);
        }
        this.mTelephonyManager = (TelephonyManager) getSystemService("phone");
        boolean z = this.mApnData.getInteger(23, 1).intValue() == 1;
        String str5 = TAG;
        Log.d(str5, "onCreate: EDITED " + z);
        if (!z && (this.mApnData.getInteger(24, 1).intValue() == 0 || apnTypesMatch(this.mReadOnlyApnTypes, this.mApnData.getString(15)))) {
            Log.d(TAG, "onCreate: apnTypesMatch; read-only APN");
            this.mReadOnlyApn = true;
            disableAllFields();
        } else if (!ArrayUtils.isEmpty(this.mReadOnlyApnFields)) {
            disableFields(this.mReadOnlyApnFields);
        }
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            getPreferenceScreen().getPreference(i).setOnPreferenceChangeListener(this);
        }
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        fillUI(bundle == null);
    }

    static String formatInteger(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return String.format(getCorrectDigitsFormat(str), new Object[]{Integer.valueOf(parseInt)});
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    static String getCorrectDigitsFormat(String str) {
        return str.length() == 2 ? "%02d" : "%03d";
    }

    static boolean hasAllApns(String[] strArr) {
        if (ArrayUtils.isEmpty(strArr)) {
            return false;
        }
        List asList = Arrays.asList(strArr);
        if (asList.contains("*")) {
            Log.d(TAG, "hasAllApns: true because apnList.contains(PhoneConstants.APN_TYPE_ALL)");
            return true;
        }
        for (String contains : PhoneConstants.APN_TYPES) {
            if (!asList.contains(contains)) {
                return false;
            }
        }
        Log.d(TAG, "hasAllApns: true");
        return true;
    }

    private boolean apnTypesMatch(String[] strArr, String str) {
        if (ArrayUtils.isEmpty(strArr)) {
            return false;
        }
        if (hasAllApns(strArr) || TextUtils.isEmpty(str)) {
            return true;
        }
        List asList = Arrays.asList(strArr);
        for (String str2 : str.split(",")) {
            if (asList.contains(str2.trim())) {
                Log.d(TAG, "apnTypesMatch: true because match found for " + str2.trim());
                return true;
            }
        }
        Log.d(TAG, "apnTypesMatch: false");
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.preference.Preference getPreferenceFromFieldName(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -2135515857: goto L_0x00e9;
                case -1954254981: goto L_0x00df;
                case -1640523526: goto L_0x00d4;
                case -1393032351: goto L_0x00c9;
                case -1230508389: goto L_0x00be;
                case -1039601666: goto L_0x00b3;
                case -989163880: goto L_0x00a8;
                case -905826493: goto L_0x009e;
                case -520149991: goto L_0x0093;
                case 96799: goto L_0x0088;
                case 107917: goto L_0x007c;
                case 108258: goto L_0x0070;
                case 3355632: goto L_0x0064;
                case 3373707: goto L_0x0059;
                case 3446913: goto L_0x004e;
                case 3575610: goto L_0x0042;
                case 3599307: goto L_0x0037;
                case 106941038: goto L_0x002c;
                case 1183882708: goto L_0x0020;
                case 1216985755: goto L_0x0015;
                case 1433229538: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x00f4
        L_0x0009:
            java.lang.String r0 = "authtype"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 13
            goto L_0x00f5
        L_0x0015:
            java.lang.String r0 = "password"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 6
            goto L_0x00f5
        L_0x0020:
            java.lang.String r0 = "mmsport"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 8
            goto L_0x00f5
        L_0x002c:
            java.lang.String r0 = "proxy"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 2
            goto L_0x00f5
        L_0x0037:
            java.lang.String r0 = "user"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 4
            goto L_0x00f5
        L_0x0042:
            java.lang.String r0 = "type"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 12
            goto L_0x00f5
        L_0x004e:
            java.lang.String r0 = "port"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 3
            goto L_0x00f5
        L_0x0059:
            java.lang.String r0 = "name"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 0
            goto L_0x00f5
        L_0x0064:
            java.lang.String r0 = "mmsc"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 9
            goto L_0x00f5
        L_0x0070:
            java.lang.String r0 = "mnc"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 11
            goto L_0x00f5
        L_0x007c:
            java.lang.String r0 = "mcc"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 10
            goto L_0x00f5
        L_0x0088:
            java.lang.String r0 = "apn"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 1
            goto L_0x00f5
        L_0x0093:
            java.lang.String r0 = "mvno_match_data"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 20
            goto L_0x00f5
        L_0x009e:
            java.lang.String r0 = "server"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 5
            goto L_0x00f5
        L_0x00a8:
            java.lang.String r0 = "protocol"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 14
            goto L_0x00f5
        L_0x00b3:
            java.lang.String r0 = "roaming_protocol"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 15
            goto L_0x00f5
        L_0x00be:
            java.lang.String r0 = "bearer_bitmask"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 18
            goto L_0x00f5
        L_0x00c9:
            java.lang.String r0 = "bearer"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 17
            goto L_0x00f5
        L_0x00d4:
            java.lang.String r0 = "carrier_enabled"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 16
            goto L_0x00f5
        L_0x00df:
            java.lang.String r0 = "mmsproxy"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 7
            goto L_0x00f5
        L_0x00e9:
            java.lang.String r0 = "mvno_type"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x00f4
            r2 = 19
            goto L_0x00f5
        L_0x00f4:
            r2 = -1
        L_0x00f5:
            switch(r2) {
                case 0: goto L_0x0133;
                case 1: goto L_0x0130;
                case 2: goto L_0x012d;
                case 3: goto L_0x012a;
                case 4: goto L_0x0127;
                case 5: goto L_0x0124;
                case 6: goto L_0x0121;
                case 7: goto L_0x011e;
                case 8: goto L_0x011b;
                case 9: goto L_0x0118;
                case 10: goto L_0x0115;
                case 11: goto L_0x0112;
                case 12: goto L_0x010f;
                case 13: goto L_0x010c;
                case 14: goto L_0x0109;
                case 15: goto L_0x0106;
                case 16: goto L_0x0103;
                case 17: goto L_0x0100;
                case 18: goto L_0x0100;
                case 19: goto L_0x00fd;
                case 20: goto L_0x00fa;
                default: goto L_0x00f8;
            }
        L_0x00f8:
            r1 = 0
            return r1
        L_0x00fa:
            androidx.preference.EditTextPreference r1 = r1.mMvnoMatchData
            return r1
        L_0x00fd:
            androidx.preference.ListPreference r1 = r1.mMvnoType
            return r1
        L_0x0100:
            androidx.preference.MultiSelectListPreference r1 = r1.mBearerMulti
            return r1
        L_0x0103:
            com.havoc.support.preferences.SwitchPreference r1 = r1.mCarrierEnabled
            return r1
        L_0x0106:
            androidx.preference.ListPreference r1 = r1.mRoamingProtocol
            return r1
        L_0x0109:
            androidx.preference.ListPreference r1 = r1.mProtocol
            return r1
        L_0x010c:
            androidx.preference.ListPreference r1 = r1.mAuthType
            return r1
        L_0x010f:
            androidx.preference.EditTextPreference r1 = r1.mApnType
            return r1
        L_0x0112:
            androidx.preference.EditTextPreference r1 = r1.mMnc
            return r1
        L_0x0115:
            androidx.preference.EditTextPreference r1 = r1.mMcc
            return r1
        L_0x0118:
            androidx.preference.EditTextPreference r1 = r1.mMmsc
            return r1
        L_0x011b:
            androidx.preference.EditTextPreference r1 = r1.mMmsPort
            return r1
        L_0x011e:
            androidx.preference.EditTextPreference r1 = r1.mMmsProxy
            return r1
        L_0x0121:
            androidx.preference.EditTextPreference r1 = r1.mPassword
            return r1
        L_0x0124:
            androidx.preference.EditTextPreference r1 = r1.mServer
            return r1
        L_0x0127:
            androidx.preference.EditTextPreference r1 = r1.mUser
            return r1
        L_0x012a:
            androidx.preference.EditTextPreference r1 = r1.mPort
            return r1
        L_0x012d:
            androidx.preference.EditTextPreference r1 = r1.mProxy
            return r1
        L_0x0130:
            androidx.preference.EditTextPreference r1 = r1.mApn
            return r1
        L_0x0133:
            androidx.preference.EditTextPreference r1 = r1.mName
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.ApnEditor.getPreferenceFromFieldName(java.lang.String):androidx.preference.Preference");
    }

    private void disableFields(String[] strArr) {
        for (String preferenceFromFieldName : strArr) {
            Preference preferenceFromFieldName2 = getPreferenceFromFieldName(preferenceFromFieldName);
            if (preferenceFromFieldName2 != null) {
                preferenceFromFieldName2.setEnabled(false);
            }
        }
    }

    private void disableAllFields() {
        this.mName.setEnabled(false);
        this.mApn.setEnabled(false);
        this.mProxy.setEnabled(false);
        this.mPort.setEnabled(false);
        this.mUser.setEnabled(false);
        this.mServer.setEnabled(false);
        this.mPassword.setEnabled(false);
        this.mMmsProxy.setEnabled(false);
        this.mMmsPort.setEnabled(false);
        this.mMmsc.setEnabled(false);
        this.mMcc.setEnabled(false);
        this.mMnc.setEnabled(false);
        this.mApnType.setEnabled(false);
        this.mAuthType.setEnabled(false);
        this.mProtocol.setEnabled(false);
        this.mRoamingProtocol.setEnabled(false);
        this.mCarrierEnabled.setEnabled(false);
        this.mBearerMulti.setEnabled(false);
        this.mMvnoType.setEnabled(false);
        this.mMvnoMatchData.setEnabled(false);
    }

    /* access modifiers changed from: package-private */
    public void fillUI(boolean z) {
        String str;
        String simOperator;
        if (z) {
            this.mName.setText(this.mApnData.getString(1));
            this.mApn.setText(this.mApnData.getString(2));
            this.mProxy.setText(this.mApnData.getString(3));
            this.mPort.setText(this.mApnData.getString(4));
            this.mUser.setText(this.mApnData.getString(5));
            this.mServer.setText(this.mApnData.getString(6));
            this.mPassword.setText(this.mApnData.getString(7));
            this.mMmsProxy.setText(this.mApnData.getString(12));
            this.mMmsPort.setText(this.mApnData.getString(13));
            this.mMmsc.setText(this.mApnData.getString(8));
            this.mMcc.setText(this.mApnData.getString(9));
            this.mMnc.setText(this.mApnData.getString(10));
            this.mApnType.setText(this.mApnData.getString(15));
            if (this.mNewApn && (simOperator = this.mTelephonyManager.getSimOperator(this.mSubId)) != null && simOperator.length() > 4) {
                String substring = simOperator.substring(0, 3);
                String substring2 = simOperator.substring(3);
                this.mMcc.setText(substring);
                this.mMnc.setText(substring2);
                this.mCurMnc = substring2;
                this.mCurMcc = substring;
            }
            int intValue = this.mApnData.getInteger(14, -1).intValue();
            if (intValue != -1) {
                this.mAuthType.setValueIndex(intValue);
            } else {
                this.mAuthType.setValue((String) null);
            }
            this.mProtocol.setValue(this.mApnData.getString(16));
            this.mRoamingProtocol.setValue(this.mApnData.getString(20));
            this.mCarrierEnabled.setChecked(this.mApnData.getInteger(17, 1).intValue() == 1);
            this.mBearerInitialVal = this.mApnData.getInteger(18, 0).intValue();
            HashSet hashSet = new HashSet();
            int intValue2 = this.mApnData.getInteger(19, 0).intValue();
            if (intValue2 != 0) {
                int i = 1;
                while (intValue2 != 0) {
                    if ((intValue2 & 1) == 1) {
                        hashSet.add("" + i);
                    }
                    intValue2 >>= 1;
                    i++;
                }
            } else if (this.mBearerInitialVal == 0) {
                hashSet.add("0");
            }
            if (this.mBearerInitialVal != 0) {
                if (!hashSet.contains("" + this.mBearerInitialVal)) {
                    hashSet.add("" + this.mBearerInitialVal);
                }
            }
            this.mBearerMulti.setValues(hashSet);
            this.mMvnoType.setValue(this.mApnData.getString(21));
            this.mMvnoMatchData.setEnabled(false);
            this.mMvnoMatchData.setText(this.mApnData.getString(22));
            if (!(!this.mNewApn || (str = this.mMvnoTypeStr) == null || this.mMvnoMatchDataStr == null)) {
                this.mMvnoType.setValue(str);
                this.mMvnoMatchData.setText(this.mMvnoMatchDataStr);
            }
        }
        EditTextPreference editTextPreference = this.mName;
        editTextPreference.setSummary((CharSequence) checkNull(editTextPreference.getText()));
        EditTextPreference editTextPreference2 = this.mApn;
        editTextPreference2.setSummary((CharSequence) checkNull(editTextPreference2.getText()));
        EditTextPreference editTextPreference3 = this.mProxy;
        editTextPreference3.setSummary((CharSequence) checkNull(editTextPreference3.getText()));
        EditTextPreference editTextPreference4 = this.mPort;
        editTextPreference4.setSummary((CharSequence) checkNull(editTextPreference4.getText()));
        EditTextPreference editTextPreference5 = this.mUser;
        editTextPreference5.setSummary((CharSequence) checkNull(editTextPreference5.getText()));
        EditTextPreference editTextPreference6 = this.mServer;
        editTextPreference6.setSummary((CharSequence) checkNull(editTextPreference6.getText()));
        EditTextPreference editTextPreference7 = this.mPassword;
        editTextPreference7.setSummary((CharSequence) starify(editTextPreference7.getText()));
        EditTextPreference editTextPreference8 = this.mMmsProxy;
        editTextPreference8.setSummary((CharSequence) checkNull(editTextPreference8.getText()));
        EditTextPreference editTextPreference9 = this.mMmsPort;
        editTextPreference9.setSummary((CharSequence) checkNull(editTextPreference9.getText()));
        EditTextPreference editTextPreference10 = this.mMmsc;
        editTextPreference10.setSummary((CharSequence) checkNull(editTextPreference10.getText()));
        EditTextPreference editTextPreference11 = this.mMcc;
        editTextPreference11.setSummary((CharSequence) formatInteger(checkNull(editTextPreference11.getText())));
        EditTextPreference editTextPreference12 = this.mMnc;
        editTextPreference12.setSummary((CharSequence) formatInteger(checkNull(editTextPreference12.getText())));
        EditTextPreference editTextPreference13 = this.mApnType;
        editTextPreference13.setSummary((CharSequence) checkNull(editTextPreference13.getText()));
        String value = this.mAuthType.getValue();
        if (value != null) {
            int parseInt = Integer.parseInt(value);
            this.mAuthType.setValueIndex(parseInt);
            this.mAuthType.setSummary(getResources().getStringArray(C1715R.array.apn_auth_entries)[parseInt]);
        } else {
            this.mAuthType.setSummary(sNotSet);
        }
        ListPreference listPreference = this.mProtocol;
        listPreference.setSummary(checkNull(protocolDescription(listPreference.getValue(), this.mProtocol)));
        ListPreference listPreference2 = this.mRoamingProtocol;
        listPreference2.setSummary(checkNull(protocolDescription(listPreference2.getValue(), this.mRoamingProtocol)));
        MultiSelectListPreference multiSelectListPreference = this.mBearerMulti;
        multiSelectListPreference.setSummary((CharSequence) checkNull(bearerMultiDescription(multiSelectListPreference.getValues())));
        ListPreference listPreference3 = this.mMvnoType;
        listPreference3.setSummary(checkNull(mvnoDescription(listPreference3.getValue())));
        EditTextPreference editTextPreference14 = this.mMvnoMatchData;
        editTextPreference14.setSummary((CharSequence) checkNull(editTextPreference14.getText()));
        if (getResources().getBoolean(C1715R.bool.config_allow_edit_carrier_enabled)) {
            this.mCarrierEnabled.setEnabled(true);
        } else {
            this.mCarrierEnabled.setEnabled(false);
        }
    }

    private String protocolDescription(String str, ListPreference listPreference) {
        int findIndexOfValue = listPreference.findIndexOfValue(str);
        if (findIndexOfValue == -1) {
            return null;
        }
        try {
            return getResources().getStringArray(C1715R.array.apn_protocol_entries)[findIndexOfValue];
        } catch (ArrayIndexOutOfBoundsException unused) {
            return null;
        }
    }

    private String bearerMultiDescription(Set<String> set) {
        String[] stringArray = getResources().getStringArray(C1715R.array.bearer_entries);
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String findIndexOfValue : set) {
            int findIndexOfValue2 = this.mBearerMulti.findIndexOfValue(findIndexOfValue);
            if (z) {
                try {
                    sb.append(stringArray[findIndexOfValue2]);
                    z = false;
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            } else {
                sb.append(", " + stringArray[findIndexOfValue2]);
            }
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2)) {
            return sb2;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0022, code lost:
        r4 = r8.mReadOnlyApnFields;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String mvnoDescription(java.lang.String r9) {
        /*
            r8 = this;
            androidx.preference.ListPreference r0 = r8.mMvnoType
            int r0 = r0.findIndexOfValue(r9)
            androidx.preference.ListPreference r1 = r8.mMvnoType
            java.lang.String r1 = r1.getValue()
            r2 = 0
            r3 = -1
            if (r0 != r3) goto L_0x0011
            return r2
        L_0x0011:
            android.content.res.Resources r3 = r8.getResources()
            r4 = 2130903217(0x7f0300b1, float:1.7413246E38)
            java.lang.String[] r3 = r3.getStringArray(r4)
            boolean r4 = r8.mReadOnlyApn
            r5 = 0
            r6 = 1
            if (r4 != 0) goto L_0x0035
            java.lang.String[] r4 = r8.mReadOnlyApnFields
            if (r4 == 0) goto L_0x0033
            java.util.List r4 = java.util.Arrays.asList(r4)
            java.lang.String r7 = "mvno_match_data"
            boolean r4 = r4.contains(r7)
            if (r4 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r4 = r5
            goto L_0x0036
        L_0x0035:
            r4 = r6
        L_0x0036:
            androidx.preference.EditTextPreference r7 = r8.mMvnoMatchData
            if (r4 != 0) goto L_0x003d
            if (r0 == 0) goto L_0x003d
            r5 = r6
        L_0x003d:
            r7.setEnabled(r5)
            if (r9 == 0) goto L_0x009c
            boolean r9 = r9.equals(r1)
            if (r9 != 0) goto L_0x009c
            r9 = r3[r0]
            java.lang.String r1 = "SPN"
            boolean r9 = r9.equals(r1)
            if (r9 == 0) goto L_0x005e
            androidx.preference.EditTextPreference r9 = r8.mMvnoMatchData
            android.telephony.TelephonyManager r8 = r8.mTelephonyManager
            java.lang.String r8 = r8.getSimOperatorName()
            r9.setText(r8)
            goto L_0x009c
        L_0x005e:
            r9 = r3[r0]
            java.lang.String r1 = "IMSI"
            boolean r9 = r9.equals(r1)
            if (r9 == 0) goto L_0x0087
            android.telephony.TelephonyManager r9 = r8.mTelephonyManager
            int r1 = r8.mSubId
            java.lang.String r9 = r9.getSimOperator(r1)
            androidx.preference.EditTextPreference r8 = r8.mMvnoMatchData
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            java.lang.String r9 = "x"
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r8.setText(r9)
            goto L_0x009c
        L_0x0087:
            r9 = r3[r0]
            java.lang.String r1 = "GID"
            boolean r9 = r9.equals(r1)
            if (r9 == 0) goto L_0x009c
            androidx.preference.EditTextPreference r9 = r8.mMvnoMatchData
            android.telephony.TelephonyManager r8 = r8.mTelephonyManager
            java.lang.String r8 = r8.getGroupIdLevel1()
            r9.setText(r8)
        L_0x009c:
            r8 = r3[r0]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x009f }
            return r8
        L_0x009f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.ApnEditor.mvnoDescription(java.lang.String):java.lang.String");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String key = preference.getKey();
        if ("auth_type".equals(key)) {
            try {
                int parseInt = Integer.parseInt((String) obj);
                this.mAuthType.setValueIndex(parseInt);
                this.mAuthType.setSummary(getResources().getStringArray(C1715R.array.apn_auth_entries)[parseInt]);
                return true;
            } catch (NumberFormatException unused) {
                return false;
            }
        } else if ("apn_protocol".equals(key)) {
            String str = (String) obj;
            String protocolDescription = protocolDescription(str, this.mProtocol);
            if (protocolDescription == null) {
                return false;
            }
            this.mProtocol.setSummary(protocolDescription);
            this.mProtocol.setValue(str);
            return true;
        } else if ("apn_roaming_protocol".equals(key)) {
            String str2 = (String) obj;
            String protocolDescription2 = protocolDescription(str2, this.mRoamingProtocol);
            if (protocolDescription2 == null) {
                return false;
            }
            this.mRoamingProtocol.setSummary(protocolDescription2);
            this.mRoamingProtocol.setValue(str2);
            return true;
        } else if ("bearer_multi".equals(key)) {
            Set set = (Set) obj;
            String bearerMultiDescription = bearerMultiDescription(set);
            if (bearerMultiDescription == null) {
                return false;
            }
            this.mBearerMulti.setValues(set);
            this.mBearerMulti.setSummary((CharSequence) bearerMultiDescription);
            return true;
        } else if ("mvno_type".equals(key)) {
            String str3 = (String) obj;
            String mvnoDescription = mvnoDescription(str3);
            if (mvnoDescription == null) {
                return false;
            }
            this.mMvnoType.setValue(str3);
            this.mMvnoType.setSummary(mvnoDescription);
            return true;
        } else if ("apn_password".equals(key)) {
            this.mPassword.setSummary((CharSequence) starify(obj != null ? String.valueOf(obj) : ""));
            return true;
        } else if ("carrier_enabled".equals(key)) {
            return true;
        } else {
            preference.setSummary((CharSequence) checkNull(obj != null ? String.valueOf(obj) : null));
            return true;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (!this.mNewApn && !this.mReadOnlyApn) {
            menu.add(0, 1, 0, C1715R.string.menu_delete).setIcon(C1715R.C1717drawable.ic_delete);
        }
        menu.add(0, 2, 0, C1715R.string.menu_save).setIcon(17301582);
        menu.add(0, 3, 0, C1715R.string.menu_cancel).setIcon(17301560);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            deleteApn();
            finish();
            return true;
        } else if (itemId == 2) {
            if (validateAndSaveApnData()) {
                finish();
            }
            return true;
        } else if (itemId != 3) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            finish();
            return true;
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return false;
        }
        if (!validateAndSaveApnData()) {
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setStringValueAndCheckIfDiff(ContentValues contentValues, String str, String str2, boolean z, int i) {
        String string = this.mApnData.getString(i);
        boolean z2 = z || ((!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(string)) && (str2 == null || !str2.equals(string)));
        if (z2 && str2 != null) {
            contentValues.put(str, str2);
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    public boolean setIntValueAndCheckIfDiff(ContentValues contentValues, String str, int i, boolean z, int i2) {
        boolean z2 = z || i != this.mApnData.getInteger(i2).intValue();
        if (z2) {
            contentValues.put(str, Integer.valueOf(i));
        }
        return z2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x01af, code lost:
        r0 = r13.mBearerInitialVal;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean validateAndSaveApnData() {
        /*
            r13 = this;
            boolean r0 = r13.mReadOnlyApn
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            if (r0 == 0) goto L_0x000a
            return r6
        L_0x000a:
            androidx.preference.EditTextPreference r0 = r13.mName
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            androidx.preference.EditTextPreference r0 = r13.mApn
            java.lang.String r0 = r0.getText()
            java.lang.String r8 = r13.checkNotSet(r0)
            androidx.preference.EditTextPreference r0 = r13.mMcc
            java.lang.String r0 = r0.getText()
            java.lang.String r9 = r13.checkNotSet(r0)
            androidx.preference.EditTextPreference r0 = r13.mMnc
            java.lang.String r0 = r0.getText()
            java.lang.String r10 = r13.checkNotSet(r0)
            java.lang.String r0 = r13.validateApnData()
            r11 = 0
            if (r0 == 0) goto L_0x003d
            r13.showError()
            return r11
        L_0x003d:
            android.content.ContentValues r12 = new android.content.ContentValues
            r12.<init>()
            boolean r4 = r13.mNewApn
            r5 = 1
            java.lang.String r2 = "name"
            r0 = r13
            r1 = r12
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            r5 = 2
            java.lang.String r2 = "apn"
            r3 = r8
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mProxy
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 3
            java.lang.String r2 = "proxy"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mPort
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 4
            java.lang.String r2 = "port"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mMmsProxy
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 12
            java.lang.String r2 = "mmsproxy"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mMmsPort
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 13
            java.lang.String r2 = "mmsport"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mUser
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 5
            java.lang.String r2 = "user"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mServer
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 6
            java.lang.String r2 = "server"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mPassword
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 7
            java.lang.String r2 = "password"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mMmsc
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 8
            java.lang.String r2 = "mmsc"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.ListPreference r0 = r13.mAuthType
            java.lang.String r0 = r0.getValue()
            if (r0 == 0) goto L_0x00ff
            int r3 = java.lang.Integer.parseInt(r0)
            r5 = 14
            java.lang.String r2 = "authtype"
            r0 = r13
            r1 = r12
            boolean r0 = r0.setIntValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            r4 = r0
        L_0x00ff:
            androidx.preference.ListPreference r0 = r13.mProtocol
            java.lang.String r0 = r0.getValue()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 16
            java.lang.String r2 = "protocol"
            r0 = r13
            r1 = r12
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.ListPreference r0 = r13.mRoamingProtocol
            java.lang.String r0 = r0.getValue()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 20
            java.lang.String r2 = "roaming_protocol"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            java.lang.String r0 = r13.getUserEnteredApnType()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 15
            java.lang.String r2 = "type"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            r5 = 9
            java.lang.String r2 = "mcc"
            r3 = r9
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            r5 = 10
            java.lang.String r2 = "mnc"
            r3 = r10
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r9)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "numeric"
            r12.put(r1, r0)
            java.lang.String r0 = r13.mCurMnc
            if (r0 == 0) goto L_0x0178
            java.lang.String r1 = r13.mCurMcc
            if (r1 == 0) goto L_0x0178
            boolean r0 = r0.equals(r10)
            if (r0 == 0) goto L_0x0178
            java.lang.String r0 = r13.mCurMcc
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x0178
            java.lang.String r0 = "current"
            r12.put(r0, r7)
        L_0x0178:
            androidx.preference.MultiSelectListPreference r0 = r13.mBearerMulti
            java.util.Set r0 = r0.getValues()
            java.util.Iterator r0 = r0.iterator()
            r1 = r11
        L_0x0183:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x01a1
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            int r3 = java.lang.Integer.parseInt(r2)
            if (r3 != 0) goto L_0x0197
            r8 = r11
            goto L_0x01a2
        L_0x0197:
            int r2 = java.lang.Integer.parseInt(r2)
            int r2 = android.telephony.ServiceState.getBitmaskForTech(r2)
            r1 = r1 | r2
            goto L_0x0183
        L_0x01a1:
            r8 = r1
        L_0x01a2:
            r5 = 19
            java.lang.String r2 = "bearer_bitmask"
            r0 = r13
            r1 = r12
            r3 = r8
            boolean r4 = r0.setIntValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            if (r8 == 0) goto L_0x01be
            int r0 = r13.mBearerInitialVal
            if (r0 != 0) goto L_0x01b4
            goto L_0x01be
        L_0x01b4:
            boolean r0 = android.telephony.ServiceState.bitmaskHasTech(r8, r0)
            if (r0 == 0) goto L_0x01be
            int r0 = r13.mBearerInitialVal
            r3 = r0
            goto L_0x01bf
        L_0x01be:
            r3 = r11
        L_0x01bf:
            r5 = 18
            java.lang.String r2 = "bearer"
            r0 = r13
            r1 = r12
            boolean r4 = r0.setIntValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.ListPreference r0 = r13.mMvnoType
            java.lang.String r0 = r0.getValue()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 21
            java.lang.String r2 = "mvno_type"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            androidx.preference.EditTextPreference r0 = r13.mMvnoMatchData
            java.lang.String r0 = r0.getText()
            java.lang.String r3 = r13.checkNotSet(r0)
            r5 = 22
            java.lang.String r2 = "mvno_match_data"
            r0 = r13
            boolean r4 = r0.setStringValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            com.havoc.support.preferences.SwitchPreference r0 = r13.mCarrierEnabled
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x01f9
            r3 = r6
            goto L_0x01fa
        L_0x01f9:
            r3 = r11
        L_0x01fa:
            r5 = 17
            java.lang.String r2 = "carrier_enabled"
            r0 = r13
            r1 = r12
            boolean r0 = r0.setIntValueAndCheckIfDiff(r1, r2, r3, r4, r5)
            java.lang.String r1 = "edited"
            r12.put(r1, r7)
            if (r0 == 0) goto L_0x021f
            com.android.settings.network.ApnEditor$ApnData r0 = r13.mApnData
            android.net.Uri r0 = r0.getUri()
            if (r0 != 0) goto L_0x0216
            android.net.Uri r0 = r13.mCarrierUri
            goto L_0x021c
        L_0x0216:
            com.android.settings.network.ApnEditor$ApnData r0 = r13.mApnData
            android.net.Uri r0 = r0.getUri()
        L_0x021c:
            r13.updateApnDataToDatabase(r0, r12)
        L_0x021f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.ApnEditor.validateAndSaveApnData():boolean");
    }

    private void updateApnDataToDatabase(Uri uri, ContentValues contentValues) {
        ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(uri, contentValues) {
            private final /* synthetic */ Uri f$1;
            private final /* synthetic */ ContentValues f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ApnEditor.this.lambda$updateApnDataToDatabase$0$ApnEditor(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$updateApnDataToDatabase$0$ApnEditor(Uri uri, ContentValues contentValues) {
        if (!uri.equals(this.mCarrierUri)) {
            getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
        } else if (getContentResolver().insert(this.mCarrierUri, contentValues) == null) {
            String str = TAG;
            Log.e(str, "Can't add a new apn to database " + this.mCarrierUri);
        }
    }

    /* access modifiers changed from: package-private */
    public String validateApnData() {
        String str;
        String checkNotSet = checkNotSet(this.mName.getText());
        String checkNotSet2 = checkNotSet(this.mApn.getText());
        String checkNotSet3 = checkNotSet(this.mMcc.getText());
        String checkNotSet4 = checkNotSet(this.mMnc.getText());
        if (TextUtils.isEmpty(checkNotSet)) {
            str = getResources().getString(C1715R.string.error_name_empty);
        } else if (TextUtils.isEmpty(checkNotSet2)) {
            str = getResources().getString(C1715R.string.error_apn_empty);
        } else if (checkNotSet3 == null || checkNotSet3.length() != 3) {
            str = getResources().getString(C1715R.string.error_mcc_not3);
        } else if (checkNotSet4 == null || (checkNotSet4.length() & 65534) != 2) {
            str = getResources().getString(C1715R.string.error_mnc_not23);
        } else {
            str = null;
        }
        if (str != null || ArrayUtils.isEmpty(this.mReadOnlyApnTypes) || !apnTypesMatch(this.mReadOnlyApnTypes, getUserEnteredApnType())) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : this.mReadOnlyApnTypes) {
            sb.append(str2);
            sb.append(", ");
            Log.d(TAG, "validateApnData: appending type: " + str2);
        }
        if (sb.length() >= 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return String.format(getResources().getString(C1715R.string.error_adding_apn_type), new Object[]{sb});
    }

    /* access modifiers changed from: package-private */
    public void showError() {
        ErrorDialog.showError(this);
    }

    private void deleteApn() {
        if (this.mApnData.getUri() != null) {
            getContentResolver().delete(this.mApnData.getUri(), (String) null, (String[]) null);
            this.mApnData = new ApnData(sProjection.length);
        }
    }

    private String starify(String str) {
        if (str == null || str.length() == 0) {
            return sNotSet;
        }
        char[] cArr = new char[str.length()];
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = '*';
        }
        return new String(cArr);
    }

    private String checkNull(String str) {
        return TextUtils.isEmpty(str) ? sNotSet : str;
    }

    private String checkNotSet(String str) {
        if (sNotSet.equals(str)) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public String getUserEnteredApnType() {
        String text = this.mApnType.getText();
        if (text != null) {
            text = text.trim();
        }
        if ((!TextUtils.isEmpty(text) && !"*".equals(text)) || ArrayUtils.isEmpty(this.mReadOnlyApnTypes)) {
            return text;
        }
        String[] strArr = PhoneConstants.APN_TYPES;
        if (TextUtils.isEmpty(text) && !ArrayUtils.isEmpty(this.mDefaultApnTypes)) {
            strArr = this.mDefaultApnTypes;
        }
        StringBuilder sb = new StringBuilder();
        List asList = Arrays.asList(this.mReadOnlyApnTypes);
        boolean z = true;
        for (String str : strArr) {
            if (!asList.contains(str) && !str.equals("ia") && !str.equals("emergency") && !str.equals("mcx")) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        String sb2 = sb.toString();
        Log.d(TAG, "getUserEnteredApnType: changed apn type to editable apn types: " + sb2);
        return sb2;
    }

    public static class ErrorDialog extends InstrumentedDialogFragment {
        public int getMetricsCategory() {
            return 530;
        }

        public static void showError(ApnEditor apnEditor) {
            ErrorDialog errorDialog = new ErrorDialog();
            errorDialog.setTargetFragment(apnEditor, 0);
            errorDialog.show(apnEditor.getFragmentManager(), "error");
        }

        public Dialog onCreateDialog(Bundle bundle) {
            String validateApnData = ((ApnEditor) getTargetFragment()).validateApnData();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle((int) C1715R.string.error_title);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            builder.setMessage((CharSequence) validateApnData);
            return builder.create();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        r7.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r6 != null) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.settings.network.ApnEditor.ApnData getApnDataFromUri(android.net.Uri r7) {
        /*
            r6 = this;
            android.content.ContentResolver r0 = r6.getContentResolver()
            java.lang.String[] r2 = sProjection
            r3 = 0
            r4 = 0
            r5 = 0
            r1 = r7
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            if (r6 == 0) goto L_0x0027
            r6.moveToFirst()     // Catch:{ all -> 0x0019 }
            com.android.settings.network.ApnEditor$ApnData r0 = new com.android.settings.network.ApnEditor$ApnData     // Catch:{ all -> 0x0019 }
            r0.<init>(r7, r6)     // Catch:{ all -> 0x0019 }
            goto L_0x0028
        L_0x0019:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x001b }
        L_0x001b:
            r0 = move-exception
            if (r6 == 0) goto L_0x0026
            r6.close()     // Catch:{ all -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r6 = move-exception
            r7.addSuppressed(r6)
        L_0x0026:
            throw r0
        L_0x0027:
            r0 = 0
        L_0x0028:
            if (r6 == 0) goto L_0x002d
            r6.close()
        L_0x002d:
            if (r0 != 0) goto L_0x0045
            java.lang.String r6 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can't get apnData from Uri "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.util.Log.d(r6, r7)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.network.ApnEditor.getApnDataFromUri(android.net.Uri):com.android.settings.network.ApnEditor$ApnData");
    }

    static class ApnData {
        Object[] mData;
        Uri mUri;

        ApnData(int i) {
            this.mData = new Object[i];
        }

        ApnData(Uri uri, Cursor cursor) {
            this.mUri = uri;
            this.mData = new Object[cursor.getColumnCount()];
            for (int i = 0; i < this.mData.length; i++) {
                int type = cursor.getType(i);
                if (type == 1) {
                    this.mData[i] = Integer.valueOf(cursor.getInt(i));
                } else if (type == 2) {
                    this.mData[i] = Float.valueOf(cursor.getFloat(i));
                } else if (type == 3) {
                    this.mData[i] = cursor.getString(i);
                } else if (type != 4) {
                    this.mData[i] = null;
                } else {
                    this.mData[i] = cursor.getBlob(i);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Uri getUri() {
            return this.mUri;
        }

        /* access modifiers changed from: package-private */
        public Integer getInteger(int i) {
            return (Integer) this.mData[i];
        }

        /* access modifiers changed from: package-private */
        public Integer getInteger(int i, Integer num) {
            Integer integer = getInteger(i);
            return integer == null ? num : integer;
        }

        /* access modifiers changed from: package-private */
        public String getString(int i) {
            return (String) this.mData[i];
        }
    }
}
