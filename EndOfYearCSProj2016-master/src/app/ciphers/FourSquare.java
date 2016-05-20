   package app.ciphers;

   import app.util.Alphabet;

   import java.util.ArrayList;
   import java.util.List;

   public class FourSquare {
   
      public static String encrypt(String input1, String ciphertxt1, String ciphertxt2) {
         StringBuilder encrypted = new StringBuilder();
         String input = sanitize(input1);
         char[][] cipher=to10x10(ciphertxt1,ciphertxt2);
         int x1,x2,y1,y2;  
         if(input.length()%2==1)
            input=input+"x";
         String[] inputtxt= new String[input.length()];
         	
         for(int x=0; x<input.length(); x++)
            inputtxt[x]=input.substring(x,x+1);
      	
         for(int b=0;b<inputtxt.length-1;b+=2){
            x1 = getAlphaXPos(cipher, inputtxt[b]);
            y1 = getAlphaYPos(cipher, inputtxt[b+1]);
            x2 = getAlphaXPos(cipher, inputtxt[b+1]);
            y2 = getAlphaYPos(cipher, inputtxt[b]);
         
            encrypted.append(cipher[x1][y1+5]);
            encrypted.append(cipher[x2+5][y2]);
         }
         return encrypted.toString();
      }
   	
      public static String decrypt(String input1, String ciphertxt1, String ciphertxt2) {
         StringBuilder decrypted = new StringBuilder();
         String input = sanitize(input1);
         char[][] cipher=to10x10(ciphertxt1,ciphertxt2);
         int x1,x2,y1,y2;  
         String[] inputtxt= new String[input.length()];
         	
         for(int x=0; x<input.length(); x++)
            inputtxt[x]=input.substring(x,x+1);
      	
         for(int b=0;b<inputtxt.length-1;b+=2){
            x1 = getTRXPos(cipher, inputtxt[b]);//[0,4]
            y1 = getBLYPos(cipher, inputtxt[b+1]);//[0,4]
            x2 = getBLXPos(cipher, inputtxt[b+1]);//[5,9]
            y2 = getTRYPos(cipher, inputtxt[b]);//[5,9]
         
            System.out.println("TRX"+x1);
            System.out.println("BLY"+y1);
            System.out.println("BLX"+x2);
            System.out.println("TRY"+y2);
         
            decrypted.append(cipher[x1][y1]);
            decrypted.append(cipher[x2][y2]);
         }
         return decrypted.toString();
      }
   	
   	
   	//Gets X index from the alphabet 5x5 blocks
      public static int getAlphaXPos(char[][] cipher, String input){
         for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
               if(input.equals(""+cipher[x][y]))
                  return x;
            }
         }
         //assert(false);
         return -1;
      }
      //Gets Y index from the alphabet 5x5 blocks
      public static int getAlphaYPos(char[][] cipher, String input){
         for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
               if(input.equals(""+cipher[x][y]))
                  return y;
            }
         }
         //assert(false);
         return -1;
      }  
      
   	//Gets X index for the Bot Left 5x5 block
      public static int getBLXPos(char[][] cipher, String input){
         for(int x=5;x<10;x++){
            for(int y=0;y<5;y++){
               if(input.equals(""+cipher[x][y]))
                  return x;
            }
         }
         //assert(false);
         return -1;
      }  
      //Gets Y index for the Bot Left 5x5 block
      public static int getBLYPos(char[][] cipher, String input){
         for(int x=5;x<10;x++){
            for(int y=0;y<5;y++){
               if(input.equals(""+cipher[x][y]))
                  return y;
            }
         }
         //assert(false);
         return -1;
      }  
      //Gets X index for the Bot Left 5x5 block
      public static int getTRXPos(char[][] cipher, String input){
         for(int x=0;x<5;x++){
            for(int y=5;y<10;y++){
               if(input.equals(""+cipher[x][y]))
                  return x;
            }
         }
         //assert(false);
         return -1;
      }  
      //Gets Y index for the Bot Left 5x5 block
      public static int getTRYPos(char[][] cipher, String input){
         for(int x=0;x<5;x++){
            for(int y=5;y<10;y++){
               if(input.equals(""+cipher[x][y]))
                  return y;
            }
         }
         //assert(false);
         return -1;
      }  
    /**
     * Constructs a 10x10 2D-array that contains in its upper-left and lower-right corners
     * the letters of the alphabet (with 'q' being removed). The upper-right corner contains the
     * letters of the first code phrase in the order they appear, with no repeating letters.
     * If not all of the letters in the alphabet appear in the code phrase, the remaining
     * spaces in the upper-right corner are filled with the letters of the alphabet, starting at
     * the beginning and going until all spaces in that corner have been filled. The same process
     * applies to the lower-left corner except using the second code phrase.
     *
     * @param codePhrase1 the first phrase/word used to construct the array
     * @param codePhrase2 the second phrase/word used to construct the array
     * @return a 10x10 String[][] with contents as described above
     */
      private static char[][] to10x10(String codePhrase1, String codePhrase2) {
         char[][] mat10 = new char[10][10];
         List<Character> alpha = Alphabet.alphabetAsList();
         String s1 = sanitize(codePhrase1);
         String s2 = sanitize(codePhrase2);
      
        // fill upper-left corner
         alpha.remove(new Character('q'));
         for(int r = 0; r < 5; r++)
            for(int c = 0; c < 5; c++)
               mat10[r][c] = alpha.remove(0);
      
        // fill upper-right corner
         alpha = Alphabet.alphabetAsList();
         alpha.remove(new Character('q'));
         List<Character> chars = new ArrayList<Character>();
         for(char c : s1.toCharArray())
            chars.add(c);
      
         for(int r = 0; r < 5; r++)
            for(int c = 5; c < 10; c++)
               if(!chars.isEmpty()) {
                  Character next = chars.remove(0);
                  mat10[r][c] = next;
                  alpha.remove(next);
                  while(chars.remove(next));
               }
      
        // if the entire corner didn't get filled, finish it
         for(int r = 0; r < 5; r++)
            for(int c = 5; c < 10; c++)
               if(mat10[r][c] == '\u0000')
                  mat10[r][c] = alpha.remove(0);
      
        // fill lower-left corner
         alpha = Alphabet.alphabetAsList();
         alpha.remove(new Character('q'));
         for(char c : s2.toCharArray())
            chars.add(c);
      
         for(int r = 5; r < 10; r++)
            for(int c = 0; c < 5; c++)
               if(!chars.isEmpty()) {
                  Character next = chars.remove(0);
                  mat10[r][c] = next;
                  alpha.remove(next);
                  while(chars.remove(next));
               }
      
        // if the entire corner didn't get filled, finish it
         for(int r = 5; r < 10; r++)
            for(int c = 0; c < 5; c++)
               if(mat10[r][c] == '\u0000')
                  mat10[r][c] = alpha.remove(0);
      
        // fill lower-right corner
         alpha = Alphabet.alphabetAsList();
         alpha.remove(new Character('q'));
         for(int r = 5; r < 10; r++)
            for(int c = 5; c < 10; c++)
               mat10[r][c] = alpha.remove(0);
      
         return mat10;
      }
   
    /**
     * Makes str compatible with this classes other methods, i.e. removes all
     * numbers, symbols, spaces, etc. It will also make all capital letters lowercase.
     *
     * @param str the String to be sanitized
     * @return the sanitized version of str
     */
      private static String sanitize(String str) {
         StringBuilder sb = new StringBuilder(str.toLowerCase());
      
         for(int i = 0; i < sb.length(); i++){
            if(!Character.isLetter(sb.charAt(i))) {
               sb.deleteCharAt(i);
               i--;
            }
            else if(sb.charAt(i)=='q')
               sb.replace(i,i+1,"cw");
         }
      
         return sb.toString();
      }
   }
