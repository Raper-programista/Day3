import java.io.*;
import java.util.*;
public class List{

	private static ArrayList<String> numbersList = new ArrayList<>();
	private static int[][] numbers;

	static{
		initList();
		newInitList();
		initNumbers();
	}

	private static void initList(){

		try(BufferedReader reader = new BufferedReader(new FileReader("plik.txt"))){
			
			char last = ' ';
			String line ="";
			
			while(reader.ready()){
			
				int read = reader.read();
				char now = (char) read;

				if(now == '\n'){

					numbersList.add(line);

					line = "";
					last = ' ';

				} else if(!(last==' ' && now==' ') && now!=13){
					
					line += ""+(char) now;
					last = now;
				}
			}
		}catch(IOException e){
			System.out.println(e);
		}

	}
	
	private static void newInitList(){
	    try(BufferedReader reader = new BufferedReader(new FileReader("plik.txt"))){
	        while(reader.ready()){
	            String s = reader.readLine().replaceAll( " +", " ").trim();
	            numbersList.add(s);
	        }
	    }catch(IOException e){
	        System.out.println(e);
	    }
	    
	}

	private static void initNumbers(){
		int length = numbersList.size();
		numbers = new int[length][];

		for(int i=0; i<length; i++){
			numbers[i] = separate(numbersList.get(i).split(" "));
		}
			
	}

	private static int[] separate(String[] nums){
	    
	    int first = Integer.parseInt(nums[0]);
	    int second = Integer.parseInt(nums[1]);
	    int last = Integer.parseInt(nums[2]);
	    
	    int [] result = {first,second,last};

		return result;	
	}

	public static int[][] getNumbers(){
		return numbers;
	}

	public static String toWrite(){
		String s = "";
		for(int[] number : numbers){
			for(int n : number)
				s+=""+n+", ";
			s+='\n';
		}
		return s;
	}

	@Override
	public String toString(){
		String s="";
		for(String ln : numbersList)
			s+=ln+'\n';
		return s;
	}

}