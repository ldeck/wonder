/*
 * Copyright (C) NetStruxr, Inc. All rights reserved.
 *
 * This software is published under the terms of the NetStruxr
 * Public Software License version 0.5, a copy of which has been
 * included with this distribution in the LICENSE.NPL file.  */
package er.extensions;

import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;
import java.util.*;

/**
 * A simle utility for providing deprecated functionality for NSTimestamps<br />
 * 
 */

public class ERXTimestampUtility {

    protected static GregorianCalendar _calendar = (GregorianCalendar)GregorianCalendar.getInstance();
    
    public static GregorianCalendar calendarForTimestamp(NSTimestamp t) {
        _calendar.setTime(t);
        return _calendar;
    }

    public static long offsetForDateInCommonEra(NSTimestamp t, int mode) {
        _calendar.setTime(t);
        switch(mode) {
            case GregorianCalendar.YEAR:
                return _calendar.get(GregorianCalendar.YEAR);
            case GregorianCalendar.MONTH:
                return _calendar.get(GregorianCalendar.YEAR) * 12 + _calendar.get(GregorianCalendar.MONTH);
            case GregorianCalendar.WEEK_OF_YEAR:
                return _calendar.get(GregorianCalendar.YEAR) * 52 + _calendar.get(GregorianCalendar.WEEK_OF_YEAR);
            case GregorianCalendar.DAY_OF_MONTH:
            case GregorianCalendar.DAY_OF_YEAR:
                return _calendar.get(GregorianCalendar.YEAR) * 365 + _calendar.get(GregorianCalendar.DAY_OF_YEAR);
            case GregorianCalendar.HOUR_OF_DAY:
            case GregorianCalendar.HOUR:
                return (_calendar.get(GregorianCalendar.YEAR) * 365 + _calendar.get(GregorianCalendar.DAY_OF_YEAR)) * 24 + _calendar.get(GregorianCalendar.HOUR_OF_DAY);
            default:
                return 0;
        }
    }

    public static long differenceByDay(NSTimestamp t1, NSTimestamp t2) {
        return compareDatesInCommonEra(t1, t2, GregorianCalendar.DAY_OF_YEAR);
    }

    public static long differenceByWeek(NSTimestamp t1, NSTimestamp t2) {
        return compareDatesInCommonEra(t1, t2, GregorianCalendar.WEEK_OF_YEAR);
    }

    public static long differenceByMonth(NSTimestamp t1, NSTimestamp t2) {
        return compareDatesInCommonEra(t1, t2, GregorianCalendar.MONTH);
    }

    public static long differenceByYear(NSTimestamp t1, NSTimestamp t2) {
        return compareDatesInCommonEra(t1, t2, GregorianCalendar.YEAR);
    }

    public static NSTimestamp firstDateInSameWeek(NSTimestamp value) {
        return new NSTimestamp(ERXTimestampUtility.yearOfCommonEra(value), ERXTimestampUtility.monthOfYear(value), -ERXTimestampUtility.dayOfWeek(value) + 1, 0, 0, 0, NSTimeZone.defaultTimeZone());
    }

    public static NSTimestamp firstDateInSameMonth(NSTimestamp value) {
        return new NSTimestamp(ERXTimestampUtility.yearOfCommonEra(value), ERXTimestampUtility.monthOfYear(value), -ERXTimestampUtility.dayOfMonth(value)+1, 0, 0, 0, NSTimeZone.defaultTimeZone());
    }
    public static NSTimestamp firstDateInNextMonth(NSTimestamp value) {
        return firstDateInSameMonth(value).timestampByAddingGregorianUnits(0, 1, 0, 0, 0, 0);
    }
    
    public static long compareDatesInCommonEra(NSTimestamp t1, NSTimestamp t2, int mode) {
        return offsetForDateInCommonEra(t2, mode) - offsetForDateInCommonEra(t1, mode);
    }

    public static int dayOfCommonEra(NSTimestamp t) {
        return yearOfCommonEra(t)*365 + dayOfYear(t);
    }

    public static int monthOfCommonEra(NSTimestamp t) {
        return yearOfCommonEra(t)*12 + monthOfYear(t);
    }

    public static int weekOfCommonEra(NSTimestamp t) {
        return yearOfCommonEra(t)*12 + weekOfYear(t);
    }

    public static boolean isWeekDay(NSTimestamp t) {
        int day = dayOfWeek(t);
        return !((day == GregorianCalendar.SATURDAY) || (day == GregorianCalendar.SUNDAY));
    }

    public static int dayOfWeek(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.DAY_OF_WEEK);        
    }

    public static int dayOfMonth(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.DAY_OF_MONTH);
    }

    public static int weekOfYear(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.WEEK_OF_YEAR);
    }    

    public static int weekOfMonth(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.WEEK_OF_MONTH);
    }

    public static int dayOfYear(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.DAY_OF_YEAR);
    }

    public static int hourOfDay(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.HOUR_OF_DAY);
    }

    public static int minuteOfHour(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.MINUTE);        
    }

    public static int monthOfYear(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.MONTH);        
    }

    public static int yearOfCommonEra(NSTimestamp t) {
        return calendarForTimestamp(t).get(GregorianCalendar.YEAR);
    }
}