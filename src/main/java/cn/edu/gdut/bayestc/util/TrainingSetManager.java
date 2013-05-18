package cn.edu.gdut.bayestc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
* 训练集管理器
*/

public class TrainingSetManager 
{
	@SuppressWarnings("unused")
	private String[] traningFileClassifications;//训练语料分类集合
	private File traningTextDir;//训练语料存放目录
	
	 private static String defaultPath = "D:" + File.separator + "temp"
	 + File.separator + "SogouC" + File.separator
	 + "ClassFile";

//	 private static String defaultPath = "D:" + File.separator + "temp"
//	 + File.separator + "SogouC.reduced" + File.separator
//	 + "Reduced";

//	 private static String defaultPath =
//	 "D:"+File.separator+"temp"+File.separator+"SogouC.mini"+File.separator+"Sample";

	
//	private static String defaultPath = "D:" + File.separator + "temp"
//
	/**
	 *构造函数 
	 */
	public TrainingSetManager() 
	{
		traningTextDir = new File(defaultPath);
		if (!traningTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("训练语料库搜索失败！ [" +defaultPath + "]");
		}
		this.traningFileClassifications = traningTextDir.list();
	}
	/**
	* 返回训练文本类别，这个类别就是目录名
	* @return 训练文本类别
	*/
	public String[] getTraningClassifications() 
	
	{
		return TrainingSetLoader.getClassifications();
		
	}
	/**
	* 根据训练文本类别返回这个类别下的所有训练文本路径（full path）
	* @param classification 给定的分类
	* @return 给定分类下所有文件的路径（full path）
	*/
	public String[] getFilesPath(String classification) 
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classification);
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			ret[i] = traningTextDir.getPath() +File.separator +classification +File.separator +ret[i];
		}
		return ret;
	}

	/**
	* 返回给定路径的文本文件内容
	* @param filePath 给定的文本文件路径
	* @return 文本内容
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getText(String filePath) throws FileNotFoundException,IOException 
	{
	
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(filePath),"GBK");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
	
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		isReader.close();
		reader.close();
		return sb.toString();
	}

	/**
	* 返回训练文本集中所有的文本数目
	* @return 训练文本集中所有的文本数目
	*/
	public int getTrainingFileCount()
	{
		String [] TrainnngSetkind  = TrainingSetLoader.getClassifications();
		int allTextCount = 0;
		for(String TrainSet:TrainnngSetkind){
			allTextCount += TrainingSetLoader.getTrainingMap().get(TrainSet).size();
		}
		return allTextCount;

	}

	/**
	* 返回训练文本集中在给定分类下的训练文本数目
	* @param classification 给定的分类
	* @return 训练文本集中在给定分类下的训练文本数目
	*/
	public int getTrainingFileCountOfClassification(String classification)	
	{	
		
		return TrainingSetLoader.getTrainingMap().get(classification).size();
	
	}

	/**
	* 返回给定分类中包含关键字／词的训练文本的数目
	* @param classification 给定的分类
	* @param key 给定的关键字／词
	* @return 给定分类中包含关键字／词的训练文本的数目
	*/
	public int getCountContainKeyOfClassification(String classification,String key) 
	{
		int keyCount = 0;
		List<String> allText= TrainingSetLoader.getTrainingMap().get(classification);
		
		int textSize = allText.size();
		
		for(int i = 0;i < textSize;i++){
			if(allText.get(i).contains(key)){
				keyCount++;
			}
		}
		return keyCount;

		
	}
	
	
	public File getTraningTextDir() {
		return traningTextDir;
	}
}