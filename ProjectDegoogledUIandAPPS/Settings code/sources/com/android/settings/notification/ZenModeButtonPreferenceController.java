package com.android.settings.notification;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class ZenModeButtonPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private final FragmentManager mFragment;
    private Button mZenButtonOff;
    private Button mZenButtonOn;

    public String getPreferenceKey() {
        return "zen_mode_toggle";
    }

    public boolean isAvailable() {
        return true;
    }

    public ZenModeButtonPreferenceController(Context context, Lifecycle lifecycle, FragmentManager fragmentManager) {
        super(context, "zen_mode_toggle", lifecycle);
        this.mFragment = fragmentManager;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mZenButtonOn == null) {
            this.mZenButtonOn = (Button) ((LayoutPreference) preference).findViewById(C1715R.C1718id.zen_mode_settings_turn_on_button);
            updateZenButtonOnClickListener();
        }
        if (this.mZenButtonOff == null) {
            this.mZenButtonOff = (Button) ((LayoutPreference) preference).findViewById(C1715R.C1718id.zen_mode_settings_turn_off_button);
            this.mZenButtonOff.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ZenModeButtonPreferenceController.this.lambda$updateState$0$ZenModeButtonPreferenceController(view);
                }
            });
        }
        updateButtons();
    }

    public /* synthetic */ void lambda$updateState$0$ZenModeButtonPreferenceController(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenMode(0);
    }

    private void updateButtons() {
        int zenMode = getZenMode();
        if (zenMode == 1 || zenMode == 2 || zenMode == 3) {
            this.mZenButtonOff.setVisibility(0);
            this.mZenButtonOn.setVisibility(8);
            return;
        }
        this.mZenButtonOff.setVisibility(8);
        updateZenButtonOnClickListener();
        this.mZenButtonOn.setVisibility(0);
    }

    private void updateZenButtonOnClickListener() {
        int zenDuration = getZenDuration();
        if (zenDuration == -1) {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ZenModeButtonPreferenceController.this.mo11172xf2109be6(view);
                }
            });
        } else if (zenDuration != 0) {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener(zenDuration) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    ZenModeButtonPreferenceController.this.mo11174xbfc21b68(this.f$1, view);
                }
            });
        } else {
            this.mZenButtonOn.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    ZenModeButtonPreferenceController.this.mo11173x58e95ba7(view);
                }
            });
        }
    }

    /* renamed from: lambda$updateZenButtonOnClickListener$1$ZenModeButtonPreferenceController */
    public /* synthetic */ void mo11172xf2109be6(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        new SettingsEnableZenModeDialog().show(this.mFragment, "EnableZenModeButton");
    }

    /* renamed from: lambda$updateZenButtonOnClickListener$2$ZenModeButtonPreferenceController */
    public /* synthetic */ void mo11173x58e95ba7(View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenMode(1);
    }

    /* renamed from: lambda$updateZenButtonOnClickListener$3$ZenModeButtonPreferenceController */
    public /* synthetic */ void mo11174xbfc21b68(int i, View view) {
        this.mMetricsFeatureProvider.action(this.mContext, 1268, false);
        this.mBackend.setZenModeForDuration(i);
    }
}
