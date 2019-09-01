package com.inclination.scaffold.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputStreamRunnable implements Runnable {

	BufferedReader bReader = null;

	public InputStreamRunnable(InputStream is) throws UnsupportedEncodingException {
		bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "utf-8"));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String line;
		try {
			while ((line = bReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
