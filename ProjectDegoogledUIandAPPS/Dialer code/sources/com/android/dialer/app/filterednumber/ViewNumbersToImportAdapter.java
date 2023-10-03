package com.android.dialer.app.filterednumber;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.support.p002v7.appcompat.R$style;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.phonenumbercache.ContactInfoHelper;

public class ViewNumbersToImportAdapter extends NumbersAdapter {
    private ViewNumbersToImportAdapter(Context context, FragmentManager fragmentManager, ContactInfoHelper contactInfoHelper, ContactPhotoManager contactPhotoManager) {
        super(context, fragmentManager, contactInfoHelper, contactPhotoManager);
    }

    public static ViewNumbersToImportAdapter newViewNumbersToImportAdapter(Context context, FragmentManager fragmentManager) {
        return new ViewNumbersToImportAdapter(context, fragmentManager, new ContactInfoHelper(context, R$style.getCurrentCountryIso(context)), ContactPhotoManager.getInstance(context));
    }

    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        String string = cursor.getString(2);
        view.findViewById(R.id.delete_button).setVisibility(8);
        updateView(view, string, (String) null);
    }
}
