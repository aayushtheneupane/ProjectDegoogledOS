package com.android.dialer.app.list;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.support.p000v4.util.LongSparseArray;
import android.support.v13.app.FragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.contacts.common.ContactTileLoaderFactory;
import com.android.contacts.common.list.ContactTileView;
import com.android.contacts.common.list.OnPhoneNumberPickerActionListener;
import com.android.dialer.R;
import com.android.dialer.app.list.PhoneFavoritesTileAdapter;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;

public class OldSpeedDialFragment extends Fragment implements AdapterView.OnItemClickListener, PhoneFavoritesTileAdapter.OnDataSetChangedForAnimationListener, EmptyContentView.OnEmptyViewActionButtonClickedListener, FragmentCompat.OnRequestPermissionsResultCallback {
    /* access modifiers changed from: private */
    public int animationDuration;
    /* access modifiers changed from: private */
    public PhoneFavoritesTileAdapter contactTileAdapter;
    private final ContactTileView.Listener contactTileAdapterListener = new ContactTileAdapterListener(this);
    private View contactTileFrame;
    private LoaderManager.LoaderCallbacks<Cursor> contactTileLoaderListener;
    private EmptyContentView emptyView;
    /* access modifiers changed from: private */
    public final LongSparseArray<Integer> itemIdLeftMap = new LongSparseArray<>();
    /* access modifiers changed from: private */
    public final LongSparseArray<Integer> itemIdTopMap = new LongSparseArray<>();
    /* access modifiers changed from: private */
    public PhoneFavoriteListView listView;
    private final ScrollListener scrollListener = new ScrollListener(this);

    private static final class ContactTileAdapterListener implements ContactTileView.Listener {
        private final OldSpeedDialFragment fragment;

        ContactTileAdapterListener(OldSpeedDialFragment oldSpeedDialFragment) {
            this.fragment = oldSpeedDialFragment;
        }

        public void onCallNumberDirectly(String str, CallSpecificAppData callSpecificAppData) {
            ((OnPhoneNumberPickerActionListener) FragmentUtils.getParentUnsafe((Fragment) this.fragment, OnPhoneNumberPickerActionListener.class)).onPickPhoneNumber(str, false, callSpecificAppData);
        }

        public void onContactSelected(Uri uri, Rect rect, CallSpecificAppData callSpecificAppData) {
            ((OnPhoneNumberPickerActionListener) FragmentUtils.getParentUnsafe((Fragment) this.fragment, OnPhoneNumberPickerActionListener.class)).onPickDataUri(uri, false, callSpecificAppData);
        }
    }

    private static final class ContactTileLoaderListener implements LoaderManager.LoaderCallbacks<Cursor> {
        private final PhoneFavoritesTileAdapter adapter;
        private final OldSpeedDialFragment fragment;

        ContactTileLoaderListener(OldSpeedDialFragment oldSpeedDialFragment, PhoneFavoritesTileAdapter phoneFavoritesTileAdapter) {
            this.fragment = oldSpeedDialFragment;
            this.adapter = phoneFavoritesTileAdapter;
        }

        public Loader onCreateLoader(int i, Bundle bundle) {
            return ContactTileLoaderFactory.createStrequentPhoneOnlyLoader(this.fragment.getContext());
        }

        public void onLoadFinished(Loader loader, Object obj) {
            this.adapter.setContactCursor((Cursor) obj);
            boolean z = true;
            this.fragment.setEmptyViewVisibility(this.adapter.getCount() == 0);
            HostInterface hostInterface = (HostInterface) FragmentUtils.getParentUnsafe((Fragment) this.fragment, HostInterface.class);
            if (this.adapter.getNumFrequents() <= 0) {
                z = false;
            }
            hostInterface.setHasFrequents(z);
        }

        public void onLoaderReset(Loader<Cursor> loader) {
        }
    }

    public interface HostInterface {
        ImageView getDragShadowOverlay();

        void setDragDropController(DragDropController dragDropController);

        void setHasFrequents(boolean z);

        void showAllContactsTab();
    }

    private static class ScrollListener implements AbsListView.OnScrollListener {
        private final OldSpeedDialFragment fragment;

