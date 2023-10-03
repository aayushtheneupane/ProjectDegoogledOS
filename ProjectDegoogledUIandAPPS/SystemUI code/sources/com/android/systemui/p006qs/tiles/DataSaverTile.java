package com.android.systemui.p006qs.tiles;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Switch;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.Prefs;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.p005qs.QSTile;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.NetworkController;

/* renamed from: com.android.systemui.qs.tiles.DataSaverTile */
public class DataSaverTile extends QSTileImpl<QSTile.BooleanState> implements DataSaverController.Listener {
    private final DataSaverController mDataSaverController;
    private final KeyguardMonitor mKeyguard;
    private final KeyguardCallback mKeyguardCallback = new KeyguardCallback();

    public int getMetricsCategory() {
        return 284;
    }

    public DataSaverTile(QSHost qSHost, NetworkController networkController) {
        super(qSHost);
        this.mDataSaverController = networkController.getDataSaverController();
        this.mDataSaverController.observe(getLifecycle(), this);
        this.mKeyguard = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
    }

    public QSTile.BooleanState newTileState() {
        return new QSTile.BooleanState();
    }

    public void handleSetListening(boolean z) {
        if (z) {
            this.mKeyguard.addCallback(this.mKeyguardCallback);
        } else {
            this.mKeyguard.removeCallback(this.mKeyguardCallback);
        }
    }

    public Intent getLongClickIntent() {
        return new Intent("android.settings.DATA_SAVER_SETTINGS");
    }

    private void handleClickInner() {
        if (((QSTile.BooleanState) this.mState).value || Prefs.getBoolean(this.mContext, "QsDataSaverDialogShown", false)) {
            toggleDataSaver();
            return;
        }
        SystemUIDialog systemUIDialog = new SystemUIDialog(this.mContext);
        systemUIDialog.setTitle(17039849);
        systemUIDialog.setMessage(17039847);
        systemUIDialog.setPositiveButton(17039848, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                DataSaverTile.this.lambda$handleClickInner$0$DataSaverTile(dialogInterface, i);
            }
        });
        systemUIDialog.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        systemUIDialog.setShowForAllUsers(true);
        systemUIDialog.show();
    }

    public /* synthetic */ void lambda$handleClickInner$0$DataSaverTile(DialogInterface dialogInterface, int i) {
        toggleDataSaver();
        Prefs.putBoolean(this.mContext, "QsDataSaverDialogShown", true);
    }

    /* access modifiers changed from: protected */
    public void handleClick() {
        if (!this.mKeyguard.isSecure() || !this.mKeyguard.isShowing()) {
            handleClickInner();
        } else {
            ((ActivityStarter) Dependency.get(ActivityStarter.class)).postQSRunnableDismissingKeyguard(new Runnable() {
                public final void run() {
                    DataSaverTile.this.lambda$handleClick$1$DataSaverTile();
                }
            });
        }
    }

    public /* synthetic */ void lambda$handleClick$1$DataSaverTile() {
        this.mHost.openPanels();
        handleClickInner();
    }

    private void toggleDataSaver() {
        ((QSTile.BooleanState) this.mState).value = !this.mDataSaverController.isDataSaverEnabled();
        this.mDataSaverController.setDataSaverEnabled(((QSTile.BooleanState) this.mState).value);
        refreshState(Boolean.valueOf(((QSTile.BooleanState) this.mState).value));
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.data_saver);
    }

    /* access modifiers changed from: protected */
    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        boolean z;
        int i;
        if (obj instanceof Boolean) {
            z = ((Boolean) obj).booleanValue();
        } else {
            z = this.mDataSaverController.isDataSaverEnabled();
        }
        booleanState.value = z;
        booleanState.state = booleanState.value ? 2 : 1;
        booleanState.label = this.mContext.getString(C1784R$string.data_saver);
        booleanState.contentDescription = booleanState.label;
        if (booleanState.value) {
            i = C1776R$drawable.ic_data_saver;
        } else {
            i = C1776R$drawable.ic_data_saver_off;
        }
        booleanState.icon = QSTileImpl.ResourceIcon.get(i);
        booleanState.expandedAccessibilityClassName = Switch.class.getName();
    }

    /* access modifiers changed from: protected */
    public String composeChangeAnnouncement() {
        if (((QSTile.BooleanState) this.mState).value) {
            return this.mContext.getString(C1784R$string.accessibility_quick_settings_data_saver_changed_on);
        }
        return this.mContext.getString(C1784R$string.accessibility_quick_settings_data_saver_changed_off);
    }

    public void onDataSaverChanged(boolean z) {
        refreshState(Boolean.valueOf(z));
    }

    /* renamed from: com.android.systemui.qs.tiles.DataSaverTile$KeyguardCallback */
    private final class KeyguardCallback implements KeyguardMonitor.Callback {
        private KeyguardCallback() {
        }

        public void onKeyguardShowingChanged() {
            DataSaverTile.this.refreshState();
        }
    }
}
