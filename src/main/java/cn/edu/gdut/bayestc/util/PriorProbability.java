package cn.edu.gdut.bayestc.util;


/**
* 先验概率计算
* <h3>先验概率计算</h3>
* P(c<sub>j</sub>)=N(C=c<sub>j</sub>)<b>/</b>N <br>
* 其中，N(C=c<sub>j</sub>)表示类别c<sub>j</sub>中的训练文本数量；
* N表示训练文本集总数量。
*/

public class PriorProbability 
{
	private static TrainingSetManager tdm =new TrainingSetManager();

	/**
	* 先验概率
	* @param aClass 给定的分类
	* @return 给定条件下的先验概率
	*/
	public static float calculatePc(String aClass)
	{
//		float priorProbability = 0F;
//		float aClassTextCount = tdm.getTrainingFileCountOfClassification(aClass);
//		float allClassTextCount = tdm.getTrainingFileCount();
//		priorProbability = aClassTextCount / allClassTextCount;
//		return priorProbability;
		
		float priorProbability = 0F;
		float aClassTextCount = tdm.getTrainingFileCountOfClassification(aClass);
		float allClassTextCount = tdm.getTrainingFileCount();
		priorProbability = aClassTextCount / allClassTextCount;
		return priorProbability;
		
		
	}
}