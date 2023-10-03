package com.android.settings.privacy;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.dashboard.profileselector.UserAdapter;
import com.android.settings.utils.ContentCaptureUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public final class EnableContentCaptureWithServiceSettingsPreferenceController extends TogglePreferenceController {
    private static final String TAG = "ContentCaptureController";
    private final UserManager mUserManager;

    public EnableContentCaptureWithServiceSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mUserManager = UserManager.get(context);
    }

    public boolean isChecked() {
        return ContentCaptureUtils.isEnabledForUser(this.mContext);
    }

    public boolean setChecked(boolean z) {
        ContentCaptureUtils.setEnabledForUser(this.mContext, z);
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        ComponentName serviceSettingsComponentName = ContentCaptureUtils.getServiceSettingsComponentName();
        if (serviceSettingsComponentName != null) {
            preference.setIntent(new Intent("android.intent.action.MAIN").setComponent(serviceSettingsComponentName));
        } else {
            Log.w(TAG, "No component name for custom service settings");
            preference.setSelectable(false);
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return EnableContentCaptureWithServiceSettingsPreferenceController.this.mo11539xcf7101ac(preference);
            }
        });
    }

    /* renamed from: lambda$updateState$0$EnableContentCaptureWithServiceSettingsPreferenceController */
    public /* synthetic */ boolean mo11539xcf7101ac(Preference preference) {
        ProfileSelectDialog.show(this.mContext, preference);
        return true;
    }

    public int getAvailabilityStatus() {
        if (ContentCaptureUtils.isFeatureAvailable() && ContentCaptureUtils.getServiceSettingsComponentName() != null) {
            return 0;
        }
        return 3;
    }

    private static final class ProfileSelectDialog {
        public static void show(Context context, Preference preference) {
            UserManager userManager = UserManager.get(context);
            List<UserInfo> users = userManager.getUsers();
            ArrayList arrayList = new ArrayList(users.size());
            for (UserInfo userHandle : users) {
                arrayList.add(userHandle.getUserHandle());
            }
            if (arrayList.size() == 1) {
                context.startActivityAsUser(preference.getIntent().addFlags(32768), (UserHandle) arrayList.get(0));
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            UserAdapter createUserAdapter = UserAdapter.createUserAdapter(userManager, context, arrayList);
            builder.setTitle((int) C1715R.string.choose_profile);
            builder.setAdapter(createUserAdapter, new DialogInterface.OnClickListener(arrayList, preference, context) {
                private final /* synthetic */ ArrayList f$0;
                private final /* synthetic */ Preference f$1;
                private final /* synthetic */ Context f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$2.startActivityAsUser(this.f$1.getIntent().addFlags(32768), (UserHandle) this.f$0.get(i));
                }
            });
            builder.show();
        }
    }
}
