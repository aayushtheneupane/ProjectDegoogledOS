package com.android.dialer.speeddial.loader;

import android.provider.ContactsContract;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.google.auto.value.AutoValue;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Objects;

@AutoValue
public abstract class SpeedDialUiItem {
    private static final String[] PHONE_PROJECTION = {"lookup", "contact_id", "display_name", "starred", "data1", "data2", "data3", "photo_id", "photo_uri", "carrier_presence"};
    private static final String[] PHONE_PROJECTION_ALTERNATIVE = {"lookup", "contact_id", "display_name_alt", "starred", "data1", "data2", "data3", "photo_id", "photo_uri", "carrier_presence"};

    public static abstract class Builder {
        public abstract SpeedDialUiItem build();

        public abstract Builder setChannels(ImmutableList<SpeedDialEntry.Channel> immutableList);

        public abstract Builder setContactId(long j);

        public abstract Builder setDefaultChannel(SpeedDialEntry.Channel channel);

        public abstract Builder setIsStarred(boolean z);

        public abstract Builder setLookupKey(String str);

        public abstract Builder setName(String str);

        public abstract Builder setPhotoId(long j);

        public abstract Builder setPhotoUri(String str);

        public abstract Builder setPinnedPosition(Optional<Integer> optional);

        public abstract Builder setSpeedDialEntryId(Long l);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.dialer.speeddial.loader.SpeedDialUiItem fromCursor(android.content.res.Resources r12, android.database.Cursor r13, boolean r14) {
        /*
            java.lang.String r0 = "fromCursor"
            android.os.Trace.beginSection(r0)
            r0 = 0
            r1 = 1
            if (r13 == 0) goto L_0x000b
            r2 = r1
            goto L_0x000c
        L_0x000b:
            r2 = r0
        L_0x000c:
            com.android.dialer.common.Assert.checkArgument(r2)
            int r2 = r13.getCount()
            if (r2 == 0) goto L_0x0017
            r2 = r1
            goto L_0x0018
        L_0x0017:
            r2 = r0
        L_0x0018:
            com.android.dialer.common.Assert.checkArgument(r2)
            java.lang.String r2 = r13.getString(r0)
            com.android.dialer.speeddial.loader.AutoValue_SpeedDialUiItem$Builder r3 = new com.android.dialer.speeddial.loader.AutoValue_SpeedDialUiItem$Builder
            r3.<init>()
            com.google.common.collect.ImmutableList r4 = com.google.common.collect.ImmutableList.m74of()
            r3.setChannels(r4)
            com.google.common.base.Optional r4 = com.google.common.base.Optional.absent()
            r3.setPinnedPosition(r4)
            r3.setLookupKey(r2)
            long r4 = r13.getLong(r1)
            r3.setContactId(r4)
            r4 = 2
            java.lang.String r5 = r13.getString(r4)
            r3.setName(r5)
            r5 = 3
            int r5 = r13.getInt(r5)
            if (r5 != r1) goto L_0x004d
            r5 = r1
            goto L_0x004e
        L_0x004d:
            r5 = r0
        L_0x004e:
            r3.setIsStarred(r5)
            r5 = 7
            long r5 = r13.getLong(r5)
            r3.setPhotoId(r5)
            r5 = 8
            java.lang.String r6 = r13.getString(r5)
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            java.lang.String r7 = ""
            if (r6 == 0) goto L_0x0069
            r5 = r7
            goto L_0x006d
        L_0x0069:
            java.lang.String r5 = r13.getString(r5)
        L_0x006d:
            r3.setPhotoUri(r5)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            android.util.ArraySet r6 = new android.util.ArraySet
            r6.<init>()
        L_0x007a:
            r8 = 4
            java.lang.String r8 = r13.getString(r8)
            boolean r9 = r6.add(r8)
            if (r9 != 0) goto L_0x0086
            goto L_0x00d4
        L_0x0086:
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel$Builder r9 = com.android.dialer.speeddial.database.SpeedDialEntry.Channel.builder()
            r9.setNumber(r8)
            r8 = 5
            int r10 = r13.getInt(r8)
            r9.setPhoneType(r10)
            int r8 = r13.getInt(r8)
            r10 = 6
            java.lang.String r10 = r13.getString(r10)
            if (r8 != 0) goto L_0x00a8
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 == 0) goto L_0x00a8
            r8 = r7
            goto L_0x00ae
        L_0x00a8:
            java.lang.CharSequence r8 = android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(r12, r8, r10)
            java.lang.String r8 = (java.lang.String) r8
        L_0x00ae:
            r9.setLabel(r8)
            r9.setTechnology(r1)
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r8 = r9.build()
            r5.add(r8)
            if (r14 == 0) goto L_0x00d4
            r9 = 9
            int r9 = r13.getInt(r9)
            r9 = r9 & r1
            if (r9 != r1) goto L_0x00d4
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel$Builder r8 = r8.toBuilder()
            r8.setTechnology(r4)
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r8 = r8.build()
            r5.add(r8)
        L_0x00d4:
            boolean r8 = r13.moveToNext()
            if (r8 == 0) goto L_0x00e4
            java.lang.String r8 = r13.getString(r0)
            boolean r8 = java.util.Objects.equals(r2, r8)
            if (r8 != 0) goto L_0x007a
        L_0x00e4:
            com.google.common.collect.ImmutableList r12 = com.google.common.collect.ImmutableList.copyOf(r5)
            r3.setChannels(r12)
            android.os.Trace.endSection()
            com.android.dialer.speeddial.loader.SpeedDialUiItem r12 = r3.build()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.loader.SpeedDialUiItem.fromCursor(android.content.res.Resources, android.database.Cursor, boolean):com.android.dialer.speeddial.loader.SpeedDialUiItem");
    }

    public static String[] getPhoneProjection(boolean z) {
        return z ? PHONE_PROJECTION : PHONE_PROJECTION_ALTERNATIVE;
    }

    public SpeedDialEntry buildSpeedDialEntry() {
        SpeedDialEntry.Builder builder = SpeedDialEntry.builder();
        builder.setId(speedDialEntryId());
        builder.setPinnedPosition(pinnedPosition());
        builder.setLookupKey(lookupKey());
        builder.setContactId(contactId());
        builder.setDefaultChannel(defaultChannel());
        return builder.build();
    }

    public abstract ImmutableList<SpeedDialEntry.Channel> channels();

    public abstract long contactId();

    public abstract SpeedDialEntry.Channel defaultChannel();

    public SpeedDialEntry.Channel getDefaultVideoChannel() {
        if (defaultChannel() == null) {
            return null;
        }
        if (defaultChannel().isVideoTechnology()) {
            return defaultChannel();
        }
        if (channels().size() == 1) {
            return null;
        }
        for (int i = 0; i < channels().size() - 1; i++) {
            if (Objects.equals(defaultChannel(), channels().get(i))) {
                SpeedDialEntry.Channel channel = channels().get(i + 1);
                if (channel.isVideoTechnology()) {
                    return channel;
                }
                return null;
            }
        }
        throw new IllegalStateException("channels() doesn't contain defaultChannel().");
    }

    public SpeedDialEntry.Channel getDefaultVoiceChannel() {
        if (channels().size() == 1) {
            return channels().get(0);
        }
        if (defaultChannel() == null) {
            return null;
        }
        if (!defaultChannel().isVideoTechnology()) {
            return defaultChannel();
        }
        UnmodifiableIterator<SpeedDialEntry.Channel> it = channels().iterator();
        while (it.hasNext()) {
            SpeedDialEntry.Channel next = it.next();
            if (next.number().equals(defaultChannel().number()) && next.technology() == 1) {
                return next;
            }
        }
        return null;
    }

    public PhotoInfo getPhotoInfo() {
        PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
        newBuilder.setPhotoId(photoId());
        newBuilder.setPhotoUri(photoUri());
        newBuilder.setName(name());
        newBuilder.setIsVideo(defaultChannel() != null && defaultChannel().isVideoTechnology());
        newBuilder.setLookupUri(ContactsContract.Contacts.getLookupUri(contactId(), lookupKey()).toString());
        return (PhotoInfo) newBuilder.build();
    }

    public abstract boolean isStarred();

    public abstract String lookupKey();

    public abstract String name();

    public abstract long photoId();

    public abstract String photoUri();

    public abstract Optional<Integer> pinnedPosition();

    public abstract Long speedDialEntryId();

    public abstract Builder toBuilder();
}
