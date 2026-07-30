package io.github.ninobomba.utils.java.tests.asserts.api.failure;

public interface ITestAssertFailureOnBasicInvalidRequest extends
        ITestAssertFailureOnRequestBasicMethods,
        ITestAssertFailureOnResponseBasicMethods
{
    void assertFailureOnInvalidRequest();
}