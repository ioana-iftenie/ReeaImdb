package Encoding;

import java.nio.charset.Charset;

public class TestEncodings {

	public static void main(String[] args) {
		try {
			for (String encoding : Charset.availableCharsets().keySet()) {
				System.out.println(encoding);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
