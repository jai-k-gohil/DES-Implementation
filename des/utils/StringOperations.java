package des.utils;

public class StringOperations{
	
    //both the function work the same as indexOf function
	public static void indexOf(String text,String find)
	{
    	char[] c =new char[text.length()];
    	char[] f =new char[find.length()];
    	text.getChars(0,text.length(),c,0);
    	find.getChars(0,find.length(),f,0);
    	

    	for (int i=0; i<(text.length()-find.length()); i++) {
    	  	if(c[i] == f[0])
    	  	{
        		String comp = text.substring(i,i+find.length());
        		if(comp.equals(find))
        		  System.out.println("String found at"+i +"and ends at"+(i+find.length()));
        	}
      	}
	}

	public static void indexOf(String text,String find,int startIndex)
	{
		String gotText = text.substring(startIndex,text.length());
    	indexOf(gotText,find);
	}

    public static void replace(String source,String search,String toReplace){
        String result ="";
        int i =source.indexOf(search);
        result=source.substring(0,i);
        result += toReplace;
        result += source.substring((i+toReplace.length()));
        source=result;
    }

    public static void replaceAll(String source,String search,String toReplace){
        String result="";
        int i;
        do{
            i=source.indexOf(search);
            if(i<source.length()){
                result=source.substring(0,i);
                result += toReplace;
                result += source.substring(i+toReplace.length());
                source=result;
            }
        }while(i<source.length());

    }

	public static String valueAt(String s,int value){
		return(s.substring(value ,value+1));
	}

	public static void split(String source,int startindex,int endindex,String part1,String part2){
    	int len=endindex - startindex;
    	part1=source.substring(startindex,(startindex+(len/2)));
    	part2=source.substring((startindex+(len/2)+1),endindex);
    }

	/*split binary into two parts C and D accepts source and two destination string 
	array to store at specifed index */
	public static void splitHalf(String source,String[] part1,String[] part2,int index){
				int len = source.length();
				part1[index]=source.substring(0,(len/2));
				part2[index]=source.substring((len/2));
	}

	//this is requires to make input string of desired length if not passed appropriately
	public static void padding(String source ,String topad,int len){
			String padded=source;
			for(int i=source.length(); i<len; i++)
				padded+=topad;
			source=padded;
	}

			// this converts the 48 bits into groups of 6 each
	public static String splitBy(String source,int membersInAGroup,int start){
				return source.substring(start,start+membersInAGroup);
	}
    
}

/*
For note if you do not remember all methods provided by string class are most frequently used:
 char charAt(int index)
 char[] toCharArray();
 boolean equalsIgnoreCase(String str);
 String trim(String str);
 int compareTo(String str);
*/