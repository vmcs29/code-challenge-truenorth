package base;

import utilities.CommonUtilities;

import java.util.Properties;

public class EnvironmentProperties {

    private static String projectPath = System.getProperty("user.dir");
    private static String PROP_FILE_PATH = "/src/main/resources/";
    private Properties props = EnvironmentProperties.getProperties();


    /**
     * Read the environment {server}.properties passed as parameter when run with MVN
     * if -Denv.USER not passed in command line then read local as default environment
     *
     * @return String
     */
    private static String getContextPropertyFile() {
        String propertyFile = System.getProperty("propertyFile");
        String username = System.getProperty("user.name");
        if (propertyFile == null || propertyFile.trim().isEmpty() || propertyFile.equals(username)) {
            propertyFile = "local.properties";
        }
        return propertyFile;
    }
    /**
     * Get the properties object from context file
     *
     * @return Properties
     */
    public static Properties getProperties() {
        return CommonUtilities.loadProperties(projectPath + PROP_FILE_PATH + getContextPropertyFile());
    }
}