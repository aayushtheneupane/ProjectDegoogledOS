package com.android.settings;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.datetime.AutoTimeFormatPreferenceController;
import com.android.settings.datetime.AutoTimePreferenceController;
import com.android.settings.datetime.AutoTimeZonePreferenceController;
import com.android.settings.datetime.DatePreferenceController;
import com.android.settings.datetime.TimeChangeListenerMixin;
import com.android.settings.datetime.TimeFormatPreferenceController;
import com.android.settings.datetime.TimePreferenceController;
import com.android.settings.datetime.TimeZonePreferenceController;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.datetime.ZoneGetter;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeSettings extends DashboardFragment implements TimePreferenceController.TimePreferenceHost, DatePreferenceController.DatePreferenceHost {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new DateTimeSearchIndexProvider();
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() {
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };

    public int getDialogMetricsCategory(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 608;
        }
        return 607;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DateTimeSettings";
    }

    public int getMetricsCategory() {
        return 38;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.date_time_prefs;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        getSettingsLifecycle().addObserver(new TimeChangeListenerMixin(context, this));
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        FragmentActivity activity = getActivity();
        boolean booleanExtra = activity.getIntent().getBooleanExtra(WizardManagerHelper.EXTRA_IS_FIRST_RUN, false);
        AutoTimeZonePreferenceController autoTimeZonePreferenceController = new AutoTimeZonePreferenceController(activity, this, booleanExtra);
        AutoTimePreferenceController autoTimePreferenceController = new AutoTimePreferenceController(activity, this);
        AutoTimeFormatPreferenceController autoTimeFormatPreferenceController = new AutoTimeFormatPreferenceController(activity, this);
        arrayList.add(autoTimeZonePreferenceController);
        arrayList.add(autoTimePreferenceController);
        arrayList.add(autoTimeFormatPreferenceController);
        arrayList.add(new TimeFormatPreferenceController(activity, this, booleanExtra));
        arrayList.add(new TimeZonePreferenceController(activity, autoTimeZonePreferenceController));
        arrayList.add(new TimePreferenceController(activity, this, autoTimePreferenceController));
        arrayList.add(new DatePreferenceController(activity, this, autoTimePreferenceController));
        return arrayList;
    }

    public void updateTimeAndDateDisplay(Context context) {
        updatePreferenceStates();
    }

    public Dialog onCreateDialog(int i) {
        if (i == 0) {
            return ((DatePreferenceController) use(DatePreferenceController.class)).buildDatePicker(getActivity());
        }
        if (i == 1) {
            return ((TimePreferenceController) use(TimePreferenceController.class)).buildTimePicker(getActivity());
        }
        throw new IllegalArgumentException();
    }

    public void showTimePicker() {
        removeDialog(1);
        showDialog(1);
    }

    public void showDatePicker() {
        showDialog(0);
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        public void setListening(boolean z) {
            if (z) {
                Calendar instance = Calendar.getInstance();
                this.mSummaryLoader.setSummary(this, ZoneGetter.getTimeZoneOffsetAndName(this.mContext, instance.getTimeZone(), instance.getTime()));
            }
        }
    }

    private static class DateTimeSearchIndexProvider extends BaseSearchIndexProvider {
        private DateTimeSearchIndexProvider() {
        }

        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.date_time_prefs;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    }
}
