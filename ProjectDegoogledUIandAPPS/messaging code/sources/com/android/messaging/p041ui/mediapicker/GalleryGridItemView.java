package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0890B;
import com.android.messaging.datamodel.p038b.C0880t;
import com.android.messaging.p041ui.AsyncImageView;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.util.C1481w;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.messaging.ui.mediapicker.GalleryGridItemView */
public class GalleryGridItemView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: Df */
    public CheckBox f2013Df;

    /* renamed from: Kg */
    private AsyncImageView f2014Kg;

    /* renamed from: Lg */
    private RelativeLayout f2015Lg;

    /* renamed from: Mg */
    private LinearLayout f2016Mg;

    /* renamed from: Ng */
    private TextView f2017Ng;

    /* renamed from: Og */
    private TextView f2018Og;
    C0890B mData = C0947h.get().mo6593Zd();
    /* access modifiers changed from: private */
    public C1290S mHostInterface;
    private ImageView mIcon;
    private final View.OnClickListener mOnClickListener = new C1286N(this);

    public GalleryGridItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f2014Kg = (AsyncImageView) findViewById(R.id.thumbnail);
        this.f2013Df = (CheckBox) findViewById(R.id.checkbox);
        this.f2013Df.setOnClickListener(this.mOnClickListener);
        this.f2015Lg = (RelativeLayout) findViewById(R.id.additional_info);
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.f2016Mg = (LinearLayout) findViewById(R.id.file_info);
        this.f2017Ng = (TextView) findViewById(R.id.file_name);
        this.f2018Og = (TextView) findViewById(R.id.file_type);
        setOnClickListener(this.mOnClickListener);
        C1287O o = new C1287O(this);
        setOnLongClickListener(o);
        this.f2013Df.setOnLongClickListener(o);
        addOnLayoutChangeListener(new C1289Q(this));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
    }

    /* renamed from: a */
    public void mo7703a(Cursor cursor, C1290S s) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.gallery_image_cell_size);
        this.mData.mo6209a(cursor, dimensionPixelSize, dimensionPixelSize);
        this.mHostInterface = s;
        if (this.mData.mo6208Ng()) {
            setBackgroundColor(C1037D.get().mo6936Ea());
            this.mIcon.setImageResource(R.drawable.ic_photo_library_light);
            this.mIcon.clearColorFilter();
            this.f2014Kg.setVisibility(8);
            this.mIcon.setVisibility(0);
            this.f2016Mg.setVisibility(8);
            this.f2015Lg.setVisibility(0);
        } else {
            String contentType = this.mData.getContentType();
            if (C1481w.m3831za(contentType)) {
                Context context = getContext();
                setBackgroundColor(getResources().getColor(R.color.gallery_image_default_background));
                this.mIcon.setImageDrawable(context.getContentResolver().getTypeInfo(contentType).getIcon().loadDrawable(context));
                this.mIcon.setColorFilter(C1037D.get().mo6936Ea(), PorterDuff.Mode.SRC_IN);
                this.f2017Ng.setText(this.mData.getFileName());
                String[] split = contentType.split("/");
                TextView textView = this.f2018Og;
                textView.setText(split[1].toUpperCase() + " " + split[0]);
                this.f2014Kg.setVisibility(8);
                this.mIcon.setVisibility(0);
                this.f2016Mg.setVisibility(0);
                this.f2015Lg.setVisibility(0);
            } else {
                this.f2014Kg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                setBackgroundColor(getResources().getColor(R.color.gallery_image_default_background));
                this.f2014Kg.mo6858a((C0880t) this.mData.mo6206Lg());
                this.f2014Kg.setVisibility(0);
                if (C1481w.m3830Ea(this.mData.getContentType())) {
                    this.mIcon.setImageResource(R.drawable.ic_video_play_light);
                    this.mIcon.clearColorFilter();
                    this.mIcon.setVisibility(0);
                } else {
                    this.mIcon.setVisibility(8);
                }
                this.f2016Mg.setVisibility(8);
                this.f2015Lg.setVisibility(0);
                long Kg = this.mData.mo6205Kg();
                this.f2014Kg.setContentDescription(String.format(getResources().getString((Kg > 0 ? 1 : (Kg == 0 ? 0 : -1)) > 0 ? R.string.mediapicker_gallery_image_item_description : R.string.mediapicker_gallery_image_item_description_no_date), new Object[]{Long.valueOf(TimeUnit.SECONDS.toMillis(1) * Kg)}));
            }
        }
        if (!this.mHostInterface.mo7715q() || this.mData.mo6208Ng()) {
            this.f2013Df.setVisibility(8);
            this.f2013Df.setClickable(false);
            return;
        }
        this.f2013Df.setVisibility(0);
        this.f2013Df.setClickable(true);
        this.f2013Df.setChecked(this.mHostInterface.mo7711a(this.mData));
    }
}
