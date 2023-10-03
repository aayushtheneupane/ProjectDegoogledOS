package com.android.messaging.p041ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0945f;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.appsettings.ApnEditorActivity;
import com.android.messaging.p041ui.appsettings.ApnSettingsActivity;
import com.android.messaging.p041ui.appsettings.ApplicationSettingsActivity;
import com.android.messaging.p041ui.appsettings.PerSubscriptionSettingsActivity;
import com.android.messaging.p041ui.appsettings.SettingsActivity;
import com.android.messaging.p041ui.attachmentchooser.AttachmentChooserActivity;
import com.android.messaging.p041ui.conversation.ConversationActivity;
import com.android.messaging.p041ui.conversation.LaunchConversationActivity;
import com.android.messaging.p041ui.conversationlist.ArchivedConversationListActivity;
import com.android.messaging.p041ui.conversationlist.ConversationListActivity;
import com.android.messaging.p041ui.conversationlist.ForwardMessageActivity;
import com.android.messaging.p041ui.conversationsettings.PeopleAndOptionsActivity;
import com.android.messaging.p041ui.debug.DebugMmsConfigActivity;
import com.android.messaging.p041ui.photoviewer.BuglePhotoViewActivity;
import com.android.messaging.receiver.NotificationReceiver;
import com.android.messaging.sms.C1027w;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;
import com.android.messaging.util.ConversationIdSet;
import com.android.p032ex.photo.C0720d;
import com.android.p032ex.photo.C0721e;
import com.android.vcard.VCardConfig;
import p000a.p010e.p011a.C0045d;

/* renamed from: com.android.messaging.ui.Ea */
public class C1040Ea {
    /* renamed from: G */
    private Intent m2561G(Context context) {
        return new Intent(context, ConversationListActivity.class);
    }

    /* renamed from: a */
    private Intent m2563a(Context context, String str, MessageData messageData, boolean z) {
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.setFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        if (str != null) {
            intent.putExtra("conversation_id", str);
        }
        if (messageData != null) {
            intent.putExtra("draft_data", messageData);
            ClipData clipData = null;
            for (MessagePartData messagePartData : messageData.getParts()) {
                if (messagePartData.mo6300dh()) {
                    Uri contentUri = messagePartData.getContentUri();
                    if (clipData == null) {
                        clipData = ClipData.newRawUri("Attachments", contentUri);
                    } else {
                        clipData.addItem(new ClipData.Item(contentUri));
                    }
                }
            }
            if (clipData != null) {
                intent.setClipData(clipData);
                intent.addFlags(1);
            }
        }
        if (z) {
            intent.putExtra("with_custom_transition", true);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        }
        return intent;
    }

    public static C1040Ea get() {
        return C0967f.get().mo6652Od();
    }

    /* renamed from: A */
    public void mo6948A(Context context) {
        context.startActivity(new Intent(context, SettingsActivity.class));
    }

    /* renamed from: b */
    public void mo6968b(Context context, String str, String str2) {
        MessageData messageData = null;
        if (!TextUtils.isEmpty(str2)) {
            messageData = MessageData.m1757b(str, (String) null, str2);
        }
        TaskStackBuilder.create(context).addNextIntentWithParentStack(m2563a(context, str, messageData, false)).startActivities();
    }

    /* renamed from: c */
    public PendingIntent mo6970c(Context context, String str, MessageData messageData) {
        Intent a = m2563a(context, str, messageData, false);
        a.setData(MessagingContentProvider.m1269n(str));
        return m2562a(context, a, 0);
    }

    /* renamed from: d */
    public void mo6972d(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.putExtra("SingleItemOnly", true);
        intent.setDataAndType(uri, "video/*");
        m2564b(context, intent);
    }

