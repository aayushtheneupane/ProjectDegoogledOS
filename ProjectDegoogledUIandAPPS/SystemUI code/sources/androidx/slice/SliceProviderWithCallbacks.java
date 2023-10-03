package androidx.slice;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import androidx.remotecallback.CallbackBase;
import androidx.remotecallback.CallbackHandlerRegistry;
import androidx.remotecallback.CallbackReceiver;
import androidx.slice.SliceProviderWithCallbacks;

public abstract class SliceProviderWithCallbacks<T extends SliceProviderWithCallbacks> extends SliceProvider implements CallbackReceiver<T>, CallbackBase<T> {
    String mAuthority;

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        this.mAuthority = providerInfo.authority;
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        if (!"androidx.remotecallback.method.PROVIDER_CALLBACK".equals(str)) {
            return super.call(str, str2, bundle);
        }
        CallbackHandlerRegistry.sInstance.invokeCallback(getContext(), this, bundle);
        return null;
    }
}
