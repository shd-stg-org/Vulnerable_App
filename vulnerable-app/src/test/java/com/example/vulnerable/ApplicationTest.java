package com.example.vulnerable;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic test class for the vulnerable application
 */
public class ApplicationTest {

    @Test
    public void testApplicationStartup() {
        // This test just verifies the application can be instantiated
        Application app = new Application();
        assertNotNull(app);
    }

    @Test
    public void testSQLInjectionVulnerabilityExists() {
        SQLInjectionVulnerability vuln = new SQLInjectionVulnerability();
        assertNotNull(vuln);
    }

    @Test
    public void testCryptographyVulnerabilitiesExist() {
        CryptographyVulnerabilities vuln = new CryptographyVulnerabilities();
        assertNotNull(vuln);
    }

    @Test
    public void testPathTraversalVulnerabilityExists() {
        PathTraversalVulnerability vuln = new PathTraversalVulnerability();
        assertNotNull(vuln);
    }

    @Test
    public void testXSSVulnerabilityExists() {
        XSSVulnerability vuln = new XSSVulnerability();
        assertNotNull(vuln);
    }

    @Test
    public void testDataExposureVulnerabilityExists() {
        DataExposureVulnerability vuln = new DataExposureVulnerability();
        assertNotNull(vuln);
    }
}
