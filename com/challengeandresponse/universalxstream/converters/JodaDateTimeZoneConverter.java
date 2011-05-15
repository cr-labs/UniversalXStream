package com.challengeandresponse.universalxstream.converters;

import org.joda.time.DateTimeZone;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class JodaDateTimeZoneConverter implements Converter {

	public void marshal(Object o, HierarchicalStreamWriter writer,
			MarshallingContext arg2) {

        DateTimeZone dtz = (DateTimeZone) o;
        writer.startNode("jodaDateTimeZoneId");
        writer.setValue(dtz.getID());
        writer.endNode();
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		reader.moveDown();
		String datetimezoneid = reader.getValue();
		reader.moveUp();
		return DateTimeZone.forID(datetimezoneid);
	}
	
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class arg0) {
		return (arg0.equals(org.joda.time.DateTimeZone.class));
	}

}
