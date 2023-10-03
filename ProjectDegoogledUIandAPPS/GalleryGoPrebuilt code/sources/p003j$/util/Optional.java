package p003j$.util;

import java.util.NoSuchElementException;
import p003j$.util.function.Consumer;
import p003j$.util.function.Function;
import p003j$.util.function.Predicate;
import p003j$.util.function.Supplier;

/* renamed from: j$.util.Optional */
public final class Optional {
    private static final Optional EMPTY = new Optional();
    private final Object value;

    private Optional() {
        this.value = null;
    }

    public static Optional empty() {
        return EMPTY;
    }

    private Optional(Object obj) {
        obj.getClass();
        this.value = obj;
    }

    /* renamed from: of */
    public static Optional m16285of(Object obj) {
        return new Optional(obj);
    }

    public static Optional ofNullable(Object obj) {
        return obj == null ? empty() : m16285of(obj);
    }

    public Object get() {
        Object obj = this.value;
        if (obj != null) {
            return obj;
        }
        throw new NoSuchElementException("No value present");
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void ifPresent(Consumer consumer) {
        Object obj = this.value;
        if (obj != null) {
            consumer.accept(obj);
        }
    }

    public Optional filter(Predicate predicate) {
        predicate.getClass();
        if (!isPresent()) {
            return this;
        }
        return predicate.test(this.value) ? this : empty();
    }

    public Optional map(Function function) {
        function.getClass();
        if (!isPresent()) {
            return empty();
        }
        return ofNullable(function.apply(this.value));
    }

    public Optional flatMap(Function function) {
        function.getClass();
        if (!isPresent()) {
            return empty();
        }
        Optional optional = (Optional) function.apply(this.value);
        optional.getClass();
        return optional;
    }

    public Object orElse(Object obj) {
        Object obj2 = this.value;
        return obj2 != null ? obj2 : obj;
    }

    public Object orElseGet(Supplier supplier) {
        Object obj = this.value;
        return obj != null ? obj : supplier.get();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        return Objects.equals(this.value, ((Optional) obj).value);
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        Object obj = this.value;
        if (obj == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{obj});
    }
}
