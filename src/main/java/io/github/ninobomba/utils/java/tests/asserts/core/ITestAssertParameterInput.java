package io.github.ninobomba.utils.java.tests.asserts.core;

import io.github.ninobomba.utils.java.tests.asserts.inputs.ITestAssertInvalidFormat;
import io.github.ninobomba.utils.java.tests.asserts.inputs.ITestAssertInvalidInput;
import io.github.ninobomba.utils.java.tests.asserts.inputs.ITestAssertMaxInput;
import io.github.ninobomba.utils.java.tests.asserts.inputs.ITestAssertMinInput;

public interface ITestAssertParameterInput extends ITestAssertInvalidInput, ITestAssertInvalidFormat, ITestAssertMaxInput, ITestAssertMinInput {
}