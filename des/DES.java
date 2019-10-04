package des;

import des.utils.StringOperations;
import des.utils.Conversion;
import des.utils.BitOperations;

public class DES{

	BitOperations bytes =new BitOperations();
	Keys pt =new Keys();
		//all variable declarations for key are here

		String key = new String();
		String[] keys = new String[16];
		String[] C = new String[20];
		String[] D = new String[20];

		//all varibale declarations for plain txt are here 
		
		String message  = new String();
		String[] L = new String[20];
		String[] R = new String[20];
		String[][] B = new String[16][8];
		String[] SB = new String[16];
		String[] PFinal = new String[16];
		String[] plaintxt = new String[16];

		void encryptKey(String s){
			//convert string from hex to four digit binary
			key=Conversion.hexToBinary(s);
    		//this will return the string from permutations from PC1 to key
    		key=bytes.permute(pt.PC1,key);
    		//split the key56 into C0 and D0
			StringOperations.splitHalf(key,C,D,0);
			//leftShift C0 and D0 till C16 and D16 according to numberOfLeftShift table
			for (int i=0 ;i < 16; i++){
					C[i+1] = bytes.leftShift(C,i,pt.numberOfLeftSHift[i]);
					D[i+1] = bytes.leftShift(D,i,pt.numberOfLeftSHift[i]);
			}

			for(int i=1; i<=16; i++) {
				keys[i-1]=bytes.permute(pt.PC2,join(C,D,i));
			}

		}
		
//this function is to encrypt plain text

 		String encryptPlainText(String data,String key){
 				encryptKey(key);
				message=Conversion.hexToBinary(data);
				message=bytes.permute(pt.IP,message);
				StringOperations.splitHalf(message,L,R,0);
				for (int i=1;i<=16;i++){
					L[i]=R[i-1];
					R[i]=bytes.xor(L[i-1],ffunction(R,keys,i-1));
				}
				String ipinverse_op=bytes.permute(pt.IPInverse,(R[16]+L[16]));
				int old=0;
				for (int i=0;i<16;i++,old+=4)
					SB[i]=StringOperations.splitBy(ipinverse_op,4,old);
				String encryptedText="";
				for (int i=0;i<16;i++)
					encryptedText +=Conversion.binaryToHex(SB[i]);
			
			return	encryptedText;
		}

		String decryptCipherText(String encryptedText,String key){
			encryptKey(key);
			encryptedText=Conversion.hexToBinary(encryptedText);
			encryptedText=bytes.repermute(pt.IPInverse,encryptedText);

			//now split into two parts and swap
			int len=encryptedText.length();
			R[16]=encryptedText.substring(0,len/2);
			L[16]=encryptedText.substring(len/2);

			for (int i=16;i>=1;i--){
					R[i]=L[i-1];
					L[i]=bytes.xor(R[i-1],ffunction(L,keys,i-1));
			}
			encryptedText=bytes.repermute(pt.IP,join(L,R,0));
			String decryptedText="";
			int old=0;
				for (int i=0;i<16;i++,old+=4)
					SB[i]=StringOperations.splitBy(encryptedText,4,old);
				for (int i=0;i<16;i++)
					decryptedText +=Conversion.binaryToHex(SB[i]);
			return	decryptedText;
		}
		
		String ffunction(String[] R,String[] keys,int index){
			String result ="",sbox_op="";
			int old=0;
			result =bytes.permute(pt.E_Bit_Selection,R[index]);
			result =bytes.xor(result,keys[index]);
			for (int i=0;i<8;i++,old+=6)
				B[index][i]=StringOperations.splitBy(result,6,old);
			for (int i=0;i<8;i++){
				int row =Conversion.binaryToDecimal(getRow(B[index][i]));
				int col=Conversion.binaryToDecimal(getCol(B[index][i]));
				sbox_op+=Conversion.decimalToBinary(pt.SBOX[i][row][col]);
			}
			result =bytes.permute(pt.P,sbox_op);
			return result;
		}
		//this function splits the 6 bits string into two parts in a different manner
		String getRow(String source){	return ("00"+source.charAt(0)+source.charAt(5));	}
		String getCol(String source){	return source.substring(1,5);	}
		String join(String[] part1,String[] part2,int index){	return (part1[index]+part2[index]);	}
		

		String matrixElementInBinary(int round,int times){
				String result="";
				for(int i=0; i < times; i++) {
					//get rows and columns from B table
					int row = Conversion.binaryToDecimal(getRowFromB(round,i));
					int column = Conversion.binaryToDecimal(getColumnFromB(round,i));
					result += Conversion.decimalToBinary(getValue(i,row,column));
				}
			return result;
		}
		private String getRowFromB(int round,int index){return ("00"+B[round][index].charAt(0)+B[round][index].charAt(5));}
		private String getColumnFromB(int round,int index){return (B[round][index].substring(1,5));}
		private int getValue(int sbox,int row,int column){return (pt.SBOX[sbox][row][column]);}
}   