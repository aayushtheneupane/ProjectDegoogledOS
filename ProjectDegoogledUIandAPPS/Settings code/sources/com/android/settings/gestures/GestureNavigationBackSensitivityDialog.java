package com.android.settings.gestures;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.om.IOverlayManager;
import android.os.Bundle;
import android.os.ServiceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.havoc.config.center.C1715R;

public class GestureNavigationBackSensitivityDialog extends InstrumentedDialogFragment {
    public int getMetricsCategory() {
        return 1748;
    }

    public static void show(SystemNavigationGestureSettings systemNavigationGestureSettings, int i, int i2, int i3) {
        if (systemNavigationGestureSettings.isAdded()) {
            GestureNavigationBackSensitivityDialog gestureNavigationBackSensitivityDialog = new GestureNavigationBackSensitivityDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("back_sensitivity", i);
            bundle.putInt("back_height", i2);
            bundle.putInt("home_handle_width", i3);
            gestureNavigationBackSensitivityDialog.setArguments(bundle);
            gestureNavigationBackSensitivityDialog.setTargetFragment(systemNavigationGestureSettings, 0);
            gestureNavigationBackSensitivityDialog.show(systemNavigationGestureSettings.getFragmentManager(), "GestureNavigationBackSensitivityDialog");
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = getActivity().getLayoutInflater().inflate(C1715R.layout.dialog_back_gesture_options, (ViewGroup) null);
        SeekBar seekBar = (SeekBar) inflate.findViewById(C1715R.C1718id.back_sensitivity_seekbar);
        seekBar.setProgress(getArguments().getInt("back_sensitivity"));
        SeekBar seekBar2 = (SeekBar) inflate.findViewById(C1715R.C1718id.back_height_seekbar);
        seekBar2.setProgress(getArguments().getInt("back_height"));
        SeekBar seekBar3 = (SeekBar) inflate.findViewById(C1715R.C1718id.home_handle_seekbar);
        seekBar3.setProgress(getArguments().getInt("home_handle_width"));
        return new AlertDialog.Builder(getContext()).setTitle(C1715R.string.back_options_dialog_title).setMessage(C1715R.string.back_sensitivity_dialog_message).setView(inflate).setPositiveButton(C1715R.string.okay, new DialogInterface.OnClickListener(seekBar, seekBar2, seekBar3) {
            private final /* synthetic */ SeekBar f$1;
            private final /* synthetic */ SeekBar f$2;
            private final /* synthetic */ SeekBar f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                GestureNavigationBackSensitivityDialog.this.lambda$onCreateDialog$0$GestureNavigationBackSensitivityDialog(this.f$1, this.f$2, this.f$3, dialogInterface, i);
            }
        }).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$GestureNavigationBackSensitivityDialog(SeekBar seekBar, SeekBar seekBar2, SeekBar seekBar3, DialogInterface dialogInterface, int i) {
        int progress = seekBar.getProgress();
        getArguments().putInt("back_sensitivity", progress);
        int progress2 = seekBar2.getProgress();
        getArguments().putInt("back_height", progress2);
        int progress3 = seekBar3.getProgress();
        getArguments().putInt("home_handle_width", progress3);
        SystemNavigationGestureSettings.setBackHeight(getActivity(), progress2);
        SystemNavigationGestureSettings.setBackSensitivity(getActivity(), getOverlayManager(), progress);
        SystemNavigationGestureSettings.setHomeHandleSize(getActivity(), progress3);
    }

    private IOverlayManager getOverlayManager() {
        return IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
    }
}
