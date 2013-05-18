package cn.edu.gdut.bayestc.classword;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 中文分词器
 * 
 */
public class ChineseSpliter {
    /**
     * 对给定的文本进行中文分词
     * 
     * @param text       给定的文本
     * 
     * @param splitToken	 用于分割的标记,如"|"
     *           
     * @return 分词完毕的文本
     * 
     * @throws IOException
     */
    public static String split(String text, String splitToken)
	    throws IOException {
//	 String result = null;
//	 MMAnalyzer analyzer = new MMAnalyzer();
//	 try
//	 {
//	 result = analyzer.segment(text, splitToken);
//	 }
//	 catch (IOException e)
//	 {
//	 e.printStackTrace();
//	 }
//	 return result;

	StringReader reader = new StringReader(text);
	IKSegmenter ik = new IKSegmenter(reader, true);
	StringBuilder afterSplitText = new StringBuilder();
	Lexeme lexeme = null;
	while ((lexeme = ik.next()) != null) {
	    String aa = lexeme.getLexemeText();
//	    if (aa != null && aa.length() > 1)
	    if (aa != null) {
		// TODO to make words to string
		afterSplitText.append(aa);
		afterSplitText.append(splitToken);
	    }
	}
//	System.out.println(afterSplitText);
	return afterSplitText.toString();
	
    }
}
