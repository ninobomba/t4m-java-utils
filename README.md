# Tools for Monkeys (T4M) - Java Utilities

T4M is a comprehensive set of common Java utilities, designed to simplify development and provide reusable
components for common tasks. It is built on Java 21 and leverages modern language features.

## Project Overview

- **Name**: Tools for Monkeys (T4M) - Java Utilities
- **Group ID**: io.github.ninobomba
- **Artifact ID**: t4m-java-utils
- **Version**: 1.0.0.40
- **License**: Apache License 2.0
- **Java Version**: 21
- **Maven Central**: [io.github.ninobomba/t4m-java-utils](https://central.sonatype.com/artifact/io.github.ninobomba/t4m-java-utils)

## Key Features

### ID Generation

- Snowflake algorithm-based ID generator
- HashSet-based ID generator
- Pre-generation and caching for performance
- UUID and ULID support

```java
long id = IdGeneratorSnowFlakeSupport.getINSTANCE().getNextId();
```

### Auditable Entities

- Support for tracking creation and modification timestamps and users
- Automatic field population based on CRUD operations

### Events & Request Management

- Request tracking with unique IDs
- Event logging with timestamps and elapsed time calculation
- JSON serialization for logging and analysis

```java
Request request = Request.builder()
        .name("Payment Process")
        .payload("{}")
        .build();

request.pushEvent("start");
request.pushEvent("processing");
request.pushEvent("complete");

System.out.println(request.toJsonString(true));
```

### Checkpoints

- Define and track progress through a multi-step process
- JSON-based checkpoint configuration and persistence support

### API Utilities

- API response builders for success and error responses
- Geolocation and IP utilities

### Exception Handling

- Comprehensive exception hierarchy (Domain-specific: communication, configuration, security)
- Factory pattern for exception creation (ExceptionFactoryPool)
- Localized exception message building

### Validation

- Custom JSR-303/JSR-380 validators
- Age, Email, Password, Phone, FutureDate validation and more

### Other Utilities

- JSON processing (Jackson-based)
- Text manipulation and parameter concatenation
- DateTime utilities
- Design pattern implementations (Lazy loading, OperationResult)
- Persistence (Disk and data persistence utilities)

## Architecture

The library is organized into logical packages under `io.github.ninobomba.utils.java`:

- **api**: API-related utilities (geolocation, IP, response builders)
- **architecture**: Hexagonal architecture patterns and adapters (Persistence, Web)
- **catalogs**: Catalog-related utilities (financial, numeric messages)
- **checkpoints**: Process checkpoint tracking and persistence
- **constants**: Common constants and process status enums
- **data**: Data-related utilities (auditable entities, DTOs, ETL, mappers, persistence/service interfaces)
- **errors**: Domain-specific error types (communication, configuration, security)
- **events**: Event tracking and stack handling
- **exceptions**: Comprehensive exception hierarchy and factory patterns
- **id**: Multi-algorithm ID generation (Snowflake, UUID, ULID, etc.)
- **j2p**: JSON to POJO generation support
- **json**: Jackson-based JSON utilities
- **patterns**: Design pattern implementations (Lazy, OperationResult)
- **persistence**: Disk and data persistence utilities
- **platform**: Platform and system metadata providers
- **project**: Package and project-level utilities
- **properties**: Property loading utilities
- **request**: Request management and event tracking
- **tests**: Test contracts, assertions, and validators
- **text**: Text processing and parameter concatenation
- **time**: DateTime utilities
- **validator**: Custom JSR-303/JSR-380 validators (Age, Email, Password, etc.)

## Getting Started

Add the dependency to your Maven project:

```xml
<dependency>
    <groupId>io.github.ninobomba</groupId>
    <artifactId>t4m-java-utils</artifactId>
    <version>1.0.0.40</version>
</dependency>
```

## Requirements

- Java 21 or higher
- Maven 3.6 or higher

## Dependencies

The library leverages several key libraries:

- **Spring Framework**: Core, Context, TX, Data JPA
- **Jackson**: Core, Annotations, Datatype JSR310
- **Apache Commons**: IO, Lang3
- **Lombok**: For boilerplate-free development
- **Validation**: Jakarta/Javax Validation APIs
- **Others**: Snowflake, ULID, Passay, MapStruct, Reflections, Guava

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome. Please feel free to submit a Pull Request.

## Contact

- **Developer**: Fernando Farfan
- **GitHub**: [https://github.com/ninobomba](https://github.com/ninobomba)
