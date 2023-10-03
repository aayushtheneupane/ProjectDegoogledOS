package com.android.dialer.app.filterednumber;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.support.p002v7.appcompat.R$style;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.blocking.BlockNumberDialogFragment;
import com.android.dialer.contactphoto.ContactPhotoManager;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;

public class BlockedNumbersAdapter extends NumbersAdapter {
    private BlockedNumbersAdapter(Context context, FragmentManager fragmentManager, ContactInfoHelper contactInfoHelper, ContactPhotoManager contactPhotoManager) {
        super(context, fragmentManager, contactInfoHelper, contactPhotoManager);
    }

    public static BlockedNumbersAdapter newBlockedNumbersAdapter(Context context, FragmentManager fragmentManager) {
        return new BlockedNumbersAdapter(context, fragmentManager, new ContactInfoHelper(context, R$style.getCurrentCountryIso(context)), ContactPhotoManager.getInstance(context));
    }

    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final Integer valueOf = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
        String string = cursor.getString(cursor.getColumnIndex("country_iso"));
        String string2 = cursor.getString(cursor.getColumnIndex("number"));
        final String str = string2;
        final String str2 = string;
        final Context context2 = context;
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BlockNumberDialogFragment.show(valueOf, str, str2, PhoneNumberHelper.formatNumber(BlockedNumbersAdapter.this.getContext(), str, str2), Integer.valueOf(R.id.blocked_numbers_activity_container), BlockedNumbersAdapter.this.getFragmentManager(), new BlockNumberDialogFragment.Callback() {
                    public void onChangeFilteredNumberUndo() {
                    }

                    public void onFilterNumberSuccess() {
                    }

                    public void onUnfilterNumberSuccess() {
                        ((LoggingBindingsStub) Logger.get(context2)).logInteraction(InteractionEvent$Type.UNBLOCK_NUMBER_MANAGEMENT_SCREEN);
                    }
                });
            }
        });
        updateView(view, string2, string);
    }

    public boolean isEmpty() {
        return false;
    }
}
