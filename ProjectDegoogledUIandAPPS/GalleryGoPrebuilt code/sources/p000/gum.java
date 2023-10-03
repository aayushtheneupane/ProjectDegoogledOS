package p000;

/* renamed from: gum */
/* compiled from: PG */
public final class gum extends RuntimeException {
    public gum() {
        super("Infinite fetch cycle detected. This occurs when a fetch does not produce a cache hit on the next load from local storage.");
    }
}
