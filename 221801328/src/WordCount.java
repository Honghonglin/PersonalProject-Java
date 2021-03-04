import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class WordCount {

    public static void main(String[] args) {

        String inputFileName = "input.txt";

        int words = countWords(inputFileName);
        int chars = countChars(inputFileName);
        int lines = countLines(inputFileName);
        ArrayList<Map.Entry<String,Integer>> list = countWordFrequency(inputFileName);

        String outputFileName = "output.txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
            bw.write(String.valueOf(words)+"\n");
            bw.write(String.valueOf(chars)+"\n");
            bw.write(String.valueOf(lines)+"\n");


            for(int i = 0;i < list.size() && i < 10;i++){
               // System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
                bw.write(list.get(i).getKey()+ ": " +list.get(i).getValue()+"\n");
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*ͳ�Ƶ�����*/
    public static int countWords(String inputFile) {
        List<String> wordList = getWordList(inputFile);
        int count = wordList.size();
        return count;
    }

    /*ͳ�Ƶ�����*/
    public static List<String> getWordList(String inputFile) {
        int count = 0;
        List<String> wordList = new ArrayList();//�����б�
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));

            String line = null;
            //һ��һ�ж�ȡ�ļ�
            while((line = br.readLine()) != null){

                //�ָ�����ո񣬷���ĸ���ַ���,�Էָ�����ָ������
                String[] words = line.split("[^a-zA-Z0-9]");
                //���ʣ�������4��Ӣ����ĸ��ͷ
                Pattern pattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");
                for (String word : words){
                    if (word.length() >= 4) {
                        //������ʽ�жϵ����Ƿ�Ϸ�
                        if (pattern.matcher(word).matches()){
                            //ͳһתСд
                            wordList.add(word.toLowerCase());
                        }
                    }
                }
            }
            br.close();
            //ͳ�Ƶ�����
            count = wordList.size();
            System.out.println(count);

//            for (String word : wordList){
//                System.out.println(word);
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return wordList;
        }
    }


    /*ͳ��ascii�ַ���*/
    public static int countChars(String inputFile) {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            int ch = -1;
            while ((ch = br.read()) != -1) {
                //����ascii�룬�ͼ���
                if (ch <=127 )
                    count++;
//                System.out.println(ch);
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return count;
        }

    }

    /*ͳ���ļ�����Ч����,�κΰ���**�ǿհ�**�ַ����У�����Ҫͳ�ơ�*/
    public static int countLines(String inputFile){
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = "";
            while((line = br.readLine())!=null){
                //�ж��Ƿ�Ϊ�հ���
                //if (!line.isBlank())
                //    count++;
                if(!(line.trim().isEmpty()))
                    count++;
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return count;
        }
    }

    /*ͳ�ƴ�Ƶ��ֻ�����������10��*/
    public static ArrayList countWordFrequency(String inputFile)
    {
        List<String> wordList = getWordList(inputFile);
        //key ����  value ���ִ���
        Map<String, Integer> words = new TreeMap<String,Integer>();

        //������������ count ++
        for (String word : wordList){
            if (words.containsKey(word))
                words.put(word,words.get(word)+1);
            else//���map��û��������ʣ���ӽ�ȥ��count=1
                words.put(word,1);
        }
        ArrayList<Map.Entry<String,Integer>> list = sortMap(words);    //��ֵ��������

        return list;
    }

    /*��map��value����*/
    public static ArrayList sortMap(Map<String,Integer> oldmap){

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //��������
                return o2.getValue() - o1.getValue();
            }
        });


        for(int i = 0;i < list.size() && i < 10;i++){
            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
        }

        return list;
    }
}
