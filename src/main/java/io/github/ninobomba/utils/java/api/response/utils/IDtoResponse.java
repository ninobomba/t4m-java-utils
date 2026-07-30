package io.github.ninobomba.utils.java.api.response.utils;

import io.github.ninobomba.utils.java.constants.processes.BizProcessResponse;
import io.github.ninobomba.utils.java.data.mapper.ITransitionData.DtoResponse;

import java.util.Objects;

public final class IDtoResponse {

    private IDtoResponse() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static DtoResponse<String> failure(String message) {
        return new DtoResponse<>(
                BizProcessResponse.Status.FAILURE,
                message
        );
    }

    public static DtoResponse<String> error(String message) {
        return new DtoResponse<>(
                BizProcessResponse.Status.ERROR,
                message
        );
    }

    public static DtoResponse<String> success(String message) {
        return new DtoResponse<>(
                BizProcessResponse.Status.SUCCESS,
                message
        );
    }

    public static <T> DtoResponse<T> reply(BizProcessResponse.Status status, T payload) {
        return new DtoResponse<>(
                Objects.requireNonNull(status, "status must not be null"),
                payload
        );
    }
}