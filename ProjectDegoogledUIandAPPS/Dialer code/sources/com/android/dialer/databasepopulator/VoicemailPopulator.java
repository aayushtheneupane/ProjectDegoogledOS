package com.android.dialer.databasepopulator;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.provider.VoicemailContract;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.common.Assert;
import com.android.dialer.databasepopulator.AutoValue_VoicemailPopulator_Voicemail;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class VoicemailPopulator {
    private static final Voicemail.Builder[] SIMPLE_VOICEMAILS;
    private static String componentName = "";

    @AutoValue
    public static abstract class Voicemail {

        public static abstract class Builder {
            public abstract Voicemail build();

            public abstract Builder setDurationSeconds(long j);

            public abstract Builder setIsRead(boolean z);

            public abstract Builder setPhoneAccountComponentName(String str);

            public abstract Builder setPhoneNumber(String str);

            public abstract Builder setTimeMillis(long j);

            public abstract Builder setTranscription(String str);
        }

        public static Builder builder() {
            return new AutoValue_VoicemailPopulator_Voicemail.Builder();
        }

        public ContentValues getAsContentValues(Context context) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", Long.valueOf(getTimeMillis()));
            contentValues.put("number", getPhoneNumber());
            contentValues.put("duration", Long.valueOf(getDurationSeconds()));
            contentValues.put("source_package", context.getPackageName());
            contentValues.put("is_read", Integer.valueOf(getIsRead() ? 1 : 0));
            contentValues.put("transcription", getTranscription());
            contentValues.put("subscription_component_name", getPhoneAccountComponentName());
            return contentValues;
        }

        public abstract long getDurationSeconds();

        public abstract boolean getIsRead();

        public abstract String getPhoneAccountComponentName();

        public abstract String getPhoneNumber();

        public abstract long getTimeMillis();

        public abstract String getTranscription();
    }

    static {
        AutoValue_VoicemailPopulator_Voicemail.Builder builder = new AutoValue_VoicemailPopulator_Voicemail.Builder();
        builder.setPhoneNumber("+1-302-6365454");
        builder.setTranscription("Hi, this is a very long voicemail. Please call me back at 650 253 0000. I hope you listen to all of it. This is very important. Hi, this is a very long voicemail. I hope you listen to all of it. It's very important.");
        builder.setPhoneAccountComponentName(componentName);
        builder.setDurationSeconds(10);
        builder.setIsRead(false);
        AutoValue_VoicemailPopulator_Voicemail.Builder builder2 = new AutoValue_VoicemailPopulator_Voicemail.Builder();
        builder2.setPhoneNumber("+1-302-6365454");
        builder2.setTranscription("هزاران دوست کم اند و یک دشمن زیاد");
        builder2.setDurationSeconds(60);
        builder2.setPhoneAccountComponentName(componentName);
        builder2.setIsRead(true);
        AutoValue_VoicemailPopulator_Voicemail.Builder builder3 = new AutoValue_VoicemailPopulator_Voicemail.Builder();
        builder3.setPhoneNumber("");
        builder3.setTranscription("");
        builder3.setDurationSeconds(60);
        builder3.setPhoneAccountComponentName(componentName);
        builder3.setIsRead(true);
        AutoValue_VoicemailPopulator_Voicemail.Builder builder4 = new AutoValue_VoicemailPopulator_Voicemail.Builder();
        builder4.setPhoneNumber("+1-302-6365454");
        builder4.setTranscription("");
        builder4.setDurationSeconds(0);
        builder4.setPhoneAccountComponentName(componentName);
        builder4.setIsRead(true);
        SIMPLE_VOICEMAILS = new Voicemail.Builder[]{builder, builder2, builder3, builder4};
    }

    private VoicemailPopulator() {
    }

    public static void deleteAllVoicemail(Context context) {
        Assert.isWorkerThread();
        context.getContentResolver().delete(VoicemailContract.Voicemails.buildSourceUri(context.getPackageName()), (String) null, (String[]) null);
    }

    public static void enableVoicemail(Context context) {
        PhoneAccountHandle phoneAccountHandle = new PhoneAccountHandle(new ComponentName(context, VoicemailPopulator.class), "ACCOUNT_ID");
        componentName = phoneAccountHandle.getComponentName().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("source_package", phoneAccountHandle.getComponentName().getPackageName());
        int i = Build.VERSION.SDK_INT;
        contentValues.put("source_type", "vvm_type_vvm3");
        contentValues.put("phone_account_component_name", phoneAccountHandle.getComponentName().flattenToString());
        contentValues.put("phone_account_id", phoneAccountHandle.getId());
        contentValues.put("configuration_state", 0);
        contentValues.put("data_channel_state", 0);
        contentValues.put("notification_channel_state", 0);
        context.getContentResolver().insert(VoicemailContract.Status.buildSourceUri(context.getPackageName()), contentValues);
    }

    public static void populateVoicemail(Context context, boolean z) {
        Assert.isWorkerThread();
        enableVoicemail(context);
        List<Voicemail.Builder> arrayList = new ArrayList<>();
        if (z) {
            arrayList.add(SIMPLE_VOICEMAILS[0]);
        } else {
            arrayList = Arrays.asList(SIMPLE_VOICEMAILS);
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            for (Voicemail.Builder builder : arrayList) {
                builder.setTimeMillis(currentTimeMillis);
                context.getContentResolver().insert(VoicemailContract.Voicemails.buildSourceUri(context.getPackageName()), builder.build().getAsContentValues(context));
                currentTimeMillis -= TimeUnit.HOURS.toMillis(2);
            }
        }
    }
}
