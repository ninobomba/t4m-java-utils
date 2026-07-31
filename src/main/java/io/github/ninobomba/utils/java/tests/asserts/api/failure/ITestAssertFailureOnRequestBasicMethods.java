package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnRequestBasicMethods {

    boolean assertSuccessOnRequestHeaders();
    boolean assertSuccessOnRequestBody();

    boolean assertSuccessOnRequestQueryParameters();
    boolean assertSuccessOnRequestPathParameters();

    boolean assertSuccessOnRequestMethod();

}