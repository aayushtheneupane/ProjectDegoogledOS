package com.android.contacts.editor;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import com.android.contacts.ContactPhotoManager;
import com.android.contacts.ContactsUtils;
import com.android.contacts.R;
import com.android.contacts.model.ValuesDelta;
import com.android.contacts.model.account.AccountDisplayInfo;
import com.android.contacts.model.account.AccountInfo;
import com.android.contacts.util.ContactPhotoUtils;
import com.android.contacts.util.MaterialColorMapUtils;
import com.android.contacts.widget.QuickContactImageView;
import com.google.common.collect.Maps;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class EditorUiUtils {
    private static final HashMap<String, Integer> mimetypeLayoutMap = Maps.newHashMap();

    private static boolean isNewerThanM(int i) {
        return i > 23;
    }

    static {
        mimetypeLayoutMap.put("vnd.android.cursor.item/name", Integer.valueOf(R.layout.structured_name_editor_view));
        mimetypeLayoutMap.put("vnd.android.cursor.item/group_membership", -1);
        mimetypeLayoutMap.put("vnd.android.cursor.item/photo", -1);
        mimetypeLayoutMap.put("vnd.android.cursor.item/contact_event", Integer.valueOf(R.layout.event_field_editor_view));
    }

    public static int getLayoutResourceId(String str) {
        Integer num = mimetypeLayoutMap.get(str);
        if (num == null) {
            return R.layout.text_fields_editor_view;
        }
        return num.intValue();
    }

    public static String getAccountHeaderLabelForMyProfile(Context context, AccountInfo accountInfo) {
        if (accountInfo.isDeviceAccount()) {
            return context.getString(R.string.local_profile_title);
        }
        return context.getString(R.string.external_profile_title, new Object[]{accountInfo.getTypeLabel()});
    }

    public static String getAccountTypeHeaderLabel(Context context, AccountDisplayInfo accountDisplayInfo) {
        if (accountDisplayInfo.isDeviceAccount()) {
            return accountDisplayInfo.getTypeLabel().toString();
        }
        if (accountDisplayInfo.isGoogleAccount()) {
            return context.getString(R.string.google_account_type_format, new Object[]{accountDisplayInfo.getTypeLabel()});
        }
        return context.getString(R.string.account_type_format, new Object[]{accountDisplayInfo.getTypeLabel()});
    }

    public static String getAccountInfoContentDescription(CharSequence charSequence, CharSequence charSequence2) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(charSequence2)) {
            sb.append(charSequence2);
            sb.append(10);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            sb.append(charSequence);
        }
        return sb.toString();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.drawable.Drawable getMimeTypeDrawable(android.content.Context r1, java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            switch(r0) {
                case -1569536764: goto L_0x0089;
                case -1328682538: goto L_0x007f;
                case -1079224304: goto L_0x0075;
                case -1079210633: goto L_0x006a;
                case -601229436: goto L_0x0060;
                case 3430506: goto L_0x0056;
                case 456415478: goto L_0x004c;
                case 684173810: goto L_0x0042;
                case 689862072: goto L_0x0037;
                case 905843021: goto L_0x002c;
                case 950831081: goto L_0x0021;
                case 1409846529: goto L_0x0015;
                case 1464725403: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0093
        L_0x0009:
            java.lang.String r0 = "vnd.android.cursor.item/group_membership"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 9
            goto L_0x0094
        L_0x0015:
            java.lang.String r0 = "vnd.android.cursor.item/relation"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 12
            goto L_0x0094
        L_0x0021:
            java.lang.String r0 = "vnd.android.cursor.item/im"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 4
            goto L_0x0094
        L_0x002c:
            java.lang.String r0 = "vnd.android.cursor.item/photo"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 8
            goto L_0x0094
        L_0x0037:
            java.lang.String r0 = "vnd.android.cursor.item/organization"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 10
            goto L_0x0094
        L_0x0042:
            java.lang.String r0 = "vnd.android.cursor.item/phone_v2"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 3
            goto L_0x0094
        L_0x004c:
            java.lang.String r0 = "vnd.android.cursor.item/website"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 7
            goto L_0x0094
        L_0x0056:
            java.lang.String r0 = "vnd.android.cursor.item/sip_address"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 2
            goto L_0x0094
        L_0x0060:
            java.lang.String r0 = "vnd.android.cursor.item/postal-address_v2"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 1
            goto L_0x0094
        L_0x006a:
            java.lang.String r0 = "vnd.android.cursor.item/note"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 11
            goto L_0x0094
        L_0x0075:
            java.lang.String r0 = "vnd.android.cursor.item/name"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 0
            goto L_0x0094
        L_0x007f:
            java.lang.String r0 = "vnd.android.cursor.item/contact_event"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 5
            goto L_0x0094
        L_0x0089:
            java.lang.String r0 = "vnd.android.cursor.item/email_v2"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0093
            r2 = 6
            goto L_0x0094
        L_0x0093:
            r2 = -1
        L_0x0094:
            r0 = 0
            switch(r2) {
                case 0: goto L_0x0129;
                case 1: goto L_0x011d;
                case 2: goto L_0x0111;
                case 3: goto L_0x0105;
                case 4: goto L_0x00f9;
                case 5: goto L_0x00ed;
                case 6: goto L_0x00e1;
                case 7: goto L_0x00d5;
                case 8: goto L_0x00c9;
                case 9: goto L_0x00bd;
                case 10: goto L_0x00b1;
                case 11: goto L_0x00a5;
                case 12: goto L_0x0099;
                default: goto L_0x0098;
            }
        L_0x0098:
            return r0
        L_0x0099:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230890(0x7f0800aa, float:1.8077846E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00a5:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230909(0x7f0800bd, float:1.8077884E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00b1:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230886(0x7f0800a6, float:1.8077837E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00bd:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230910(0x7f0800be, float:1.8077886E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00c9:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230888(0x7f0800a8, float:1.8077841E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00d5:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230921(0x7f0800c9, float:1.8077908E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00e1:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230900(0x7f0800b4, float:1.8077866E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00ed:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230902(0x7f0800b6, float:1.807787E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x00f9:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230913(0x7f0800c1, float:1.8077892E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x0105:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230919(0x7f0800c7, float:1.8077904E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x0111:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230895(0x7f0800af, float:1.8077856E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x011d:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230920(0x7f0800c8, float:1.8077906E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        L_0x0129:
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131230918(0x7f0800c6, float:1.8077902E38)
            android.graphics.drawable.Drawable r1 = androidx.core.content.res.ResourcesCompat.getDrawable(r1, r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.editor.EditorUiUtils.getMimeTypeDrawable(android.content.Context, java.lang.String):android.graphics.drawable.Drawable");
    }

    public static String getRingtoneStringFromUri(Uri uri, int i) {
        if (isNewerThanM(i)) {
            if (uri == null) {
                return "";
            }
            if (RingtoneManager.isDefault(uri)) {
                return null;
            }
        }
        if (uri == null || RingtoneManager.isDefault(uri)) {
            return null;
        }
        return uri.toString();
    }

    public static Uri getRingtoneUriFromString(String str, int i) {
        if (str == null) {
            return RingtoneManager.getDefaultUri(1);
        }
        if (!isNewerThanM(i) || !TextUtils.isEmpty(str)) {
            return Uri.parse(str);
        }
        return null;
    }

    public static Long getPhotoFileId(ValuesDelta valuesDelta) {
        if (valuesDelta == null) {
            return null;
        }
        if (valuesDelta.getAfter() == null || valuesDelta.getAfter().get("data15") == null) {
            return valuesDelta.getAsLong("data14");
        }
        return null;
    }

    static void loadPhoto(ContactPhotoManager contactPhotoManager, ImageView imageView, Uri uri) {
        ContactPhotoManager contactPhotoManager2 = contactPhotoManager;
        ImageView imageView2 = imageView;
        Uri uri2 = uri;
        contactPhotoManager2.loadPhoto(imageView2, uri2, imageView.getWidth(), false, false, (ContactPhotoManager.DefaultImageRequest) null, new ContactPhotoManager.DefaultImageProvider() {
            public void applyDefaultImage(ImageView imageView, int i, boolean z, ContactPhotoManager.DefaultImageRequest defaultImageRequest) {
            }
        });
    }

    public static Bitmap getPhotoBitmap(ValuesDelta valuesDelta) {
        byte[] asByteArray;
        if (valuesDelta == null || (asByteArray = valuesDelta.getAsByteArray("data15")) == null) {
            return null;
        }
        return BitmapFactory.decodeByteArray(asByteArray, 0, asByteArray.length);
    }

    public static void setDefaultPhoto(ImageView imageView, Resources resources, MaterialColorMapUtils.MaterialPalette materialPalette) {
        int i;
        imageView.setImageDrawable(ContactPhotoManager.getDefaultAvatarDrawableForContact(resources, false, (ContactPhotoManager.DefaultImageRequest) null));
        if (imageView instanceof QuickContactImageView) {
            QuickContactImageView quickContactImageView = (QuickContactImageView) imageView;
            if (materialPalette == null) {
                i = MaterialColorMapUtils.getDefaultPrimaryAndSecondaryColors(resources).mPrimaryColor;
            } else {
                i = materialPalette.mPrimaryColor;
            }
            quickContactImageView.setTint(i);
        }
    }

    public static byte[] getCompressedThumbnailBitmapBytes(Context context, Uri uri) throws FileNotFoundException {
        Bitmap bitmapFromUri = ContactPhotoUtils.getBitmapFromUri(context, uri);
        int thumbnailSize = ContactsUtils.getThumbnailSize(context);
        return ContactPhotoUtils.compressBitmap(Bitmap.createScaledBitmap(bitmapFromUri, thumbnailSize, thumbnailSize, false));
    }
}
