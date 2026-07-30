package io.github.ninobomba.utils.java.tests.asserts.inputs.failure;

public interface ITestAsserFailureOnInput extends
        ITestAssertFailureOnInvalidFormat,
        ITestAssertFailureOnInvalidMaxInput,
        ITestAssertFailureOnInvalidMinInput,
        ITestAsserFailureOnInvalidInput
{

    void assertFailureOnInvalidInput(String input);

}