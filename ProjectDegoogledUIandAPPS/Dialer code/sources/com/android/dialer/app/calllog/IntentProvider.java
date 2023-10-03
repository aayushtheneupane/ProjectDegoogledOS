package com.android.dialer.app.calllog;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telecom.PhoneAccountHandle;
import com.android.contacts.common.model.Contact;
import com.android.contacts.common.model.ContactLoader;
import com.android.dialer.app.AccountSelectionActivity;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calldetails.OldCallDetailsActivity;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.precall.PreCall;
import com.android.dialer.util.CallUtil;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class IntentProvider {
    public static IntentProvider getAddContactIntentProvider(Uri uri, CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        final Uri uri2 = uri;
        final boolean z2 = z;
        final CharSequence charSequence3 = charSequence;
        final CharSequence charSequence4 = charSequence2;
        final int i2 = i;
        return new IntentProvider() {
            public Intent getClickIntent(Context context) {
                Intent intent;
                Uri uri = uri2;
                Contact parseEncodedContactEntity = uri != null ? ContactLoader.parseEncodedContactEntity(uri) : null;
                if (parseEncodedContactEntity != null) {
                    if (z2) {
                        intent = CallUtil.getNewContactIntent();
                    } else {
                        intent = new Intent("android.intent.action.INSERT_OR_EDIT");
                        intent.setType("vnd.android.cursor.item/contact");
                    }
                    ArrayList<ContentValues> contentValues = parseEncodedContactEntity.getContentValues();
                    if (parseEncodedContactEntity.getDisplayNameSource() >= 35) {
                        intent.putExtra("name", parseEncodedContactEntity.getDisplayName());
                    } else if (parseEncodedContactEntity.getDisplayNameSource() == 30) {
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("data1", parseEncodedContactEntity.getDisplayName());
                        contentValues2.put("mimetype", "vnd.android.cursor.item/organization");
                        contentValues.add(contentValues2);
                    }
                    Iterator<ContentValues> it = contentValues.iterator();
                    while (it.hasNext()) {
                        ContentValues next = it.next();
                        next.remove("last_time_used");
                        next.remove("times_used");
                    }
                    intent.putExtra("data", contentValues);
                    return intent;
                } else if (z2) {
                    return CallUtil.getNewContactIntent(charSequence3, charSequence4, i2);
                } else {
                    return CallUtil.getAddToExistingContactIntent(charSequence3, charSequence4, i2);
                }
            }
        };
    }

    public static IntentProvider getAssistedDialIntentProvider(final String str) {
        return new IntentProvider() {
            public Intent getClickIntent(Context context) {
                return PreCall.getIntent(context, new CallIntentBuilder(str, CallInitiationType$Type.CALL_LOG).setAllowAssistedDial(true));
            }
        };
    }

    public static IntentProvider getCallDetailIntentProvider(final CallDetailsEntries callDetailsEntries, final DialerContact dialerContact, final boolean z, final boolean z2) {
        return new IntentProvider() {
            public Intent getClickIntent(Context context) {
                return OldCallDetailsActivity.newInstance(context, CallDetailsEntries.this, dialerContact, z, z2);
            }
        };
    }

    public static IntentProvider getReturnCallIntentProvider(final String str) {
        return new IntentProvider((PhoneAccountHandle) null) {
            public Intent getClickIntent(Context context) {
                return PreCall.getIntent(context, new CallIntentBuilder(str, CallInitiationType$Type.CALL_LOG).setPhoneAccountHandle(null));
            }

            public Intent getLongClickIntent(Context context) {
                return AccountSelectionActivity.createIntent(context, str, CallInitiationType$Type.CALL_LOG);
            }
        };
    }

    public static IntentProvider getReturnVideoCallIntentProvider(final String str) {
        return new IntentProvider((PhoneAccountHandle) null) {
            public Intent getClickIntent(Context context) {
                return PreCall.getIntent(context, new CallIntentBuilder(r1, CallInitiationType$Type.CALL_LOG).setPhoneAccountHandle(r2).setIsVideoCall(true));
            }
        };
    }

    public static IntentProvider getSendSmsIntentProvider(final String str) {
        return new IntentProvider() {
            public Intent getClickIntent(Context context) {
                return CallUtil.getSendSmsIntent(str);
            }
        };
    }

    public abstract Intent getClickIntent(Context context);

    public Intent getLongClickIntent(Context context) {
        return null;
    }

    public void logInteraction(Context context) {
    }

    public static IntentProvider getReturnVideoCallIntentProvider(final String str, final PhoneAccountHandle phoneAccountHandle) {
        return new IntentProvider() {
            public Intent getClickIntent(Context context) {
                return PreCall.getIntent(context, new CallIntentBuilder(str, CallInitiationType$Type.CALL_LOG).setPhoneAccountHandle(phoneAccountHandle).setIsVideoCall(true));
            }
        };
    }
}
