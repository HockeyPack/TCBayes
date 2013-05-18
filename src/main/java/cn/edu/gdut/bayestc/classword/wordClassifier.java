package cn.edu.gdut.bayestc.classword;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 对中文文本进行分词的类
 * 
 * @author Hejiepeng
 * 
 */
public class wordClassifier {
	
	/**
	 * 将文本转换成单词组的方法
	 * @param text
	 * @return
	 * @throws IOException
	 */
	public List<String> textToWords(String text) throws IOException {
		List<String> words = new ArrayList<String>();
		// TODO 实现将文本分成单词并将单词返回。
		StringReader reader = new StringReader(text);
		IKSegmenter ik = new IKSegmenter(reader, true);
		Lexeme lexeme = null;
		while ((lexeme = ik.next()) != null) {
			String aa = lexeme.getLexemeText();
			if (aa != null && aa.length() > 1) {
				words.add(aa);
			}
		}
		return words;
	}

}