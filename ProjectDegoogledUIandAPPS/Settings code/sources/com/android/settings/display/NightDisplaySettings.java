package com.android.settings.display;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.NightDisplayListener;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NightDisplaySettings extends DashboardFragment implements NightDisplayListener.Callback {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.night_display_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return ColorDisplayManager.isNightDisplayAvailable(context);
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return NightDisplaySettings.buildPreferenceControllers(context);
        }
    };
    private ColorDisplayManager mColorDisplayManager;
    private NightDisplayListener mNightDisplayListener;

    public int getDialogMetricsCategory(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 589;
        }
        return 588;
    }

    public int getHelpResource() {
        return C1715R.string.help_url_night_display;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "NightDisplaySettings";
    }

    public int getMetricsCategory() {
        return 488;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.night_display_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mNightDisplayListener = new NightDisplayListener(context);
    }

    public void onStart() {
        super.onStart();
        this.mNightDisplayListener.setCallback(this);
    }

    public void onStop() {
        super.onStop();
        this.mNightDisplayListener.setCallback((NightDisplayListener.Callback) null);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if ("night_display_end_time".equals(preference.getKey())) {
            if (this.mColorDisplayManager.getNightDisplayAutoMode() == 1) {
                showDialog(1);
                return true;
            } else if (this.mColorDisplayManager.getNightDisplayAutoMode() == 2) {
                updatePreferenceStates();
                return true;
            }
        } else if ("night_display_start_time".equals(preference.getKey())) {
            if (this.mColorDisplayManager.getNightDisplayAutoMode() == 1) {
                showDialog(0);
                return true;
            } else if (this.mColorDisplayManager.getNightDisplayAutoMode() == 2) {
                updatePreferenceStates();
                return true;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    public /* synthetic */ void lambda$onCreateDialog$0$NightDisplaySettings(int i, TimePicker timePicker, int i2, int i3) {
        LocalTime of = LocalTime.of(i2, i3);
        if (i == 0) {
            this.mColorDisplayManager.setNightDisplayCustomStartTime(of);
        } else {
            this.mColorDisplayManager.setNightDisplayCustomEndTime(of);
        }
    }

    public Dialog onCreateDialog(int i) {
        LocalTime localTime;
        if (i != 0 && i != 1) {
            return super.onCreateDialog(i);
        }
        if (i == 0) {
            localTime = this.mColorDisplayManager.getNightDisplayCustomStartTime();
        } else {
            localTime = this.mColorDisplayManager.getNightDisplayCustomEndTime();
        }
        Context context = getContext();
        return new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void onTimeSet(TimePicker timePicker, int i, int i2) {
                NightDisplaySettings.this.lambda$onCreateDialog$0$NightDisplaySettings(this.f$1, timePicker, i, i2);
            }
        }, localTime.getHour(), localTime.getMinute(), DateFormat.is24HourFormat(context));
    }

    public void onActivated(boolean z) {
        updatePreferenceStates();
    }

    public void onAutoModeChanged(int i) {
        updatePreferenceStates();
    }

    public void onColorTemperatureChanged(int i) {
        updatePreferenceStates();
    }

    public void onCustomStartTimeChanged(LocalTime localTime) {
        updatePreferenceStates();
    }

    public void onCustomEndTimeChanged(LocalTime localTime) {
        updatePreferenceStates();
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    /* access modifiers changed from: private */
    public static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new NightDisplayFooterPreferenceController(context));
        return arrayList;
    }
}
