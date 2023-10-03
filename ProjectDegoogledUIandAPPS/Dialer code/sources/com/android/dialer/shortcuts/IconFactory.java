package com.android.dialer.shortcuts;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.lettertile.LetterTileDrawable;
import java.io.InputStream;

class IconFactory {
    private final Context context;

    IconFactory(Context context2) {
        this.context = context2;
    }

    public Icon create(DialerShortcut dialerShortcut) {
        Assert.isWorkerThread();
        return create(ContactsContract.Contacts.getLookupUri(dialerShortcut.getContactId(), dialerShortcut.getLookupKey()), dialerShortcut.getDisplayName(), dialerShortcut.getLookupKey());
    }

    public Icon create(ShortcutInfo shortcutInfo) {
        Assert.isWorkerThread();
        return create(DialerShortcut.getLookupUriFromShortcutInfo(shortcutInfo), shortcutInfo.getShortLabel().toString(), shortcutInfo.getId());
    }

    private Icon create(Uri uri, String str, String str2) {
        Assert.isWorkerThread();
        InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.context.getContentResolver(), uri, false);
        int i = Build.VERSION.SDK_INT;
        if (openContactPhotoInputStream != null) {
            return Icon.createWithAdaptiveBitmap(BitmapFactory.decodeStream(openContactPhotoInputStream));
        }
        LetterTileDrawable letterTileDrawable = new LetterTileDrawable(this.context.getResources());
        letterTileDrawable.setScale(1.0f / (AdaptiveIconDrawable.getExtraInsetFraction() + 1.0f));
        letterTileDrawable.setCanonicalDialerLetterTileDetails(str, str2, 2, 1);
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.launcher_shortcut_adaptive_icon_size);
        return Icon.createWithAdaptiveBitmap(R$style.drawableToBitmap(letterTileDrawable, dimensionPixelSize, dimensionPixelSize));
    }
}
