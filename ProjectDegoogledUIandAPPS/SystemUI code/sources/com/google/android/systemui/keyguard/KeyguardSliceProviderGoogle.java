package com.google.android.systemui.keyguard;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Trace;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.google.android.systemui.smartspace.SmartSpaceCard;
import com.google.android.systemui.smartspace.SmartSpaceController;
import com.google.android.systemui.smartspace.SmartSpaceData;
import com.google.android.systemui.smartspace.SmartSpaceUpdateListener;
import java.lang.ref.WeakReference;

public class KeyguardSliceProviderGoogle extends KeyguardSliceProvider implements SmartSpaceUpdateListener {
    private final Uri mCalendarUri = Uri.parse("content://com.android.systemui.keyguard/smartSpace/calendar");
    private SmartSpaceData mSmartSpaceData;
    private final Uri mWeatherUri = Uri.parse("content://com.android.systemui.keyguard/smartSpace/weather");

    private static class AddShadowTask extends AsyncTask<Bitmap, Void, Bitmap> {
        private final float mBlurRadius;
        private final WeakReference<KeyguardSliceProviderGoogle> mProviderReference;
        private final SmartSpaceCard mWeatherCard;

        AddShadowTask(KeyguardSliceProviderGoogle keyguardSliceProviderGoogle, SmartSpaceCard smartSpaceCard) {
            this.mProviderReference = new WeakReference<>(keyguardSliceProviderGoogle);
            this.mWeatherCard = smartSpaceCard;
            this.mBlurRadius = keyguardSliceProviderGoogle.getContext().getResources().getDimension(C1775R$dimen.smartspace_icon_shadow);
        }

        public Bitmap doInBackground(Bitmap... bitmapArr) {
            return applyShadow(bitmapArr[0]);
        }

        public void onPostExecute(Bitmap bitmap) {
            KeyguardSliceProviderGoogle keyguardSliceProviderGoogle;
            synchronized (this) {
                this.mWeatherCard.setIcon(bitmap);
                keyguardSliceProviderGoogle = (KeyguardSliceProviderGoogle) this.mProviderReference.get();
            }
            if (keyguardSliceProviderGoogle != null) {
                keyguardSliceProviderGoogle.notifyChange();
            }
        }

        private Bitmap applyShadow(Bitmap bitmap) {
            BlurMaskFilter blurMaskFilter = new BlurMaskFilter(this.mBlurRadius, BlurMaskFilter.Blur.NORMAL);
            Paint paint = new Paint();
            paint.setMaskFilter(blurMaskFilter);
            int[] iArr = new int[2];
            Bitmap extractAlpha = bitmap.extractAlpha(paint, iArr);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint2 = new Paint();
            paint2.setAlpha(70);
            canvas.drawBitmap(extractAlpha, (float) iArr[0], ((float) iArr[1]) + (this.mBlurRadius / 2.0f), paint2);
            extractAlpha.recycle();
            paint2.setAlpha(255);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint2);
            return createBitmap;
        }
    }

    public boolean onCreateSliceProvider() {
        boolean onCreateSliceProvider = super.onCreateSliceProvider();
        SmartSpaceController.get(getContext()).addListener(this);
        this.mSmartSpaceData = new SmartSpaceData();
        return onCreateSliceProvider;
    }

    public Slice onBindSlice(Uri uri) {
        Slice build;
        IconCompat iconCompat;
        Trace.beginSection("KeyguardSliceProviderGoogle#onBindSlice");
        synchronized (this) {
            ListBuilder listBuilder = new ListBuilder(getContext(), this.mSliceUri, -1);
            SmartSpaceCard currentCard = this.mSmartSpaceData.getCurrentCard();
            if (currentCard != null && !currentCard.isExpired()) {
                if (!TextUtils.isEmpty(currentCard.getTitle())) {
                    Bitmap icon = currentCard.getIcon();
                    SliceAction sliceAction = null;
                    if (icon == null) {
                        iconCompat = null;
                    } else {
                        iconCompat = IconCompat.createWithBitmap(icon);
                    }
                    PendingIntent pendingIntent = currentCard.getPendingIntent();
                    if (!(iconCompat == null || pendingIntent == null)) {
                        sliceAction = SliceAction.create(pendingIntent, iconCompat, 1, currentCard.getTitle());
                    }
                    ListBuilder.HeaderBuilder headerBuilder = new ListBuilder.HeaderBuilder(this.mHeaderUri);
                    headerBuilder.setTitle(currentCard.getFormattedTitle());
                    if (sliceAction != null) {
                        headerBuilder.setPrimaryAction(sliceAction);
                    }
                    listBuilder.setHeader(headerBuilder);
                    String subtitle = currentCard.getSubtitle();
                    if (subtitle != null) {
                        ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder(this.mCalendarUri);
                        rowBuilder.setTitle(subtitle);
                        if (iconCompat != null) {
                            rowBuilder.addEndItem(iconCompat, 1);
                        }
                        if (sliceAction != null) {
                            rowBuilder.setPrimaryAction(sliceAction);
                        }
                        listBuilder.addRow(rowBuilder);
                    }
                    addWeather(listBuilder);
                    addZenModeLocked(listBuilder);
                    addPrimaryActionLocked(listBuilder);
                    build = listBuilder.build();
                }
            }
            if (needsMediaLocked()) {
                addMediaLocked(listBuilder);
            } else {
                ListBuilder.RowBuilder rowBuilder2 = new ListBuilder.RowBuilder(this.mDateUri);
                rowBuilder2.setTitle(getFormattedDateLocked());
                listBuilder.addRow(rowBuilder2);
            }
            addWeather(listBuilder);
            addNextAlarmLocked(listBuilder);
            addZenModeLocked(listBuilder);
            addPrimaryActionLocked(listBuilder);
            build = listBuilder.build();
        }
        Trace.endSection();
        return build;
    }

    private void addWeather(ListBuilder listBuilder) {
        SmartSpaceCard weatherCard = this.mSmartSpaceData.getWeatherCard();
        if (weatherCard != null && !weatherCard.isExpired()) {
            ListBuilder.RowBuilder rowBuilder = new ListBuilder.RowBuilder(this.mWeatherUri);
            rowBuilder.setTitle(weatherCard.getTitle());
            Bitmap icon = weatherCard.getIcon();
            if (icon != null) {
                IconCompat createWithBitmap = IconCompat.createWithBitmap(icon);
                createWithBitmap.setTintMode(PorterDuff.Mode.DST);
                rowBuilder.addEndItem(createWithBitmap, 1);
            }
            listBuilder.addRow(rowBuilder);
        }
    }

    public void onSmartSpaceUpdated(SmartSpaceData smartSpaceData) {
        synchronized (this) {
            this.mSmartSpaceData = smartSpaceData;
        }
        SmartSpaceCard weatherCard = smartSpaceData.getWeatherCard();
        if (weatherCard == null || weatherCard.getIcon() == null || weatherCard.isIconProcessed()) {
            notifyChange();
            return;
        }
        weatherCard.setIconProcessed(true);
        new AddShadowTask(this, weatherCard).execute(new Bitmap[]{weatherCard.getIcon()});
    }

    public void updateClockLocked() {
        notifyChange();
    }
}
