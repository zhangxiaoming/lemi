package com.sample.libs.image.core.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;

public class ExtendedImageDownLoader extends URLConnectionImageDownloader {

	public static final String PROTOCOL_ASSETS = "assets";
	public static final String PROTOCOL_DRAWABLE = "drawable";

	private static final String PROTOCOL_ASSETS_PREFIX = PROTOCOL_ASSETS
			+ "://";
	private static final String PROTOCOL_DRAWABLE_PREFIX = PROTOCOL_DRAWABLE
			+ "://";

	private Context mContext;

	public ExtendedImageDownLoader(Context context) {
		this.mContext = context;
	}
	

	@Override
	protected InputStream getStreamFromOtherSource(URI imageUri)
			throws IOException {
		String schema = imageUri.getScheme();
		if(PROTOCOL_ASSETS_PREFIX.equalsIgnoreCase(schema)){
			return getStreamFromAssets(imageUri);
		}else if(PROTOCOL_DRAWABLE_PREFIX.equalsIgnoreCase(schema)){
			return getStreamFromDrawables(imageUri);
		}
		return super.getStreamFromOtherSource(imageUri);
	}
	
	protected InputStream getStreamFromAssets(URI imageUri) throws IOException{
		String filePath = imageUri.toString().substring(PROTOCOL_ASSETS_PREFIX.length()); // Remove "assets://" prefix from image URI
		return mContext.getAssets().open(filePath);
	}
	
	protected InputStream getStreamFromDrawables(URI imageUri){
		String filePath = imageUri.toString().substring(PROTOCOL_DRAWABLE_PREFIX.length());
		int resId = Integer.parseInt(filePath);
		
		Bitmap bitmap =((BitmapDrawable) mContext.getResources().getDrawable(resId)).getBitmap();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 100, out);
		return new ByteArrayInputStream(out.toByteArray());
		
	}
	
	

}
