package com.android.dialer.calllog;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource;
import com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource;
import com.android.dialer.calllog.datasources.voicemail.VoicemailDataSource;
import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallLogModule_ProvideCallLogDataSourcesFactory implements Factory<DataSources> {
    private final Provider<PhoneLookupDataSource> phoneLookupDataSourceProvider;
    private final Provider<SystemCallLogDataSource> systemCallLogDataSourceProvider;
    private final Provider<VoicemailDataSource> voicemailDataSourceProvider;

    public CallLogModule_ProvideCallLogDataSourcesFactory(Provider<SystemCallLogDataSource> provider, Provider<PhoneLookupDataSource> provider2, Provider<VoicemailDataSource> provider3) {
        this.systemCallLogDataSourceProvider = provider;
        this.phoneLookupDataSourceProvider = provider2;
        this.voicemailDataSourceProvider = provider3;
    }

    public Object get() {
        SystemCallLogDataSource systemCallLogDataSource = this.systemCallLogDataSourceProvider.get();
        CallLogModule$1 callLogModule$1 = new CallLogModule$1(systemCallLogDataSource, ImmutableList.m77of(systemCallLogDataSource, this.phoneLookupDataSourceProvider.get(), this.voicemailDataSourceProvider.get()));
        R$style.checkNotNull1(callLogModule$1, "Cannot return null from a non-@Nullable @Provides method");
        return callLogModule$1;
    }
}
