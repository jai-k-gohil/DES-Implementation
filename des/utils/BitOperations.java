package des.utils;

public class BitOperations
{
	//To xor the string and return it ie string is binary
	public static String xor(String s1,String s2){
		String result ="";
		int length=Math.min(s1.length(),s2.length());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <length; i++) {
      		sb.append(charOf(bitOf(s1.charAt(i)) ^ bitOf(s2.charAt(i))));
    	}
    	result = sb.toString();
    	return result;
	}
	private static boolean bitOf(char in) {return(in=='1'? true:false);}
	private static char charOf(boolean in) {return (in==true? '1' :'0');}

	/*function that gets values of matrix and a binary string and returns another matrix 
	that contains all the digits at specified location*/
	public static String permute(int[][] matrix,String source){
		String result="";
		for (int i=0; i < matrix.length; i++)
		{
			for (int j=0; j < matrix[i].length ; j++)
			{
				result += source.charAt(matrix[i][j]-1);
			}
		}
		return result;
	}

	public static String repermute(int matrix[][],String input){
		char[] output =input.toCharArray();
		int k=0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++){ 
				output[matrix[i][j]-1] = input.charAt(k++);
			}
		}
		return new String(output);
	}

		//function to leftshift which takes string array and its index and how many times left shift has to be performed
	public static String leftShift(String[] value,int index,int times){
		char[] stringarray=new char[value[index].length()];
		value[index].getChars(0,value[index].length(),stringarray,0);
		int j=0;
		char[] temp =new char[5];
		for (int i=0;i<times;i++)
			temp[i]=stringarray[i];
		for(int i=0; i<times; i++){
			for(j=0; j<value[index].length()-1; j++){
				stringarray[j]=stringarray[j+1];
			}
			stringarray[j]=temp[i];
		}
		return  (new String(stringarray));
	}
}