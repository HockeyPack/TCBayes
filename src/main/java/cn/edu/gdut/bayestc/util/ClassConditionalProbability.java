package cn.edu.gdut.bayestc.util;


/**
* <b>类</b>条件概率计算
*
* <h3>类条件概率</h3>
* P(x<sub>j</sub>|c<sub>j</sub>)=( N(X=x<sub>i</sub>, C=c<sub>j
* </sub>)+1 ) <b>/</b> ( N(C=c<sub>j</sub>)+M+V ) <br>
* 其中，N(X=x<sub>i</sub>, C=c<sub>j</sub>）表示类别c<sub>j</sub>中包含属性x<sub>
* i</sub>的训练文本数量；N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量；M值用于避免
* N(X=x<sub>i</sub>, C=c<sub>j</sub>）过小所引发的问题；V表示类别的总数。
*
* <h3>条件概率</h3>
* <b>定义</b> 设A, B是两个事件，且P(A)>0 称<br>
* <tt>P(B∣A)=P(AB)/P(A)</tt><br>
* 为在条件A下发生的条件事件B发生的条件概率。

*/

public class ClassConditionalProbability 
{
	private static TrainingSetManager tdm = new TrainingSetManager();
	private static final float M = 0F;
	
	/**
	* 根据某一个词出现的文本的数量，计算类条件概率
	* @param keyInText 给定的文本属性
	* @param classification 给定的分类
	* @return 给定条件下的类条件概率
	*/
	public static double calculatePxc(String keyInText, String classification) 
	{	
//		double ret = 0F;
//		double containKeyTextCount = tdm.getCountContainKeyOfClassification(classification, keyInText); //containKeyTextCount某一个类别下所有文本中出现X的文本数量
//		double allTextCount = tdm.getTrainingFileCountOfClassification(classification);//Nc所有文本的数量
//		double allClassCount = tdm.getTraningClassifications().length;
//		ret = (containKeyTextCount + 1) / (allTextCount + M + allClassCount);
//		return ret;
		
		
		double ret = 0F;
		//已重构
		double containKeyTextCount = tdm.getCountContainKeyOfClassification(classification, keyInText); //containKeyTextCount某一个类别下所有文本中出现X的文本数量
		//已重构
		double allTextCount = tdm.getTrainingFileCountOfClassification(classification);//Nc所有文本的数量
		//已重构
		double allClassCount = tdm.getTraningClassifications().length;
		ret = (containKeyTextCount + 1) / (allTextCount + M + allClassCount);	
		return ret;
		
		
		
	}
}
