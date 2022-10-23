package com.example.demo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface InmutableList {

    static <E> List<E> of() {
        return Collections.emptyList();
    }

    static <E> List<E> of(E e1) {
        return Collections.singletonList(e1);
    }

    static <E> List<E> of(E e1, E e2) {
        return Collections.unmodifiableList(Arrays.asList(e1, e2));
    }

}