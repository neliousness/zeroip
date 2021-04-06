package com.neliolucas.zeroip.utils;

import java.util.logging.Logger;

/**
 * @author Nelio
 * @date 24/03/2021
 */
public class Utils {

    public static void logInfo(Object o, String message)
    {
        Logger logger = Logger.getLogger(o.getClass().getName());
        logger.info(message);
    }

    public static void logError(Object o, String message)
    {
        Logger logger = Logger.getLogger(o.getClass().getName());
        logger.severe(message);
    }
}
