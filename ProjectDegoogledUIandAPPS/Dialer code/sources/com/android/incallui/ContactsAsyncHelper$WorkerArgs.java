package com.android.incallui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

final class ContactsAsyncHelper$WorkerArgs {
    public Context context;
    public Object cookie;
    public Uri displayPhotoUri;
    public ContactsAsyncHelper$OnImageLoadCompleteListener listener;
    public Drawable photo;
    public Bitmap photoIcon;
    public int token;

    private ContactsAsyncHelper$WorkerArgs() {
    }

    /* synthetic */ ContactsAsyncHelper$WorkerArgs(ContactsAsyncHelper$1 contactsAsyncHelper$1) {
    }
}
