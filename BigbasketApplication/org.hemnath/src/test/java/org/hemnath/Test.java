package org.hemnath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String port="8095";
		String findPidCommand = "netstat -ano | findstr "+ 8095;
		Process findPidProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", findPidCommand});
		BufferedReader reader = new BufferedReader(new InputStreamReader(findPidProcess.getInputStream()));
		String line;
		String pid = null;
		while ((line = reader.readLine()) != null) {
			if (line.contains("LISTENING")) {
				String[] tokens = line.trim().split("\\s+");
				pid = tokens[tokens.length - 1];
				break;
			}
		}
		reader.close();
		findPidProcess.waitFor();

		// Step 2: Kill the process using the PID
		if (pid != null) {
			String killCommand = String.format("taskkill /F /PID %s", pid);
			Process killProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", killCommand});
			killProcess.waitFor();
			System.out.println("Process with PID " + pid + " using port "  + " has been killed.");
		} else {
			System.out.println("No process found using port " );
		}


	}


}
