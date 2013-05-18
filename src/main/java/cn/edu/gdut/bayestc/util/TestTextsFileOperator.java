package cn.edu.gdut.bayestc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试文本的文件操作类
 * @author Hejiepeng
 *
 */
public class TestTextsFileOperator {
	
	/**
	 * 存储测试集里面的内容
	 */
	private static Map<String,String> testSet =  new HashMap<String, String>();
	
	/**
	 * 测试集的名字集合
	 */
	private static List<String> testList = new ArrayList<String>();
	
	/**
	 * 测试集归类的文件夹名字
	 */
	public final static String CLASS_SORT_OUT_FILE = "classesfications";
	
	/**
	 * 读取测试集的文件夹名字
	 */
	public final static String TEST_TEXT_FILE_DIR = "testset";
	
	
	
	/**
	 * 读入测试文本存放到Map 里面
	 */

	static{
		String path = System.getProperty("user.dir")+File.separator+TEST_TEXT_FILE_DIR;
		String[] fileNames = new File(path).list();		
		for(String fileName:fileNames){		
			String filePath = path+File.separator+fileName;
			InputStreamReader inputStreamReader;
			try {
				inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"GBK");
				BufferedReader reader = new BufferedReader(inputStreamReader);
				String aline;
				StringBuilder sb = new StringBuilder();
			
				while ((aline = reader.readLine()) != null)
				{
					sb.append(aline + " ");
				}
				inputStreamReader.close();
				reader.close();
				String text = sb.toString();
				testSet.put(fileName,text);
				testList.add(fileName);
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();		
			}
		}
	}	
	
	public static Map<String, String> getTestSet() {
		return testSet;
	}

	public static List<String> getTestList() {
		return testList;
	}

	/**
	 * 将对应的文本输出对应文件
	 * @throws IOException 
	 */
	public  static void sortOut(String Classfication,String fileName) throws IOException{
		String path = System.getProperty("user.dir")+File.separator+CLASS_SORT_OUT_FILE+File.separator+ClassPorpertiesUtils.classProperties.get(Classfication);
		File file = new File(path+File.separator+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStreamWriter outputStreamWriter ;
		try {
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			String Content = TestTextsFileOperator.testSet.get(fileName);
			bufferedWriter.write(Content, 0, Content.length()-1);			
			bufferedWriter.close();
			outputStreamWriter.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据文本的训练集来构造相应的分类的文件以存放以分类的文本
	 * @throws IOException 
	 */
	public static void makeDir() throws IOException{
		String UserPath = System.getProperty("user.dir");
		String [] classifications = TrainingSetLoader.getClassifications();
		for(String path : classifications){
			String name = path;
			if(null != (String) ClassPorpertiesUtils.classProperties.get(path)){
				name = (String) ClassPorpertiesUtils.classProperties.get(path);
			}			
			String filePath = UserPath+File.separator+CLASS_SORT_OUT_FILE+File.separator+name;
			File file = new File(filePath);
			file.mkdirs();
			file.exists();			
			if(!file.exists()){
				file.createNewFile();				
			}
		}
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(new String(((String)ClassPorpertiesUtils.classProperties.get("C7-History")).getBytes("UTF8")));
//		try {
//			new SortOutHelper().sortOut("IT","14.txt");
			System.out.println();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
	}
	
}
