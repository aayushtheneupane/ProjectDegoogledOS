package com.android.settings.datetime.timezone;

import android.content.Intent;
import android.icu.text.Collator;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.datetime.timezone.BaseTimeZoneAdapter;
import com.android.settings.datetime.timezone.BaseTimeZonePicker;
import com.android.settings.datetime.timezone.RegionSearchPicker;
import com.android.settings.datetime.timezone.model.FilteredCountryTimeZones;
import com.android.settings.datetime.timezone.model.TimeZoneData;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RegionSearchPicker extends BaseTimeZonePicker {
    private BaseTimeZoneAdapter<RegionItem> mAdapter;
    private TimeZoneData mTimeZoneData;

    public int getMetricsCategory() {
        return 1355;
    }

    public RegionSearchPicker() {
        super(C1715R.string.date_time_select_region, C1715R.string.date_time_search_region, true, true);
    }

    /* access modifiers changed from: protected */
    public BaseTimeZoneAdapter createAdapter(TimeZoneData timeZoneData) {
        this.mTimeZoneData = timeZoneData;
        this.mAdapter = new BaseTimeZoneAdapter(createAdapterItem(timeZoneData.getRegionIds()), new BaseTimeZonePicker.OnListItemClickListener() {
            public final void onListItemClick(BaseTimeZoneAdapter.AdapterItem adapterItem) {
                RegionSearchPicker.this.onListItemClick((RegionSearchPicker.RegionItem) adapterItem);
            }
        }, getLocale(), false, (CharSequence) null);
        return this.mAdapter;
    }

    /* access modifiers changed from: private */
    public void onListItemClick(RegionItem regionItem) {
        String id = regionItem.getId();
        FilteredCountryTimeZones lookupCountryTimeZones = this.mTimeZoneData.lookupCountryTimeZones(id);
        FragmentActivity activity = getActivity();
        if (lookupCountryTimeZones == null || lookupCountryTimeZones.getTimeZoneIds().isEmpty()) {
            Log.e("RegionSearchPicker", "Region has no time zones: " + id);
            activity.setResult(0);
            activity.finish();
            return;
        }
        List<String> timeZoneIds = lookupCountryTimeZones.getTimeZoneIds();
        if (timeZoneIds.size() == 1) {
            getActivity().setResult(-1, new Intent().putExtra("com.android.settings.datetime.timezone.result_region_id", id).putExtra("com.android.settings.datetime.timezone.result_time_zone_id", timeZoneIds.get(0)));
            getActivity().finish();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("com.android.settings.datetime.timezone.region_id", id);
        new SubSettingLauncher(getContext()).setDestination(RegionZonePicker.class.getCanonicalName()).setArguments(bundle).setSourceMetricsCategory(getMetricsCategory()).setResultListener(this, 1).launch();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == -1) {
                getActivity().setResult(-1, intent);
            }
            getActivity().finish();
        }
    }

    private List<RegionItem> createAdapterItem(Set<String> set) {
        TreeSet treeSet = new TreeSet(new RegionInfoComparator(Collator.getInstance(getLocale())));
        LocaleDisplayNames instance = LocaleDisplayNames.getInstance(getLocale());
        long j = 0;
        for (String next : set) {
            treeSet.add(new RegionItem(j, next, instance.regionDisplayName(next)));
            j = 1 + j;
        }
        return new ArrayList(treeSet);
    }

    static class RegionItem implements BaseTimeZoneAdapter.AdapterItem {
        private final String mId;
        private final long mItemId;
        private final String mName;
        private final String[] mSearchKeys = {this.mId, this.mName};

        public String getCurrentTime() {
            return null;
        }

        public String getIconText() {
            return null;
        }

        public CharSequence getSummary() {
            return null;
        }

        RegionItem(long j, String str, String str2) {
            this.mId = str;
            this.mName = str2;
            this.mItemId = j;
        }

        public String getId() {
            return this.mId;
        }

        public CharSequence getTitle() {
            return this.mName;
        }

        public long getItemId() {
            return this.mItemId;
        }

        public String[] getSearchKeys() {
            return this.mSearchKeys;
        }
    }

    private static class RegionInfoComparator implements Comparator<RegionItem> {
        private final Collator mCollator;

        RegionInfoComparator(Collator collator) {
            this.mCollator = collator;
        }

        public int compare(RegionItem regionItem, RegionItem regionItem2) {
            return this.mCollator.compare(regionItem.getTitle(), regionItem2.getTitle());
        }
    }
}
