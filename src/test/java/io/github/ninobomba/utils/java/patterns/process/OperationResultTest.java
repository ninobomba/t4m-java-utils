package io.github.ninobomba.utils.java.patterns.process;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link OperationResult#isFailure()} method.
 * <p>
 * The {@code isFailure()} method is used to determine if an {@link OperationResult}
 * instance represents a failure state.
 */
class OperationResultTest {

    record Request(String accountId) {
    }

    @Test
    void isFailure_shouldReturnTrue_whenInstanceIsFailure1() {
        // Arrange
        OperationResult<Integer> operationResult = OperationResult.failure("Operation failed");

        // Act
        boolean result = operationResult.isFailure();

        // Assert
        assertTrue(operationResult.isFailure());
        assertFalse(operationResult.isSuccess());
        assertEquals("Operation failed", operationResult.getFailureMessage());
        assertEquals(10, operationResult.getOrElse(10));
        assertNull(operationResult.getOrNull());

        assertThrows(RuntimeException.class, operationResult::getOrThrow);
        assertTrue(result, "isFailure should return true when the instance is a Failure");
    }


    @Test
    void isFailure_shouldReturnTrue_whenInstanceIsFailure() {
        // Arrange
        OperationResult<String> operationResult = OperationResult.failure("Operation failed");

        // Act
        boolean result = operationResult.isFailure();

        // Assert
        assertTrue(result, "isFailure should return true when the instance is a Failure");
    }

    @Test
    void isFailure_shouldReturnFalse_whenInstanceIsSuccess() {
        // Arrange
        OperationResult<String> operationResult = OperationResult.success("Operation succeeded");

        // Act
        boolean result = operationResult.isFailure();

        // Assert
        assertFalse(result, "isFailure should return false when the instance is a Success");
    }

    @Test
    void isFailure_shouldReturnTrue_whenFailureHasNullValue() {
        // Arrange
        OperationResult<Void> operationResult = OperationResult.failure(null);

        // Act
        boolean result = operationResult.isFailure();

        // Assert
        assertTrue(result, "isFailure should return true even if the Failure instance contains null data");
    }

    @Test
    void getFailureMessage_shouldReturnPayload_whenInstanceIsFailure() {
        OperationResult<String> operationResult = OperationResult.failure("timeout");

        assertEquals("timeout", operationResult.getFailureMessage());
        assertEquals("timeout", operationResult.getError());
    }

    @Test
    void getFailureMessage_shouldReturnNull_whenInstanceIsSuccess() {
        OperationResult<String> operationResult = OperationResult.success("ok");

        assertNull(operationResult.getFailureMessage());
        assertNull(operationResult.getError());
    }

    @Test
    void failure_shouldAcceptStringMessage_whenSuccessTypeIsNotString() {
        String accountId = "abc";
        OperationResult<Request> result = OperationResult.<Request>failure(
                "Profile not found for accountId=" + accountId
        );

        assertTrue(result.isFailure());
        assertFalse(result.isSuccess());
        assertEquals("Profile not found for accountId=abc", result.getFailureMessage());
        assertEquals("Profile not found for accountId=abc", result.getError());
        assertNull(result.get());
        assertNull(result.getOrNull());
        assertEquals(new Request("fallback"), result.getOrElse(new Request("fallback")));
    }

    @Test
    void getOrThrow_shouldUseFailureMessage() {
        OperationResult<Request> result = OperationResult.failure("Profile not found for accountId=abc");

        OperationResult.OperationFailedException exception = assertThrows(
                OperationResult.OperationFailedException.class,
                result::getOrThrow
        );

        assertEquals("Profile not found for accountId=abc", exception.getMessage());
        assertEquals(List.of("Profile not found for accountId=abc"), exception.getMessages());
    }

    @Test
    void getOrThrow_shouldReturnData_whenInstanceIsSuccess() {
        Request request = new Request("abc");
        OperationResult<Request> result = OperationResult.success(request);

        assertEquals(request, result.getOrThrow());
        assertEquals(request, result.getOrNull());
        assertEquals(request, result.getOrElse(new Request("fallback")));
    }

}