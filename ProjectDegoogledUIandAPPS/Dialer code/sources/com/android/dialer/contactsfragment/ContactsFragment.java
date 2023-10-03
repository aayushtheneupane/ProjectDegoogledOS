package com.android.dialer.contactsfragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.v13.app.FragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;

public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnScrollChangeListener, EmptyContentView.OnEmptyViewActionButtonClickedListener {
    /* access modifiers changed from: private */
    public ContactsAdapter adapter;
    private TextView anchoredHeader;
    private EmptyContentView emptyContentView;
    /* access modifiers changed from: private */
    public FastScroller fastScroller;
    private boolean hasPhoneNumbers;
    private int header;
    private LinearLayoutManager manager;
    private String query;
    private final BroadcastReceiver readContactsPermissionGrantedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            ContactsFragment.this.loadContacts();
        }
    };
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;

    public interface OnContactSelectedListener {
        void onContactSelected(ImageView imageView, Uri uri, long j);
    }

    public interface OnContactsFragmentHiddenChangedListener {
        void onContactsFragmentHiddenChanged(boolean z);
    }

    public interface OnContactsListScrolledListener {
        void onContactsListScrolled(boolean z);
    }

    private ContactViewHolder getContactHolder(int i) {
        return (ContactViewHolder) this.recyclerView.findViewHolderForAdapterPosition(i);
    }

    /* access modifiers changed from: private */
    public void loadContacts() {
        getLoaderManager().initLoader(0, (Bundle) null, this);
        this.recyclerView.setVisibility(0);
        this.emptyContentView.setVisibility(8);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.header = getArguments().getInt("extra_header");
        this.hasPhoneNumbers = getArguments().getBoolean("extra_has_phone_numbers");
        if (bundle == null) {
            super.onHiddenChanged(false);
            OnContactsFragmentHiddenChangedListener onContactsFragmentHiddenChangedListener = (OnContactsFragmentHiddenChangedListener) FragmentUtils.getParent((Fragment) this, OnContactsFragmentHiddenChangedListener.class);
            if (onContactsFragmentHiddenChangedListener != null) {
                onContactsFragmentHiddenChangedListener.onContactsFragmentHiddenChanged(false);
            }
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        ContactsCursorLoader contactsCursorLoader = new ContactsCursorLoader(getContext(), this.hasPhoneNumbers);
        contactsCursorLoader.setQuery(this.query);
        return contactsCursorLoader;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_contacts, viewGroup, false);
        this.fastScroller = (FastScroller) inflate.findViewById(R.id.fast_scroller);
        this.anchoredHeader = (TextView) inflate.findViewById(R.id.header);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.adapter = new ContactsAdapter(getContext(), this.header, (OnContactSelectedListener) FragmentUtils.getParent((Fragment) this, OnContactSelectedListener.class));
        this.recyclerView.setAdapter(this.adapter);
        this.manager = new LinearLayoutManager(getContext()) {
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                if (ContactsFragment.this.adapter.getItemCount() > (findLastVisibleItemPosition() - findFirstVisibleItemPosition()) + 1) {
                    ContactsFragment.this.fastScroller.setVisibility(0);
                    ContactsFragment.this.recyclerView.setOnScrollChangeListener(ContactsFragment.this);
                    return;
                }
                ContactsFragment.this.fastScroller.setVisibility(8);
            }
        };
        this.recyclerView.setLayoutManager(this.manager);
        this.emptyContentView = (EmptyContentView) inflate.findViewById(R.id.empty_list_view);
        this.emptyContentView.setImage(R.drawable.empty_contacts);
        this.emptyContentView.setActionClickedListener(this);
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            loadContacts();
        } else {
            this.emptyContentView.setDescription(R.string.permission_no_contacts);
            this.emptyContentView.setActionLabel(R.string.permission_single_turn_on);
            this.emptyContentView.setVisibility(0);
            this.recyclerView.setVisibility(8);
        }
        return inflate;
    }

    public void onEmptyViewActionButtonClicked() {
        if (this.emptyContentView.getActionLabel() == R.string.permission_single_turn_on) {
            String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(getContext(), PermissionsUtil.allContactsGroupPermissionsUsedInDialer);
            if (permissionsCurrentlyDenied.length > 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Requesting permissions: ");
                outline13.append(Arrays.toString(permissionsCurrentlyDenied));
                LogUtil.m9i("ContactsFragment.onEmptyViewActionButtonClicked", outline13.toString(), new Object[0]);
                FragmentCompat.requestPermissions(this, permissionsCurrentlyDenied, 1);
            }
        } else if (this.emptyContentView.getActionLabel() == R.string.all_contacts_empty_add_contact_action) {
            DialerUtils.startActivityWithErrorToast(getContext(), CallUtil.getNewContactIntent(), R.string.add_contact_not_available);
        } else {
            throw new IllegalStateException("Invalid empty content view action label.");
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        OnContactsFragmentHiddenChangedListener onContactsFragmentHiddenChangedListener = (OnContactsFragmentHiddenChangedListener) FragmentUtils.getParent((Fragment) this, OnContactsFragmentHiddenChangedListener.class);
        if (onContactsFragmentHiddenChangedListener != null) {
            onContactsFragmentHiddenChangedListener.onContactsFragmentHiddenChanged(z);
        }
    }

    public void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        LogUtil.enterBlock("ContactsFragment.onLoadFinished");
        if (cursor == null || cursor.getCount() == 0) {
            this.emptyContentView.setDescription(R.string.all_contacts_empty);
            this.emptyContentView.setActionLabel(R.string.all_contacts_empty_add_contact_action);
            this.emptyContentView.setVisibility(0);
            this.recyclerView.setVisibility(8);
            return;
        }
        this.emptyContentView.setVisibility(8);
        this.recyclerView.setVisibility(0);
        this.adapter.updateCursor(cursor);
        PerformanceReport.logOnScrollStateChange(this.recyclerView);
        this.fastScroller.setup(this.adapter, this.manager);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.recyclerView.setAdapter((RecyclerView.Adapter) null);
        this.recyclerView.setOnScrollChangeListener((View.OnScrollChangeListener) null);
        this.adapter = null;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1 && iArr.length >= 1 && iArr[0] == 0) {
            PermissionsUtil.notifyPermissionGranted(getContext(), strArr[0]);
        }
    }

    public void onResume() {
        super.onResume();
        if (getActivity() != null && isAdded() && PermissionsUtil.hasContactsReadPermissions(getContext())) {
            getLoaderManager().restartLoader(0, (Bundle) null, this);
        }
    }

    public void onScrollChange(View view, int i, int i2, int i3, int i4) {
        this.fastScroller.updateContainerAndScrollBarPosition(this.recyclerView);
        int findFirstVisibleItemPosition = this.manager.findFirstVisibleItemPosition();
        int findFirstCompletelyVisibleItemPosition = this.manager.findFirstCompletelyVisibleItemPosition();
        if (findFirstCompletelyVisibleItemPosition != -1) {
            String headerString = this.adapter.getHeaderString(findFirstCompletelyVisibleItemPosition);
            OnContactsListScrolledListener onContactsListScrolledListener = (OnContactsListScrolledListener) FragmentUtils.getParent((Fragment) this, OnContactsListScrolledListener.class);
            if (onContactsListScrolledListener != null) {
                boolean z = true;
                if (this.recyclerView.getScrollState() != 1 && !this.fastScroller.isDragStarted()) {
                    z = false;
                }
                onContactsListScrolledListener.onContactsListScrolled(z);
            }
            if (findFirstVisibleItemPosition == findFirstCompletelyVisibleItemPosition && findFirstVisibleItemPosition == 0) {
                this.adapter.refreshHeaders();
                this.anchoredHeader.setVisibility(4);
            } else if (findFirstVisibleItemPosition == 0) {
            } else {
                if (this.adapter.getHeaderString(findFirstVisibleItemPosition).equals(headerString)) {
                    this.anchoredHeader.setText(headerString);
                    this.anchoredHeader.setVisibility(0);
                    getContactHolder(findFirstVisibleItemPosition).getHeaderView().setVisibility(4);
                    getContactHolder(findFirstCompletelyVisibleItemPosition).getHeaderView().setVisibility(4);
                    return;
                }
                this.anchoredHeader.setVisibility(4);
                getContactHolder(findFirstVisibleItemPosition).getHeaderView().setVisibility(0);
                getContactHolder(findFirstCompletelyVisibleItemPosition).getHeaderView().setVisibility(0);
            }
        }
    }

    public void onStart() {
        super.onStart();
        PermissionsUtil.registerPermissionReceiver(getActivity(), this.readContactsPermissionGrantedReceiver, "android.permission.READ_CONTACTS");
    }

    public void onStop() {
        PermissionsUtil.unregisterPermissionReceiver(getActivity(), this.readContactsPermissionGrantedReceiver);
        super.onStop();
    }
}
