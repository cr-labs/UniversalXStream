package com.challengeandresponse.universalxstream.converters;

import com.thoughtworks.xstream.XStream;

/**
 * This is an adapter class that just encapsulates the shorthands for JODA Time
 * 
 * Call like this: JodaTimeXStreamConfigurator.configureXStream(xstream);
 * 
 */


public final class JodaTimeXStreamConfigurator {

	private JodaTimeXStreamConfigurator() {
	}


	public static void configureXStream(XStream xs) {
		xs.registerConverter(new JodaDateTimeZoneConverter());
		xs.registerConverter(new JodaCachedDateTimeZoneConverter());
		xs.registerConverter(new JodaDateTimeConverter());
	}	

}