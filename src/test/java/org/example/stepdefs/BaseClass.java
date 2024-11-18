package org.example.stepdefs;

import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.example.utils.ReadConfig;

/**
 * @author Sushant
 */
public class BaseClass {

    public static WebDriver          driver;
    public static Logger     logs;
    public static ReadConfig readConfig=new ReadConfig();
    //generate unique email id

    protected static String   token;
    private static   Response response;



}
