package com.android.dialer.searchfragment.list;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.android.contacts.common.extensions.PhoneDirectoryExtenderAccessor;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.callcomposer.CallComposerActivity;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.searchfragment.common.SearchCursor;
import com.android.dialer.searchfragment.cp2.SearchContactsCursorLoader;
import com.android.dialer.searchfragment.directories.DirectoriesCursorLoader;
import com.android.dialer.searchfragment.directories.DirectoryContactsCursorLoader;
import com.android.dialer.searchfragment.nearbyplaces.NearbyPlacesCursorLoader;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class NewSearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, EmptyContentView.OnEmptyViewActionButtonClickedListener, EnrichedCallManager.CapabilitiesListener, View.OnTouchListener, RowClickListener {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    public static final int READ_CONTACTS_PERMISSION_REQUEST_CODE = 1;
    private SearchAdapter adapter;
    private CallInitiationType$Type callInitiationType = CallInitiationType$Type.UNKNOWN_INITIATION;
    private final Runnable capabilitiesUpdatedRunnable = new Runnable() {
        public final void run() {
            NewSearchFragment.this.lambda$new$3$NewSearchFragment();
        }
    };
    private final List<DirectoriesCursorLoader.Directory> directories = new ArrayList();
    private boolean directoriesDisabledForTesting;
    private EmptyContentView emptyContentView;
    private final Runnable loadDirectoryContactsRunnable = new Runnable() {
        public final void run() {
            NewSearchFragment.this.lambda$new$2$NewSearchFragment();
        }
    };
    private final Runnable loadNearbyPlacesRunnable = new Runnable() {
        public final void run() {
            NewSearchFragment.this.lambda$new$1$NewSearchFragment();
        }
    };
    private final Runnable loaderCp2ContactsRunnable = new Runnable() {
        public final void run() {
            NewSearchFragment.this.lambda$new$0$NewSearchFragment();
        }
    };
    private String query;
    private String rawNumber;
    private RecyclerView recyclerView;
    private Runnable updatePositionRunnable;

    public interface SearchFragmentListener {
        void onCallPlacedFromSearch();

        void onSearchListTouch();

        void requestingPermission();
    }

    private List<Integer> getActions() {
        boolean isGlobalPhoneNumber = PhoneNumberUtils.isGlobalPhoneNumber(this.query);
        boolean z = isRegularSearch() && !isGlobalPhoneNumber;
        if (TextUtils.isEmpty(this.query) || this.query.length() == 1 || z) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (!isRegularSearch()) {
            arrayList.add(1);
            arrayList.add(Integer.valueOf(LOCATION_PERMISSION_REQUEST_CODE));
        }
        if (isRegularSearch() && isGlobalPhoneNumber) {
            arrayList.add(5);
        }
        arrayList.add(3);
        if (CallUtil.isVideoEnabled(getContext())) {
            arrayList.add(4);
        }
        return arrayList;
    }

    private void initLoaders() {
        getLoaderManager().initLoader(0, (Bundle) null, this);
        if (!this.directoriesDisabledForTesting) {
            getLoaderManager().initLoader(LOCATION_PERMISSION_REQUEST_CODE, (Bundle) null, this);
        }
    }

    private boolean isRegularSearch() {
        return this.callInitiationType == CallInitiationType$Type.REGULAR_SEARCH;
    }

    private void loadDirectoryContactsCursors() {
        if (!this.directoriesDisabledForTesting) {
            DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loadDirectoryContactsRunnable);
            DialerExecutorModule.getUiThreadHandler().postDelayed(this.loadDirectoryContactsRunnable, 300);
        }
    }

    private void loadNearbyPlacesCursor() {
        if (!showLocationPermission() && getContext() != null && PermissionsUtil.hasPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
            DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loadNearbyPlacesRunnable);
            PhoneDirectoryExtenderAccessor.get(getContext()).isEnabled(getContext());
        }
    }

    private void placeCall(String str, int i, boolean z) {
        CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder();
        newBuilder.setCallInitiationType(this.callInitiationType);
        newBuilder.setPositionOfSelectedSearchResult(i);
        String str2 = this.query;
        newBuilder.setCharactersInSearchString(str2 == null ? 0 : str2.length());
        newBuilder.setAllowAssistedDialing(true);
        PreCall.start(getContext(), new CallIntentBuilder(str, (CallSpecificAppData) newBuilder.build()).setIsVideoCall(z).setAllowAssistedDial(true));
        ((SearchFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, SearchFragmentListener.class)).onCallPlacedFromSearch();
    }

    private boolean showLocationPermission() {
        if (this.adapter == null) {
            return false;
        }
        if (getContext() == null || PermissionsUtil.hasPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION") || PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("search_location_prompt_dismissed", false) || !isRegularSearch()) {
            this.adapter.hideLocationPermissionRequest();
            return false;
        }
        this.adapter.showLocationPermissionRequest(new View.OnClickListener() {
            public final void onClick(View view) {
                NewSearchFragment.this.lambda$showLocationPermission$4$NewSearchFragment(view);
            }
        }, new View.OnClickListener() {
            public final void onClick(View view) {
                NewSearchFragment.this.lambda$showLocationPermission$5$NewSearchFragment(view);
            }
        });
        return true;
    }

    public void animatePosition(int i, int i2, int i3) {
        if (getView() == null) {
            this.updatePositionRunnable = new Runnable(i, i2) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    NewSearchFragment.this.lambda$animatePosition$6$NewSearchFragment(this.f$1, this.f$2);
                }
            };
            return;
        }
        Interpolator interpolator = i > i2 ? AnimUtils.EASE_IN : AnimUtils.EASE_OUT;
        int height = getActivity().findViewById(16908290).getHeight();
        getView().setTranslationY((float) i);
        getView().animate().translationY((float) i2).setInterpolator(interpolator).setDuration((long) i3).setUpdateListener(new ValueAnimator.AnimatorUpdateListener(height, height - (i2 - i)) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                NewSearchFragment.this.lambda$animatePosition$7$NewSearchFragment(this.f$1, this.f$2, valueAnimator);
            }
        });
        this.updatePositionRunnable = null;
    }

    public void dismissLocationPermission() {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("search_location_prompt_dismissed", true).apply();
        this.adapter.hideLocationPermissionRequest();
    }

    public String getRawNumber() {
        return this.rawNumber;
    }

    public /* synthetic */ void lambda$animatePosition$6$NewSearchFragment(int i, int i2) {
        animatePosition(i, i2, 0);
    }

    public /* synthetic */ void lambda$animatePosition$7$NewSearchFragment(int i, int i2, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        View view = getView();
        if (view != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = (int) ((((float) (i2 - i)) * animatedFraction) + ((float) i));
            view.setLayoutParams(layoutParams);
        }
    }

    public /* synthetic */ void lambda$new$0$NewSearchFragment() {
        if (getHost() != null) {
            getLoaderManager().restartLoader(0, (Bundle) null, this);
        }
    }

    public /* synthetic */ void lambda$new$1$NewSearchFragment() {
        if (getHost() != null) {
            getLoaderManager().restartLoader(1, (Bundle) null, this);
        }
    }

    public /* synthetic */ void lambda$new$2$NewSearchFragment() {
        if (getHost() != null) {
            getLoaderManager().restartLoader(3, (Bundle) null, this);
        }
    }

    public /* synthetic */ void lambda$new$3$NewSearchFragment() {
        this.adapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$showLocationPermission$4$NewSearchFragment(View view) {
        Assert.checkArgument(!PermissionsUtil.hasPermission(getContext(), "android.permission.ACCESS_FINE_LOCATION"), "attempted to request already granted location permission", new Object[0]);
        String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(getContext(), PermissionsUtil.allLocationGroupPermissionsUsedInDialer);
        ((SearchFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, SearchFragmentListener.class)).requestingPermission();
        requestPermissions(permissionsCurrentlyDenied, LOCATION_PERMISSION_REQUEST_CODE);
    }

    public /* synthetic */ void lambda$showLocationPermission$5$NewSearchFragment(View view) {
        dismissLocationPermission();
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        LogUtil.m9i("NewSearchFragment.onCreateLoader", GeneratedOutlineSupport.outline5("loading cursor: ", i), new Object[0]);
        if (i == 0) {
            return new SearchContactsCursorLoader(getContext(), this.query, isRegularSearch());
        }
        if (i == 1) {
            ArrayList arrayList = new ArrayList();
            for (DirectoriesCursorLoader.Directory id : this.directories) {
                arrayList.add(Long.valueOf(id.getId()));
            }
            PhoneDirectoryExtenderAccessor.get(getContext()).getContentUri();
            throw null;
        } else if (i == LOCATION_PERMISSION_REQUEST_CODE) {
            return new DirectoriesCursorLoader(getContext());
        } else {
            if (i == 3) {
                return new DirectoryContactsCursorLoader(getContext(), this.query, this.directories);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid loader id: ", i));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_search, viewGroup, false);
        this.adapter = new SearchAdapter(getContext(), new SearchCursorManager(), this);
        this.adapter.setQuery(this.query, this.rawNumber);
        this.adapter.setSearchActions(getActions());
        showLocationPermission();
        this.emptyContentView = (EmptyContentView) inflate.findViewById(R.id.empty_view);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setOnTouchListener(this);
        this.recyclerView.setAdapter(this.adapter);
        if (!PermissionsUtil.hasContactsReadPermissions(getContext())) {
            this.emptyContentView.setDescription(R.string.new_permission_no_search);
            this.emptyContentView.setActionLabel(R.string.permission_single_turn_on);
            this.emptyContentView.setActionClickedListener(this);
            this.emptyContentView.setImage(R.drawable.empty_contacts);
            this.emptyContentView.setVisibility(0);
        } else {
            initLoaders();
        }
        if (bundle != null) {
            setQuery(bundle.getString("key_query"), CallInitiationType$Type.forNumber(bundle.getInt("key_call_initiation_type")));
        }
        Runnable runnable = this.updatePositionRunnable;
        if (runnable != null) {
            CallUtil.doOnPreDraw(inflate, false, runnable);
        }
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loaderCp2ContactsRunnable);
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loadNearbyPlacesRunnable);
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loadDirectoryContactsRunnable);
        DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.capabilitiesUpdatedRunnable);
    }

    public void onEmptyViewActionButtonClicked() {
        String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(getContext(), PermissionsUtil.allContactsGroupPermissionsUsedInDialer);
        if (permissionsCurrentlyDenied.length > 0) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Requesting permissions: ");
            outline13.append(Arrays.toString(permissionsCurrentlyDenied));
            LogUtil.m9i("NewSearchFragment.onEmptyViewActionButtonClicked", outline13.toString(), new Object[0]);
            ((SearchFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, SearchFragmentListener.class)).requestingPermission();
            requestPermissions(permissionsCurrentlyDenied, 1);
        }
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        LogUtil.m9i("NewSearchFragment.onLoaderReset", GeneratedOutlineSupport.outline6("Loader reset: ", loader), new Object[0]);
        if (loader instanceof SearchContactsCursorLoader) {
            this.adapter.setContactsCursor((SearchCursor) null);
        } else if (loader instanceof NearbyPlacesCursorLoader) {
            this.adapter.setNearbyPlacesCursor((SearchCursor) null);
        } else if (loader instanceof DirectoryContactsCursorLoader) {
            this.adapter.setDirectoryContactsCursor((SearchCursor) null);
        }
    }

    public void onPause() {
        super.onPause();
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(getContext()).getEnrichedCallManager()).unregisterCapabilitiesListener(this);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            if (iArr.length >= 1 && iArr[0] == 0) {
                this.emptyContentView.setVisibility(8);
                initLoaders();
            }
        } else if (i == LOCATION_PERMISSION_REQUEST_CODE && iArr.length >= 1 && iArr[0] == 0) {
            loadNearbyPlacesCursor();
            this.adapter.hideLocationPermissionRequest();
        }
    }

    public void onResume() {
        super.onResume();
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(getContext()).getEnrichedCallManager()).registerCapabilitiesListener(this);
        getLoaderManager().restartLoader(0, (Bundle) null, this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("key_call_initiation_type", this.callInitiationType.getNumber());
        bundle.putString("key_query", this.query);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            view.performClick();
        }
        if (motionEvent.getAction() != 0) {
            return false;
        }
        ((SearchFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, SearchFragmentListener.class)).onSearchListTouch();
        return false;
    }

    public void openCallAndShare(DialerContact dialerContact) {
        DialerUtils.startActivityWithErrorToast(getContext(), CallComposerActivity.newIntent(getContext(), dialerContact), R.string.activity_not_available);
    }

    public void placeDuoCall(String str) {
        ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.LIGHTBRINGER_VIDEO_REQUESTED_FROM_SEARCH);
        PreCall.start(getContext(), new CallIntentBuilder(str, CallInitiationType$Type.REGULAR_SEARCH).setIsVideoCall(true).setIsDuoCall(true));
        ((SearchFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, SearchFragmentListener.class)).onCallPlacedFromSearch();
    }

    public void placeVideoCall(String str, int i) {
        placeCall(str, i, true);
    }

    public void placeVoiceCall(String str, int i) {
        placeCall(str, i, false);
    }

    public void setDirectoriesDisabled(boolean z) {
        this.directoriesDisabledForTesting = z;
    }

    public void setQuery(String str, CallInitiationType$Type callInitiationType$Type) {
        this.query = str;
        this.callInitiationType = callInitiationType$Type;
        SearchAdapter searchAdapter = this.adapter;
        if (searchAdapter != null) {
            searchAdapter.setQuery(str, this.rawNumber);
            this.adapter.setSearchActions(getActions());
            showLocationPermission();
            DialerExecutorModule.getUiThreadHandler().removeCallbacks(this.loaderCp2ContactsRunnable);
            DialerExecutorModule.getUiThreadHandler().postDelayed(this.loaderCp2ContactsRunnable, 300);
            loadNearbyPlacesCursor();
            loadDirectoryContactsCursors();
        }
    }

    public void setRawNumber(String str) {
        this.rawNumber = str;
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        LogUtil.m9i("NewSearchFragment.onLoadFinished", GeneratedOutlineSupport.outline6("Loader finished: ", loader), new Object[0]);
        if (cursor != null && !(loader instanceof DirectoriesCursorLoader) && !(cursor instanceof SearchCursor)) {
            throw new IllegalStateException("Cursors must implement SearchCursor");
        } else if (loader instanceof SearchContactsCursorLoader) {
            this.adapter.setContactsCursor((SearchCursor) cursor);
        } else if (loader instanceof NearbyPlacesCursorLoader) {
            this.adapter.setNearbyPlacesCursor((SearchCursor) cursor);
        } else if (loader instanceof DirectoryContactsCursorLoader) {
            this.adapter.setDirectoryContactsCursor((SearchCursor) cursor);
        } else if (loader instanceof DirectoriesCursorLoader) {
            this.directories.clear();
            this.directories.addAll(DirectoriesCursorLoader.toDirectories(cursor));
            loadNearbyPlacesCursor();
            loadDirectoryContactsCursors();
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline6("Invalid loader: ", loader));
        }
    }
}
