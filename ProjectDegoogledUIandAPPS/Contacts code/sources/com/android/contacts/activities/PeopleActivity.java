package com.android.contacts.activities;

import android.accounts.Account;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncStatusObserver;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.android.contacts.AppCompatContactsActivity;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.drawer.DrawerFragment;
import com.android.contacts.editor.SelectAccountDialogFragment;
import com.android.contacts.group.GroupListItem;
import com.android.contacts.group.GroupMembersFragment;
import com.android.contacts.group.GroupNameEditDialogFragment;
import com.android.contacts.group.GroupUtil;
import com.android.contacts.list.ContactListFilter;
import com.android.contacts.list.ContactListFilterController;
import com.android.contacts.list.ContactsIntentResolver;
import com.android.contacts.list.ContactsRequest;
import com.android.contacts.list.ContactsUnavailableFragment;
import com.android.contacts.list.DefaultContactBrowseListFragment;
import com.android.contacts.list.MultiSelectContactsListFragment;
import com.android.contacts.list.ProviderStatusWatcher;
import com.android.contacts.logging.Logger;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.preference.ContactsPreferenceActivity;
import com.android.contacts.util.AccountFilterUtil;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contacts.util.SharedPreferenceUtil;
import com.android.contacts.util.SyncUtil;
import com.android.contacts.util.ViewUtil;
import com.android.contacts.widget.FloatingActionButtonController;
import com.android.contactsbind.FeatureHighlightHelper;
import com.android.contactsbind.ObjectFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.util.concurrent.Futures;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PeopleActivity extends AppCompatContactsActivity implements DrawerFragment.DrawerFragmentListener, SelectAccountDialogFragment.Listener {
    private static final AtomicInteger sNextInstanceId = new AtomicInteger();
    private AccountTypeManager mAccountTypeManager;
    /* access modifiers changed from: private */
    public ContactListFilterController mContactListFilterController;
    private DefaultContactBrowseListFragment mContactsListFragment;
    private ContactsView mCurrentView;
    private DrawerFragment mDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private final ContactListFilterController.ContactListFilterListener mFilterListener = new ContactListFilterController.ContactListFilterListener() {
        public void onContactListFilterChanged() {
            ContactListFilter filter = PeopleActivity.this.mContactListFilterController.getFilter();
            PeopleActivity.this.handleFilterChangeForFragment(filter);
            PeopleActivity.this.handleFilterChangeForActivity(filter);
        }
    };
    private View mFloatingActionButtonContainer;
    private FloatingActionButtonController mFloatingActionButtonController;
    private Uri mGroupUri;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    private final int mInstanceId = sNextInstanceId.getAndIncrement();
    private ContactsIntentResolver mIntentResolver = new ContactsIntentResolver(this);
    private boolean mIsRecreatedInstance;
    private CoordinatorLayout mLayoutRoot;
    private GroupMembersFragment mMembersFragment;
    private AccountWithDataSet mNewGroupAccount;
    private Integer mProviderStatus;
    private final ProviderStatusWatcher.ProviderStatusListener mProviderStatusListener = new ProviderStatusWatcher.ProviderStatusListener() {
        public void onProviderStatusChange() {
            PeopleActivity.this.updateViewConfiguration(false);
        }
    };
    private ProviderStatusWatcher mProviderStatusWatcher = ProviderStatusWatcher.getInstance(this);
    private ContactsRequest mRequest;
    private BroadcastReceiver mSaveServiceListener;
    private boolean mShouldSwitchToAllContacts;
    private boolean mShouldSwitchToGroupView;
    private Object mStatusChangeListenerHandle;
    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() {
        public void onStatusChanged(int i) {
            PeopleActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    PeopleActivity.this.onSyncStateUpdated();
                }
            });
        }
    };
    private ContactsActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private boolean wasLastFabAnimationScaleIn = false;

    public enum ContactsView {
        NONE,
        ALL_CONTACTS,
        ASSISTANT,
        GROUP_VIEW,
        ACCOUNT_VIEW
    }

    public void onAccountSelectorCancelled() {
    }

    public void onLaunchHelpFeedback() {
    }

    /* access modifiers changed from: private */
    public void onSyncStateUpdated() {
        ContactListFilter filter;
        List<AccountWithDataSet> list;
        if (!isListFragmentInSearchMode() && !isListFragmentInSelectionMode() && (filter = this.mContactListFilterController.getFilter()) != null) {
            SwipeRefreshLayout swipeRefreshLayout = this.mContactsListFragment.getSwipeRefreshLayout();
            if (swipeRefreshLayout != null) {
                if (filter.filterType == 0 && filter.isGoogleAccountType()) {
                    list = Collections.singletonList(new AccountWithDataSet(filter.accountName, filter.accountType, (String) null));
                } else if (filter.shouldShowSyncState()) {
                    list = AccountInfo.extractAccounts(this.mAccountTypeManager.getWritableGoogleAccounts());
                } else {
                    list = Collections.emptyList();
                }
                if (!SyncUtil.isAnySyncing(list)) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            } else if (Log.isLoggable("PeopleActivity", 3)) {
                Log.d("PeopleActivity", "Can not load swipeRefreshLayout, swipeRefreshLayout is null");
            }
        }
    }

    public void showConnectionErrorMsg() {
        Snackbar.make((View) this.mLayoutRoot, (int) R.string.connection_error_message, 0).show();
    }

    private class ContactsActionBarDrawerToggle extends ActionBarDrawerToggle {
        private boolean mMenuClickedBefore = SharedPreferenceUtil.getHamburgerMenuClickedBefore(PeopleActivity.this);

        public ContactsActionBarDrawerToggle(AppCompatActivity appCompatActivity, DrawerLayout drawerLayout, Toolbar toolbar, int i, int i2) {
            super(appCompatActivity, drawerLayout, toolbar, i, i2);
        }

        public void onDrawerOpened(View view) {
            super.onDrawerOpened(view);
            if (!this.mMenuClickedBefore) {
                SharedPreferenceUtil.setHamburgerMenuClickedBefore(PeopleActivity.this);
                this.mMenuClickedBefore = true;
            }
            view.requestFocus();
            PeopleActivity.this.invalidateOptionsMenu();
            stopSearchAndSelection();
            PeopleActivity.this.updateStatusBarBackground();
        }

        private void stopSearchAndSelection() {
            MultiSelectContactsListFragment multiSelectContactsListFragment;
            ActionBarAdapter actionBarAdapter;
            if (PeopleActivity.this.isAllContactsView() || PeopleActivity.this.isAccountView()) {
                multiSelectContactsListFragment = PeopleActivity.this.getListFragment();
            } else {
                multiSelectContactsListFragment = PeopleActivity.this.isGroupView() ? PeopleActivity.this.getGroupFragment() : null;
            }
            if (multiSelectContactsListFragment != null && (actionBarAdapter = multiSelectContactsListFragment.getActionBarAdapter()) != null) {
                if (actionBarAdapter.isSearchMode()) {
                    actionBarAdapter.setSearchMode(false);
                } else if (actionBarAdapter.isSelectionMode()) {
                    actionBarAdapter.setSelectionMode(false);
                }
            }
        }

        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            PeopleActivity.this.invalidateOptionsMenu();
        }

        public void onDrawerStateChanged(int i) {
            super.onDrawerStateChanged(i);
            if (i != 0) {
                PeopleActivity.this.updateStatusBarBackground();
            }
        }
    }

    public String toString() {
        return String.format("%s@%d", new Object[]{PeopleActivity.class.getSimpleName(), Integer.valueOf(this.mInstanceId)});
    }

    private boolean areContactsAvailable() {
        Integer num = this.mProviderStatus;
        return num != null && num.equals(0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "PeopleActivity.onCreate start");
        }
        setTheme(R.style.PeopleActivityTheme);
        super.onCreate(bundle);
        this.mAccountTypeManager = AccountTypeManager.getInstance(this);
        this.mContactListFilterController = ContactListFilterController.getInstance(this);
        RequestPermissionsActivity.startPermissionActivityIfNeeded(this);
        boolean z = false;
        if (!processIntent(false)) {
            finish();
            return;
        }
        this.mContactListFilterController.checkFilterValidity(false);
        super.setContentView((int) R.layout.contacts_drawer_activity);
        this.mToolbar = (Toolbar) getView(R.id.toolbar);
        setSupportActionBar(this.mToolbar);
        ViewUtil.addRectangularOutlineProvider(findViewById(R.id.toolbar_parent), getResources());
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mDrawerFragment = (DrawerFragment) getFragmentManager().findFragmentById(R.id.drawer);
        this.mToggle = new ContactsActionBarDrawerToggle(this, this.mDrawerLayout, this.mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.mDrawerLayout.setDrawerListener(this.mToggle);
        this.mToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PeopleActivity.this.onBackPressed();
            }
        });
        if (bundle != null) {
            this.mCurrentView = ContactsView.values()[bundle.getInt("contactsView")];
        } else {
            this.mCurrentView = ContactsView.ALL_CONTACTS;
        }
        if (bundle != null && bundle.containsKey("newGroupAccount")) {
            this.mNewGroupAccount = AccountWithDataSet.unstringify(bundle.getString("newGroupAccount"));
        }
        this.mContactListFilterController.addListener(this.mFilterListener);
        this.mProviderStatusWatcher.addListener(this.mProviderStatusListener);
        if (bundle != null) {
            z = true;
        }
        this.mIsRecreatedInstance = z;
        if (this.mIsRecreatedInstance) {
            this.mGroupUri = (Uri) bundle.getParcelable("groupUri");
        }
        createViewsAndFragments();
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "PeopleActivity.onCreate finish");
        }
        getWindow().setBackgroundDrawable((Drawable) null);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        String action = intent.getAction();
        if ("createGroup".equals(action)) {
            this.mGroupUri = intent.getData();
            if (this.mGroupUri == null) {
                toast(R.string.groupSavedErrorToast);
                return;
            }
            if (Log.isLoggable("PeopleActivity", 2)) {
                Log.v("PeopleActivity", "Received group URI " + this.mGroupUri);
            }
            switchView(ContactsView.GROUP_VIEW);
            this.mMembersFragment.toastForSaveAction(action);
            return;
        }
        if (isGroupSaveAction(action)) {
            this.mGroupUri = intent.getData();
            if (this.mGroupUri == null) {
                popSecondLevel();
                toast(R.string.groupSavedErrorToast);
                return;
            }
            if (Log.isLoggable("PeopleActivity", 2)) {
                Log.v("PeopleActivity", "Received group URI " + this.mGroupUri);
            }
            if (GroupUtil.ACTION_REMOVE_FROM_GROUP.equals(action)) {
                switchToOrUpdateGroupView(action);
            } else {
                switchView(ContactsView.GROUP_VIEW);
            }
            this.mMembersFragment.toastForSaveAction(action);
        }
        setIntent(intent);
        if (!processIntent(true)) {
            finish();
            return;
        }
        this.mContactListFilterController.checkFilterValidity(false);
        if (!isInSecondLevel()) {
            this.mContactsListFragment.setParameters(this.mRequest, true);
            this.mContactsListFragment.initializeActionBarAdapter((Bundle) null);
        }
        initializeFabVisibility();
        invalidateOptionsMenuIfNeeded();
    }

    private static boolean isGroupSaveAction(String str) {
        return "updateGroup".equals(str) || GroupUtil.ACTION_ADD_TO_GROUP.equals(str) || GroupUtil.ACTION_REMOVE_FROM_GROUP.equals(str);
    }

    private void toast(int i) {
        if (i >= 0) {
            Toast.makeText(this, i, 0).show();
        }
    }

    private boolean processIntent(boolean z) {
        this.mRequest = this.mIntentResolver.resolveIntent(getIntent());
        if (Log.isLoggable("PeopleActivity", 3)) {
            Log.d("PeopleActivity", this + " processIntent: forNewIntent=" + z + " intent=" + getIntent() + " request=" + this.mRequest);
        }
        if (!this.mRequest.isValid()) {
            setResult(0);
            return false;
        }
        int actionCode = this.mRequest.getActionCode();
        if (actionCode != 140) {
            switch (actionCode) {
                case 22:
                    onCreateGroupMenuItemClicked();
                    return true;
                case 23:
                case 24:
                    this.mShouldSwitchToGroupView = true;
                    return true;
                default:
                    return true;
            }
        } else {
            ImplicitIntentsUtil.startQuickContact(this, this.mRequest.getContactUri(), 0);
            return false;
        }
    }

    private void createViewsAndFragments() {
        setContentView(R.layout.people_activity);
        FragmentManager fragmentManager = getFragmentManager();
        setUpListFragment(fragmentManager);
        this.mMembersFragment = (GroupMembersFragment) fragmentManager.findFragmentByTag("contacts-groups");
        this.mFloatingActionButtonContainer = findViewById(R.id.floating_action_button_container);
        ImageButton imageButton = (ImageButton) findViewById(R.id.floating_action_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PeopleActivity peopleActivity = PeopleActivity.this;
                AccountFilterUtil.startEditorIntent(peopleActivity, peopleActivity.getIntent(), PeopleActivity.this.mContactListFilterController.getFilter());
            }
        });
        this.mFloatingActionButtonController = new FloatingActionButtonController(this, this.mFloatingActionButtonContainer, imageButton);
        invalidateOptionsMenuIfNeeded();
        this.mLayoutRoot = (CoordinatorLayout) findViewById(R.id.root);
        if (this.mShouldSwitchToGroupView && !this.mIsRecreatedInstance) {
            this.mGroupUri = this.mRequest.getContactUri();
            switchToOrUpdateGroupView(GroupUtil.ACTION_SWITCH_GROUP);
            this.mShouldSwitchToGroupView = false;
        }
    }

    public void setContentView(int i) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content_frame);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, viewGroup);
    }

    private void setUpListFragment(FragmentManager fragmentManager) {
        this.mContactsListFragment = (DefaultContactBrowseListFragment) fragmentManager.findFragmentByTag("contacts-all");
        if (this.mContactsListFragment == null) {
            this.mContactsListFragment = new DefaultContactBrowseListFragment();
            this.mContactsListFragment.setAnimateOnLoad(true);
            fragmentManager.beginTransaction().add(R.id.contacts_list_container, this.mContactsListFragment, "contacts-all").commit();
            fragmentManager.executePendingTransactions();
        }
        this.mContactsListFragment.setContactsAvailable(areContactsAvailable());
        this.mContactsListFragment.setListType(this.mContactListFilterController.getFilterListType());
        this.mContactsListFragment.setParameters(this.mRequest, false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mProviderStatusWatcher.stop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mSaveServiceListener);
        super.onPause();
        ContentResolver.removeStatusChangeListener(this.mStatusChangeListenerHandle);
        onSyncStateUpdated();
    }

    public void onMultiWindowModeChanged(boolean z) {
        initializeHomeVisibility();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mDrawerLayout.isDrawerOpen(8388611)) {
            updateStatusBarBackground();
        }
        if (this.mShouldSwitchToAllContacts) {
            switchToAllContacts();
        }
        this.mProviderStatusWatcher.start();
        updateViewConfiguration(true);
        this.mStatusChangeListenerHandle = ContentResolver.addStatusChangeListener(7, this.mSyncStatusObserver);
        onSyncStateUpdated();
        initializeFabVisibility();
        initializeHomeVisibility();
        this.mSaveServiceListener = new SaveServiceListener();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mSaveServiceListener, new IntentFilter(ContactSaveService.BROADCAST_GROUP_DELETED));
    }

    public void updateStatusBarBackground() {
        updateStatusBarBackground(-1);
    }

    public void updateStatusBarBackground(int i) {
        if (CompatUtils.isLollipopCompatible()) {
            if (i == -1) {
                this.mDrawerLayout.setStatusBarBackgroundColor(MaterialColorMapUtils.getStatusBarColor(this));
            } else {
                this.mDrawerLayout.setStatusBarBackgroundColor(i);
            }
            this.mDrawerLayout.invalidate();
            getWindow().setStatusBarColor(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mProviderStatusWatcher.removeListener(this.mProviderStatusListener);
        this.mContactListFilterController.removeListener(this.mFilterListener);
        super.onDestroy();
    }

    private void initializeFabVisibility() {
        this.mFloatingActionButtonContainer.setVisibility(shouldHideFab() ? 8 : 0);
        this.mFloatingActionButtonController.resetIn();
        this.wasLastFabAnimationScaleIn = !shouldHideFab();
    }

    private void initializeHomeVisibility() {
        if (getToolbar() == null) {
            return;
        }
        if (isListFragmentInSelectionMode() || isListFragmentInSearchMode() || isGroupsFragmentInSelectionMode() || isGroupsFragmentInSearchMode()) {
            getToolbar().setNavigationIcon((Drawable) null);
        }
    }

    private boolean shouldHideFab() {
        DefaultContactBrowseListFragment defaultContactBrowseListFragment = this.mContactsListFragment;
        if ((defaultContactBrowseListFragment == null || defaultContactBrowseListFragment.getActionBarAdapter() != null) && !isInSecondLevel() && !isListFragmentInSearchMode() && !isListFragmentInSelectionMode()) {
            return false;
        }
        return true;
    }

    public void showFabWithAnimation(boolean z) {
        View view = this.mFloatingActionButtonContainer;
        if (view != null) {
            if (z) {
                if (!this.wasLastFabAnimationScaleIn) {
                    view.setVisibility(0);
                    this.mFloatingActionButtonController.scaleIn(0);
                }
                this.wasLastFabAnimationScaleIn = true;
                return;
            }
            if (this.wasLastFabAnimationScaleIn) {
                view.setVisibility(0);
                this.mFloatingActionButtonController.scaleOut();
            }
            this.wasLastFabAnimationScaleIn = false;
        }
    }

    /* access modifiers changed from: private */
    public void updateViewConfiguration(boolean z) {
        Integer num;
        int providerStatus = this.mProviderStatusWatcher.getProviderStatus();
        if (z || (num = this.mProviderStatus) == null || !num.equals(Integer.valueOf(providerStatus))) {
            this.mProviderStatus = Integer.valueOf(providerStatus);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            if (!shouldShowList()) {
                DefaultContactBrowseListFragment defaultContactBrowseListFragment = this.mContactsListFragment;
                if (defaultContactBrowseListFragment != null) {
                    defaultContactBrowseListFragment.setEnabled(false);
                }
                ContactsUnavailableFragment contactsUnavailableFragment = new ContactsUnavailableFragment();
                beginTransaction.hide(this.mContactsListFragment);
                beginTransaction.replace(R.id.contacts_unavailable_container, contactsUnavailableFragment, "contacts-unavailable");
                contactsUnavailableFragment.updateStatus(this.mProviderStatus.intValue());
            } else if (this.mContactsListFragment != null) {
                Fragment findFragmentByTag = fragmentManager.findFragmentByTag("contacts-unavailable");
                if (findFragmentByTag != null) {
                    beginTransaction.remove(findFragmentByTag);
                }
                if (this.mContactsListFragment.isHidden()) {
                    beginTransaction.show(this.mContactsListFragment);
                }
                this.mContactsListFragment.setContactsAvailable(areContactsAvailable());
                this.mContactsListFragment.setEnabled(true);
            }
            if (!beginTransaction.isEmpty()) {
                beginTransaction.commit();
                fragmentManager.executePendingTransactions();
            }
            invalidateOptionsMenuIfNeeded();
        }
    }

    private boolean shouldShowList() {
        Integer num = this.mProviderStatus;
        if (num == null) {
            return false;
        }
        if ((!num.equals(2) || !this.mAccountTypeManager.hasNonLocalAccount()) && !this.mProviderStatus.equals(0)) {
            return false;
        }
        return true;
    }

    private void invalidateOptionsMenuIfNeeded() {
        DefaultContactBrowseListFragment defaultContactBrowseListFragment = this.mContactsListFragment;
        if (defaultContactBrowseListFragment != null && defaultContactBrowseListFragment.getOptionsMenuContactsAvailable() != areContactsAvailable()) {
            invalidateOptionsMenu();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!this.mDrawerLayout.isDrawerOpen(8388611)) {
            int unicodeChar = keyEvent.getUnicodeChar();
            if (unicodeChar == 0 || (Integer.MIN_VALUE & unicodeChar) != 0 || Character.isWhitespace(unicodeChar) || !this.mContactsListFragment.onKeyDown(unicodeChar)) {
                return super.onKeyDown(i, keyEvent);
            }
            return true;
        } else if (i == 4) {
            return super.onKeyDown(i, keyEvent);
        } else {
            return false;
        }
    }

    public void onBackPressed() {
        if (isSafeToCommitTransactions()) {
            if (this.mDrawerLayout.isDrawerOpen(8388611)) {
                closeDrawer();
            } else if (isGroupView()) {
                onBackPressedGroupView();
            } else if (isAssistantView()) {
                onBackPressedAssistantView();
            } else if (!FeatureHighlightHelper.tryRemoveHighlight(this) && !maybeHandleInListFragment()) {
                super.onBackPressed();
            }
        }
    }

    private void onBackPressedGroupView() {
        if (this.mMembersFragment.isEditMode()) {
            this.mMembersFragment.exitEditMode();
        } else if (this.mMembersFragment.getActionBarAdapter().isSelectionMode()) {
            this.mMembersFragment.getActionBarAdapter().setSelectionMode(false);
            this.mMembersFragment.displayCheckBoxes(false);
        } else if (this.mMembersFragment.getActionBarAdapter().isSearchMode()) {
            this.mMembersFragment.getActionBarAdapter().setSearchMode(false);
        } else {
            switchToAllContacts();
        }
    }

    private void onBackPressedAssistantView() {
        if (!isInThirdLevel()) {
            switchToAllContacts();
            return;
        }
        setDrawerLockMode(true);
        super.onBackPressed();
    }

    private boolean maybeHandleInListFragment() {
        if (isListFragmentInSelectionMode()) {
            this.mContactsListFragment.getActionBarAdapter().setSelectionMode(false);
            return true;
        } else if (isListFragmentInSearchMode()) {
            this.mContactsListFragment.getActionBarAdapter().setSearchMode(false);
            if (this.mContactsListFragment.wasSearchResultClicked()) {
                this.mContactsListFragment.resetSearchResultClicked();
            } else {
                Logger.logScreenView(this, 2);
                Logger.logSearchEvent(this.mContactsListFragment.createSearchState());
            }
            return true;
        } else if (AccountFilterUtil.isAllContactsFilter(this.mContactListFilterController.getFilter()) || this.mContactsListFragment.isHidden()) {
            return false;
        } else {
            switchToAllContacts();
            return true;
        }
    }

    private boolean isListFragmentInSelectionMode() {
        DefaultContactBrowseListFragment defaultContactBrowseListFragment = this.mContactsListFragment;
        return (defaultContactBrowseListFragment == null || defaultContactBrowseListFragment.getActionBarAdapter() == null || !this.mContactsListFragment.getActionBarAdapter().isSelectionMode()) ? false : true;
    }

    private boolean isListFragmentInSearchMode() {
        DefaultContactBrowseListFragment defaultContactBrowseListFragment = this.mContactsListFragment;
        return (defaultContactBrowseListFragment == null || defaultContactBrowseListFragment.getActionBarAdapter() == null || !this.mContactsListFragment.getActionBarAdapter().isSearchMode()) ? false : true;
    }

    private boolean isGroupsFragmentInSelectionMode() {
        GroupMembersFragment groupMembersFragment = this.mMembersFragment;
        return (groupMembersFragment == null || groupMembersFragment.getActionBarAdapter() == null || !this.mMembersFragment.getActionBarAdapter().isSelectionMode()) ? false : true;
    }

    private boolean isGroupsFragmentInSearchMode() {
        GroupMembersFragment groupMembersFragment = this.mMembersFragment;
        return (groupMembersFragment == null || groupMembersFragment.getActionBarAdapter() == null || !this.mMembersFragment.getActionBarAdapter().isSearchMode()) ? false : true;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        AccountWithDataSet accountWithDataSet = this.mNewGroupAccount;
        if (accountWithDataSet != null) {
            bundle.putString("newGroupAccount", accountWithDataSet.stringify());
        }
        bundle.putInt("contactsView", this.mCurrentView.ordinal());
        bundle.putParcelable("groupUri", this.mGroupUri);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mGroupUri = (Uri) bundle.getParcelable("groupUri");
    }

    /* access modifiers changed from: private */
    public void onGroupDeleted(final Intent intent) {
        if (ContactSaveService.canUndo(intent)) {
            int i = ((AccessibilityManager) getSystemService("accessibility")).isEnabled() ? 15000 : 0;
            String string = getString(R.string.groupDeletedToast);
            Snackbar make = Snackbar.make((View) this.mLayoutRoot, (CharSequence) string, i);
            make.setAction((int) R.string.undo, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    PeopleActivity peopleActivity = PeopleActivity.this;
                    ContactSaveService.startService(peopleActivity, ContactSaveService.createUndoIntent(peopleActivity, intent));
                }
            });
            make.setActionTextColor(ContextCompat.getColor(this, R.color.snackbar_action_text));
            this.mLayoutRoot.announceForAccessibility(string);
            this.mLayoutRoot.announceForAccessibility(getString(R.string.undo));
            make.show();
        }
    }

    private class SaveServiceListener extends BroadcastReceiver {
        private SaveServiceListener() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (((action.hashCode() == 1201034394 && action.equals(ContactSaveService.BROADCAST_GROUP_DELETED)) ? (char) 0 : 65535) == 0) {
                PeopleActivity.this.onGroupDeleted(intent);
            }
        }
    }

    private void onGroupMenuItemClicked(long j) {
        GroupMembersFragment groupMembersFragment;
        if (!isGroupView() || (groupMembersFragment = this.mMembersFragment) == null || !groupMembersFragment.isCurrentGroup(j)) {
            this.mGroupUri = ContentUris.withAppendedId(ContactsContract.Groups.CONTENT_URI, j);
            switchToOrUpdateGroupView(GroupUtil.ACTION_SWITCH_GROUP);
        }
    }

    private void onFilterMenuItemClicked(Intent intent) {
        if (isInSecondLevel()) {
            popSecondLevel();
            showFabWithAnimation(true);
            ContactListFilter filter = this.mContactListFilterController.getFilter();
            this.mContactListFilterController.setContactListFilter(AccountFilterUtil.createContactsFilter(this), false);
            this.mContactListFilterController.setContactListFilter(filter, false);
        }
        this.mCurrentView = ContactsView.ACCOUNT_VIEW;
        AccountFilterUtil.handleAccountFilterResult(this.mContactListFilterController, -1, intent);
    }

    private void switchToOrUpdateGroupView(String str) {
        GroupMembersFragment groupMembersFragment = this.mMembersFragment;
        if (groupMembersFragment == null || groupMembersFragment.isInactive()) {
            switchView(ContactsView.GROUP_VIEW);
        } else {
            this.mMembersFragment.updateExistingGroupFragment(this.mGroupUri, str);
        }
    }

    /* access modifiers changed from: protected */
    public void launchAssistant() {
        switchView(ContactsView.ASSISTANT);
    }

    private void switchView(ContactsView contactsView) {
        this.mCurrentView = contactsView;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        popSecondLevel();
        if (isGroupView()) {
            this.mMembersFragment = GroupMembersFragment.newInstance(this.mGroupUri);
            beginTransaction.replace(R.id.contacts_list_container, this.mMembersFragment, "contacts-groups");
        } else if (isAssistantView()) {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag("contacts-assistant");
            Fragment findFragmentByTag2 = fragmentManager.findFragmentByTag("contacts-unavailable");
            if (findFragmentByTag == null) {
                findFragmentByTag = ObjectFactory.getAssistantFragment();
            }
            if (findFragmentByTag2 != null) {
                beginTransaction.remove(findFragmentByTag2);
            }
            beginTransaction.replace(R.id.contacts_list_container, findFragmentByTag, "contacts-assistant");
            resetToolBarStatusBarColor();
        }
        beginTransaction.addToBackStack("second-level");
        beginTransaction.commit();
        fragmentManager.executePendingTransactions();
        showFabWithAnimation(false);
    }

    public void switchToAllContacts() {
        popSecondLevel();
        this.mShouldSwitchToAllContacts = false;
        ContactsView contactsView = ContactsView.ALL_CONTACTS;
        this.mCurrentView = contactsView;
        this.mDrawerFragment.setNavigationItemChecked(contactsView);
        showFabWithAnimation(true);
        this.mContactsListFragment.scrollToTop();
        resetFilter();
        setTitle(getString(R.string.contactsList));
    }

    private void resetFilter() {
        Intent intent = new Intent();
        intent.putExtra("contactListFilter", AccountFilterUtil.createContactsFilter(this));
        AccountFilterUtil.handleAccountFilterResult(this.mContactListFilterController, -1, intent);
    }

    private void resetToolBarStatusBarColor() {
        findViewById(R.id.toolbar_frame).setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
        updateStatusBarBackground(ContextCompat.getColor(this, R.color.primary_color));
    }

    /* access modifiers changed from: protected */
    public DefaultContactBrowseListFragment getListFragment() {
        return this.mContactsListFragment;
    }

    /* access modifiers changed from: protected */
    public GroupMembersFragment getGroupFragment() {
        return this.mMembersFragment;
    }

    /* access modifiers changed from: private */
    public void handleFilterChangeForFragment(ContactListFilter contactListFilter) {
        if (this.mContactsListFragment.canSetActionBar()) {
            this.mContactsListFragment.setFilterAndUpdateTitle(contactListFilter);
            this.mContactsListFragment.scrollToTop();
        }
    }

    /* access modifiers changed from: private */
    public void handleFilterChangeForActivity(ContactListFilter contactListFilter) {
        if (isAssistantView() && contactListFilter.isContactsFilterType()) {
            this.mShouldSwitchToAllContacts = true;
        }
        if (CompatUtils.isNCompatible()) {
            getWindow().getDecorView().sendAccessibilityEvent(32);
        }
        invalidateOptionsMenu();
    }

    public void updateDrawerGroupMenu(long j) {
        DrawerFragment drawerFragment = this.mDrawerFragment;
        if (drawerFragment != null) {
            drawerFragment.updateGroupMenu(j);
        }
    }

    public void setDrawerLockMode(boolean z) {
        this.mDrawerLayout.setDrawerLockMode(z ^ true ? 1 : 0);
        if (z) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            this.mToggle.setDrawerIndicatorEnabled(true);
            return;
        }
        this.mToggle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mToggle.syncState();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mToggle.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreateGroupMenuItemClicked() {
        Account account;
        String str;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            account = null;
        } else {
            account = (Account) extras.getParcelable("android.provider.extra.ACCOUNT");
        }
        if (account == null) {
            selectAccountForNewGroup();
            return;
        }
        if (extras == null) {
            str = null;
        } else {
            str = extras.getString("android.provider.extra.DATA_SET");
        }
        onAccountChosen(new AccountWithDataSet(account.name, account.type, str), (Bundle) null);
    }

    private void selectAccountForNewGroup() {
        List list = (List) Futures.getUnchecked(AccountTypeManager.getInstance(this).filterAccountsAsync(AccountTypeManager.AccountFilter.GROUPS_WRITABLE));
        if (list.isEmpty()) {
            Toast.makeText(this, R.string.groupCreateFailedToast, 0).show();
        } else if (list.size() == 1) {
            onAccountChosen(((AccountInfo) list.get(0)).getAccount(), (Bundle) null);
        } else {
            SelectAccountDialogFragment.show(getFragmentManager(), R.string.dialog_new_group_account, AccountTypeManager.AccountFilter.GROUPS_WRITABLE, (Bundle) null, "selectAccountDialog");
        }
    }

    public void onAccountChosen(AccountWithDataSet accountWithDataSet, Bundle bundle) {
        this.mNewGroupAccount = accountWithDataSet;
        GroupNameEditDialogFragment.newInstanceForCreation(this.mNewGroupAccount, "createGroup").show(getFragmentManager(), "groupNameEditDialog");
    }

    public void onDrawerItemClicked() {
        closeDrawer();
    }

    public void onContactsViewSelected(ContactsView contactsView) {
        if (contactsView == ContactsView.ALL_CONTACTS) {
            switchToAllContacts();
        } else if (contactsView == ContactsView.ASSISTANT) {
            launchAssistant();
        } else {
            throw new IllegalStateException("Unknown view " + contactsView);
        }
    }

    public void onCreateLabelButtonClicked() {
        onCreateGroupMenuItemClicked();
    }

    public void onOpenSettings() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PeopleActivity peopleActivity = PeopleActivity.this;
                peopleActivity.startActivity(peopleActivity.createPreferenceIntent());
            }
        }, 300);
    }

    public void onGroupViewSelected(GroupListItem groupListItem) {
        onGroupMenuItemClicked(groupListItem.getGroupId());
    }

    public void onAccountViewSelected(ContactListFilter contactListFilter) {
        Intent intent = new Intent();
        intent.putExtra("contactListFilter", contactListFilter);
        onFilterMenuItemClicked(intent);
    }

    public void onProfileViewSelected(long j) {
        if (j != -1) {
            ImplicitIntentsUtil.startQuickContact(this, ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, j), 10);
            return;
        }
        Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("newLocalProfile", true);
        ImplicitIntentsUtil.startActivityInApp(this, intent);
    }

    public void onEmergencyViewSelected() {
        ImplicitIntentsUtil.startActivityOutsideApp(this, ImplicitIntentsUtil.getIntentForEmergencyInfo(this));
    }

    public boolean isGroupView() {
        return this.mCurrentView == ContactsView.GROUP_VIEW;
    }

    /* access modifiers changed from: protected */
    public boolean isAssistantView() {
        return this.mCurrentView == ContactsView.ASSISTANT;
    }

    /* access modifiers changed from: protected */
    public boolean isAllContactsView() {
        return this.mCurrentView == ContactsView.ALL_CONTACTS;
    }

    /* access modifiers changed from: protected */
    public boolean isAccountView() {
        return this.mCurrentView == ContactsView.ACCOUNT_VIEW;
    }

    public boolean isInSecondLevel() {
        return isGroupView() || isAssistantView();
    }

    private boolean isInThirdLevel() {
        return isLastBackStackTag("third-level");
    }

    private boolean isLastBackStackTag(String str) {
        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount <= 0) {
            return false;
        }
        FragmentManager.BackStackEntry backStackEntryAt = getFragmentManager().getBackStackEntryAt(backStackEntryCount - 1);
        if (str != null) {
            return str.equals(backStackEntryAt.getName());
        }
        if (backStackEntryAt.getName() == null) {
            return true;
        }
        return false;
    }

    private void popSecondLevel() {
        getFragmentManager().popBackStackImmediate("assistant-helper", 1);
        getFragmentManager().popBackStackImmediate("second-level", 1);
        this.mMembersFragment = null;
        resetToolBarStatusBarColor();
    }

    public void closeDrawer() {
        this.mDrawerLayout.closeDrawer(8388611);
    }

    /* access modifiers changed from: private */
    public Intent createPreferenceIntent() {
        Intent intent = new Intent(this, ContactsPreferenceActivity.class);
        intent.putExtra("newLocalProfile", "newLocalProfile");
        return intent;
    }
}
