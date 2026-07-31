package io.github.ninobomba.utils.java.tests.asserts.api.success;

public interface ITestAssertSuccessOnRequestExtendedMethods {

    boolean assertSuccessOnRequestFormParameters();

    boolean assertSuccessOnRequestMultipartFormData();
    boolean assertSuccessOnRequestJsonBody();
    boolean assertSuccessOnRequestXmlBody();
    boolean assertSuccessOnRequestTextBody();
    boolean assertSuccessOnRequestHtmlBody();
    boolean assertSuccessOnRequestBinaryBody();
    boolean assertSuccessOnRequestCustomBody();

    boolean assertSuccessOnRequestMethod();
    boolean assertSuccessOnRequestCookies();
    boolean assertSuccessOnRequestUrl();

}