package com.android.settings.password;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.SetupWizardUtils;
import com.android.settings.utils.SettingsDividerItemDecoration;
import com.google.android.setupdesign.GlifPreferenceLayout;
import com.havoc.config.center.C1715R;

public class ChooseLockPatternSize extends SettingsActivity {
    public Intent getIntent() {
        Intent intent = new Intent(super.getIntent());
        intent.putExtra(":settings:show_fragment", ChooseLockPatternSizeFragment.class.getName());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        super.onApplyThemeResource(theme, SetupWizardUtils.getTheme(getIntent()), z);
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return ChooseLockPatternSizeFragment.class.getName().equals(str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        findViewById(C1715R.C1718id.content_parent).setFitsSystemWindows(false);
    }

    public static class ChooseLockPatternSizeFragment extends SettingsPreferenceFragment {
        private ChooseLockSettingsHelper mChooseLockSettingsHelper;

        public int getMetricsCategory() {
            return 1999;
        }

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mChooseLockSettingsHelper = new ChooseLockSettingsHelper(getActivity());
            if (getActivity() instanceof ChooseLockPatternSize) {
                addPreferencesFromResource(C1715R.xml.security_settings_pattern_size);
                setHeaderView(C1715R.layout.choose_lock_pattern_size_header);
                return;
            }
            throw new SecurityException("Fragment contained in wrong activity");
        }

        public boolean onPreferenceTreeClick(Preference preference) {
            byte b;
            String key = preference.getKey();
            if ("lock_pattern_size_4".equals(key)) {
                b = 4;
            } else if ("lock_pattern_size_5".equals(key)) {
                b = 5;
            } else {
                b = "lock_pattern_size_6".equals(key) ? (byte) 6 : 3;
            }
            boolean booleanExtra = getActivity().getIntent().getBooleanExtra("lockscreen.biometric_weak_fallback", false);
            Intent intent = new Intent(getActivity(), ChooseLockPattern.class);
            intent.putExtra("pattern_size", b);
            intent.putExtra("key_lock_method", "pattern");
            intent.putExtra("confirm_credentials", false);
            intent.putExtra("lockscreen.biometric_weak_fallback", booleanExtra);
            Intent intent2 = getActivity().getIntent();
            if (intent2.hasExtra("has_challenge")) {
                intent.putExtra("has_challenge", intent2.getBooleanExtra("has_challenge", false));
                intent.putExtra("challenge", intent2.getLongExtra("challenge", 0));
            }
            if (intent2.hasExtra("extra_require_password")) {
                intent.putExtra("extra_require_password", intent2.getBooleanExtra("extra_require_password", true));
            }
            intent.putExtra("password", intent2.getByteArrayExtra("password"));
            intent.addFlags(33554432);
            startActivity(intent);
            finish();
            return true;
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
            glifPreferenceLayout.setDividerItemDecoration(new SettingsDividerItemDecoration(getContext()));
            glifPreferenceLayout.setIcon(getContext().getDrawable(C1715R.C1717drawable.ic_lock));
            setDivider((Drawable) null);
        }

        public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
        }
    }
}
