package com.google.common.collect;

interface Constraint<E> {
    E checkElement(E e);

    String toString();
}
