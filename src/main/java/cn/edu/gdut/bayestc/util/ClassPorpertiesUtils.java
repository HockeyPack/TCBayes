package cn.edu.gdut.bayestc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;
/**
 * 读取训练集分类将分类代码和分类名称映射的工具类
 * @author Hejiepeng
 *
 */
public class ClassPorpertiesUtils {
	private static String CLASS_PROPERTIES_FILE = "classficaiton.properties";
	private static String TRAINING_SET_PATH_FILE ="trainingPath.properties";
	
	public static Properties classProperties;
	public static Properties traininingPath;
	static InputStream classPropertiesis;
	static InputStream traininingPathis;

	static {
		classProperties = new Properties();
		traininingPath = new Properties();
		try {
			String path = System.getProperty("user.dir");
			URLDecoder.decode(path, "UTF8");
//			System.out.println(path);
			classPropertiesis = new FileInputStream(new File(path+
					File.separator+ CLASS_PROPERTIES_FILE));
			classProperties.load(classPropertiesis);
			classPropertiesis.close();
			
			traininingPathis = new FileInputStream(new File(path+
					File.separator+ TRAINING_SET_PATH_FILE));
			traininingPath.load(traininingPathis);
			traininingPathis.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
