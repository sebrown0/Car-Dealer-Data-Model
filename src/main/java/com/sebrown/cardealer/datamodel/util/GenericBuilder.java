package com.sebrown.cardealer.datamodel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 
 * @author Steve Brown
 *
 * A generic object builder.
 * Use when all the fields of the instantiator object do not have to be set.
 */
public class GenericBuilder <T> {
    private final Supplier<T> instantiator;

    private List<Consumer<T>> instanceOfType = new ArrayList<>();

    public GenericBuilder(Supplier<T> instantiator) {
        this.instantiator = instantiator;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> instantiator) {
        return new GenericBuilder<T>(instantiator);
    }

    public <U> GenericBuilder<T> with(BiConsumer<T, U> consumer, U value) {
        Consumer<T> c = instance -> consumer.accept(instance, value);
        instanceOfType.add(c);
        return this;
    }

    public T build() {
        T value = instantiator.get();
        instanceOfType.forEach(modifier -> modifier.accept(value));
        instanceOfType.clear();
        return value;
    }
}
