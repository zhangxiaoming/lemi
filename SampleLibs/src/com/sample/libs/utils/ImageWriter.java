package com.sample.libs.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public final class ImageWriter {

	public static void imageWriteToFile(Bitmap bitmap,File file) throws IOException{
		FileOutputStream out = new FileOutputStream(file);
		bitmap.compress(CompressFormat.PNG, 100, out);
		out.close();
	}
}
