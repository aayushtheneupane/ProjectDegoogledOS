package com.android.systemui.p006qs;

import android.content.Context;
import com.android.systemui.p006qs.external.TileServices;

/* renamed from: com.android.systemui.qs.QSHost */
public interface QSHost {

    /* renamed from: com.android.systemui.qs.QSHost$Callback */
    public interface Callback {
        void onTilesChanged();
    }

    void collapsePanels();

    Context getContext();

    TileServices getTileServices();

    int indexOf(String str);

    void openPanels();

    void removeTile(String str);

    void unmarkTileAsAutoAdded(String str);

    void warn(String str, Throwable th);
}
