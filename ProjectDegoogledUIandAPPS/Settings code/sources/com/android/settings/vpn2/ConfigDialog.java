package com.android.settings.vpn2;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Proxy;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.os.SystemProperties;
import android.security.KeyStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.internal.net.VpnProfile;
import com.havoc.config.center.C1715R;

class ConfigDialog extends AlertDialog implements TextWatcher, View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private TextView mAlwaysOnInvalidReason;
    private CheckBox mAlwaysOnVpn;
    private TextView mDnsServers;
    private boolean mEditing;
    private boolean mExists;
    private Spinner mIpsecCaCert;
    private TextView mIpsecIdentifier;
    private TextView mIpsecSecret;
    private Spinner mIpsecServerCert;
    private Spinner mIpsecUserCert;
    private final KeyStore mKeyStore = KeyStore.getInstance();
    private TextView mL2tpSecret;
    private final DialogInterface.OnClickListener mListener;
    private CheckBox mMppe;
    private TextView mName;
    private TextView mPassword;
    private final VpnProfile mProfile;
    private TextView mProxyHost;
    private TextView mProxyPort;
    private Spinner mProxySettings;
    private TextView mRoutes;
    private CheckBox mSaveLogin;
    private TextView mSearchDomains;
    private TextView mServer;
    private CheckBox mShowOptions;
    private Spinner mType;
    private TextView mUsername;
    private View mView;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    ConfigDialog(Context context, DialogInterface.OnClickListener onClickListener, VpnProfile vpnProfile, boolean z, boolean z2) {
        super(context);
        this.mListener = onClickListener;
        this.mProfile = vpnProfile;
        this.mEditing = z;
        this.mExists = z2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ProxyInfo proxyInfo;
        this.mView = getLayoutInflater().inflate(C1715R.layout.vpn_dialog, (ViewGroup) null);
        setView(this.mView);
        Context context = getContext();
        this.mName = (TextView) this.mView.findViewById(C1715R.C1718id.name);
        this.mType = (Spinner) this.mView.findViewById(C1715R.C1718id.type);
        this.mServer = (TextView) this.mView.findViewById(C1715R.C1718id.server);
        this.mUsername = (TextView) this.mView.findViewById(C1715R.C1718id.username);
        this.mPassword = (TextView) this.mView.findViewById(C1715R.C1718id.password);
        this.mSearchDomains = (TextView) this.mView.findViewById(C1715R.C1718id.search_domains);
        this.mDnsServers = (TextView) this.mView.findViewById(C1715R.C1718id.dns_servers);
        this.mRoutes = (TextView) this.mView.findViewById(C1715R.C1718id.routes);
        this.mProxySettings = (Spinner) this.mView.findViewById(C1715R.C1718id.vpn_proxy_settings);
        this.mProxyHost = (TextView) this.mView.findViewById(C1715R.C1718id.vpn_proxy_host);
        this.mProxyPort = (TextView) this.mView.findViewById(C1715R.C1718id.vpn_proxy_port);
        this.mMppe = (CheckBox) this.mView.findViewById(C1715R.C1718id.mppe);
        this.mL2tpSecret = (TextView) this.mView.findViewById(C1715R.C1718id.l2tp_secret);
        this.mIpsecIdentifier = (TextView) this.mView.findViewById(C1715R.C1718id.ipsec_identifier);
        this.mIpsecSecret = (TextView) this.mView.findViewById(C1715R.C1718id.ipsec_secret);
        this.mIpsecUserCert = (Spinner) this.mView.findViewById(C1715R.C1718id.ipsec_user_cert);
        this.mIpsecCaCert = (Spinner) this.mView.findViewById(C1715R.C1718id.ipsec_ca_cert);
        this.mIpsecServerCert = (Spinner) this.mView.findViewById(C1715R.C1718id.ipsec_server_cert);
        this.mSaveLogin = (CheckBox) this.mView.findViewById(C1715R.C1718id.save_login);
        this.mShowOptions = (CheckBox) this.mView.findViewById(C1715R.C1718id.show_options);
        this.mAlwaysOnVpn = (CheckBox) this.mView.findViewById(C1715R.C1718id.always_on_vpn);
        this.mAlwaysOnInvalidReason = (TextView) this.mView.findViewById(C1715R.C1718id.always_on_invalid_reason);
        this.mName.setText(this.mProfile.name);
        this.mType.setSelection(this.mProfile.type);
        this.mServer.setText(this.mProfile.server);
        VpnProfile vpnProfile = this.mProfile;
        if (vpnProfile.saveLogin) {
            this.mUsername.setText(vpnProfile.username);
            this.mPassword.setText(this.mProfile.password);
        }
        this.mSearchDomains.setText(this.mProfile.searchDomains);
        this.mDnsServers.setText(this.mProfile.dnsServers);
        this.mRoutes.setText(this.mProfile.routes);
        ProxyInfo proxyInfo2 = this.mProfile.proxy;
        if (proxyInfo2 != null) {
            this.mProxyHost.setText(proxyInfo2.getHost());
            int port = this.mProfile.proxy.getPort();
            this.mProxyPort.setText(port == 0 ? "" : Integer.toString(port));
        }
        this.mMppe.setChecked(this.mProfile.mppe);
        this.mL2tpSecret.setText(this.mProfile.l2tpSecret);
        this.mL2tpSecret.setTextAppearance(16974257);
        this.mIpsecIdentifier.setText(this.mProfile.ipsecIdentifier);
        this.mIpsecSecret.setText(this.mProfile.ipsecSecret);
        loadCertificates(this.mIpsecUserCert, "USRPKEY_", 0, this.mProfile.ipsecUserCert);
        loadCertificates(this.mIpsecCaCert, "CACERT_", C1715R.string.vpn_no_ca_cert, this.mProfile.ipsecCaCert);
        loadCertificates(this.mIpsecServerCert, "USRCERT_", C1715R.string.vpn_no_server_cert, this.mProfile.ipsecServerCert);
        this.mSaveLogin.setChecked(this.mProfile.saveLogin);
        this.mAlwaysOnVpn.setChecked(this.mProfile.key.equals(VpnUtils.getLockdownVpn()));
        this.mPassword.setTextAppearance(16974257);
        if (SystemProperties.getBoolean("persist.radio.imsregrequired", false)) {
            this.mAlwaysOnVpn.setVisibility(8);
        }
        this.mName.addTextChangedListener(this);
        this.mType.setOnItemSelectedListener(this);
        this.mServer.addTextChangedListener(this);
        this.mUsername.addTextChangedListener(this);
        this.mPassword.addTextChangedListener(this);
        this.mDnsServers.addTextChangedListener(this);
        this.mRoutes.addTextChangedListener(this);
        this.mProxySettings.setOnItemSelectedListener(this);
        this.mProxyHost.addTextChangedListener(this);
        this.mProxyPort.addTextChangedListener(this);
        this.mIpsecSecret.addTextChangedListener(this);
        this.mIpsecUserCert.setOnItemSelectedListener(this);
        this.mShowOptions.setOnClickListener(this);
        this.mAlwaysOnVpn.setOnCheckedChangeListener(this);
        this.mEditing = this.mEditing || !validate(true);
        if (this.mEditing) {
            setTitle((int) C1715R.string.vpn_edit);
            this.mView.findViewById(C1715R.C1718id.editor).setVisibility(0);
            changeType(this.mProfile.type);
            this.mSaveLogin.setVisibility(8);
            if (!this.mProfile.searchDomains.isEmpty() || !this.mProfile.dnsServers.isEmpty() || !this.mProfile.routes.isEmpty() || ((proxyInfo = this.mProfile.proxy) != null && (!proxyInfo.getHost().isEmpty() || this.mProfile.proxy.getPort() != 0))) {
                showAdvancedOptions();
            }
            if (this.mExists) {
                setButton(-3, context.getString(C1715R.string.vpn_forget), this.mListener);
            }
            setButton(-1, context.getString(C1715R.string.vpn_save), this.mListener);
        } else {
            setTitle(context.getString(C1715R.string.vpn_connect_to, new Object[]{this.mProfile.name}));
            setButton(-1, context.getString(C1715R.string.vpn_connect), this.mListener);
        }
        setButton(-2, context.getString(C1715R.string.vpn_cancel), this.mListener);
        super.onCreate(bundle);
        updateUiControls();
        getWindow().setSoftInputMode(20);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (this.mShowOptions.isChecked()) {
            showAdvancedOptions();
        }
    }

    public void afterTextChanged(Editable editable) {
        updateUiControls();
    }

    public void onClick(View view) {
        if (view == this.mShowOptions) {
            showAdvancedOptions();
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == this.mType) {
            changeType(i);
        } else if (adapterView == this.mProxySettings) {
            updateProxyFieldsVisibility(i);
        }
        updateUiControls();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.mAlwaysOnVpn) {
            updateUiControls();
        }
    }

    public boolean isVpnAlwaysOn() {
        return this.mAlwaysOnVpn.isChecked();
    }

    private void updateUiControls() {
        VpnProfile profile = getProfile();
        if (profile.isValidLockdownProfile()) {
            this.mAlwaysOnVpn.setEnabled(true);
            this.mAlwaysOnInvalidReason.setVisibility(8);
        } else {
            this.mAlwaysOnVpn.setChecked(false);
            this.mAlwaysOnVpn.setEnabled(false);
            if (!profile.isTypeValidForLockdown()) {
                this.mAlwaysOnInvalidReason.setText(C1715R.string.vpn_always_on_invalid_reason_type);
            } else if (!profile.isServerAddressNumeric()) {
                this.mAlwaysOnInvalidReason.setText(C1715R.string.vpn_always_on_invalid_reason_server);
            } else if (!profile.hasDns()) {
                this.mAlwaysOnInvalidReason.setText(C1715R.string.vpn_always_on_invalid_reason_no_dns);
            } else if (!profile.areDnsAddressesNumeric()) {
                this.mAlwaysOnInvalidReason.setText(C1715R.string.vpn_always_on_invalid_reason_dns);
            } else {
                this.mAlwaysOnInvalidReason.setText(C1715R.string.vpn_always_on_invalid_reason_other);
            }
            this.mAlwaysOnInvalidReason.setVisibility(0);
        }
        ProxyInfo proxyInfo = this.mProfile.proxy;
        if (proxyInfo != null && (!proxyInfo.getHost().isEmpty() || this.mProfile.proxy.getPort() != 0)) {
            this.mProxySettings.setSelection(1);
            updateProxyFieldsVisibility(1);
        }
        if (this.mAlwaysOnVpn.isChecked()) {
            this.mSaveLogin.setChecked(true);
            this.mSaveLogin.setEnabled(false);
        } else {
            this.mSaveLogin.setChecked(this.mProfile.saveLogin);
            this.mSaveLogin.setEnabled(true);
        }
        getButton(-1).setEnabled(validate(this.mEditing));
    }

    private void updateProxyFieldsVisibility(int i) {
        this.mView.findViewById(C1715R.C1718id.vpn_proxy_fields).setVisibility(i == 1 ? 0 : 8);
    }

    private void showAdvancedOptions() {
        this.mView.findViewById(C1715R.C1718id.options).setVisibility(0);
        this.mShowOptions.setVisibility(8);
    }

    private void changeType(int i) {
        this.mMppe.setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.l2tp).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.ipsec_psk).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.ipsec_user).setVisibility(8);
        this.mView.findViewById(C1715R.C1718id.ipsec_peer).setVisibility(8);
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    this.mView.findViewById(C1715R.C1718id.l2tp).setVisibility(0);
                } else if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                        this.mView.findViewById(C1715R.C1718id.ipsec_peer).setVisibility(0);
                        return;
                    }
                }
                this.mView.findViewById(C1715R.C1718id.ipsec_user).setVisibility(0);
                this.mView.findViewById(C1715R.C1718id.ipsec_peer).setVisibility(0);
                return;
            }
            this.mView.findViewById(C1715R.C1718id.l2tp).setVisibility(0);
            this.mView.findViewById(C1715R.C1718id.ipsec_psk).setVisibility(0);
            return;
        }
        this.mMppe.setVisibility(0);
    }

    private boolean validate(boolean z) {
        if (this.mAlwaysOnVpn.isChecked() && !getProfile().isValidLockdownProfile()) {
            return false;
        }
        if (!z) {
            if (this.mUsername.getText().length() == 0 || this.mPassword.getText().length() == 0) {
                return false;
            }
            return true;
        } else if (this.mName.getText().length() == 0 || this.mServer.getText().length() == 0 || !validateAddresses(this.mDnsServers.getText().toString(), false) || !validateAddresses(this.mRoutes.getText().toString(), true) || !validateProxy()) {
            return false;
        } else {
            int selectedItemPosition = this.mType.getSelectedItemPosition();
            if (selectedItemPosition == 0) {
                return true;
            }
            if (selectedItemPosition != 1) {
                if (selectedItemPosition != 2) {
                    if (selectedItemPosition != 3) {
                        if (selectedItemPosition != 4) {
                            return selectedItemPosition == 5;
                        }
                    }
                }
                if (this.mIpsecUserCert.getSelectedItemPosition() != 0) {
                    return true;
                }
                return false;
            }
            if (this.mIpsecSecret.getText().length() != 0) {
                return true;
            }
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean validateAddresses(java.lang.String r10, boolean r11) {
        /*
            r9 = this;
            r9 = 0
            java.lang.String r0 = " "
            java.lang.String[] r10 = r10.split(r0)     // Catch:{ Exception -> 0x0062 }
            int r0 = r10.length     // Catch:{ Exception -> 0x0062 }
            r1 = r9
        L_0x0009:
            r2 = 1
            if (r1 >= r0) goto L_0x0061
            r3 = r10[r1]     // Catch:{ Exception -> 0x0062 }
            boolean r4 = r3.isEmpty()     // Catch:{ Exception -> 0x0062 }
            if (r4 == 0) goto L_0x0015
            goto L_0x005d
        L_0x0015:
            r4 = 2
            r5 = 32
            if (r11 == 0) goto L_0x002c
            java.lang.String r6 = "/"
            java.lang.String[] r3 = r3.split(r6, r4)     // Catch:{ Exception -> 0x0062 }
            r6 = r3[r9]     // Catch:{ Exception -> 0x0062 }
            r3 = r3[r2]     // Catch:{ Exception -> 0x0062 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Exception -> 0x0062 }
            r8 = r6
            r6 = r3
            r3 = r8
            goto L_0x002d
        L_0x002c:
            r6 = r5
        L_0x002d:
            java.net.InetAddress r3 = java.net.InetAddress.parseNumericAddress(r3)     // Catch:{ Exception -> 0x0062 }
            byte[] r3 = r3.getAddress()     // Catch:{ Exception -> 0x0062 }
            r7 = 3
            byte r7 = r3[r7]     // Catch:{ Exception -> 0x0062 }
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r4 = r3[r4]     // Catch:{ Exception -> 0x0062 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 8
            r4 = r4 | r7
            byte r2 = r3[r2]     // Catch:{ Exception -> 0x0062 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 16
            r2 = r2 | r4
            byte r4 = r3[r9]     // Catch:{ Exception -> 0x0062 }
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << 24
            r2 = r2 | r4
            int r3 = r3.length     // Catch:{ Exception -> 0x0062 }
            r4 = 4
            if (r3 != r4) goto L_0x0060
            if (r6 < 0) goto L_0x0060
            if (r6 > r5) goto L_0x0060
            if (r6 >= r5) goto L_0x005d
            int r2 = r2 << r6
            if (r2 == 0) goto L_0x005d
            goto L_0x0060
        L_0x005d:
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0060:
            return r9
        L_0x0061:
            return r2
        L_0x0062:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.vpn2.ConfigDialog.validateAddresses(java.lang.String, boolean):boolean");
    }

    private void loadCertificates(Spinner spinner, String str, int i, String str2) {
        String str3;
        String[] strArr;
        Context context = getContext();
        if (i == 0) {
            str3 = "";
        } else {
            str3 = context.getString(i);
        }
        String[] list = this.mKeyStore.list(str);
        if (list == null || list.length == 0) {
            strArr = new String[]{str3};
        } else {
            strArr = new String[(list.length + 1)];
            strArr[0] = str3;
            System.arraycopy(list, 0, strArr, 1, list.length);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367048, strArr);
        arrayAdapter.setDropDownViewResource(17367049);
        spinner.setAdapter(arrayAdapter);
        for (int i2 = 1; i2 < strArr.length; i2++) {
            if (strArr[i2].equals(str2)) {
                spinner.setSelection(i2);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isEditing() {
        return this.mEditing;
    }

    /* access modifiers changed from: package-private */
    public boolean hasProxy() {
        return this.mProxySettings.getSelectedItemPosition() == 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00c0, code lost:
        if (r1 != 5) goto L_0x0133;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.internal.net.VpnProfile getProfile() {
        /*
            r6 = this;
            com.android.internal.net.VpnProfile r0 = new com.android.internal.net.VpnProfile
            com.android.internal.net.VpnProfile r1 = r6.mProfile
            java.lang.String r1 = r1.key
            r0.<init>(r1)
            android.widget.TextView r1 = r6.mName
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.name = r1
            android.widget.Spinner r1 = r6.mType
            int r1 = r1.getSelectedItemPosition()
            r0.type = r1
            android.widget.TextView r1 = r6.mServer
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            r0.server = r1
            android.widget.TextView r1 = r6.mUsername
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.username = r1
            android.widget.TextView r1 = r6.mPassword
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.password = r1
            android.widget.TextView r1 = r6.mSearchDomains
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            r0.searchDomains = r1
            android.widget.TextView r1 = r6.mDnsServers
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            r0.dnsServers = r1
            android.widget.TextView r1 = r6.mRoutes
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            r0.routes = r1
            boolean r1 = r6.hasProxy()
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x00ad
            android.widget.TextView r1 = r6.mProxyHost
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.trim()
            android.widget.TextView r4 = r6.mProxyPort
            java.lang.CharSequence r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.trim()
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x00a1
            r4 = r3
            goto L_0x00a5
        L_0x00a1:
            int r4 = java.lang.Integer.parseInt(r4)
        L_0x00a5:
            android.net.ProxyInfo r5 = new android.net.ProxyInfo
            r5.<init>(r1, r4, r2)
            r0.proxy = r5
            goto L_0x00af
        L_0x00ad:
            r0.proxy = r2
        L_0x00af:
            int r1 = r0.type
            r2 = 1
            if (r1 == 0) goto L_0x012b
            if (r1 == r2) goto L_0x0106
            r4 = 2
            if (r1 == r4) goto L_0x00c3
            r4 = 3
            if (r1 == r4) goto L_0x0112
            r4 = 4
            if (r1 == r4) goto L_0x00cf
            r4 = 5
            if (r1 == r4) goto L_0x00e1
            goto L_0x0133
        L_0x00c3:
            android.widget.TextView r1 = r6.mL2tpSecret
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.l2tpSecret = r1
        L_0x00cf:
            android.widget.Spinner r1 = r6.mIpsecUserCert
            int r1 = r1.getSelectedItemPosition()
            if (r1 == 0) goto L_0x00e1
            android.widget.Spinner r1 = r6.mIpsecUserCert
            java.lang.Object r1 = r1.getSelectedItem()
            java.lang.String r1 = (java.lang.String) r1
            r0.ipsecUserCert = r1
        L_0x00e1:
            android.widget.Spinner r1 = r6.mIpsecCaCert
            int r1 = r1.getSelectedItemPosition()
            if (r1 == 0) goto L_0x00f3
            android.widget.Spinner r1 = r6.mIpsecCaCert
            java.lang.Object r1 = r1.getSelectedItem()
            java.lang.String r1 = (java.lang.String) r1
            r0.ipsecCaCert = r1
        L_0x00f3:
            android.widget.Spinner r1 = r6.mIpsecServerCert
            int r1 = r1.getSelectedItemPosition()
            if (r1 == 0) goto L_0x0133
            android.widget.Spinner r1 = r6.mIpsecServerCert
            java.lang.Object r1 = r1.getSelectedItem()
            java.lang.String r1 = (java.lang.String) r1
            r0.ipsecServerCert = r1
            goto L_0x0133
        L_0x0106:
            android.widget.TextView r1 = r6.mL2tpSecret
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.l2tpSecret = r1
        L_0x0112:
            android.widget.TextView r1 = r6.mIpsecIdentifier
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.ipsecIdentifier = r1
            android.widget.TextView r1 = r6.mIpsecSecret
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            r0.ipsecSecret = r1
            goto L_0x0133
        L_0x012b:
            android.widget.CheckBox r1 = r6.mMppe
            boolean r1 = r1.isChecked()
            r0.mppe = r1
        L_0x0133:
            java.lang.String r1 = r0.username
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0146
            java.lang.String r1 = r0.password
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0144
            goto L_0x0146
        L_0x0144:
            r1 = r3
            goto L_0x0147
        L_0x0146:
            r1 = r2
        L_0x0147:
            android.widget.CheckBox r4 = r6.mSaveLogin
            boolean r4 = r4.isChecked()
            if (r4 != 0) goto L_0x0157
            boolean r6 = r6.mEditing
            if (r6 == 0) goto L_0x0156
            if (r1 == 0) goto L_0x0156
            goto L_0x0157
        L_0x0156:
            r2 = r3
        L_0x0157:
            r0.saveLogin = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.vpn2.ConfigDialog.getProfile():com.android.internal.net.VpnProfile");
    }

    private boolean validateProxy() {
        if (hasProxy() && Proxy.validate(this.mProxyHost.getText().toString().trim(), this.mProxyPort.getText().toString().trim(), "") != 0) {
            return false;
        }
        return true;
    }
}
