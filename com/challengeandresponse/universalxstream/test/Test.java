package com.challengeandresponse.universalxstream.test;

public class Test  {
	
	public String elephant;
	public String castle;

	// transient fields should never be serialized by XStream
	public transient String atransientfield;
	
	

	public String toString() {
		return elephant+" "+castle+" "+atransientfield;
	}
	
	
}


