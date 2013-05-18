package cn.edu.gdut.bayestc.main;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import cn.edu.gdut.bayestc.textclassifier.entity.ClassifyResult;
import cn.edu.gdut.bayestc.util.ClassConditionalProbability;
import cn.edu.gdut.bayestc.util.ClassPorpertiesUtils;
import cn.edu.gdut.bayestc.util.PriorProbability;
import cn.edu.gdut.bayestc.util.TrainingSetManager;

public class CalEveryClassProbability extends Thread {
	public TrainingSetManager tdm;	
	private int i;
	private CountDownLatch begin;
	private CountDownLatch end;
	private static double zoomFactor = 14.0f;
	private  String[] terms; //

	public CalEveryClassProbability(CountDownLatch begin, CountDownLatch end) {
		this.begin = begin;
		this.end = end;
	}

	public int getI() {
		return i;
	}

	public TrainingSetManager getTdm() {
		return tdm;
	}

	public void setTdm(TrainingSetManager tdm) {
		this.tdm = tdm;
	}

	public void setI(int i) {
		this.i = i;
	}

	private  String[] Classes;
	private  List<ClassifyResult> crs;

	public  String[] getTerms() {
		return terms;
	}

	public  void setTerms(String[] terms) {
		this.terms = terms;
	}

	public  String[] getClasses() {
		return Classes;
	}

	public  void setClasses(String[] classes) {
		Classes = classes;
	}

	public  List<ClassifyResult> getCrs() {
		return crs;
	}

	public  void setCrs(List<ClassifyResult> crs) {
		this.crs = crs;
	}

	private void calEveryClassProbability(TrainingSetManager tdm,String[] terms, String[] Classes,
			List<ClassifyResult> crs, int i) {
		double probility;
		String Ci = Classes[i];// 第i个分类
		probility = calcProd(terms, Ci);// 计算给定的文本属性向量terms在给定的分类Ci中的分类条件概率
		// 保存分类结果
		ClassifyResult cr = new ClassifyResult();
		cr.classification = Ci;// 分类
		cr.probility = probility;// 关键字在分类的条件概率
//		System.out.println("In process....");
		System.out.println("在"+tdm.getTraningTextDir()+"训练语料库下,"+"文本属于  "+ClassPorpertiesUtils.classProperties.get(Ci)
				+ "  的概率"+"：" + probility);
		crs.add(cr);
	}

	/**
	 * 计算给定的文本属性向量X在给定的分类aclass中的类条件概率 <code>ClassConditionalProbability</code>
	 * 连乘值
	 * 
	 * @param words
	 *                给定的文本属性向量
	 * @param aclass
	 *                给定的类别
	 * @return 分类条件概率连乘值，即<br>
	 */
	double calcProd(String[] words, String aclass) {
		double ret = 1.0F;
		// 类条件概率连乘
		for (int i = 0; i < words.length; i++) {
			String Xi = words[i];
			// 因为结果过小，因此在连乘之前放大14倍，这对最终结果并无影响，因为我们只是比较概率大小而已
			ret *= ClassConditionalProbability.calculatePxc(Xi, aclass)
					* zoomFactor;
			
		}
//		System.out.println(ret);
		 
		// 再乘以先验概率
		ret *= PriorProbability.calculatePc(aclass);
//		System.out.println(ret);
		return ret;

	}

	@Override
	public void run() {
		
		try {
			begin.await();
			calEveryClassProbability(this.tdm,this.terms, this.Classes, this.crs,
				this.i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			end.countDown();
		}
		
	}
}