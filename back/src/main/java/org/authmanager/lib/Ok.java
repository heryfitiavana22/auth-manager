package org.authmanager.lib;

import java.util.function.Function;

public final class Ok<T, E> extends Result<T, E> {
    private final T value;

    public Ok(T value) {
        this.value = value;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public T unwrap() {
        return value;
    }

    @Override
    public E unwrapErr() {
        throw new RuntimeException("Tried to unwrapErr from Ok");
    }

    @Override
    public <U> Result<U, E> map(Function<T, U> mapper) {
        return new Ok<>(mapper.apply(value));
    }

    @Override
    public <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapper) {
        return mapper.apply(value);
    }
}
