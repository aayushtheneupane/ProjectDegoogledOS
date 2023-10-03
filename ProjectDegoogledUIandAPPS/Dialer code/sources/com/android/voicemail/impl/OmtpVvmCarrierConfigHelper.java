package com.android.voicemail.impl;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.telephony.VisualVoicemailSmsFilterSettings;
import android.util.ArraySet;
import com.android.dialer.common.Assert;
import com.android.voicemail.impl.protocol.VisualVoicemailProtocol;
import com.android.voicemail.impl.sms.StatusMessage;
import com.android.voicemail.impl.sync.VvmAccountManager;
import java.util.Collections;
import java.util.Set;

@TargetApi(26)
public class OmtpVvmCarrierConfigHelper {
    private static PersistableBundle overrideConfigForTest;
    private final PersistableBundle carrierConfig;
    private final Context context;
    private final PersistableBundle overrideConfig;
    private PhoneAccountHandle phoneAccountHandle;
    private final VisualVoicemailProtocol protocol;
    private final PersistableBundle telephonyConfig;
    private final String vvmType;

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0147, code lost:
        if (android.text.TextUtils.isEmpty(r0.getString("vvm_type_string")) != false) goto L_0x0149;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public OmtpVvmCarrierConfigHelper(android.content.Context r10, android.telecom.PhoneAccountHandle r11) {
        /*
            r9 = this;
            r9.<init>()
            r9.context = r10
            r9.phoneAccountHandle = r11
            android.os.PersistableBundle r0 = overrideConfigForTest
            if (r0 == 0) goto L_0x001d
            r9.overrideConfig = r0
            android.os.PersistableBundle r10 = new android.os.PersistableBundle
            r10.<init>()
            r9.carrierConfig = r10
            android.os.PersistableBundle r10 = new android.os.PersistableBundle
            r10.<init>()
            r9.telephonyConfig = r10
            goto L_0x015d
        L_0x001d:
            if (r11 != 0) goto L_0x0024
            java.util.Optional r11 = java.util.Optional.empty()
            goto L_0x005a
        L_0x0024:
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r10.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            android.telephony.TelephonyManager r11 = r0.createForPhoneAccountHandle(r11)
            if (r11 != 0) goto L_0x0037
            java.util.Optional r11 = java.util.Optional.empty()
            goto L_0x005a
        L_0x0037:
            java.lang.String r0 = r11.getGroupIdLevel1()
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0040
            r0 = r1
        L_0x0040:
            com.android.voicemail.impl.AutoValue_CarrierIdentifier$Builder r2 = new com.android.voicemail.impl.AutoValue_CarrierIdentifier$Builder
            r2.<init>()
            r2.setGid1(r1)
            java.lang.String r11 = r11.getSimOperator()
            r2.setMccMnc(r11)
            r2.setGid1(r0)
            com.android.voicemail.impl.CarrierIdentifier r11 = r2.build()
            java.util.Optional r11 = java.util.Optional.of(r11)
        L_0x005a:
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r10.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            android.telecom.PhoneAccountHandle r1 = r9.phoneAccountHandle
            android.telephony.TelephonyManager r0 = r0.createForPhoneAccountHandle(r1)
            java.lang.String r1 = "OmtpVvmCarrierCfgHlpr"
            r2 = 0
            if (r0 == 0) goto L_0x0171
            boolean r3 = r11.isPresent()
            if (r3 != 0) goto L_0x0075
            goto L_0x0171
        L_0x0075:
            boolean r3 = com.android.voicemail.impl.configui.ConfigOverrideFragment.isOverridden(r10)
            if (r3 == 0) goto L_0x0122
            boolean r3 = com.android.voicemail.impl.configui.ConfigOverrideFragment.isOverridden(r10)
            com.android.dialer.common.Assert.checkState(r3)
            android.os.PersistableBundle r3 = new android.os.PersistableBundle
            r3.<init>()
            android.content.SharedPreferences r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r10)
            java.util.Map r5 = r4.getAll()
            java.util.Set r5 = r5.keySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0097:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x010d
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "vvm_config_override_key_"
            boolean r7 = r6.startsWith(r7)
            if (r7 != 0) goto L_0x00ac
            goto L_0x0097
        L_0x00ac:
            r7 = 24
            java.lang.String r7 = r6.substring(r7)
            java.lang.String r8 = "bool"
            boolean r8 = r7.endsWith(r8)
            if (r8 == 0) goto L_0x00c3
            r8 = 0
            boolean r6 = r4.getBoolean(r6, r8)
            r3.putBoolean(r7, r6)
            goto L_0x0097
        L_0x00c3:
            java.lang.String r8 = "int"
            boolean r8 = r7.endsWith(r8)
            if (r8 == 0) goto L_0x00db
            java.lang.String r6 = r4.getString(r6, r2)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r6 = r6.intValue()
            r3.putInt(r7, r6)
            goto L_0x0097
        L_0x00db:
            java.lang.String r8 = "string"
            boolean r8 = r7.endsWith(r8)
            if (r8 == 0) goto L_0x00eb
            java.lang.String r6 = r4.getString(r6, r2)
            r3.putString(r7, r6)
            goto L_0x0097
        L_0x00eb:
            java.lang.String r8 = "string_array"
            boolean r8 = r7.endsWith(r8)
            if (r8 == 0) goto L_0x0101
            java.lang.String r6 = r4.getString(r6, r2)
            java.lang.String r8 = ","
            java.lang.String[] r6 = r6.split(r8)
            r3.putStringArray(r7, r6)
            goto L_0x0097
        L_0x0101:
            java.lang.String r9 = "unknown type for key "
            java.lang.String r9 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r9, r7)
            java.lang.AssertionError r10 = new java.lang.AssertionError
            r10.<init>(r9)
            throw r10
        L_0x010d:
            r9.overrideConfig = r3
            java.lang.String r3 = "Config override is activated: "
            java.lang.StringBuilder r3 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r3)
            android.os.PersistableBundle r4 = r9.overrideConfig
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.android.voicemail.impl.VvmLog.m46w(r1, r3)
            goto L_0x0124
        L_0x0122:
            r9.overrideConfig = r2
        L_0x0124:
            android.content.Context r3 = r9.context
            java.lang.String r4 = "carrier_config"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.telephony.CarrierConfigManager r3 = (android.telephony.CarrierConfigManager) r3
            if (r3 != 0) goto L_0x0136
            java.lang.String r0 = "No carrier config service found."
            com.android.voicemail.impl.VvmLog.m46w(r1, r0)
            goto L_0x0149
        L_0x0136:
            android.os.PersistableBundle r0 = r0.getCarrierConfig()
            if (r0 != 0) goto L_0x013d
            goto L_0x0149
        L_0x013d:
            java.lang.String r1 = "vvm_type_string"
            java.lang.String r1 = r0.getString(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x014a
        L_0x0149:
            r0 = r2
        L_0x014a:
            r9.carrierConfig = r0
            com.android.voicemail.impl.DialerVvmConfigManager r0 = new com.android.voicemail.impl.DialerVvmConfigManager
            r0.<init>(r10)
            java.lang.Object r10 = r11.get()
            com.android.voicemail.impl.CarrierIdentifier r10 = (com.android.voicemail.impl.CarrierIdentifier) r10
            android.os.PersistableBundle r10 = r0.getConfig(r10)
            r9.telephonyConfig = r10
        L_0x015d:
            java.lang.String r10 = r9.getVvmType()
            r9.vvmType = r10
            android.content.Context r10 = r9.context
            r10.getResources()
            java.lang.String r10 = r9.vvmType
            com.android.voicemail.impl.protocol.VisualVoicemailProtocol r10 = android.support.p002v7.appcompat.R$style.create(r10)
            r9.protocol = r10
            return
        L_0x0171:
            java.lang.String r10 = "PhoneAccountHandle is invalid"
            com.android.voicemail.impl.VvmLog.m43e(r1, r10)
            r9.carrierConfig = r2
            r9.telephonyConfig = r2
            r9.overrideConfig = r2
            r9.vvmType = r2
            r9.protocol = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.OmtpVvmCarrierConfigHelper.<init>(android.content.Context, android.telecom.PhoneAccountHandle):void");
    }

    private static Set<String> getCarrierVvmPackageNames(PersistableBundle persistableBundle) {
        String[] stringArray;
        if (persistableBundle == null) {
            return null;
        }
        ArraySet arraySet = new ArraySet();
        if (persistableBundle.containsKey("carrier_vvm_package_name_string")) {
            arraySet.add(persistableBundle.getString("carrier_vvm_package_name_string"));
        }
        if (persistableBundle.containsKey("carrier_vvm_package_name_string_array") && (stringArray = persistableBundle.getStringArray("carrier_vvm_package_name_string_array")) != null && stringArray.length > 0) {
            Collections.addAll(arraySet, stringArray);
        }
        if (arraySet.isEmpty()) {
            return null;
        }
        return arraySet;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r1 = r1.get(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object getValue(java.lang.String r2, java.lang.Object r3) {
        /*
            r1 = this;
            android.os.PersistableBundle r0 = r1.overrideConfig
            if (r0 == 0) goto L_0x000b
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            android.os.PersistableBundle r0 = r1.carrierConfig
            if (r0 == 0) goto L_0x0016
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L_0x0016
            return r0
        L_0x0016:
            android.os.PersistableBundle r1 = r1.telephonyConfig
            if (r1 == 0) goto L_0x0021
            java.lang.Object r1 = r1.get(r2)
            if (r1 == 0) goto L_0x0021
            return r1
        L_0x0021:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.OmtpVvmCarrierConfigHelper.getValue(java.lang.String, java.lang.Object):java.lang.Object");
    }

    public static void setOverrideConfigForTest(PersistableBundle persistableBundle) {
        overrideConfigForTest = persistableBundle;
    }

    public void activateSmsFilter() {
        Assert.checkArgument(isValid());
        ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(getPhoneAccountHandle()).setVisualVoicemailSmsFilterSettings(new VisualVoicemailSmsFilterSettings.Builder().setClientPrefix(getClientPrefix()).build());
    }

    public int getApplicationPort() {
        Assert.checkArgument(isValid());
        return ((Integer) getValue("vvm_port_number_int", 0)).intValue();
    }

    public String getClientPrefix() {
        Assert.checkArgument(isValid());
        String str = (String) getValue("vvm_client_prefix_string", (Object) null);
        return str != null ? str : "//VVM";
    }

    public PersistableBundle getConfig() {
        PersistableBundle persistableBundle = new PersistableBundle();
        PersistableBundle persistableBundle2 = this.telephonyConfig;
        if (persistableBundle2 != null) {
            persistableBundle.putAll(persistableBundle2);
        }
        PersistableBundle persistableBundle3 = this.carrierConfig;
        if (persistableBundle3 != null) {
            persistableBundle.putAll(persistableBundle3);
        }
        return persistableBundle;
    }

    public Context getContext() {
        return this.context;
    }

    public String getDestinationNumber() {
        Assert.checkArgument(isValid());
        return (String) getValue("vvm_destination_number_string", (Object) null);
    }

    public Set<String> getDisabledCapabilities() {
        Assert.checkArgument(isValid());
        Set<String> disabledCapabilities = getDisabledCapabilities(this.overrideConfig);
        if (disabledCapabilities != null) {
            return disabledCapabilities;
        }
        Set<String> disabledCapabilities2 = getDisabledCapabilities(this.carrierConfig);
        if (disabledCapabilities2 != null) {
            return disabledCapabilities2;
        }
        return getDisabledCapabilities(this.telephonyConfig);
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    public VisualVoicemailProtocol getProtocol() {
        return this.protocol;
    }

    public int getSslPort() {
        Assert.checkArgument(isValid());
        return ((Integer) getValue("vvm_ssl_port_number_int", 0)).intValue();
    }

    public String getString(String str) {
        Assert.checkArgument(isValid());
        return (String) getValue(str, (Object) null);
    }

    public String getVvmType() {
        return (String) getValue("vvm_type_string", (Object) null);
    }

    public void handleEvent(VoicemailStatus$Editor voicemailStatus$Editor, OmtpEvents omtpEvents) {
        Assert.checkArgument(isValid());
        VvmLog.m45i("OmtpVvmCarrierCfgHlpr", "OmtpEvent:" + omtpEvents);
        this.protocol.handleEvent(this.context, this, voicemailStatus$Editor, omtpEvents);
    }

    public boolean ignoreTranscription() {
        Assert.checkArgument(isValid());
        return ((Boolean) getValue("vvm_ignore_transcription", false)).booleanValue();
    }

    public boolean isCarrierAppInstalled() {
        Set<String> carrierVvmPackageNames = getCarrierVvmPackageNames(this.overrideConfig);
        if (carrierVvmPackageNames == null && (carrierVvmPackageNames = getCarrierVvmPackageNames(this.carrierConfig)) == null) {
            carrierVvmPackageNames = getCarrierVvmPackageNames(this.telephonyConfig);
        }
        if (carrierVvmPackageNames == null) {
            return false;
        }
        for (String applicationInfo : carrierVvmPackageNames) {
            try {
                if (getContext().getPackageManager().getApplicationInfo(applicationInfo, 0).enabled) {
                    return true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public boolean isCellularDataRequired() {
        Assert.checkArgument(isValid());
        return ((Boolean) getValue("vvm_cellular_data_required_bool", false)).booleanValue();
    }

    public boolean isEnabledByDefault() {
        if (!isValid()) {
            return false;
        }
        return !isCarrierAppInstalled();
    }

    public boolean isLegacyModeEnabled() {
        Assert.checkArgument(isValid());
        return ((Boolean) getValue("vvm_legacy_mode_enabled_bool", false)).booleanValue();
    }

    public boolean isPrefetchEnabled() {
        Assert.checkArgument(isValid());
        return ((Boolean) getValue("vvm_prefetch_bool", true)).booleanValue();
    }

    public boolean isValid() {
        return this.protocol != null;
    }

    public void requestStatus(PendingIntent pendingIntent) {
        Assert.checkArgument(isValid());
        this.protocol.requestStatus(this, pendingIntent);
    }

    public void startActivation() {
        PhoneAccountHandle phoneAccountHandle2 = getPhoneAccountHandle();
        if (phoneAccountHandle2 != null) {
            if (!isValid()) {
                VvmLog.m43e("OmtpVvmCarrierCfgHlpr", "startActivation : invalid config for account " + phoneAccountHandle2);
                return;
            }
            ActivationTask.start(this.context, this.phoneAccountHandle, (Bundle) null);
        }
    }

    public void startDeactivation() {
        VvmLog.m45i("OmtpVvmCarrierCfgHlpr", "startDeactivation");
        if (isValid()) {
            if (!isLegacyModeEnabled()) {
                ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(getPhoneAccountHandle()).setVisualVoicemailSmsFilterSettings((VisualVoicemailSmsFilterSettings) null);
                VvmLog.m45i("OmtpVvmCarrierCfgHlpr", "filter disabled");
            }
            this.protocol.startDeactivation(this);
        }
        VvmAccountManager.removeAccount(this.context, getPhoneAccountHandle());
    }

    public void startProvisioning(ActivationTask activationTask, PhoneAccountHandle phoneAccountHandle2, VoicemailStatus$Editor voicemailStatus$Editor, StatusMessage statusMessage, Bundle bundle, boolean z) {
        Assert.checkArgument(isValid());
        this.protocol.startProvisioning(activationTask, phoneAccountHandle2, this, voicemailStatus$Editor, statusMessage, bundle, z);
    }

    public boolean supportsProvisioning() {
        Assert.checkArgument(isValid());
        return this.protocol.supportsProvisioning();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OmtpVvmCarrierConfigHelper [");
        sb.append("phoneAccountHandle: ");
        sb.append(this.phoneAccountHandle);
        sb.append(", carrierConfig: ");
        boolean z = true;
        sb.append(this.carrierConfig != null);
        sb.append(", telephonyConfig: ");
        if (this.telephonyConfig == null) {
            z = false;
        }
        sb.append(z);
        sb.append(", type: ");
        sb.append(getVvmType());
        sb.append(", destinationNumber: ");
        sb.append(getDestinationNumber());
        sb.append(", applicationPort: ");
        sb.append(getApplicationPort());
        sb.append(", sslPort: ");
        sb.append(getSslPort());
        sb.append(", isEnabledByDefault: ");
        sb.append(isEnabledByDefault());
        sb.append(", isCellularDataRequired: ");
        sb.append(isCellularDataRequired());
        sb.append(", isPrefetchEnabled: ");
        sb.append(isPrefetchEnabled());
        sb.append(", isLegacyModeEnabled: ");
        sb.append(isLegacyModeEnabled());
        sb.append("]");
        return sb.toString();
    }

    private static Set<String> getDisabledCapabilities(PersistableBundle persistableBundle) {
        String[] stringArray;
        if (persistableBundle == null || !persistableBundle.containsKey("vvm_disabled_capabilities_string_array") || (stringArray = persistableBundle.getStringArray("vvm_disabled_capabilities_string_array")) == null || stringArray.length <= 0) {
            return null;
        }
        ArraySet arraySet = new ArraySet();
        Collections.addAll(arraySet, stringArray);
        return arraySet;
    }

    OmtpVvmCarrierConfigHelper(Context context2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, PhoneAccountHandle phoneAccountHandle2) {
        this.context = context2;
        this.carrierConfig = persistableBundle;
        this.telephonyConfig = persistableBundle2;
        this.phoneAccountHandle = phoneAccountHandle2;
        this.overrideConfig = null;
        this.vvmType = getVvmType();
        this.context.getResources();
        this.protocol = R$style.create(this.vvmType);
    }
}
