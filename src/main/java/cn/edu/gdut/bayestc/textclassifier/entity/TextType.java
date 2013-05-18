package cn.edu.gdut.bayestc.textclassifier.entity;
/**
 * 文本的类型
 * 用类别标识码还是直接用类别比较好呢？
 * 这里暂时用类别直接分类吧。
 * @author Hejiepeng
 *
 */
public class TextType {
	String textType="";

	public String getTextType() {
		return textType;
	}

	public void setTextType(String textType) {
		this.textType = textType;
	}
}
