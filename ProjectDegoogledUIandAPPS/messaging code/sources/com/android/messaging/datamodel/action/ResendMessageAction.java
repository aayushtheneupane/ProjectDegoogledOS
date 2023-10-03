package com.android.messaging.datamodel.action;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.datamodel.C0887c;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.C0955p;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

public class ResendMessageAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0795I();

    ResendMessageAction(String str) {
        this.f1057Oy.putString("message_id", str);
    }

    /* renamed from: S */
    public static void m1438S(String str) {
        new ResendMessageAction(str).start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ve */
    public Object mo5956ve() {
        String str;
        String str2;
        String string = this.f1057Oy.getString("message_id");
        C0955p database = C0947h.get().getDatabase();
        MessageData h = C0887c.m1671h(database, string);
        if (h == null || !h.mo6246Qg()) {
            String d = C0632a.m1023d("ResendMessageAction: Cannot resend message ", string, "; ");
            if (h != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(d);
                sb.append("status = ");
                int status = h.getStatus();
                switch (status) {
                    case 0:
                        str2 = "UNKNOWN";
                        break;
                    case 1:
                        str2 = "OUTGOING_COMPLETE";
                        break;
                    case 2:
                        str2 = "OUTGOING_DELIVERED";
                        break;
                    case 3:
                        str2 = "OUTGOING_DRAFT";
                        break;
                    case 4:
                        str2 = "OUTGOING_YET_TO_SEND";
                        break;
                    case 5:
                        str2 = "OUTGOING_SENDING";
                        break;
                    case 6:
                        str2 = "OUTGOING_RESENDING";
                        break;
                    case 7:
                        str2 = "OUTGOING_AWAITING_RETRY";
                        break;
                    case 8:
                        str2 = "OUTGOING_FAILED";
                        break;
                    case 9:
                        str2 = "OUTGOING_FAILED_EMERGENCY_NUMBER";
                        break;
                    default:
                        switch (status) {
                            case 100:
                                str2 = "INCOMING_COMPLETE";
                                break;
                            case 101:
                                str2 = "INCOMING_YET_TO_MANUAL_DOWNLOAD";
                                break;
                            case 102:
                                str2 = "INCOMING_RETRYING_MANUAL_DOWNLOAD";
                                break;
                            case 103:
                                str2 = "INCOMING_MANUAL_DOWNLOADING";
                                break;
                            case 104:
                                str2 = "INCOMING_RETRYING_AUTO_DOWNLOAD";
                                break;
                            case 105:
                                str2 = "INCOMING_AUTO_DOWNLOADING";
                                break;
                            case 106:
                                str2 = "INCOMING_DOWNLOAD_FAILED";
                                break;
                            case 107:
                                str2 = "INCOMING_EXPIRED_OR_NOT_AVAILABLE";
                                break;
                            default:
                                str2 = String.valueOf(status) + " (check MessageData)";
                                break;
                        }
                }
                sb.append(str2);
                str = sb.toString();
            } else {
                str = C0632a.m1025k(d, "not found in database");
            }
            C1430e.m3622e("MessagingAppDataModel", str);
            return null;
        }
        boolean ff = h.mo6266ff();
        long currentTimeMillis = System.currentTimeMillis();
        if (ff) {
            currentTimeMillis = ((currentTimeMillis + 500) / 1000) * 1000;
        }
        C1430e.m3625i("MessagingAppDataModel", "ResendMessageAction: Resending message " + string + "; changed timestamp from " + h.mo6288rg() + " to " + currentTimeMillis);
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_status", 4);
        contentValues.put("received_timestamp", Long.valueOf(currentTimeMillis));
        contentValues.put("sent_timestamp", Long.valueOf(currentTimeMillis));
        contentValues.put("retry_start_timestamp", Long.valueOf(currentTimeMillis));
        C0887c.m1663c(database, h.getMessageId(), contentValues);
        MessagingContentProvider.m1273r(h.mo6250Ue());
        this.f1057Oy.putInt("sub_id", C0887c.m1667e(database, h.mo6275kf()));
        ProcessPendingMessagesAction.m1420a(false, this);
        return h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ ResendMessageAction(Parcel parcel, C0795I i) {
        super(parcel);
    }
}
