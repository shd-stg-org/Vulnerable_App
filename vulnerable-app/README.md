# Vulnerable Test Application

This is a test Java/Maven application with intentional security vulnerabilities created for testing the "Fail a Pull Request" feature (POLDELIVER-2866).

## Purpose

This application is designed to be scanned by security scanning tools (SAST and SCA) to generate security findings for testing PR failure policies in Polaris.

## Security Vulnerabilities

This application contains the following intentional vulnerabilities organized by severity level:

### CRITICAL Issues

#### SQL Injection (SQLInjectionVulnerability.java)
- **Description**: User input directly concatenated into SQL query without parameterization
- **Impact**: Remote Code Execution, Data Breach
- **Method**: `getUserData(String userId)`

#### Command Injection (SQLInjectionVulnerability.java)
- **Description**: User input directly executed as shell command
- **Impact**: Remote Code Execution
- **Method**: `executeUserCommand(String userInput)`

#### Path Traversal (PathTraversalVulnerability.java)
- **Description**: File path constructed from user input without validation
- **Impact**: Unauthorized File Access
- **Method**: `readFileContent(String filename)`

### HIGH Issues

#### Hardcoded Credentials (SQLInjectionVulnerability.java)
- **Description**: Database credentials hardcoded in source code
- **Impact**: Credential Exposure
- **Method**: `connectWithHardcodedPassword()`

#### Weak Hashing - MD5 (CryptographyVulnerabilities.java)
- **Description**: Using MD5 for password hashing (cryptographically broken)
- **Impact**: Weak Password Storage
- **Method**: `hashPasswordWithMD5(String password)`

#### Insecure Deserialization (PathTraversalVulnerability.java)
- **Description**: ObjectInputStream used to deserialize untrusted data
- **Impact**: Remote Code Execution
- **Method**: `deserializeUserInput(byte[] data)`

#### Reflected XSS (XSSVulnerability.java)
- **Description**: User input directly embedded in HTML without encoding
- **Impact**: Cross-Site Scripting
- **Method**: `generateHTMLResponse(String userInput)`

#### DOM-based XSS (XSSVulnerability.java)
- **Description**: Unsafe evaluation of user-supplied code
- **Impact**: Cross-Site Scripting
- **Method**: `evaluateUserCode(String userCode)`

### MEDIUM Issues

#### Weak Random Number Generator (CryptographyVulnerabilities.java)
- **Description**: Using java.util.Random for security token generation (not cryptographically secure)
- **Impact**: Predictable Security Tokens
- **Method**: `generateSecurityToken()`

#### Weak Hashing - SHA-1 (CryptographyVulnerabilities.java)
- **Description**: Using SHA-1 for hashing (considered weak)
- **Impact**: Weak Hash Algorithm
- **Method**: `hashWithSHA1(String data)`

#### Information Disclosure (PathTraversalVulnerability.java)
- **Description**: Full exception stack traces printed to console
- **Impact**: Information Disclosure
- **Method**: `processUserFile(String filename)`

#### Sensitive Data Logging (DataExposureVulnerability.java)
- **Description**: Passwords and credentials logged to console/logs
- **Impact**: Credential Exposure
- **Method**: `logUserCredentials(String username, String password)`

#### Cleartext Password Storage (DataExposureVulnerability.java)
- **Description**: Password stored in plaintext without encryption
- **Impact**: Credential Exposure
- **Field**: `storedPassword`

#### Insufficient Input Validation (XSSVulnerability.java)
- **Description**: Minimal input validation on user data
- **Impact**: Various injection attacks
- **Method**: `processUserData(String userData)`

#### Unvalidated Redirect (XSSVulnerability.java)
- **Description**: Redirect URL not validated
- **Impact**: Open Redirect
- **Method**: `createRedirectURL(String targetURL)`

### LOW Issues

#### Missing Input Validation (DataExposureVulnerability.java)
- **Description**: No try-catch for parseInt on user input
- **Impact**: Denial of Service
- **Method**: `parseInt(String value)`

#### Insecure Logging (DataExposureVulnerability.java)
- **Description**: Using System.out for security operations
- **Impact**: Information Disclosure
- **Method**: `performSecurityOperation(String operation)`

