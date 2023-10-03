package com.android.dialer.databasepopulator;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.databasepopulator.AutoValue_ContactsPopulator_Contact;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ContactsPopulator {
    private static final Contact[] SIMPLE_CONTACTS;

    @AutoValue
    static abstract class Contact {

        static abstract class Builder {
            private final List<Email> emails = new ArrayList();
            private final List<PhoneNumber> phoneNumbers = new ArrayList();

            Builder() {
            }

            private static ByteArrayOutputStream getPhotoStreamWithColor(int i) {
                Bitmap createBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(Color.argb(255, 76, 156, 35));
                Paint paint = new Paint();
                paint.setColor(i);
                paint.setStyle(Paint.Style.FILL);
                float f = (float) 150;
                canvas.drawCircle(f, f, (float) 100, paint);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createBitmap.compress(Bitmap.CompressFormat.PNG, 75, byteArrayOutputStream);
                return byteArrayOutputStream;
            }

            /* access modifiers changed from: package-private */
            public Builder addEmail(Email email) {
                this.emails.add(email);
                setEmails(this.emails);
                return this;
            }

            /* access modifiers changed from: package-private */
            public Builder addPhoneNumber(PhoneNumber phoneNumber) {
                this.phoneNumbers.add(phoneNumber);
                setPhoneNumbers(this.phoneNumbers);
                return this;
            }

            /* access modifiers changed from: package-private */
            public abstract Contact build();

            /* access modifiers changed from: package-private */
            public abstract Builder setAccountName(String str);

            /* access modifiers changed from: package-private */
            public Builder setBluePhoto() {
                setPhotoStream(getPhotoStreamWithColor(Color.rgb(0, 170, 230)));
                return this;
            }

            /* access modifiers changed from: package-private */
            public abstract Builder setEmails(List<Email> list);

            /* access modifiers changed from: package-private */
            public abstract Builder setIsStarred(boolean z);

            /* access modifiers changed from: package-private */
            public abstract Builder setName(String str);

            /* access modifiers changed from: package-private */
            public Builder setOrangePhoto() {
                setPhotoStream(getPhotoStreamWithColor(Color.rgb(234, 149, 0)));
                return this;
            }

            /* access modifiers changed from: package-private */
            public abstract Builder setPhoneNumbers(List<PhoneNumber> list);

            /* access modifiers changed from: package-private */
            public abstract Builder setPhotoStream(ByteArrayOutputStream byteArrayOutputStream);

            /* access modifiers changed from: package-private */
            public abstract Builder setPinned(int i);

            /* access modifiers changed from: package-private */
            public Builder setPurplePhoto() {
                setPhotoStream(getPhotoStreamWithColor(Color.rgb(153, 90, 160)));
                return this;
            }

            /* access modifiers changed from: package-private */
            public Builder setRedPhoto() {
                setPhotoStream(getPhotoStreamWithColor(Color.rgb(227, 51, 28)));
                return this;
            }
        }

        Contact() {
        }

        static Builder builder() {
            AutoValue_ContactsPopulator_Contact.Builder builder = new AutoValue_ContactsPopulator_Contact.Builder();
            builder.setAccountType("com.google");
            builder.setAccountName("foo@example");
            builder.setPinned(0);
            builder.setIsStarred(false);
            builder.setPhoneNumbers(new ArrayList());
            builder.setEmails(new ArrayList());
            return builder;
        }

        /* access modifiers changed from: package-private */
        public abstract String getAccountName();

        /* access modifiers changed from: package-private */
        public abstract String getAccountType();

        /* access modifiers changed from: package-private */
        public abstract List<Email> getEmails();

        /* access modifiers changed from: package-private */
        public abstract boolean getIsStarred();

        /* access modifiers changed from: package-private */
        public abstract String getName();

        /* access modifiers changed from: package-private */
        public abstract List<PhoneNumber> getPhoneNumbers();

        /* access modifiers changed from: package-private */
        public abstract ByteArrayOutputStream getPhotoStream();

        /* access modifiers changed from: package-private */
        public abstract int getPinned();
    }

    static class Email {
        public final String label = "simulator email";
        public final int type = 2;
        public final String value;

        Email(String str) {
            this.value = str;
        }
    }

    static class PhoneNumber {
        public final String label = "simulator phone number";
        public final int type;
        public final String value;

        PhoneNumber(String str, int i) {
            this.value = str;
            this.type = i;
        }
    }

    static {
        Contact.Builder builder = Contact.builder();
        builder.setName("Michelangelo");
        builder.addPhoneNumber(new PhoneNumber("+1-302-6365454", 2));
        builder.addEmail(new Email("m@example.com"));
        builder.setIsStarred(true);
        builder.setPinned(1);
        builder.setOrangePhoto();
        Contact.Builder builder2 = Contact.builder();
        builder2.setName("Leonardo da Vinci");
        builder2.addPhoneNumber(new PhoneNumber("(425) 739-5600", 2));
        builder2.addEmail(new Email("l@example.com"));
        builder2.setIsStarred(true);
        builder2.setPinned(2);
        builder2.setBluePhoto();
        Contact.Builder builder3 = Contact.builder();
        builder3.setName("Raphael");
        builder3.addPhoneNumber(new PhoneNumber("+44 (0) 20 7031 3000", 2));
        builder3.addEmail(new Email("r@example.com"));
        builder3.setIsStarred(true);
        builder3.setPinned(3);
        builder3.setRedPhoto();
        Contact.Builder builder4 = Contact.builder();
        builder4.setName("Donatello di Niccolò di Betto Bardi");
        builder4.addPhoneNumber(new PhoneNumber("+1-650-2530000", 1));
        builder4.addPhoneNumber(new PhoneNumber("+1 404-487-9000", 3));
        builder4.addPhoneNumber(new PhoneNumber("+61 2 9374 4001", 5));
        builder4.setIsStarred(true);
        builder4.setPinned(4);
        builder4.setPurplePhoto();
        Contact.Builder builder5 = Contact.builder();
        builder5.setName("Splinter");
        builder5.addPhoneNumber(new PhoneNumber("+1-650-2530000", 1));
        builder5.addPhoneNumber(new PhoneNumber("+1 303-245-0086;123,456", 3));
        builder5.setBluePhoto();
        Contact.Builder builder6 = Contact.builder();
        builder6.setName("スパイク・スピーゲル");
        builder6.addPhoneNumber(new PhoneNumber("+33 (0)1 42 68 53 00", 2));
        builder6.setBluePhoto();
        Contact.Builder builder7 = Contact.builder();
        builder7.setName("עקב אריה טברסק");
        builder7.addPhoneNumber(new PhoneNumber("+33 (0)1 42 68 53 00", 2));
        builder7.setBluePhoto();
        Contact.Builder builder8 = Contact.builder();
        builder8.setName("سلام دنیا");
        builder8.addPhoneNumber(new PhoneNumber("+971 4 4509500", 2));
        builder8.setBluePhoto();
        Contact.Builder builder9 = Contact.builder();
        builder9.addPhoneNumber(new PhoneNumber("+55-31-2128-6800", 2));
        builder9.setBluePhoto();
        Contact.Builder builder10 = Contact.builder();
        builder10.addPhoneNumber(new PhoneNumber("611", 2));
        Contact.Builder builder11 = Contact.builder();
        builder11.setName("Anonymous");
        builder11.addPhoneNumber(new PhoneNumber("*86 512-343-5283", 2));
        builder11.setBluePhoto();
        Contact.Builder builder12 = Contact.builder();
        builder12.setName("No Phone Number");
        builder12.addEmail(new Email("no@example.com"));
        builder12.setIsStarred(true);
        builder12.setBluePhoto();
        SIMPLE_CONTACTS = new Contact[]{builder.build(), builder2.build(), builder3.build(), builder4.build(), builder5.build(), builder6.build(), builder7.build(), builder8.build(), builder9.build(), builder10.build(), builder11.build(), builder12.build()};
    }

    public static void deleteAllContacts(Context context) {
        Assert.isWorkerThread();
        try {
            context.getContentResolver().applyBatch("com.android.contacts", new ArrayList(Arrays.asList(new ContentProviderOperation[]{ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI).build()})));
        } catch (OperationApplicationException | RemoteException e) {
            throw new AssertionError(GeneratedOutlineSupport.outline6("failed to delete contacts: ", e));
        }
    }

    public static void populateContacts(Context context, boolean z) {
        Assert.isWorkerThread();
        ArrayList arrayList = new ArrayList();
        List<Contact> arrayList2 = new ArrayList<>();
        if (z) {
            arrayList2.add(SIMPLE_CONTACTS[0]);
        } else {
            arrayList2 = Arrays.asList(SIMPLE_CONTACTS);
        }
        for (Contact contact : arrayList2) {
            int size = arrayList.size();
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", contact.getAccountType()).withValue("account_name", contact.getAccountName()).withValue("starred", Integer.valueOf(contact.getIsStarred() ? 1 : 0)).withValue("pinned", Integer.valueOf(contact.getIsStarred() ? contact.getPinned() : 0)).withYieldAllowed(true).build());
            if (!TextUtils.isEmpty(contact.getName())) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", size).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", contact.getName()).build());
            }
            if (contact.getPhotoStream() != null) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", size).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", contact.getPhotoStream().toByteArray()).build());
            }
            for (PhoneNumber next : contact.getPhoneNumbers()) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", size).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", next.value).withValue("data2", Integer.valueOf(next.type)).withValue("data3", next.label).build());
            }
            for (Email next2 : contact.getEmails()) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", size).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", next2.value).withValue("data2", Integer.valueOf(next2.type)).withValue("data3", next2.label).build());
            }
        }
        try {
            context.getContentResolver().applyBatch("com.android.contacts", arrayList);
        } catch (OperationApplicationException | RemoteException e) {
            throw new AssertionError(GeneratedOutlineSupport.outline6("error adding contacts: ", e));
        }
    }
}
