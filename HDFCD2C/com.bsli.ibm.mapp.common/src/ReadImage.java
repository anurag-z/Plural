

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class ReadImage {

	public static void main(String[] args) {

	
	
		
		ITesseract image=new Tesseract();
//		image.setDatapath("E:\\IBM_DEV\\Chetan\\Sampleproject\\capt\\tessdata\\");
//		image.setLanguage("eng");


		try {
			String str=image.doOCR(new File("C:\\Users\\Admin\\Downloads\\ocr-tess4j-example-master\\ocr-tess4j-example-master\\samples\\eurotext.png"));
			System.out.println(str);
		} catch (Exception e) {
			e.getMessage();
		}
		
	
	}

}
