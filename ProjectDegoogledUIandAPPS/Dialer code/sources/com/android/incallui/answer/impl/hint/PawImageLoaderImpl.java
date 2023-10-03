package com.android.incallui.answer.impl.hint;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.storage.StorageComponent;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public final class PawImageLoaderImpl implements PawImageLoader {
    public Drawable loadPayload(Context context) {
        Assert.isNotNull(context);
        SharedPreferences unencryptedSharedPrefs = StorageComponent.get(context).unencryptedSharedPrefs();
        if (!unencryptedSharedPrefs.getBoolean("paw_enabled_with_secret_code", false)) {
            return null;
        }
        int i = unencryptedSharedPrefs.getInt("paw_type", 0);
        if (i == 0) {
            LogUtil.m9i("PawImageLoaderImpl.loadPayload", "paw type not found, rerolling", new Object[0]);
            PawSecretCodeListener.selectPawType(unencryptedSharedPrefs);
            i = unencryptedSharedPrefs.getInt("paw_type", 0);
        }
        if (i == 1) {
            return context.getDrawable(R.drawable.cat_paw);
        }
        if (i == 2) {
            return context.getDrawable(R.drawable.dog_paw);
        }
        throw new AssertionError(GeneratedOutlineSupport.outline5("unknown paw type ", i));
    }
}
