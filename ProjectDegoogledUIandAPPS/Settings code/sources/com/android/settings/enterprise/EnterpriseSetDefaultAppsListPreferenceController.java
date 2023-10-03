package com.android.settings.enterprise;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.applications.ApplicationFeatureProvider;
import com.android.settings.applications.EnterpriseDefaultApps;
import com.android.settings.applications.UserAppInfo;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.users.UserFeatureProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class EnterpriseSetDefaultAppsListPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final ApplicationFeatureProvider mApplicationFeatureProvider;
    private List<EnumMap<EnterpriseDefaultApps, List<ApplicationInfo>>> mApps = Collections.emptyList();
    private final EnterprisePrivacyFeatureProvider mEnterprisePrivacyFeatureProvider;
    private final SettingsPreferenceFragment mParent;
    private final PackageManager mPm;
    private final UserFeatureProvider mUserFeatureProvider;
    private List<UserInfo> mUsers = Collections.emptyList();

    public String getPreferenceKey() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    public EnterpriseSetDefaultAppsListPreferenceController(Context context, SettingsPreferenceFragment settingsPreferenceFragment, PackageManager packageManager) {
        super(context);
        this.mPm = packageManager;
        this.mParent = settingsPreferenceFragment;
        FeatureFactory factory = FeatureFactory.getFactory(context);
        this.mApplicationFeatureProvider = factory.getApplicationFeatureProvider(context);
        this.mEnterprisePrivacyFeatureProvider = factory.getEnterprisePrivacyFeatureProvider(context);
        this.mUserFeatureProvider = factory.getUserFeatureProvider(context);
        buildAppList();
    }

    private void buildAppList() {
        this.mUsers = new ArrayList();
        this.mApps = new ArrayList();
        for (UserHandle next : this.mUserFeatureProvider.getUserProfiles()) {
            EnumMap enumMap = null;
            boolean z = false;
            for (EnterpriseDefaultApps enterpriseDefaultApps : EnterpriseDefaultApps.values()) {
                List<UserAppInfo> findPersistentPreferredActivities = this.mApplicationFeatureProvider.findPersistentPreferredActivities(next.getIdentifier(), enterpriseDefaultApps.getIntents());
                if (!findPersistentPreferredActivities.isEmpty()) {
                    if (!z) {
                        this.mUsers.add(findPersistentPreferredActivities.get(0).userInfo);
                        enumMap = new EnumMap(EnterpriseDefaultApps.class);
                        this.mApps.add(enumMap);
                        z = true;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (UserAppInfo userAppInfo : findPersistentPreferredActivities) {
                        arrayList.add(userAppInfo.appInfo);
                    }
                    enumMap.put(enterpriseDefaultApps, arrayList);
                }
            }
        }
        ThreadUtils.postOnMainThread(new Runnable() {
            public final void run() {
                EnterpriseSetDefaultAppsListPreferenceController.this.mo9776xd2730fb1();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: updateUi */
    public void mo9776xd2730fb1() {
        Context context = this.mParent.getPreferenceManager().getContext();
        PreferenceScreen preferenceScreen = this.mParent.getPreferenceScreen();
        if (preferenceScreen != null) {
            if (this.mEnterprisePrivacyFeatureProvider.isInCompMode() || this.mUsers.size() != 1) {
                for (int i = 0; i < this.mUsers.size(); i++) {
                    PreferenceCategory preferenceCategory = new PreferenceCategory(context);
                    preferenceScreen.addPreference(preferenceCategory);
                    if (this.mUsers.get(i).isManagedProfile()) {
                        preferenceCategory.setTitle((int) C1715R.string.managed_device_admin_title);
                    } else {
                        preferenceCategory.setTitle((int) C1715R.string.personal_device_admin_title);
                    }
                    preferenceCategory.setOrder(i);
                    createPreferences(context, preferenceCategory, this.mApps.get(i));
                }
                return;
            }
            createPreferences(context, preferenceScreen, this.mApps.get(0));
        }
    }

    private void createPreferences(Context context, PreferenceGroup preferenceGroup, EnumMap<EnterpriseDefaultApps, List<ApplicationInfo>> enumMap) {
        if (preferenceGroup != null) {
            for (EnterpriseDefaultApps enterpriseDefaultApps : EnterpriseDefaultApps.values()) {
                List list = enumMap.get(enterpriseDefaultApps);
                if (list != null && !list.isEmpty()) {
                    Preference preference = new Preference(context);
                    preference.setTitle((CharSequence) getTitle(context, enterpriseDefaultApps, list.size()));
                    preference.setSummary(buildSummaryString(context, list));
                    preference.setOrder(enterpriseDefaultApps.ordinal());
                    preference.setSelectable(false);
                    preferenceGroup.addPreference(preference);
                }
            }
        }
    }

    private CharSequence buildSummaryString(Context context, List<ApplicationInfo> list) {
        CharSequence[] charSequenceArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            charSequenceArr[i] = list.get(i).loadLabel(this.mPm);
        }
        if (list.size() == 1) {
            return charSequenceArr[0];
        }
        if (list.size() == 2) {
            return context.getString(C1715R.string.app_names_concatenation_template_2, new Object[]{charSequenceArr[0], charSequenceArr[1]});
        }
        return context.getString(C1715R.string.app_names_concatenation_template_3, new Object[]{charSequenceArr[0], charSequenceArr[1], charSequenceArr[2]});
    }

    /* renamed from: com.android.settings.enterprise.EnterpriseSetDefaultAppsListPreferenceController$1 */
    static /* synthetic */ class C08231 {

        /* renamed from: $SwitchMap$com$android$settings$applications$EnterpriseDefaultApps */
        static final /* synthetic */ int[] f28x4783f43a = new int[EnterpriseDefaultApps.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.android.settings.applications.EnterpriseDefaultApps[] r0 = com.android.settings.applications.EnterpriseDefaultApps.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f28x4783f43a = r0
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.BROWSER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.CALENDAR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.CONTACTS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.PHONE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.MAP     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.EMAIL     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f28x4783f43a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.android.settings.applications.EnterpriseDefaultApps r1 = com.android.settings.applications.EnterpriseDefaultApps.CAMERA     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.enterprise.EnterpriseSetDefaultAppsListPreferenceController.C08231.<clinit>():void");
        }
    }

    private String getTitle(Context context, EnterpriseDefaultApps enterpriseDefaultApps, int i) {
        switch (C08231.f28x4783f43a[enterpriseDefaultApps.ordinal()]) {
            case 1:
                return context.getString(C1715R.string.default_browser_title);
            case 2:
                return context.getString(C1715R.string.default_calendar_app_title);
            case 3:
                return context.getString(C1715R.string.default_contacts_app_title);
            case 4:
                return context.getResources().getQuantityString(C1715R.plurals.default_phone_app_title, i);
            case 5:
                return context.getString(C1715R.string.default_map_app_title);
            case 6:
                return context.getResources().getQuantityString(C1715R.plurals.default_email_app_title, i);
            case 7:
                return context.getResources().getQuantityString(C1715R.plurals.default_camera_app_title, i);
            default:
                throw new IllegalStateException("Unknown type of default " + enterpriseDefaultApps);
        }
    }
}
