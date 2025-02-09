package org.authmanager.lib;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Result<T, E> {
    public abstract boolean isOk();

    public abstract boolean isErr();

    public abstract T unwrap() throws RuntimeException;

    public abstract E unwrapErr() throws RuntimeException;

    public abstract <U> Result<U, E> map(Function<T, U> mapper);

    public abstract <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapper);

    public static <T, E> Result<T, E> ok(T value) {
        return new Ok<>(value);
    }

    public static <T, E> Result<T, E> err(E error) {
        return new Err<>(error);
    }

    public static <T, E> Result<T, E> tryCatch(Supplier<T> supplier, Function<Exception, E> errorHandler) {
        try {
            return ok(supplier.get());
        } catch (Exception e) {
            return err(errorHandler.apply(e));
        }
    }
}