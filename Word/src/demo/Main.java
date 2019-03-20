package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\workspace\\EnglishWords\\src\\demo\\essay.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\workspace\\EnglishWords\\src\\demo\\result.txt"));
        String s;
        Map<String, Integer> map = new TreeMap<String, Integer>();
        while ((s = bufferedReader.readLine()) != null) {
        	//通过键值对的方式去分别存储单词和出现的次数
            //Map<String, Integer> map = new TreeMap<String, Integer>();
            //创建一个words数组，将split分割的字符串存入数组
            String[] words = s.split("[【】、.。,\"!--;:?\'\\] ]");
            for (int i = 0; i < words.length; i++) {
            	String key = words[i].toLowerCase();//将所有单词转化为小写
                if (key.length() > 0) {
                	//用containsKey判断map集合对象中是否包含某个字符串
                    if (!map.containsKey(key)) {//如果不包括说明第一次出现,则给频率值赋1;
                    	map.put(key, 1);
                    	} else {// 如果不是第一次出现，就把value值++，那么value值是多少就是出现了几次
                    		int value = map.get(key);//用get(key)获取对应的value值
                    		value++;
                    		map.put(key, value);
                    		}
                    }
                }
            
           	}
        System.out.print("请输入想使用的功能：");
        System.out.println("1.查询一个单词出现次数；");
        System.out.println("2.查询前n名出现最多的单词；");
        System.out.println("3.输出全部词频，字典顺序排序；");
        System.out.println("4.降序排列全部词频。");
        Scanner scan = new Scanner(System.in);
        int read = scan.nextInt();
        
        if(read==1){
        	System.out.print("请输入想查询的单词：");
            Scanner scan1 = new Scanner(System.in);
            String word = scan1.nextLine();
            //判断是否存在所要查询的单词
            boolean b = map.containsKey(word);
            if(b){
            	//根据key单词查找次数value
            	for (Map.Entry<String, Integer> m :map.entrySet())  {

            		if (m.getKey().equals(word)) {
            		int v = m.getValue();
            		System.out.println(v);
            		}}
            }else {
				System.out.println("此单词不存在！");
			}
        }else if(read==2){
        	 System.out.print("请输入数字n查询前n名出现最多的单词:");
             Scanner scan2 = new Scanner(System.in);
             int n = scan2.nextInt();
             
             List<Map.Entry<String, Integer>> nlist = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
             Collections.sort(nlist, new Comparator<Map.Entry<String, Integer>>()
             {
                 @Override
                 public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
                 {
                     int compare = (o1.getValue()).compareTo(o2.getValue());
                     return -compare;
                 }
             });

             Map<String, Integer> result = new LinkedHashMap<String, Integer>();
             for (Map.Entry<String, Integer> entry : nlist) {
                 result.put(entry.getKey(), entry.getValue());
             }
             
             for (String a : result.keySet()) {
             	System.out.println( a + "-----" + result.get(a));
             	}
             
             
        }else if(read==3){
        	 //利用TreeMap实现Comparator接口
            Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            	public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) { 
            		return o1.getKey().compareTo(o2.getKey());//降序排序
           		}
            }; 
            //map转换成list进行排序，Entry是Map中的一个静态内部类，用来表示Map中的每个键值对
            //map.EntrySet(),实现了Set接口，里面存放的是键值对.
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet()); 
            // 排序
            Collections.sort(list,valueComparator); 
            System.out.println("已生成result.txt文件");
            System.out.println("-----------------所有单词按字典顺序排序如下---------------");
          	for (Map.Entry<String, Integer> entry : list) { 
          		System.out.println(entry.getKey() + "----" + entry.getValue());
           		bufferedWriter.write(entry.getKey()+"----"+entry.getValue()+"\r\n");
           		}
              
        }else if(read==4){
         
	         List<Map.Entry<String, Integer>> nlist = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
	         Collections.sort(nlist, new Comparator<Map.Entry<String, Integer>>()
	         {
	             @Override
	             public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
	             {
	                 int compare = (o1.getValue()).compareTo(o2.getValue());
	                 return -compare;
	             }
	         });
	
	         Map<String, Integer> result = new LinkedHashMap<String, Integer>();
	         for (Map.Entry<String, Integer> entry : nlist) {
	             result.put(entry.getKey(), entry.getValue());
	         }
	
	         //升序
	         //Map<String, Integer> result = new LinkedHashMap<>();
	         //Stream<Entry<String, Integer>> st = map.entrySet().stream();
	         //st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
	         for (String a : result.keySet()) {
	         	System.out.println( a + "-----" + result.get(a));
	         	}
	         
	         
	    }else {
        	System.out.println("输入错误！");
		}
        
        bufferedWriter.newLine();
        // 关闭输入输出流
        bufferedReader.close();
        bufferedWriter.close();
    }
}
