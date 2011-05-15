package com.challengeandresponse.universalxstream.converters;

import org.joda.time.tz.CachedDateTimeZone;

import com.thoughtworks.xstream.converters.*;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class JodaCachedDateTimeZoneConverter implements Converter {

	public void marshal(Object o, HierarchicalStreamWriter writer,
			MarshallingContext arg2) {

		CachedDateTimeZone dtz = (CachedDateTimeZone) o;
        writer.startNode("jodaDateTimeZoneId");
        writer.setValue(dtz.getID());
        writer.endNode();
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		reader.moveDown();
		String datetimezoneid = reader.getValue();
		reader.moveUp();
		return CachedDateTimeZone.forID(datetimezoneid);
	}

	@SuppressWarnings("unchecked")
	public boolean canConvert(Class arg0) {
		return (arg0.equals(org.joda.time.tz.CachedDateTimeZone.class));
	}

}
