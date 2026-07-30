package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnRequestBasicMethods {

    void assertSuccessOnRequestHeaders();
    void assertSuccessOnRequestBodY();

    void assertSuccessOnRequestQueryParameters();
    void assertSuccessOnRequestPathParameters();

    void assertSuccessOnRequestMethod();

}