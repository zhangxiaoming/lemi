package com.sample.libs.image.core.download;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import com.sample.libs.image.core.HttpImage;
import com.sample.libs.image.core.assist.FlushedInputStream;

/**
 * Default implementation of ImageDownloader. Uses {@link URLConnection} for image stream retrieving.
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class URLConnectionImageDownloader extends ImageDownloader {

	/** {@value} */
	public static final int DEFAULT_HTTP_CONNECT_TIMEOUT = 5 * 1000; // milliseconds
	/** {@value} */
	public static final int DEFAULT_HTTP_READ_TIMEOUT = 20 * 1000; // milliseconds

	private int connectTimeout;
	private int readTimeout;

	public URLConnectionImageDownloader() {
		this(DEFAULT_HTTP_CONNECT_TIMEOUT, DEFAULT_HTTP_READ_TIMEOUT);
	}

	public URLConnectionImageDownloader(int connectTimeout, int readTimeout) {
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
	}

	@Override
	public InputStream getStreamFromNetwork(URI imageUri) throws IOException {
		URLConnection conn = imageUri.toURL().openConnection();
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		return new FlushedInputStream(new BufferedInputStream(conn.getInputStream(), BUFFER_SIZE));
	}
	
	public OutputStream  getStreamFromURL(URI iamgeUri,HttpImage image) throws IOException{
		URLConnection _urlConnection = null;
		OutputStream out= new FileOutputStream(image.filePath);
		
		_urlConnection = iamgeUri.toURL().openConnection();
		_urlConnection.setConnectTimeout(DEFAULT_HTTP_CONNECT_TIMEOUT);
		_urlConnection.setReadTimeout(readTimeout);
		BufferedInputStream buf =new BufferedInputStream(_urlConnection.getInputStream());
		
		int count =0; 
		byte buffer[] = new byte[1024];
		final int byteCount = 1024;
		while((count =buf.read(buffer, 0, byteCount) ) != -1){
			out.write(buffer, 0, count);
		}		
		out.flush();
		out.close();
		return out;
	}
}