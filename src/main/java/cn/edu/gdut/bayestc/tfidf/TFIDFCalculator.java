package cn.edu.gdut.bayestc.tfidf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.edu.gdut.bayestc.tfidf.entity.TFIDFResult;

/**
 * 计算文本分出来的单词的TF-IDF值 如何计算TFIDF值见/TCBayes/src/计算文本特征式.txt的解释
 * 
 * @author Hejiepeng
 * 
 */
public class TFIDFCalculator {
	/**
	 * 计算单词组的TFI-DF值
	 */
	public TFIDFResult doCalculateIn(List<String> words){
		TFIDFResult tfidfResult = new TFIDFResult();
		//TODO 降维很重要，这个循环要好多次
		double tfidf = 0;
		for(int i=1;i<=words.size();i++){
			tfidf = calculateTFIDF(calTF(words),calIDF(words) );
		}
		tfidfResult.setResult(tfidf);
		return  tfidfResult;
	}

	private double calculateTFIDF(Map<String, Double> TFmap, Map<String, Double> IDFmap) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	private Map<String ,Double> calTF(List<String> words) {
		// TODO Auto-generated method stub
		int wordsAmout = words.size();
		//统计单词出现的个数功能
		Map<String, Integer> wordAndAmount = new HashMap<String, Integer>();
		for(int i = 0;  i < wordsAmout;i++){
			String word = words.get(i);
			if(wordAndAmount.containsKey(word)){
				int amount = wordAndAmount.get(word)+1;
				wordAndAmount.remove(word);
				wordAndAmount.put(word, amount);
				words.remove(i);
			}
			else{
				wordAndAmount.put(word, 1);
			}
		}
		//计算每个单词对应的TF值，
		Map<String,Double> wordAndTF = new HashMap<String, Double>();
		int uniqueWordAmont = words.size();
		for(int i =0 ;i < uniqueWordAmont;i++){
			String uniqueWord  = words.get(i);
			if(wordAndAmount.containsKey(uniqueWord)){
				wordAndTF.put(uniqueWord, (double) (wordAndAmount.get(uniqueWord)/uniqueWordAmont));
			}
		}
		return wordAndTF;
	}

	private Map<String,Double> calIDF(List<String> words) {
		// TODO Auto-generated method stub
		//在谷歌上输入改词，log（互联网网页总数/返回查询结果结果总数）就可以求的那个词的普遍性的IDF了
		//下载对应的词汇表，但是现在还没有找到。
		return null;
	}
}
