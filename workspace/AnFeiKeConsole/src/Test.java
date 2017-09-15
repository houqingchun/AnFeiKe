import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.TimeUtil;

public class Test {
	public void bufferWriteRead(String fileName, String msg) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(fileName));

			bw.write(msg);
			bw.flush();

			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String s;
			while ((s = br.readLine()) != null) {
				bw.write(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}

	}

	public void exeCommandLine() {
		try {
			Runtime.getRuntime().exec("cmd /c del file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ioReadWrite() {
		String fileStr = "C:\temp\test.txt";
		File f = new File(fileStr);

		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileStr));
			byte[] byteArray = new byte[1024];
			int bt;
			while ((bt = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bt);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void readFromConsole() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a double value:");
		double d = sc.nextDouble();
		System.out.println("You entered : " + d);
	}

	public static String formatString(String[] s) {
		MessageFormat mf = new MessageFormat("你好{0}");
		return mf.format(s);
	}

	public static String nbrFmt(Double value) {
		return NumberFormat.getInstance().format(value);
	}
	
	public static void testThreadExecutor(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		Test t = new Test();
		for (int i = 0; i < 10; i++) {
			es.execute(t.new MyThread(i));
		}
		es.shutdown();
		try {
			es.awaitTermination(2, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testCallableExecutor(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		Test t = new Test();
		
		List<Future<Long>> callList = new ArrayList<Future<Long>>();
		
		for (int i = 0; i < 20000; i++) {
			MyCallable c = t.new MyCallable();
			Future<Long> f = es.submit(c);
			callList.add(f);
		}
		
		int sum = 0;
		System.out.println(callList.size());
		for (Future<Long> f : callList){
			try {
				sum += f.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(sum);
		es.shutdown();
	}
	
	public static int binarySearch(Integer[] srcArray, int des){
		int low = 0;
		int high = srcArray.length - 1;
		while(low <=high){
			int mid = low + ((high-low)>>1);
			int value = srcArray[mid];
			if (value == des){
				return mid;
			}else if (des < value){
				high = mid - 1;
			}else{
				low = mid + 1; 
			}
		}
				
		return -1;
	}
	
	public static int binSearch(Integer[] srcArray, int low, int high, int key){
		if (low < high){
			int mid = (low + high)/2;
			if (key == srcArray[mid]){
				return mid;
			}else if (key < srcArray[mid]){
				high = mid - 1;
				return binSearch(srcArray, low, high, key);
			}else{
				low = mid + 1;
				return binSearch(srcArray, low, high, key);
			}
		}else{
			return -1;
		}
	}
	
	public static void binarySearchTest(){
		Integer[] array = new Integer[100];
		
		for (int i = 0; i < 100; i++){
			array[i] = i;
		}
		
		System.out.println("binarySearch result: " + binSearch(array, 0, 99, 49));
		System.out.println("binarySearch result: " + binarySearch(array, 49));
	}
	
	public static void quickSort2(int[] data, int start, int end){
		int key = data[start];
		
		int i = start;
		int j = end;
		
		while(i < j){
			while(data[j] > key && i < j){
				j--;
			}
			
			data[i] = data[j];
			
			while(data[i] < key && i < j){
				i++;
			}
			
			data[j] = data[i];
		}
		
		data[i] = key;
		
		if (i - 1 > start){
			quickSort2(data, start, i-1);
		}
		
		if (i + 1 < end){
			quickSort2(data, i + 1, end);
		}
	}
	
	public static void quickSort(int[] data, int start, int end){
		int key = data[start];
		
		int i = start;
		int j = end;
		
		while(i < j){
			while(data[j] > key && i < j){
				j--;
			}
			
			//move low to left 
			data[i] = data[j];
			
			while(data[i] < key && i < j){
				i++;
			}
			//move high to right 			
			data[j] = data[i];
		}
		
		data[i] = key;
		
		//递归左边排序
		if ((i-1) > start){
			quickSort(data, start, i-1);
		}
		
		//递归右边排序
		if (i + 1 < end){
			quickSort(data, i + 1, end);
		}
	}
	
	public static void quickSortTest(){
		int[] p = { 34, 21, 54, 18, 23, 76, 38, 98, 45, 33, 27, 51, 11, 20, 79,  
                30, 89, 41 };  

		
        quickSort(p, 0, p.length - 1);// 快速排序
        for (int i = 0; i < p.length; i++){
        	System.out.print(p[i] + ", ");
        }
	}
	
	
	public static List<Integer> sortList(List<Integer> target){
		Collections.sort(target, new Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				int i1 = (Integer)o1;
				int i2 = (Integer)o2;
				if (i1 > i2){
					return 1;
				}else{
					return -1;
				}
			}
			
		});
		
		return target;
	}
	
	public static void sortListTest(){
		List<Integer> array = new ArrayList<Integer>();
		
		for (int i = 0; i < 100; i++){
			array.add((int) (Math.random()*100));
		}
		
		System.out.println("original: " + array);
		System.out.println("sort result: " + sortList(array));
	}

	public static void main(String[] args) {
		// Test.readFromConsole();
		String s = "abc%s123";
		// System.out.println(Test.formatString(new String[]{"ab"}));
		// System.out.println(Test.nbrFmt(24294.20));
		
//		testCallableExecutor();
//		binarySearchTest();
//		sortListTest();
		quickSortTest();
	}

	class MyThread implements Runnable {
		int seq;
		volatile int count;

		public MyThread(int seq) {
			this.seq = seq;
		}

		@Override
		public void run() {
			try {
				// count++;
				Thread.sleep((long) (Math.random() * 10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread " + seq + " count: " + count++);
		}
	}
	
	class MyCallable implements Callable<Long> {

		@Override
		public Long call() throws Exception {
			long k = 0;
			for (long i = 0; i <= 100; i++) {
				k += i;
			}
			return k;
		}

	}
}
