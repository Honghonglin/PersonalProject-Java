import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class WordCount {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            List<String> wordList = new ArrayList();//�����б�
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

            for (String word : wordList){
                System.out.println(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
