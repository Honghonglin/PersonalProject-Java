import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Lib {

    private int words = 0;
    private int chars = 0;
    private int lines = 0;
    private ArrayList<Map.Entry<String,Integer>> wordList;
    private String inputFileName;
    private String outputFileName;

    public Lib(String input,String output){
        inputFileName = input;
        outputFileName = output;
    }

    public void startCount() {
        words = countWords(inputFileName);
        chars = countChars(inputFileName);
        lines = countLines(inputFileName);
        wordList = countWordFrequency(inputFileName);
    }

    public void outputResult() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
            bw.write("characters: "+chars +"\n");
            bw.write("words: "+words +"\n");
            bw.write("lines: "+lines +"\n");

            for(int i = 0;i < wordList.size() && i < 10;i++){
                // System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
                bw.write(wordList.get(i).getKey()+ ": " +wordList.get(i).getValue()+"\n");
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*ͳ�Ƶ�����*/
    public int countWords(String inputFile) {
        List<String> wordList = getWordList(inputFile);
        int count = wordList.size();
        return count;
    }

    /*��ȡ�����б�*/
    public List<String> getWordList(String inputFile) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }


    /*ͳ��ascii�ַ���*/
    public int countChars(String inputFile) {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            while ((br.read()) != -1)
                count++;
//            int ch = -1;
//            while ((ch = br.read()) != -1) {
//                //����ascii�룬�ͼ���
//                if (ch <=127 )
//                    count++;
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*ͳ���ļ�����Ч����,�κΰ��� �ǿհ� �ַ����У�����Ҫͳ�ơ�*/
    public int countLines(String inputFile){
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = "";
            while((line = br.readLine())!=null){
                //�ж��Ƿ�Ϊ�հ���
                //if (!line.isBlank())java8û�����
                //   count++;
                if (!(line.trim().isEmpty()))
                    count++;
            }
//            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*ͳ�ƴ�Ƶ��ֻ�����������10��*/
    public ArrayList countWordFrequency(String inputFile)
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
    public ArrayList sortMap(Map<String,Integer> oldmap){

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //��������
                return o2.getValue() - o1.getValue();
            }
        });
        return list;
    }

    public void printWords(ArrayList<Map.Entry<String,Integer>> list) {

        for(int i = 0;i < list.size() && i < 10;i++){
            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
        }
    }
}
