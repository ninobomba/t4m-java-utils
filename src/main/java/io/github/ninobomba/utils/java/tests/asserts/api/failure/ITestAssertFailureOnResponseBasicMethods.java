package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnResponseBasicMethods {

    void assertSuccessOnResponseBody();
    void assertSuccessOnResponseHeaders();
    void assertSuccessOnResponseStatusCode();
    void assertSuccessOnResponseStatusMessage();
    void assertSuccessOnResponseTime();
    void assertSuccessOnResponseContentType();

}