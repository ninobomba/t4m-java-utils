package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnRequestExtendedMethods {

    void assertSuccessOnRequestFormParameters();

    void assertSuccessOnRequestMultipartFormData();
    void assertSuccessOnRequestJsonBody();
    void assertSuccessOnRequestXmlBody();
    void assertSuccessOnRequestTextBody();
    void assertSuccessOnRequestHtmlBody();
    void assertSuccessOnRequestBinaryBody();
    void assertSuccessOnRequestCustomBody();

    void assertSuccessOnRequestMethod();
    void assertSuccessOnRequestCookies();
    void assertSuccessOnRequestUrl();

}