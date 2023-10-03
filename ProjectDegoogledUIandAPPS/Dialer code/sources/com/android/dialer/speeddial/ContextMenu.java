package com.android.dialer.speeddial;

import android.content.Context;
import android.support.p002v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;

public class ContextMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener {
    private final ContextMenuItemListener listener;
    private final SpeedDialUiItem speedDialUiItem;
    private final SpeedDialEntry.Channel videoChannel;
    private boolean visible;
    private final SpeedDialEntry.Channel voiceChannel;

    public interface ContextMenuItemListener {
        void openContactInfo(SpeedDialUiItem speedDialUiItem);

        void openSmsConversation(String str);

        void placeCall(SpeedDialEntry.Channel channel);

        void removeFavoriteContact(SpeedDialUiItem speedDialUiItem);
    }

    private ContextMenu(Context context, View view, ContextMenuItemListener contextMenuItemListener, SpeedDialUiItem speedDialUiItem2) {
        super(context, view, 17);
        String str;
        this.listener = contextMenuItemListener;
        this.speedDialUiItem = speedDialUiItem2;
        this.voiceChannel = speedDialUiItem2.getDefaultVoiceChannel();
        this.videoChannel = speedDialUiItem2.getDefaultVideoChannel();
        setOnMenuItemClickListener(this);
        getMenuInflater().inflate(R.menu.starred_contact_context_menu, getMenu());
        getMenu().findItem(R.id.voice_call_container).setVisible(this.voiceChannel != null);
        getMenu().findItem(R.id.video_call_container).setVisible(this.videoChannel != null);
        getMenu().findItem(R.id.send_message_container).setVisible(this.voiceChannel != null);
        SpeedDialEntry.Channel channel = this.voiceChannel;
        if (channel != null) {
            if (TextUtils.isEmpty(channel.label())) {
                str = this.voiceChannel.number();
            } else {
                str = context.getString(R.string.call_subject_type_and_number, new Object[]{this.voiceChannel.label(), this.voiceChannel.number()});
            }
            getMenu().findItem(R.id.starred_contact_context_menu_title).setTitle(str);
            getMenu().findItem(R.id.starred_contact_context_menu_title).setVisible(true);
            return;
        }
        getMenu().findItem(R.id.starred_contact_context_menu_title).setVisible(false);
    }

    public static ContextMenu show(Context context, View view, ContextMenuItemListener contextMenuItemListener, SpeedDialUiItem speedDialUiItem2) {
        ContextMenu contextMenu = new ContextMenu(context, view, contextMenuItemListener, speedDialUiItem2);
        contextMenu.show();
        contextMenu.visible = true;
        return contextMenu;
    }

    public void hide() {
        dismiss();
        this.visible = false;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.voice_call_container) {
            ContextMenuItemListener contextMenuItemListener = this.listener;
            SpeedDialEntry.Channel channel = this.voiceChannel;
            Assert.isNotNull(channel);
            contextMenuItemListener.placeCall(channel);
            return true;
        } else if (menuItem.getItemId() == R.id.video_call_container) {
            ContextMenuItemListener contextMenuItemListener2 = this.listener;
            SpeedDialEntry.Channel channel2 = this.videoChannel;
            Assert.isNotNull(channel2);
            contextMenuItemListener2.placeCall(channel2);
            return true;
        } else if (menuItem.getItemId() == R.id.send_message_container) {
            this.listener.openSmsConversation(this.voiceChannel.number());
            return true;
        } else if (menuItem.getItemId() == R.id.remove_container) {
            this.listener.removeFavoriteContact(this.speedDialUiItem);
            return true;
        } else if (menuItem.getItemId() == R.id.contact_info_container) {
            this.listener.openContactInfo(this.speedDialUiItem);
            return true;
        } else {
            throw new IllegalStateException("Menu option click not handled");
        }
    }
}
