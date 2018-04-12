package com.challengeandresponse.universalxstream;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;


/**
 * An implementation of XStream tuned for universal use.
 * Be sure to call excludeClasses() before instantiating, if intending to leave classes out
 *
 * @author jim
 *
 */
public class UniversalXStream extends XStream {

	@SuppressWarnings("unchecked")
	private static Vector <Class> excludeClasses = new Vector <Class> ();

	@SuppressWarnings("unchecked")
	public static void excludeClasses(Class... c) {
		for (int i = 0; i < c.length; i++)
			excludeClasses.add(c[i]);
	}

	public static void includeAllClasses() {
		excludeClasses.removeAllElements();
	}

	public String toXML(Object o) {
		return super.toXML(o);
	}


	public Object fromXML(String s) {
		return super.fromXML(s);
	}


	// omit all fields that are not to be serialized by our serializer. Only our own declared classes should be serialized
	@SuppressWarnings("unchecked")
	public UniversalXStream() {
		super();
		Iterator <Class> itClass = excludeClasses.iterator();
		while (itClass.hasNext()) {
			Class c = itClass.next();
			Field fields[] = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				this.omitField(c, fields[i].getName());
			}
		}




	}








	public static void main(String[] args) {
		UniversalXStream.excludeClasses(Vector.class);

		String a = "Hello";
		Vector <String> v = new Vector <String> ();
		v.add("a");
		v.add("b");
		v.add(a);

		UniversalXStream xs = new UniversalXStream();
		System.out.println(xs.toXML(v));

		UniversalXStream.includeAllClasses();

		com.challengeandresponse.universalxstream.test.Test t = new com.challengeandresponse.universalxstream.test.Test();
		t.castle = "castle";
		t.elephant = "elephant";
		t.atransientfield = "atransientfield";

		System.out.println("\nSerialize and deserialize with no fields excluded");
		UniversalXStream xs2 = new UniversalXStream();
		String s2 = xs2.toXML(t);
		System.out.println("serialized version:"+s2);
		com.challengeandresponse.universalxstream.test.Test t2 = (com.challengeandresponse.universalxstream.test.Test) xs2.fromXML(s2);
		System.out.println(t2);

		System.out.println("\nSerialize and deserialize with all Test.class fields excluded");
		UniversalXStream.excludeClasses(com.challengeandresponse.universalxstream.test.Test.class);
		UniversalXStream xs3 = new UniversalXStream();
		String s3 = xs3.toXML(t);
		System.out.println("serialized version:"+s3);
		com.challengeandresponse.universalxstream.test.Test t3 = (com.challengeandresponse.universalxstream.test.Test) xs3.fromXML(s3);
		System.out.println(t3);

	}


}
