package com.android.dialer.app.contactinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.lettertile.LetterTileDrawable;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ContactPhotoLoader {
    private final ContactInfo contactInfo;
    private final Context context;

    public ContactPhotoLoader(Context context2, ContactInfo contactInfo2) {
        this.context = (Context) Objects.requireNonNull(context2);
        this.contactInfo = (ContactInfo) Objects.requireNonNull(contactInfo2);
    }

    /* access modifiers changed from: package-private */
    public Drawable getIcon() {
        RoundedBitmapDrawable roundedBitmapDrawable = null;
        if (this.contactInfo.photoUri != null) {
            try {
                InputStream openInputStream = this.context.getContentResolver().openInputStream(this.contactInfo.photoUri);
                if (openInputStream == null) {
                    LogUtil.m10w("ContactPhotoLoader.createPhotoIconDrawable", "createPhotoIconDrawable: InputStream is null", new Object[0]);
                } else {
                    Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
                    openInputStream.close();
                    if (decodeStream == null) {
                        LogUtil.m10w("ContactPhotoLoader.createPhotoIconDrawable", "createPhotoIconDrawable: Bitmap is null", new Object[0]);
                    } else {
                        RoundedBitmapDrawable create = DrawableCompat.create(this.context.getResources(), decodeStream);
                        create.setAntiAlias(true);
                        create.setCircular(true);
                        roundedBitmapDrawable = create;
                    }
                }
            } catch (IOException e) {
                LogUtil.m8e("ContactPhotoLoader.createPhotoIconDrawable", e.toString(), new Object[0]);
            }
        }
        if (roundedBitmapDrawable != null) {
            return roundedBitmapDrawable;
        }
        Context context2 = this.context;
        ContactInfoHelper contactInfoHelper = new ContactInfoHelper(context2, R$style.getCurrentCountryIso(context2));
        LetterTileDrawable letterTileDrawable = new LetterTileDrawable(this.context.getResources());
        ContactInfo contactInfo2 = this.contactInfo;
        String str = contactInfo2.name;
        String str2 = contactInfo2.lookupKey;
        contactInfoHelper.isBusiness(contactInfo2.sourceType);
        letterTileDrawable.setCanonicalDialerLetterTileDetails(str, str2, 1, 1);
        return letterTileDrawable;
    }

    public Bitmap loadPhotoIcon() {
        Assert.isWorkerThread();
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.contact_photo_size);
        Drawable icon = getIcon();
        Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        icon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        icon.draw(canvas);
        return createBitmap;
    }
}
