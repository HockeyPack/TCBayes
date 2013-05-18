package cn.edu.gdut.bayestc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取文件训练集的类
 * @author Hejiepeng
 *
 */
public class TrainingSetLoader {
	
	private static Map<String,List<String>> trainingMap = new HashMap<String, List<String>>();
	private static String[] classfications ;
	
	public final static String TRAININGPATH = "trainingpath";
	
	static {
		String trainingSetPath = (String)( ClassPorpertiesUtils.traininingPath.get(TRAININGPATH));
		System.out.println("加载训练集"+",训练集路径为："+trainingSetPath);
		long  time = System.currentTimeMillis();
		File trainSetFile = new File(trainingSetPath);
		classfications  = trainSetFile.list();
		
		for(String className:classfications){
			String classPath = trainingSetPath+File.separator+className;
			File classFile = new File(classPath);
			String texts[] = classFile.list();
			List<String> allTextContent = new ArrayList<String>();
			for(String text :texts){				
				String textPath =  classFile + File.separator +text;
				File textFile = new File(textPath);
				InputStreamReader isReader;
				try {
					isReader = new InputStreamReader(new FileInputStream(textFile),"GBK");
					BufferedReader reader = new BufferedReader(isReader);
					String aline;
					StringBuilder sb = new StringBuilder();
				
					while ((aline = reader.readLine()) != null)
					{
						sb.append(aline + " ");
					}
					isReader.close();
					reader.close();
					String textContent =  sb.toString();
					allTextContent.add(textContent);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			trainingMap.put(className, allTextContent);
		}
		System.out.println("加载训练集时间是："+(System.currentTimeMillis()-time)/1000+"seconds");
	}

	public static String[] getClassifications() {
		
		return classfications;
	}

	public static Map<String,List<String>> getTrainingMap() {
		return trainingMap;
	}
	
	public static void main(String[] args) {
		
		System.out.println(111);
	}

}
