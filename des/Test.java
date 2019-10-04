package des;

public class Test{
    public static void main(String[] args) {
        //Just for testing purposes.
        DES des =new DES();
        DES des1 =new DES();
        DES des2 =new DES();
        String encryptedData=des.encryptPlainText("0123456789ABCDEF","133457799BBCDFF1");
        encryptedData=des1.encryptPlainText(encryptedData,"133457799BBCDFF1");
        encryptedData=des2.encryptPlainText(encryptedData,"133457799BBCDFF1");
        System.out.println("encryptedData is "+encryptedData);
        String decryptedText=des2.decryptCipherText(encryptedData,"133457799BBCDFF1");
        decryptedText=des1.decryptCipherText(decryptedText,"133457799BBCDFF1");
        decryptedText=des.decryptCipherText(decryptedText,"133457799BBCDFF1");
        System.out.println("decryptCipherText is :"+decryptedText);
    }
}