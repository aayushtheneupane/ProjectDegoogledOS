package com.android.settings.datetime.timezone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.datetime.timezone.BaseTimeZoneAdapter;
import com.android.settings.datetime.timezone.model.TimeZoneData;
import com.android.settings.datetime.timezone.model.TimeZoneDataLoader;
import com.havoc.config.center.C1715R;
import java.util.Locale;

public abstract class BaseTimeZonePicker extends InstrumentedFragment implements SearchView.OnQueryTextListener {
    private BaseTimeZoneAdapter mAdapter;
    private final boolean mDefaultExpandSearch;
    private RecyclerView mRecyclerView;
    private final boolean mSearchEnabled;
    private final int mSearchHintResId;
    private SearchView mSearchView;
    private TimeZoneData mTimeZoneData;
    private final int mTitleResId;

    public interface OnListItemClickListener<T extends BaseTimeZoneAdapter.AdapterItem> {
        void onListItemClick(T t);
    }

    /* access modifiers changed from: protected */
    public abstract BaseTimeZoneAdapter createAdapter(TimeZoneData timeZoneData);

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    protected BaseTimeZonePicker(int i, int i2, boolean z, boolean z2) {
        this.mTitleResId = i;
        this.mSearchHintResId = i2;
        this.mSearchEnabled = z;
        this.mDefaultExpandSearch = z2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        getActivity().setTitle(this.mTitleResId);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1715R.layout.time_zone_items_list, viewGroup, false);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(C1715R.C1718id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.mRecyclerView.setAdapter(this.mAdapter);
        getLoaderManager().initLoader(0, (Bundle) null, new TimeZoneDataLoader.LoaderCreator(getContext(), new TimeZoneDataLoader.OnDataReadyCallback() {
            public final void onTimeZoneDataReady(TimeZoneData timeZoneData) {
                BaseTimeZonePicker.this.onTimeZoneDataReady(timeZoneData);
            }
        }));
        return inflate;
    }

    public void onTimeZoneDataReady(TimeZoneData timeZoneData) {
        if (this.mTimeZoneData == null && timeZoneData != null) {
            this.mTimeZoneData = timeZoneData;
            this.mAdapter = createAdapter(this.mTimeZoneData);
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.setAdapter(this.mAdapter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Locale getLocale() {
        return getContext().getResources().getConfiguration().getLocales().get(0);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mSearchEnabled) {
            menuInflater.inflate(C1715R.C1720menu.time_zone_base_search_menu, menu);
            MenuItem findItem = menu.findItem(C1715R.C1718id.time_zone_search_menu);
            this.mSearchView = (SearchView) findItem.getActionView();
            this.mSearchView.setQueryHint(getText(this.mSearchHintResId));
            this.mSearchView.setOnQueryTextListener(this);
            if (this.mDefaultExpandSearch) {
                findItem.expandActionView();
                this.mSearchView.setIconified(false);
                this.mSearchView.setActivated(true);
                this.mSearchView.setQuery("", true);
            }
            TextView textView = (TextView) this.mSearchView.findViewById(16909327);
            textView.setPadding(0, textView.getPaddingTop(), 0, textView.getPaddingBottom());
            View findViewById = this.mSearchView.findViewById(16909323);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.setMarginStart(0);
            layoutParams.setMarginEnd(0);
            findViewById.setLayoutParams(layoutParams);
        }
    }

    public boolean onQueryTextChange(String str) {
        BaseTimeZoneAdapter baseTimeZoneAdapter = this.mAdapter;
        if (baseTimeZoneAdapter == null) {
            return false;
        }
        baseTimeZoneAdapter.getFilter().filter(str);
        return false;
    }
}
