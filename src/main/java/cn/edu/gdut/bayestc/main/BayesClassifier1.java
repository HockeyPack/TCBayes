package cn.edu.gdut.bayestc.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.gdut.bayestc.classword.ChineseSpliter;
import cn.edu.gdut.bayestc.textclassifier.entity.ClassifyResult;
import cn.edu.gdut.bayestc.util.ClassPorpertiesUtils;
import cn.edu.gdut.bayestc.util.StopWordsHandler;
import cn.edu.gdut.bayestc.util.TestTextsFileOperator;
import cn.edu.gdut.bayestc.util.TrainingSetManager;

/**
 * 朴素贝叶斯分类器
 */
public class BayesClassifier1 {

	private static final String DEFAULT_TEXT = "微软公司提出以446亿美元的价格收购雅虎中国网2月1日报道 美联社消息，微软公司提出以446亿美元现金加股票的价格收购搜索网站雅虎公司。微软提出以每股31美元的价格收购雅虎。微软的收购报价较雅虎1月31日的收盘价19.18美元溢价62%。微软公司称雅虎公司的股东可以选择以现金或股票进行交易。微软和雅虎公司在2006年底和2007年初已在寻求双方合作。而近两年，雅虎一直处于困境：市场份额下滑、运营业绩不佳、股价大幅下跌。对于力图在互联网市场有所作为的微软来说，收购雅虎无疑是一条捷径，因为双方具有非常强的互补性。(小桥)";
	private TrainingSetManager trainingSetManager;// 训练集管理器
	private static final int BEGIN_COUNT = 1;

	/**
	 * 默认的构造器，初始化训练集
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public BayesClassifier1() throws UnsupportedEncodingException {
		trainingSetManager = new TrainingSetManager();

	}



	/**
	 * 去掉停用词
	 * 
	 * @param text
	 *                给定的文本
	 * @return 去停用词后结果
	 */
	public String[] DropStopWords(String[] oldWords) {
		Vector<String> v1 = new Vector<String>();
		for (int i = 0; i < oldWords.length; ++i) {
			if (StopWordsHandler.IsStopWord(oldWords[i]) == false) {// 不是停用词
				v1.add(oldWords[i]);
			}
		}
		String[] newWords = new String[v1.size()];
		v1.toArray(newWords);
		return newWords;
	}

	/**
	 * 对给定的文本进行分类
	 * 
	 * @param text	     给定的文本
	 * 
	 * @return 分类结果
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public String classify(String text) throws IOException {
		String[] terms = null;
		terms = ChineseSpliter.split(text,"\t").split("\t");// 中文分词处理(分词后结果可能还包含有停用词）
		System.out.println();
		String[] Classes = trainingSetManager.getTraningClassifications();// 分类
		List<ClassifyResult> crs = new Vector<ClassifyResult>();
		
		CaculateProbilities(terms, Classes, crs);
		// 对最后概率结果进行排序
		sortProbability(crs);
		
		// 返回概率最大的分类
		
		return crs.get(0).classification;
		
	}


	/**
	 * 计算每一个term的概率在每一个Classes中的概率
	 * 
	 * 计算得到的每一个概率存在crs中
	 * 
	 * @param terms
	 * @param Classes
	 * @param crs
	 */
	private void CaculateProbilities(String[] terms, String[] Classes,
			List<ClassifyResult> crs) {
		//  多线程計算分类结果
		CountDownLatch begin = new CountDownLatch(BEGIN_COUNT);
		CountDownLatch end = new CountDownLatch(Classes.length);
		CalEveryClassProbability[] cals = new CalEveryClassProbability[Classes.length];
		for (int i = 0; i < Classes.length; i++) { 
			
			cals[i] = new CalEveryClassProbability(begin, end);
			cals[i].setI(i);
			cals[i].setClasses(Classes);
			cals[i].setCrs(crs);
			cals[i].setTerms(terms);
			cals[i].setTdm(trainingSetManager);

		}
		ExecutorService exe = Executors.newFixedThreadPool(Classes.length);
		for (CalEveryClassProbability cal : cals) {
			exe.execute(cal);
		}
			begin.countDown();
		try {
			end.await();// 等待计算分析的所有线程结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("计算结束");
		}

		// 注意：此时main线程已经要结束了，但是exe线程如果不关闭是不会结束的
		exe.shutdown();
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void sortProbability(List<ClassifyResult> crs) {
		java.util.Collections.sort(crs, new Comparator() {
			public int compare(final Object o1, final Object o2) {
				final ClassifyResult m1 = (ClassifyResult) o1;
				final ClassifyResult m2 = (ClassifyResult) o2;
				final double ret = m1.probility - m2.probility;
				if (ret < 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();
		System.out.println();
		String text = BayesClassifier1.DEFAULT_TEXT;
		BayesClassifier1 classifier = new BayesClassifier1();// 构造Bayes分类器
		TestTextsFileOperator.makeDir();
		List<String> texts = TestTextsFileOperator.getTestList();
		String result = null;
		for(int i = 0 ; i < texts.size();i++){
			text = TestTextsFileOperator.getTestSet().get(texts.get(i));
			result = classifier.classify(text);// 进行分类
			TestTextsFileOperator.sortOut(result, texts.get(i)); 
			System.out.println(texts.get(i)+"此项属于["
					+ ClassPorpertiesUtils.classProperties
							.getProperty(result) + "]");
		}
		System.out.println("计算用时约为："+(System.currentTimeMillis()-time)/1000+" seconds");
	}
}