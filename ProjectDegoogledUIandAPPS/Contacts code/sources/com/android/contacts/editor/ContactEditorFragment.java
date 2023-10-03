package com.android.contacts.editor;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.android.contacts.ContactSaveService;
import com.android.contacts.GroupMetaDataLoader;
import com.android.contacts.R;
import com.android.contacts.activities.ContactEditorAccountsChangedActivity;
import com.android.contacts.activities.ContactEditorActivity;
import com.android.contacts.activities.ContactSelectionActivity;
import com.android.contacts.activities.RequestPermissionsActivity;
import com.android.contacts.editor.AggregationSuggestionEngine;
import com.android.contacts.editor.AggregationSuggestionView;
import com.android.contacts.editor.CancelEditDialogFragment;
import com.android.contacts.editor.JoinContactConfirmationDialogFragment;
import com.android.contacts.editor.PhotoEditorView;
import com.android.contacts.editor.RawContactEditorView;
import com.android.contacts.editor.SplitContactConfirmationDialogFragment;
import com.android.contacts.group.GroupUtil;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.Contact;
import com.android.contacts.model.ContactLoader;
import com.android.contacts.model.RawContact;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.RawContactDeltaList;
import com.android.contacts.model.RawContactModifier;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountType;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.model.account.AccountsLoader;
import com.android.contacts.preference.ContactsPreferences;
import com.android.contacts.quickcontact.InvisibleContactUtil;
import com.android.contacts.util.ContactDisplayUtils;
import com.android.contacts.util.ContactPhotoUtils;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contacts.util.UiClosables;
import com.android.contactsbind.HelpUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ContactEditorFragment extends Fragment implements ContactEditorActivity.ContactEditor, SplitContactConfirmationDialogFragment.Listener, JoinContactConfirmationDialogFragment.Listener, AggregationSuggestionEngine.Listener, AggregationSuggestionView.Listener, CancelEditDialogFragment.Listener, RawContactEditorView.Listener, PhotoEditorView.Listener, AccountsLoader.AccountsListener {
    private static final List<String> VALID_INTENT_ACTIONS = new ArrayList<String>() {
        {
            add("android.intent.action.EDIT");
            add("android.intent.action.INSERT");
            add("saveCompleted");
        }
    };
    private InputMethodManager inputMethodManager;
    protected AccountWithDataSet mAccountWithDataSet;
    protected String mAction;
    private AggregationSuggestionEngine mAggregationSuggestionEngine;
    protected ListPopupWindow mAggregationSuggestionPopup;
    private long mAggregationSuggestionsRawContactId;
    protected boolean mAutoAddToDefaultGroup;
    protected RawContactDeltaComparator mComparator;
    protected Contact mContact;
    protected long mContactIdForJoin;
    protected final LoaderManager.LoaderCallbacks<Contact> mContactLoaderListener = new LoaderManager.LoaderCallbacks<Contact>() {
        protected long mLoaderStartTime;

        public void onLoaderReset(Loader<Contact> loader) {
        }

        public Loader<Contact> onCreateLoader(int i, Bundle bundle) {
            this.mLoaderStartTime = SystemClock.elapsedRealtime();
            ContactEditorFragment contactEditorFragment = ContactEditorFragment.this;
            return new ContactLoader(contactEditorFragment.mContext, contactEditorFragment.mLookupUri, true, true);
        }

        public void onLoadFinished(Loader<Contact> loader, Contact contact) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (Log.isLoggable("ContactEditor", 2)) {
                Log.v("ContactEditor", "Time needed for loading: " + (elapsedRealtime - this.mLoaderStartTime));
            }
            if (!contact.isLoaded()) {
                Log.i("ContactEditor", "No contact found. Closing activity");
                ContactEditorFragment contactEditorFragment = ContactEditorFragment.this;
                contactEditorFragment.mStatus = 3;
                Listener listener = contactEditorFragment.mListener;
                if (listener != null) {
                    listener.onContactNotFound();
                    return;
                }
                return;
            }
            ContactEditorFragment contactEditorFragment2 = ContactEditorFragment.this;
            contactEditorFragment2.mStatus = 1;
            contactEditorFragment2.mLookupUri = contact.getLookupUri();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            ContactEditorFragment.this.setState(contact);
            long elapsedRealtime3 = SystemClock.elapsedRealtime();
            if (Log.isLoggable("ContactEditor", 2)) {
                Log.v("ContactEditor", "Time needed for setting UI: " + (elapsedRealtime3 - elapsedRealtime2));
            }
        }
    };
    protected LinearLayout mContent;
    protected Context mContext;
    protected boolean mCopyReadOnlyName;
    protected boolean mDisableDeleteMenuOption;
    protected ContactEditorUtils mEditorUtils;
    private boolean mEnabled = true;
    protected boolean mExistingContactDataReady;
    protected Cursor mGroupMetaData;
    protected final LoaderManager.LoaderCallbacks<Cursor> mGroupsLoaderListener = new LoaderManager.LoaderCallbacks<Cursor>() {
        public void onLoaderReset(Loader<Cursor> loader) {
        }

        public CursorLoader onCreateLoader(int i, Bundle bundle) {
            return new GroupMetaDataLoader(ContactEditorFragment.this.mContext, ContactsContract.Groups.CONTENT_URI, GroupUtil.ALL_GROUPS_SELECTION);
        }

        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            ContactEditorFragment contactEditorFragment = ContactEditorFragment.this;
            contactEditorFragment.mGroupMetaData = cursor;
            contactEditorFragment.setGroupMetaData();
        }
    };
    protected boolean mHasNewContact;
    protected Bundle mIntentExtras;
    protected boolean mIsEdit;
    protected boolean mIsUserProfile;
    protected Listener mListener;
    protected Uri mLookupUri;
    protected MaterialColorMapUtils.MaterialPalette mMaterialPalette;
    protected boolean mNewContactAccountChanged;
    protected boolean mNewContactDataReady;
    protected boolean mNewLocalProfile;
    private long mPhotoRawContactId;
    protected long mRawContactIdToDisplayAlone = -1;
    protected ImmutableList<RawContact> mRawContacts;
    protected long mReadOnlyDisplayNameId;
    protected RawContactDeltaList mState;
    protected int mStatus;
    private Bundle mUpdatedPhotos = new Bundle();
    protected ViewIdGenerator mViewIdGenerator;
    protected List<AccountInfo> mWritableAccounts = Collections.emptyList();

    public interface Listener {
        void onContactNotFound();

        void onContactSplit(Uri uri);

        void onDeleteRequested(Uri uri);

        void onEditOtherRawContactRequested(Uri uri, long j, ArrayList<ContentValues> arrayList);

        void onReverted();

        void onSaveFinished(Intent intent);
    }

    public void onSplitContactCanceled() {
    }

    private static final class AggregationSuggestionAdapter extends BaseAdapter {
        private final LayoutInflater mLayoutInflater;
        private final AggregationSuggestionView.Listener mListener;
        private final List<AggregationSuggestionEngine.Suggestion> mSuggestions;

        public long getItemId(int i) {
            return (long) i;
        }

        public AggregationSuggestionAdapter(Activity activity, AggregationSuggestionView.Listener listener, List<AggregationSuggestionEngine.Suggestion> list) {
            this.mLayoutInflater = activity.getLayoutInflater();
            this.mListener = listener;
            this.mSuggestions = list;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            AggregationSuggestionView aggregationSuggestionView = (AggregationSuggestionView) this.mLayoutInflater.inflate(R.layout.aggregation_suggestions_item, (ViewGroup) null);
            aggregationSuggestionView.setListener(this.mListener);
            aggregationSuggestionView.bindSuggestion((AggregationSuggestionEngine.Suggestion) getItem(i));
            return aggregationSuggestionView;
        }

        public Object getItem(int i) {
            return this.mSuggestions.get(i);
        }

        public int getCount() {
            return this.mSuggestions.size();
        }
    }

    public Context getContext() {
        return getActivity();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
        this.mEditorUtils = ContactEditorUtils.create(this.mContext);
        this.mComparator = new RawContactDeltaComparator(this.mContext);
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mAction = bundle.getString("action");
            this.mLookupUri = (Uri) bundle.getParcelable("uri");
        }
        super.onCreate(bundle);
        this.inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (bundle == null) {
            this.mViewIdGenerator = new ViewIdGenerator();
            this.mState = new RawContactDeltaList();
            return;
        }
        this.mViewIdGenerator = (ViewIdGenerator) bundle.getParcelable("viewidgenerator");
        this.mAutoAddToDefaultGroup = bundle.getBoolean("autoAddToDefaultGroup");
        this.mDisableDeleteMenuOption = bundle.getBoolean("disableDeleteMenuOption");
        this.mNewLocalProfile = bundle.getBoolean("newLocalProfile");
        this.mMaterialPalette = (MaterialColorMapUtils.MaterialPalette) bundle.getParcelable("materialPalette");
        this.mAccountWithDataSet = (AccountWithDataSet) bundle.getParcelable("saveToAccount");
        this.mRawContacts = ImmutableList.copyOf(bundle.getParcelableArrayList("rawContacts"));
        this.mState = (RawContactDeltaList) bundle.getParcelable(ContactSaveService.EXTRA_CONTACT_STATE);
        this.mStatus = bundle.getInt("status");
        this.mHasNewContact = bundle.getBoolean("hasNewContact");
        this.mNewContactDataReady = bundle.getBoolean("newContactDataReady");
        this.mIsEdit = bundle.getBoolean("isEdit");
        this.mExistingContactDataReady = bundle.getBoolean("existingContactDataReady");
        this.mIsUserProfile = bundle.getBoolean("isUserProfile");
        this.mEnabled = bundle.getBoolean("enabled");
        this.mAggregationSuggestionsRawContactId = bundle.getLong("aggregationSuggestionsRawContactId");
        this.mContactIdForJoin = bundle.getLong("contactidforjoin");
        this.mReadOnlyDisplayNameId = bundle.getLong("readOnlyDisplayNameId");
        this.mCopyReadOnlyName = bundle.getBoolean("copyReadOnlyDisplayName", false);
        this.mPhotoRawContactId = bundle.getLong("photo_raw_contact_id");
        this.mUpdatedPhotos = (Bundle) bundle.getParcelable("updated_photos");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setHasOptionsMenu(true);
        View inflate = layoutInflater.inflate(R.layout.contact_editor_fragment, viewGroup, false);
        this.mContent = (LinearLayout) inflate.findViewById(R.id.raw_contacts_editor_view);
        return inflate;
    }

    /* JADX WARNING: type inference failed for: r7v11, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreated(android.os.Bundle r7) {
        /*
            r6 = this;
            super.onActivityCreated(r7)
            java.lang.String r0 = r6.mAction
            validateAction(r0)
            com.android.contacts.model.RawContactDeltaList r0 = r6.mState
            boolean r0 = r0.isEmpty()
            java.lang.String r1 = "android.intent.action.EDIT"
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0030
            java.lang.String r0 = r6.mAction
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0033
            android.app.LoaderManager r0 = r6.getLoaderManager()
            android.app.LoaderManager$LoaderCallbacks<com.android.contacts.model.Contact> r4 = r6.mContactLoaderListener
            r0.initLoader(r2, r3, r4)
            android.app.LoaderManager r0 = r6.getLoaderManager()
            r4 = 2
            android.app.LoaderManager$LoaderCallbacks<android.database.Cursor> r5 = r6.mGroupsLoaderListener
            r0.initLoader(r4, r3, r5)
            goto L_0x0033
        L_0x0030:
            r6.bindEditors()
        L_0x0033:
            if (r7 != 0) goto L_0x0087
            android.os.Bundle r7 = r6.mIntentExtras
            if (r7 == 0) goto L_0x0069
            if (r7 != 0) goto L_0x003d
            r7 = r3
            goto L_0x0045
        L_0x003d:
            java.lang.String r0 = "android.provider.extra.ACCOUNT"
            android.os.Parcelable r7 = r7.getParcelable(r0)
            android.accounts.Account r7 = (android.accounts.Account) r7
        L_0x0045:
            android.os.Bundle r0 = r6.mIntentExtras
            if (r0 != 0) goto L_0x004a
            goto L_0x0050
        L_0x004a:
            java.lang.String r3 = "android.provider.extra.DATA_SET"
            java.lang.String r3 = r0.getString(r3)
        L_0x0050:
            if (r7 == 0) goto L_0x005c
            com.android.contacts.model.account.AccountWithDataSet r0 = new com.android.contacts.model.account.AccountWithDataSet
            java.lang.String r4 = r7.name
            java.lang.String r7 = r7.type
            r0.<init>(r4, r7, r3)
            goto L_0x0067
        L_0x005c:
            android.os.Bundle r7 = r6.mIntentExtras
            java.lang.String r0 = "com.android.contacts.ACCOUNT_WITH_DATA_SET"
            android.os.Parcelable r7 = r7.getParcelable(r0)
            r0 = r7
            com.android.contacts.model.account.AccountWithDataSet r0 = (com.android.contacts.model.account.AccountWithDataSet) r0
        L_0x0067:
            r6.mAccountWithDataSet = r0
        L_0x0069:
            java.lang.String r7 = r6.mAction
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x0074
            r6.mIsEdit = r2
            goto L_0x0087
        L_0x0074:
            java.lang.String r7 = r6.mAction
            java.lang.String r0 = "android.intent.action.INSERT"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L_0x0087
            r6.mHasNewContact = r2
            com.android.contacts.model.account.AccountWithDataSet r7 = r6.mAccountWithDataSet
            if (r7 == 0) goto L_0x0087
            r6.createContact(r7)
        L_0x0087:
            boolean r7 = r6.mHasNewContact
            if (r7 == 0) goto L_0x0093
            r7 = 3
            com.google.common.base.Predicate r0 = com.android.contacts.model.AccountTypeManager.writableFilter()
            com.android.contacts.model.account.AccountsLoader.loadAccounts(r6, (int) r7, (com.google.common.base.Predicate<com.android.contacts.model.account.AccountInfo>) r0)
        L_0x0093:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.editor.ContactEditorFragment.onActivityCreated(android.os.Bundle):void");
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            maybeRestoreFocus(bundle);
        }
    }

    private static void validateAction(String str) {
        if (!VALID_INTENT_ACTIONS.contains(str)) {
            throw new IllegalArgumentException("Unknown action " + str + "; Supported actions: " + VALID_INTENT_ACTIONS);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("action", this.mAction);
        bundle.putParcelable("uri", this.mLookupUri);
        bundle.putBoolean("autoAddToDefaultGroup", this.mAutoAddToDefaultGroup);
        bundle.putBoolean("disableDeleteMenuOption", this.mDisableDeleteMenuOption);
        bundle.putBoolean("newLocalProfile", this.mNewLocalProfile);
        MaterialColorMapUtils.MaterialPalette materialPalette = this.mMaterialPalette;
        if (materialPalette != null) {
            bundle.putParcelable("materialPalette", materialPalette);
        }
        bundle.putParcelable("viewidgenerator", this.mViewIdGenerator);
        ImmutableList<RawContact> immutableList = this.mRawContacts;
        bundle.putParcelableArrayList("rawContacts", immutableList == null ? Lists.newArrayList() : Lists.newArrayList(immutableList));
        bundle.putParcelable(ContactSaveService.EXTRA_CONTACT_STATE, this.mState);
        bundle.putInt("status", this.mStatus);
        bundle.putBoolean("hasNewContact", this.mHasNewContact);
        bundle.putBoolean("newContactDataReady", this.mNewContactDataReady);
        bundle.putBoolean("isEdit", this.mIsEdit);
        bundle.putBoolean("existingContactDataReady", this.mExistingContactDataReady);
        bundle.putParcelable("saveToAccount", this.mAccountWithDataSet);
        bundle.putBoolean("isUserProfile", this.mIsUserProfile);
        bundle.putBoolean("enabled", this.mEnabled);
        bundle.putLong("aggregationSuggestionsRawContactId", this.mAggregationSuggestionsRawContactId);
        bundle.putLong("contactidforjoin", this.mContactIdForJoin);
        bundle.putLong("readOnlyDisplayNameId", this.mReadOnlyDisplayNameId);
        bundle.putBoolean("copyReadOnlyDisplayName", this.mCopyReadOnlyName);
        bundle.putLong("photo_raw_contact_id", this.mPhotoRawContactId);
        bundle.putParcelable("updated_photos", this.mUpdatedPhotos);
        View findFocus = getView() == null ? null : getView().findFocus();
        if (findFocus != null) {
            bundle.putInt("focusedViewId", findFocus.getId());
            bundle.putBoolean("restoreSoftInput", this.inputMethodManager.isActive(findFocus));
        }
        super.onSaveInstanceState(bundle);
    }

    public void onStop() {
        super.onStop();
        UiClosables.closeQuietly(this.mAggregationSuggestionPopup);
    }

    public void onDestroy() {
        super.onDestroy();
        AggregationSuggestionEngine aggregationSuggestionEngine = this.mAggregationSuggestionEngine;
        if (aggregationSuggestionEngine != null) {
            aggregationSuggestionEngine.quit();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 0) {
            if (i == 1) {
                if (i2 != -1 || intent == null || !intent.hasExtra("android.provider.extra.ACCOUNT")) {
                    Listener listener = this.mListener;
                    if (listener != null) {
                        listener.onReverted();
                        return;
                    }
                    return;
                }
                createContact((AccountWithDataSet) intent.getParcelableExtra("android.provider.extra.ACCOUNT"));
            }
        } else if (i2 == -1 && intent != null) {
            long parseId = ContentUris.parseId(intent.getData());
            if (hasPendingChanges()) {
                JoinContactConfirmationDialogFragment.show(this, parseId);
            } else {
                joinAggregate(parseId);
            }
        }
    }

    public void onAccountsLoaded(List<AccountInfo> list) {
        this.mWritableAccounts = list;
        if (this.mAccountWithDataSet == null && this.mHasNewContact) {
            selectAccountAndCreateContact();
        }
        RawContactEditorView content = getContent();
        if (content != null) {
            content.setAccounts(list);
            if (this.mAccountWithDataSet != null || content.getCurrentRawContactDelta() != null) {
                AccountWithDataSet accountWithDataSet = this.mAccountWithDataSet;
                if (accountWithDataSet == null) {
                    accountWithDataSet = content.getCurrentRawContactDelta().getAccountWithDataSet();
                }
                if (!AccountInfo.contains(list, accountWithDataSet) && !list.isEmpty()) {
                    if (isReadyToBindEditors()) {
                        onRebindEditorsForNewContact(getContent().getCurrentRawContactDelta(), accountWithDataSet, list.get(0).getAccount());
                    } else {
                        this.mAccountWithDataSet = list.get(0).getAccount();
                    }
                }
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.edit_contact, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        final MenuItem findItem = menu.findItem(R.id.menu_save);
        MenuItem findItem2 = menu.findItem(R.id.menu_split);
        MenuItem findItem3 = menu.findItem(R.id.menu_join);
        MenuItem findItem4 = menu.findItem(R.id.menu_delete);
        findItem3.setVisible(false);
        findItem2.setVisible(false);
        findItem4.setVisible(false);
        findItem.setVisible(!isEditingReadOnlyRawContact());
        if (findItem.isVisible()) {
            findItem.getActionView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ContactEditorFragment.this.onOptionsItemSelected(findItem);
                }
            });
        }
        menu.findItem(R.id.menu_help).setVisible(HelpUtils.isHelpAndFeedbackAvailable());
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            menu.getItem(i).setEnabled(this.mEnabled);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            return revert();
        }
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return true;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_save) {
            return save(0);
        }
        if (itemId == R.id.menu_delete) {
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onDeleteRequested(this.mLookupUri);
            }
            return true;
        } else if (itemId == R.id.menu_split) {
            return doSplitContactAction();
        } else {
            if (itemId == R.id.menu_join) {
                return doJoinContactAction();
            }
            if (itemId != R.id.menu_help) {
                return false;
            }
            getActivity();
            return true;
        }
    }

    public boolean revert() {
        if (this.mState.isEmpty() || !hasPendingChanges()) {
            onCancelEditConfirmed();
            return true;
        }
        CancelEditDialogFragment.show(this);
        return true;
    }

    public void onCancelEditConfirmed() {
        this.mStatus = 3;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onReverted();
        }
    }

    public void onSplitContactConfirmed(boolean z) {
        if (this.mState.isEmpty()) {
            Log.e("ContactEditor", "mState became null during the user's confirming split action. Cannot perform the save action.");
            return;
        }
        if (!z && this.mHasNewContact) {
            Iterator it = this.mState.iterator();
            while (it.hasNext()) {
                if (((RawContactDelta) it.next()).getRawContactId().longValue() < 0) {
                    it.remove();
                }
            }
        }
        this.mState.markRawContactsForSplitting();
        save(2);
    }

    private boolean doSplitContactAction() {
        if (!hasValidState()) {
            return false;
        }
        SplitContactConfirmationDialogFragment.show(this, hasPendingChanges());
        return true;
    }

    private boolean doJoinContactAction() {
        if (!hasValidState() || this.mLookupUri == null) {
            return false;
        }
        if (this.mState.size() != 1 || !((RawContactDelta) this.mState.get(0)).isContactInsert() || hasPendingChanges()) {
            showJoinAggregateActivity(this.mLookupUri);
            return true;
        }
        Toast.makeText(this.mContext, R.string.toast_join_with_empty_contact, 1).show();
        return true;
    }

    public void onJoinContactConfirmed(long j) {
        doSaveAction(3, Long.valueOf(j));
    }

    public boolean save(int i) {
        if (!hasValidState() || this.mStatus != 1) {
            return false;
        }
        if (i == 0 || i == 4 || i == 2) {
            getLoaderManager().destroyLoader(1);
        }
        this.mStatus = 2;
        if (hasPendingChanges()) {
            setEnabled(false);
            hideSoftKeyboard();
            return doSaveAction(i, (Long) null);
        } else if (this.mLookupUri == null && i == 1) {
            this.mStatus = 1;
            return true;
        } else {
            onSaveCompleted(false, i, this.mLookupUri != null, this.mLookupUri, (Long) null);
            return true;
        }
    }

    private boolean hasValidState() {
        return this.mState.size() > 0;
    }

    private boolean isEditingUserProfile() {
        return this.mNewLocalProfile || this.mIsUserProfile;
    }

    private boolean isEditingReadOnlyRawContactWithNewContact() {
        return this.mHasNewContact && this.mState.size() > 1;
    }

    private boolean isEditingReadOnlyRawContact() {
        if (hasValidState()) {
            long j = this.mRawContactIdToDisplayAlone;
            return j > 0 && !this.mState.getByRawContactId(Long.valueOf(j)).getAccountType(AccountTypeManager.getInstance(this.mContext)).areContactsWritable();
        }
    }

    private boolean hasPendingRawContactChanges(Set<String> set) {
        return RawContactModifier.hasChanges(this.mState, AccountTypeManager.getInstance(this.mContext), set);
    }

    private boolean hasPendingChanges() {
        ValuesDelta valuesDelta = null;
        if (!isEditingReadOnlyRawContactWithNewContact()) {
            return hasPendingRawContactChanges((Set<String>) null);
        }
        RawContactDelta byRawContactId = this.mState.getByRawContactId(Long.valueOf(this.mReadOnlyDisplayNameId));
        if (byRawContactId != null) {
            valuesDelta = byRawContactId.getSuperPrimaryEntry("vnd.android.cursor.item/name");
        }
        if (!structuredNamesAreEqual(valuesDelta, this.mState.getSuperPrimaryEntry("vnd.android.cursor.item/name"))) {
            return true;
        }
        HashSet hashSet = new HashSet();
        hashSet.add("vnd.android.cursor.item/name");
        return hasPendingRawContactChanges(hashSet);
    }

    private boolean structuredNamesAreEqual(ValuesDelta valuesDelta, ValuesDelta valuesDelta2) {
        if (valuesDelta == valuesDelta2) {
            return true;
        }
        if (!(valuesDelta == null || valuesDelta2 == null)) {
            ContentValues before = valuesDelta.getBefore();
            ContentValues after = valuesDelta2.getAfter();
            if (before == null || after == null || !TextUtils.equals(before.getAsString("data1"), after.getAsString("data1")) || !TextUtils.equals(before.getAsString("data4"), after.getAsString("data4")) || !TextUtils.equals(before.getAsString("data2"), after.getAsString("data2")) || !TextUtils.equals(before.getAsString("data5"), after.getAsString("data5")) || !TextUtils.equals(before.getAsString("data3"), after.getAsString("data3"))) {
                return false;
            }
            return TextUtils.equals(before.getAsString("data6"), after.getAsString("data6"));
        }
        return false;
    }

    private void selectAccountAndCreateContact() {
        Preconditions.checkNotNull(this.mWritableAccounts, "Accounts must be loaded first");
        if (this.mNewLocalProfile) {
            createContact((AccountWithDataSet) null);
            return;
        }
        List<AccountWithDataSet> extractAccounts = AccountInfo.extractAccounts(this.mWritableAccounts);
        if (this.mEditorUtils.shouldShowAccountChangedNotification(extractAccounts)) {
            Intent intent = new Intent(this.mContext, ContactEditorAccountsChangedActivity.class);
            intent.setFlags(603979776);
            this.mStatus = 4;
            startActivityForResult(intent, 1);
            return;
        }
        this.mEditorUtils.maybeUpdateDefaultAccount(extractAccounts);
        createContact(this.mEditorUtils.getOnlyOrDefaultAccount(extractAccounts));
    }

    private void createContact(AccountWithDataSet accountWithDataSet) {
        setStateForNewContact(accountWithDataSet, AccountTypeManager.getInstance(this.mContext).getAccountTypeForAccount(accountWithDataSet), isEditingUserProfile());
    }

    /* access modifiers changed from: private */
    public void setState(Contact contact) {
        if (this.mState.isEmpty()) {
            this.mContact = contact;
            this.mRawContacts = contact.getRawContacts();
            if (contact.isUserProfile() || contact.isWritableContact(this.mContext)) {
                this.mHasNewContact = false;
            } else {
                this.mHasNewContact = true;
                this.mReadOnlyDisplayNameId = contact.getNameRawContactId();
                this.mCopyReadOnlyName = true;
                selectAccountAndCreateContact();
            }
            setStateForExistingContact(contact.isUserProfile(), this.mRawContacts);
            if (this.mAutoAddToDefaultGroup && InvisibleContactUtil.isInvisibleAndAddable(contact, getContext())) {
                InvisibleContactUtil.markAddToDefaultGroup(contact, this.mState, getContext());
            }
        } else if (Log.isLoggable("ContactEditor", 2)) {
            Log.v("ContactEditor", "Ignoring background change. This will have to be rebased later");
        }
    }

    private void setStateForNewContact(AccountWithDataSet accountWithDataSet, AccountType accountType, boolean z) {
        setStateForNewContact(accountWithDataSet, accountType, (RawContactDelta) null, (AccountType) null, z);
    }

    private void setStateForNewContact(AccountWithDataSet accountWithDataSet, AccountType accountType, RawContactDelta rawContactDelta, AccountType accountType2, boolean z) {
        this.mStatus = 1;
        this.mAccountWithDataSet = accountWithDataSet;
        this.mState.add(createNewRawContactDelta(accountWithDataSet, accountType, rawContactDelta, accountType2));
        this.mIsUserProfile = z;
        this.mNewContactDataReady = true;
        bindEditors();
    }

    private RawContactDelta createNewRawContactDelta(AccountWithDataSet accountWithDataSet, AccountType accountType, RawContactDelta rawContactDelta, AccountType accountType2) {
        RawContact rawContact = new RawContact();
        if (accountWithDataSet != null) {
            rawContact.setAccount(accountWithDataSet);
        } else {
            rawContact.setAccountToLocal();
        }
        RawContactDelta rawContactDelta2 = new RawContactDelta(ValuesDelta.fromAfter(rawContact.getValues()));
        if (rawContactDelta == null) {
            RawContactModifier.parseExtras(this.mContext, accountType, rawContactDelta2, this.mIntentExtras);
        } else {
            RawContactModifier.migrateStateForNewContact(this.mContext, rawContactDelta, rawContactDelta2, accountType2, accountType);
        }
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/name");
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/phone_v2");
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/email_v2");
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/organization");
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/contact_event");
        RawContactModifier.ensureKindExists(rawContactDelta2, accountType, "vnd.android.cursor.item/postal-address_v2");
        if (this.mNewLocalProfile) {
            rawContactDelta2.setProfileQueryUri();
        }
        return rawContactDelta2;
    }

    private void setStateForExistingContact(boolean z, ImmutableList<RawContact> immutableList) {
        setEnabled(true);
        this.mState.addAll(immutableList.iterator());
        setIntentExtras(this.mIntentExtras);
        this.mIntentExtras = null;
        this.mIsUserProfile = z;
        if (this.mIsUserProfile) {
            Iterator it = this.mState.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                RawContactDelta rawContactDelta = (RawContactDelta) it.next();
                rawContactDelta.setProfileQueryUri();
                if (rawContactDelta.getValues().getAsString("account_type") == null) {
                    z2 = true;
                }
            }
            if (!z2 && this.mRawContactIdToDisplayAlone <= 0) {
                this.mState.add(createLocalRawContactDelta());
            }
        }
        this.mExistingContactDataReady = true;
        bindEditors();
    }

    private void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            LinearLayout linearLayout = this.mContent;
            if (linearLayout != null) {
                int childCount = linearLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    this.mContent.getChildAt(i).setEnabled(z);
                }
            }
            Activity activity = getActivity();
            if (activity != null) {
                activity.invalidateOptionsMenu();
            }
        }
    }

    private static RawContactDelta createLocalRawContactDelta() {
        RawContact rawContact = new RawContact();
        rawContact.setAccountToLocal();
        RawContactDelta rawContactDelta = new RawContactDelta(ValuesDelta.fromAfter(rawContact.getValues()));
        rawContactDelta.setProfileQueryUri();
        return rawContactDelta;
    }

    private void copyReadOnlyName() {
        if (isEditingReadOnlyRawContactWithNewContact()) {
            int indexOfFirstWritableRawContact = this.mState.indexOfFirstWritableRawContact(getContext());
            RawContactDelta byRawContactId = this.mState.getByRawContactId(Long.valueOf(this.mContact.getNameRawContactId()));
            ValuesDelta superPrimaryEntry = ((RawContactDelta) this.mState.get(indexOfFirstWritableRawContact)).getSuperPrimaryEntry("vnd.android.cursor.item/name");
            ValuesDelta superPrimaryEntry2 = byRawContactId.getSuperPrimaryEntry("vnd.android.cursor.item/name");
            this.mCopyReadOnlyName = false;
            if (superPrimaryEntry != null && superPrimaryEntry2 != null) {
                superPrimaryEntry.copyStructuredNameFieldsFrom(superPrimaryEntry2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bindEditors() {
        Toolbar toolbar;
        if (isReadyToBindEditors()) {
            RawContactEditorView content = getContent();
            content.setListener(this);
            if (this.mCopyReadOnlyName) {
                copyReadOnlyName();
            }
            content.setState(this.mState, this.mMaterialPalette, this.mViewIdGenerator, this.mHasNewContact, this.mIsUserProfile, this.mAccountWithDataSet, this.mRawContactIdToDisplayAlone);
            if (isEditingReadOnlyRawContact() && (toolbar = getEditorActivity().getToolbar()) != null) {
                toolbar.setTitle((int) R.string.contact_editor_title_read_only_contact);
                getEditorActivity().setTitle(R.string.contact_editor_title_read_only_contact);
                toolbar.setNavigationIcon((int) R.drawable.quantum_ic_arrow_back_vd_theme_24);
                toolbar.setNavigationContentDescription((int) R.string.back_arrow_content_description);
                toolbar.getNavigationIcon().setAutoMirrored(true);
            }
            content.setPhotoListener(this);
            this.mPhotoRawContactId = content.getPhotoRawContactId();
            Uri uri = (Uri) this.mUpdatedPhotos.get(String.valueOf(this.mPhotoRawContactId));
            if (uri != null) {
                content.setFullSizePhoto(uri);
            }
            StructuredNameEditorView nameEditorView = content.getNameEditorView();
            TextFieldsEditorView phoneticEditorView = content.getPhoneticEditorView();
            if (!(!Locale.JAPANESE.getLanguage().equals(Locale.getDefault().getLanguage()) || nameEditorView == null || phoneticEditorView == null)) {
                nameEditorView.setPhoneticView(phoneticEditorView);
            }
            content.setEnabled(this.mEnabled);
            content.setVisibility(0);
            invalidateOptionsMenu();
        }
    }

    private void invalidateOptionsMenu() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    private boolean isReadyToBindEditors() {
        if (this.mState.isEmpty()) {
            if (Log.isLoggable("ContactEditor", 2)) {
                Log.v("ContactEditor", "No data to bind editors");
            }
            return false;
        } else if (this.mIsEdit && !this.mExistingContactDataReady) {
            if (Log.isLoggable("ContactEditor", 2)) {
                Log.v("ContactEditor", "Existing contact data is not ready to bind editors.");
            }
            return false;
        } else if (!this.mHasNewContact || this.mNewContactDataReady) {
            return RequestPermissionsActivity.hasRequiredPermissions(this.mContext);
        } else {
            if (Log.isLoggable("ContactEditor", 2)) {
                Log.v("ContactEditor", "New contact data is not ready to bind editors.");
            }
            return false;
        }
    }

    private void rebindEditorsForNewContact(RawContactDelta rawContactDelta, AccountWithDataSet accountWithDataSet, AccountWithDataSet accountWithDataSet2) {
        AccountTypeManager instance = AccountTypeManager.getInstance(this.mContext);
        AccountType accountTypeForAccount = instance.getAccountTypeForAccount(accountWithDataSet);
        AccountType accountTypeForAccount2 = instance.getAccountTypeForAccount(accountWithDataSet2);
        this.mExistingContactDataReady = false;
        this.mNewContactDataReady = false;
        this.mState = new RawContactDeltaList();
        setStateForNewContact(accountWithDataSet2, accountTypeForAccount2, rawContactDelta, accountTypeForAccount, isEditingUserProfile());
        if (this.mIsEdit) {
            setStateForExistingContact(isEditingUserProfile(), this.mRawContacts);
        }
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void load(String str, Uri uri, Bundle bundle) {
        this.mAction = str;
        this.mLookupUri = uri;
        this.mIntentExtras = bundle;
        Bundle bundle2 = this.mIntentExtras;
        if (bundle2 != null) {
            this.mAutoAddToDefaultGroup = bundle2.containsKey("addToDefaultDirectory");
            this.mNewLocalProfile = this.mIntentExtras.getBoolean("newLocalProfile");
            this.mDisableDeleteMenuOption = this.mIntentExtras.getBoolean("disableDeleteMenuOption");
            if (this.mIntentExtras.containsKey("material_palette_primary_color") && this.mIntentExtras.containsKey("material_palette_secondary_color")) {
                this.mMaterialPalette = new MaterialColorMapUtils.MaterialPalette(this.mIntentExtras.getInt("material_palette_primary_color"), this.mIntentExtras.getInt("material_palette_secondary_color"));
            }
            this.mRawContactIdToDisplayAlone = this.mIntentExtras.getLong("raw_contact_id_to_display_alone");
        }
    }

    public void setIntentExtras(Bundle bundle) {
        getContent().setIntentExtras(bundle);
    }

    public void onJoinCompleted(Uri uri) {
        onSaveCompleted(false, 1, uri != null, uri, (Long) null);
    }

    /* JADX INFO: finally extract failed */
    private String getNameToDisplay(Uri uri) {
        Cursor query;
        if (!(uri == null || (query = this.mContext.getContentResolver().query(uri, new String[]{"display_name", "display_name_alt"}, (String) null, (String[]) null, (String) null)) == null)) {
            try {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    query.close();
                    String preferredDisplayName = ContactDisplayUtils.getPreferredDisplayName(string, string2, new ContactsPreferences(this.mContext));
                    query.close();
                    return preferredDisplayName;
                }
                query.close();
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        return null;
    }

    public void onSaveCompleted(boolean z, int i, boolean z2, Uri uri, Long l) {
        String str;
        if (z) {
            if (!z2) {
                Toast.makeText(this.mContext, R.string.contactSavedErrorToast, 1).show();
            } else if (i == 2) {
                Toast.makeText(this.mContext, R.string.contactUnlinkedToast, 0).show();
            } else if (i != 3) {
                String nameToDisplay = getNameToDisplay(uri);
                if (!TextUtils.isEmpty(nameToDisplay)) {
                    str = getResources().getString(R.string.contactSavedNamedToast, new Object[]{nameToDisplay});
                } else {
                    str = getResources().getString(R.string.contactSavedToast);
                }
                Toast.makeText(this.mContext, str, 0).show();
            }
        }
        Intent intent = null;
        if (i == 0) {
            if (z2 && uri != null) {
                intent = ImplicitIntentsUtil.composeQuickContactIntent(this.mContext, ContactEditorUtils.maybeConvertToLegacyLookupUri(this.mContext, uri, this.mLookupUri), 6);
                intent.putExtra("contact_edited", true);
            }
            this.mStatus = 3;
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onSaveFinished(intent);
            }
        } else if (i != 1) {
            if (i == 2) {
                this.mStatus = 3;
                Listener listener2 = this.mListener;
                if (listener2 != null) {
                    listener2.onContactSplit(uri);
                } else if (Log.isLoggable("ContactEditor", 3)) {
                    Log.d("ContactEditor", "No listener registered, can not call onSplitFinished");
                }
            } else if (i != 3) {
                if (i == 4) {
                    this.mStatus = 3;
                    Listener listener3 = this.mListener;
                    if (listener3 != null) {
                        listener3.onSaveFinished((Intent) null);
                    }
                }
            } else if (z2 && uri != null && l != null) {
                joinAggregate(l.longValue());
            }
        } else if (z2 && uri != null) {
            this.mState = new RawContactDeltaList();
            load("android.intent.action.EDIT", uri, (Bundle) null);
            this.mStatus = 0;
            getLoaderManager().restartLoader(1, (Bundle) null, this.mContactLoaderListener);
        }
    }

    private void showJoinAggregateActivity(Uri uri) {
        if (uri != null && isAdded()) {
            this.mContactIdForJoin = ContentUris.parseId(uri);
            Intent intent = new Intent(this.mContext, ContactSelectionActivity.class);
            intent.setAction("com.android.contacts.action.JOIN_CONTACT");
            intent.putExtra("com.android.contacts.action.CONTACT_ID", this.mContactIdForJoin);
            startActivityForResult(intent, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void acquireAggregationSuggestions(Context context, long j, ValuesDelta valuesDelta) {
        this.mAggregationSuggestionsRawContactId = j;
        if (this.mAggregationSuggestionEngine == null) {
            this.mAggregationSuggestionEngine = new AggregationSuggestionEngine(context);
            this.mAggregationSuggestionEngine.setListener(this);
            this.mAggregationSuggestionEngine.start();
        }
        this.mAggregationSuggestionEngine.setContactId(getContactId());
        this.mAggregationSuggestionEngine.setAccountFilter(getContent().getCurrentRawContactDelta().getAccountWithDataSet());
        this.mAggregationSuggestionEngine.onNameChange(valuesDelta);
    }

    private long getContactId() {
        Iterator it = this.mState.iterator();
        while (it.hasNext()) {
            Long asLong = ((RawContactDelta) it.next()).getValues().getAsLong("contact_id");
            if (asLong != null) {
                return asLong.longValue();
            }
        }
        return 0;
    }

    public void onAggregationSuggestionChange() {
        View aggregationAnchorView;
        Activity activity = getActivity();
        if ((activity == null || !activity.isFinishing()) && isVisible() && !this.mState.isEmpty() && this.mStatus == 1) {
            UiClosables.closeQuietly(this.mAggregationSuggestionPopup);
            if (this.mAggregationSuggestionEngine.getSuggestedContactCount() != 0 && (aggregationAnchorView = getAggregationAnchorView()) != null) {
                this.mAggregationSuggestionPopup = new ListPopupWindow(this.mContext, (AttributeSet) null);
                this.mAggregationSuggestionPopup.setAnchorView(aggregationAnchorView);
                this.mAggregationSuggestionPopup.setWidth(aggregationAnchorView.getWidth());
                this.mAggregationSuggestionPopup.setInputMethodMode(2);
                this.mAggregationSuggestionPopup.setAdapter(new AggregationSuggestionAdapter(getActivity(), this, this.mAggregationSuggestionEngine.getSuggestions()));
                this.mAggregationSuggestionPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        ((AggregationSuggestionView) view).handleItemClickEvent();
                        UiClosables.closeQuietly(ContactEditorFragment.this.mAggregationSuggestionPopup);
                        ContactEditorFragment.this.mAggregationSuggestionPopup = null;
                    }
                });
                this.mAggregationSuggestionPopup.show();
            }
        }
    }

    /* access modifiers changed from: protected */
    public View getAggregationAnchorView() {
        return getContent().getAggregationAnchorView();
    }

    public void onEditAction(Uri uri, long j) {
        SuggestionEditConfirmationDialogFragment.show(this, uri, j);
    }

    public void doEditSuggestedContact(Uri uri, long j) {
        Listener listener = this.mListener;
        if (listener != null) {
            this.mStatus = 3;
            listener.onEditOtherRawContactRequested(uri, j, getContent().getCurrentRawContactDelta().getContentValues());
        }
    }

    /* access modifiers changed from: protected */
    public void setGroupMetaData() {
        if (this.mGroupMetaData != null) {
            getContent().setGroupMetaData(this.mGroupMetaData);
        }
    }

    /* access modifiers changed from: protected */
    public boolean doSaveAction(int i, Long l) {
        return startSaveService(this.mContext, ContactSaveService.createSaveContactIntent(this.mContext, this.mState, ContactSaveService.EXTRA_SAVE_MODE, i, isEditingUserProfile(), ((Activity) this.mContext).getClass(), "saveCompleted", this.mUpdatedPhotos, "joinContactId", l), i);
    }

    private boolean startSaveService(Context context, Intent intent, int i) {
        boolean startService = ContactSaveService.startService(context, intent, i);
        if (!startService) {
            onCancelEditConfirmed();
        }
        return startService;
    }

    /* access modifiers changed from: protected */
    public void joinAggregate(long j) {
        this.mContext.startService(ContactSaveService.createJoinContactsIntent(this.mContext, this.mContactIdForJoin, j, ContactEditorActivity.class, "joinCompleted"));
    }

    public void removePhoto() {
        getContent().removePhoto();
        this.mUpdatedPhotos.remove(String.valueOf(this.mPhotoRawContactId));
    }

    public void updatePhoto(Uri uri) throws FileNotFoundException {
        Bitmap bitmapFromUri = ContactPhotoUtils.getBitmapFromUri(getActivity(), uri);
        if (bitmapFromUri == null || bitmapFromUri.getHeight() <= 0 || bitmapFromUri.getWidth() <= 0) {
            Toast.makeText(this.mContext, R.string.contactPhotoSavedErrorToast, 0).show();
            return;
        }
        this.mUpdatedPhotos.putParcelable(String.valueOf(this.mPhotoRawContactId), uri);
        getContent().updatePhoto(uri);
    }

    public void onNameFieldChanged(long j, ValuesDelta valuesDelta) {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            acquireAggregationSuggestions(activity, j, valuesDelta);
        }
    }

    public void onRebindEditorsForNewContact(RawContactDelta rawContactDelta, AccountWithDataSet accountWithDataSet, AccountWithDataSet accountWithDataSet2) {
        this.mNewContactAccountChanged = true;
        rebindEditorsForNewContact(rawContactDelta, accountWithDataSet, accountWithDataSet2);
    }

    public void onBindEditorsFailed() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            Toast.makeText(activity, R.string.editor_failed_to_load, 0).show();
            activity.setResult(0);
            activity.finish();
        }
    }

    public void onEditorsBound() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            getLoaderManager().initLoader(2, (Bundle) null, this.mGroupsLoaderListener);
        }
    }

    public void onPhotoEditorViewClicked() {
        getEditorActivity().changePhoto(getPhotoMode());
    }

    private int getPhotoMode() {
        return getContent().isWritablePhotoSet() ? 14 : 4;
    }

    private ContactEditorActivity getEditorActivity() {
        return (ContactEditorActivity) getActivity();
    }

    private RawContactEditorView getContent() {
        return (RawContactEditorView) this.mContent;
    }

    private void maybeRestoreFocus(Bundle bundle) {
        int i = bundle.getInt("focusedViewId", -1);
        if (i != -1) {
            new Handler().postDelayed(new Runnable(i, bundle.getBoolean("restoreSoftInput")) {
                private final /* synthetic */ int f$1;
                private final /* synthetic */ boolean f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    ContactEditorFragment.this.lambda$maybeRestoreFocus$0$ContactEditorFragment(this.f$1, this.f$2);
                }
            }, 100);
        }
    }

    public /* synthetic */ void lambda$maybeRestoreFocus$0$ContactEditorFragment(int i, boolean z) {
        View view;
        View findViewById;
        if (!isResumed() || (view = getView()) == null || view.findFocus() != null || (findViewById = getView().findViewById(i)) == null) {
            return;
        }
        if (!findViewById.requestFocus()) {
            Log.i("ContactEditor", "requestFocus failed");
        } else if (z) {
            boolean showSoftInput = this.inputMethodManager.showSoftInput(findViewById, 1);
            if (Log.isLoggable("ContactEditor", 3)) {
                Log.d("ContactEditor", "showSoftInput -> " + showSoftInput);
            }
        }
    }

    private void hideSoftKeyboard() {
        LinearLayout linearLayout;
        InputMethodManager inputMethodManager2 = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager2 != null && (linearLayout = this.mContent) != null) {
            inputMethodManager2.hideSoftInputFromWindow(linearLayout.getWindowToken(), 2);
        }
    }
}
