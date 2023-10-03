package com.android.contacts.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.android.contacts.AppCompatContactsActivity;
import com.android.contacts.ContactSaveService;
import com.android.contacts.R;
import com.android.contacts.editor.EditorIntents;
import com.android.contacts.editor.PickRawContactDialogFragment;
import com.android.contacts.editor.PickRawContactLoader;
import com.android.contacts.editor.SplitContactConfirmationDialogFragment;
import com.android.contacts.logging.Logger;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.quickcontact.QuickContactActivity;
import com.android.contacts.util.ImplicitIntentsUtil;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contactsbind.FeedbackHelper;
import java.lang.reflect.Array;

public class ContactEditorSpringBoardActivity extends AppCompatContactsActivity implements PickRawContactDialogFragment.PickRawContactListener, SplitContactConfirmationDialogFragment.Listener {
    private boolean mHasWritableAccount;
    private MaterialColorMapUtils.MaterialPalette mMaterialPalette;
    protected final LoaderManager.LoaderCallbacks<PickRawContactLoader.RawContactsMetadata> mRawContactLoaderListener = new LoaderManager.LoaderCallbacks<PickRawContactLoader.RawContactsMetadata>() {
        public void onLoaderReset(Loader<PickRawContactLoader.RawContactsMetadata> loader) {
        }

        public Loader<PickRawContactLoader.RawContactsMetadata> onCreateLoader(int i, Bundle bundle) {
            ContactEditorSpringBoardActivity contactEditorSpringBoardActivity = ContactEditorSpringBoardActivity.this;
            return new PickRawContactLoader(contactEditorSpringBoardActivity, contactEditorSpringBoardActivity.mUri);
        }

        public void onLoadFinished(Loader<PickRawContactLoader.RawContactsMetadata> loader, PickRawContactLoader.RawContactsMetadata rawContactsMetadata) {
            if (rawContactsMetadata == null) {
                ContactEditorSpringBoardActivity.this.toastErrorAndFinish();
                return;
            }
            PickRawContactLoader.RawContactsMetadata unused = ContactEditorSpringBoardActivity.this.mResult = rawContactsMetadata;
            ContactEditorSpringBoardActivity.this.onLoad();
        }
    };
    /* access modifiers changed from: private */
    public PickRawContactLoader.RawContactsMetadata mResult;
    private boolean mShowReadOnly;
    /* access modifiers changed from: private */
    public Uri mUri;
    private int mWritableAccountPosition;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            Intent intent = getIntent();
            if (!"android.intent.action.EDIT".equals(intent.getAction())) {
                finish();
                return;
            }
            if (intent.hasExtra("material_palette_primary_color") && intent.hasExtra("material_palette_secondary_color")) {
                this.mMaterialPalette = new MaterialColorMapUtils.MaterialPalette(intent.getIntExtra("material_palette_primary_color", -1), intent.getIntExtra("material_palette_secondary_color", -1));
            }
            this.mShowReadOnly = intent.getBooleanExtra("showReadOnly", false);
            this.mUri = intent.getData();
            String authority = this.mUri.getAuthority();
            String type = getContentResolver().getType(this.mUri);
            if ("com.android.contacts".equals(authority) && "vnd.android.cursor.item/raw_contact".equals(type)) {
                Logger.logEditorEvent(1, 0);
                startEditorAndForwardExtras(getIntentForRawContact(ContentUris.parseId(this.mUri)));
            } else if ("contacts".equals(authority)) {
                FeedbackHelper.sendFeedback(this, "EditorSpringBoard", "Legacy Uri was passed to editor.", new IllegalArgumentException());
                toastErrorAndFinish();
            } else {
                getLoaderManager().initLoader(1, (Bundle) null, this.mRawContactLoaderListener);
            }
        }
    }

    public void onPickRawContact(long j) {
        startEditorAndForwardExtras(getIntentForRawContact(j));
    }

    /* access modifiers changed from: private */
    public void onLoad() {
        maybeTrimReadOnly();
        setHasWritableAccount();
        if (this.mShowReadOnly || (this.mResult.rawContacts.size() > 1 && this.mHasWritableAccount)) {
            showDialog();
        } else {
            loadEditor();
        }
    }

    private void maybeTrimReadOnly() {
        PickRawContactLoader.RawContactsMetadata rawContactsMetadata = this.mResult;
        boolean z = this.mShowReadOnly;
        rawContactsMetadata.showReadOnly = z;
        if (!z) {
            rawContactsMetadata.trimReadOnly(AccountTypeManager.getInstance(this));
        }
    }

    private void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        SplitContactConfirmationDialogFragment splitContactConfirmationDialogFragment = (SplitContactConfirmationDialogFragment) fragmentManager.findFragmentByTag("SplitConfirmation");
        if (splitContactConfirmationDialogFragment != null && splitContactConfirmationDialogFragment.isAdded()) {
            fragmentManager.beginTransaction().show(splitContactConfirmationDialogFragment).commitAllowingStateLoss();
        } else if (((PickRawContactDialogFragment) fragmentManager.findFragmentByTag("rawContactsDialog")) == null) {
            PickRawContactDialogFragment instance = PickRawContactDialogFragment.getInstance(this.mResult);
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(instance, "rawContactsDialog");
            beginTransaction.commitAllowingStateLoss();
        }
    }

    private void loadEditor() {
        Intent intent;
        Logger.logEditorEvent(1, 0);
        if (this.mHasWritableAccount) {
            intent = getIntentForRawContact(this.mResult.rawContacts.get(this.mWritableAccountPosition).f10id);
        } else {
            intent = EditorIntents.createEditContactIntent(this, this.mUri, this.mMaterialPalette, -1);
            intent.setClass(this, ContactEditorActivity.class);
        }
        startEditorAndForwardExtras(intent);
    }

    private void setHasWritableAccount() {
        this.mWritableAccountPosition = this.mResult.getIndexOfFirstWritableAccount(AccountTypeManager.getInstance(this));
        this.mHasWritableAccount = this.mWritableAccountPosition != -1;
    }

    private Intent getIntentForRawContact(long j) {
        Intent createEditContactIntentForRawContact = EditorIntents.createEditContactIntentForRawContact(this, this.mUri, j, this.mMaterialPalette);
        createEditContactIntentForRawContact.setFlags(33554432);
        return createEditContactIntentForRawContact;
    }

    private void startEditorAndForwardExtras(Intent intent) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        ImplicitIntentsUtil.startActivityInApp(this, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void toastErrorAndFinish() {
        Toast.makeText(this, R.string.editor_failed_to_load, 0).show();
        setResult(0, (Intent) null);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            finish();
        }
        if (intent != null) {
            startService(ContactSaveService.createJoinContactsIntent(this, this.mResult.contactId, ContentUris.parseId(intent.getData()), QuickContactActivity.class, "android.intent.action.VIEW"));
            finish();
        }
    }

    public void onSplitContactConfirmed(boolean z) {
        startService(ContactSaveService.createHardSplitContactIntent(this, getRawContactIds()));
        finish();
    }

    public void onSplitContactCanceled() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("rawContactsMetadata", this.mResult);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mResult = (PickRawContactLoader.RawContactsMetadata) bundle.getParcelable("rawContactsMetadata");
    }

    private long[][] getRawContactIds() {
        long[][] jArr = (long[][]) Array.newInstance(long.class, new int[]{this.mResult.rawContacts.size(), 1});
        for (int i = 0; i < this.mResult.rawContacts.size(); i++) {
            jArr[i][0] = this.mResult.rawContacts.get(i).f10id;
        }
        return jArr;
    }
}
