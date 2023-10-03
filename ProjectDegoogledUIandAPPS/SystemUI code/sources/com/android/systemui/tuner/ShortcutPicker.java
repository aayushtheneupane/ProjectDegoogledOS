package com.android.systemui.tuner;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.tuner.ShortcutParser;
import com.android.systemui.tuner.TunerService;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ShortcutPicker extends PreferenceFragment implements TunerService.Tunable {
    private String mKey;
    private SelectablePreference mNonePreference;
    private final ArrayList<SelectablePreference> mSelectablePreferences = new ArrayList<>();
    private TunerService mTunerService;

    public void onCreatePreferences(Bundle bundle, String str) {
        Context context = getPreferenceManager().getContext();
        PreferenceScreen createPreferenceScreen = getPreferenceManager().createPreferenceScreen(context);
        createPreferenceScreen.setOrderingAsAdded(true);
        PreferenceCategory preferenceCategory = new PreferenceCategory(context);
        preferenceCategory.setTitle(C1784R$string.tuner_other_apps);
        this.mNonePreference = new SelectablePreference(context);
        this.mSelectablePreferences.add(this.mNonePreference);
        this.mNonePreference.setTitle(C1784R$string.lockscreen_none);
        this.mNonePreference.setIcon(C1776R$drawable.ic_remove_circle);
        createPreferenceScreen.addPreference(this.mNonePreference);
        List<LauncherActivityInfo> activityList = ((LauncherApps) getContext().getSystemService(LauncherApps.class)).getActivityList((String) null, Process.myUserHandle());
        createPreferenceScreen.addPreference(preferenceCategory);
        activityList.forEach(new Consumer(context, createPreferenceScreen, preferenceCategory) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ PreferenceScreen f$2;
            private final /* synthetic */ PreferenceCategory f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void accept(Object obj) {
                ShortcutPicker.this.lambda$onCreatePreferences$1$ShortcutPicker(this.f$1, this.f$2, this.f$3, (LauncherActivityInfo) obj);
            }
        });
        createPreferenceScreen.removePreference(preferenceCategory);
        for (int i = 0; i < preferenceCategory.getPreferenceCount(); i++) {
            Preference preference = preferenceCategory.getPreference(0);
            preferenceCategory.removePreference(preference);
            preference.setOrder(Integer.MAX_VALUE);
            createPreferenceScreen.addPreference(preference);
        }
        setPreferenceScreen(createPreferenceScreen);
        this.mKey = getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT");
        this.mTunerService = (TunerService) Dependency.get(TunerService.class);
        this.mTunerService.addTunable(this, this.mKey);
    }

    public /* synthetic */ void lambda$onCreatePreferences$1$ShortcutPicker(Context context, PreferenceScreen preferenceScreen, PreferenceCategory preferenceCategory, LauncherActivityInfo launcherActivityInfo) {
        try {
            List<ShortcutParser.Shortcut> shortcuts = new ShortcutParser(getContext(), launcherActivityInfo.getComponentName()).getShortcuts();
            AppPreference appPreference = new AppPreference(context, launcherActivityInfo);
            this.mSelectablePreferences.add(appPreference);
            if (shortcuts.size() != 0) {
                preferenceScreen.addPreference(appPreference);
                shortcuts.forEach(new Consumer(context, launcherActivityInfo, preferenceScreen) {
                    private final /* synthetic */ Context f$1;
                    private final /* synthetic */ LauncherActivityInfo f$2;
                    private final /* synthetic */ PreferenceScreen f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void accept(Object obj) {
                        ShortcutPicker.this.lambda$onCreatePreferences$0$ShortcutPicker(this.f$1, this.f$2, this.f$3, (ShortcutParser.Shortcut) obj);
                    }
                });
                return;
            }
            preferenceCategory.addPreference(appPreference);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public /* synthetic */ void lambda$onCreatePreferences$0$ShortcutPicker(Context context, LauncherActivityInfo launcherActivityInfo, PreferenceScreen preferenceScreen, ShortcutParser.Shortcut shortcut) {
        ShortcutPreference shortcutPreference = new ShortcutPreference(context, shortcut, launcherActivityInfo.getLabel());
        this.mSelectablePreferences.add(shortcutPreference);
        preferenceScreen.addPreference(shortcutPreference);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        this.mTunerService.setValue(this.mKey, preference.toString());
        getActivity().onBackPressed();
        return true;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if ("sysui_keyguard_left".equals(this.mKey)) {
            getActivity().setTitle(C1784R$string.lockscreen_shortcut_left);
        } else {
            getActivity().setTitle(C1784R$string.lockscreen_shortcut_right);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mTunerService.removeTunable(this);
    }

    public void onTuningChanged(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        this.mSelectablePreferences.forEach(new Consumer(str2) {
            private final /* synthetic */ String f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                ((SelectablePreference) obj).setChecked(this.f$0.equals(((SelectablePreference) obj).toString()));
            }
        });
    }

    private static class AppPreference extends SelectablePreference {
        private boolean mBinding;
        private final LauncherActivityInfo mInfo;

        public AppPreference(Context context, LauncherActivityInfo launcherActivityInfo) {
            super(context);
            this.mInfo = launcherActivityInfo;
            setTitle((CharSequence) context.getString(C1784R$string.tuner_launch_app, new Object[]{launcherActivityInfo.getLabel()}));
            setSummary((CharSequence) context.getString(C1784R$string.tuner_app, new Object[]{launcherActivityInfo.getLabel()}));
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            this.mBinding = true;
            if (getIcon() == null) {
                setIcon(this.mInfo.getBadgedIcon(getContext().getResources().getConfiguration().densityDpi));
            }
            this.mBinding = false;
            super.onBindViewHolder(preferenceViewHolder);
        }

        /* access modifiers changed from: protected */
        public void notifyChanged() {
            if (!this.mBinding) {
                super.notifyChanged();
            }
        }

        public String toString() {
            return this.mInfo.getComponentName().flattenToString();
        }
    }

    private static class ShortcutPreference extends SelectablePreference {
        private boolean mBinding;
        private final ShortcutParser.Shortcut mShortcut;

        public ShortcutPreference(Context context, ShortcutParser.Shortcut shortcut, CharSequence charSequence) {
            super(context);
            this.mShortcut = shortcut;
            setTitle((CharSequence) shortcut.label);
            setSummary((CharSequence) context.getString(C1784R$string.tuner_app, new Object[]{charSequence}));
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            this.mBinding = true;
            if (getIcon() == null) {
                setIcon(this.mShortcut.icon.loadDrawable(getContext()));
            }
            this.mBinding = false;
            super.onBindViewHolder(preferenceViewHolder);
        }

        /* access modifiers changed from: protected */
        public void notifyChanged() {
            if (!this.mBinding) {
                super.notifyChanged();
            }
        }

        public String toString() {
            return this.mShortcut.toString();
        }
    }
}
