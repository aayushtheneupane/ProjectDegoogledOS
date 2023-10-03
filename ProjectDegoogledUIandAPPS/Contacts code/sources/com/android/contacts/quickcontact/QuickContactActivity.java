package com.android.contacts.quickcontact;

import android.accounts.Account;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.Loader;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.provider.ContactsContract;
import android.telecom.PhoneAccount;
import android.text.BidiFormatter;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.p002os.BuildCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.palette.graphics.Palette;
import com.android.contacts.CallUtil;
import com.android.contacts.ClipboardUtils;
import com.android.contacts.Collapser;
import com.android.contacts.ContactSaveService;
import com.android.contacts.ContactsActivity;
import com.android.contacts.DynamicShortcuts;
import com.android.contacts.NfcHandler;
import com.android.contacts.R;
import com.android.contacts.ShortcutIntentBuilder;
import com.android.contacts.activities.ContactSelectionActivity;
import com.android.contacts.activities.RequestPermissionsActivity;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.compat.MultiWindowCompat;
import com.android.contacts.compat.PhoneNumberUtilsCompat;
import com.android.contacts.detail.ContactDisplayUtils;
import com.android.contacts.editor.EditorIntents;
import com.android.contacts.editor.EditorUiUtils;
import com.android.contacts.interactions.ContactDeletionInteraction;
import com.android.contacts.interactions.TouchPointManager;
import com.android.contacts.lettertiles.LetterTileDrawable;
import com.android.contacts.logging.Logger;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.Contact;
import com.android.contacts.model.ContactLoader;
import com.android.contacts.model.RawContact;
import com.android.contacts.model.dataitem.DataItem;
import com.android.contacts.model.dataitem.DataKind;
import com.android.contacts.model.dataitem.PhoneDataItem;
import com.android.contacts.quickcontact.ExpandingEntryCardView;
import com.android.contacts.util.ImageViewDrawableSetter;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contacts.util.PhoneCapabilityTester;
import com.android.contacts.util.SchedulingUtils;
import com.android.contacts.util.SharedPreferenceUtil;
import com.android.contacts.util.ViewUtil;
import com.android.contacts.widget.MultiShrinkScroller;
import com.android.contacts.widget.QuickContactImageView;
import com.android.contactsbind.HelpUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickContactActivity extends ContactsActivity {
    private static final int CURRENT_API_VERSION = Build.VERSION.SDK_INT;
    private static final String KEY_LOADER_EXTRA_EMAILS = (QuickContactActivity.class.getCanonicalName() + ".KEY_LOADER_EXTRA_EMAILS");
    private static final String KEY_LOADER_EXTRA_PHONES = (QuickContactActivity.class.getCanonicalName() + ".KEY_LOADER_EXTRA_PHONES");
    private static final String KEY_LOADER_EXTRA_SIP_NUMBERS = (QuickContactActivity.class.getCanonicalName() + ".KEY_LOADER_EXTRA_SIP_NUMBERS");
    /* access modifiers changed from: private */
    public static final List<String> LEADING_MIMETYPES = Lists.newArrayList((E[]) new String[]{"vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/sip_address", "vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/postal-address_v2"});
    private static final int SCRIM_COLOR = Color.argb(200, 0, 0, 0);
    private static final List<String> SORTED_ABOUT_CARD_MIMETYPES = Lists.newArrayList((E[]) new String[]{"vnd.android.cursor.item/nickname", "vnd.android.cursor.item/website", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/contact_event", "vnd.android.cursor.item/relation", "vnd.android.cursor.item/im", "vnd.android.cursor.item/group_membership", "vnd.android.cursor.item/identity", "vnd.com.google.cursor.item/contact_user_defined_field", "vnd.android.cursor.item/note"});
    private static final BidiFormatter sBidiFormatter = BidiFormatter.getInstance();
    private ExpandingEntryCardView mAboutCard;
    private final Comparator<List<DataItem>> mAmongstMimeTypeDataItemComparator = new Comparator<List<DataItem>>() {
        public int compare(List<DataItem> list, List<DataItem> list2) {
            String mimeType = list.get(0).getMimeType();
            String mimeType2 = list2.get(0).getMimeType();
            if (!TextUtils.isEmpty(QuickContactActivity.this.mExtraPrioritizedMimeType) && !mimeType.equals(mimeType2)) {
                if (mimeType2.equals(QuickContactActivity.this.mExtraPrioritizedMimeType)) {
                    return 1;
                }
                if (mimeType.equals(QuickContactActivity.this.mExtraPrioritizedMimeType)) {
                    return -1;
                }
            }
            if (!mimeType.equals(mimeType2)) {
                for (String str : QuickContactActivity.LEADING_MIMETYPES) {
                    if (mimeType.equals(str)) {
                        return -1;
                    }
                    if (mimeType2.equals(str)) {
                        return 1;
                    }
                }
            }
            return 0;
        }
    };
    private boolean mArePhoneOptionsChangable;
    private Cp2DataCardModel mCachedCp2DataCardModel;
    private PorterDuffColorFilter mColorFilter;
    private int mColorFilterColor;
    private ExpandingEntryCardView mContactCard;
    /* access modifiers changed from: private */
    public Contact mContactData;
    private ContactLoader mContactLoader;
    /* access modifiers changed from: private */
    public int mContactType;
    private String mCustomRingtone;
    private AsyncTask<Void, Void, Cp2DataCardModel> mEntriesAndActionsTask;
    final View.OnClickListener mEntryClickHandler = new View.OnClickListener() {
        public void onClick(View view) {
            Object tag = view.getTag();
            if (tag == null || !(tag instanceof ExpandingEntryCardView.EntryTag)) {
                Log.w("QuickContact", "EntryTag was not used correctly");
                return;
            }
            ExpandingEntryCardView.EntryTag entryTag = (ExpandingEntryCardView.EntryTag) tag;
            Intent intent = entryTag.getIntent();
            if (entryTag.getId() == -2) {
                QuickContactActivity.this.editContact();
                return;
            }
            if ("android.intent.action.CALL".equals(intent.getAction()) && TouchPointManager.getInstance().hasValidPoint()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("touchPoint", TouchPointManager.getInstance().getPoint());
                intent.putExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS", bundle);
            }
            boolean unused = QuickContactActivity.this.mHasIntentLaunched = true;
            try {
                Logger.logQuickContactEvent(QuickContactActivity.this.mReferrer, QuickContactActivity.this.mContactType, 0, intent.getIntExtra("action_type", 0), intent.getStringExtra("third_party_action"));
                if ("com.google.android.apps.tachyon.action.CALL".equals(intent.getAction())) {
                    QuickContactActivity.this.startActivityForResult(intent, 0);
                    return;
                }
                intent.addFlags(268435456);
                ImplicitIntentsUtil.startActivityInAppIfPossible(QuickContactActivity.this, intent);
            } catch (SecurityException unused2) {
                Toast.makeText(QuickContactActivity.this, R.string.missing_app, 0).show();
                Log.e("QuickContact", "QuickContacts does not have permission to launch " + intent);
            } catch (ActivityNotFoundException unused3) {
                Toast.makeText(QuickContactActivity.this, R.string.missing_app, 0).show();
            }
        }
    };
    private final View.OnCreateContextMenuListener mEntryContextMenuListener = new View.OnCreateContextMenuListener() {
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            boolean z;
            if (contextMenuInfo != null) {
                ExpandingEntryCardView.EntryContextMenuInfo entryContextMenuInfo = (ExpandingEntryCardView.EntryContextMenuInfo) contextMenuInfo;
                String mimeType = entryContextMenuInfo.getMimeType();
                contextMenu.setHeaderTitle(entryContextMenuInfo.getCopyText());
                if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
                    addPhoneAccountsToMenu(QuickContactActivity.this, contextMenu, entryContextMenuInfo);
                }
                contextMenu.add(0, 0, 0, QuickContactActivity.this.getString(R.string.copy_text));
                if (QuickContactActivity.this.isContactEditable()) {
                    if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
                        z = QuickContactActivity.this.mOnlyOnePhoneNumber;
                    } else {
                        z = "vnd.android.cursor.item/email_v2".equals(mimeType) ? QuickContactActivity.this.mOnlyOneEmail : true;
                    }
                    if (entryContextMenuInfo.isSuperPrimary()) {
                        contextMenu.add(0, 1, 0, QuickContactActivity.this.getString(R.string.clear_default));
                    } else if (!z) {
                        contextMenu.add(0, 2, 0, QuickContactActivity.this.getString(R.string.set_default));
                    }
                }
            }
        }

        private void addPhoneAccountsToMenu(Context context, Menu menu, ExpandingEntryCardView.EntryContextMenuInfo entryContextMenuInfo) {
            String normalizeNumber = PhoneNumberUtilsCompat.normalizeNumber(entryContextMenuInfo.getCopyText());
            List<PhoneAccount> callCapablePhoneAccounts = CallUtil.getCallCapablePhoneAccounts(context, "tel");
            if (callCapablePhoneAccounts != null && callCapablePhoneAccounts.size() > 1) {
                for (PhoneAccount next : callCapablePhoneAccounts) {
                    String string = context.getString(R.string.call_via_template, new Object[]{next.getLabel()});
                    menu.add(0, 3, 0, string).setIntent(CallUtil.getCallWithSubjectIntent(normalizeNumber, next.getAccountHandle(), (String) null));
                }
            }
        }
    };
    private String[] mExcludeMimes;
    final ExpandingEntryCardView.ExpandingEntryCardViewListener mExpandingEntryCardViewListener = new ExpandingEntryCardView.ExpandingEntryCardViewListener() {
        public void onCollapse(int i) {
            QuickContactActivity.this.mScroller.prepareForShrinkingScrollChild(i);
        }

        public void onExpand() {
            QuickContactActivity.this.mScroller.setDisableTouchesForSuppressLayout(true);
        }

        public void onExpandDone() {
            QuickContactActivity.this.mScroller.setDisableTouchesForSuppressLayout(false);
        }
    };
    /* access modifiers changed from: private */
    public int mExtraMode;
    /* access modifiers changed from: private */
    public String mExtraPrioritizedMimeType;
    /* access modifiers changed from: private */
    public boolean mHasAlreadyBeenOpened;
    /* access modifiers changed from: private */
    public boolean mHasComputedThemeColor;
    /* access modifiers changed from: private */
    public boolean mHasIntentLaunched;
    /* access modifiers changed from: private */
    public boolean mIsEntranceAnimationFinished;
    /* access modifiers changed from: private */
    public boolean mIsExitAnimationInProgress;
    /* access modifiers changed from: private */
    public boolean mIsRecreatedInstance;
    private SaveServiceListener mListener;
    private final LoaderManager.LoaderCallbacks<Contact> mLoaderContactCallbacks = new LoaderManager.LoaderCallbacks<Contact>() {
        public void onLoaderReset(Loader<Contact> loader) {
            Contact unused = QuickContactActivity.this.mContactData = null;
        }

        public void onLoadFinished(Loader<Contact> loader, Contact contact) {
            Trace.beginSection("onLoadFinished()");
            try {
                if (!QuickContactActivity.this.isFinishing()) {
                    if (contact.isError()) {
                        Log.i("QuickContact", "Failed to load contact: " + ((ContactLoader) loader).getLookupUri());
                        Toast.makeText(QuickContactActivity.this, R.string.invalidContactMessage, 1).show();
                        QuickContactActivity.this.finish();
                        Trace.endSection();
                    } else if (contact.isNotFound()) {
                        Log.i("QuickContact", "No contact found: " + ((ContactLoader) loader).getLookupUri());
                        Toast.makeText(QuickContactActivity.this, R.string.invalidContactMessage, 1).show();
                        QuickContactActivity.this.finish();
                        Trace.endSection();
                    } else {
                        if (!QuickContactActivity.this.mIsRecreatedInstance && !QuickContactActivity.this.mShortcutUsageReported && contact != null) {
                            boolean unused = QuickContactActivity.this.mShortcutUsageReported = true;
                            DynamicShortcuts.reportShortcutUsed(QuickContactActivity.this, contact.getLookupKey());
                        }
                        QuickContactActivity.this.bindContactData(contact);
                        Trace.endSection();
                    }
                }
            } finally {
                Trace.endSection();
            }
        }

        public Loader<Contact> onCreateLoader(int i, Bundle bundle) {
            if (QuickContactActivity.this.mLookupUri == null) {
                Log.wtf("QuickContact", "Lookup uri wasn't initialized. Loader was started too early");
            }
            return new ContactLoader(QuickContactActivity.this.getApplicationContext(), QuickContactActivity.this.mLookupUri, true, true, true);
        }
    };
    /* access modifiers changed from: private */
    public Uri mLookupUri;
    /* access modifiers changed from: private */
    public MaterialColorMapUtils mMaterialColorMapUtils;
    final MultiShrinkScroller.MultiShrinkScrollerListener mMultiShrinkScrollerListener = new MultiShrinkScroller.MultiShrinkScrollerListener() {
        public void onScrolledOffBottom() {
            QuickContactActivity.this.finish();
        }

        public void onEnterFullscreen() {
            QuickContactActivity.this.updateStatusBarColor();
        }

        public void onExitFullscreen() {
            QuickContactActivity.this.updateStatusBarColor();
        }

        public void onStartScrollOffBottom() {
            boolean unused = QuickContactActivity.this.mIsExitAnimationInProgress = true;
        }

        public void onEntranceAnimationDone() {
            boolean unused = QuickContactActivity.this.mIsEntranceAnimationFinished = true;
        }

        public void onTransparentViewHeightChange(float f) {
            if (QuickContactActivity.this.mIsEntranceAnimationFinished) {
                QuickContactActivity.this.mWindowScrim.setAlpha((int) (f * 255.0f));
            }
        }
    };
    private ExpandingEntryCardView mNoContactDetailsCard;
    /* access modifiers changed from: private */
    public boolean mOnlyOneEmail;
    /* access modifiers changed from: private */
    public boolean mOnlyOnePhoneNumber;
    private final ImageViewDrawableSetter mPhotoSetter = new ImageViewDrawableSetter();
    /* access modifiers changed from: private */
    public QuickContactImageView mPhotoView;
    private long mPreviousContactId = 0;
    private ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public String mReferrer;
    /* access modifiers changed from: private */
    public MultiShrinkScroller mScroller;
    private boolean mSendToVoicemailState;
    /* access modifiers changed from: private */
    public boolean mShortcutUsageReported = false;
    private boolean mShouldLog;
    private int mStatusBarColor;
    /* access modifiers changed from: private */
    public ColorDrawable mWindowScrim;
    private final Comparator<DataItem> mWithinMimeTypeDataItemComparator = new Comparator<DataItem>() {
        public int compare(DataItem dataItem, DataItem dataItem2) {
            if (!dataItem.getMimeType().equals(dataItem2.getMimeType())) {
                Log.wtf("QuickContact", "Comparing DataItems with different mimetypes lhs.getMimeType(): " + dataItem.getMimeType() + " rhs.getMimeType(): " + dataItem2.getMimeType());
                return 0;
            } else if (dataItem.isSuperPrimary()) {
                return -1;
            } else {
                if (dataItem2.isSuperPrimary()) {
                    return 1;
                }
                if (dataItem.isPrimary() && !dataItem2.isPrimary()) {
                    return -1;
                }
                if (dataItem.isPrimary() || !dataItem2.isPrimary()) {
                    return 0;
                }
                return 1;
            }
        }
    };

    public boolean onContextItemSelected(MenuItem menuItem) {
        try {
            ExpandingEntryCardView.EntryContextMenuInfo entryContextMenuInfo = (ExpandingEntryCardView.EntryContextMenuInfo) menuItem.getMenuInfo();
            int itemId = menuItem.getItemId();
            if (itemId == 0) {
                ClipboardUtils.copyText(this, entryContextMenuInfo.getCopyLabel(), entryContextMenuInfo.getCopyText(), true);
                return true;
            } else if (itemId == 1) {
                startService(ContactSaveService.createClearPrimaryIntent(this, entryContextMenuInfo.getId()));
                return true;
            } else if (itemId == 2) {
                startService(ContactSaveService.createSetSuperPrimaryIntent(this, entryContextMenuInfo.getId()));
                return true;
            } else if (itemId == 3) {
                startActivity(menuItem.getIntent());
                return true;
            } else {
                throw new IllegalArgumentException("Unknown menu option " + menuItem.getItemId());
            }
        } catch (ClassCastException e) {
            Log.e("QuickContact", "bad menuInfo", e);
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            TouchPointManager.getInstance().setPoint((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Trace.beginSection("onCreate()");
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            this.mIsRecreatedInstance = bundle != null;
            if (this.mIsRecreatedInstance) {
                this.mPreviousContactId = bundle.getLong("previous_contact_id");
                this.mSendToVoicemailState = bundle.getBoolean("sendToVoicemailState");
                this.mArePhoneOptionsChangable = bundle.getBoolean("arePhoneOptionsChangable");
                this.mCustomRingtone = bundle.getString(ContactSaveService.EXTRA_CUSTOM_RINGTONE);
            }
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setIndeterminate(true);
            this.mProgressDialog.setCancelable(false);
            this.mListener = new SaveServiceListener();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ContactSaveService.BROADCAST_LINK_COMPLETE);
            intentFilter.addAction(ContactSaveService.BROADCAST_UNLINK_COMPLETE);
            LocalBroadcastManager.getInstance(this).registerReceiver(this.mListener, intentFilter);
            this.mShouldLog = true;
            Logger.logScreenView(this, 5, getIntent().getIntExtra("previous_screen_type", 0));
            this.mReferrer = getCallingPackage();
            if (this.mReferrer == null && CompatUtils.isLollipopMr1Compatible() && getReferrer() != null) {
                this.mReferrer = getReferrer().getAuthority();
            }
            this.mContactType = 0;
            if (CompatUtils.isLollipopCompatible()) {
                getWindow().setStatusBarColor(0);
            }
            processIntent(getIntent());
            getWindow().setFlags(131072, 131072);
            setContentView(R.layout.quickcontact_activity);
            this.mMaterialColorMapUtils = new MaterialColorMapUtils(getResources());
            this.mScroller = (MultiShrinkScroller) findViewById(R.id.multiscroller);
            this.mContactCard = (ExpandingEntryCardView) findViewById(R.id.communication_card);
            this.mNoContactDetailsCard = (ExpandingEntryCardView) findViewById(R.id.no_contact_data_card);
            this.mAboutCard = (ExpandingEntryCardView) findViewById(R.id.about_card);
            this.mNoContactDetailsCard.setOnClickListener(this.mEntryClickHandler);
            this.mContactCard.setOnClickListener(this.mEntryClickHandler);
            this.mContactCard.setOnCreateContextMenuListener(this.mEntryContextMenuListener);
            this.mAboutCard.setOnClickListener(this.mEntryClickHandler);
            this.mAboutCard.setOnCreateContextMenuListener(this.mEntryContextMenuListener);
            this.mPhotoView = (QuickContactImageView) findViewById(R.id.photo);
            View findViewById = findViewById(R.id.transparent_view);
            if (this.mScroller != null) {
                findViewById.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        QuickContactActivity.this.mScroller.scrollOffBottom();
                    }
                });
            }
            ViewUtil.addRectangularOutlineProvider(findViewById(R.id.toolbar_parent), getResources());
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setActionBar(toolbar);
            getActionBar().setTitle((CharSequence) null);
            toolbar.addView(getLayoutInflater().inflate(R.layout.quickcontact_title_placeholder, (ViewGroup) null));
            this.mHasAlreadyBeenOpened = bundle != null;
            this.mIsEntranceAnimationFinished = this.mHasAlreadyBeenOpened;
            this.mWindowScrim = new ColorDrawable(SCRIM_COLOR);
            this.mWindowScrim.setAlpha(0);
            getWindow().setBackgroundDrawable(this.mWindowScrim);
            this.mScroller.initialize(this.mMultiShrinkScrollerListener, this.mExtraMode == 4, -1, true);
            this.mScroller.setVisibility(4);
            setHeaderNameText(R.string.missing_name);
            SchedulingUtils.doOnPreDraw(this.mScroller, true, new Runnable() {
                public void run() {
                    float f;
                    if (!QuickContactActivity.this.mHasAlreadyBeenOpened) {
                        if (QuickContactActivity.this.mExtraMode == 4) {
                            f = 1.0f;
                        } else {
                            f = QuickContactActivity.this.mScroller.getStartingTransparentHeightRatio();
                        }
                        int integer = QuickContactActivity.this.getResources().getInteger(17694720);
                        ObjectAnimator.ofInt(QuickContactActivity.this.mWindowScrim, "alpha", new int[]{0, (int) (f * 255.0f)}).setDuration((long) integer).start();
                    }
                }
            });
            if (bundle != null) {
                final int i = bundle.getInt("theme_color", 0);
                SchedulingUtils.doOnPreDraw(this.mScroller, false, new Runnable() {
                    public void run() {
                        if (QuickContactActivity.this.mHasAlreadyBeenOpened) {
                            QuickContactActivity.this.mScroller.setVisibility(0);
                            QuickContactActivity.this.mScroller.setScroll(QuickContactActivity.this.mScroller.getScrollNeededToBeFullScreen());
                        }
                        if (i != 0) {
                            QuickContactActivity quickContactActivity = QuickContactActivity.this;
                            quickContactActivity.setThemeColor(quickContactActivity.mMaterialColorMapUtils.calculatePrimaryAndSecondaryColor(i));
                        }
                    }
                });
            }
            Trace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        if (!(i == 1 && (i2 == 3 || i2 == 2))) {
            z = false;
        }
        setResult(i2, intent);
        if (z) {
            finish();
        } else if (i == 2 && i2 != 0) {
            processIntent(intent);
        } else if (i == 3) {
            if (i2 == -1 && intent != null) {
                joinAggregate(ContentUris.parseId(intent.getData()));
            }
        } else if (i == 4 && intent != null) {
            onRingtonePicked((Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI"));
        }
    }

    private void onRingtonePicked(Uri uri) {
        this.mCustomRingtone = EditorUiUtils.getRingtoneStringFromUri(uri, CURRENT_API_VERSION);
        startService(ContactSaveService.createSetRingtone(this, this.mLookupUri, this.mCustomRingtone));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mHasAlreadyBeenOpened = true;
        this.mIsEntranceAnimationFinished = true;
        this.mHasComputedThemeColor = false;
        processIntent(intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mColorFilter != null) {
            bundle.putInt("theme_color", this.mColorFilterColor);
        }
        bundle.putLong("previous_contact_id", this.mPreviousContactId);
        bundle.putBoolean("sendToVoicemailState", this.mSendToVoicemailState);
        bundle.putBoolean("arePhoneOptionsChangable", this.mArePhoneOptionsChangable);
        bundle.putString(ContactSaveService.EXTRA_CUSTOM_RINGTONE, this.mCustomRingtone);
    }

    private void processIntent(Intent intent) {
        if (intent == null) {
            finish();
        } else if ("splitCompleted".equals(intent.getAction())) {
            Toast.makeText(this, R.string.contactUnlinkedToast, 0).show();
            finish();
        } else {
            Uri data = intent.getData();
            if (intent.getBooleanExtra("contact_edited", false)) {
                setResult(4);
            }
            if (data != null && "contacts".equals(data.getAuthority())) {
                data = ContactsContract.RawContacts.getContactLookupUri(getContentResolver(), ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, ContentUris.parseId(data)));
            }
            this.mExtraMode = getIntent().getIntExtra("android.provider.extra.MODE", 3);
            if (isMultiWindowOnPhone()) {
                this.mExtraMode = 3;
            }
            this.mExtraPrioritizedMimeType = getIntent().getStringExtra("android.provider.extra.PRIORITIZED_MIMETYPE");
            Uri uri = this.mLookupUri;
            if (data == null) {
                finish();
                return;
            }
            this.mLookupUri = data;
            this.mExcludeMimes = intent.getStringArrayExtra("android.provider.extra.EXCLUDE_MIMES");
            if (uri == null) {
                this.mShouldLog = true ^ this.mIsRecreatedInstance;
                this.mContactLoader = (ContactLoader) getLoaderManager().initLoader(0, (Bundle) null, this.mLoaderContactCallbacks);
            } else if (uri != this.mLookupUri) {
                this.mShouldLog = true;
                this.mContactLoader = (ContactLoader) getLoaderManager().getLoader(0);
                this.mContactLoader.setNewLookup(this.mLookupUri);
                this.mCachedCp2DataCardModel = null;
            }
            this.mContactLoader.forceLoad();
        }
    }

    /* access modifiers changed from: private */
    public void runEntranceAnimation() {
        if (!this.mHasAlreadyBeenOpened) {
            boolean z = true;
            this.mHasAlreadyBeenOpened = true;
            MultiShrinkScroller multiShrinkScroller = this.mScroller;
            if (isMultiWindowOnPhone() || this.mExtraMode == 4) {
                z = false;
            }
            multiShrinkScroller.scrollUpForEntranceAnimation(z);
        }
    }

    private boolean isMultiWindowOnPhone() {
        return MultiWindowCompat.isInMultiWindowMode(this) && PhoneCapabilityTester.isPhone(this);
    }

    private void setHeaderNameText(int i) {
        MultiShrinkScroller multiShrinkScroller = this.mScroller;
        if (multiShrinkScroller != null) {
            multiShrinkScroller.setTitle(getText(i) == null ? null : getText(i).toString(), false);
        }
    }

    private void setHeaderNameText(String str, boolean z) {
        MultiShrinkScroller multiShrinkScroller;
        if (!TextUtils.isEmpty(str) && (multiShrinkScroller = this.mScroller) != null) {
            multiShrinkScroller.setTitle(str, z);
        }
    }

    private boolean isMimeExcluded(String str) {
        String[] strArr = this.mExcludeMimes;
        if (strArr == null) {
            return false;
        }
        for (String equals : strArr) {
            if (TextUtils.equals(equals, str)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void bindContactData(final Contact contact) {
        int i;
        Trace.beginSection("bindContactData");
        boolean z = true;
        int i2 = this.mContactData == null ? 1 : 0;
        this.mContactData = contact;
        if (DirectoryContactUtil.isDirectoryContact(this.mContactData)) {
            i = 3;
        } else if (InvisibleContactUtil.isInvisibleAndAddable(this.mContactData, this)) {
            i = 2;
        } else {
            i = isContactEditable() ? 1 : 0;
        }
        if (this.mShouldLog && this.mContactType != i) {
            Logger.logQuickContactEvent(this.mReferrer, i, 0, i2, (String) null);
        }
        this.mContactType = i;
        setStateForPhoneMenuItems(this.mContactData);
        invalidateOptionsMenu();
        Trace.endSection();
        Trace.beginSection("Set display photo & name");
        this.mPhotoView.setIsBusiness(this.mContactData.isDisplayNameFromOrganization());
        this.mPhotoSetter.setupContactPhoto(contact, this.mPhotoView);
        extractAndApplyTintFromPhotoViewAsynchronously();
        String charSequence = ContactDisplayUtils.getDisplayName(this, contact).toString();
        if (this.mContactData.getDisplayNameSource() != 20) {
            z = false;
        }
        setHeaderNameText(charSequence, z);
        String phoneticName = ContactDisplayUtils.getPhoneticName(this, contact);
        if (this.mScroller != null) {
            if (TextUtils.isEmpty(phoneticName) || phoneticName.equals(charSequence)) {
                this.mScroller.setPhoneticNameGone();
            } else {
                this.mScroller.setPhoneticName(phoneticName);
            }
        }
        Trace.endSection();
        this.mEntriesAndActionsTask = new AsyncTask<Void, Void, Cp2DataCardModel>() {
            /* access modifiers changed from: protected */
            public Cp2DataCardModel doInBackground(Void... voidArr) {
                return QuickContactActivity.this.generateDataModelFromContact(contact);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Cp2DataCardModel cp2DataCardModel) {
                super.onPostExecute(cp2DataCardModel);
                if (contact == QuickContactActivity.this.mContactData && !isCancelled()) {
                    QuickContactActivity.this.bindDataToCards(cp2DataCardModel);
                    QuickContactActivity.this.showActivity();
                }
            }
        };
        this.mEntriesAndActionsTask.execute(new Void[0]);
        NfcHandler.register(this, this.mContactData.getLookupUri());
    }

    /* access modifiers changed from: private */
    public void bindDataToCards(Cp2DataCardModel cp2DataCardModel) {
        Map<String, List<DataItem>> map = cp2DataCardModel.dataItemsMap;
        List list = map.get("vnd.android.cursor.item/phone_v2");
        boolean z = false;
        this.mOnlyOnePhoneNumber = list != null && list.size() == 1;
        List list2 = map.get("vnd.android.cursor.item/email_v2");
        if (list2 != null && list2.size() == 1) {
            z = true;
        }
        this.mOnlyOneEmail = z;
        populateContactAndAboutCard(cp2DataCardModel, true);
    }

    /* access modifiers changed from: private */
    public void showActivity() {
        MultiShrinkScroller multiShrinkScroller = this.mScroller;
        if (multiShrinkScroller != null) {
            multiShrinkScroller.setVisibility(0);
            SchedulingUtils.doOnPreDraw(this.mScroller, false, new Runnable() {
                public void run() {
                    QuickContactActivity.this.runEntranceAnimation();
                }
            });
        }
    }

    private List<List<ExpandingEntryCardView.Entry>> buildAboutCardEntries(Map<String, List<DataItem>> map) {
        ArrayList arrayList = new ArrayList();
        for (String str : SORTED_ABOUT_CARD_MIMETYPES) {
            List list = map.get(str);
            if (list != null) {
                List<ExpandingEntryCardView.Entry> dataItemsToEntries = dataItemsToEntries(list, (MutableString) null);
                if (dataItemsToEntries.size() > 0) {
                    arrayList.add(dataItemsToEntries);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mHasIntentLaunched) {
            this.mHasIntentLaunched = false;
            populateContactAndAboutCard(this.mCachedCp2DataCardModel, false);
        }
        maybeShowProgressDialog();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        dismissProgressBar();
    }

    private void populateContactAndAboutCard(Cp2DataCardModel cp2DataCardModel, boolean z) {
        Cp2DataCardModel cp2DataCardModel2 = cp2DataCardModel;
        this.mCachedCp2DataCardModel = cp2DataCardModel2;
        if (!this.mHasIntentLaunched && cp2DataCardModel2 != null) {
            Trace.beginSection("bind contact card");
            List<List<ExpandingEntryCardView.Entry>> list = cp2DataCardModel2.contactCardEntries;
            List<List<ExpandingEntryCardView.Entry>> list2 = cp2DataCardModel2.aboutCardEntries;
            String str = cp2DataCardModel2.customAboutCardName;
            if (list.size() > 0) {
                ExpandingEntryCardView expandingEntryCardView = this.mContactCard;
                expandingEntryCardView.initialize(list, 3, expandingEntryCardView.isExpanded(), true, this.mExpandingEntryCardViewListener, this.mScroller);
                if (this.mContactCard.getVisibility() == 8 && this.mShouldLog) {
                    Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 2, 0, (String) null);
                }
                this.mContactCard.setVisibility(0);
            } else {
                this.mContactCard.setVisibility(8);
            }
            Trace.endSection();
            Trace.beginSection("bind about card");
            String phoneticName = this.mContactData.getPhoneticName();
            if (z && !TextUtils.isEmpty(phoneticName)) {
                String string = getResources().getString(R.string.name_phonetic);
                ExpandingEntryCardView.EntryContextMenuInfo entryContextMenuInfo = r15;
                ExpandingEntryCardView.EntryContextMenuInfo entryContextMenuInfo2 = new ExpandingEntryCardView.EntryContextMenuInfo(phoneticName, getResources().getString(R.string.name_phonetic), (String) null, -1, false);
                ExpandingEntryCardView.Entry entry = new ExpandingEntryCardView.Entry(-1, (Drawable) null, string, phoneticName, (Drawable) null, (String) null, (Drawable) null, (Spannable) null, (Intent) null, (Drawable) null, (Intent) null, (Spannable) null, false, false, entryContextMenuInfo, (Drawable) null, (Intent) null, (String) null, 1, (Bundle) null, true, 0);
                ArrayList arrayList = new ArrayList();
                arrayList.add(entry);
                if (list2.size() <= 0 || !((ExpandingEntryCardView.Entry) list2.get(0).get(0)).getHeader().equals(getResources().getString(R.string.header_nickname_entry))) {
                    list2.add(0, arrayList);
                } else {
                    list2.add(1, arrayList);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                this.mAboutCard.setTitle(str);
            }
            this.mAboutCard.initialize(list2, 1, true, true, this.mExpandingEntryCardViewListener, this.mScroller);
            if (list.size() == 0 && list2.size() == 0) {
                initializeNoContactDetailCard(cp2DataCardModel2.areAllRawContactsSimAccounts);
            } else {
                this.mNoContactDetailsCard.setVisibility(8);
            }
            if (list2.size() > 0) {
                if (this.mAboutCard.getVisibility() == 8 && this.mShouldLog) {
                    Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 4, 0, (String) null);
                }
                this.mAboutCard.setVisibility(0);
            }
            Trace.endSection();
        }
    }

    private void initializeNoContactDetailCard(boolean z) {
        ExpandingEntryCardView.Entry entry = new ExpandingEntryCardView.Entry(-2, ResourcesCompat.getDrawable(getResources(), R.drawable.quantum_ic_phone_vd_theme_24, (Resources.Theme) null).mutate(), getString(R.string.quickcontact_add_phone_number), (String) null, (Drawable) null, (String) null, (Drawable) null, (Spannable) null, getEditContactIntent(), (Drawable) null, (Intent) null, (Spannable) null, true, false, (ExpandingEntryCardView.EntryContextMenuInfo) null, (Drawable) null, (Intent) null, (String) null, 1, (Bundle) null, true, R.drawable.quantum_ic_phone_vd_theme_24);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ArrayList(1));
        ((List) arrayList.get(0)).add(entry);
        if (!z) {
            ExpandingEntryCardView.Entry entry2 = new ExpandingEntryCardView.Entry(-2, ResourcesCompat.getDrawable(getResources(), R.drawable.quantum_ic_email_vd_theme_24, (Resources.Theme) null).mutate(), getString(R.string.quickcontact_add_email), (String) null, (Drawable) null, (String) null, (Drawable) null, (Spannable) null, getEditContactIntent(), (Drawable) null, (Intent) null, (Spannable) null, true, false, (ExpandingEntryCardView.EntryContextMenuInfo) null, (Drawable) null, (Intent) null, (String) null, 1, (Bundle) null, true, R.drawable.quantum_ic_email_vd_theme_24);
            arrayList.add(new ArrayList(1));
            ((List) arrayList.get(1)).add(entry2);
        }
        int color = getResources().getColor(R.color.quickcontact_entry_sub_header_text_color);
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        this.mNoContactDetailsCard.initialize(arrayList, 2, true, true, this.mExpandingEntryCardViewListener, this.mScroller);
        if (this.mNoContactDetailsCard.getVisibility() == 8 && this.mShouldLog) {
            Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 1, 0, (String) null);
        }
        this.mNoContactDetailsCard.setVisibility(0);
        this.mNoContactDetailsCard.setEntryHeaderColor(color);
        this.mNoContactDetailsCard.setColorAndFilter(color, porterDuffColorFilter);
    }

    /* access modifiers changed from: private */
    public Cp2DataCardModel generateDataModelFromContact(Contact contact) {
        Trace.beginSection("Build data items map");
        HashMap hashMap = new HashMap();
        boolean isTachyonEnabled = CallUtil.isTachyonEnabled(this);
        UnmodifiableIterator<RawContact> it = contact.getRawContacts().iterator();
        while (it.hasNext()) {
            RawContact next = it.next();
            for (DataItem next2 : next.getDataItems()) {
                next2.setRawContactId(next.getId().longValue());
                String mimeType = next2.getMimeType();
                if (mimeType != null) {
                    if (!"vnd.android.cursor.item/com.google.android.apps.tachyon.phone".equals(mimeType)) {
                        DataKind kindOrFallback = AccountTypeManager.getInstance(this).getKindOrFallback(next.getAccountType(this), mimeType);
                        if (kindOrFallback != null) {
                            next2.setDataKind(kindOrFallback);
                            boolean z = !TextUtils.isEmpty(next2.buildDataString(this, kindOrFallback));
                            if (!isMimeExcluded(mimeType)) {
                                if (!z) {
                                }
                            }
                        }
                    } else if (!isTachyonEnabled) {
                    }
                    List list = (List) hashMap.get(mimeType);
                    if (list == null) {
                        list = new ArrayList();
                        hashMap.put(mimeType, list);
                    }
                    list.add(next2);
                }
            }
        }
        Trace.endSection();
        bindReachability(hashMap);
        Trace.beginSection("sort within mimetypes");
        ArrayList arrayList = new ArrayList();
        for (List list2 : hashMap.values()) {
            Collapser.collapseList(list2, this);
            Collections.sort(list2, this.mWithinMimeTypeDataItemComparator);
            arrayList.add(list2);
        }
        Trace.endSection();
        Trace.beginSection("sort amongst mimetypes");
        Collections.sort(arrayList, this.mAmongstMimeTypeDataItemComparator);
        Trace.endSection();
        Trace.beginSection("cp2 data items to entries");
        ArrayList arrayList2 = new ArrayList();
        List<List<ExpandingEntryCardView.Entry>> buildAboutCardEntries = buildAboutCardEntries(hashMap);
        MutableString mutableString = new MutableString();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!SORTED_ABOUT_CARD_MIMETYPES.contains(((DataItem) ((List) arrayList.get(i)).get(0)).getMimeType())) {
                List<ExpandingEntryCardView.Entry> dataItemsToEntries = dataItemsToEntries((List) arrayList.get(i), mutableString);
                if (dataItemsToEntries.size() > 0) {
                    arrayList2.add(dataItemsToEntries);
                }
            }
        }
        Trace.endSection();
        Cp2DataCardModel cp2DataCardModel = new Cp2DataCardModel();
        cp2DataCardModel.customAboutCardName = mutableString.value;
        cp2DataCardModel.aboutCardEntries = buildAboutCardEntries;
        cp2DataCardModel.contactCardEntries = arrayList2;
        cp2DataCardModel.dataItemsMap = hashMap;
        cp2DataCardModel.areAllRawContactsSimAccounts = contact.areAllRawContactsSimAccounts(this);
        return cp2DataCardModel;
    }

    private void bindReachability(Map<String, List<DataItem>> map) {
        List<DataItem> list = map.get("vnd.android.cursor.item/phone_v2");
        List<DataItem> list2 = map.get("vnd.android.cursor.item/com.google.android.apps.tachyon.phone");
        if (list != null && list2 != null) {
            for (DataItem dataItem : list) {
                if (dataItem instanceof PhoneDataItem) {
                    PhoneDataItem phoneDataItem = (PhoneDataItem) dataItem;
                    if (phoneDataItem.getNumber() != null) {
                        for (DataItem dataItem2 : list2) {
                            if (phoneDataItem.getNumber().equals(dataItem2.getContentValues().getAsString("data1"))) {
                                phoneDataItem.setTachyonReachable(true);
                                phoneDataItem.setReachableDataItem(dataItem2);
                            }
                        }
                    }
                }
            }
        }
    }

    private static class Cp2DataCardModel {
        public List<List<ExpandingEntryCardView.Entry>> aboutCardEntries;
        public boolean areAllRawContactsSimAccounts;
        public List<List<ExpandingEntryCardView.Entry>> contactCardEntries;
        public String customAboutCardName;
        public Map<String, List<DataItem>> dataItemsMap;

        private Cp2DataCardModel() {
        }
    }

    private static class MutableString {
        public String value;

        private MutableString() {
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v0, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v0, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v6, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v7, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v1, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v1, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v8, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v2, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v2, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v10, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v13, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v3, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v6, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v3, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v15, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v8, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v4, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v4, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v16, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v19, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v20, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v21, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v22, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v5, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v9, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v5, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v23, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v6, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v10, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v6, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v7, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v11, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v7, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v24, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v8, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v12, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v8, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v25, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v9, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v13, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v9, resolved type: android.os.Bundle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v27, resolved type: android.text.SpannableString} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v10, resolved type: android.graphics.drawable.Drawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v14, resolved type: android.content.Intent} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v10, resolved type: android.os.Bundle} */
    /* JADX WARNING: type inference failed for: r14v26, types: [android.text.Spannable] */
    /* JADX WARNING: type inference failed for: r24v26 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x041c  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x041f  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0427  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x048c  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x089a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x089c  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x03b3  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x03c1  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0409  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.android.contacts.quickcontact.ExpandingEntryCardView.Entry dataItemToEntry(com.android.contacts.model.dataitem.DataItem r36, com.android.contacts.model.dataitem.DataItem r37, android.content.Context r38, com.android.contacts.model.Contact r39, com.android.contacts.quickcontact.QuickContactActivity.MutableString r40) {
        /*
            r9 = r36
            r0 = r40
            r10 = 0
            if (r39 != 0) goto L_0x0008
            return r10
        L_0x0008:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r33 = 1
            r2 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            android.content.Context r12 = r38.getApplicationContext()
            android.content.res.Resources r1 = r12.getResources()
            com.android.contacts.model.dataitem.DataKind r3 = r36.getDataKind()
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.ImDataItem
            r13 = -1
            r14 = 0
            if (r4 == 0) goto L_0x00b2
            r0 = r9
            com.android.contacts.model.dataitem.ImDataItem r0 = (com.android.contacts.model.dataitem.ImDataItem) r0
            android.util.Pair r3 = com.android.contacts.ContactsUtils.buildImIntent(r12, r0)
            java.lang.Object r3 = r3.first
            android.content.Intent r3 = (android.content.Intent) r3
            boolean r4 = r0.isCreatedFromEmail()
            boolean r6 = r0.isProtocolValid()
            if (r6 != 0) goto L_0x003e
            r4 = -1
            goto L_0x004a
        L_0x003e:
            if (r4 == 0) goto L_0x0042
            r4 = 5
            goto L_0x004a
        L_0x0042:
            java.lang.Integer r4 = r0.getProtocol()
            int r4 = r4.intValue()
        L_0x004a:
            if (r4 != r13) goto L_0x006a
            r6 = 2131755388(0x7f10017c, float:1.9141654E38)
            java.lang.String r6 = r1.getString(r6)
            java.lang.String r7 = r0.getCustomProtocol()
            java.lang.CharSequence r1 = android.provider.ContactsContract.CommonDataKinds.Im.getProtocolLabel(r1, r4, r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r4 = r0.getData()
            r35 = r4
            r4 = r1
            r1 = r6
            r6 = r35
            goto L_0x007b
        L_0x006a:
            java.lang.String r6 = r0.getCustomProtocol()
            java.lang.CharSequence r1 = android.provider.ContactsContract.CommonDataKinds.Im.getProtocolLabel(r1, r4, r6)
            java.lang.String r1 = r1.toString()
            java.lang.String r4 = r0.getData()
            r6 = r10
        L_0x007b:
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r7 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r17 = r0.getData()
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r7
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
            r15 = r1
            r16 = r4
            r18 = r6
            r27 = r7
            r6 = r10
            r14 = r6
            r22 = r14
            r24 = r22
            r28 = r24
            r29 = r28
            r30 = r29
            r32 = r30
            r25 = 1
            r31 = 1
            r34 = 0
            r10 = r3
            goto L_0x0863
        L_0x00b2:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.OrganizationDataItem
            if (r4 == 0) goto L_0x00fc
            r0 = r9
            com.android.contacts.model.dataitem.OrganizationDataItem r0 = (com.android.contacts.model.dataitem.OrganizationDataItem) r0
            r3 = 2131755392(0x7f100180, float:1.9141662E38)
            java.lang.String r1 = r1.getString(r3)
            java.lang.String r3 = r0.getCompany()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r4 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r4
            r17 = r3
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
            java.lang.String r0 = r0.getTitle()
            r18 = r0
            r15 = r1
            r16 = r3
            r27 = r4
            r6 = r10
            r14 = r6
            r22 = r14
        L_0x00ea:
            r24 = r22
            r28 = r24
            r29 = r28
            r30 = r29
            r32 = r30
        L_0x00f4:
            r25 = 1
            r31 = 1
        L_0x00f8:
            r34 = 0
            goto L_0x0863
        L_0x00fc:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.NicknameDataItem
            if (r4 == 0) goto L_0x014c
            r0 = r9
            com.android.contacts.model.dataitem.NicknameDataItem r0 = (com.android.contacts.model.dataitem.NicknameDataItem) r0
            long r3 = r39.getNameRawContactId()
            java.lang.Long r6 = r36.getRawContactId()
            long r6 = r6.longValue()
            int r8 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0115
            r3 = 1
            goto L_0x0116
        L_0x0115:
            r3 = 0
        L_0x0116:
            if (r3 == 0) goto L_0x0122
            int r3 = r39.getDisplayNameSource()
            r4 = 35
            if (r3 != r4) goto L_0x0122
            r3 = 1
            goto L_0x0123
        L_0x0122:
            r3 = 0
        L_0x0123:
            if (r3 != 0) goto L_0x0148
            r3 = 2131755390(0x7f10017e, float:1.9141658E38)
            java.lang.String r1 = r1.getString(r3)
            java.lang.String r0 = r0.getName()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r3 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r3
            r17 = r0
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
            goto L_0x01ab
        L_0x0148:
            r0 = r10
            r1 = r0
            r3 = r1
            goto L_0x01ab
        L_0x014c:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.CustomDataItem
            if (r4 == 0) goto L_0x0182
            r0 = r9
            com.android.contacts.model.dataitem.CustomDataItem r0 = (com.android.contacts.model.dataitem.CustomDataItem) r0
            java.lang.String r3 = r0.getSummary()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L_0x0165
            r3 = 2131755416(0x7f100198, float:1.914171E38)
            java.lang.String r1 = r1.getString(r3)
            goto L_0x0166
        L_0x0165:
            r1 = r3
        L_0x0166:
            java.lang.String r0 = r0.getContent()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r3 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r3
            r17 = r0
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
            goto L_0x01ab
        L_0x0182:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.NoteDataItem
            if (r4 == 0) goto L_0x01b8
            r0 = r9
            com.android.contacts.model.dataitem.NoteDataItem r0 = (com.android.contacts.model.dataitem.NoteDataItem) r0
            r3 = 2131755391(0x7f10017f, float:1.914166E38)
            java.lang.String r1 = r1.getString(r3)
            java.lang.String r0 = r0.getNote()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r3 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r3
            r17 = r0
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
        L_0x01ab:
            r16 = r0
            r15 = r1
            r27 = r3
            r6 = r10
            r14 = r6
            r18 = r14
            r22 = r18
            goto L_0x00ea
        L_0x01b8:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.WebsiteDataItem
            java.lang.String r6 = "android.intent.action.VIEW"
            if (r4 == 0) goto L_0x022d
            r0 = r9
            com.android.contacts.model.dataitem.WebsiteDataItem r0 = (com.android.contacts.model.dataitem.WebsiteDataItem) r0
            r4 = 2131755395(0x7f100183, float:1.9141668E38)
            java.lang.String r1 = r1.getString(r4)
            java.lang.String r4 = r0.getUrl()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r7 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r19 = r36.getMimeType()
            long r20 = r36.getId()
            boolean r22 = r36.isSuperPrimary()
            r16 = r7
            r17 = r4
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r22)
            com.android.contacts.quickcontact.WebAddress r8 = new com.android.contacts.quickcontact.WebAddress     // Catch:{ ParseException -> 0x01fa }
            java.lang.String r13 = r0.buildDataStringForDisplay(r12, r3)     // Catch:{ ParseException -> 0x01fa }
            r8.<init>(r13)     // Catch:{ ParseException -> 0x01fa }
            android.content.Intent r13 = new android.content.Intent     // Catch:{ ParseException -> 0x01fa }
            java.lang.String r8 = r8.toString()     // Catch:{ ParseException -> 0x01fa }
            android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch:{ ParseException -> 0x01fa }
            r13.<init>(r6, r8)     // Catch:{ ParseException -> 0x01fa }
            goto L_0x0215
        L_0x01fa:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Couldn't parse website: "
            r6.append(r8)
            java.lang.String r0 = r0.buildDataStringForDisplay(r12, r3)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            java.lang.String r3 = "QuickContact"
            android.util.Log.e(r3, r0)
            r13 = r10
        L_0x0215:
            r15 = r1
            r16 = r4
            r27 = r7
            r6 = r10
            r14 = r6
            r18 = r14
            r22 = r18
            r24 = r22
            r28 = r24
            r29 = r28
            r30 = r29
            r32 = r30
            r10 = r13
            goto L_0x00f4
        L_0x022d:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.EventDataItem
            if (r4 == 0) goto L_0x02ba
            r0 = r9
            com.android.contacts.model.dataitem.EventDataItem r0 = (com.android.contacts.model.dataitem.EventDataItem) r0
            java.lang.String r4 = r0.buildDataStringForDisplay(r12, r3)
            java.util.Calendar r7 = com.android.contacts.util.DateUtils.parseDate(r4, r14)
            if (r7 == 0) goto L_0x0262
            java.util.Date r7 = com.android.contacts.util.DateUtils.getNextAnnualDate(r7)
            android.net.Uri r8 = android.provider.CalendarContract.CONTENT_URI
            android.net.Uri$Builder r8 = r8.buildUpon()
            java.lang.String r13 = "time"
            r8.appendPath(r13)
            long r14 = r7.getTime()
            android.content.ContentUris.appendId(r8, r14)
            android.content.Intent r7 = new android.content.Intent
            r7.<init>(r6)
            android.net.Uri r6 = r8.build()
            android.content.Intent r6 = r7.setData(r6)
            goto L_0x0263
        L_0x0262:
            r6 = r10
        L_0x0263:
            r7 = 2131755387(0x7f10017b, float:1.9141652E38)
            java.lang.String r7 = r1.getString(r7)
            boolean r8 = r0.hasKindTypeColumn(r3)
            if (r8 == 0) goto L_0x0281
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r0 = r0.getLabel()
            java.lang.CharSequence r0 = com.android.contacts.compat.EventCompat.getTypeLabel(r1, r3, r0)
            java.lang.String r0 = r0.toString()
            goto L_0x0282
        L_0x0281:
            r0 = r10
        L_0x0282:
            java.lang.String r1 = com.android.contacts.util.DateUtils.formatDate(r12, r4)
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r3 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r20 = r36.getMimeType()
            long r21 = r36.getId()
            boolean r23 = r36.isSuperPrimary()
            r17 = r3
            r18 = r1
            r19 = r7
            r17.<init>(r18, r19, r20, r21, r23)
            r16 = r0
            r27 = r3
            r15 = r7
        L_0x02a2:
            r14 = r10
            r22 = r14
            r24 = r22
            r28 = r24
            r29 = r28
            r30 = r29
            r32 = r30
            r25 = 1
            r31 = 1
            r34 = 0
            r10 = r6
            r6 = r32
            goto L_0x0863
        L_0x02ba:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.RelationDataItem
            if (r4 == 0) goto L_0x0320
            r0 = r9
            com.android.contacts.model.dataitem.RelationDataItem r0 = (com.android.contacts.model.dataitem.RelationDataItem) r0
            java.lang.String r4 = r0.buildDataStringForDisplay(r12, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L_0x02dd
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r7 = "android.intent.action.SEARCH"
            r6.<init>(r7)
            java.lang.String r7 = "query"
            r6.putExtra(r7, r4)
            java.lang.String r4 = "vnd.android.cursor.dir/contact"
            r6.setType(r4)
            goto L_0x02de
        L_0x02dd:
            r6 = r10
        L_0x02de:
            r4 = 2131755394(0x7f100182, float:1.9141666E38)
            java.lang.String r4 = r1.getString(r4)
            java.lang.String r7 = r0.getName()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r8 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r20 = r36.getMimeType()
            long r21 = r36.getId()
            boolean r23 = r36.isSuperPrimary()
            r17 = r8
            r18 = r7
            r19 = r4
            r17.<init>(r18, r19, r20, r21, r23)
            boolean r14 = r0.hasKindTypeColumn(r3)
            if (r14 == 0) goto L_0x0317
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r0 = r0.getLabel()
            java.lang.CharSequence r0 = android.provider.ContactsContract.CommonDataKinds.Relation.getTypeLabel(r1, r3, r0)
            java.lang.String r0 = r0.toString()
            goto L_0x0318
        L_0x0317:
            r0 = r10
        L_0x0318:
            r18 = r0
            r15 = r4
            r16 = r7
            r27 = r8
            goto L_0x02a2
        L_0x0320:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.PhoneDataItem
            java.lang.String r7 = "android.intent.action.SENDTO"
            r8 = 2131755518(0x7f1001fe, float:1.9141918E38)
            r14 = 2131755164(0x7f10009c, float:1.91412E38)
            java.lang.String r15 = "action_type"
            java.lang.String r13 = " "
            if (r4 == 0) goto L_0x0539
            r0 = r9
            com.android.contacts.model.dataitem.PhoneDataItem r0 = (com.android.contacts.model.dataitem.PhoneDataItem) r0
            java.lang.String r4 = r0.getNumber()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0506
            java.lang.String r2 = r1.getString(r14)
            r11.append(r2)
            r11.append(r13)
            android.text.BidiFormatter r2 = sBidiFormatter
            java.lang.String r4 = r0.buildDataStringForDisplay(r12, r3)
            android.text.TextDirectionHeuristic r14 = android.text.TextDirectionHeuristics.LTR
            java.lang.String r2 = r2.unicodeWrap(r4, r14)
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r4 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r20 = r1.getString(r8)
            java.lang.String r21 = r36.getMimeType()
            long r22 = r36.getId()
            boolean r24 = r36.isSuperPrimary()
            r18 = r4
            r19 = r2
            r18.<init>(r19, r20, r21, r22, r24)
            boolean r8 = r0.hasKindTypeColumn(r3)
            if (r8 == 0) goto L_0x0399
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r8 = r0.getLabel()
            if (r3 != 0) goto L_0x038a
            boolean r14 = android.text.TextUtils.isEmpty(r8)
            if (r14 == 0) goto L_0x038a
            java.lang.String r3 = ""
            r35 = r8
            r8 = r3
            r3 = r35
            goto L_0x039b
        L_0x038a:
            java.lang.CharSequence r3 = android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(r1, r3, r8)
            java.lang.String r3 = r3.toString()
            r11.append(r3)
            r11.append(r13)
            goto L_0x039a
        L_0x0399:
            r3 = r10
        L_0x039a:
            r8 = r3
        L_0x039b:
            r11.append(r2)
            java.lang.String r13 = r11.toString()
            android.text.Spannable r14 = com.android.contacts.util.ContactDisplayUtils.getTelephoneTtsSpannable(r13, r2)
            r13 = 2131230919(0x7f0800c7, float:1.8077904E38)
            android.graphics.drawable.Drawable r18 = r1.getDrawable(r13)
            boolean r19 = com.android.contacts.util.PhoneCapabilityTester.isPhone(r12)
            if (r19 == 0) goto L_0x03c1
            java.lang.String r19 = r0.getNumber()
            android.content.Intent r6 = com.android.contacts.CallUtil.getCallIntent((java.lang.String) r19)
            r13 = 10
            r6.putExtra(r15, r13)
            goto L_0x03c2
        L_0x03c1:
            r6 = r10
        L_0x03c2:
            android.content.Intent r13 = new android.content.Intent
            r19 = r4
            java.lang.String r4 = r0.getNumber()
            r20 = r6
            java.lang.String r6 = "smsto"
            android.net.Uri r4 = android.net.Uri.fromParts(r6, r4, r10)
            r13.<init>(r7, r4)
            r4 = 11
            r13.putExtra(r15, r4)
            r4 = 2131230913(0x7f0800c1, float:1.8077892E38)
            android.graphics.drawable.Drawable r4 = r1.getDrawable(r4)
            r6 = 2131755591(0x7f100247, float:1.9142066E38)
            r7 = 1
            java.lang.Object[] r10 = new java.lang.Object[r7]
            r7 = 0
            r10[r7] = r2
            r17 = r13
            r7 = 2131230919(0x7f0800c7, float:1.8077904E38)
            java.lang.String r6 = r1.getString(r6, r10)
            r5.append(r6)
            java.lang.String r6 = r5.toString()
            android.text.Spannable r6 = com.android.contacts.util.ContactDisplayUtils.getTelephoneTtsSpannable(r6, r2)
            int r10 = com.android.contacts.CallUtil.getVideoCallingAvailability(r12)
            r22 = r10 & 2
            if (r22 == 0) goto L_0x0409
            r16 = 1
            goto L_0x040b
        L_0x0409:
            r16 = 0
        L_0x040b:
            r22 = 1
            r10 = r10 & 1
            if (r10 == 0) goto L_0x0413
            r10 = 1
            goto L_0x0414
        L_0x0413:
            r10 = 0
        L_0x0414:
            int r23 = r36.getCarrierPresence()
            r23 = r23 & 1
            if (r23 == 0) goto L_0x041f
            r23 = 1
            goto L_0x0421
        L_0x041f:
            r23 = 0
        L_0x0421:
            boolean r24 = com.android.contacts.CallUtil.isCallWithSubjectSupported(r12)
            if (r24 == 0) goto L_0x048c
            r10 = 2131230916(0x7f0800c4, float:1.8077898E38)
            android.graphics.drawable.Drawable r10 = r1.getDrawable(r10)
            r7 = 2131755174(0x7f1000a6, float:1.914122E38)
            java.lang.String r1 = r1.getString(r7)
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            r24 = r14
            long r13 = r39.getPhotoId()
            java.lang.String r15 = "PHOTO_ID"
            r7.putLong(r15, r13)
            java.lang.String r13 = r39.getPhotoUri()
            android.net.Uri r13 = com.android.contacts.util.UriUtils.parseUriOrNull(r13)
            java.lang.String r14 = "PHOTO_URI"
            r7.putParcelable(r14, r13)
            android.net.Uri r13 = r39.getLookupUri()
            java.lang.String r14 = "CONTACT_URI"
            r7.putParcelable(r14, r13)
            java.lang.String r13 = r39.getDisplayName()
            java.lang.String r14 = "NAME_OR_NUMBER"
            r7.putString(r14, r13)
            java.lang.String r13 = "IS_BUSINESS"
            r14 = 0
            r7.putBoolean(r13, r14)
            java.lang.String r13 = r0.getNumber()
            java.lang.String r14 = "NUMBER"
            r7.putString(r14, r13)
            java.lang.String r0 = r0.getFormattedPhoneNumber()
            java.lang.String r13 = "DISPLAY_NUMBER"
            r7.putString(r13, r0)
            java.lang.String r0 = "NUMBER_LABEL"
            r7.putString(r0, r3)
            r3 = r1
            r1 = r10
            r10 = r18
            r0 = 0
            r13 = 3
        L_0x0487:
            r14 = 2131230919(0x7f0800c7, float:1.8077904E38)
            goto L_0x051b
        L_0x048c:
            r24 = r14
            r3 = 2131230929(0x7f0800d1, float:1.8077925E38)
            if (r10 == 0) goto L_0x04be
            if (r16 == 0) goto L_0x0497
            if (r23 == 0) goto L_0x04be
        L_0x0497:
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r3)
            java.lang.String r0 = r0.getNumber()
            java.lang.String r7 = "com.android.contacts.quickcontact.QuickContactActivity"
            android.content.Intent r0 = com.android.contacts.CallUtil.getVideoCallIntent(r0, r7)
            r7 = 12
            r0.putExtra(r15, r7)
            r7 = 2131755275(0x7f10010b, float:1.9141425E38)
            java.lang.String r1 = r1.getString(r7)
            r10 = r18
            r7 = 0
            r13 = 2
            r14 = 2131230919(0x7f0800c7, float:1.8077904E38)
            r35 = r3
            r3 = r1
            r1 = r35
            goto L_0x051b
        L_0x04be:
            boolean r7 = com.android.contacts.CallUtil.isTachyonEnabled(r12)
            if (r7 == 0) goto L_0x04fe
            boolean r7 = r0.isTachyonReachable()
            if (r7 == 0) goto L_0x04fe
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r3)
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r7 = "com.google.android.apps.tachyon.action.CALL"
            r3.<init>(r7)
            java.lang.String r7 = r0.getNumber()
            java.lang.String r10 = "tel"
            r13 = 0
            android.net.Uri r7 = android.net.Uri.fromParts(r10, r7, r13)
            r3.setData(r7)
            com.android.contacts.model.dataitem.DataItem r0 = r0.getReachableDataItem()
            android.content.ContentValues r0 = r0.getContentValues()
            java.lang.String r7 = "data2"
            java.lang.String r0 = r0.getAsString(r7)
            r10 = r18
            r7 = 0
            r13 = 2
            r14 = 2131230919(0x7f0800c7, float:1.8077904E38)
            r35 = r3
            r3 = r0
            r0 = r35
            goto L_0x051b
        L_0x04fe:
            r10 = r18
            r0 = 0
            r1 = 0
            r3 = 0
            r7 = 0
            r13 = 1
            goto L_0x0487
        L_0x0506:
            r14 = 0
            r22 = 1
            r17 = r2
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r10 = 0
            r13 = 1
            r19 = 0
            r20 = 0
            r24 = 0
        L_0x051b:
            r29 = r0
            r28 = r1
            r15 = r2
            r30 = r3
            r22 = r4
            r32 = r7
            r18 = r8
            r31 = r13
            r34 = r14
            r2 = r17
            r27 = r19
            r16 = 0
            r25 = 1
            r14 = r10
            r10 = r20
            goto L_0x0863
        L_0x0539:
            r10 = 0
            r22 = 1
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.EmailDataItem
            if (r4 == 0) goto L_0x05dd
            r0 = r9
            com.android.contacts.model.dataitem.EmailDataItem r0 = (com.android.contacts.model.dataitem.EmailDataItem) r0
            java.lang.String r4 = r0.getData()
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L_0x05b9
            r6 = 2131755328(0x7f100140, float:1.9141532E38)
            java.lang.String r6 = r1.getString(r6)
            r11.append(r6)
            r11.append(r13)
            java.lang.String r6 = "mailto"
            r8 = 0
            android.net.Uri r4 = android.net.Uri.fromParts(r6, r4, r8)
            android.content.Intent r10 = new android.content.Intent
            r10.<init>(r7, r4)
            r4 = 13
            r10.putExtra(r15, r4)
            java.lang.String r4 = r0.getAddress()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r6 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            r7 = 2131755324(0x7f10013c, float:1.9141524E38)
            java.lang.String r25 = r1.getString(r7)
            java.lang.String r26 = r36.getMimeType()
            long r27 = r36.getId()
            boolean r29 = r36.isSuperPrimary()
            r23 = r6
            r24 = r4
            r23.<init>(r24, r25, r26, r27, r29)
            boolean r7 = r0.hasKindTypeColumn(r3)
            if (r7 == 0) goto L_0x05a8
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r0 = r0.getLabel()
            java.lang.CharSequence r0 = android.provider.ContactsContract.CommonDataKinds.Email.getTypeLabel(r1, r3, r0)
            java.lang.String r0 = r0.toString()
            r11.append(r0)
            r11.append(r13)
            goto L_0x05a9
        L_0x05a8:
            r0 = 0
        L_0x05a9:
            r11.append(r4)
            r14 = 2131230900(0x7f0800b4, float:1.8077866E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r14)
        L_0x05b3:
            r35 = r10
            r10 = r1
            r1 = r35
            goto L_0x05bf
        L_0x05b9:
            r0 = 0
            r1 = 0
            r4 = 0
            r6 = 0
            r10 = 0
            r14 = 0
        L_0x05bf:
            r18 = r0
            r15 = r4
            r27 = r6
            r34 = r14
            r6 = 0
            r16 = 0
            r22 = 0
        L_0x05cb:
            r24 = 0
            r25 = 1
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 1
            r32 = 0
            r14 = r10
            r10 = r1
            goto L_0x0863
        L_0x05dd:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.StructuredPostalDataItem
            if (r4 == 0) goto L_0x0689
            r0 = r9
            com.android.contacts.model.dataitem.StructuredPostalDataItem r0 = (com.android.contacts.model.dataitem.StructuredPostalDataItem) r0
            java.lang.String r4 = r0.getFormattedAddress()
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L_0x0672
            r2 = 2131755435(0x7f1001ab, float:1.914175E38)
            java.lang.String r2 = r1.getString(r2)
            r11.append(r2)
            r11.append(r13)
            android.content.Intent r10 = com.android.contacts.util.StructuredPostalUtils.getViewPostalAddressIntent(r4)
            r2 = 15
            r10.putExtra(r15, r2)
            java.lang.String r2 = r0.getFormattedAddress()
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r6 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            r7 = 2131755524(0x7f100204, float:1.914193E38)
            java.lang.String r25 = r1.getString(r7)
            java.lang.String r26 = r36.getMimeType()
            long r27 = r36.getId()
            boolean r29 = r36.isSuperPrimary()
            r23 = r6
            r24 = r2
            r23.<init>(r24, r25, r26, r27, r29)
            boolean r7 = r0.hasKindTypeColumn(r3)
            if (r7 == 0) goto L_0x0641
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r0 = r0.getLabel()
            java.lang.CharSequence r0 = android.provider.ContactsContract.CommonDataKinds.StructuredPostal.getTypeLabel(r1, r3, r0)
            java.lang.String r0 = r0.toString()
            r11.append(r0)
            r11.append(r13)
            goto L_0x0642
        L_0x0641:
            r0 = 0
        L_0x0642:
            r11.append(r2)
            android.content.Intent r3 = com.android.contacts.util.StructuredPostalUtils.getViewPostalAddressDirectionsIntent(r4)
            r4 = 16
            r3.putExtra(r15, r4)
            r4 = 2131230896(0x7f0800b0, float:1.8077858E38)
            android.graphics.drawable.Drawable r4 = r1.getDrawable(r4)
            r7 = 2131755249(0x7f1000f1, float:1.9141372E38)
            java.lang.String r7 = r1.getString(r7)
            r5.append(r7)
            r5.append(r13)
            r5.append(r2)
            r14 = 2131230920(0x7f0800c8, float:1.8077906E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r14)
            r35 = r10
            r10 = r1
            r1 = r35
            goto L_0x067a
        L_0x0672:
            r3 = r2
            r0 = 0
            r1 = 0
            r2 = 0
            r4 = 0
            r6 = 0
            r10 = 0
            r14 = 0
        L_0x067a:
            r18 = r0
            r15 = r2
            r2 = r3
            r22 = r4
            r27 = r6
            r34 = r14
            r6 = 0
            r16 = 0
            goto L_0x05cb
        L_0x0689:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.SipAddressDataItem
            if (r4 == 0) goto L_0x06ff
            r0 = r9
            com.android.contacts.model.dataitem.SipAddressDataItem r0 = (com.android.contacts.model.dataitem.SipAddressDataItem) r0
            java.lang.String r4 = r0.getSipAddress()
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 != 0) goto L_0x05b9
            java.lang.String r6 = r1.getString(r14)
            r11.append(r6)
            r11.append(r13)
            boolean r6 = com.android.contacts.util.PhoneCapabilityTester.isSipPhone(r12)
            if (r6 == 0) goto L_0x06bb
            java.lang.String r6 = "sip"
            r7 = 0
            android.net.Uri r6 = android.net.Uri.fromParts(r6, r4, r7)
            android.content.Intent r10 = com.android.contacts.CallUtil.getCallIntent((android.net.Uri) r6)
            r6 = 14
            r10.putExtra(r15, r6)
            goto L_0x06bc
        L_0x06bb:
            r10 = 0
        L_0x06bc:
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r6 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r25 = r1.getString(r8)
            java.lang.String r26 = r36.getMimeType()
            long r27 = r36.getId()
            boolean r29 = r36.isSuperPrimary()
            r23 = r6
            r24 = r4
            r23.<init>(r24, r25, r26, r27, r29)
            boolean r7 = r0.hasKindTypeColumn(r3)
            if (r7 == 0) goto L_0x06f2
            int r3 = r0.getKindTypeColumn(r3)
            java.lang.String r0 = r0.getLabel()
            java.lang.CharSequence r0 = android.provider.ContactsContract.CommonDataKinds.SipAddress.getTypeLabel(r1, r3, r0)
            java.lang.String r0 = r0.toString()
            r11.append(r0)
            r11.append(r13)
            goto L_0x06f3
        L_0x06f2:
            r0 = 0
        L_0x06f3:
            r11.append(r4)
            r14 = 2131230895(0x7f0800af, float:1.8077856E38)
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r14)
            goto L_0x05b3
        L_0x06ff:
            boolean r4 = r9 instanceof com.android.contacts.model.dataitem.StructuredNameDataItem
            if (r4 == 0) goto L_0x075e
            boolean r3 = r36.isSuperPrimary()
            if (r3 != 0) goto L_0x0713
            java.lang.String r3 = r0.value
            if (r3 == 0) goto L_0x0713
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0742
        L_0x0713:
            r3 = r9
            com.android.contacts.model.dataitem.StructuredNameDataItem r3 = (com.android.contacts.model.dataitem.StructuredNameDataItem) r3
            java.lang.String r3 = r3.getGivenName()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            r6 = 2131755036(0x7f10001c, float:1.914094E38)
            if (r4 != 0) goto L_0x073c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = r1.getString(r6)
            r4.append(r1)
            r4.append(r13)
            r4.append(r3)
            java.lang.String r1 = r4.toString()
            r0.value = r1
            goto L_0x0742
        L_0x073c:
            java.lang.String r1 = r1.getString(r6)
            r0.value = r1
        L_0x0742:
            r6 = 0
            r10 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r18 = 0
        L_0x074a:
            r22 = 0
            r24 = 0
            r25 = 1
        L_0x0750:
            r27 = 0
        L_0x0752:
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 1
            r32 = 0
            goto L_0x00f8
        L_0x075e:
            boolean r0 = com.android.contacts.CallUtil.isTachyonEnabled(r12)
            if (r0 == 0) goto L_0x0772
            java.lang.String r0 = r36.getMimeType()
            java.lang.String r4 = "vnd.android.cursor.item/com.google.android.apps.tachyon.phone"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0772
            r0 = 0
            return r0
        L_0x0772:
            java.lang.String r7 = r9.buildDataStringForDisplay(r12, r3)
            java.lang.String r8 = r3.typeColumn
            android.content.Intent r3 = new android.content.Intent
            r3.<init>(r6)
            android.net.Uri r0 = android.provider.ContactsContract.Data.CONTENT_URI
            long r13 = r36.getId()
            android.net.Uri r0 = android.content.ContentUris.withAppendedId(r0, r13)
            java.lang.String r4 = r36.getMimeType()
            r3.setDataAndType(r0, r4)
            r0 = 17
            r3.putExtra(r15, r0)
            java.lang.String r0 = r36.getMimeType()
            java.lang.String r4 = "third_party_action"
            r3.putExtra(r4, r0)
            java.lang.String r0 = r3.getType()
            java.lang.String r4 = "vnd.android.cursor.item/vnd.googleplus.profile.comm"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0811
            r0 = 2131230906(0x7f0800ba, float:1.8077878E38)
            r4 = 2131230905(0x7f0800b9, float:1.8077876E38)
            if (r37 == 0) goto L_0x07f2
            android.graphics.drawable.Drawable r13 = r1.getDrawable(r4)
            android.graphics.drawable.Drawable r14 = r1.getDrawable(r0)
            com.android.contacts.quickcontact.QuickContactActivity$HangoutsDataItemModel r15 = new com.android.contacts.quickcontact.QuickContactActivity$HangoutsDataItemModel
            r0 = r15
            r1 = r3
            r3 = r36
            r4 = r37
            r6 = r7
            r7 = r8
            r8 = r12
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            populateHangoutsDataItemModel(r15)
            android.content.Intent r0 = r15.intent
            android.content.Intent r1 = r15.alternateIntent
            java.lang.StringBuilder r5 = r15.alternateContentDescription
            java.lang.String r2 = r15.header
            java.lang.String r3 = r15.text
            r10 = r0
            r15 = r2
            r18 = r3
            r22 = r14
            r6 = 0
            r16 = 0
            r24 = 0
            r25 = 1
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 1
            r32 = 0
            r34 = 0
            r2 = r1
            r14 = r13
            goto L_0x0863
        L_0x07f2:
            java.lang.String r6 = r3.getDataString()
            java.lang.String r13 = "hangout"
            boolean r6 = r13.equals(r6)
            if (r6 == 0) goto L_0x0803
            android.graphics.drawable.Drawable r0 = r1.getDrawable(r0)
            goto L_0x0807
        L_0x0803:
            android.graphics.drawable.Drawable r0 = r1.getDrawable(r4)
        L_0x0807:
            r14 = r0
            r10 = r3
            r15 = r7
            r18 = r8
            r6 = 0
            r16 = 0
            goto L_0x074a
        L_0x0811:
            com.android.contacts.quickcontact.ResolveCache r1 = com.android.contacts.quickcontact.ResolveCache.getInstance(r12)
            java.lang.String r4 = r36.getMimeType()
            android.graphics.drawable.Drawable r1 = r1.getIcon(r4, r3)
            if (r1 == 0) goto L_0x0822
            r1.mutate()
        L_0x0822:
            java.lang.String r4 = "vnd.android.cursor.item/vnd.googleplus.profile"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0853
            com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo r4 = new com.android.contacts.quickcontact.ExpandingEntryCardView$EntryContextMenuInfo
            java.lang.String r26 = r36.getMimeType()
            long r27 = r36.getId()
            boolean r29 = r36.isSuperPrimary()
            r23 = r4
            r24 = r7
            r25 = r0
            r23.<init>(r24, r25, r26, r27, r29)
            r14 = r1
            r10 = r3
            r27 = r4
            r15 = r7
            r18 = r8
            r6 = 0
            r16 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            goto L_0x0752
        L_0x0853:
            r14 = r1
            r10 = r3
            r15 = r7
            r18 = r8
            r6 = 0
            r16 = 0
            r22 = 0
            r24 = 0
            r25 = 0
            goto L_0x0750
        L_0x0863:
            if (r10 == 0) goto L_0x086c
            boolean r0 = com.android.contacts.util.PhoneCapabilityTester.isIntentRegistered(r12, r10)
            if (r0 != 0) goto L_0x086c
            r10 = 0
        L_0x086c:
            if (r2 == 0) goto L_0x0884
            boolean r0 = com.android.contacts.util.PhoneCapabilityTester.isIntentRegistered(r12, r2)
            if (r0 != 0) goto L_0x0877
            r23 = 0
            goto L_0x0886
        L_0x0877:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x0884
            java.lang.String r0 = getIntentResolveLabel(r2, r12)
            r5.append(r0)
        L_0x0884:
            r23 = r2
        L_0x0886:
            if (r14 != 0) goto L_0x089c
            boolean r0 = android.text.TextUtils.isEmpty(r15)
            if (r0 == 0) goto L_0x089c
            boolean r0 = android.text.TextUtils.isEmpty(r16)
            if (r0 == 0) goto L_0x089c
            boolean r0 = android.text.TextUtils.isEmpty(r18)
            if (r0 == 0) goto L_0x089c
            r0 = 0
            return r0
        L_0x089c:
            long r0 = r36.getId()
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x08a9
            r13 = -1
            goto L_0x08af
        L_0x08a9:
            long r0 = r36.getId()
            int r1 = (int) r0
            r13 = r1
        L_0x08af:
            com.android.contacts.quickcontact.ExpandingEntryCardView$Entry r0 = new com.android.contacts.quickcontact.ExpandingEntryCardView$Entry
            if (r24 != 0) goto L_0x08bf
            android.text.SpannableString r1 = new android.text.SpannableString
            java.lang.String r2 = r11.toString()
            r1.<init>(r2)
            r20 = r1
            goto L_0x08c1
        L_0x08bf:
            r20 = r24
        L_0x08c1:
            if (r6 != 0) goto L_0x08cf
            android.text.SpannableString r1 = new android.text.SpannableString
            java.lang.String r2 = r5.toString()
            r1.<init>(r2)
            r24 = r1
            goto L_0x08d1
        L_0x08cf:
            r24 = r6
        L_0x08d1:
            r26 = 0
            r17 = 0
            r19 = 0
            r12 = r0
            r21 = r10
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.quickcontact.QuickContactActivity.dataItemToEntry(com.android.contacts.model.dataitem.DataItem, com.android.contacts.model.dataitem.DataItem, android.content.Context, com.android.contacts.model.Contact, com.android.contacts.quickcontact.QuickContactActivity$MutableString):com.android.contacts.quickcontact.ExpandingEntryCardView$Entry");
    }

    private List<ExpandingEntryCardView.Entry> dataItemsToEntries(List<DataItem> list, MutableString mutableString) {
        if (list.get(0).getMimeType().equals("vnd.android.cursor.item/vnd.googleplus.profile")) {
            return gPlusDataItemsToEntries(list);
        }
        if (list.get(0).getMimeType().equals("vnd.android.cursor.item/vnd.googleplus.profile.comm")) {
            return hangoutsDataItemsToEntries(list);
        }
        ArrayList arrayList = new ArrayList();
        for (DataItem dataItemToEntry : list) {
            ExpandingEntryCardView.Entry dataItemToEntry2 = dataItemToEntry(dataItemToEntry, (DataItem) null, this, this.mContactData, mutableString);
            if (dataItemToEntry2 != null) {
                arrayList.add(dataItemToEntry2);
            }
        }
        return arrayList;
    }

    private Map<Long, List<DataItem>> dataItemsToBucket(List<DataItem> list) {
        HashMap hashMap = new HashMap();
        for (DataItem next : list) {
            List list2 = (List) hashMap.get(next.getRawContactId());
            if (list2 == null) {
                list2 = new ArrayList();
                hashMap.put(next.getRawContactId(), list2);
            }
            list2.add(next);
        }
        return hashMap;
    }

    private List<ExpandingEntryCardView.Entry> gPlusDataItemsToEntries(List<DataItem> list) {
        ExpandingEntryCardView.Entry dataItemToEntry;
        ArrayList arrayList = new ArrayList();
        for (List<DataItem> it : dataItemsToBucket(list).values()) {
            for (DataItem dataItem : it) {
                if ("view".equals(dataItem.getContentValues().getAsString("data5")) && (dataItemToEntry = dataItemToEntry(dataItem, (DataItem) null, this, this.mContactData, (MutableString) null)) != null) {
                    arrayList.add(dataItemToEntry);
                }
            }
        }
        return arrayList;
    }

    private List<ExpandingEntryCardView.Entry> hangoutsDataItemsToEntries(List<DataItem> list) {
        ArrayList arrayList = new ArrayList();
        for (List<DataItem> next : dataItemsToBucket(list).values()) {
            if (next.size() == 2) {
                ExpandingEntryCardView.Entry dataItemToEntry = dataItemToEntry((DataItem) next.get(0), (DataItem) next.get(1), this, this.mContactData, (MutableString) null);
                if (dataItemToEntry != null) {
                    arrayList.add(dataItemToEntry);
                }
            } else {
                for (DataItem dataItemToEntry2 : next) {
                    ExpandingEntryCardView.Entry dataItemToEntry3 = dataItemToEntry(dataItemToEntry2, (DataItem) null, this, this.mContactData, (MutableString) null);
                    if (dataItemToEntry3 != null) {
                        arrayList.add(dataItemToEntry3);
                    }
                }
            }
        }
        return arrayList;
    }

    private static final class HangoutsDataItemModel {
        public StringBuilder alternateContentDescription;
        public Intent alternateIntent;
        public Context context;
        public DataItem dataItem;
        public String header;
        public Intent intent;
        public DataItem secondDataItem;
        public String text;

        public HangoutsDataItemModel(Intent intent2, Intent intent3, DataItem dataItem2, DataItem dataItem3, StringBuilder sb, String str, String str2, Context context2) {
            this.intent = intent2;
            this.alternateIntent = intent3;
            this.dataItem = dataItem2;
            this.secondDataItem = dataItem3;
            this.alternateContentDescription = sb;
            this.header = str;
            this.text = str2;
            this.context = context2;
        }
    }

    private static void populateHangoutsDataItemModel(HangoutsDataItemModel hangoutsDataItemModel) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, hangoutsDataItemModel.secondDataItem.getId()), hangoutsDataItemModel.secondDataItem.getMimeType());
        intent.putExtra("action_type", 17);
        intent.putExtra("third_party_action", hangoutsDataItemModel.secondDataItem.getMimeType());
        if ("hangout".equals(hangoutsDataItemModel.dataItem.getContentValues().getAsString("data5"))) {
            hangoutsDataItemModel.alternateIntent = hangoutsDataItemModel.intent;
            hangoutsDataItemModel.alternateContentDescription = new StringBuilder(hangoutsDataItemModel.header);
            hangoutsDataItemModel.intent = intent;
            DataItem dataItem = hangoutsDataItemModel.secondDataItem;
            hangoutsDataItemModel.header = dataItem.buildDataStringForDisplay(hangoutsDataItemModel.context, dataItem.getDataKind());
            hangoutsDataItemModel.text = hangoutsDataItemModel.secondDataItem.getDataKind().typeColumn;
        } else if ("conversation".equals(hangoutsDataItemModel.dataItem.getContentValues().getAsString("data5"))) {
            hangoutsDataItemModel.alternateIntent = intent;
            DataItem dataItem2 = hangoutsDataItemModel.secondDataItem;
            hangoutsDataItemModel.alternateContentDescription = new StringBuilder(dataItem2.buildDataStringForDisplay(hangoutsDataItemModel.context, dataItem2.getDataKind()));
        }
    }

    private static String getIntentResolveLabel(Intent intent, Context context) {
        ResolveInfo resolveInfo;
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        int size = queryIntentActivities.size();
        if (size == 1) {
            resolveInfo = queryIntentActivities.get(0);
        } else {
            resolveInfo = size > 1 ? ResolveCache.getInstance(context).getBestResolve(intent, queryIntentActivities) : null;
        }
        if (resolveInfo == null) {
            return null;
        }
        return String.valueOf(resolveInfo.loadLabel(context.getPackageManager()));
    }

    private void extractAndApplyTintFromPhotoViewAsynchronously() {
        if (this.mScroller != null) {
            final Drawable drawable = this.mPhotoView.getDrawable();
            new AsyncTask<Void, Void, MaterialColorMapUtils.MaterialPalette>() {
                /* access modifiers changed from: protected */
                public MaterialColorMapUtils.MaterialPalette doInBackground(Void... voidArr) {
                    if ((drawable instanceof BitmapDrawable) && QuickContactActivity.this.mContactData != null && QuickContactActivity.this.mContactData.getThumbnailPhotoBinaryData() != null && QuickContactActivity.this.mContactData.getThumbnailPhotoBinaryData().length > 0) {
                        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(QuickContactActivity.this.mContactData.getThumbnailPhotoBinaryData(), 0, QuickContactActivity.this.mContactData.getThumbnailPhotoBinaryData().length);
                        try {
                            int access$2600 = QuickContactActivity.this.colorFromBitmap(decodeByteArray);
                            if (access$2600 != 0) {
                                return QuickContactActivity.this.mMaterialColorMapUtils.calculatePrimaryAndSecondaryColor(access$2600);
                            }
                            decodeByteArray.recycle();
                        } finally {
                            decodeByteArray.recycle();
                        }
                    }
                    Drawable drawable = drawable;
                    if (!(drawable instanceof LetterTileDrawable)) {
                        return MaterialColorMapUtils.getDefaultPrimaryAndSecondaryColors(QuickContactActivity.this.getResources());
                    }
                    return QuickContactActivity.this.mMaterialColorMapUtils.calculatePrimaryAndSecondaryColor(((LetterTileDrawable) drawable).getColor());
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(MaterialColorMapUtils.MaterialPalette materialPalette) {
                    super.onPostExecute(materialPalette);
                    if (!QuickContactActivity.this.mHasComputedThemeColor && drawable == QuickContactActivity.this.mPhotoView.getDrawable()) {
                        boolean unused = QuickContactActivity.this.mHasComputedThemeColor = true;
                        QuickContactActivity.this.setThemeColor(materialPalette);
                    }
                }
            }.execute(new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void setThemeColor(MaterialColorMapUtils.MaterialPalette materialPalette) {
        this.mColorFilterColor = materialPalette.mPrimaryColor;
        this.mScroller.setHeaderTintColor(this.mColorFilterColor);
        this.mStatusBarColor = materialPalette.mSecondaryColor;
        updateStatusBarColor();
        this.mColorFilter = new PorterDuffColorFilter(this.mColorFilterColor, PorterDuff.Mode.SRC_ATOP);
        this.mContactCard.setColorAndFilter(this.mColorFilterColor, this.mColorFilter);
        this.mAboutCard.setColorAndFilter(this.mColorFilterColor, this.mColorFilter);
    }

    /* access modifiers changed from: private */
    public void updateStatusBarColor() {
        if (this.mScroller != null && CompatUtils.isLollipopCompatible()) {
            ObjectAnimator ofInt = ObjectAnimator.ofInt(getWindow(), "statusBarColor", new int[]{getWindow().getStatusBarColor(), this.mScroller.getScrollNeededToBeFullScreen() <= 0 ? this.mStatusBarColor : 0});
            ofInt.setDuration(150);
            ofInt.setEvaluator(new ArgbEvaluator());
            ofInt.start();
        }
    }

    /* access modifiers changed from: private */
    public int colorFromBitmap(Bitmap bitmap) {
        Palette generate = Palette.generate(bitmap, 24);
        if (generate == null || generate.getVibrantSwatch() == null) {
            return 0;
        }
        return generate.getVibrantSwatch().getRgb();
    }

    public void onBackPressed() {
        int intExtra = getIntent().getIntExtra("previous_screen_type", 0);
        if ((intExtra == 4 || intExtra == 3) && !SharedPreferenceUtil.getHamburgerPromoTriggerActionHappenedBefore(this)) {
            SharedPreferenceUtil.setHamburgerPromoTriggerActionHappenedBefore(this);
        }
        MultiShrinkScroller multiShrinkScroller = this.mScroller;
        if (multiShrinkScroller == null) {
            super.onBackPressed();
        } else if (!this.mIsExitAnimationInProgress) {
            multiShrinkScroller.scrollOffBottom();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        AsyncTask<Void, Void, Cp2DataCardModel> asyncTask = this.mEntriesAndActionsTask;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
    }

    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mListener);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public boolean isContactEditable() {
        Contact contact = this.mContactData;
        return contact != null && !contact.isDirectoryEntry();
    }

    private boolean isContactShareable() {
        Contact contact = this.mContactData;
        return contact != null && !contact.isDirectoryEntry();
    }

    private Intent getEditContactIntent() {
        return EditorIntents.createEditContactIntent(this, this.mContactData.getLookupUri(), this.mHasComputedThemeColor ? new MaterialColorMapUtils.MaterialPalette(this.mColorFilterColor, this.mStatusBarColor) : null, this.mContactData.getPhotoId());
    }

    /* access modifiers changed from: private */
    public void editContact() {
        this.mHasIntentLaunched = true;
        this.mContactLoader.cacheResult();
        startActivityForResult(getEditContactIntent(), 1);
    }

    private void deleteContact() {
        ContactDeletionInteraction.start(this, this.mContactData.getLookupUri(), true);
    }

    private void toggleStar(MenuItem menuItem, boolean z) {
        CharSequence charSequence;
        ContactDisplayUtils.configureStarredMenuItem(menuItem, this.mContactData.isDirectoryEntry(), this.mContactData.isUserProfile(), !z);
        startService(ContactSaveService.createSetStarredIntent(this, this.mContactData.getLookupUri(), !z));
        if (!z) {
            charSequence = getResources().getText(R.string.description_action_menu_add_star);
        } else {
            charSequence = getResources().getText(R.string.description_action_menu_remove_star);
        }
        this.mScroller.announceForAccessibility(charSequence);
    }

    private void shareContact() {
        Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, this.mContactData.getLookupKey());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/x-vcard");
        intent.putExtra("android.intent.extra.STREAM", withAppendedPath);
        Intent createChooser = Intent.createChooser(intent, getResources().getQuantityString(R.plurals.title_share_via, 1));
        try {
            this.mHasIntentLaunched = true;
            ImplicitIntentsUtil.startActivityOutsideApp(this, createChooser);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, R.string.share_error, 0).show();
        }
    }

    private void createLauncherShortcutWithContact() {
        if (BuildCompat.isAtLeastO()) {
            ShortcutManager shortcutManager = (ShortcutManager) getSystemService("shortcut");
            DynamicShortcuts dynamicShortcuts = new DynamicShortcuts(this);
            String displayName = this.mContactData.getDisplayName();
            if (displayName == null) {
                displayName = getString(R.string.missing_name);
            }
            ShortcutInfo quickContactShortcutInfo = dynamicShortcuts.getQuickContactShortcutInfo(this.mContactData.getId(), this.mContactData.getLookupKey(), displayName);
            if (quickContactShortcutInfo != null) {
                shortcutManager.requestPinShortcut(quickContactShortcutInfo, (IntentSender) null);
                return;
            }
            return;
        }
        new ShortcutIntentBuilder(this, new ShortcutIntentBuilder.OnShortcutIntentCreatedListener() {
            public void onShortcutIntentCreated(Uri uri, Intent intent) {
                String str;
                intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                QuickContactActivity.this.sendBroadcast(intent);
                String stringExtra = intent.getStringExtra("android.intent.extra.shortcut.NAME");
                if (TextUtils.isEmpty(stringExtra)) {
                    str = QuickContactActivity.this.getString(R.string.createContactShortcutSuccessful_NoName);
                } else {
                    str = QuickContactActivity.this.getString(R.string.createContactShortcutSuccessful, new Object[]{stringExtra});
                }
                Toast.makeText(QuickContactActivity.this, str, 0).show();
            }
        }).createContactShortcutIntent(this.mContactData.getLookupUri());
    }

    private boolean isShortcutCreatable() {
        Contact contact = this.mContactData;
        if (contact == null || contact.isUserProfile() || this.mContactData.isDirectoryEntry()) {
            return false;
        }
        if (BuildCompat.isAtLeastO()) {
            return ((ShortcutManager) getSystemService("shortcut")).isRequestPinShortcutSupported();
        }
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        List<ResolveInfo> queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return false;
        }
        return true;
    }

    private void setStateForPhoneMenuItems(Contact contact) {
        if (contact != null) {
            this.mSendToVoicemailState = contact.isSendToVoicemail();
            this.mCustomRingtone = contact.getCustomRingtone();
            this.mArePhoneOptionsChangable = isContactEditable() && PhoneCapabilityTester.isPhone(this);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quickcontact, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean z = false;
        if (this.mContactData == null) {
            return false;
        }
        ContactDisplayUtils.configureStarredMenuItem(menu.findItem(R.id.menu_star), this.mContactData.isDirectoryEntry(), this.mContactData.isUserProfile(), this.mContactData.getStarred());
        MenuItem findItem = menu.findItem(R.id.menu_edit);
        findItem.setVisible(true);
        if (DirectoryContactUtil.isDirectoryContact(this.mContactData) || InvisibleContactUtil.isInvisibleAndAddable(this.mContactData, this)) {
            findItem.setIcon(R.drawable.quantum_ic_person_add_vd_theme_24);
            findItem.setTitle(R.string.menu_add_contact);
        } else if (isContactEditable()) {
            findItem.setIcon(R.drawable.quantum_ic_create_vd_theme_24);
            findItem.setTitle(R.string.menu_editContact);
        } else {
            findItem.setVisible(false);
        }
        MenuItem findItem2 = menu.findItem(R.id.menu_join);
        findItem2.setVisible(!InvisibleContactUtil.isInvisibleAndAddable(this.mContactData, this) && isContactEditable() && !this.mContactData.isUserProfile() && !this.mContactData.isMultipleRawContacts());
        menu.findItem(R.id.menu_linked_contacts).setVisible(this.mContactData.isMultipleRawContacts() && !findItem2.isVisible());
        menu.findItem(R.id.menu_delete).setVisible(isContactEditable() && !this.mContactData.isUserProfile());
        menu.findItem(R.id.menu_share).setVisible(isContactShareable());
        menu.findItem(R.id.menu_create_contact_shortcut).setVisible(isShortcutCreatable());
        menu.findItem(R.id.menu_set_ringtone).setVisible(!this.mContactData.isUserProfile() && this.mArePhoneOptionsChangable);
        MenuItem findItem3 = menu.findItem(R.id.menu_send_to_voicemail);
        if (Build.VERSION.SDK_INT < 23 && !this.mContactData.isUserProfile() && this.mArePhoneOptionsChangable) {
            z = true;
        }
        findItem3.setVisible(z);
        findItem3.setTitle(this.mSendToVoicemailState ? R.string.menu_unredirect_calls_to_vm : R.string.menu_redirect_calls_to_vm);
        menu.findItem(R.id.menu_help).setVisible(HelpUtils.isHelpAndFeedbackAvailable());
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        int i = 2;
        if (itemId == R.id.menu_star) {
            if (this.mContactData != null) {
                boolean isChecked = menuItem.isChecked();
                String str = this.mReferrer;
                int i2 = this.mContactType;
                if (isChecked) {
                    i = 3;
                }
                Logger.logQuickContactEvent(str, i2, 0, i, (String) null);
                toggleStar(menuItem, isChecked);
            }
        } else if (itemId == R.id.menu_edit) {
            if (DirectoryContactUtil.isDirectoryContact(this.mContactData)) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 5, (String) null);
                Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
                intent.setType("vnd.android.cursor.item/contact");
                ArrayList<ContentValues> contentValues = this.mContactData.getContentValues();
                if (this.mContactData.getDisplayNameSource() >= 35) {
                    intent.putExtra("name", this.mContactData.getDisplayName());
                } else if (this.mContactData.getDisplayNameSource() == 30) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("data1", this.mContactData.getDisplayName());
                    contentValues2.put("mimetype", "vnd.android.cursor.item/organization");
                    contentValues.add(contentValues2);
                }
                intent.putExtra("data", contentValues);
                if (this.mContactData.getDirectoryExportSupport() == 1) {
                    intent.putExtra("android.provider.extra.ACCOUNT", new Account(this.mContactData.getDirectoryAccountName(), this.mContactData.getDirectoryAccountType()));
                    intent.putExtra("android.provider.extra.DATA_SET", this.mContactData.getRawContacts().get(0).getDataSet());
                }
                intent.putExtra("disableDeleteMenuOption", true);
                intent.setPackage(getPackageName());
                startActivityForResult(intent, 2);
            } else if (InvisibleContactUtil.isInvisibleAndAddable(this.mContactData, this)) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 5, (String) null);
                InvisibleContactUtil.addToDefaultGroup(this.mContactData, this);
            } else if (isContactEditable()) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 4, (String) null);
                editContact();
            }
        } else if (itemId == R.id.menu_join) {
            return doJoinContactAction();
        } else {
            if (itemId == R.id.menu_linked_contacts) {
                return showRawContactPickerDialog();
            }
            if (itemId == R.id.menu_delete) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 6, (String) null);
                if (isContactEditable()) {
                    deleteContact();
                }
            } else if (itemId == R.id.menu_share) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 7, (String) null);
                if (isContactShareable()) {
                    shareContact();
                }
            } else if (itemId == R.id.menu_create_contact_shortcut) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 8, (String) null);
                if (isShortcutCreatable()) {
                    createLauncherShortcutWithContact();
                }
            } else if (itemId == R.id.menu_set_ringtone) {
                doPickRingtone();
            } else if (itemId == R.id.menu_send_to_voicemail) {
                this.mSendToVoicemailState = !this.mSendToVoicemailState;
                menuItem.setTitle(this.mSendToVoicemailState ? R.string.menu_unredirect_calls_to_vm : R.string.menu_redirect_calls_to_vm);
                startService(ContactSaveService.createSetSendToVoicemail(this, this.mLookupUri, this.mSendToVoicemailState));
            } else if (itemId == R.id.menu_help) {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 9, (String) null);
            } else {
                Logger.logQuickContactEvent(this.mReferrer, this.mContactType, 0, 0, (String) null);
                return super.onOptionsItemSelected(menuItem);
            }
        }
        return true;
    }

    private boolean showRawContactPickerDialog() {
        Contact contact = this.mContactData;
        if (contact == null) {
            return false;
        }
        startActivityForResult(EditorIntents.createViewLinkedContactsIntent(this, contact.getLookupUri(), this.mHasComputedThemeColor ? new MaterialColorMapUtils.MaterialPalette(this.mColorFilterColor, this.mStatusBarColor) : null), 1);
        return true;
    }

    private boolean doJoinContactAction() {
        Contact contact = this.mContactData;
        if (contact == null) {
            return false;
        }
        this.mPreviousContactId = contact.getId();
        Intent intent = new Intent(this, ContactSelectionActivity.class);
        intent.setAction("com.android.contacts.action.JOIN_CONTACT");
        intent.putExtra("com.android.contacts.action.CONTACT_ID", this.mPreviousContactId);
        startActivityForResult(intent, 3);
        return true;
    }

    private void joinAggregate(long j) {
        startService(ContactSaveService.createJoinContactsIntent(this, this.mPreviousContactId, j, QuickContactActivity.class, "android.intent.action.VIEW"));
        showLinkProgressBar();
    }

    private void doPickRingtone() {
        Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
        intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
        intent.putExtra("android.intent.extra.ringtone.TYPE", 1);
        intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", true);
        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", EditorUiUtils.getRingtoneUriFromString(this.mCustomRingtone, CURRENT_API_VERSION));
        try {
            startActivityForResult(intent, 4);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, R.string.missing_app, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgressBar() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    private void showLinkProgressBar() {
        this.mProgressDialog.setMessage(getString(R.string.contacts_linking_progress_bar));
        this.mProgressDialog.show();
    }

    private void showUnlinkProgressBar() {
        this.mProgressDialog.setMessage(getString(R.string.contacts_unlinking_progress_bar));
        this.mProgressDialog.show();
    }

    private void maybeShowProgressDialog() {
        if (ContactSaveService.getState().isActionPending(ContactSaveService.ACTION_SPLIT_CONTACT)) {
            showUnlinkProgressBar();
        } else if (ContactSaveService.getState().isActionPending(ContactSaveService.ACTION_JOIN_CONTACTS)) {
            showLinkProgressBar();
        }
    }

    private class SaveServiceListener extends BroadcastReceiver {
        private SaveServiceListener() {
        }

        public void onReceive(Context context, Intent intent) {
            if (Log.isLoggable("QuickContact", 3)) {
                Log.d("QuickContact", "Got broadcast from save service " + intent);
            }
            if (ContactSaveService.BROADCAST_LINK_COMPLETE.equals(intent.getAction()) || ContactSaveService.BROADCAST_UNLINK_COMPLETE.equals(intent.getAction())) {
                QuickContactActivity.this.dismissProgressBar();
                if (ContactSaveService.BROADCAST_UNLINK_COMPLETE.equals(intent.getAction())) {
                    QuickContactActivity.this.finish();
                }
            }
        }
    }
}
