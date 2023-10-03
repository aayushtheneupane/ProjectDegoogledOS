package com.android.systemui.statusbar;

import android.content.Context;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.util.FeatureFlagUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

public class MediaTransferManager {
    /* access modifiers changed from: private */
    public final ActivityStarter mActivityStarter;
    private final Context mContext;
    private final View.OnClickListener mOnClickHandler = new View.OnClickListener() {
        public void onClick(View view) {
            if (handleMediaTransfer(view)) {
            }
        }

        private boolean handleMediaTransfer(View view) {
            if (view.findViewById(16909103) == null) {
                return false;
            }
            MediaTransferManager.this.mActivityStarter.startActivity(new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").putExtra("com.android.settings.panel.extra.PACKAGE_NAME", getNotificationForParent(view.getParent()).getPackageName()), false, true, 268468224);
            return true;
        }

        private StatusBarNotification getNotificationForParent(ViewParent viewParent) {
            while (viewParent != null) {
                if (viewParent instanceof ExpandableNotificationRow) {
                    return ((ExpandableNotificationRow) viewParent).getStatusBarNotification();
                }
                viewParent = viewParent.getParent();
            }
            return null;
        }
    };

    public MediaTransferManager(Context context) {
        this.mContext = context;
        this.mActivityStarter = (ActivityStarter) Dependency.get(ActivityStarter.class);
    }

    public void applyMediaTransferView(ViewGroup viewGroup, NotificationEntry notificationEntry) {
        View findViewById;
        if (FeatureFlagUtils.isEnabled(this.mContext, "settings_seamless_transfer") && (findViewById = viewGroup.findViewById(16909103)) != null) {
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(this.mOnClickHandler);
        }
    }
}
