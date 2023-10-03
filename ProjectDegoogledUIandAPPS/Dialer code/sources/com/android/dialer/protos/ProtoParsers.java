package com.android.dialer.protos;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import com.android.dialer.common.Assert;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

public final class ProtoParsers {
    public static <T extends MessageLite> T getTrusted(Bundle bundle, String str, T t) {
        try {
            Assert.isNotNull(bundle);
            Assert.isNotNull(str);
            Assert.isNotNull(t);
            return mergeFrom(bundle.getByteArray(str), t.getDefaultInstanceForType());
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    private static <T extends MessageLite> T mergeFrom(byte[] bArr, T t) {
        try {
            GeneratedMessageLite.Builder builder = ((GeneratedMessageLite) t).toBuilder();
            builder.mergeFrom(bArr);
            return builder.build();
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public static void put(Intent intent, String str, MessageLite messageLite) {
        Assert.isNotNull(messageLite);
        Assert.isNotNull(intent);
        Assert.isNotNull(str);
        intent.putExtra(str, ((AbstractMessageLite) messageLite).toByteArray());
    }

    public static <T extends MessageLite> T getTrusted(ContentValues contentValues, String str, T t) {
        try {
            Assert.isNotNull(contentValues);
            Assert.isNotNull(str);
            Assert.isNotNull(t);
            return mergeFrom(contentValues.getAsByteArray(str), t.getDefaultInstanceForType());
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalStateException(e.toString());
        }
    }

    public static <T extends MessageLite> T getTrusted(Intent intent, String str, T t) {
        Assert.isNotNull(intent);
        return getTrusted(intent.getExtras(), str, t);
    }
}
