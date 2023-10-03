package p000;

import java.util.ArrayDeque;
import java.util.Deque;

/* renamed from: gur */
/* compiled from: PG */
final class gur {

    /* renamed from: a */
    private final Deque f12086a;

    /* renamed from: b */
    private final hpr f12087b;

    /* renamed from: c */
    private final int f12088c;

    public gur(hpr hpr, int i) {
        ife.m12845a(true, (Object) "maxValues must be greater than zero. Were it equal to zero, the queue would unconditionally (and unhelpfully) preempt all added values.");
        this.f12087b = hpr;
        this.f12088c = i;
        this.f12086a = new ArrayDeque(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo7094b(Object obj) {
        ife.m12898e(obj);
        ArrayDeque arrayDeque = new ArrayDeque(this.f12086a.size());
        while (true) {
            Object poll = this.f12086a.poll();
            if (poll == null) {
                break;
            } else if (arrayDeque.size() >= this.f12088c - 1) {
                this.f12087b.mo1484a(poll);
            } else {
                arrayDeque.add(poll);
            }
        }
        while (true) {
            Object poll2 = arrayDeque.poll();
            if (poll2 != null) {
                this.f12086a.add(poll2);
            } else {
                this.f12086a.add(obj);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7092a() {
        while (true) {
            Object poll = this.f12086a.poll();
            if (poll != null) {
                this.f12087b.mo1484a(poll);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo7095b() {
        return this.f12086a.isEmpty();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo7093a(Object obj) {
        ife.m12898e(obj);
        ArrayDeque arrayDeque = new ArrayDeque(this.f12086a.size());
        while (this.f12086a.peek() != null && this.f12086a.peek() != obj) {
            arrayDeque.add(this.f12086a.poll());
        }
        if (this.f12086a.peek() != obj) {
            while (true) {
                Object pollLast = arrayDeque.pollLast();
                if (pollLast == null) {
                    return false;
                }
                this.f12086a.addFirst(pollLast);
            }
        } else {
            this.f12086a.poll();
            while (true) {
                Object poll = arrayDeque.poll();
                if (poll == null) {
                    return true;
                }
                this.f12087b.mo1484a(poll);
            }
        }
    }
}
