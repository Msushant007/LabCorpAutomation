package org.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Sushant
 */
public class ReadConfig {

    Properties properties;

    String path = "config.properties";

    //constructor
    public ReadConfig() {
        try {
            properties = new Properties();

            //to open config .properties file in input mode and load the file
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);

        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    public String getBrowser() {
        String browser = properties.getProperty("browser");
        return browser;
    }

    public String getEndpoint() {
        String url = properties.getProperty("endPoint");
        return url;
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseURL");
        return url;
    }

    public Integer getWaitTime() {
        Integer wait = Integer.valueOf(properties.getProperty("waitTime"));
        return wait;
    }

    public String getChromeDriver() {
        String chromeDriver = properties.getProperty("chromeDriverPath");
        return chromeDriver;
    }

    public String getfireFoxDriver() {
        String fireFoxDriver = properties.getProperty("fireFoxDriverPath");
        return fireFoxDriver;
    }
}
