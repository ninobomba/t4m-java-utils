package io.github.ninobomba.utils.java.tests.asserts.api.success;

public interface ITestAssertSuccessOnRequestBasicMethods {

    void assertSuccessOnRequestHeaders();
    void assertSuccessOnRequestBodY();

    void assertSuccessOnRequestQueryParameters();
    void assertSuccessOnRequestPathParameters();

    void assertSuccessOnRequestMethod();

}