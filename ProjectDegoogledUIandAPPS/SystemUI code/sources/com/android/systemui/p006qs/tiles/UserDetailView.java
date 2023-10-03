package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.RestrictedLockUtils;
import com.android.systemui.C1779R$layout;
import com.android.systemui.p006qs.PseudoGridView;
import com.android.systemui.statusbar.policy.UserSwitcherController;

/* renamed from: com.android.systemui.qs.tiles.UserDetailView */
public class UserDetailView extends PseudoGridView {
    protected Adapter mAdapter;

    public UserDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static UserDetailView inflate(Context context, ViewGroup viewGroup, boolean z) {
        return (UserDetailView) LayoutInflater.from(context).inflate(C1779R$layout.qs_user_detail, viewGroup, z);
    }

    public void createAndSetAdapter(UserSwitcherController userSwitcherController) {
        this.mAdapter = new Adapter(this.mContext, userSwitcherController);
        PseudoGridView.ViewGroupAdapterBridge.link(this, this.mAdapter);
    }

    public void refreshAdapter() {
        this.mAdapter.refresh();
    }

    /* renamed from: com.android.systemui.qs.tiles.UserDetailView$Adapter */
    public static class Adapter extends UserSwitcherController.BaseUserAdapter implements View.OnClickListener {
        private final Context mContext;
        protected UserSwitcherController mController;

        public Adapter(Context context, UserSwitcherController userSwitcherController) {
            super(userSwitcherController);
            this.mContext = context;
            this.mController = userSwitcherController;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return createUserDetailItemView(view, viewGroup, getItem(i));
        }

        public UserDetailItemView createUserDetailItemView(View view, ViewGroup viewGroup, UserSwitcherController.UserRecord userRecord) {
            UserDetailItemView convertOrInflate = UserDetailItemView.convertOrInflate(this.mContext, view, viewGroup);
            if (!userRecord.isCurrent || userRecord.isGuest) {
                convertOrInflate.setOnClickListener(this);
            } else {
                convertOrInflate.setOnClickListener((View.OnClickListener) null);
                convertOrInflate.setClickable(false);
            }
            String name = getName(this.mContext, userRecord);
            Bitmap bitmap = userRecord.picture;
            if (bitmap == null) {
                convertOrInflate.bind(name, getDrawable(this.mContext, userRecord), userRecord.resolveId());
            } else {
                convertOrInflate.bind(name, bitmap, userRecord.info.id);
            }
            convertOrInflate.setActivated(userRecord.isCurrent);
            convertOrInflate.setDisabledByAdmin(userRecord.isDisabledByAdmin);
            if (!userRecord.isSwitchToEnabled) {
                convertOrInflate.setEnabled(false);
            }
            convertOrInflate.setTag(userRecord);
            return convertOrInflate;
        }

        public void onClick(View view) {
            UserSwitcherController.UserRecord userRecord = (UserSwitcherController.UserRecord) view.getTag();
            if (userRecord.isDisabledByAdmin) {
                this.mController.startActivity(RestrictedLockUtils.getShowAdminSupportDetailsIntent(this.mContext, userRecord.enforcedAdmin));
            } else if (userRecord.isSwitchToEnabled) {
                MetricsLogger.action(this.mContext, 156);
                switchTo(userRecord);
            }
        }
    }
}
