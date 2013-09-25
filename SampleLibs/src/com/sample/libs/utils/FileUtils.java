package com.sample.libs.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Provides operations with files
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class FileUtils {

	private static final int BUFFER_SIZE = 8 * 1024; // 8 KB

	private FileUtils() {
	}

	public interface DataProgressCallBack {
		public int onProgress(int progress);
	}

	/*
	 * copyStream one stream to one stream;
	 */
	public static void copyStream(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		int count;
		while ((count = is.read(bytes, 0, BUFFER_SIZE)) != -1) {
			os.write(bytes, 0, count);
		}

		is.close();
		os.close();
	}

	public static void copyStream2(InputStream is, OutputStream os,
			DataProgressCallBack callback) throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		int count = -1, total = 0;
		while ((count = is.read(bytes, 0, BUFFER_SIZE)) != -1) {
			os.write(bytes, 0, count);
			total += count;
			if (callback != null) {
				callback.onProgress(total);
			}
		}
		is.close();
		os.close();
	}

	/*
	 * inputStream to byte array
	 */
	public static byte[] toByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] bytes = new byte[BUFFER_SIZE];
		int count;
		while ((count = in.read(bytes, 0, BUFFER_SIZE)) != -1) {
			out.write(bytes, 0, count);
		}
		in.close();
		out.close();
		return out.toByteArray();
	}

	/*
	 * copyFile:
	 */
	public static void copyFile(File src, File dest,
			DataProgressCallBack callback) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dest);
		copyStream2(in, os, callback);
	}

	/*
	 * String parmas etc:logcat,-d
	 */
	public static InputStream getDeviceInputLogs(String... parmas)
			throws IOException {
		ProcessBuilder builder = new ProcessBuilder(parmas);
		builder.redirectErrorStream(true);
		Process process = builder.start();
		return process.getInputStream();
	}

	public static OutputStream getDeviceOutPutLogs(String... parmas)
			throws IOException {
		ProcessBuilder builder = new ProcessBuilder(parmas);
		builder.redirectErrorStream(true);
		Process process = builder.start();
		return process.getOutputStream();
	}

	/*
	 * write byteArray to File
	 */

	public static void writeBytesToFile(File file, byte[] bytes)
			throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream(file);
		if (file.canWrite()) {
			try {
				out.write(bytes);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
