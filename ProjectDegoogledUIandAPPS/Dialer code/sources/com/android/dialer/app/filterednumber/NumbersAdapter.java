package com.android.dialer.app.filterednumber;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;

public class NumbersAdapter extends SimpleCursorAdapter {
    private final BidiFormatter bidiFormatter = BidiFormatter.getInstance();
    private final ContactInfoHelper contactInfoHelper;
    private final ContactPhotoManager contactPhotoManager;
    private final Context context;
    private final FragmentManager fragmentManager;

    public NumbersAdapter(Context context2, FragmentManager fragmentManager2, ContactInfoHelper contactInfoHelper2, ContactPhotoManager contactPhotoManager2) {
        super(context2, R.layout.blocked_number_item, (Cursor) null, new String[0], new int[0], 0);
        this.context = context2;
        this.fragmentManager = fragmentManager2;
        this.contactInfoHelper = contactInfoHelper2;
        this.contactPhotoManager = contactPhotoManager2;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public void updateView(View view, String str, String str2) {
        CharSequence charSequence;
        String str3;
        TextView textView = (TextView) view.findViewById(R.id.caller_name);
        TextView textView2 = (TextView) view.findViewById(R.id.caller_number);
        QuickContactBadge quickContactBadge = (QuickContactBadge) view.findViewById(R.id.quick_contact_photo);
        String str4 = null;
        quickContactBadge.setOverlay((Drawable) null);
        quickContactBadge.setPrioritizedMimeType("vnd.android.cursor.item/phone_v2");
        ContactInfo lookupNumber = this.contactInfoHelper.lookupNumber(str, str2, -1);
        if (lookupNumber == null) {
            lookupNumber = new ContactInfo();
            lookupNumber.number = str;
        }
        if (!TextUtils.isEmpty(lookupNumber.name)) {
            charSequence = ContactsContract.CommonDataKinds.Phone.getTypeLabel(this.context.getResources(), lookupNumber.type, lookupNumber.label);
        } else {
            charSequence = PhoneNumberHelper.getGeoDescription(this.context, lookupNumber.number, str2);
        }
        if (!TextUtils.isEmpty(lookupNumber.formattedNumber)) {
            str3 = lookupNumber.formattedNumber;
        } else {
            str3 = !TextUtils.isEmpty(lookupNumber.number) ? lookupNumber.number : "";
        }
        String unicodeWrap = this.bidiFormatter.unicodeWrap(str3, TextDirectionHeuristics.LTR);
        if (!TextUtils.isEmpty(lookupNumber.name)) {
            str3 = lookupNumber.name;
            textView.setText(str3);
            textView2.setText(charSequence + " " + unicodeWrap);
        } else {
            textView.setText(unicodeWrap);
            if (!TextUtils.isEmpty(charSequence)) {
                textView2.setText(charSequence);
                textView2.setVisibility(0);
            } else {
                textView2.setVisibility(8);
            }
        }
        Uri uri = lookupNumber.lookupUri;
        if (uri != null) {
            str4 = R$style.getLookupKeyFromUri(uri);
        }
        this.contactInfoHelper.isBusiness(lookupNumber.sourceType);
        ContactPhotoManager.DefaultImageRequest defaultImageRequest = new ContactPhotoManager.DefaultImageRequest(str3, str4, 1, true);
        quickContactBadge.assignContactUri(lookupNumber.lookupUri);
        quickContactBadge.setContentDescription(this.context.getResources().getString(R.string.description_contact_details, new Object[]{str3}));
        this.contactPhotoManager.loadDirectoryPhoto(quickContactBadge, lookupNumber.photoUri, false, true, defaultImageRequest);
    }
}
