package mma.legacy.interval;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    static private Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        BasicConfigurator.configure();
        logger.info("Hello World!");
    }
}