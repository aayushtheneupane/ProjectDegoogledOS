package com.android.dialer.calllog;

import com.android.dialer.calllog.datasources.DataSources;
import com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource;
import com.google.common.collect.ImmutableList;

class CallLogModule$1 implements DataSources {
    final /* synthetic */ ImmutableList val$allDataSources;
    final /* synthetic */ SystemCallLogDataSource val$systemCallLogDataSource;

    CallLogModule$1(SystemCallLogDataSource systemCallLogDataSource, ImmutableList immutableList) {
        this.val$systemCallLogDataSource = systemCallLogDataSource;
        this.val$allDataSources = immutableList;
    }
}
