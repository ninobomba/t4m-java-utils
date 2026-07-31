package io.github.ninobomba.utils.java.tests.asserts.api.success;

public interface ITestAssertSuccessOnBasicValidRequest extends
        ITestAssertSuccessOnRequestBasicMethods,
        ITestAssertSuccessOnResponseBasicMethods
{

    boolean assertSuccessOnValidRequest ( );

}