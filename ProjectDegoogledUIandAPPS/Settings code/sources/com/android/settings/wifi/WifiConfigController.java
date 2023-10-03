package com.android.settings.wifi;

import android.content.Context;
import android.content.res.Resources;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.NetworkInfo;
import android.net.NetworkUtils;
import android.net.ProxyInfo;
import android.net.StaticIpConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.UserManager;
import android.security.KeyStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.settings.wifi.details.WifiPrivacyPreferenceController;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.AccessPoint;
import com.havoc.config.center.C1715R;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public class WifiConfigController implements TextWatcher, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, TextView.OnEditorActionListener, View.OnKeyListener {
    static final String[] UNDESIRED_CERTIFICATES = {"MacRandSecret", "MacRandSapSecret"};
    private final AccessPoint mAccessPoint;
    int mAccessPointSecurity;
    private final WifiConfigUiBase mConfigUi;
    private Context mContext;
    private ScrollView mDialogContainer;
    private TextView mDns1View;
    private TextView mDns2View;
    private String mDoNotProvideEapUserCertString;
    private String mDoNotValidateEapServerString;
    private TextView mEapAnonymousView;
    private Spinner mEapCaCertSpinner;
    private TextView mEapDomainView;
    private TextView mEapIdentityView;
    private Spinner mEapMethodSpinner;
    private Spinner mEapUserCertSpinner;
    private TextView mGatewayView;
    private Spinner mHiddenSettingsSpinner;
    private TextView mHiddenWarningView;
    private ProxyInfo mHttpProxy = null;
    private TextView mIpAddressView;
    private IpConfiguration.IpAssignment mIpAssignment = IpConfiguration.IpAssignment.UNASSIGNED;
    private Spinner mIpSettingsSpinner;
    private String[] mLevels;
    private Spinner mMeteredSettingsSpinner;
    private int mMode;
    private String mMultipleCertSetString;
    private TextView mNetworkPrefixLengthView;
    private ImageButton mPasswordScanButton;
    private TextView mPasswordView;
    private ArrayAdapter<CharSequence> mPhase2Adapter;
    private ArrayAdapter<CharSequence> mPhase2PeapAdapter;
    private Spinner mPhase2Spinner;
    private ArrayAdapter<CharSequence> mPhase2TtlsAdapter;
    private Spinner mPrivacySettingsSpinner;
    private TextView mProxyExclusionListView;
    private TextView mProxyHostView;
    private TextView mProxyPacView;
    private TextView mProxyPortView;
    private IpConfiguration.ProxySettings mProxySettings = IpConfiguration.ProxySettings.UNASSIGNED;
    private Spinner mProxySettingsSpinner;
    Integer[] mSecurityInPosition;
    private Spinner mSecuritySpinner;
    private CheckBox mSharedCheckBox;
    private ImageButton mSsidScanButton;
    private TextView mSsidView;
    private StaticIpConfiguration mStaticIpConfiguration = null;
    private String mUnspecifiedCertString;
    private String mUseSystemCertsString;
    private final View mView;
    private final WifiManager mWifiManager;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public WifiConfigController(WifiConfigUiBase wifiConfigUiBase, View view, AccessPoint accessPoint, int i) {
        this.mConfigUi = wifiConfigUiBase;
        this.mView = view;
        this.mAccessPoint = accessPoint;
        this.mContext = this.mConfigUi.getContext();
        this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        initWifiConfigController(accessPoint, i);
    }

    public WifiConfigController(WifiConfigUiBase wifiConfigUiBase, View view, AccessPoint accessPoint, int i, WifiManager wifiManager) {
        this.mConfigUi = wifiConfigUiBase;
        this.mView = view;
        this.mAccessPoint = accessPoint;
        this.mContext = this.mConfigUi.getContext();
        this.mWifiManager = wifiManager;
        initWifiConfigController(accessPoint, i);
    }

    private void initWifiConfigController(AccessPoint accessPoint, int i) {
        boolean z;
        LinkAddress linkAddress;
        this.mAccessPointSecurity = accessPoint == null ? 0 : accessPoint.getSecurity();
        this.mMode = i;
        Resources resources = this.mContext.getResources();
        this.mLevels = resources.getStringArray(C1715R.array.wifi_signal);
        if (Utils.isWifiOnly(this.mContext) || !this.mContext.getResources().getBoolean(17891438)) {
            this.mPhase2PeapAdapter = new ArrayAdapter<>(this.mContext, 17367048, resources.getStringArray(C1715R.array.wifi_peap_phase2_entries));
        } else {
            this.mPhase2PeapAdapter = new ArrayAdapter<>(this.mContext, 17367048, resources.getStringArray(C1715R.array.wifi_peap_phase2_entries_with_sim_auth));
        }
        this.mPhase2PeapAdapter.setDropDownViewResource(17367049);
        this.mPhase2TtlsAdapter = new ArrayAdapter<>(this.mContext, 17367048, resources.getStringArray(C1715R.array.wifi_ttls_phase2_entries));
        this.mPhase2TtlsAdapter.setDropDownViewResource(17367049);
        this.mUnspecifiedCertString = this.mContext.getString(C1715R.string.wifi_unspecified);
        this.mMultipleCertSetString = this.mContext.getString(C1715R.string.wifi_multiple_cert_added);
        this.mUseSystemCertsString = this.mContext.getString(C1715R.string.wifi_use_system_certs);
        this.mDoNotProvideEapUserCertString = this.mContext.getString(C1715R.string.wifi_do_not_provide_eap_user_cert);
        this.mDoNotValidateEapServerString = this.mContext.getString(C1715R.string.wifi_do_not_validate_eap_server);
        this.mSsidScanButton = (ImageButton) this.mView.findViewById(C1715R.C1718id.ssid_scanner_button);
        this.mPasswordScanButton = (ImageButton) this.mView.findViewById(C1715R.C1718id.password_scanner_button);
        this.mDialogContainer = (ScrollView) this.mView.findViewById(C1715R.C1718id.dialog_scrollview);
        this.mIpSettingsSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.ip_settings);
        this.mIpSettingsSpinner.setOnItemSelectedListener(this);
        this.mProxySettingsSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.proxy_settings);
        this.mProxySettingsSpinner.setOnItemSelectedListener(this);
        this.mSharedCheckBox = (CheckBox) this.mView.findViewById(C1715R.C1718id.shared);
        this.mMeteredSettingsSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.metered_settings);
        this.mHiddenSettingsSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.hidden_settings);
        this.mPrivacySettingsSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.privacy_settings);
        if (this.mContext.getResources().getBoolean(17891612)) {
            this.mView.findViewById(C1715R.C1718id.privacy_settings_fields).setVisibility(0);
        }
        this.mHiddenSettingsSpinner.setOnItemSelectedListener(this);
        this.mHiddenWarningView = (TextView) this.mView.findViewById(C1715R.C1718id.hidden_settings_warning);
        this.mHiddenWarningView.setVisibility(this.mHiddenSettingsSpinner.getSelectedItemPosition() == 0 ? 8 : 0);
        this.mSecurityInPosition = new Integer[7];
        AccessPoint accessPoint2 = this.mAccessPoint;
        if (accessPoint2 == null) {
            configureSecuritySpinner();
            this.mConfigUi.setSubmitButton(resources.getString(C1715R.string.wifi_save));
            this.mPasswordScanButton.setVisibility(8);
        } else {
            this.mConfigUi.setTitle((CharSequence) accessPoint2.getTitle());
            ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(C1715R.C1718id.info);
            if (this.mAccessPoint.isSaved()) {
                WifiConfiguration config = this.mAccessPoint.getConfig();
                this.mMeteredSettingsSpinner.setSelection(config.meteredOverride);
                this.mHiddenSettingsSpinner.setSelection(config.hiddenSSID ? 1 : 0);
                this.mPrivacySettingsSpinner.setSelection(WifiPrivacyPreferenceController.translateMacRandomizedValueToPrefValue(config.macRandomizationSetting));
                if (config.getIpAssignment() == IpConfiguration.IpAssignment.STATIC) {
                    this.mIpSettingsSpinner.setSelection(1);
                    StaticIpConfiguration staticIpConfiguration = config.getStaticIpConfiguration();
                    if (!(staticIpConfiguration == null || (linkAddress = staticIpConfiguration.ipAddress) == null)) {
                        addRow(viewGroup, C1715R.string.wifi_ip_address, linkAddress.getAddress().getHostAddress());
                    }
                    z = true;
                } else {
                    this.mIpSettingsSpinner.setSelection(0);
                    z = false;
                }
                this.mSharedCheckBox.setEnabled(config.shared);
                if (!config.shared) {
                    z = true;
                }
                if (config.getProxySettings() == IpConfiguration.ProxySettings.STATIC) {
                    this.mProxySettingsSpinner.setSelection(1);
                } else if (config.getProxySettings() == IpConfiguration.ProxySettings.PAC) {
                    this.mProxySettingsSpinner.setSelection(2);
                } else {
                    this.mProxySettingsSpinner.setSelection(0);
                    if (config != null && config.isPasspoint()) {
                        addRow(viewGroup, C1715R.string.passpoint_label, String.format(this.mContext.getString(C1715R.string.passpoint_content), new Object[]{config.providerFriendlyName}));
                    }
                }
                z = true;
                addRow(viewGroup, C1715R.string.passpoint_label, String.format(this.mContext.getString(C1715R.string.passpoint_content), new Object[]{config.providerFriendlyName}));
            } else {
                z = false;
            }
            if ((!this.mAccessPoint.isSaved() && !this.mAccessPoint.isActive() && !this.mAccessPoint.isPasspointConfig()) || this.mMode != 0) {
                showSecurityFields(true, true);
                showIpConfigFields();
                showProxyFields();
                CheckBox checkBox = (CheckBox) this.mView.findViewById(C1715R.C1718id.wifi_advanced_togglebox);
                this.mView.findViewById(C1715R.C1718id.wifi_advanced_toggle).setVisibility(this.mAccessPoint.isCarrierAp() ? 8 : 0);
                checkBox.setOnCheckedChangeListener(this);
                checkBox.setChecked(z);
                this.mView.findViewById(C1715R.C1718id.wifi_advanced_fields).setVisibility(z ? 0 : 8);
                if (this.mAccessPoint.isCarrierAp()) {
                    addRow(viewGroup, C1715R.string.wifi_carrier_connect, String.format(this.mContext.getString(C1715R.string.wifi_carrier_content), new Object[]{this.mAccessPoint.getCarrierName()}));
                }
            }
            int i2 = this.mMode;
            if (i2 == 2) {
                this.mConfigUi.setSubmitButton(resources.getString(C1715R.string.wifi_save));
            } else if (i2 == 1) {
                this.mConfigUi.setSubmitButton(resources.getString(C1715R.string.wifi_connect));
            } else {
                NetworkInfo.DetailedState detailedState = this.mAccessPoint.getDetailedState();
                String signalString = getSignalString();
                if ((detailedState == null || detailedState == NetworkInfo.DetailedState.DISCONNECTED) && signalString != null) {
                    this.mConfigUi.setSubmitButton(resources.getString(C1715R.string.wifi_connect));
                } else {
                    String str = null;
                    if (detailedState != null) {
                        boolean isEphemeral = this.mAccessPoint.isEphemeral();
                        WifiConfiguration config2 = this.mAccessPoint.getConfig();
                        if (config2 != null && config2.isPasspoint()) {
                            String str2 = config2.providerFriendlyName;
                        }
                        addRow(viewGroup, C1715R.string.wifi_status, AccessPoint.getSummary(this.mConfigUi.getContext(), (String) null, detailedState, isEphemeral, (config2 == null || (!config2.fromWifiNetworkSpecifier && !config2.fromWifiNetworkSuggestion)) ? null : config2.creatorName));
                    }
                    if (signalString != null) {
                        addRow(viewGroup, C1715R.string.wifi_signal, signalString);
                    }
                    WifiInfo info = this.mAccessPoint.getInfo();
                    if (!(info == null || info.getTxLinkSpeedMbps() == -1)) {
                        addRow(viewGroup, C1715R.string.tx_wifi_speed, String.format(resources.getString(C1715R.string.tx_link_speed), new Object[]{Integer.valueOf(info.getTxLinkSpeedMbps())}));
                    }
                    if (!(info == null || info.getRxLinkSpeedMbps() == -1)) {
                        addRow(viewGroup, C1715R.string.rx_wifi_speed, String.format(resources.getString(C1715R.string.rx_link_speed), new Object[]{Integer.valueOf(info.getRxLinkSpeedMbps())}));
                    }
                    if (!(info == null || info.getFrequency() == -1)) {
                        int frequency = info.getFrequency();
                        if (frequency >= 2400 && frequency < 2500) {
                            str = resources.getString(C1715R.string.wifi_band_24ghz);
                        } else if (frequency < 4900 || frequency >= 5900) {
                            Log.e("WifiConfigController", "Unexpected frequency " + frequency);
                        } else {
                            str = resources.getString(C1715R.string.wifi_band_5ghz);
                        }
                        if (str != null) {
                            addRow(viewGroup, C1715R.string.wifi_frequency, str);
                        }
                    }
                    addRow(viewGroup, C1715R.string.wifi_security, this.mAccessPoint.getSecurityString(false));
                    this.mView.findViewById(C1715R.C1718id.ip_fields).setVisibility(8);
                }
                if (this.mAccessPoint.isSaved() || this.mAccessPoint.isActive() || this.mAccessPoint.isPasspointConfig()) {
                    this.mConfigUi.setForgetButton(resources.getString(C1715R.string.wifi_forget));
                }
            }
            if (!WifiDppUtils.isSupportEnrolleeQrCodeScanner(this.mContext, this.mAccessPointSecurity)) {
                this.mPasswordScanButton.setVisibility(8);
            }
            this.mSsidScanButton.setVisibility(8);
        }
        if (!isSplitSystemUser()) {
            this.mSharedCheckBox.setVisibility(8);
        }
        this.mConfigUi.setCancelButton(resources.getString(C1715R.string.wifi_cancel));
        if (this.mConfigUi.getSubmitButton() != null) {
            enableSubmitIfAppropriate();
        }
        this.mView.findViewById(C1715R.C1718id.l_wifidialog).requestFocus();
    }

    /* access modifiers changed from: package-private */
    public boolean isSplitSystemUser() {
        UserManager userManager = (UserManager) this.mContext.getSystemService("user");
        return UserManager.isSplitSystemUser();
    }

    private void addRow(ViewGroup viewGroup, int i, String str) {
        View inflate = this.mConfigUi.getLayoutInflater().inflate(C1715R.layout.wifi_dialog_row, viewGroup, false);
        ((TextView) inflate.findViewById(C1715R.C1718id.name)).setText(i);
        ((TextView) inflate.findViewById(C1715R.C1718id.value)).setText(str);
        viewGroup.addView(inflate);
    }

    /* access modifiers changed from: package-private */
    public String getSignalString() {
        int level;
        if (!this.mAccessPoint.isReachable() || (level = this.mAccessPoint.getLevel()) <= -1) {
            return null;
        }
        String[] strArr = this.mLevels;
        if (level < strArr.length) {
            return strArr[level];
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void hideForgetButton() {
        Button forgetButton = this.mConfigUi.getForgetButton();
        if (forgetButton != null) {
            forgetButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void hideSubmitButton() {
        Button submitButton = this.mConfigUi.getSubmitButton();
        if (submitButton != null) {
            submitButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void enableSubmitIfAppropriate() {
        Button submitButton = this.mConfigUi.getSubmitButton();
        if (submitButton != null) {
            submitButton.setEnabled(isSubmittable());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isValidPsk(String str) {
        if (str.length() == 64 && str.matches("[0-9A-Fa-f]{64}")) {
            return true;
        }
        if (str.length() < 8 || str.length() > 63) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isValidSaePassword(String str) {
        return str.length() >= 1 && str.length() <= 63;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r0 = r7.mAccessPoint;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        r0 = r7.mAccessPoint;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSubmittable() {
        /*
            r7 = this;
            android.widget.TextView r0 = r7.mPasswordView
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x003b
            int r3 = r7.mAccessPointSecurity
            if (r3 != r1) goto L_0x0010
            int r0 = r0.length()
            if (r0 == 0) goto L_0x003c
        L_0x0010:
            int r0 = r7.mAccessPointSecurity
            r3 = 2
            if (r0 != r3) goto L_0x0025
            android.widget.TextView r0 = r7.mPasswordView
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            boolean r0 = r7.isValidPsk(r0)
            if (r0 == 0) goto L_0x003c
        L_0x0025:
            int r0 = r7.mAccessPointSecurity
            r3 = 5
            if (r0 != r3) goto L_0x003b
            android.widget.TextView r0 = r7.mPasswordView
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            boolean r0 = r7.isValidSaePassword(r0)
            if (r0 != 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            r1 = r2
        L_0x003c:
            android.widget.TextView r0 = r7.mSsidView
            if (r0 == 0) goto L_0x0046
            int r0 = r0.length()
            if (r0 == 0) goto L_0x006c
        L_0x0046:
            com.android.settingslib.wifi.AccessPoint r0 = r7.mAccessPoint
            if (r0 == 0) goto L_0x0050
            boolean r0 = r0.isSaved()
            if (r0 != 0) goto L_0x0052
        L_0x0050:
            if (r1 != 0) goto L_0x006c
        L_0x0052:
            com.android.settingslib.wifi.AccessPoint r0 = r7.mAccessPoint
            if (r0 == 0) goto L_0x0067
            boolean r0 = r0.isSaved()
            if (r0 == 0) goto L_0x0067
            if (r1 == 0) goto L_0x0067
            android.widget.TextView r0 = r7.mPasswordView
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0067
            goto L_0x006c
        L_0x0067:
            boolean r0 = r7.ipAndProxyFieldsAreValid()
            goto L_0x006d
        L_0x006c:
            r0 = r2
        L_0x006d:
            int r1 = r7.mAccessPointSecurity
            r3 = 6
            r4 = 3
            r5 = 8
            if (r1 == r4) goto L_0x0077
            if (r1 != r3) goto L_0x00c7
        L_0x0077:
            android.widget.Spinner r1 = r7.mEapCaCertSpinner
            if (r1 == 0) goto L_0x00c7
            android.view.View r1 = r7.mView
            r6 = 2131362472(0x7f0a02a8, float:1.8344726E38)
            android.view.View r1 = r1.findViewById(r6)
            int r1 = r1.getVisibility()
            if (r1 == r5) goto L_0x00c7
            android.widget.Spinner r1 = r7.mEapCaCertSpinner
            java.lang.Object r1 = r1.getSelectedItem()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r6 = r7.mUnspecifiedCertString
            boolean r6 = r1.equals(r6)
            if (r6 == 0) goto L_0x009b
            r0 = r2
        L_0x009b:
            java.lang.String r6 = r7.mUseSystemCertsString
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x00c7
            android.widget.TextView r1 = r7.mEapDomainView
            if (r1 == 0) goto L_0x00c7
            android.view.View r1 = r7.mView
            r6 = 2131362473(0x7f0a02a9, float:1.8344728E38)
            android.view.View r1 = r1.findViewById(r6)
            int r1 = r1.getVisibility()
            if (r1 == r5) goto L_0x00c7
            android.widget.TextView r1 = r7.mEapDomainView
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x00c7
            r0 = r2
        L_0x00c7:
            int r1 = r7.mAccessPointSecurity
            if (r1 == r4) goto L_0x00cd
            if (r1 != r3) goto L_0x00ef
        L_0x00cd:
            android.widget.Spinner r1 = r7.mEapUserCertSpinner
            if (r1 == 0) goto L_0x00ef
            android.view.View r1 = r7.mView
            r3 = 2131362477(0x7f0a02ad, float:1.8344736E38)
            android.view.View r1 = r1.findViewById(r3)
            int r1 = r1.getVisibility()
            if (r1 == r5) goto L_0x00ef
            android.widget.Spinner r1 = r7.mEapUserCertSpinner
            java.lang.Object r1 = r1.getSelectedItem()
            java.lang.String r7 = r7.mUnspecifiedCertString
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x00ef
            r0 = r2
        L_0x00ef:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.WifiConfigController.isSubmittable():boolean");
    }

    /* access modifiers changed from: package-private */
    public void showWarningMessagesIfAppropriate() {
        this.mView.findViewById(C1715R.C1718id.no_ca_cert_warning).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.no_domain_warning).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.ssid_too_long_warning).setVisibility(8);
        TextView textView = this.mSsidView;
        if (textView != null && WifiUtils.isSSIDTooLong(textView.getText().toString())) {
            this.mView.findViewById(C1715R.C1718id.ssid_too_long_warning).setVisibility(0);
        }
        if (this.mEapCaCertSpinner != null && this.mView.findViewById(C1715R.C1718id.l_ca_cert).getVisibility() != 8) {
            String str = (String) this.mEapCaCertSpinner.getSelectedItem();
            if (str.equals(this.mDoNotValidateEapServerString)) {
                this.mView.findViewById(C1715R.C1718id.no_ca_cert_warning).setVisibility(0);
            }
            if (str.equals(this.mUseSystemCertsString) && this.mEapDomainView != null && this.mView.findViewById(C1715R.C1718id.l_domain).getVisibility() != 8 && TextUtils.isEmpty(this.mEapDomainView.getText().toString())) {
                this.mView.findViewById(C1715R.C1718id.no_domain_warning).setVisibility(0);
            }
        }
    }

    public WifiConfiguration getConfig() {
        if (this.mMode == 0) {
            return null;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        AccessPoint accessPoint = this.mAccessPoint;
        if (accessPoint == null) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mSsidView.getText().toString());
            wifiConfiguration.hiddenSSID = this.mHiddenSettingsSpinner.getSelectedItemPosition() == 1;
        } else if (!accessPoint.isSaved()) {
            wifiConfiguration.SSID = AccessPoint.convertToQuotedString(this.mAccessPoint.getSsidStr());
        } else {
            wifiConfiguration.networkId = this.mAccessPoint.getConfig().networkId;
            wifiConfiguration.hiddenSSID = this.mAccessPoint.getConfig().hiddenSSID;
        }
        wifiConfiguration.shared = this.mSharedCheckBox.isChecked();
        switch (this.mAccessPointSecurity) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                break;
            case 1:
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                if (this.mPasswordView.length() != 0) {
                    int length = this.mPasswordView.length();
                    String charSequence = this.mPasswordView.getText().toString();
                    if ((length != 10 && length != 26 && length != 58) || !charSequence.matches("[0-9A-Fa-f]*")) {
                        String[] strArr = wifiConfiguration.wepKeys;
                        strArr[0] = '\"' + charSequence + '\"';
                        break;
                    } else {
                        wifiConfiguration.wepKeys[0] = charSequence;
                        break;
                    }
                }
                break;
            case 2:
                wifiConfiguration.allowedKeyManagement.set(1);
                if (this.mPasswordView.length() != 0) {
                    String charSequence2 = this.mPasswordView.getText().toString();
                    if (!charSequence2.matches("[0-9A-Fa-f]{64}")) {
                        wifiConfiguration.preSharedKey = '\"' + charSequence2 + '\"';
                        break;
                    } else {
                        wifiConfiguration.preSharedKey = charSequence2;
                        break;
                    }
                }
                break;
            case 3:
            case 6:
                wifiConfiguration.allowedKeyManagement.set(2);
                wifiConfiguration.allowedKeyManagement.set(3);
                if (this.mAccessPointSecurity == 6) {
                    wifiConfiguration.allowedKeyManagement.set(10);
                    wifiConfiguration.requirePMF = true;
                    wifiConfiguration.allowedPairwiseCiphers.set(3);
                    wifiConfiguration.allowedGroupCiphers.set(5);
                    wifiConfiguration.allowedGroupManagementCiphers.set(2);
                }
                wifiConfiguration.enterpriseConfig = new WifiEnterpriseConfig();
                int selectedItemPosition = this.mEapMethodSpinner.getSelectedItemPosition();
                int selectedItemPosition2 = this.mPhase2Spinner.getSelectedItemPosition();
                wifiConfiguration.enterpriseConfig.setEapMethod(selectedItemPosition);
                if (selectedItemPosition != 0) {
                    if (selectedItemPosition == 2) {
                        if (selectedItemPosition2 == 0) {
                            wifiConfiguration.enterpriseConfig.setPhase2Method(1);
                        } else if (selectedItemPosition2 == 1) {
                            wifiConfiguration.enterpriseConfig.setPhase2Method(2);
                        } else if (selectedItemPosition2 == 2) {
                            wifiConfiguration.enterpriseConfig.setPhase2Method(3);
                        } else if (selectedItemPosition2 != 3) {
                            Log.e("WifiConfigController", "Unknown phase2 method" + selectedItemPosition2);
                        } else {
                            wifiConfiguration.enterpriseConfig.setPhase2Method(4);
                        }
                    }
                } else if (selectedItemPosition2 == 0) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(3);
                } else if (selectedItemPosition2 == 1) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(4);
                } else if (selectedItemPosition2 == 2) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(5);
                } else if (selectedItemPosition2 == 3) {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(6);
                } else if (selectedItemPosition2 != 4) {
                    Log.e("WifiConfigController", "Unknown phase2 method" + selectedItemPosition2);
                } else {
                    wifiConfiguration.enterpriseConfig.setPhase2Method(7);
                }
                String str = (String) this.mEapCaCertSpinner.getSelectedItem();
                wifiConfiguration.enterpriseConfig.setCaCertificateAliases((String[]) null);
                wifiConfiguration.enterpriseConfig.setCaPath((String) null);
                wifiConfiguration.enterpriseConfig.setDomainSuffixMatch(this.mEapDomainView.getText().toString());
                if (!str.equals(this.mUnspecifiedCertString) && !str.equals(this.mDoNotValidateEapServerString)) {
                    if (str.equals(this.mUseSystemCertsString)) {
                        wifiConfiguration.enterpriseConfig.setCaPath("/system/etc/security/cacerts");
                    } else if (str.equals(this.mMultipleCertSetString)) {
                        AccessPoint accessPoint2 = this.mAccessPoint;
                        if (accessPoint2 != null) {
                            if (!accessPoint2.isSaved()) {
                                Log.e("WifiConfigController", "Multiple certs can only be set when editing saved network");
                            }
                            wifiConfiguration.enterpriseConfig.setCaCertificateAliases(this.mAccessPoint.getConfig().enterpriseConfig.getCaCertificateAliases());
                        }
                    } else {
                        wifiConfiguration.enterpriseConfig.setCaCertificateAliases(new String[]{str});
                    }
                }
                if (!(wifiConfiguration.enterpriseConfig.getCaCertificateAliases() == null || wifiConfiguration.enterpriseConfig.getCaPath() == null)) {
                    Log.e("WifiConfigController", "ca_cert (" + wifiConfiguration.enterpriseConfig.getCaCertificateAliases() + ") and ca_path (" + wifiConfiguration.enterpriseConfig.getCaPath() + ") should not both be non-null");
                }
                String str2 = (String) this.mEapUserCertSpinner.getSelectedItem();
                if (str2.equals(this.mUnspecifiedCertString) || str2.equals(this.mDoNotProvideEapUserCertString)) {
                    str2 = "";
                }
                wifiConfiguration.enterpriseConfig.setClientCertificateAlias(str2);
                if (selectedItemPosition == 4 || selectedItemPosition == 5 || selectedItemPosition == 6) {
                    wifiConfiguration.enterpriseConfig.setIdentity("");
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity("");
                } else if (selectedItemPosition == 3) {
                    wifiConfiguration.enterpriseConfig.setIdentity(this.mEapIdentityView.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity("");
                } else {
                    wifiConfiguration.enterpriseConfig.setIdentity(this.mEapIdentityView.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity(this.mEapAnonymousView.getText().toString());
                }
                if (this.mPasswordView.isShown()) {
                    if (this.mPasswordView.length() > 0) {
                        wifiConfiguration.enterpriseConfig.setPassword(this.mPasswordView.getText().toString());
                        break;
                    }
                } else {
                    wifiConfiguration.enterpriseConfig.setPassword(this.mPasswordView.getText().toString());
                    break;
                }
                break;
            case 4:
                wifiConfiguration.allowedKeyManagement.set(9);
                wifiConfiguration.requirePMF = true;
                break;
            case 5:
                wifiConfiguration.allowedKeyManagement.set(8);
                wifiConfiguration.requirePMF = true;
                if (this.mPasswordView.length() != 0) {
                    String charSequence3 = this.mPasswordView.getText().toString();
                    wifiConfiguration.preSharedKey = '\"' + charSequence3 + '\"';
                    break;
                }
                break;
            default:
                return null;
        }
        wifiConfiguration.setIpConfiguration(new IpConfiguration(this.mIpAssignment, this.mProxySettings, this.mStaticIpConfiguration, this.mHttpProxy));
        Spinner spinner = this.mMeteredSettingsSpinner;
        if (spinner != null) {
            wifiConfiguration.meteredOverride = spinner.getSelectedItemPosition();
        }
        Spinner spinner2 = this.mPrivacySettingsSpinner;
        if (spinner2 != null) {
            wifiConfiguration.macRandomizationSetting = WifiPrivacyPreferenceController.translatePrefValueToMacRandomizedValue(spinner2.getSelectedItemPosition());
        }
        return wifiConfiguration;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0073 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean ipAndProxyFieldsAreValid() {
        /*
            r6 = this;
            android.widget.Spinner r0 = r6.mIpSettingsSpinner
            r1 = 1
            if (r0 == 0) goto L_0x000e
            int r0 = r0.getSelectedItemPosition()
            if (r0 != r1) goto L_0x000e
            android.net.IpConfiguration$IpAssignment r0 = android.net.IpConfiguration.IpAssignment.STATIC
            goto L_0x0010
        L_0x000e:
            android.net.IpConfiguration$IpAssignment r0 = android.net.IpConfiguration.IpAssignment.DHCP
        L_0x0010:
            r6.mIpAssignment = r0
            android.net.IpConfiguration$IpAssignment r0 = r6.mIpAssignment
            android.net.IpConfiguration$IpAssignment r2 = android.net.IpConfiguration.IpAssignment.STATIC
            r3 = 0
            if (r0 != r2) goto L_0x0029
            android.net.StaticIpConfiguration r0 = new android.net.StaticIpConfiguration
            r0.<init>()
            r6.mStaticIpConfiguration = r0
            android.net.StaticIpConfiguration r0 = r6.mStaticIpConfiguration
            int r0 = r6.validateIpConfigFields(r0)
            if (r0 == 0) goto L_0x0029
            return r3
        L_0x0029:
            android.widget.Spinner r0 = r6.mProxySettingsSpinner
            int r0 = r0.getSelectedItemPosition()
            android.net.IpConfiguration$ProxySettings r2 = android.net.IpConfiguration.ProxySettings.NONE
            r6.mProxySettings = r2
            r2 = 0
            r6.mHttpProxy = r2
            if (r0 != r1) goto L_0x0074
            android.widget.TextView r2 = r6.mProxyHostView
            if (r2 == 0) goto L_0x0074
            android.net.IpConfiguration$ProxySettings r0 = android.net.IpConfiguration.ProxySettings.STATIC
            r6.mProxySettings = r0
            java.lang.CharSequence r0 = r2.getText()
            java.lang.String r0 = r0.toString()
            android.widget.TextView r2 = r6.mProxyPortView
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            android.widget.TextView r4 = r6.mProxyExclusionListView
            java.lang.CharSequence r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            int r5 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0065 }
            int r2 = com.android.settings.ProxySelector.validate(r0, r2, r4)     // Catch:{ NumberFormatException -> 0x0066 }
            goto L_0x0069
        L_0x0065:
            r5 = r3
        L_0x0066:
            r2 = 2131889809(0x7f120e91, float:1.9414292E38)
        L_0x0069:
            if (r2 != 0) goto L_0x0073
            android.net.ProxyInfo r2 = new android.net.ProxyInfo
            r2.<init>(r0, r5, r4)
            r6.mHttpProxy = r2
            goto L_0x009c
        L_0x0073:
            return r3
        L_0x0074:
            r2 = 2
            if (r0 != r2) goto L_0x009c
            android.widget.TextView r0 = r6.mProxyPacView
            if (r0 == 0) goto L_0x009c
            android.net.IpConfiguration$ProxySettings r2 = android.net.IpConfiguration.ProxySettings.PAC
            r6.mProxySettings = r2
            java.lang.CharSequence r0 = r0.getText()
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x008a
            return r3
        L_0x008a:
            java.lang.String r0 = r0.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            if (r0 != 0) goto L_0x0095
            return r3
        L_0x0095:
            android.net.ProxyInfo r2 = new android.net.ProxyInfo
            r2.<init>(r0)
            r6.mHttpProxy = r2
        L_0x009c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.wifi.WifiConfigController.ipAndProxyFieldsAreValid():boolean");
    }

    private Inet4Address getIPv4Address(String str) {
        try {
            return (Inet4Address) NetworkUtils.numericToInetAddress(str);
        } catch (ClassCastException | IllegalArgumentException unused) {
            return null;
        }
    }

    private int validateIpConfigFields(StaticIpConfiguration staticIpConfiguration) {
        Inet4Address iPv4Address;
        TextView textView = this.mIpAddressView;
        if (textView == null) {
            return 0;
        }
        String charSequence = textView.getText().toString();
        if (TextUtils.isEmpty(charSequence) || (iPv4Address = getIPv4Address(charSequence)) == null || iPv4Address.equals(Inet4Address.ANY)) {
            return C1715R.string.wifi_ip_settings_invalid_ip_address;
        }
        int i = -1;
        try {
            i = Integer.parseInt(this.mNetworkPrefixLengthView.getText().toString());
            if (i < 0) {
                return C1715R.string.wifi_ip_settings_invalid_network_prefix_length;
            }
            if (i > 32) {
                return C1715R.string.wifi_ip_settings_invalid_network_prefix_length;
            }
            staticIpConfiguration.ipAddress = new LinkAddress(iPv4Address, i);
            String charSequence2 = this.mGatewayView.getText().toString();
            if (TextUtils.isEmpty(charSequence2)) {
                try {
                    byte[] address = NetworkUtils.getNetworkPart(iPv4Address, i).getAddress();
                    address[address.length - 1] = 1;
                    this.mGatewayView.setText(InetAddress.getByAddress(address).getHostAddress());
                } catch (RuntimeException | UnknownHostException unused) {
                }
            } else {
                Inet4Address iPv4Address2 = getIPv4Address(charSequence2);
                if (iPv4Address2 == null || iPv4Address2.isMulticastAddress()) {
                    return C1715R.string.wifi_ip_settings_invalid_gateway;
                }
                staticIpConfiguration.gateway = iPv4Address2;
            }
            String charSequence3 = this.mDns1View.getText().toString();
            if (TextUtils.isEmpty(charSequence3)) {
                this.mDns1View.setText(this.mConfigUi.getContext().getString(C1715R.string.wifi_dns1_hint));
            } else {
                Inet4Address iPv4Address3 = getIPv4Address(charSequence3);
                if (iPv4Address3 == null) {
                    return C1715R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address3);
            }
            if (this.mDns2View.length() > 0) {
                Inet4Address iPv4Address4 = getIPv4Address(this.mDns2View.getText().toString());
                if (iPv4Address4 == null) {
                    return C1715R.string.wifi_ip_settings_invalid_dns;
                }
                staticIpConfiguration.dnsServers.add(iPv4Address4);
            }
            return 0;
        } catch (NumberFormatException unused2) {
            this.mNetworkPrefixLengthView.setText(this.mConfigUi.getContext().getString(C1715R.string.wifi_network_prefix_length_hint));
        } catch (IllegalArgumentException unused3) {
            return C1715R.string.wifi_ip_settings_invalid_ip_address;
        }
    }

    private void showSecurityFields(boolean z, boolean z2) {
        boolean z3;
        AccessPoint accessPoint;
        AccessPoint accessPoint2;
        int i = this.mAccessPointSecurity;
        if (i == 0 || i == 4) {
            this.mView.findViewById(C1715R.C1718id.security_fields).setVisibility(8);
            return;
        }
        this.mView.findViewById(C1715R.C1718id.security_fields).setVisibility(0);
        if (this.mPasswordView == null) {
            this.mPasswordView = (TextView) this.mView.findViewById(C1715R.C1718id.password);
            this.mPasswordView.addTextChangedListener(this);
            this.mPasswordView.setOnEditorActionListener(this);
            this.mPasswordView.setOnKeyListener(this);
            ((CheckBox) this.mView.findViewById(C1715R.C1718id.show_password)).setOnCheckedChangeListener(this);
            AccessPoint accessPoint3 = this.mAccessPoint;
            if (accessPoint3 != null && accessPoint3.isSaved()) {
                this.mPasswordView.setHint(C1715R.string.wifi_unchanged);
                this.mPasswordScanButton.setVisibility(8);
            }
        }
        int i2 = this.mAccessPointSecurity;
        if (i2 == 3 || i2 == 6) {
            this.mView.findViewById(C1715R.C1718id.eap).setVisibility(0);
            if (this.mEapMethodSpinner == null) {
                this.mEapMethodSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.method);
                this.mEapMethodSpinner.setOnItemSelectedListener(this);
                this.mPhase2Spinner = (Spinner) this.mView.findViewById(C1715R.C1718id.phase2);
                this.mPhase2Spinner.setOnItemSelectedListener(this);
                this.mEapCaCertSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.ca_cert);
                this.mEapCaCertSpinner.setOnItemSelectedListener(this);
                this.mEapDomainView = (TextView) this.mView.findViewById(C1715R.C1718id.domain);
                this.mEapDomainView.addTextChangedListener(this);
                this.mEapUserCertSpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.user_cert);
                this.mEapUserCertSpinner.setOnItemSelectedListener(this);
                this.mEapIdentityView = (TextView) this.mView.findViewById(C1715R.C1718id.identity);
                this.mEapAnonymousView = (TextView) this.mView.findViewById(C1715R.C1718id.anonymous);
                z3 = true;
            } else {
                z3 = false;
            }
            if (z) {
                if (this.mAccessPointSecurity == 6) {
                    this.mEapMethodSpinner.setAdapter(getSpinnerAdapter(C1715R.array.wifi_eap_method));
                    this.mEapMethodSpinner.setSelection(1);
                    this.mEapMethodSpinner.setEnabled(false);
                } else if (Utils.isWifiOnly(this.mContext) || !this.mContext.getResources().getBoolean(17891438)) {
                    this.mEapMethodSpinner.setAdapter(getSpinnerAdapter(C1715R.array.eap_method_without_sim_auth));
                    this.mEapMethodSpinner.setEnabled(true);
                } else {
                    this.mEapMethodSpinner.setAdapter(getSpinnerAdapter(C1715R.array.wifi_eap_method));
                    this.mEapMethodSpinner.setEnabled(true);
                }
            }
            if (!(this.mAccessPointSecurity == 6 || (accessPoint2 = this.mAccessPoint) == null || !accessPoint2.isCarrierAp())) {
                this.mEapMethodSpinner.setSelection(this.mAccessPoint.getCarrierApEapType());
            }
            if (z2) {
                loadCertificates(this.mEapCaCertSpinner, "CACERT_", this.mDoNotValidateEapServerString, false, true);
                loadCertificates(this.mEapUserCertSpinner, "USRPKEY_", this.mDoNotProvideEapUserCertString, false, false);
            }
            if (!z3 || (accessPoint = this.mAccessPoint) == null || !accessPoint.isSaved()) {
                showEapFieldsByMethod(this.mEapMethodSpinner.getSelectedItemPosition());
                return;
            }
            WifiEnterpriseConfig wifiEnterpriseConfig = this.mAccessPoint.getConfig().enterpriseConfig;
            int eapMethod = wifiEnterpriseConfig.getEapMethod();
            int phase2Method = wifiEnterpriseConfig.getPhase2Method();
            this.mEapMethodSpinner.setSelection(eapMethod);
            showEapFieldsByMethod(eapMethod);
            if (eapMethod != 0) {
                if (eapMethod == 2) {
                    if (phase2Method == 1) {
                        this.mPhase2Spinner.setSelection(0);
                    } else if (phase2Method == 2) {
                        this.mPhase2Spinner.setSelection(1);
                    } else if (phase2Method == 3) {
                        this.mPhase2Spinner.setSelection(2);
                    } else if (phase2Method != 4) {
                        Log.e("WifiConfigController", "Invalid phase 2 method " + phase2Method);
                    } else {
                        this.mPhase2Spinner.setSelection(3);
                    }
                }
            } else if (phase2Method == 3) {
                this.mPhase2Spinner.setSelection(0);
            } else if (phase2Method == 4) {
                this.mPhase2Spinner.setSelection(1);
            } else if (phase2Method == 5) {
                this.mPhase2Spinner.setSelection(2);
            } else if (phase2Method == 6) {
                this.mPhase2Spinner.setSelection(3);
            } else if (phase2Method != 7) {
                Log.e("WifiConfigController", "Invalid phase 2 method " + phase2Method);
            } else {
                this.mPhase2Spinner.setSelection(4);
            }
            if (!TextUtils.isEmpty(wifiEnterpriseConfig.getCaPath())) {
                setSelection(this.mEapCaCertSpinner, this.mUseSystemCertsString);
            } else {
                String[] caCertificateAliases = wifiEnterpriseConfig.getCaCertificateAliases();
                if (caCertificateAliases == null) {
                    setSelection(this.mEapCaCertSpinner, this.mDoNotValidateEapServerString);
                } else if (caCertificateAliases.length == 1) {
                    setSelection(this.mEapCaCertSpinner, caCertificateAliases[0]);
                } else {
                    loadCertificates(this.mEapCaCertSpinner, "CACERT_", this.mDoNotValidateEapServerString, true, true);
                    setSelection(this.mEapCaCertSpinner, this.mMultipleCertSetString);
                }
            }
            this.mEapDomainView.setText(wifiEnterpriseConfig.getDomainSuffixMatch());
            String clientCertificateAlias = wifiEnterpriseConfig.getClientCertificateAlias();
            if (TextUtils.isEmpty(clientCertificateAlias)) {
                setSelection(this.mEapUserCertSpinner, this.mDoNotProvideEapUserCertString);
            } else {
                setSelection(this.mEapUserCertSpinner, clientCertificateAlias);
            }
            this.mEapIdentityView.setText(wifiEnterpriseConfig.getIdentity());
            this.mEapAnonymousView.setText(wifiEnterpriseConfig.getAnonymousIdentity());
            return;
        }
        this.mView.findViewById(C1715R.C1718id.eap).setVisibility(8);
    }

    private void showEapFieldsByMethod(int i) {
        this.mView.findViewById(C1715R.C1718id.l_method).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.l_identity).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.l_domain).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.l_ca_cert).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.password_layout).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.show_password_layout).setVisibility(0);
        this.mConfigUi.getContext();
        switch (i) {
            case 0:
                ArrayAdapter<CharSequence> arrayAdapter = this.mPhase2Adapter;
                ArrayAdapter<CharSequence> arrayAdapter2 = this.mPhase2PeapAdapter;
                if (arrayAdapter != arrayAdapter2) {
                    this.mPhase2Adapter = arrayAdapter2;
                    this.mPhase2Spinner.setAdapter(this.mPhase2Adapter);
                }
                this.mView.findViewById(C1715R.C1718id.l_phase2).setVisibility(0);
                this.mView.findViewById(C1715R.C1718id.l_anonymous).setVisibility(0);
                showPeapFields();
                setUserCertInvisible();
                break;
            case 1:
                this.mView.findViewById(C1715R.C1718id.l_user_cert).setVisibility(0);
                setPhase2Invisible();
                setAnonymousIdentInvisible();
                setPasswordInvisible();
                break;
            case 2:
                ArrayAdapter<CharSequence> arrayAdapter3 = this.mPhase2Adapter;
                ArrayAdapter<CharSequence> arrayAdapter4 = this.mPhase2TtlsAdapter;
                if (arrayAdapter3 != arrayAdapter4) {
                    this.mPhase2Adapter = arrayAdapter4;
                    this.mPhase2Spinner.setAdapter(this.mPhase2Adapter);
                }
                this.mView.findViewById(C1715R.C1718id.l_phase2).setVisibility(0);
                this.mView.findViewById(C1715R.C1718id.l_anonymous).setVisibility(0);
                setUserCertInvisible();
                break;
            case 3:
                setPhase2Invisible();
                setCaCertInvisible();
                setDomainInvisible();
                setAnonymousIdentInvisible();
                setUserCertInvisible();
                break;
            case 4:
            case 5:
            case 6:
                setPhase2Invisible();
                setAnonymousIdentInvisible();
                setCaCertInvisible();
                setDomainInvisible();
                setUserCertInvisible();
                setPasswordInvisible();
                setIdentityInvisible();
                AccessPoint accessPoint = this.mAccessPoint;
                if (accessPoint != null && accessPoint.isCarrierAp()) {
                    setEapMethodInvisible();
                    break;
                }
        }
        if (this.mView.findViewById(C1715R.C1718id.l_ca_cert).getVisibility() != 8) {
            String str = (String) this.mEapCaCertSpinner.getSelectedItem();
            if (str.equals(this.mDoNotValidateEapServerString) || str.equals(this.mUnspecifiedCertString)) {
                setDomainInvisible();
            }
        }
    }

    private void showPeapFields() {
        int selectedItemPosition = this.mPhase2Spinner.getSelectedItemPosition();
        if (selectedItemPosition == 2 || selectedItemPosition == 3 || selectedItemPosition == 4) {
            this.mEapIdentityView.setText("");
            this.mView.findViewById(C1715R.C1718id.l_identity).setVisibility(8);
            setPasswordInvisible();
            return;
        }
        this.mView.findViewById(C1715R.C1718id.l_identity).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.l_anonymous).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.password_layout).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.show_password_layout).setVisibility(0);
    }

    private void setIdentityInvisible() {
        this.mView.findViewById(C1715R.C1718id.l_identity).setVisibility(8);
    }

    private void setPhase2Invisible() {
        this.mView.findViewById(C1715R.C1718id.l_phase2).setVisibility(8);
    }

    private void setCaCertInvisible() {
        this.mView.findViewById(C1715R.C1718id.l_ca_cert).setVisibility(8);
        setSelection(this.mEapCaCertSpinner, this.mUnspecifiedCertString);
    }

    private void setDomainInvisible() {
        this.mView.findViewById(C1715R.C1718id.l_domain).setVisibility(8);
        this.mEapDomainView.setText("");
    }

    private void setUserCertInvisible() {
        this.mView.findViewById(C1715R.C1718id.l_user_cert).setVisibility(8);
        setSelection(this.mEapUserCertSpinner, this.mUnspecifiedCertString);
    }

    private void setAnonymousIdentInvisible() {
        this.mView.findViewById(C1715R.C1718id.l_anonymous).setVisibility(8);
        this.mEapAnonymousView.setText("");
    }

    private void setPasswordInvisible() {
        this.mPasswordView.setText("");
        this.mView.findViewById(C1715R.C1718id.password_layout).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.show_password_layout).setVisibility(8);
    }

    private void setEapMethodInvisible() {
        this.mView.findViewById(C1715R.C1718id.eap).setVisibility(8);
    }

    private void showIpConfigFields() {
        StaticIpConfiguration staticIpConfiguration;
        this.mView.findViewById(C1715R.C1718id.ip_fields).setVisibility(0);
        AccessPoint accessPoint = this.mAccessPoint;
        WifiConfiguration config = (accessPoint == null || !accessPoint.isSaved()) ? null : this.mAccessPoint.getConfig();
        if (this.mIpSettingsSpinner.getSelectedItemPosition() == 1) {
            this.mView.findViewById(C1715R.C1718id.staticip).setVisibility(0);
            if (this.mIpAddressView == null) {
                this.mIpAddressView = (TextView) this.mView.findViewById(C1715R.C1718id.ipaddress);
                this.mIpAddressView.addTextChangedListener(this);
                this.mGatewayView = (TextView) this.mView.findViewById(C1715R.C1718id.gateway);
                this.mGatewayView.addTextChangedListener(this);
                this.mNetworkPrefixLengthView = (TextView) this.mView.findViewById(C1715R.C1718id.network_prefix_length);
                this.mNetworkPrefixLengthView.addTextChangedListener(this);
                this.mDns1View = (TextView) this.mView.findViewById(C1715R.C1718id.dns1);
                this.mDns1View.addTextChangedListener(this);
                this.mDns2View = (TextView) this.mView.findViewById(C1715R.C1718id.dns2);
                this.mDns2View.addTextChangedListener(this);
            }
            if (config != null && (staticIpConfiguration = config.getStaticIpConfiguration()) != null) {
                LinkAddress linkAddress = staticIpConfiguration.ipAddress;
                if (linkAddress != null) {
                    this.mIpAddressView.setText(linkAddress.getAddress().getHostAddress());
                    this.mNetworkPrefixLengthView.setText(Integer.toString(staticIpConfiguration.ipAddress.getNetworkPrefixLength()));
                }
                InetAddress inetAddress = staticIpConfiguration.gateway;
                if (inetAddress != null) {
                    this.mGatewayView.setText(inetAddress.getHostAddress());
                }
                Iterator it = staticIpConfiguration.dnsServers.iterator();
                if (it.hasNext()) {
                    this.mDns1View.setText(((InetAddress) it.next()).getHostAddress());
                }
                if (it.hasNext()) {
                    this.mDns2View.setText(((InetAddress) it.next()).getHostAddress());
                    return;
                }
                return;
            }
            return;
        }
        this.mView.findViewById(C1715R.C1718id.staticip).setVisibility(8);
    }

    private void showProxyFields() {
        ProxyInfo httpProxy;
        ProxyInfo httpProxy2;
        this.mView.findViewById(C1715R.C1718id.proxy_settings_fields).setVisibility(0);
        AccessPoint accessPoint = this.mAccessPoint;
        WifiConfiguration config = (accessPoint == null || !accessPoint.isSaved()) ? null : this.mAccessPoint.getConfig();
        if (this.mProxySettingsSpinner.getSelectedItemPosition() == 1) {
            setVisibility(C1715R.C1718id.proxy_warning_limited_support, 0);
            setVisibility(C1715R.C1718id.proxy_fields, 0);
            setVisibility(C1715R.C1718id.proxy_pac_field, 8);
            if (this.mProxyHostView == null) {
                this.mProxyHostView = (TextView) this.mView.findViewById(C1715R.C1718id.proxy_hostname);
                this.mProxyHostView.addTextChangedListener(this);
                this.mProxyPortView = (TextView) this.mView.findViewById(C1715R.C1718id.proxy_port);
                this.mProxyPortView.addTextChangedListener(this);
                this.mProxyExclusionListView = (TextView) this.mView.findViewById(C1715R.C1718id.proxy_exclusionlist);
                this.mProxyExclusionListView.addTextChangedListener(this);
            }
            if (config != null && (httpProxy2 = config.getHttpProxy()) != null) {
                this.mProxyHostView.setText(httpProxy2.getHost());
                this.mProxyPortView.setText(Integer.toString(httpProxy2.getPort()));
                this.mProxyExclusionListView.setText(httpProxy2.getExclusionListAsString());
            }
        } else if (this.mProxySettingsSpinner.getSelectedItemPosition() == 2) {
            setVisibility(C1715R.C1718id.proxy_warning_limited_support, 8);
            setVisibility(C1715R.C1718id.proxy_fields, 8);
            setVisibility(C1715R.C1718id.proxy_pac_field, 0);
            if (this.mProxyPacView == null) {
                this.mProxyPacView = (TextView) this.mView.findViewById(C1715R.C1718id.proxy_pac);
                this.mProxyPacView.addTextChangedListener(this);
            }
            if (config != null && (httpProxy = config.getHttpProxy()) != null) {
                this.mProxyPacView.setText(httpProxy.getPacFileUrl().toString());
            }
        } else {
            setVisibility(C1715R.C1718id.proxy_warning_limited_support, 8);
            setVisibility(C1715R.C1718id.proxy_fields, 8);
            setVisibility(C1715R.C1718id.proxy_pac_field, 8);
        }
    }

    private void setVisibility(int i, int i2) {
        View findViewById = this.mView.findViewById(i);
        if (findViewById != null) {
            findViewById.setVisibility(i2);
        }
    }

    /* access modifiers changed from: package-private */
    public KeyStore getKeyStore() {
        return KeyStore.getInstance();
    }

    /* access modifiers changed from: package-private */
    public void loadCertificates(Spinner spinner, String str, String str2, boolean z, boolean z2) {
        Context context = this.mConfigUi.getContext();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mUnspecifiedCertString);
        if (z) {
            arrayList.add(this.mMultipleCertSetString);
        }
        if (z2) {
            arrayList.add(this.mUseSystemCertsString);
        }
        String[] strArr = null;
        try {
            strArr = getKeyStore().list(str, 1010);
        } catch (Exception unused) {
            Log.e("WifiConfigController", "can't get the certificate list from KeyStore");
        }
        if (!(strArr == null || strArr.length == 0)) {
            arrayList.addAll((Collection) Arrays.stream(strArr).filter($$Lambda$WifiConfigController$aHwAFoUScMrqGBeuDOQscm8s6RE.INSTANCE).collect(Collectors.toList()));
        }
        if (this.mAccessPointSecurity != 6) {
            arrayList.add(str2);
        }
        if (arrayList.size() == 2) {
            arrayList.remove(this.mUnspecifiedCertString);
            spinner.setEnabled(false);
        } else {
            spinner.setEnabled(true);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367048, (String[]) arrayList.toArray(new String[arrayList.size()]));
        arrayAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter(arrayAdapter);
    }

    static /* synthetic */ boolean lambda$loadCertificates$0(String str) {
        for (String startsWith : UNDESIRED_CERTIFICATES) {
            if (str.startsWith(startsWith)) {
                return false;
            }
        }
        return true;
    }

    private void setSelection(Spinner spinner, String str) {
        if (str != null) {
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
            for (int count = arrayAdapter.getCount() - 1; count >= 0; count--) {
                if (str.equals(arrayAdapter.getItem(count))) {
                    spinner.setSelection(count);
                    return;
                }
            }
        }
    }

    public int getMode() {
        return this.mMode;
    }

    public void afterTextChanged(Editable editable) {
        ThreadUtils.postOnMainThread(new Runnable() {
            public final void run() {
                WifiConfigController.this.lambda$afterTextChanged$1$WifiConfigController();
            }
        });
    }

    public /* synthetic */ void lambda$afterTextChanged$1$WifiConfigController() {
        showWarningMessagesIfAppropriate();
        enableSubmitIfAppropriate();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (textView != this.mPasswordView || i != 6 || !isSubmittable()) {
            return false;
        }
        this.mConfigUi.dispatchSubmit();
        return true;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view != this.mPasswordView || i != 66 || !isSubmittable()) {
            return false;
        }
        this.mConfigUi.dispatchSubmit();
        return true;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        int i2;
        if (compoundButton.getId() == C1715R.C1718id.show_password) {
            int selectionEnd = this.mPasswordView.getSelectionEnd();
            this.mPasswordView.setInputType((z ? 144 : 128) | 1);
            if (selectionEnd >= 0) {
                ((EditText) this.mPasswordView).setSelection(selectionEnd);
            }
        } else if (compoundButton.getId() == C1715R.C1718id.wifi_advanced_togglebox) {
            View findViewById = this.mView.findViewById(C1715R.C1718id.wifi_advanced_toggle);
            if (z) {
                i = 0;
                i2 = C1715R.string.wifi_advanced_toggle_description_expanded;
            } else {
                i = 8;
                i2 = C1715R.string.wifi_advanced_toggle_description_collapsed;
            }
            this.mView.findViewById(C1715R.C1718id.wifi_advanced_fields).setVisibility(i);
            findViewById.setContentDescription(this.mContext.getString(i2));
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = 8;
        if (adapterView == this.mSecuritySpinner) {
            this.mAccessPointSecurity = this.mSecurityInPosition[i].intValue();
            showSecurityFields(true, true);
            if (WifiDppUtils.isSupportEnrolleeQrCodeScanner(this.mContext, this.mAccessPointSecurity)) {
                this.mSsidScanButton.setVisibility(0);
            } else {
                this.mSsidScanButton.setVisibility(8);
            }
        } else {
            Spinner spinner = this.mEapMethodSpinner;
            if (adapterView == spinner) {
                showSecurityFields(false, true);
            } else if (adapterView == this.mEapCaCertSpinner) {
                showSecurityFields(false, false);
            } else if (adapterView == this.mPhase2Spinner && spinner.getSelectedItemPosition() == 0) {
                showPeapFields();
            } else if (adapterView == this.mProxySettingsSpinner) {
                showProxyFields();
            } else if (adapterView == this.mHiddenSettingsSpinner) {
                TextView textView = this.mHiddenWarningView;
                if (i != 0) {
                    i2 = 0;
                }
                textView.setVisibility(i2);
                if (i == 1) {
                    this.mDialogContainer.post(new Runnable() {
                        public final void run() {
                            WifiConfigController.this.lambda$onItemSelected$2$WifiConfigController();
                        }
                    });
                }
            } else {
                showIpConfigFields();
            }
        }
        showWarningMessagesIfAppropriate();
        enableSubmitIfAppropriate();
    }

    public /* synthetic */ void lambda$onItemSelected$2$WifiConfigController() {
        this.mDialogContainer.fullScroll(130);
    }

    public void updatePassword() {
        ((TextView) this.mView.findViewById(C1715R.C1718id.password)).setInputType((((CheckBox) this.mView.findViewById(C1715R.C1718id.show_password)).isChecked() ? 144 : 128) | 1);
    }

    public AccessPoint getAccessPoint() {
        return this.mAccessPoint;
    }

    private void configureSecuritySpinner() {
        int i;
        int i2;
        this.mConfigUi.setTitle((int) C1715R.string.wifi_add_network);
        this.mSsidView = (TextView) this.mView.findViewById(C1715R.C1718id.ssid);
        this.mSsidView.addTextChangedListener(this);
        this.mSecuritySpinner = (Spinner) this.mView.findViewById(C1715R.C1718id.security);
        this.mSecuritySpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, 17367048, 16908308);
        arrayAdapter.setDropDownViewResource(17367049);
        this.mSecuritySpinner.setAdapter(arrayAdapter);
        arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_none));
        this.mSecurityInPosition[0] = 0;
        if (this.mWifiManager.isEnhancedOpenSupported()) {
            arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_owe));
            this.mSecurityInPosition[1] = 4;
            i = 2;
        } else {
            i = 1;
        }
        arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_wep));
        int i3 = i + 1;
        this.mSecurityInPosition[i] = 1;
        arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_wpa_wpa2));
        int i4 = i3 + 1;
        this.mSecurityInPosition[i3] = 2;
        if (this.mWifiManager.isWpa3SaeSupported()) {
            arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_sae));
            i2 = i4 + 1;
            this.mSecurityInPosition[i4] = 5;
        } else {
            i2 = i4;
        }
        arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_eap));
        int i5 = i2 + 1;
        this.mSecurityInPosition[i2] = 3;
        if (this.mWifiManager.isWpa3SuiteBSupported()) {
            arrayAdapter.add(this.mContext.getString(C1715R.string.wifi_security_eap_suiteb));
            this.mSecurityInPosition[i5] = 6;
        }
        arrayAdapter.notifyDataSetChanged();
        this.mView.findViewById(C1715R.C1718id.type).setVisibility(0);
        showIpConfigFields();
        showProxyFields();
        this.mView.findViewById(C1715R.C1718id.wifi_advanced_toggle).setVisibility(0);
        this.mView.findViewById(C1715R.C1718id.hidden_settings_field).setVisibility(0);
        ((CheckBox) this.mView.findViewById(C1715R.C1718id.wifi_advanced_togglebox)).setOnCheckedChangeListener(this);
    }

    private ArrayAdapter<CharSequence> getSpinnerAdapter(int i) {
        ArrayAdapter<CharSequence> arrayAdapter = new ArrayAdapter<>(this.mContext, 17367048, this.mContext.getResources().getStringArray(i));
        arrayAdapter.setDropDownViewResource(17367049);
        return arrayAdapter;
    }
}
