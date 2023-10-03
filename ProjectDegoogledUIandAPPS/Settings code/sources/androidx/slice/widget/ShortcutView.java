package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.view.R$dimen;
import java.util.Set;

public class ShortcutView extends SliceChildView {
    private SliceItem mActionItem;
    private IconCompat mIcon;
    private CharSequence mLabel;
    private int mLargeIconSize;
    private ListContent mListContent;
    private Set<SliceItem> mLoadingActions;
    private int mSmallIconSize;
    private Uri mUri;

    public int getMode() {
        return 3;
    }

    public ShortcutView(Context context) {
        super(context);
        Resources resources = getResources();
        this.mSmallIconSize = resources.getDimensionPixelSize(R$dimen.abc_slice_icon_size);
        this.mLargeIconSize = resources.getDimensionPixelSize(R$dimen.abc_slice_shortcut_size);
    }

    public void setSliceContent(ListContent listContent) {
        resetView();
        this.mListContent = listContent;
        ListContent listContent2 = this.mListContent;
        if (listContent2 != null) {
            SliceActionImpl sliceActionImpl = (SliceActionImpl) listContent2.getShortcut(getContext());
            this.mActionItem = sliceActionImpl.getActionItem();
            this.mIcon = sliceActionImpl.getIcon();
            this.mLabel = sliceActionImpl.getTitle();
            boolean z = sliceActionImpl.getImageMode() == 0;
            int accentColor = this.mListContent.getAccentColor();
            if (accentColor == -1) {
                accentColor = SliceViewUtil.getColorAccent(getContext());
            }
            Drawable wrap = DrawableCompat.wrap(new ShapeDrawable(new OvalShape()));
            DrawableCompat.setTint(wrap, accentColor);
            ImageView imageView = new ImageView(getContext());
            if (this.mIcon != null && z) {
                imageView.setBackground(wrap);
            }
            addView(imageView);
            if (this.mIcon != null) {
                SliceViewUtil.createCircledIcon(getContext(), z ? this.mSmallIconSize : this.mLargeIconSize, this.mIcon, !z, this);
                this.mUri = listContent.getSliceItem().getSlice().getUri();
                setClickable(true);
            } else {
                setClickable(false);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.gravity = 17;
            setLayoutParams(layoutParams);
        }
    }

    public boolean performClick() {
        SliceItem sliceItem;
        if (this.mListContent == null) {
            return false;
        }
        if (!callOnClick()) {
            try {
                if (this.mActionItem != null) {
                    this.mActionItem.fireAction((Context) null, (Intent) null);
                    if (this.mObserver != null) {
                        EventInfo eventInfo = new EventInfo(3, 1, -1, 0);
                        if (this.mActionItem != null) {
                            sliceItem = this.mActionItem;
                        } else {
                            sliceItem = this.mListContent.getSliceItem();
                        }
                        this.mObserver.onSliceAction(eventInfo, sliceItem);
                    }
                }
            } catch (PendingIntent.CanceledException e) {
                Log.e("ShortcutView", "PendingIntent for slice cannot be sent", e);
            }
        }
        return true;
    }

    public void setLoadingActions(Set<SliceItem> set) {
        this.mLoadingActions = set;
    }

    public Set<SliceItem> getLoadingActions() {
        return this.mLoadingActions;
    }

    public void resetView() {
        this.mListContent = null;
        this.mUri = null;
        this.mActionItem = null;
        this.mLabel = null;
        this.mIcon = null;
        setBackground((Drawable) null);
        removeAllViews();
    }
}
