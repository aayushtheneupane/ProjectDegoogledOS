package com.android.dialer.searchfragment.list;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.searchfragment.common.SearchCursor;
import com.android.dialer.searchfragment.cp2.SearchContactViewHolder;
import com.android.dialer.searchfragment.directories.DirectoryContactViewHolder;
import com.android.dialer.searchfragment.nearbyplaces.NearbyPlaceViewHolder;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.List;

public final class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View.OnClickListener allowClickListener;
    private final Context context;
    private View.OnClickListener dismissClickListener;
    private String query;
    private String rawNumber;
    private RowClickListener rowClickListener;
    private final SearchCursorManager searchCursorManager;

    private static class LocationPermissionViewHolder extends RecyclerView.ViewHolder {
        LocationPermissionViewHolder(View view, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            super(view);
            Assert.isNotNull(onClickListener);
            Assert.isNotNull(onClickListener2);
            view.findViewById(R.id.location_permission_allow).setOnClickListener(onClickListener);
            view.findViewById(R.id.location_permission_dismiss).setOnClickListener(onClickListener2);
        }
    }

    public SearchAdapter(Context context2, SearchCursorManager searchCursorManager2, RowClickListener rowClickListener2) {
        this.context = context2;
        this.searchCursorManager = searchCursorManager2;
        this.rowClickListener = rowClickListener2;
    }

    public int getItemCount() {
        return this.searchCursorManager.getCount();
    }

    public int getItemViewType(int i) {
        return this.searchCursorManager.getRowType(i);
    }

    /* access modifiers changed from: package-private */
    public void hideLocationPermissionRequest() {
        this.allowClickListener = null;
        this.dismissClickListener = null;
        if (this.searchCursorManager.showLocationPermissionRequest(false)) {
            notifyItemRemoved(0);
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SearchContactViewHolder) {
            ((SearchContactViewHolder) viewHolder).bind(this.searchCursorManager.getCursor(i), this.query);
        } else if (viewHolder instanceof NearbyPlaceViewHolder) {
            ((NearbyPlaceViewHolder) viewHolder).bind(this.searchCursorManager.getCursor(i), this.query);
        } else if (viewHolder instanceof DirectoryContactViewHolder) {
            ((DirectoryContactViewHolder) viewHolder).bind(this.searchCursorManager.getCursor(i), this.query);
        } else if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).setHeader(this.searchCursorManager.getCursor(i).getString(0));
        } else if (viewHolder instanceof SearchActionViewHolder) {
            ((SearchActionViewHolder) viewHolder).setAction(this.searchCursorManager.getSearchAction(i), i, TextUtils.isEmpty(this.rawNumber) ? this.query : this.rawNumber);
        } else if (!(viewHolder instanceof LocationPermissionViewHolder)) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Invalid ViewHolder: ", viewHolder));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
            case 3:
            case 5:
                return new HeaderViewHolder(LayoutInflater.from(this.context).inflate(R.layout.header_layout, viewGroup, false));
            case 2:
                return new SearchContactViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_contact_row, viewGroup, false), this.rowClickListener);
            case 4:
                return new NearbyPlaceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_contact_row, viewGroup, false), this.rowClickListener);
            case 6:
                return new DirectoryContactViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_contact_row, viewGroup, false), this.rowClickListener);
            case 7:
                return new SearchActionViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_action_layout, viewGroup, false), this.rowClickListener);
            case 8:
                return new LocationPermissionViewHolder(LayoutInflater.from(this.context).inflate(R.layout.location_permission_row, viewGroup, false), this.allowClickListener, this.dismissClickListener);
            default:
                throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid RowType: ", i));
        }
    }

    public void setContactsCursor(SearchCursor searchCursor) {
        if (this.searchCursorManager.setContactsCursor(searchCursor)) {
            this.searchCursorManager.setQuery(this.query);
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void setDirectoryContactsCursor(SearchCursor searchCursor) {
        if (this.searchCursorManager.setCorpDirectoryCursor(searchCursor)) {
            notifyDataSetChanged();
        }
    }

    public void setNearbyPlacesCursor(SearchCursor searchCursor) {
        if (this.searchCursorManager.setNearbyPlacesCursor(searchCursor)) {
            notifyDataSetChanged();
        }
    }

    public void setQuery(String str, String str2) {
        this.query = str;
        this.rawNumber = str2;
        if (this.searchCursorManager.setQuery(str)) {
            notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void setSearchActions(List<Integer> list) {
        if (this.searchCursorManager.setSearchActions(list)) {
            notifyDataSetChanged();
        }
    }

    public void showLocationPermissionRequest(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        Assert.isNotNull(onClickListener);
        this.allowClickListener = onClickListener;
        Assert.isNotNull(onClickListener2);
        this.dismissClickListener = onClickListener2;
        if (this.searchCursorManager.showLocationPermissionRequest(true)) {
            notifyItemInserted(0);
        }
    }
}
