package cps.lab.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cps.lab.signal.Signal;

public class IOUtils {
	
	
	public static void saveSignal(String path,Signal signal){
		File file = new File(path);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(signal);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public static Signal loadSignal(String path){
		File file = new File(path);
		Signal signal = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			signal = (Signal) inputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			return signal;
		}
	}

}
