package io.github.ninobomba.utils.java.tests.asserts.api.success;

public interface ITestAssertSuccessOnResponseBasicMethods {

    boolean assertSuccessOnResponseBody();
    boolean assertSuccessOnResponseHeaders();
    boolean assertSuccessOnResponseStatusCode();
    boolean assertSuccessOnResponseStatusMessage();
    boolean assertSuccessOnResponseTime();
    boolean assertSuccessOnResponseContentType();

}