package io.github.ninobomba.utils.java.tests.asserts.core;

import io.github.ninobomba.utils.java.tests.asserts.containers.ITestAssertCache;
import io.github.ninobomba.utils.java.tests.asserts.containers.ITestAssertDatabase;
import io.github.ninobomba.utils.java.tests.asserts.containers.ITestAssertQueue;

public interface ITestAssertContainer extends ITestAssertDatabase, ITestAssertCache, ITestAssertQueue {
}