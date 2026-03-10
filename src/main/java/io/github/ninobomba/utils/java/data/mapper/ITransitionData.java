package io.github.ninobomba.utils.java.data.mapper;

import io.github.ninobomba.utils.java.constants.processes.BizProcessResponse;

public sealed interface ITransitionData<T> {

    BizProcessResponse.Status status();

    T payload();

    default BizProcessResponse response() {
        return BizProcessResponse.builder()
                .status(status())
                .build();
    }

    record DtoResponse<T>(
            BizProcessResponse.Status status,
            T payload
    ) implements ITransitionData<T> {
    }

    record PersistenceResponse<T>(
            BizProcessResponse.Status status,
            T payload
    ) implements ITransitionData<T> {
    }
}
