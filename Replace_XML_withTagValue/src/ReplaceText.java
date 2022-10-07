import Wa.Baru;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.list;
import static java.util.Collections.sort;

public class ReplaceText {

	public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		Baru dataBaru = new Baru();
		Path source = Paths.get("D:\\Java Dasar\\Replace_XML_withTagValue\\src\\Teknis.xml");
		Path path = Paths.get("D:\\Java Dasar\\Replace_XML_withTagValue\\src\\Test\\Teknis.xml");
		List<String> originalData = Files.readAllLines(source);
		copyFileUsingChannel(source, path);
		Stream<String> lines;
		Map<String,String> variableMap = fillMap(dataBaru, originalData);
		try {
			lines = Files.lines(path,Charset.forName("UTF-8"));
			List<String> replacedLines = lines.map(line -> replaceTag(line,variableMap))
                    .collect(Collectors.toList());
			Files.write(path, replacedLines, Charset.forName("UTF-8"));
			lines.close();
			System.out.println("Find and replace done");
			System.out.println(replacedLines.stream().map(String::trim).collect(Collectors.toList()));
			String newLines = String.join(", ", replacedLines.stream().map(String::trim).collect(Collectors.toList()));
			System.out.println(newLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String,String> fillMap(Baru dataBaru, List originalData) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Map<String,String> map = new HashMap<String,String>();
		Field[] fieldData = dataBaru.getClass().getDeclaredFields();
		ArrayList subData = (ArrayList) originalData;
		ArrayList newData = new ArrayList<>();
		String[] str;
		String dataValue;
		Method method;
		for (int i=0;i<subData.size();i++){
			str = String.valueOf(subData.get(i)).split("[\\[\\]]");
			if (str.length==3){
				newData.add(str[1]);
				dataValue = str[1];
				method = dataBaru.getClass().getMethod(dataValue);
				method.invoke(dataBaru);
				System.out.println(method.invoke(dataBaru));
				map.put("["+dataValue+"]" , String.valueOf(method.invoke(dataBaru))) ;
			}
		}
		System.out.println(newData);
		System.out.println(newData.size());

		System.out.println(map);
		return map;
	}

	private static void copyFileUsingChannel(Path source, Path dest) throws IOException {
		try {
			Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException ex) {
			System.err.format("I/O Error when copying file");
		}
	}

	private static String replaceTag(String str, Map<String,String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (str.contains(entry.getKey())) {
				str = str.replace(entry.getKey(), entry.getValue());
			}
		}
		return str;
	}

}
