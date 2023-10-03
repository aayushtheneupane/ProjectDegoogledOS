package com.android.incallui.video.impl;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import android.support.p000v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class VideoChargesAlertDialogFragment extends DialogFragment {
    static final String KEY_DO_NOT_SHOW_VIDEO_CHARGES_ALERT = "key_do_not_show_video_charges_alert";

    public static boolean shouldShow(Context context, String str) {
        DialerCall callById = CallList.getInstance().getCallById(str);
        if (callById == null) {
            LogUtil.m9i("VideoChargesAlertDialogFragment.shouldShow", "null call", new Object[0]);
            return false;
        } else if (callById.hasProperty(8)) {
            return false;
        } else {
            if (callById.didDismissVideoChargesAlertDialog()) {
                LogUtil.m9i("VideoChargesAlertDialogFragment.shouldShow", "The dialog has been dismissed by user", new Object[0]);
                return false;
            } else if (!callById.showVideoChargesAlertDialog()) {
                return false;
            } else {
                if (!R$dimen.isUserUnlocked(context)) {
                    LogUtil.m9i("VideoChargesAlertDialogFragment.shouldShow", "user locked, returning false", new Object[0]);
                    return false;
                } else if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_DO_NOT_SHOW_VIDEO_CHARGES_ALERT, false)) {
                    return true;
                } else {
                    LogUtil.m9i("VideoChargesAlertDialogFragment.shouldShow", "Video charges alert has been disabled by user, returning false", new Object[0]);
                    return false;
                }
            }
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$0$VideoChargesAlertDialogFragment(SharedPreferences sharedPreferences, CheckBox checkBox, DialogInterface dialogInterface, int i) {
        boolean isChecked = checkBox.isChecked();
        LogUtil.m9i("VideoChargesAlertDialogFragment.onPositiveButtonClicked", "isChecked: %b", Boolean.valueOf(isChecked));
        sharedPreferences.edit().putBoolean(KEY_DO_NOT_SHOW_VIDEO_CHARGES_ALERT, isChecked).apply();
        DialerCall callById = CallList.getInstance().getCallById(getArguments().getString("call_id"));
        if (callById != null) {
            callById.setDidDismissVideoChargesAlertDialog(true);
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        if (shouldShow(getActivity(), getArguments().getString("call_id"))) {
            View inflate = View.inflate(getActivity(), R.layout.frag_video_charges_alert_dialog, (ViewGroup) null);
            AlertDialog create = new AlertDialog.Builder(getActivity()).setView(inflate).setPositiveButton(17039370, new DialogInterface.OnClickListener(PreferenceManager.getDefaultSharedPreferences(getActivity()), (CheckBox) inflate.findViewById(R.id.do_not_show)) {
                private final /* synthetic */ SharedPreferences f$1;
                private final /* synthetic */ CheckBox f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    VideoChargesAlertDialogFragment.this.lambda$onCreateDialog$0$VideoChargesAlertDialogFragment(this.f$1, this.f$2, dialogInterface, i);
                }
            }).create();
            setCancelable(false);
            return create;
        }
        throw new IllegalStateException("shouldShow indicated VideoChargesAlertDialogFragment should not have showed");
    }
}
