package com.android.settings.dream;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.dream.DreamBackend;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class StartNowPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private final DreamBackend mBackend;

    public String getPreferenceKey() {
        return "dream_start_now_button_container";
    }

    public boolean isAvailable() {
        return true;
    }

    public StartNowPreferenceController(Context context) {
        super(context);
        this.mBackend = DreamBackend.getInstance(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        ((Button) ((LayoutPreference) preferenceScreen.findPreference(getPreferenceKey())).findViewById(C1715R.C1718id.dream_start_now_button)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                StartNowPreferenceController.this.lambda$displayPreference$0$StartNowPreferenceController(view);
            }
        });
    }

    public /* synthetic */ void lambda$displayPreference$0$StartNowPreferenceController(View view) {
        this.mBackend.startDreaming();
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        ((Button) ((LayoutPreference) preference).findViewById(C1715R.C1718id.dream_start_now_button)).setEnabled(this.mBackend.getWhenToDreamSetting() != 3);
    }
}
