package io.github.ninobomba.utils.java.tests.asserts.api.success;

public interface ITestAssertSuccessOnRequestBasicMethods {

    boolean assertSuccessOnRequestHeaders();
    boolean assertSuccessOnRequestBody();

    boolean assertSuccessOnRequestQueryParameters();
    boolean assertSuccessOnRequestPathParameters();

    boolean assertSuccessOnRequestMethod();

}