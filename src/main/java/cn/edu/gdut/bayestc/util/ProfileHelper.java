package cn.edu.gdut.bayestc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProfileHelper {
	
	private static final String PROFILENAME = "trainingPath.properties";
//	int packageCount; //包的层数
//	int pathCount;	//路径的深度
//	final int CLASS_LENGTH = 1; //类长度为1
//	final int PREFIX_LENGTH = 1;//前缀长度为1
//	final int PROJECT_LENGTH = 1;//工程长度为1
//	

	public static String  getPath() {		
		return System.getProperty("user.dir");
	}
	
	public static String getProfile(){
		return getPath() + File.separator+PROFILENAME;
	}
	
	public static String getTrainDir() throws IOException{
		File file = new File(getProfile());
		InputStream is = new FileInputStream(file);		
		Properties prop = new Properties();
		prop.load(is);
		System.out.println("训练集路径："+prop.getProperty("trainingpath"));
		return prop.getProperty("trainingpath");
		
	}
	
	
	
	public static void main(String[] args) {
		String path = ProfileHelper.class.getResource("ProfileHelper.class").toString();
		String[] paths = path.split("/");
		System.out.println(paths.length);
		String packagePath = ProfileHelper.class.getPackage().toString();
		String[] packagePaths = packagePath.split("[ ]|[.]");
		System.out.println(packagePaths.length);
		System.out.println(path);
		System.out.println(packagePath);
		System.out.println(System.getProperty("user.dir"));
	}
	
}
