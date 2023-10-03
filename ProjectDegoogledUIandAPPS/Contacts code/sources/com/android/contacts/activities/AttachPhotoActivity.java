package com.android.contacts.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import com.android.contacts.ContactSaveService;
import com.android.contacts.ContactsActivity;
import com.android.contacts.ContactsUtils;
import com.android.contacts.R;
import com.android.contacts.editor.ContactEditorUtils;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.Contact;
import com.android.contacts.model.ContactLoader;
import com.android.contacts.model.RawContactDelta;
import com.android.contacts.model.RawContactDeltaList;
import com.android.contacts.model.RawContactModifier;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.util.ContactPhotoUtils;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.FileNotFoundException;
import java.util.List;

public class AttachPhotoActivity extends ContactsActivity {
    /* access modifiers changed from: private */
    public static final String TAG = "AttachPhotoActivity";
    private static int mPhotoDim;
    private ListenableFuture<List<AccountInfo>> mAccountsFuture;
    private Uri mContactUri;
    private ContentResolver mContentResolver;
    private Uri mCroppedPhotoUri;
    private Uri mTempPhotoUri;

    private interface Listener {
        void onContactLoaded(Contact contact);
    }

    public void onCreate(Bundle bundle) {
        Cursor query;
        Uri uri;
        super.onCreate(bundle);
        if (!RequestPermissionsActivity.startPermissionActivityIfNeeded(this)) {
            if (bundle != null) {
                String string = bundle.getString("contact_uri");
                if (string == null) {
                    uri = null;
                } else {
                    uri = Uri.parse(string);
                }
                this.mContactUri = uri;
                this.mTempPhotoUri = Uri.parse(bundle.getString("temp_photo_uri"));
                this.mCroppedPhotoUri = Uri.parse(bundle.getString("cropped_photo_uri"));
            } else {
                this.mTempPhotoUri = ContactPhotoUtils.generateTempImageUri(this);
                this.mCroppedPhotoUri = ContactPhotoUtils.generateTempCroppedImageUri(this);
                Intent intent = new Intent("android.intent.action.PICK");
                intent.setType("vnd.android.cursor.dir/contact");
                intent.setPackage(getPackageName());
                startActivityForResult(intent, 1);
            }
            this.mContentResolver = getContentResolver();
            if (mPhotoDim == 0 && (query = this.mContentResolver.query(ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI, new String[]{"display_max_dim"}, (String) null, (String[]) null, (String) null)) != null) {
                try {
                    if (query.moveToFirst()) {
                        mPhotoDim = query.getInt(0);
                    }
                } finally {
                    query.close();
                }
            }
            this.mAccountsFuture = AccountTypeManager.getInstance(this).filterAccountsAsync(AccountTypeManager.writableFilter());
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Uri uri = this.mContactUri;
        if (uri != null) {
            bundle.putString("contact_uri", uri.toString());
        }
        Uri uri2 = this.mTempPhotoUri;
        if (uri2 != null) {
            bundle.putString("temp_photo_uri", uri2.toString());
        }
        Uri uri3 = this.mCroppedPhotoUri;
        if (uri3 != null) {
            bundle.putString("cropped_photo_uri", uri3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        AccountWithDataSet accountWithDataSet;
        if (i == 3) {
            if (i2 != -1) {
                Log.w(TAG, "account selector was not successful");
                finish();
            } else if (intent == null || (accountWithDataSet = (AccountWithDataSet) intent.getParcelableExtra("android.provider.extra.ACCOUNT")) == null) {
                createNewRawContact((AccountWithDataSet) null);
            } else {
                createNewRawContact(accountWithDataSet);
            }
        } else if (i == 1) {
            if (i2 != -1) {
                finish();
                return;
            }
            Intent intent2 = getIntent();
            if (!ContactPhotoUtils.savePhotoFromUriToUri(this, intent2.getData(), this.mTempPhotoUri, false)) {
                finish();
                return;
            }
            Intent intent3 = new Intent("com.android.camera.action.CROP", this.mTempPhotoUri);
            if (intent2.getStringExtra("mimeType") != null) {
                intent3.setDataAndType(this.mTempPhotoUri, intent2.getStringExtra("mimeType"));
            }
            ContactPhotoUtils.addPhotoPickerExtras(intent3, this.mCroppedPhotoUri);
            int i3 = mPhotoDim;
            if (i3 == 0) {
                i3 = 720;
            }
            ContactPhotoUtils.addCropExtras(intent3, i3);
            if (!hasIntentHandler(intent3)) {
                this.mCroppedPhotoUri = this.mTempPhotoUri;
                this.mContactUri = intent.getData();
                loadContact(this.mContactUri, new Listener() {
                    public void onContactLoaded(Contact contact) {
                        AttachPhotoActivity.this.saveContact(contact);
                    }
                });
                return;
            }
            try {
                startActivityForResult(intent3, 2);
                this.mContactUri = intent.getData();
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, R.string.missing_app, 0).show();
            }
        } else if (i == 2) {
            getContentResolver().delete(this.mTempPhotoUri, (String) null, (String[]) null);
            if (i2 != -1) {
                finish();
            } else {
                loadContact(this.mContactUri, new Listener() {
                    public void onContactLoaded(Contact contact) {
                        AttachPhotoActivity.this.saveContact(contact);
                    }
                });
            }
        }
    }

    private boolean hasIntentHandler(Intent intent) {
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    private void loadContact(Uri uri, final Listener listener) {
        ContactLoader contactLoader = new ContactLoader(this, uri, true);
        contactLoader.registerListener(0, new Loader.OnLoadCompleteListener<Contact>() {
            public void onLoadComplete(Loader<Contact> loader, Contact contact) {
                try {
                    loader.reset();
                } catch (RuntimeException e) {
                    Log.e(AttachPhotoActivity.TAG, "Error resetting loader", e);
                }
                listener.onContactLoaded(contact);
            }
        });
        contactLoader.startLoading();
    }

    /* access modifiers changed from: private */
    public void saveContact(Contact contact) {
        if (contact.getRawContacts() == null) {
            Log.w(TAG, "No raw contacts found for contact");
            finish();
            return;
        }
        RawContactDeltaList createRawContactDeltaList = contact.createRawContactDeltaList();
        RawContactDelta firstWritableRawContact = createRawContactDeltaList.getFirstWritableRawContact(this);
        if (firstWritableRawContact == null) {
            selectAccountAndCreateContact();
        } else {
            saveToContact(contact, createRawContactDeltaList, firstWritableRawContact);
        }
    }

    /* access modifiers changed from: private */
    public void saveToContact(Contact contact, RawContactDeltaList rawContactDeltaList, RawContactDelta rawContactDelta) {
        int thumbnailSize = ContactsUtils.getThumbnailSize(this);
        try {
            Bitmap bitmapFromUri = ContactPhotoUtils.getBitmapFromUri(this, this.mCroppedPhotoUri);
            if (bitmapFromUri == null) {
                Log.w(TAG, "Could not decode bitmap");
                finish();
                return;
            }
            byte[] compressBitmap = ContactPhotoUtils.compressBitmap(Bitmap.createScaledBitmap(bitmapFromUri, thumbnailSize, thumbnailSize, false));
            if (compressBitmap == null) {
                Log.w(TAG, "could not create scaled and compressed Bitmap");
                finish();
                return;
            }
            ValuesDelta ensureKindExists = RawContactModifier.ensureKindExists(rawContactDelta, rawContactDelta.getRawContactAccountType(this), "vnd.android.cursor.item/photo");
            if (ensureKindExists == null) {
                Log.w(TAG, "cannot attach photo to this account type");
                finish();
                return;
            }
            ensureKindExists.setPhoto(compressBitmap);
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "all prerequisites met, about to save photo to contact");
            }
            ContactSaveService.startService(this, ContactSaveService.createSaveContactIntent(this, rawContactDeltaList, "", 0, contact.isUserProfile(), (Class<? extends Activity>) null, (String) null, rawContactDelta.getRawContactId() != null ? rawContactDelta.getRawContactId().longValue() : -1, this.mCroppedPhotoUri));
            finish();
        } catch (FileNotFoundException unused) {
            Log.w(TAG, "Could not find bitmap");
            finish();
        }
    }

    private void selectAccountAndCreateContact() {
        Preconditions.checkNotNull(this.mAccountsFuture, "Accounts future must be initialized first");
        ContactEditorUtils create = ContactEditorUtils.create(this);
        List<AccountWithDataSet> extractAccounts = AccountInfo.extractAccounts((List) Futures.getUnchecked(this.mAccountsFuture));
        if (create.shouldShowAccountChangedNotification(extractAccounts)) {
            startActivityForResult(new Intent(this, ContactEditorAccountsChangedActivity.class).addFlags(603979776), 3);
        } else {
            createNewRawContact(create.getOnlyOrDefaultAccount(extractAccounts));
        }
    }

    private void createNewRawContact(final AccountWithDataSet accountWithDataSet) {
        loadContact(this.mContactUri, new Listener() {
            public void onContactLoaded(Contact contact) {
                RawContactDeltaList createRawContactDeltaList = contact.createRawContactDeltaList();
                ContentValues contentValues = new ContentValues();
                AccountWithDataSet accountWithDataSet = accountWithDataSet;
                String str = null;
                contentValues.put("account_type", accountWithDataSet != null ? accountWithDataSet.type : null);
                AccountWithDataSet accountWithDataSet2 = accountWithDataSet;
                contentValues.put("account_name", accountWithDataSet2 != null ? accountWithDataSet2.name : null);
                AccountWithDataSet accountWithDataSet3 = accountWithDataSet;
                if (accountWithDataSet3 != null) {
                    str = accountWithDataSet3.dataSet;
                }
                contentValues.put("data_set", str);
                RawContactDelta rawContactDelta = new RawContactDelta(ValuesDelta.fromAfter(contentValues));
                createRawContactDeltaList.add(rawContactDelta);
                AttachPhotoActivity.this.saveToContact(contact, createRawContactDeltaList, rawContactDelta);
            }
        });
    }
}
