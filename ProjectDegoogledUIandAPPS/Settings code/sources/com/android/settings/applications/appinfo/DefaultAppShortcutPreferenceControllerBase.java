package com.android.settings.applications.appinfo;

import android.app.role.RoleControllerManager;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.CollectionUtils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public abstract class DefaultAppShortcutPreferenceControllerBase extends BasePreferenceController {
    private boolean mAppQualified;
    protected final String mPackageName;
    private PreferenceScreen mPreferenceScreen;
    private final RoleManager mRoleManager;
    private final String mRoleName;
    private boolean mRoleVisible;

    public DefaultAppShortcutPreferenceControllerBase(Context context, String str, String str2, String str3) {
        super(context, str);
        this.mRoleName = str2;
        this.mPackageName = str3;
        this.mRoleManager = (RoleManager) context.getSystemService(RoleManager.class);
        RoleControllerManager roleControllerManager = (RoleControllerManager) this.mContext.getSystemService(RoleControllerManager.class);
        Executor mainExecutor = this.mContext.getMainExecutor();
        roleControllerManager.isRoleVisible(this.mRoleName, mainExecutor, new Consumer() {
            public final void accept(Object obj) {
                DefaultAppShortcutPreferenceControllerBase.this.lambda$new$0$DefaultAppShortcutPreferenceControllerBase((Boolean) obj);
            }
        });
        roleControllerManager.isApplicationQualifiedForRole(this.mRoleName, this.mPackageName, mainExecutor, new Consumer() {
            public final void accept(Object obj) {
                DefaultAppShortcutPreferenceControllerBase.this.lambda$new$1$DefaultAppShortcutPreferenceControllerBase((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$DefaultAppShortcutPreferenceControllerBase(Boolean bool) {
        this.mRoleVisible = bool.booleanValue();
        refreshAvailability();
    }

    public /* synthetic */ void lambda$new$1$DefaultAppShortcutPreferenceControllerBase(Boolean bool) {
        this.mAppQualified = bool.booleanValue();
        refreshAvailability();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
    }

    private void refreshAvailability() {
        Preference findPreference;
        PreferenceScreen preferenceScreen = this.mPreferenceScreen;
        if (preferenceScreen != null && (findPreference = preferenceScreen.findPreference(getPreferenceKey())) != null) {
            findPreference.setVisible(isAvailable());
            updateState(findPreference);
        }
    }

    public int getAvailabilityStatus() {
        if (((UserManager) this.mContext.getSystemService(UserManager.class)).isManagedProfile()) {
            return 4;
        }
        return (!this.mRoleVisible || !this.mAppQualified) ? 3 : 0;
    }

    public CharSequence getSummary() {
        return this.mContext.getText(isDefaultApp() ? C1715R.string.yes : C1715R.string.f93no);
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(this.mPreferenceKey, preference.getKey())) {
            return false;
        }
        this.mContext.startActivity(new Intent("android.intent.action.MANAGE_DEFAULT_APP").putExtra("android.intent.extra.ROLE_NAME", this.mRoleName));
        return true;
    }

    private boolean isDefaultApp() {
        return TextUtils.equals(this.mPackageName, (String) CollectionUtils.firstOrNull(this.mRoleManager.getRoleHolders(this.mRoleName)));
    }
}
