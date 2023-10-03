package com.android.systemui.p006qs.tiles;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.p006qs.QSHost;
import com.android.systemui.p006qs.tileimpl.QSTileImpl;
import com.android.systemui.plugins.p005qs.QSTile;

/* renamed from: com.android.systemui.qs.tiles.SoundSearchTile */
public class SoundSearchTile extends QSTileImpl<QSTile.BooleanState> {
    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 1999;
    }

    public void handleSetListening(boolean z) {
    }

    public SoundSearchTile(QSHost qSHost) {
        super(qSHost);
    }

    public void handleClick() {
        this.mHost.collapsePanels();
        if (Utils.isPackageInstalled(this.mContext, "com.shazam.android") || Utils.isPackageInstalled(this.mContext, "com.shazam.encore.android")) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addFlags(268468224);
            intent.setAction("com.shazam.android.intent.actions.START_TAGGING");
            this.mContext.startActivity(intent);
        } else if (Utils.isPackageInstalled(this.mContext, "com.melodis.midomiMusicIdentifier.freemium") || Utils.isPackageInstalled(this.mContext, "com.melodis.midomiMusicIdentifier")) {
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.addFlags(268468224);
            intent2.setAction("com.soundhound.android.ID_NOW_EXTERNAL");
            this.mContext.startActivity(intent2);
        } else if (Utils.isPackageInstalled(this.mContext, "com.google.android.googlequicksearchbox")) {
            Intent intent3 = new Intent("android.intent.action.MAIN");
            intent3.addFlags(268468224);
            intent3.setAction("com.google.android.googlequicksearchbox.MUSIC_SEARCH");
            this.mContext.startActivity(intent3);
        } else {
            Context context = this.mContext;
            Toast.makeText(context, context.getString(C1784R$string.quick_settings_sound_search_no_app), 1).show();
        }
    }

    public CharSequence getTileLabel() {
        return this.mContext.getString(C1784R$string.quick_settings_sound_search);
    }

    public void handleUpdateState(QSTile.BooleanState booleanState, Object obj) {
        booleanState.label = this.mContext.getString(C1784R$string.quick_settings_sound_search);
        booleanState.contentDescription = this.mContext.getString(C1784R$string.quick_settings_sound_search);
        booleanState.icon = QSTileImpl.ResourceIcon.get(C1776R$drawable.ic_qs_sound_search);
        booleanState.state = 1;
    }

    public QSTile.BooleanState newTileState() {
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.handlesLongClick = false;
        return booleanState;
    }
}
