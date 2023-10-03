package com.android.settings.deviceinfo.legal;

import android.content.Context;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageManager;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.ArrayUtils;
import com.android.settings.core.BasePreferenceController;
import java.io.IOException;
import java.util.Comparator;
import java.util.function.Consumer;

public class ModuleLicensesPreferenceController extends BasePreferenceController {
    public int getAvailabilityStatus() {
        return 1;
    }

    public ModuleLicensesPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mContext.getPackageManager().getInstalledModules(0).stream().sorted(Comparator.comparing(C0779x6231540a.INSTANCE)).filter(new Predicate(this.mContext)).forEach(new Consumer() {
            public final void accept(Object obj) {
                PreferenceGroup.this.addPreference(new ModuleLicensePreference(PreferenceGroup.this.getContext(), (ModuleInfo) obj));
            }
        });
    }

    static class Predicate implements java.util.function.Predicate<ModuleInfo> {
        private final Context mContext;

        public Predicate(Context context) {
            this.mContext = context;
        }

        public boolean test(ModuleInfo moduleInfo) {
            try {
                return ArrayUtils.contains(ModuleLicenseProvider.getPackageAssetManager(this.mContext.getPackageManager(), moduleInfo.getPackageName()).list(""), "NOTICE.html.gz");
            } catch (PackageManager.NameNotFoundException | IOException unused) {
                return false;
            }
        }
    }
}
