package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnResponseBasicMethods {

    boolean assertSuccessOnResponseBody();
    boolean assertSuccessOnResponseHeaders();
    boolean assertSuccessOnResponseStatusCode();
    boolean assertSuccessOnResponseStatusMessage();
    boolean assertSuccessOnResponseTime();
    boolean assertSuccessOnResponseContentType();

}