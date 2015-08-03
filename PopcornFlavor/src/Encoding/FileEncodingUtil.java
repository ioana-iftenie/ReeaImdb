package Encoding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

public class FileEncodingUtil {

	static void listspecialCharacters(String filePath, String output)
			throws Exception {
		int k = 0;

		BufferedWriter bw = new BufferedWriter(new FileWriter(output));
		for (String encoding : Charset.availableCharsets().keySet()) {
			k++;
			if ((k < 23) || (k > 23)) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(filePath),
								encoding));
				Set<Character> specialCharacters = new TreeSet<>();
				long lineNumber = 0;
				long nrChar = 0;
				for (;;) {
					String line = reader.readLine();

					if (line == null)
						break;
					if (line.length() > nrChar) {
						nrChar = line.length();
					}
					lineNumber++;
					boolean hasSpecials = false;
					for (int i = 0; i < line.length(); i++) {
						char ch = line.charAt(i);
						if (ch >= 128) {
							specialCharacters.add(ch);
							hasSpecials = true;
						}
					}
					if (hasSpecials) {
					}

				}

				System.out.println("Max on line  = " + nrChar);
				bw.write(nrChar + "\t");
				reader.close();
				System.out.print(encoding + ": \t");
				bw.write(encoding + " -> ");
				for (char ch : specialCharacters)
					bw.write(ch);
				bw.write("\n");
				System.out.println();

			}
		}
	}

	public static void main(String[] args) {
		try {
			listspecialCharacters(
					"C:\\Users\\reea210\\Desktop\\imdbDezarhivat\\movies.list",
					"C:\\Users\\reea210\\Desktop\\imdbDezarhivat\\output.txt");
			listspecialCharacters(
					"C:\\Users\\reea210\\Desktop\\imdbDezarhivat\\actors.list",
					"C:\\Users\\reea210\\Desktop\\imdbDezarhivat\\actorsOutput.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
