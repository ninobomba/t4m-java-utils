package io.github.ninobomba.utils.java.biz.workflow;

import java.util.List;
import java.util.function.Function;

public abstract class WorkflowTemplate<Request, Response> {

    // 1. Template Method - compiler enforces order
    public final Response execute(Request request) {
        // 2. Functional pipeline for pre-steps
        List<Function<Request, Request>> prePipeline = List.of(
                this::sanitize,
                this::validateAndReturn,
                this::authorizeAndReturn
        );

        Request cleanReq = request;
        for (var step : prePipeline) {
            cleanReq = step.apply(cleanReq);
        }

        Response response = doExecute(cleanReq);
        save(response);
        audit(cleanReq, response);
        return response;
    }

    // must return Request to fit Function<Request,Request>
    protected abstract Request sanitize(Request request);

    protected abstract Request validateAndReturn(Request request);

    protected abstract Request authorizeAndReturn(Request request);

    protected abstract Response doExecute(Request request);

    protected void save(Response response) {
    }

    protected void audit(Request request, Response response) {
    }
}