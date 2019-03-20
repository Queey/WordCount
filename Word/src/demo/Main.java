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
        	//ͨ����ֵ�Եķ�ʽȥ�ֱ�洢���ʺͳ��ֵĴ���
            //Map<String, Integer> map = new TreeMap<String, Integer>();
            //����һ��words���飬��split�ָ���ַ�����������
            String[] words = s.split("[������.��,\"!--;:?\'\\] ]");
            for (int i = 0; i < words.length; i++) {
            	String key = words[i].toLowerCase();//�����е���ת��ΪСд
                if (key.length() > 0) {
                	//��containsKey�ж�map���϶������Ƿ����ĳ���ַ���
                    if (!map.containsKey(key)) {//���������˵����һ�γ���,���Ƶ��ֵ��1;
                    	map.put(key, 1);
                    	} else {// ������ǵ�һ�γ��֣��Ͱ�valueֵ++����ôvalueֵ�Ƕ��پ��ǳ����˼���
                    		int value = map.get(key);//��get(key)��ȡ��Ӧ��valueֵ
                    		value++;
                    		map.put(key, value);
                    		}
                    }
                }
            
           	}
        System.out.print("��������ʹ�õĹ��ܣ�");
        System.out.println("1.��ѯһ�����ʳ��ִ�����");
        System.out.println("2.��ѯǰn���������ĵ��ʣ�");
        System.out.println("3.���ȫ����Ƶ���ֵ�˳������");
        System.out.println("4.��������ȫ����Ƶ��");
        Scanner scan = new Scanner(System.in);
        int read = scan.nextInt();
        
        if(read==1){
        	System.out.print("���������ѯ�ĵ��ʣ�");
            Scanner scan1 = new Scanner(System.in);
            String word = scan1.nextLine();
            //�ж��Ƿ������Ҫ��ѯ�ĵ���
            boolean b = map.containsKey(word);
            if(b){
            	//����key���ʲ��Ҵ���value
            	for (Map.Entry<String, Integer> m :map.entrySet())  {

            		if (m.getKey().equals(word)) {
            		int v = m.getValue();
            		System.out.println(v);
            		}}
            }else {
				System.out.println("�˵��ʲ����ڣ�");
			}
        }else if(read==2){
        	 System.out.print("����������n��ѯǰn���������ĵ���:");
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
        	 //����TreeMapʵ��Comparator�ӿ�
            Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            	public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) { 
            		return o1.getKey().compareTo(o2.getKey());//��������
           		}
            }; 
            //mapת����list��������Entry��Map�е�һ����̬�ڲ��࣬������ʾMap�е�ÿ����ֵ��
            //map.EntrySet(),ʵ����Set�ӿڣ������ŵ��Ǽ�ֵ��.
            List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet()); 
            // ����
            Collections.sort(list,valueComparator); 
            System.out.println("������result.txt�ļ�");
            System.out.println("-----------------���е��ʰ��ֵ�˳����������---------------");
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
	
	         //����
	         //Map<String, Integer> result = new LinkedHashMap<>();
	         //Stream<Entry<String, Integer>> st = map.entrySet().stream();
	         //st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));
	         for (String a : result.keySet()) {
	         	System.out.println( a + "-----" + result.get(a));
	         	}
	         
	         
	    }else {
        	System.out.println("�������");
		}
        
        bufferedWriter.newLine();
        // �ر����������
        bufferedReader.close();
        bufferedWriter.close();
    }
}
