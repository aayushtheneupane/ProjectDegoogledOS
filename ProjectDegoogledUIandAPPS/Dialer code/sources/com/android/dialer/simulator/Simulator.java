package com.android.dialer.simulator;

import android.support.p002v7.app.AppCompatActivity;
import android.view.ActionProvider;
import java.util.Objects;

public interface Simulator {
    ActionProvider getActionProvider(AppCompatActivity appCompatActivity);

    boolean shouldShow();

    public static class Event {
        public final String data1;
        public final String data2;
        public final int type;

        public Event(int i) {
            this.type = i;
            this.data1 = null;
            this.data2 = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Event)) {
                return false;
            }
            Event event = (Event) obj;
            if (this.type != event.type || !Objects.equals(this.data1, event.data1) || !Objects.equals(this.data2, event.data2)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.type), this.data1, this.data2});
        }

        public Event(int i, String str, String str2) {
            this.type = i;
            this.data1 = str;
            this.data2 = str2;
        }
    }
}
