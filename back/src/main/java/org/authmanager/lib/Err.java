package org.authmanager.lib;

import java.util.function.Function;

public final class Err<T, E> extends Result<T, E> {
    private final E error;

    public Err(E error) {
        this.error = error;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public T unwrap() {
        throw new RuntimeException("Tried to unwrap from Err: " + error);
    }

    @Override
    public E unwrapErr() {
        return error;
    }

    @Override
    public <U> Result<U, E> map(Function<T, U> mapper) {
        return new Err<>(error);
    }

    @Override
    public <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapper) {
        return new Err<>(error);
    }
}
