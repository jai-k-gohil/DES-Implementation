package des.utils;

public class Conversion
{
	static String[] Binary=new String[]{"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111" };
	
	//converts the hexadeciaml key to binary format in string
		//This all should go in conversion package
		public static String hexToBinary(String s)
		{
			int element;
			String output="";
			char switchelement;		
			for(int i=0; i < s.length();i++)
			{
				switchelement =s.charAt(i);
				switch(switchelement)
				{
						case 'A': 
						case 'a': element=10;break;
						case 'B':
						case 'b': element=11;break;
						case 'C':
						case 'c': element=12;break;
						case 'D':
						case 'd': element=13;break;
						case 'E':
						case 'e': element=14;break;
						case 'F':
						case 'f': element=15;break;
						default:element=Integer.parseInt(switchelement+"");break;
				}
				output=output+""+Binary[element];
			}
			return output;
		}

		public static String decimalToBinary(int decimal){
			String output="";
				output=output+""+Binary[decimal];
			return output;
		}
		// yhis function takes 4bit binary string and returns it equivalent decimal assumed that padding is done till 4-bits 
		public static int binaryToDecimal(String s){
			for(int i=0; i<16; i++){
				if(s.equals(Binary[i]))
				{
					return i;
				}
			}
			return -1;

		}

		public static String binaryToHex(String s){
			int element =binaryToDecimal(s);
			String result="";
				switch(element)
				{
					case 10: result=result+"A";break;
					case 11: result=result+"B";break;
					case 12: result=result+"C";break;
					case 13: result=result+"D";break;
					case 14: result=result+"E";break;
					case 15: result=result+"F";break;
					default: result=result+element;break;
				}
			return result;

		}
}