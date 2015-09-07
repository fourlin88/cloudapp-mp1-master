//import java.io.File;
import java.io.*;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MP12 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
        }
        return ret;
    }

    public MP12(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
    }

    public String[] process() throws Exception {
        String[] ret = new String[20];
       
        //TODO


    String[] tmp1 = new String[50000];
	Integer[] myindex = new Integer[10000];
	myindex = getIndexes();

	List<Integer> intList = new ArrayList<Integer>();
    	for (int index = 0; index < myindex.length; index++)
    	{
        	intList.add(myindex[index]);
    	}

        Map<String, Integer> wordCount = new HashMap<String, Integer>();     
//Creates an Hash Map for storing the words and its count

        //List<String> mylist = new List<String>(stopWordsArray);

        List myList = new ArrayList();
        Collections.addAll(myList, stopWordsArray);

	
        //ArrayList<String> lines = new ArrayList<String>();
        //ArrayList<String> sentences = new ArrayList<String>();
        String [] sentences = new String[50000];
        String [] randArray = new String[10000];

        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        String line = null;
		int linenum=0;
        while ((line = br.readLine()) != null) {
//String[] result = "this is a test".split("\\s");
//for (int x=0; x<result.length; x++)
//System.out.println(result[x]);
        line = line.toLowerCase().trim();
        //System.out.println(sentence);
        //
      	sentences[linenum]=line;
		linenum++; 
        //
		}
	// For each line in myindex, convert the line. 
		for (int index = 0; index < myindex.length; index++) {
		line = sentences[myindex[index]];
        //line = line.toLowerCase();
        StringTokenizer st = new StringTokenizer(line,delimiters);
        int i=0;
		String tmp2;
		Integer freq;
        while (st.hasMoreTokens()) {
		tmp2 = st.nextToken();
        //System.out.println(st.nextToken(delimiters));
        //System.out.println(i++);
        //        tmp1[i] = st.nextToken(delimiters);
        
		if (!myList.contains(tmp2)) {
		//System.out.println("Contains "+tmp2);
		// Proceed with recording this. 
			System.out.println(tmp2);
			freq = wordCount.get(tmp2);
			wordCount.put(tmp2, (freq == null) ? 1 : freq + 1);
		}
        }
                              //  for (String read : tmp1) {
                              //Integer freq = wordCount.get(read);
                              //wordCount.put(read, (freq == null) ? 1 : freq + 1)
//For Each word the count will be incremented in the Hashmap
                     //
                     //}
        linenum++;
        }
		System.out.println(wordCount);
        return ret;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP12 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "./input.txt";
            MP12 mp = new MP12(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}
