package com.android.systemui.tuner;

import android.os.Bundle;
import android.os.Handler;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import com.android.systemui.C1786R$xml;
import com.android.systemui.Dependency;
import com.android.systemui.tuner.TunerService;
import java.util.ArrayList;

public class NavBarTuner extends PreferenceFragment {
    private Handler mHandler;
    private final ArrayList<TunerService.Tunable> mTunables = new ArrayList<>();

    public void onCreate(Bundle bundle) {
        this.mHandler = new Handler();
        super.onCreate(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(C1786R$xml.nav_bar_tuner);
        bindLayout((ListPreference) findPreference("layout"));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mTunables.forEach($$Lambda$NavBarTuner$tsKQ8HfwaDSvc3iDCsgHsW954hc.INSTANCE);
    }

    private void addTunable(TunerService.Tunable tunable, String... strArr) {
        this.mTunables.add(tunable);
        ((TunerService) Dependency.get(TunerService.class)).addTunable(tunable, strArr);
    }

    private void bindLayout(ListPreference listPreference) {
        addTunable(new TunerService.Tunable(listPreference) {
            private final /* synthetic */ ListPreference f$1;

            {
                this.f$1 = r2;
            }

            public final void onTuningChanged(String str, String str2) {
                NavBarTuner.this.lambda$bindLayout$2$NavBarTuner(this.f$1, str, str2);
            }
        }, "sysui_nav_bar");
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return NavBarTuner.lambda$bindLayout$3(ListPreference.this, preference, obj);
            }
        });
    }

    public /* synthetic */ void lambda$bindLayout$2$NavBarTuner(ListPreference listPreference, String str, String str2) {
        this.mHandler.post(new Runnable(str2, listPreference) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ ListPreference f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void run() {
                NavBarTuner.lambda$bindLayout$1(this.f$0, this.f$1);
            }
        });
    }

    static /* synthetic */ void lambda$bindLayout$1(String str, ListPreference listPreference) {
        if (str == null) {
            str = "default";
        }
        listPreference.setValue(str);
    }

    static /* synthetic */ boolean lambda$bindLayout$3(ListPreference listPreference, Preference preference, Object obj) {
        String str = (String) obj;
        listPreference.setSummary(listPreference.getEntries()[listPreference.findIndexOfValue(str)]);
        if ("default".equals(str)) {
            str = null;
        }
        ((TunerService) Dependency.get(TunerService.class)).setValue("sysui_nav_bar", str);
        return true;
    }
}
