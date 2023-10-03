package com.android.dialer.voicemail.listui;

import android.content.Context;
import android.database.Cursor;
import android.support.p000v4.content.CursorLoader;
import android.text.TextUtils;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.android.dialer.calllog.database.contract.AnnotatedCallLogContract;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.voicemail.model.VoicemailEntry;
import com.google.protobuf.InvalidProtocolBufferException;

final class VoicemailCursorLoader extends CursorLoader {
    public static final String[] VOICEMAIL_COLUMNS = {"_id", "timestamp", "number", "formatted_number", "duration", "geocoded_location", "call_type", "transcription", "voicemail_uri", "is_read", "number_attributes", "transcription_state", "phone_account_component_name", "phone_account_id"};

    VoicemailCursorLoader(Context context) {
        super(context, AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI, VOICEMAIL_COLUMNS, "call_type = ?", new String[]{Integer.toString(4)}, "timestamp DESC");
    }

    static long getTimestamp(Cursor cursor) {
        return cursor.getLong(1);
    }

    static VoicemailEntry toVoicemailEntry(Cursor cursor) {
        try {
            DialerPhoneNumber parseFrom = DialerPhoneNumber.parseFrom(cursor.getBlob(2));
            try {
                NumberAttributes parseFrom2 = NumberAttributes.parseFrom(cursor.getBlob(10));
                Assert.checkArgument(!parseFrom2.getIsCp2InfoIncomplete(), "CP2 info incomplete for number: %s", LogUtil.sanitizePii(parseFrom.getNormalizedNumber()));
                VoicemailEntry.Builder newBuilder = VoicemailEntry.newBuilder();
                newBuilder.setId((long) cursor.getInt(0));
                newBuilder.setTimestamp(cursor.getLong(1));
                newBuilder.setNumber(parseFrom);
                newBuilder.setDuration(cursor.getLong(4));
                newBuilder.setCallType(cursor.getInt(6));
                newBuilder.setIsRead(cursor.getInt(9));
                newBuilder.setNumberAttributes(parseFrom2);
                newBuilder.setTranscriptionState(cursor.getInt(11));
                String string = cursor.getString(3);
                if (!TextUtils.isEmpty(string)) {
                    newBuilder.setFormattedNumber(string);
                }
                String string2 = cursor.getString(5);
                if (!TextUtils.isEmpty(string2)) {
                    newBuilder.setGeocodedLocation(string2);
                }
                String string3 = cursor.getString(7);
                if (!TextUtils.isEmpty(string3)) {
                    newBuilder.setTranscription(string3);
                }
                String string4 = cursor.getString(8);
                if (!TextUtils.isEmpty(string4)) {
                    newBuilder.setVoicemailUri(string4);
                }
                String string5 = cursor.getString(12);
                if (!TextUtils.isEmpty(string5)) {
                    newBuilder.setPhoneAccountComponentName(string5);
                }
                String string6 = cursor.getString(13);
                if (!TextUtils.isEmpty(string6)) {
                    newBuilder.setPhoneAccountId(string6);
                }
                return (VoicemailEntry) newBuilder.build();
            } catch (InvalidProtocolBufferException unused) {
                throw new IllegalStateException("Couldn't parse NumberAttributes bytes");
            }
        } catch (InvalidProtocolBufferException unused2) {
            throw new IllegalStateException("Couldn't parse DialerPhoneNumber bytes");
        }
    }
}
