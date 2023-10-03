package com.android.settings.custom.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;

public class CustomDialogPreference<T extends DialogInterface> extends DialogPreference {
    private CustomPreferenceDialogFragment mFragment;

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
    }

    /* access modifiers changed from: protected */
    public void onClick(T t, int i) {
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    public View onCreateDialogView(Context context) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean onDismissDialog(T t, int i) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onStop() {
    }

    public CustomDialogPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public CustomDialogPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CustomDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomDialogPreference(Context context) {
        super(context);
    }

    public T getDialog() {
        CustomPreferenceDialogFragment customPreferenceDialogFragment = this.mFragment;
        if (customPreferenceDialogFragment != null) {
            return customPreferenceDialogFragment.getDialog();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void setFragment(CustomPreferenceDialogFragment customPreferenceDialogFragment) {
        this.mFragment = customPreferenceDialogFragment;
    }

    public static class CustomPreferenceDialogFragment extends PreferenceDialogFragmentCompat {
        public static CustomPreferenceDialogFragment newInstance(String str) {
            CustomPreferenceDialogFragment customPreferenceDialogFragment = new CustomPreferenceDialogFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("key", str);
            customPreferenceDialogFragment.setArguments(bundle);
            return customPreferenceDialogFragment;
        }

        /* access modifiers changed from: private */
        public CustomDialogPreference getCustomizablePreference() {
            return (CustomDialogPreference) getPreference();
        }

        private class OnDismissListener implements View.OnClickListener {
            private final DialogInterface mDialog;
            private final int mWhich;

            public OnDismissListener(DialogInterface dialogInterface, int i) {
                this.mWhich = i;
                this.mDialog = dialogInterface;
            }

            public void onClick(View view) {
                CustomPreferenceDialogFragment.this.onClick(this.mDialog, this.mWhich);
                if (CustomPreferenceDialogFragment.this.getCustomizablePreference().onDismissDialog(this.mDialog, this.mWhich)) {
                    this.mDialog.dismiss();
                }
            }
        }

        public void onStart() {
            super.onStart();
            if (getDialog() instanceof AlertDialog) {
                AlertDialog alertDialog = (AlertDialog) getDialog();
                if (alertDialog.getButton(-3) != null) {
                    alertDialog.getButton(-3).setOnClickListener(new OnDismissListener(alertDialog, -3));
                }
                if (alertDialog.getButton(-1) != null) {
                    alertDialog.getButton(-1).setOnClickListener(new OnDismissListener(alertDialog, -1));
                }
                if (alertDialog.getButton(-2) != null) {
                    alertDialog.getButton(-2).setOnClickListener(new OnDismissListener(alertDialog, -2));
                }
            }
            getCustomizablePreference().onStart();
        }

        public void onStop() {
            super.onStop();
            getCustomizablePreference().onStop();
        }

        public void onPause() {
            super.onPause();
            getCustomizablePreference().onPause();
        }

        public void onResume() {
            super.onResume();
            getCustomizablePreference().onResume();
        }

        /* access modifiers changed from: protected */
        public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
            super.onPrepareDialogBuilder(builder);
            getCustomizablePreference().setFragment(this);
            getCustomizablePreference().onPrepareDialogBuilder(builder, this);
        }

        public void onDialogClosed(boolean z) {
            getCustomizablePreference().onDialogClosed(z);
        }

        /* access modifiers changed from: protected */
        public void onBindDialogView(View view) {
            super.onBindDialogView(view);
            getCustomizablePreference().onBindDialogView(view);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            super.onClick(dialogInterface, i);
            getCustomizablePreference().onClick(dialogInterface, i);
        }

        public Dialog onCreateDialog(Bundle bundle) {
            getCustomizablePreference().setFragment(this);
            Dialog onCreateDialog = getCustomizablePreference().onCreateDialog(bundle);
            return onCreateDialog == null ? super.onCreateDialog(bundle) : onCreateDialog;
        }

        /* access modifiers changed from: protected */
        public View onCreateDialogView(Context context) {
            View onCreateDialogView = getCustomizablePreference().onCreateDialogView(context);
            return onCreateDialogView == null ? super.onCreateDialogView(context) : onCreateDialogView;
        }
    }
}
