package io.github.ninobomba.utils.java.unit.persistence;

import io.github.ninobomba.utils.java.persistence.PersistenceDiskUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersistenceDiskUtilsTest {

	@TempDir
	Path tempDir;

	@Test
	void persistTest ( ) throws IOException {
		String outputDirectory = tempDir.resolve ( "output" ).toString ( );
		String data = "{\"test\":\"data\"}";

		PersistenceDiskUtils.persist ( outputDirectory, data );

		// Check if directory was created
		assertTrue ( Files.exists ( tempDir.resolve ( "output" ) ) );

		// Check if at least one file was created in the directory
		try ( var files = Files.list ( tempDir.resolve ( "output" ) ) ) {
			assertTrue ( files.findAny ( ).isPresent ( ) );
		}
	}

}