    /* renamed from: dj */
    public Intent mo6973dj() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.android.cellbroadcastreceiver", "com.android.cellbroadcastreceiver.CellBroadcastListActivity"));
        intent.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        return intent;
    }

    /* renamed from: e */
    public void mo6975e(Context context, Uri uri) {
        C1424b.m3592ia(MediaScratchFileProvider.m1258d(uri));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/x-vCard".toLowerCase());
        intent.addFlags(1);
        m2564b(context, intent);
    }

    /* renamed from: f */
    public void mo6976f(Context context, Uri uri) {
        context.startActivity(new Intent(context, VCardDetailActivity.class).putExtra("vcard_uri", uri));
    }

    /* renamed from: h */
    public void mo6977h(Context context, String str) {
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
        String str2 = C1027w.isEmailAddress(str) ? NotificationCompat.CATEGORY_EMAIL : "phone";
        intent.setType("vnd.android.cursor.item/contact");
        intent.putExtra(str2, str);
        m2564b(context, intent);
    }

    /* renamed from: i */
    public void mo6978i(Context context, String str) {
        m2564b(context, new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* renamed from: p */
    public Intent mo6979p(Context context) {
        return new Intent(context, PerSubscriptionSettingsActivity.class).putExtra("sub_id", -1).putExtra("per_sub_setting_title", (String) null);
    }

    /* renamed from: q */
    public Intent mo6980q(Context context) {
        Intent intent = new Intent(context, LaunchConversationActivity.class);
        intent.setFlags(1140850688);
        return intent;
    }

    /* renamed from: r */
    public PendingIntent mo6981r(Context context) {
        return m2562a(context, m2561G(context), 0);
    }

    /* renamed from: s */
    public PendingIntent mo6982s(Context context) {
        TaskStackBuilder create = TaskStackBuilder.create(context);
        create.addNextIntent(m2561G(context));
        create.addNextIntentWithParentStack(new Intent(context, SmsStorageLowWarningActivity.class));
        return create.getPendingIntent(0, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
    }

    /* renamed from: t */
    public PendingIntent mo6983t(Context context) {
        return m2562a(context, m2561G(context), 0);
    }

    /* renamed from: u */
    public PendingIntent mo6984u(Context context) {
        return m2562a(context, m2561G(context), 0);
    }

    /* renamed from: v */
    public void mo6985v(Context context) {
        context.startActivity(new Intent(context, ArchivedConversationListActivity.class));
    }

    /* renamed from: w */
    public void mo6986w(Context context) {
        context.startActivity(new Intent(context, BlockedParticipantsActivity.class));
    }

    /* renamed from: x */
    public void mo6987x(Context context) {
        context.startActivity(m2561G(context));
    }

    /* renamed from: y */
    public void mo6988y(Context context) {
        context.startActivity(new Intent(context, DebugMmsConfigActivity.class));
    }

    /* renamed from: z */
    public void mo6989z(Context context) {
        context.startActivity(new Intent(context, PermissionCheckActivity.class));
    }

    /* renamed from: c */
    public PendingIntent mo6969c(Context context, String str, int i) {
        Intent a = m2563a(context, (String) null, (MessageData) null, false);
        if (str != null) {
            a.putExtra("conversation_id", str);
            a.setAction("com.android.messaging.widget_conversation:" + str);
        }
        a.addFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        return m2562a(context, a, i);
    }

    /* renamed from: d */
    public Intent mo6971d(Context context, int i) {
        return new Intent(context, ApnSettingsActivity.class).putExtra("sub_id", i);
    }

    /* renamed from: b */
    public void mo6966b(Fragment fragment) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.putExtra("android.intent.extra.MIME_TYPES", MessagePartData.f1178UB);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        fragment.startActivityForResult(intent, 1400);
    }

    /* renamed from: e */
    public PendingIntent mo6974e(Context context, int i) {
        Intent intent = new Intent(context, WidgetPickConversationActivity.class);
        intent.putExtra("appWidgetId", i);
        intent.setAction("android.appwidget.action.APPWIDGET_CONFIGURE");
        intent.setData(Uri.parse(intent.toUri(1)));
        intent.addFlags(1342177280);
        return m2562a(context, intent, 0);
    }

    /* renamed from: b */
    public void mo6967b(Context context, MessageData messageData) {
        context.startActivity(new Intent(context, ForwardMessageActivity.class).putExtra("draft_data", messageData));
    }

    /* renamed from: a */
    public void mo6960a(Context context, String str, MessageData messageData, Bundle bundle, boolean z) {
        C1424b.m3592ia(!z || bundle != null);
        context.startActivity(m2563a(context, str, messageData, z), bundle);
    }

    /* renamed from: b */
    public Intent mo6965b(Context context, String str, MessageData messageData) {
        return m2563a(context, str, messageData, false);
    }

    /* renamed from: b */
    public Intent mo6964b(Context context, String str, int i) {
        Intent intent = new Intent(context, ApnEditorActivity.class);
        intent.putExtra("apn_row_id", str);
        intent.putExtra("sub_id", i);
        return intent;
    }

    /* renamed from: a */
    public void mo6958a(Context context, MessageData messageData) {
        context.startActivity(m2563a(context, (String) null, messageData, false));
    }

    /* renamed from: b */
    public Intent mo6963b(Activity activity) {
        Intent intent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
        intent.putExtra("package", activity.getPackageName());
        return intent;
    }

    /* renamed from: a */
    public void mo6955a(Fragment fragment) {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("vnd.android.cursor.dir/contact");
        try {
            fragment.startActivityForResult(intent, 1500);
        } catch (ActivityNotFoundException e) {
            C1430e.m3631w("MessagingApp", "Couldn't find activity:", e);
            C1486ya.m3848Pa(R.string.activity_not_found_message);
        }
    }

    /* renamed from: b */
    private void m2564b(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            C1430e.m3631w("MessagingApp", "Couldn't find activity:", e);
            C1486ya.m3848Pa(R.string.activity_not_found_message);
        }
    }

    /* renamed from: a */
    public void mo6953a(Activity activity, String str) {
        Intent intent = new Intent(activity, PeopleAndOptionsActivity.class);
        intent.putExtra("conversation_id", str);
        activity.startActivityForResult(intent, 0);
    }

    /* renamed from: a */
    public void mo6959a(Context context, String str, Point point) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
        Bundle bundle = new Bundle();
        bundle.putParcelable("touchPoint", point);
        intent.putExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS", bundle);
        m2564b(context, intent);
    }

    /* renamed from: a */
    public void mo6957a(Context context, ContentValues contentValues) {
        context.startActivity(new Intent(context, ClassZeroActivity.class).putExtra("message_values", contentValues).setFlags(402653184));
    }

    /* renamed from: a */
    public void mo6954a(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, AttachmentChooserActivity.class);
        intent.putExtra("conversation_id", str);
        activity.startActivityForResult(intent, i);
    }

    /* renamed from: a */
    public void mo6952a(Activity activity, Uri uri, Rect rect, Uri uri2) {
        C0720d b = C0721e.m1129b(activity, BuglePhotoViewActivity.class);
        b.mo5730E(uri2.toString());
        b.mo5729D(uri.toString());
        b.setProjection(C0945f.f1350Wu);
        b.mo5735b(rect.left, rect.top, rect.width(), rect.height());
        b.mo5733G(false);
        b.mo5734b(8.0f);
        activity.startActivity(b.build());
        activity.overridePendingTransition(0, 0);
    }

    /* renamed from: a */
    public void mo6962a(Context context, boolean z) {
        Intent intent = new Intent(context, ApplicationSettingsActivity.class);
        intent.putExtra("top_level_settings", z);
        context.startActivity(intent);
    }

    /* renamed from: a */
    public void mo6961a(Context context, String str, String str2) {
        Intent intent = new Intent("conversation_self_id_change");
        intent.putExtra("conversation_id", str);
        intent.putExtra("conversation_self_id", str2);
        C0045d.getInstance(context).sendBroadcast(intent);
    }

    /* renamed from: a */
    public PendingIntent mo6951a(Context context, String str, String str2, boolean z, int i) {
        Intent intent = new Intent(context, RemoteInputEntrypointActivity.class);
        intent.setAction("android.intent.action.SENDTO");
        intent.setData(MessagingContentProvider.m1269n(str));
        intent.putExtra("conversation_id", str);
        intent.putExtra("self_id", str2);
        intent.putExtra("requires_mms", z);
        intent.setFlags(268468224);
        return m2562a(context, intent, i);
    }

    /* renamed from: a */
    public PendingIntent mo6949a(Context context, int i, ConversationIdSet conversationIdSet, int i2) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.setAction("com.android.messaging.reset_notifications");
        intent.putExtra("notifications_update", i);
        if (conversationIdSet != null) {
            intent.putExtra("conversation_id_set", conversationIdSet.mo8037Ul());
        }
        return PendingIntent.getBroadcast(context, i2, intent, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
    }

    /* renamed from: a */
    public PendingIntent mo6950a(Context context, ConversationIdSet conversationIdSet, int i) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.setAction("com.android.messaging.mark_as_read");
        if (conversationIdSet != null) {
            intent.putExtra("conversation_id_set", conversationIdSet.mo8037Ul());
        }
        return PendingIntent.getBroadcast(context, i, intent, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
    }

    /* renamed from: a */
    private static PendingIntent m2562a(Context context, Intent intent, int i) {
        TaskStackBuilder create = TaskStackBuilder.create(context);
        create.addNextIntentWithParentStack(intent);
        return create.getPendingIntent(i, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
    }

    /* renamed from: a */
    public void mo6956a(Context context, int i, String str) {
        context.startActivity(new Intent(context, PerSubscriptionSettingsActivity.class).putExtra("sub_id", i).putExtra("per_sub_setting_title", str));
    }
}
