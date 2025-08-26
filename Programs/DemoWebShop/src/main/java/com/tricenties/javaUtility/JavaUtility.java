package com.tricenties.javaUtility;

import java.time.LocalDateTime;

public class JavaUtility {
	public String getTime()
	{
		String time = LocalDateTime.now().toString().replaceAll(":", "-");
		return time;
	}

}
