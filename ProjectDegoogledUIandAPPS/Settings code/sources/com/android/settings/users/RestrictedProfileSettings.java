package com.android.settings.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.settings.Utils;
import com.android.settings.users.EditUserInfoController;
import com.havoc.config.center.C1715R;

public class RestrictedProfileSettings extends AppRestrictionsFragment implements EditUserInfoController.OnContentChangedCallback {
    private ImageView mDeleteButton;
    private EditUserInfoController mEditUserInfoController = new EditUserInfoController();
    private View mHeaderView;
    private ImageView mUserIconView;
    private TextView mUserNameView;

    public int getDialogMetricsCategory(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 591;
        }
        return 590;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mEditUserInfoController.onRestoreInstanceState(bundle);
        }
        init(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        this.mHeaderView = setPinnedHeaderView((int) C1715R.layout.user_info_header);
        this.mHeaderView.setOnClickListener(this);
        this.mUserIconView = (ImageView) this.mHeaderView.findViewById(16908294);
        this.mUserNameView = (TextView) this.mHeaderView.findViewById(16908310);
        this.mDeleteButton = (ImageView) this.mHeaderView.findViewById(C1715R.C1718id.delete);
        this.mDeleteButton.setOnClickListener(this);
        super.onActivityCreated(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mEditUserInfoController.onSaveInstanceState(bundle);
    }

    public void onResume() {
        super.onResume();
        UserInfo existingUser = Utils.getExistingUser(this.mUserManager, this.mUser);
        if (existingUser == null) {
            finishFragment();
            return;
        }
        ((TextView) this.mHeaderView.findViewById(16908310)).setText(existingUser.name);
        ((ImageView) this.mHeaderView.findViewById(16908294)).setImageDrawable(com.android.settingslib.Utils.getUserIcon(getActivity(), this.mUserManager, existingUser));
    }

    public void startActivityForResult(Intent intent, int i) {
        this.mEditUserInfoController.startingActivityForResult();
        super.startActivityForResult(intent, i);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mEditUserInfoController.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        if (view == this.mHeaderView) {
            showDialog(1);
        } else if (view == this.mDeleteButton) {
            showDialog(2);
        } else {
            super.onClick(view);
        }
    }

    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            return this.mEditUserInfoController.createDialog(this, this.mUserIconView.getDrawable(), this.mUserNameView.getText(), C1715R.string.profile_info_settings_title, this, this.mUser);
        } else if (i == 2) {
            return UserDialogs.createRemoveDialog(getActivity(), this.mUser.getIdentifier(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    RestrictedProfileSettings.this.removeUser();
                }
            });
        } else {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void removeUser() {
        getView().post(new Runnable() {
            public void run() {
                RestrictedProfileSettings restrictedProfileSettings = RestrictedProfileSettings.this;
                restrictedProfileSettings.mUserManager.removeUser(restrictedProfileSettings.mUser.getIdentifier());
                RestrictedProfileSettings.this.finishFragment();
            }
        });
    }

    public void onPhotoChanged(Drawable drawable) {
        this.mUserIconView.setImageDrawable(drawable);
    }

    public void onLabelChanged(CharSequence charSequence) {
        this.mUserNameView.setText(charSequence);
    }
}
