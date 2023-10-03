package com.android.settings.display;

import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class DarkUIInfoDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    public int getMetricsCategory() {
        return 1740;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(builder.getContext()).inflate(C1715R.layout.settings_dialog_title, (ViewGroup) null);
        ((ImageView) inflate.findViewById(C1715R.C1718id.settings_icon)).setImageDrawable(context.getDrawable(C1715R.C1717drawable.dark_theme));
        ((TextView) inflate.findViewById(C1715R.C1718id.settings_title)).setText(C1715R.string.dark_ui_mode);
        builder.setCustomTitle(inflate);
        builder.setMessage((int) C1715R.string.dark_ui_settings_dark_summary);
        builder.setPositiveButton((int) C1715R.string.dark_ui_settings_dialog_acknowledge, (DialogInterface.OnClickListener) this);
        return builder.create();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        enableDarkTheme();
        super.onDismiss(dialogInterface);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        enableDarkTheme();
    }

    private void enableDarkTheme() {
        Context context = getContext();
        if (context != null) {
            Settings.Secure.putInt(context.getContentResolver(), DarkUIPreferenceController.PREF_DARK_MODE_DIALOG_SEEN, 1);
            ((UiModeManager) context.getSystemService(UiModeManager.class)).setNightMode(2);
        }
    }
}
