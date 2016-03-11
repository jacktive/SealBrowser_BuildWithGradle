package com.zxing.barcode.util;

import java.util.Hashtable;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * @author Ryan Tang
 *
 */
public final class EncodingHandler {
	private static final int BLACK = 0xff000000;
	//private static final int BLACK = 0xff256838;
	public static Bitmap createQRCode(String str,int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	

	

	public static Bitmap createQRCodeBitmap(int qrsize,String qrcontent) {

		Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();

		qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		


		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(qrcontent,
					BarcodeFormat.QR_CODE, qrsize, qrsize, qrParam);


			int w = bitMatrix.getWidth();
			int h = bitMatrix.getHeight();
			int[] data = new int[w * h];

			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					if (bitMatrix.get(x, y))
						data[y * w + x] = 0xff000000;
					else
						data[y * w + x] = -1;
				}
			}


			Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

			bitmap.setPixels(data, 0, w, 0, 0, w, h);
			return bitmap;
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}