        ScrollListener(OldSpeedDialFragment oldSpeedDialFragment) {
            this.fragment = oldSpeedDialFragment;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            ((OnListFragmentScrolledListener) FragmentUtils.getParentUnsafe((Fragment) this.fragment, OnListFragmentScrolledListener.class)).onListFragmentScroll(i, i2, i3);
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            ((OnListFragmentScrolledListener) FragmentUtils.getParentUnsafe((Fragment) this.fragment, OnListFragmentScrolledListener.class)).onListFragmentScrollStateChange(i);
        }
    }

    /* access modifiers changed from: private */
    public boolean containsId(long[] jArr, long j) {
        for (long j2 : jArr) {
            if (j2 == j) {
                return true;
            }
        }
        return false;
    }

    public void cacheOffsetsForDatasetChange() {
        int firstVisiblePosition = this.listView.getFirstVisiblePosition();
        for (int i = 0; i < this.listView.getChildCount(); i++) {
            View childAt = this.listView.getChildAt(i);
            int i2 = firstVisiblePosition + i;
            if (this.contactTileAdapter.isIndexInBound(i2)) {
                long itemId = this.contactTileAdapter.getItemId(i2);
                this.itemIdTopMap.put(itemId, Integer.valueOf(childAt.getTop()));
                this.itemIdLeftMap.put(itemId, Integer.valueOf(childAt.getLeft()));
            }
        }
        this.itemIdTopMap.put(Long.MAX_VALUE, 0);
    }

    public boolean hasFrequents() {
        PhoneFavoritesTileAdapter phoneFavoritesTileAdapter = this.contactTileAdapter;
        if (phoneFavoritesTileAdapter != null && phoneFavoritesTileAdapter.getNumFrequents() > 0) {
            return true;
        }
        return false;
    }

