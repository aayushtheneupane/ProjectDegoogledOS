package com.android.dialer.main.impl.toolbar;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;
import com.android.dialer.R;

public final class MainToolbarMenu extends PopupMenu {
    public MainToolbarMenu(Context context, View view) {
        super(context, view, 0, R.attr.actionOverflowMenuStyle, 0);
    }
}
