package cn.edu.gdut.bayestc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StopWordDicReader {
	/**
	 * 停止词典读取器
	 */
		String stopWordDicFileName = "stopword.dic";
		File stopWordDicFile ;
		FileReader stopWordReader ;
				
		/**
		 * 默认构造器
		 * @throws IOException
		 */
		private StopWordDicReader() throws IOException{
			 stopWordDicFile = new File(this.stopWordDicFileName);
			 try {
				 stopWordReader = new FileReader(stopWordDicFile);
			} catch (FileNotFoundException e) {
				stopWordReader.close();
				throw new IOException(e);
			}			
		}
		
		private StopWordDicReader(String stopWordDicFileName)throws IOException{	
			this.stopWordDicFileName = stopWordDicFileName;
			 stopWordDicFile = new File(this.stopWordDicFileName);
			 try {
				 stopWordReader = new FileReader(stopWordDicFile);
			} catch (FileNotFoundException e) {
				stopWordReader.close();
				throw new IOException(e);
			}	
		}
		
		/**
		 * StopWordReader的工厂方法
		 * @return
		 * @throws IOException
		 */
		public static StopWordDicReader stopWordReaderCreater() throws IOException{
			return new StopWordDicReader();			
		}
		
}
