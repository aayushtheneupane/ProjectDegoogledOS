package com.android.voicemail.impl.scheduling;

import android.content.Context;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import java.util.Objects;

public interface Task {

    public static class TaskId {

        /* renamed from: id */
        public final int f53id;
        public final PhoneAccountHandle phoneAccountHandle;

        public TaskId(int i, PhoneAccountHandle phoneAccountHandle2) {
            this.f53id = i;
            this.phoneAccountHandle = phoneAccountHandle2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TaskId)) {
                return false;
            }
            TaskId taskId = (TaskId) obj;
            if (this.f53id != taskId.f53id || !this.phoneAccountHandle.equals(taskId.phoneAccountHandle)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.f53id), this.phoneAccountHandle});
        }
    }

    TaskId getId();

    long getReadyInMilliSeconds();

    void onBeforeExecute();

    void onCompleted();

    void onCreate(Context context, Bundle bundle);

    void onDuplicatedTaskAdded(Task task);

    void onExecuteInBackgroundThread();

    void onRestore(Bundle bundle);

    Bundle toBundle();
}
