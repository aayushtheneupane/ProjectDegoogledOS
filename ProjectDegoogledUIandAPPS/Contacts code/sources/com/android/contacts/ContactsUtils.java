package com.android.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Pair;
import com.android.contacts.compat.ContactsCompat;
import com.android.contacts.compat.DirectoryCompat;
import com.android.contacts.model.dataitem.ImDataItem;

public class ContactsUtils {
    private static final int DEFAULT_THUMBNAIL_SIZE = 96;
    public static final boolean FLAG_N_FEATURE = (Build.VERSION.SDK_INT >= 24);
    public static final String SCHEME_IMTO = "imto";
    public static final String SCHEME_MAILTO = "mailto";
    public static final String SCHEME_SMSTO = "smsto";
    private static final String TAG = "ContactsUtils";
    public static final long USER_TYPE_CURRENT = 0;
    public static final long USER_TYPE_WORK = 1;
    private static int sThumbnailSize = -1;

    public static String lookupProviderNameFromId(int i) {
        switch (i) {
            case 0:
                return "AIM";
            case 1:
                return "MSN";
            case 2:
                return "Yahoo";
            case 3:
                return "SKYPE";
            case 4:
                return "QQ";
            case 5:
                return "GTalk";
            case 6:
                return "ICQ";
            case 7:
                return "JABBER";
            default:
                return null;
        }
    }

    public static boolean isGraphic(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && TextUtils.isGraphic(charSequence);
    }

    public static boolean areObjectsEqual(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static final boolean areIntentActionEqual(Intent intent, Intent intent2) {
        if (intent == intent2) {
            return true;
        }
        if (intent == null || intent2 == null) {
            return false;
        }
        return TextUtils.equals(intent.getAction(), intent2.getAction());
    }

    public static int getThumbnailSize(Context context) {
        Cursor query;
        if (sThumbnailSize == -1 && (query = context.getContentResolver().query(ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI, new String[]{"thumbnail_max_dim"}, (String) null, (String[]) null, (String) null)) != null) {
            try {
                if (query.moveToFirst()) {
                    sThumbnailSize = query.getInt(0);
                }
            } finally {
                query.close();
            }
        }
        int i = sThumbnailSize;
        return i != -1 ? i : DEFAULT_THUMBNAIL_SIZE;
    }

    private static Intent getCustomImIntent(ImDataItem imDataItem, int i) {
        String customProtocol = imDataItem.getCustomProtocol();
        String data = imDataItem.getData();
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        if (i != -1) {
            customProtocol = lookupProviderNameFromId(i);
        }
        if (TextUtils.isEmpty(customProtocol)) {
            return null;
        }
        return new Intent("android.intent.action.SENDTO", new Uri.Builder().scheme(SCHEME_IMTO).authority(customProtocol.toLowerCase()).appendPath(data).build());
    }

    public static Pair<Intent, Intent> buildImIntent(Context context, ImDataItem imDataItem) {
        int i;
        Intent intent;
        boolean isCreatedFromEmail = imDataItem.isCreatedFromEmail();
        Intent intent2 = null;
        if (!isCreatedFromEmail && !imDataItem.isProtocolValid()) {
            return new Pair<>((Object) null, (Object) null);
        }
        String data = imDataItem.getData();
        if (TextUtils.isEmpty(data)) {
            return new Pair<>((Object) null, (Object) null);
        }
        if (isCreatedFromEmail) {
            i = 5;
        } else {
            i = imDataItem.getProtocol().intValue();
        }
        if (i == 5) {
            int chatCapability = imDataItem.getChatCapability();
            if ((chatCapability & 4) != 0) {
                intent = new Intent("android.intent.action.SENDTO", Uri.parse("xmpp:" + data + "?message"));
                intent2 = new Intent("android.intent.action.SENDTO", Uri.parse("xmpp:" + data + "?call"));
            } else if ((chatCapability & 1) != 0) {
                intent = new Intent("android.intent.action.SENDTO", Uri.parse("xmpp:" + data + "?message"));
                intent2 = new Intent("android.intent.action.SENDTO", Uri.parse("xmpp:" + data + "?call"));
            } else {
                intent = new Intent("android.intent.action.SENDTO", Uri.parse("xmpp:" + data + "?message"));
            }
        } else {
            intent = getCustomImIntent(imDataItem, i);
        }
        return new Pair<>(intent, intent2);
    }

    public static long determineUserType(Long l, Long l2) {
        return l != null ? DirectoryCompat.isEnterpriseDirectoryId(l.longValue()) ? 1 : 0 : (l2 == null || l2.longValue() == 0 || !ContactsCompat.isEnterpriseContactId(l2.longValue())) ? 0 : 1;
    }
}
