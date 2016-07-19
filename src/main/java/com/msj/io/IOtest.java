package com.msj.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

@SuppressWarnings("resource")
public class IOtest {

	public static void main(String[] args) {
		IOtest test = new IOtest();
		try {
//			test.outputstream();
//			test.inputSteam();
//			test.writer();
//			test.read();
			test.bufferOutPutStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 基于字节输出
	 * @throws IOException
	 */
	public void outputstream() throws IOException{
		File file = new File("E:/test/1.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		out.write("hello".getBytes());
		out.flush();
		out.close();
	}
	
	/**
	 * 基于字节读取
	 * @throws IOException
	 */
	public void inputSteam() throws IOException{
		File file = new File("E:/test/1.txt");
		
		FileInputStream input = new FileInputStream(file);
		byte[] b = new byte[512];
		int len = 0;
		String result = "";
		while ((len = input.read(b)) != -1) {
			result = result + new String(b, 0, len);
		}
		System.out.println(result.toString());
	}
	
	/**
	 * 基于字符流输出
	 * @throws IOException
	 */
	public void writer() throws IOException{
		File file = new File("E:/test/2.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);
		fw.write("hello2");
		fw.flush();
		fw.close();
	}
	
	/**
	 * 基于字符流读取
	 * @throws IOException
	 */
	public void read() throws IOException{
		File file = new File("E:/test/2.txt");
		FileReader fr = new FileReader(file);
		char[] b = new char[512];
		int len = 0;
		String result = "";
		while ((len = fr.read(b)) != -1) {
			result = result + new String(b, 0, len);
		}
		System.out.println(result.toString());
	}
	
	/**
	 * 基于带缓存字节流输出
	 * @throws IOException
	 */
	public void bufferOutPutStream() throws IOException{
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("E:/test/1.txt", true));
		
		bout.write(" \n 123123".getBytes());
		bout.write("ljlsdjfkds".getBytes());
		bout.flush();
		bout.close();
	}
	
	
	
	public void bufferRead() throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(new File("E:/test/1.txt")));
	}
	
	
	
}
