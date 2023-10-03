package com.google.android.systemui.smartspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import com.google.android.systemui.smartspace.nano.SmartspaceProto;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;

public class SmartSpaceBroadcastReceiver extends BroadcastReceiver {
    private final SmartSpaceController mController;

    public SmartSpaceBroadcastReceiver(SmartSpaceController smartSpaceController) {
        this.mController = smartSpaceController;
    }

    public void onReceive(Context context, Intent intent) {
        if (SmartSpaceController.DEBUG) {
            Log.d("SmartSpaceReceiver", "receiving update");
        }
        int myUserId = UserHandle.myUserId();
        if (myUserId == 0) {
            if (!intent.hasExtra("uid")) {
                intent.putExtra("uid", myUserId);
            }
            byte[] byteArrayExtra = intent.getByteArrayExtra("com.google.android.apps.nexuslauncher.extra.SMARTSPACE_CARD");
            if (byteArrayExtra != null) {
                SmartspaceProto.SmartspaceUpdate smartspaceUpdate = new SmartspaceProto.SmartspaceUpdate();
                try {
                    MessageNano.mergeFrom(smartspaceUpdate, byteArrayExtra);
                    for (SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard : smartspaceUpdate.card) {
                        boolean z = smartspaceCard.cardPriority == 1;
                        boolean z2 = smartspaceCard.cardPriority == 2;
                        if (!z && !z2) {
                            Log.w("SmartSpaceReceiver", "unrecognized card priority: " + smartspaceCard.cardPriority);
                        }
                        notify(smartspaceCard, context, intent, z);
                    }
                } catch (InvalidProtocolBufferNanoException e) {
                    Log.e("SmartSpaceReceiver", "proto", e);
                }
            } else {
                Log.e("SmartSpaceReceiver", "receiving update with no proto: " + intent.getExtras());
            }
        } else if (!intent.getBooleanExtra("rebroadcast", false)) {
            intent.putExtra("rebroadcast", true);
            intent.putExtra("uid", myUserId);
            context.sendBroadcastAsUser(intent, UserHandle.ALL);
        }
    }

    private void notify(SmartspaceProto.SmartspaceUpdate.SmartspaceCard smartspaceCard, Context context, Intent intent, boolean z) {
        PackageInfo packageInfo;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.google.android.googlequicksearchbox", 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("SmartSpaceReceiver", "Cannot find GSA", e);
            packageInfo = null;
        }
        this.mController.onNewCard(new NewCardInfo(smartspaceCard, intent, z, currentTimeMillis, packageInfo));
    }
}
