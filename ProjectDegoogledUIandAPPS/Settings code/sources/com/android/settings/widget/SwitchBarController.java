package com.android.settings.widget;

import android.widget.Switch;
import com.android.settings.widget.SwitchBar;
import com.android.settings.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtils;

public class SwitchBarController extends SwitchWidgetController implements SwitchBar.OnSwitchChangeListener {
    private final SwitchBar mSwitchBar;

    public SwitchBarController(SwitchBar switchBar) {
        this.mSwitchBar = switchBar;
    }

    public void setupView() {
        this.mSwitchBar.show();
    }

    public void teardownView() {
        this.mSwitchBar.hide();
    }

    public void updateTitle(boolean z) {
        this.mSwitchBar.setTextViewLabelAndBackground(z);
    }

    public void startListening() {
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    public void stopListening() {
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    public void setChecked(boolean z) {
        this.mSwitchBar.setChecked(z);
    }

    public boolean isChecked() {
        return this.mSwitchBar.isChecked();
    }

    public void setEnabled(boolean z) {
        this.mSwitchBar.setEnabled(z);
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener = this.mListener;
        if (onSwitchChangeListener != null) {
            onSwitchChangeListener.onSwitchToggled(z);
        }
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mSwitchBar.setDisabledByAdmin(enforcedAdmin);
    }
}
