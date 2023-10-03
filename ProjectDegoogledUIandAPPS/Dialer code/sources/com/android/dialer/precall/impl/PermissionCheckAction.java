package com.android.dialer.precall.impl;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.precall.PreCallAction;
import com.android.dialer.precall.PreCallCoordinator;
import com.android.dialer.telecom.TelecomUtil;

public class PermissionCheckAction implements PreCallAction {
    public void onDiscard() {
    }

    public boolean requiresUi(Context context, CallIntentBuilder callIntentBuilder) {
        return !TelecomUtil.hasCallPhonePermission(context);
    }

    public void runWithUi(PreCallCoordinator preCallCoordinator) {
        PreCallCoordinatorImpl preCallCoordinatorImpl = (PreCallCoordinatorImpl) preCallCoordinator;
        Activity activity = preCallCoordinatorImpl.getActivity();
        preCallCoordinatorImpl.getBuilder();
        if (!TelecomUtil.hasCallPhonePermission(activity)) {
            Toast.makeText(preCallCoordinatorImpl.getActivity(), preCallCoordinatorImpl.getActivity().getString(R.string.pre_call_permission_check_no_phone_permission), 1).show();
            preCallCoordinatorImpl.abortCall();
        }
    }

    public void runWithoutUi(Context context, CallIntentBuilder callIntentBuilder) {
    }
}
