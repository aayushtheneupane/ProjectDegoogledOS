package com.android.voicemail.impl.scheduling;

import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import com.android.voicemail.impl.VvmLog;

final class Tasks {

    static final class TaskCreationException extends Exception {
        TaskCreationException(Throwable th) {
            super(th);
        }
    }

    public static Task createTask(Context context, Bundle bundle) throws TaskCreationException {
        bundle.setClassLoader(context.getClassLoader());
        try {
            String string = bundle.getString("extra_class_name");
            VvmLog.m45i("Task.createTask", "create task:" + string);
            if (string != null) {
                try {
                    Task task = (Task) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    task.onCreate(context, bundle);
                    return task;
                } catch (ReflectiveOperationException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                throw new IllegalArgumentException("EXTRA_CLASS_NAME expected");
            }
        } catch (BadParcelableException e2) {
            throw new TaskCreationException(e2);
        }
    }
}