    public void onCreate(Bundle bundle) {
        Trace.beginSection("OldSpeedDialFragment onCreate");
        super.onCreate(bundle);
        this.contactTileAdapter = new PhoneFavoritesTileAdapter(getContext(), this.contactTileAdapterListener, this);
        this.contactTileAdapter.setPhotoLoader(ContactPhotoManager.getInstance(getContext()));
        this.contactTileLoaderListener = new ContactTileLoaderListener(this, this.contactTileAdapter);
        this.animationDuration = getResources().getInteger(R.integer.fade_duration);
        Trace.endSection();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Trace.beginSection("OldSpeedDialFragment onCreateView");
        View inflate = layoutInflater.inflate(R.layout.speed_dial_fragment, viewGroup, false);
        this.listView = (PhoneFavoriteListView) inflate.findViewById(R.id.contact_tile_list);
        this.listView.setOnItemClickListener(this);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setVerticalScrollbarPosition(2);
        this.listView.setScrollBarStyle(33554432);
        this.listView.getDragDropController().addOnDragDropListener(this.contactTileAdapter);
        this.listView.setDragShadowOverlay(((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).getDragShadowOverlay());
        this.emptyView = (EmptyContentView) inflate.findViewById(R.id.empty_list_view);
        this.emptyView.setImage(R.drawable.empty_speed_dial);
        this.emptyView.setActionClickedListener(this);
        this.contactTileFrame = inflate.findViewById(R.id.contact_tile_frame);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(AnimationUtils.loadAnimation(getContext(), 17432576));
        layoutAnimationController.setDelay(0.0f);
        this.listView.setLayoutAnimation(layoutAnimationController);
        this.listView.setAdapter(this.contactTileAdapter);
        this.listView.setOnScrollListener(this.scrollListener);
        this.listView.setFastScrollEnabled(false);
        this.listView.setFastScrollAlwaysVisible(false);
        this.listView.setAccessibilityLiveRegion(0);
        ContentChangedFilter.addToParent(this.listView);
        Trace.endSection();
        return inflate;
    }

    public void onDataSetChangedForAnimation(final long... jArr) {
        if (this.itemIdTopMap.size() != 0) {
            CallUtil.doOnPreDraw(this.listView, true, new Runnable() {
                public void run() {
                    int firstVisiblePosition = OldSpeedDialFragment.this.listView.getFirstVisiblePosition();
                    AnimatorSet animatorSet = new AnimatorSet();
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (true) {
                        if (i >= OldSpeedDialFragment.this.listView.getChildCount()) {
                            break;
                        }
                        View childAt = OldSpeedDialFragment.this.listView.getChildAt(i);
                        int i2 = firstVisiblePosition + i;
                        if (OldSpeedDialFragment.this.contactTileAdapter.isIndexInBound(i2)) {
                            long itemId = OldSpeedDialFragment.this.contactTileAdapter.getItemId(i2);
                            if (OldSpeedDialFragment.this.containsId(jArr, itemId)) {
                                arrayList.add(ObjectAnimator.ofFloat(childAt, "alpha", new float[]{0.0f, 1.0f}));
                                break;
                            }
                            Integer num = (Integer) OldSpeedDialFragment.this.itemIdTopMap.get(itemId);
                            Integer num2 = (Integer) OldSpeedDialFragment.this.itemIdLeftMap.get(itemId);
                            int top = childAt.getTop();
                            int left = childAt.getLeft();
                            if (!(num2 == null || num2.intValue() == left)) {
                                arrayList.add(ObjectAnimator.ofFloat(childAt, "translationX", new float[]{(float) (num2.intValue() - left), 0.0f}));
                            }
                            if (!(num == null || num.intValue() == top)) {
                                arrayList.add(ObjectAnimator.ofFloat(childAt, "translationY", new float[]{(float) (num.intValue() - top), 0.0f}));
                            }
                        }
                        i++;
                    }
                    if (arrayList.size() > 0) {
                        animatorSet.setDuration((long) OldSpeedDialFragment.this.animationDuration).playTogether(arrayList);
                        animatorSet.start();
                    }
                    OldSpeedDialFragment.this.itemIdTopMap.clear();
                    OldSpeedDialFragment.this.itemIdLeftMap.clear();
                }
            });
        }
    }

    public void onEmptyViewActionButtonClicked() {
        String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(getContext(), PermissionsUtil.allContactsGroupPermissionsUsedInDialer);
        if (permissionsCurrentlyDenied.length > 0) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Requesting permissions: ");
            outline13.append(Arrays.toString(permissionsCurrentlyDenied));
            LogUtil.m9i("OldSpeedDialFragment.onEmptyViewActionButtonClicked", outline13.toString(), new Object[0]);
            FragmentCompat.requestPermissions(this, permissionsCurrentlyDenied, 1);
            return;
        }
        ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).showAllContactsTab();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i <= this.contactTileAdapter.getCount()) {
            LogUtil.m8e("OldSpeedDialFragment.onItemClick", "event for unexpected position. The position " + i + " is before \"all\" section. Ignored.", new Object[0]);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1 && iArr.length == 1 && iArr[0] == 0) {
            PermissionsUtil.notifyPermissionGranted(getContext(), "android.permission.READ_CONTACTS");
        }
    }

    public void onResume() {
        Trace.beginSection("OldSpeedDialFragment onResume");
        super.onResume();
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            if (getLoaderManager().getLoader(1) == null) {
                getLoaderManager().initLoader(1, (Bundle) null, this.contactTileLoaderListener);
            } else {
                getLoaderManager().getLoader(1).forceLoad();
            }
            this.emptyView.setDescription(R.string.speed_dial_empty);
            this.emptyView.setActionLabel(R.string.speed_dial_empty_add_favorite_action);
        } else {
            this.emptyView.setDescription(R.string.permission_no_speeddial);
            this.emptyView.setActionLabel(R.string.permission_single_turn_on);
        }
        Trace.endSection();
    }

    public void onStart() {
        super.onStart();
        this.listView.getDragDropController().addOnDragDropListener((OnDragDropListener) FragmentUtils.getParentUnsafe((Fragment) this, OnDragDropListener.class));
        ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).setDragDropController(this.listView.getDragDropController());
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            getLoaderManager().initLoader(1, (Bundle) null, this.contactTileLoaderListener);
        } else {
            setEmptyViewVisibility(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void setEmptyViewVisibility(boolean z) {
        int visibility = this.emptyView.getVisibility();
        int i = 0;
        int i2 = z ? 0 : 8;
        if (z) {
            i = 8;
        }
        if (visibility != i2) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.contactTileFrame.getLayoutParams();
            layoutParams.height = z ? -2 : -1;
            this.contactTileFrame.setLayoutParams(layoutParams);
            this.emptyView.setVisibility(i2);
            this.listView.setVisibility(i);
        }
    }
}
