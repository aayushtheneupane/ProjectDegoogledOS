package com.android.voicemail.impl;

import android.content.Context;
import android.os.Build;
import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailPermissionHelper;
import com.android.voicemail.stub.StubVoicemailClient;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class VoicemailModule_ProvideVoicemailClientFactory implements Factory<VoicemailClient> {
    private final Provider<Context> contextProvider;

    public VoicemailModule_ProvideVoicemailClientFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public Object get() {
        Object obj;
        Context context = this.contextProvider.get();
        int i = Build.VERSION.SDK_INT;
        if (!VoicemailPermissionHelper.getMissingPermissions(context).isEmpty()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("missing permissions ");
            outline13.append(VoicemailPermissionHelper.getMissingPermissions(context));
            VvmLog.m45i("VoicemailModule.provideVoicemailClient", outline13.toString());
            obj = new StubVoicemailClient();
        } else {
            VvmLog.m45i("VoicemailModule.provideVoicemailClient", "providing VoicemailClientImpl");
            obj = new VoicemailClientImpl();
        }
        R$style.checkNotNull1(obj, "Cannot return null from a non-@Nullable @Provides method");
        return obj;
    }
}
