package com.android.settings.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.settingslib.Utils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.havoc.config.center.C1715R;
import java.io.File;

public class EditUserInfoController {
    private Dialog mEditUserInfoDialog;
    /* access modifiers changed from: private */
    public EditUserPhotoController mEditUserPhotoController;
    private Bitmap mSavedPhoto;
    /* access modifiers changed from: private */
    public UserHandle mUser;
    /* access modifiers changed from: private */
    public UserManager mUserManager;
    private boolean mWaitingForActivityResult = false;

    public interface OnContentChangedCallback {
        void onLabelChanged(CharSequence charSequence);

        void onPhotoChanged(Drawable drawable);
    }

    public void clear() {
        this.mEditUserPhotoController.removeNewUserPhotoBitmapFile();
        this.mEditUserInfoDialog = null;
        this.mSavedPhoto = null;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        String string = bundle.getString("pending_photo");
        if (string != null) {
            this.mSavedPhoto = EditUserPhotoController.loadNewUserPhotoBitmap(new File(string));
        }
        this.mWaitingForActivityResult = bundle.getBoolean("awaiting_result", false);
    }

    public void onSaveInstanceState(Bundle bundle) {
        EditUserPhotoController editUserPhotoController;
        File saveNewUserPhotoBitmap;
        Dialog dialog = this.mEditUserInfoDialog;
        if (!(dialog == null || !dialog.isShowing() || (editUserPhotoController = this.mEditUserPhotoController) == null || (saveNewUserPhotoBitmap = editUserPhotoController.saveNewUserPhotoBitmap()) == null)) {
            bundle.putString("pending_photo", saveNewUserPhotoBitmap.getPath());
        }
        boolean z = this.mWaitingForActivityResult;
        if (z) {
            bundle.putBoolean("awaiting_result", z);
        }
    }

    public void startingActivityForResult() {
        this.mWaitingForActivityResult = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mWaitingForActivityResult = false;
        if (this.mEditUserInfoDialog != null) {
            this.mEditUserPhotoController.onActivityResult(i, i2, intent);
        }
    }

    public Dialog createDialog(Fragment fragment, Drawable drawable, CharSequence charSequence, int i, OnContentChangedCallback onContentChangedCallback, UserHandle userHandle) {
        Drawable drawable2;
        FragmentActivity activity = fragment.getActivity();
        this.mUser = userHandle;
        if (this.mUserManager == null) {
            this.mUserManager = (UserManager) activity.getSystemService(UserManager.class);
        }
        View inflate = activity.getLayoutInflater().inflate(C1715R.layout.edit_user_info_dialog_content, (ViewGroup) null);
        UserInfo userInfo = this.mUserManager.getUserInfo(this.mUser.getIdentifier());
        final EditText editText = (EditText) inflate.findViewById(C1715R.C1718id.user_name);
        editText.setText(userInfo.name);
        ImageView imageView = (ImageView) inflate.findViewById(C1715R.C1718id.user_photo);
        Bitmap bitmap = this.mSavedPhoto;
        if (bitmap != null) {
            drawable2 = CircleFramedDrawable.getInstance(activity, bitmap);
        } else {
            drawable2 = drawable == null ? Utils.getUserIcon(activity, this.mUserManager, userInfo) : drawable;
        }
        imageView.setImageDrawable(drawable2);
        this.mEditUserPhotoController = createEditUserPhotoController(fragment, imageView, drawable2);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle((int) C1715R.string.profile_info_settings_title);
        builder.setView(inflate);
        builder.setCancelable(true);
        final CharSequence charSequence2 = charSequence;
        final OnContentChangedCallback onContentChangedCallback2 = onContentChangedCallback;
        final Drawable drawable3 = drawable;
        final Fragment fragment2 = fragment;
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    Editable text = editText.getText();
                    if (!TextUtils.isEmpty(text) && (charSequence2 == null || !text.toString().equals(charSequence2.toString()))) {
                        OnContentChangedCallback onContentChangedCallback = onContentChangedCallback2;
                        if (onContentChangedCallback != null) {
                            onContentChangedCallback.onLabelChanged(text.toString());
                        }
                        EditUserInfoController.this.mUserManager.setUserName(EditUserInfoController.this.mUser.getIdentifier(), text.toString());
                    }
                    Drawable newUserPhotoDrawable = EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoDrawable();
                    Bitmap newUserPhotoBitmap = EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoBitmap();
                    if (!(newUserPhotoDrawable == null || newUserPhotoBitmap == null || newUserPhotoDrawable.equals(drawable3))) {
                        OnContentChangedCallback onContentChangedCallback2 = onContentChangedCallback2;
                        if (onContentChangedCallback2 != null) {
                            onContentChangedCallback2.onPhotoChanged(newUserPhotoDrawable);
                        }
                        new AsyncTask<Void, Void, Void>() {
                            /* access modifiers changed from: protected */
                            public Void doInBackground(Void... voidArr) {
                                EditUserInfoController.this.mUserManager.setUserIcon(EditUserInfoController.this.mUser.getIdentifier(), EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoBitmap());
                                return null;
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
                    }
                    fragment2.getActivity().removeDialog(1);
                }
                EditUserInfoController.this.clear();
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditUserInfoController.this.clear();
            }
        });
        this.mEditUserInfoDialog = builder.create();
        this.mEditUserInfoDialog.getWindow().setSoftInputMode(4);
        return this.mEditUserInfoDialog;
    }

    /* access modifiers changed from: package-private */
    public EditUserPhotoController createEditUserPhotoController(Fragment fragment, ImageView imageView, Drawable drawable) {
        return new EditUserPhotoController(fragment, imageView, this.mSavedPhoto, drawable, this.mWaitingForActivityResult);
    }
}
