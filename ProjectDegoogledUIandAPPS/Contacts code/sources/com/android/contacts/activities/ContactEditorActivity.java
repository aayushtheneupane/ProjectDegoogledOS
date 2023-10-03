package com.android.contacts.activities;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.android.contacts.AppCompatContactsActivity;
import com.android.contacts.ContactSaveService;
import com.android.contacts.DynamicShortcuts;
import com.android.contacts.R;
import com.android.contacts.detail.PhotoSelectionHandler;
import com.android.contacts.editor.ContactEditorFragment;
import com.android.contacts.editor.EditorIntents;
import com.android.contacts.editor.PhotoSourceDialogFragment;
import com.android.contacts.interactions.ContactDeletionInteraction;
import com.android.contacts.model.RawContactDeltaList;
import com.android.contacts.util.DialogManager;
import com.android.contacts.util.ImplicitIntentsUtil;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ContactEditorActivity extends AppCompatContactsActivity implements PhotoSourceDialogFragment.Listener, DialogManager.DialogShowingViewActivity {
    private int mActionBarTitleResId;
    private DialogManager mDialogManager = new DialogManager(this);
    /* access modifiers changed from: private */
    public boolean mFinishActivityOnSaveCompleted;
    private ContactEditor mFragment;
    private final ContactEditorFragment.Listener mFragmentListener = new ContactEditorFragment.Listener() {
        public void onDeleteRequested(Uri uri) {
            ContactDeletionInteraction.start(ContactEditorActivity.this, uri, true);
        }

        public void onReverted() {
            ContactEditorActivity.this.finish();
        }

        public void onSaveFinished(Intent intent) {
            if (ContactEditorActivity.this.mFinishActivityOnSaveCompleted) {
                ContactEditorActivity.this.setResult(intent == null ? 0 : -1, intent);
            } else if (intent != null) {
                ImplicitIntentsUtil.startActivityInApp(ContactEditorActivity.this, intent);
            }
            ContactEditorActivity.this.finish();
        }

        public void onContactSplit(Uri uri) {
            ContactEditorActivity.this.setResult(2, (Intent) null);
            ContactEditorActivity.this.finish();
        }

        public void onContactNotFound() {
            ContactEditorActivity.this.finish();
        }

        public void onEditOtherRawContactRequested(Uri uri, long j, ArrayList<ContentValues> arrayList) {
            ImplicitIntentsUtil.startActivityInApp(ContactEditorActivity.this, EditorIntents.createEditOtherRawContactIntent(ContactEditorActivity.this, uri, j, arrayList));
            ContactEditorActivity.this.finish();
        }
    };
    private int mPhotoMode;
    /* access modifiers changed from: private */
    public EditorPhotoSelectionHandler mPhotoSelectionHandler;
    /* access modifiers changed from: private */
    public Uri mPhotoUri;
    private Toolbar mToolbar;

    public interface ContactEditor {
        void load(String str, Uri uri, Bundle bundle);

        void onJoinCompleted(Uri uri);

        void onSaveCompleted(boolean z, int i, boolean z2, Uri uri, Long l);

        boolean revert();

        void setIntentExtras(Bundle bundle);

        void setListener(ContactEditorFragment.Listener listener);
    }

    private final class EditorPhotoSelectionHandler extends PhotoSelectionHandler {
        private final EditorPhotoActionListener mPhotoActionListener = new EditorPhotoActionListener();

        private final class EditorPhotoActionListener extends PhotoSelectionHandler.PhotoActionListener {
            public void onPhotoSelectionDismissed() {
            }

            private EditorPhotoActionListener() {
                super();
            }

            public void onRemovePictureChosen() {
                ContactEditorActivity.this.getEditorFragment().removePhoto();
            }

            public void onPhotoSelected(Uri uri) throws FileNotFoundException {
                Uri unused = ContactEditorActivity.this.mPhotoUri = uri;
                ContactEditorActivity.this.getEditorFragment().updatePhoto(uri);
                EditorPhotoSelectionHandler unused2 = ContactEditorActivity.this.mPhotoSelectionHandler = null;
            }

            public Uri getCurrentPhotoUri() {
                return ContactEditorActivity.this.mPhotoUri;
            }
        }

        public EditorPhotoSelectionHandler(int i) {
            super(ContactEditorActivity.this, (View) null, i, false, new RawContactDeltaList());
        }

        public PhotoSelectionHandler.PhotoActionListener getListener() {
            return this.mPhotoActionListener;
        }

        /* access modifiers changed from: protected */
        public void startPhotoActivity(Intent intent, int i, Uri uri) {
            Uri unused = ContactEditorActivity.this.mPhotoUri = uri;
            ContactEditorActivity.this.startActivityForResult(intent, i);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RequestPermissionsActivity.startPermissionActivityIfNeeded(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        intent.setComponent(new ComponentName(this, ContactEditorActivity.class));
        this.mFinishActivityOnSaveCompleted = intent.getBooleanExtra("finishActivityOnSaveCompleted", false);
        if ("joinCompleted".equals(action)) {
            finish();
        } else if ("saveCompleted".equals(action)) {
            finish();
        } else {
            setContentView((int) R.layout.contact_editor_activity);
            this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(this.mToolbar);
            if ("android.intent.action.EDIT".equals(action)) {
                this.mActionBarTitleResId = R.string.contact_editor_title_existing_contact;
            } else {
                this.mActionBarTitleResId = R.string.contact_editor_title_new_contact;
            }
            this.mToolbar.setTitle(this.mActionBarTitleResId);
            setTitle(this.mActionBarTitleResId);
            this.mFragment = (ContactEditor) getFragmentManager().findFragmentById(R.id.contact_editor_fragment);
            if (bundle != null) {
                this.mPhotoMode = bundle.getInt("photo_mode");
                this.mActionBarTitleResId = bundle.getInt("action_bar_title");
                this.mPhotoUri = Uri.parse(bundle.getString("photo_uri"));
                this.mToolbar.setTitle(this.mActionBarTitleResId);
            }
            this.mFragment.setListener(this.mFragmentListener);
            this.mFragment.load(action, "android.intent.action.EDIT".equals(action) ? getIntent().getData() : null, getIntent().getExtras());
            if ("android.intent.action.INSERT".equals(action)) {
                DynamicShortcuts.reportShortcutUsed(this, DynamicShortcuts.SHORTCUT_ADD_CONTACT);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mFragment != null) {
            String action = intent.getAction();
            if ("android.intent.action.EDIT".equals(action)) {
                this.mFragment.setIntentExtras(intent.getExtras());
            } else if ("saveCompleted".equals(action)) {
                this.mFragment.onSaveCompleted(true, intent.getIntExtra(ContactSaveService.EXTRA_SAVE_MODE, 0), intent.getBooleanExtra(ContactSaveService.EXTRA_SAVE_SUCCEEDED, false), intent.getData(), Long.valueOf(intent.getLongExtra("joinContactId", -1)));
            } else if ("joinCompleted".equals(action)) {
                this.mFragment.onJoinCompleted(intent.getData());
            }
        }
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (DialogManager.isManagedId(i)) {
            return this.mDialogManager.onCreateDialog(i, bundle);
        }
        Log.w("ContactEditorActivity", "Unknown dialog requested, id: " + i + ", args: " + bundle);
        return null;
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("photo_mode", this.mPhotoMode);
        bundle.putInt("action_bar_title", this.mActionBarTitleResId);
        Uri uri = this.mPhotoUri;
        if (uri == null) {
            uri = Uri.EMPTY;
        }
        bundle.putString("photo_uri", uri.toString());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mPhotoSelectionHandler == null) {
            this.mPhotoSelectionHandler = (EditorPhotoSelectionHandler) getPhotoSelectionHandler();
        }
        if (!this.mPhotoSelectionHandler.handlePhotoActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        ContactEditor contactEditor = this.mFragment;
        if (contactEditor != null) {
            contactEditor.revert();
        }
    }

    public void changePhoto(int i) {
        this.mPhotoMode = i;
        if (isSafeToCommitTransactions()) {
            PhotoSourceDialogFragment.show(this, this.mPhotoMode);
        }
    }

    public Toolbar getToolbar() {
        return this.mToolbar;
    }

    public void onRemovePictureChosen() {
        getPhotoSelectionHandler().getListener().onRemovePictureChosen();
    }

    public void onTakePhotoChosen() {
        getPhotoSelectionHandler().getListener().onTakePhotoChosen();
    }

    public void onPickFromGalleryChosen() {
        getPhotoSelectionHandler().getListener().onPickFromGalleryChosen();
    }

    private PhotoSelectionHandler getPhotoSelectionHandler() {
        if (this.mPhotoSelectionHandler == null) {
            this.mPhotoSelectionHandler = new EditorPhotoSelectionHandler(this.mPhotoMode);
        }
        return this.mPhotoSelectionHandler;
    }

    /* access modifiers changed from: private */
    public ContactEditorFragment getEditorFragment() {
        return (ContactEditorFragment) this.mFragment;
    }
}
