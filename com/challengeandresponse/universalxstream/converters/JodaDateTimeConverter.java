package com.challengeandresponse.universalxstream.converters;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class JodaDateTimeConverter implements Converter {

	public void marshal(Object o, HierarchicalStreamWriter writer,
			MarshallingContext arg2) {

        DateTime dt = (DateTime) o;
        writer.startNode("jodaTimeEpochInstant");
        writer.setValue(""+dt.getMillis());
        writer.endNode();
        writer.startNode("jodaDateTimeZoneId");
        writer.setValue(dt.getZone().getID());
        writer.endNode();
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		reader.moveDown();
		String epochinstant = reader.getValue();
		reader.moveUp();
		reader.moveDown();
		String datetimezoneid = reader.getValue();
		reader.moveUp();
		return new DateTime(Long.parseLong(epochinstant),DateTimeZone.forID(datetimezoneid));
	}

	@SuppressWarnings("unchecked")
	public boolean canConvert(Class arg0) {
		return (arg0.equals(org.joda.time.DateTime.class));
	}

}
