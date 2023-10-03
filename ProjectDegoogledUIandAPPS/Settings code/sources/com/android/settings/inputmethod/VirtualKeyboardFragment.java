package com.android.settings.inputmethod;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.preference.Preference;
import com.android.internal.util.Preconditions;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.inputmethod.InputMethodAndSubtypeUtilCompat;
import com.android.settingslib.inputmethod.InputMethodPreference;
import com.havoc.config.center.C1715R;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class VirtualKeyboardFragment extends SettingsPreferenceFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.virtual_keyboard_settings;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };
    private Preference mAddVirtualKeyboardScreen;
    private DevicePolicyManager mDpm;
    private InputMethodManager mImm;
    private final ArrayList<InputMethodPreference> mInputMethodPreferenceList = new ArrayList<>();

    public int getMetricsCategory() {
        return 345;
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        Activity activity = (Activity) Preconditions.checkNotNull(getActivity());
        addPreferencesFromResource(C1715R.xml.virtual_keyboard_settings);
        this.mImm = (InputMethodManager) Preconditions.checkNotNull((InputMethodManager) activity.getSystemService(InputMethodManager.class));
        this.mDpm = (DevicePolicyManager) Preconditions.checkNotNull((DevicePolicyManager) activity.getSystemService(DevicePolicyManager.class));
        this.mAddVirtualKeyboardScreen = (Preference) Preconditions.checkNotNull(findPreference("add_virtual_keyboard_screen"));
    }

    public void onResume() {
        super.onResume();
        updateInputMethodPreferenceViews();
    }

    private void updateInputMethodPreferenceViews() {
        int i;
        this.mInputMethodPreferenceList.clear();
        List permittedInputMethodsForCurrentUser = this.mDpm.getPermittedInputMethodsForCurrentUser();
        Context prefContext = getPrefContext();
        List<InputMethodInfo> enabledInputMethodList = this.mImm.getEnabledInputMethodList();
        if (enabledInputMethodList == null) {
            i = 0;
        } else {
            i = enabledInputMethodList.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            InputMethodInfo inputMethodInfo = enabledInputMethodList.get(i2);
            boolean z = permittedInputMethodsForCurrentUser == null || permittedInputMethodsForCurrentUser.contains(inputMethodInfo.getPackageName());
            Drawable loadIcon = inputMethodInfo.loadIcon(prefContext.getPackageManager());
            InputMethodPreference inputMethodPreference = new InputMethodPreference(prefContext, inputMethodInfo, false, z, (InputMethodPreference.OnSavePreferenceListener) null);
            inputMethodPreference.setIcon(loadIcon);
            this.mInputMethodPreferenceList.add(inputMethodPreference);
        }
        this.mInputMethodPreferenceList.sort(new Comparator(Collator.getInstance()) {
            private final /* synthetic */ Collator f$0;

            {
                this.f$0 = r1;
            }

            public final int compare(Object obj, Object obj2) {
                return ((InputMethodPreference) obj).compareTo((InputMethodPreference) obj2, this.f$0);
            }
        });
        getPreferenceScreen().removeAll();
        for (int i3 = 0; i3 < i; i3++) {
            InputMethodPreference inputMethodPreference2 = this.mInputMethodPreferenceList.get(i3);
            inputMethodPreference2.setOrder(i3);
            getPreferenceScreen().addPreference(inputMethodPreference2);
            InputMethodAndSubtypeUtilCompat.removeUnnecessaryNonPersistentPreference(inputMethodPreference2);
            inputMethodPreference2.updatePreferenceViews();
        }
        this.mAddVirtualKeyboardScreen.setIcon((int) C1715R.C1717drawable.ic_add_24dp);
        this.mAddVirtualKeyboardScreen.setOrder(i);
        getPreferenceScreen().addPreference(this.mAddVirtualKeyboardScreen);
    }
}
