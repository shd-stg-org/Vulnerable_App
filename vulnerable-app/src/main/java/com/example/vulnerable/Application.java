package com.example.vulnerable;

/**
 * Main application class - Entry point for the vulnerable test application
 */
public class Application {

    public static void main(String[] args) {
        System.out.println("Vulnerable Test Application started");
        System.out.println("This application contains intentional security vulnerabilities for testing purposes");
        
        try {
            // Example usage of vulnerable classes
            SQLInjectionVulnerability sqlVuln = new SQLInjectionVulnerability();
            CryptographyVulnerabilities cryptoVuln = new CryptographyVulnerabilities();
            PathTraversalVulnerability pathVuln = new PathTraversalVulnerability();
            XSSVulnerability xssVuln = new XSSVulnerability();
            DataExposureVulnerability dataVuln = new DataExposureVulnerability();
            
            System.out.println("All vulnerable components loaded");
        } catch (Exception e) {
            System.err.println("Error initializing vulnerable components");
            e.printStackTrace();
        }
    }
}
