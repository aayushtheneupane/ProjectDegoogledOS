package com.android.dialer.app.list;

import android.view.View;
import com.android.dialer.app.list.PhoneFavoriteTileView;

/* renamed from: com.android.dialer.app.list.-$$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6I-bS8ltmF6CMA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6IbS8ltmF6CMA implements View.OnLongClickListener {
    public static final /* synthetic */ $$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6IbS8ltmF6CMA INSTANCE = new $$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6IbS8ltmF6CMA();

    private /* synthetic */ $$Lambda$PhoneFavoriteTileView$GgkKvFY_MrCSF6IbS8ltmF6CMA() {
    }

    public final boolean onLongClick(View view) {
        boolean unused = ((PhoneFavoriteTileView) view).startDragAndDrop(PhoneFavoriteTileView.EMPTY_CLIP_DATA, new PhoneFavoriteTileView.EmptyDragShadowBuilder(), "PHONE_FAVORITE_TILE", 0);
        return true;
    }
}
