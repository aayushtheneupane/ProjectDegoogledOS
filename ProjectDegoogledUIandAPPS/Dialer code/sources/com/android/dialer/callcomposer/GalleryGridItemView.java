package com.android.dialer.callcomposer;

import android.content.Context;
import android.database.Cursor;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.dialer.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.concurrent.TimeUnit;

public class GalleryGridItemView extends FrameLayout {
    private View checkbox;
    private String currentFilePath;
    private final GalleryGridItemData data = new GalleryGridItemData();
    private View gallery;
    private ImageView image;
    private boolean isGallery;

    public GalleryGridItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bind(Cursor cursor) {
        this.data.bind(cursor);
        showGallery(false);
        this.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String str = this.currentFilePath;
        if (str == null || !str.equals(this.data.getFilePath())) {
            this.currentFilePath = this.data.getFilePath();
            Glide.with(getContext()).load(this.data.getFileUri()).apply(new RequestOptions().downsample(DownsampleStrategy.AT_MOST).skipMemoryCache(true)).transition(DrawableTransitionOptions.withCrossFade()).into(this.image);
        }
        long dateModifiedSeconds = this.data.getDateModifiedSeconds();
        if (dateModifiedSeconds > 0) {
            this.image.setContentDescription(getResources().getString(R.string.gallery_item_description, new Object[]{Long.valueOf(TimeUnit.SECONDS.toMillis(dateModifiedSeconds))}));
            return;
        }
        this.image.setContentDescription(getResources().getString(R.string.gallery_item_description_no_date));
    }

    public GalleryGridItemData getData() {
        return this.data;
    }

    public boolean isGallery() {
        return this.isGallery;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.image = (ImageView) findViewById(R.id.image);
        this.checkbox = findViewById(R.id.checkbox);
        this.gallery = findViewById(R.id.gallery);
        this.image.setClipToOutline(true);
        this.checkbox.setClipToOutline(true);
        this.gallery.setClipToOutline(true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    public void setSelected(boolean z) {
        if (z) {
            this.checkbox.setVisibility(0);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.gallery_item_selected_padding);
            setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            return;
        }
        this.checkbox.setVisibility(8);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.gallery_item_padding);
        setPadding(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
    }

    public void showGallery(boolean z) {
        this.isGallery = z;
        this.gallery.setVisibility(z ? 0 : 4);
    }
}