### INFORMATIONAL Issues

#### Dead Code (DataExposureVulnerability.java)
- **Description**: Unused variables and dead code paths
- **Impact**: Code Quality
- **Method**: `processData(String input)`

## SCA Vulnerabilities (Dependencies)

The `pom.xml` includes intentionally vulnerable dependencies:

| Dependency | Version | Severity | Known Vulnerability |
|---|---|---|---|
| log4j-core | 2.14.0 | CRITICAL | Log4Shell (CVE-2021-44228) |
| commons-beanutils | 1.9.2 | HIGH | RCE via PropertyUtils |
| commons-collections4 | 4.0 | HIGH | Unsafe Deserialization |
| xstream | 1.4.10 | MEDIUM | XML Deserialization RCE |
| struts2-core | 2.3.15 | MEDIUM | Multiple RCE vulnerabilities |
| guava | 11.0 | LOW | Outdated version |
| commons-io | 2.4 | INFORMATIONAL | Outdated version |

## Project Structure

```
vulnerable-app/
├── pom.xml                  # Maven configuration with vulnerable dependencies
├── README.md               # This file
└── src/
    ├── main/
    │   └── java/com/example/vulnerable/
    │       ├── Application.java                    # Main entry point
    │       ├── SQLInjectionVulnerability.java      # SQL/Command injection & hardcoded creds
    │       ├── CryptographyVulnerabilities.java    # Weak crypto & hashing
    │       ├── PathTraversalVulnerability.java     # Path traversal & deserialization
    │       ├── XSSVulnerability.java               # XSS vulnerabilities
    │       └── DataExposureVulnerability.java      # Data exposure & logging issues
    └── test/
        └── java/com/example/vulnerable/
            └── ApplicationTest.java                # Unit tests

```

## Building the Project

### Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

### Build Commands

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Package the application
mvn clean package

# Build with full lifecycle
mvn clean install
```

## Security Scanning

This application is designed to be scanned by security scanning tools such as:

- **SAST Scanners**: Polaris SAST, SonarQube, Checkmarx, Fortify
- **SCA Scanners**: Polaris SCA, Black Duck, OWASP Dependency-Check
- **DAST Scanners**: For web vulnerabilities if deployed

### Expected Scan Results

When scanned, you should expect to find:
- **7 CRITICAL** issues (SQL injection, command injection, path traversal, etc.)
- **8 HIGH** issues (weak crypto, hardcoded credentials, etc.)
- **7 MEDIUM** issues (weak algorithms, data exposure, etc.)
- **2 LOW** issues
- **1 INFORMATIONAL** issue (code quality)
- **7 SCA vulnerabilities** from dependencies

## Testing PR Failure Policies

This application is specifically designed for testing the "Fail a PR" feature. When used in a PR:

1. Push this code to a feature branch
2. Create a Pull Request
3. Configure PR policies with "Fail PR" action for:
   - Critical issues (should fail/block)
   - High issues (should warn or block based on policy)
4. The PR scan will detect all vulnerabilities
5. PR policies will evaluate and either block or warn based on configuration
6. Test the PR blocking/unblocking behavior

## Disclaimer

⚠️ **WARNING**: This application contains intentional security vulnerabilities and should **NEVER** be used in production or deployed to any real system. It is designed solely for testing purposes.

This code is meant to:
- ✓ Test security scanning tools
- ✓ Validate PR failure policies
- ✓ Train security teams
- ✗ NOT be deployed to production
- ✗ NOT be used in real applications
- ✗ NOT expose sensitive systems

## References

- Epic: [POLDELIVER-2866 - Fail a Pull Request](https://blackduck.atlassian.net/browse/POLDELIVER-2866)
- Feature Documentation: [Fail a Pull Request](https://blackduck.atlassian.net/wiki/spaces/PLATSERV/pages/2369159169/Fail+a+Pull+Request)
- Test Plan: [E2E Acceptance Test Plan](https://blackduck.atlassian.net/wiki/spaces/RnDQA/pages/3006922753/E2E+Acceptance+Test+Plan+Fail+a+Pull+Request)

## License

This test application is provided as-is for testing purposes only.
