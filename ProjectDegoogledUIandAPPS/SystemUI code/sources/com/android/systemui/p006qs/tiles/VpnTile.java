package com.android.systemui.p006qs.tiles;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.internal.net.VpnConfig;
import com.android.internal.net.VpnProfile;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.SecurityController;
import java.util.List;

/* renamed from: com.android.systemui.qs.tiles.VpnTile */
public class VpnTile extends QSTileImpl<QSTile.BooleanState> {
    private final ActivityStarter mActivityStarter = ((ActivityStarter) Dependency.get(ActivityStarter.class));
    private final Callback mCallback = new Callback();
    private final SecurityController mController = ((SecurityController) Dependency.get(SecurityController.class));
    private final KeyguardMonitor mKeyguard = ((KeyguardMonitor) Dependency.get(KeyguardMonitor.class));
    private final UnlockMethodCache mUnlockMethodCache = UnlockMethodCache.getInstance(this.mHost.getContext());

    public int getMetricsCategory() {
        return 100;
    }

    public VpnTile(QSHost qSHost) {
        super(qSHost);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (QSTileImpl.DEBUG) {
            String str = this.TAG;
            Log.d(str, "handleSetListening " + z);
        }
        if (z) {
            this.mController.addCallback(this.mCallback);
            this.mKeyguard.addCallback(this.mCallback);
            return;
        }
        this.mController.removeCallback(this.mCallback);
        this.mKeyguard.removeCallback(this.mCallback);
    }

    /* access modifiers changed from: protected */
    public void handleUserSwitch(int i) {
        super.handleUserSwitch(i);
        this.mController.onUserSwitched(i);
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.VPN_SETTINGS");
    }

