package app.ciphers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Hill {
    
    int initVector[];
    int keyMatrix[][];
    int resultVector[];
    
    /**
     * Checks the validity of the key entered by the user.
     * 
     * @param key The String key entered by the user. 
     * @param sq  The square root of key length. <p>
     * @return    True if valid, false if invalid.
     */
    public boolean check(String key, int sq) {
        keyToMatrix(key, sq);
        int d = det(keyMatrix, sq) % 26;
        if (d == 0) {
            System.out.println("Invalid key - det=0 (you fool)");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0) {
            System.out.println("Invalid key - has common factor with 26");
            return false;
        }
        return true; 
    }
    
    /** 
     * Takes message to encrypt and divides it up into Strings of length len.
     * Encrypts each piece of the message individually. 
     * 
     * @param message String inputed to encrypt.
     * @param len     Length of message pieces.
     */
    public void partition(String message, int len) {
        while (message.length() > len) {
            String temp = message.substring(0, len);
            message = message.substring(len, message.length());
            encrypt(temp);
        }
        if (message.length() < len) {
            for (int i = message.length(); i < len; i++)
                message = message + 'x';
            encrypt(message);
        }
        encrypt(message); 
    }
    
    /**
     * Performs a cofactor expansion of the matrix.
     * 
     * @param matrix Matrix being expanded.
     * @param num    Number of columns.
     */
    public void cofactor(int matrix[][], int num) {
        int[][] temp = new int[num][num];
        int[][] fac = new int[num][num];
        
        for (int b = 0; b < num; b++) {
            for (int a = 0; a < num; a++) {
                int c = 0;
                int d = 0;
                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num; j++) {
                        temp[i][j] = 0;
                        if (i != b && j != a) {
                            temp[c][d] = matrix[i][j];
                            if (d < (num - 2))
                                d++;
                            else {
                                d = 0;
                                c++;
                            }
                        }
                    }
                }
                fac[b][a] = (int) Math.pow(-1, b + a) * det(temp, num - 1);
            }
        }
        invert(fac, num);
    }
 
    /**
     * Converts string s to a column vector.
     * Multiplies vector by key matrix, gets result.
     * 
     * @param s String being encrypted.
     */
    private void encrypt(String s) {
        lineToVector(s);
        lineByKey(s.length());
        result(s.length());
    }
 
    /**
     * Converts key entered by user to a key matrix.
     * 
     * @param key String inputed by user as a key.
     * @param len Key matrix is square with len rows and columns.
     */
    private void keyToMatrix(String key, int len) {
        int c = 0;
        keyMatrix = new int[len][len];
        for(int m = 0; m < len; m++) {
            for(int n = 0; n < len; n++) {
                keyMatrix[m][n] = ((int) key.charAt(c)) - 97;
                c++;
            }
        }
    }
 
    /**
     * Converts string to a initial vector. 
     * 
     * @param s String translated to initial vector.
     */
    private void lineToVector(String s) {
        initVector = new int[s.length()];
        for(int i = 0; i < s.length(); i++) {
            initVector[i] = ((int) s.charAt(i)) - 97;
        }
    }
    
    /**
     * Multiplies the column vector by the key matrix.
     * 
     * @param len Number of rows in the resultVector.
     */
    private void lineByKey(int len) {
        resultVector = new int[len];
        for(int m = 0; m < len; m++) {
            for(int n = 0; n < len; n++) {
                resultVector[m] += keyMatrix[m][n] * initVector[n];
            }
            resultVector[m] %= 26;
        }
    }
 
    /**
     * Prints the result of the encryption to the console.
     * 
     * @param len Number of rows in the resultVector.
     */
    private void result(int len) {
        String result = "";
        for(int m = 0; m < len; m++) {
            result += (char) (resultVector[m] + 97);
        }
        System.out.print(result);
    }

 
    /**
     * Calculates the determinant.
     * 
     * @param matrix The matrix that is having its determinant calculated.
     * @param n      Number of columns of the matrix. <p>
     * @return       The determinant of the matrix.
     */
    private int det(int matrix[][], int n) {
        int result;
        if (n == 1)
            result = matrix[0][0];
        else if (n == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else {
            result = 0;
            for (int k = 0; k < n; k++) {
                int m[][] = new int[n - 1][n - 1];
                for (int i = 1; i < n; i++) {
                    int idk = 0;
                    for (int j = 0; j < n; j++) {
                        if (j == k)
                            continue;
                        m[i - 1][idk] = matrix[i][j];
                        idk++;
                    }
                }
                result += Math.pow(-1.0, 1.0 + k + 1.0) * matrix[0][k] * det(m, n - 1);
            }
        }
        return result;
    }
 

 
    /**
     * Completes the inversion of the key matrix. 
     * 
     * @param adj Adjucate of the initial matrix.
     * @param n   Honestly who cares how many columns there are any more?
     */
    private void invert(int adj[][], int n) {
        int[][] temp = new int[n][n];
        int[][] inv = new int[n][n];
        int d = det(keyMatrix, n);
        int gaus = gaus(d % 26);
        gaus %= 26;
        if (gaus < 0)
            gaus += 26;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = adj[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inv[i][j] = temp[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= gaus;
                inv[i][j] %= 26;
            }
        }
        System.out.println("\nInverse key:");
        inverseToString(inv, n);
    }
 
    /**
     * Performs the necessary inversion black magic (gaussian).
     * 
     * @param det    Determinant. <p>
     * @return       Corrective value.
     */
    private int gaus(int det) {
        int res1 = 0;
        int res2 = 1;
        int temp1 = 26;
        int temp2 = det;
        while (temp1 != 1 && temp2 != 0) {
            int r = temp1 % temp2;
            int t = res1 - (res2 * (temp1/temp2));
            temp1 = temp2;
            temp2 = r;
            res1 = res2;
            res2 = t;
        }
        return (res1 + res2);
    }
 
    /**
     * Translates inverse of key matrix to String form.
     * 
     * @param inv Inverse matrix.
     * @param n   Number of columns.
     */
    private void inverseToString(int inv[][], int n) {
        String invkey = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
            {
                invkey += (char) (inv[i][j] + 97);
            }
        }
        System.out.print(invkey);
    }
 
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Hill hill = new Hill();
        System.out.println("Enter message: ");
        String message = br.readLine();
        System.out.println("Enter key: ");
        String key = br.readLine();
        if (Math.sqrt(key.length()) != (long) Math.sqrt(key.length()))
            System.out.println("Invalid key length - 'It's hip to be square' - Huey Lewis");
        else {
            int s = (int) Math.sqrt(key.length());
            if (hill.check(key, s)) {
                System.out.println("Encrypted String:");
                hill.partition(message, s);
                hill.cofactor(hill.keyMatrix, s);
            }
        }
    }
}