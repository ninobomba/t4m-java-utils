package io.github.ninobomba.utils.java.tests.asserts.inputs.failure;

public interface ITestAssertFailureOnInput extends
        ITestAssertFailureOnInvalidFormat,
        ITestAssertFailureOnInvalidMaxInput,
        ITestAssertFailureOnInvalidMinInput,
        ITestAssertFailureOnNullOrEmptyInput
{

    boolean assertFailureOnInvalidInput(String input);

}