    /* access modifiers changed from: protected */
    public void handleSecondaryClick() {
        handleClick();
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!this.mKeyguard.isSecure() || this.mUnlockMethodCache.canSkipBouncer()) {
            lambda$handleClick$0$VpnTile();
        } else {
            this.mActivityStarter.postQSRunnableDismissingKeyguard(new Runnable() {
                public final void run() {
                    VpnTile.this.lambda$handleClick$0$VpnTile();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showConnectDialogOrDisconnect */
    public void lambda$handleClick$0$VpnTile() {
        if (!this.mController.isVpnRestricted()) {
            if (this.mController.isVpnEnabled()) {
                this.mController.disconnectPrimaryVpn();
                return;
            }
            List<VpnProfile> configuredLegacyVpns = this.mController.getConfiguredLegacyVpns();
            List<String> vpnAppPackageNames = this.mController.getVpnAppPackageNames();
            if (configuredLegacyVpns.isEmpty() && vpnAppPackageNames.isEmpty()) {
                return;
            }
            if (configuredLegacyVpns.isEmpty() && vpnAppPackageNames.size() == 1) {
                this.mController.launchVpnApp(vpnAppPackageNames.get(0));
            } else if (!vpnAppPackageNames.isEmpty() || configuredLegacyVpns.size() != 1) {
                this.mUiHandler.post(new Runnable(configuredLegacyVpns, vpnAppPackageNames) {
                    private final /* synthetic */ List f$1;
                    private final /* synthetic */ List f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        VpnTile.this.lambda$showConnectDialogOrDisconnect$2$VpnTile(this.f$1, this.f$2);
                    }
                });
            } else {
                connectVpnOrAskForCredentials(configuredLegacyVpns.get(0));
            }
        }
    }

    public /* synthetic */ void lambda$showConnectDialogOrDisconnect$2$VpnTile(List list, List list2) {
        CharSequence[] charSequenceArr = new CharSequence[(list.size() + list2.size())];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            charSequenceArr[i] = ((VpnProfile) list.get(i)).name;
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            int i3 = size + i2;
            try {
                charSequenceArr[i3] = VpnConfig.getVpnLabel(this.mContext, (String) list2.get(i2));
            } catch (PackageManager.NameNotFoundException unused) {
                charSequenceArr[i3] = (CharSequence) list2.get(i2);
            }
        }
        prepareAndShowDialog(new AlertDialog.Builder(this.mContext).setTitle(C1784R$string.quick_settings_vpn_connect_dialog_title).setItems(charSequenceArr, new DialogInterface.OnClickListener(size, list, list2) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ List f$2;
            private final /* synthetic */ List f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                VpnTile.this.lambda$showConnectDialogOrDisconnect$1$VpnTile(this.f$1, this.f$2, this.f$3, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create());
        this.mHost.collapsePanels();
    }

    public /* synthetic */ void lambda$showConnectDialogOrDisconnect$1$VpnTile(int i, List list, List list2, DialogInterface dialogInterface, int i2) {
        if (i2 < i) {
            connectVpnOrAskForCredentials((VpnProfile) list.get(i2));
        } else {
            this.mController.launchVpnApp((String) list2.get(i2 - i));
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_vpn_label);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_vpn_label);
        booleanState.value = this.mController.isVpnEnabled();
        booleanState.secondaryLabel = this.mController.getPrimaryVpnName();
        booleanState.contentDescription = booleanState.label;
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_vpn);
        boolean z = this.mController.getConfiguredLegacyVpns().size() > 0 || this.mController.getVpnAppPackageNames().size() > 0;
        if (this.mController.isVpnRestricted() || !z) {
            booleanState.state = 0;
        } else if (booleanState.value) {
            booleanState.state = 2;
        } else {
            booleanState.state = 1;
        }
    }

    private void connectVpnOrAskForCredentials(VpnProfile vpnProfile) {
        if (vpnProfile.saveLogin) {
            this.mController.connectLegacyVpn(vpnProfile);
            return;
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(C1779R$layout.vpn_credentials_dialog, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(C1777R$id.username);
        EditText editText2 = (EditText) inflate.findViewById(C1777R$id.password);
        ((TextView) inflate.findViewById(C1777R$id.hint)).setText(this.mContext.getString(C1784R$string.vpn_credentials_hint, new Object[]{vpnProfile.name}));
        AlertDialog create = new AlertDialog.Builder(this.mContext).setView(inflate).setPositiveButton(C1784R$string.vpn_credentials_dialog_connect, new DialogInterface.OnClickListener(vpnProfile, editText, editText2) {
            private final /* synthetic */ VpnProfile f$1;
            private final /* synthetic */ EditText f$2;
            private final /* synthetic */ EditText f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                VpnTile.this.lambda$connectVpnOrAskForCredentials$3$VpnTile(this.f$1, this.f$2, this.f$3, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
        prepareAndShowDialog(create);
        this.mHost.collapsePanels();
        this.mUiHandler.post(new Runnable(editText, editText2, create) {
            private final /* synthetic */ EditText f$0;
            private final /* synthetic */ EditText f$1;
            private final /* synthetic */ AlertDialog f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                VpnTile.lambda$connectVpnOrAskForCredentials$4(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$connectVpnOrAskForCredentials$3$VpnTile(VpnProfile vpnProfile, EditText editText, EditText editText2, DialogInterface dialogInterface, int i) {
        vpnProfile.username = editText.getText().toString();
        vpnProfile.password = editText2.getText().toString();
        this.mController.connectLegacyVpn(vpnProfile);
    }

    static /* synthetic */ void lambda$connectVpnOrAskForCredentials$4(EditText editText, EditText editText2, AlertDialog alertDialog) {
        UsernameAndPasswordWatcher usernameAndPasswordWatcher = new UsernameAndPasswordWatcher(editText, editText2, alertDialog.getButton(-1));
        editText.addTextChangedListener(usernameAndPasswordWatcher);
        editText2.addTextChangedListener(usernameAndPasswordWatcher);
    }

    private void prepareAndShowDialog(Dialog dialog) {
        dialog.getWindow().setType(2009);
        SystemUIDialog.setShowForAllUsers(dialog, true);
        SystemUIDialog.registerDismissListener(dialog);
        SystemUIDialog.setWindowOnTop(dialog);
        this.mUiHandler.post(new Runnable(dialog) {
            private final /* synthetic */ Dialog f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.show();
            }
        });
    }

    /* renamed from: com.android.systemui.qs.tiles.VpnTile$UsernameAndPasswordWatcher */
    private static final class UsernameAndPasswordWatcher implements TextWatcher {
        private final Button mOkButton;
        private final EditText mPasswordEditor;
        private final EditText mUserNameEditor;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public UsernameAndPasswordWatcher(EditText editText, EditText editText2, Button button) {
            this.mUserNameEditor = editText;
            this.mPasswordEditor = editText2;
            this.mOkButton = button;
            updateOkButtonState();
        }

        public void afterTextChanged(Editable editable) {
            updateOkButtonState();
        }

        private void updateOkButtonState() {
            this.mOkButton.setEnabled(this.mUserNameEditor.getText().length() > 0 && this.mPasswordEditor.getText().length() > 0);
        }
    }

    /* renamed from: com.android.systemui.qs.tiles.VpnTile$Callback */
    private final class Callback implements SecurityController.SecurityControllerCallback, KeyguardMonitor.Callback {
        private Callback() {
        }

        public void onStateChanged() {
            VpnTile.this.refreshState();
        }

        public void onKeyguardShowingChanged() {
            VpnTile.this.refreshState();
        }
    }
}
