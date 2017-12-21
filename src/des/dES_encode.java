/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import com.sun.javafx.geom.AreaOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nasrallah
 */
public class dES_encode {

    public dES_encode() {
        cipher = "";
        plain = "";
        hex_cipher = "";
        hex_plain = "";
        fill_pc_1();

        fill_ip();
        fill_s_boxs();
        fill_E_bit_selection();
        fill_p();
        fill_ip_1();
    }

    void encode() {
        if (plain.equals("") && hex_plain.equals("")) {
            JOptionPane.showMessageDialog(null, "please provid plain text");
        } else {
            produce_permutation_key();
            get_16_subkeys();
            steps();
        }
    }

    private static String big_key,//original key
            p_key//after pc-1
            , plain//plain text
            , hex_plain// plain in hex
            , cipher//cipher in binary
            , hex_cipher//cipher in hex
            , c0//28
            , d0//28;
            , c[][], d[][];
    private static int keys[][];//16 48
    private static int pc_1[]//56
            , pc_2[]//48*/ 
            , E_bit[]//48*/
            , ip[]//64*/
            , ip_1[]//*64*/
            , p[]//32
            , dat[]//64
            , data[]//64;
            , l[][]//17 32
            , r[][];//17 32
    //, L[][]//17 32
    //  , R[][];//17 32
    private static int s_box[][][];

    public static String getBig_key() {
        return big_key;
    }

    public static String getP_key() {
        return p_key;
    }

    public static String getPlain() {
        return plain;
    }

    public static String getHex_plain() {
        return hex_plain;
    }

    public static String getCipher() {
        return cipher;
    }

    public static String getHex_cipher() {
        return hex_cipher;
    }

    public static String getC0() {
        return c0;
    }

    public static String getD0() {
        return d0;
    }

    public static String[][] getC() {
        return c;
    }

    public static String[][] getD() {
        return d;
    }

    public static int[][] getKeys() {
        return keys;
    }

    public static int[] getPc_1() {
        return pc_1;
    }

    public static int[] getPc_2() {
        return pc_2;
    }

    public static int[] getE_bit() {
        return E_bit;
    }

    public static int[] getIp() {
        return ip;
    }

    public static int[] getIp_1() {
        return ip_1;
    }

    public static int[] getP() {
        return p;
    }

    public static int[] getDat() {
        return dat;
    }

    public static int[] getData() {
        return data;
    }

    public static int[][] getL() {
        return l;
    }

    public static int[][] getR() {
        return r;
    }

    public static int[][][] getS_box() {
        return s_box;
    }

    public static void setBig_key(String big_key) {
        dES_encode.big_key = big_key;
    }

    public static void setP_key(String p_key) {
        dES_encode.p_key = p_key;
    }

    public static void setPlain(String plain) {
        dES_encode.plain = plain;
    }

    public static void setHex_plain(String hex_plain) {
        dES_encode.hex_plain = hex_plain;
    }

    public static void setCipher(String cipher) {
        dES_encode.cipher = cipher;
    }

    public static void setHex_cipher(String hex_cipher) {
        dES_encode.hex_cipher = hex_cipher;
    }

    public static void setC0(String c0) {
        dES_encode.c0 = c0;
    }

    public static void setD0(String d0) {
        dES_encode.d0 = d0;
    }

    public static void setC(String[][] c) {
        dES_encode.c = c;
    }

    public static void setD(String[][] d) {
        dES_encode.d = d;
    }

    public static void setKeys(int[][] keys) {
        dES_encode.keys = keys;
    }

    public static void setPc_1(int[] pc_1) {
        dES_encode.pc_1 = pc_1;
    }

    public static void setPc_2(int[] pc_2) {
        dES_encode.pc_2 = pc_2;
    }

    public static void setE_bit(int[] E_bit) {
        dES_encode.E_bit = E_bit;
    }

    public static void setIp(int[] ip) {
        dES_encode.ip = ip;
    }

    public static void setIp_1(int[] ip_1) {
        dES_encode.ip_1 = ip_1;
    }

    public static void setP(int[] p) {
        dES_encode.p = p;
    }

    public static void setDat(int[] dat) {
        dES_encode.dat = dat;
    }

    public static void setData(int[] data) {
        dES_encode.data = data;
    }

    public static void setL(int[][] l) {
        dES_encode.l = l;
    }

    public static void setR(int[][] r) {
        dES_encode.r = r;
    }

    public static void setS_box(int[][][] s_box) {
        dES_encode.s_box = s_box;
    }

    static void produce_permutation_key() {
        p_key = "";
        for (int i = 0; i < 56; i++) {
            p_key += big_key.toCharArray()[pc_1[i] - 1];

        }
        System.out.print(p_key);
    }

    static void fill_pc_1() {
        int i = 0;
        try {
            File file = new File("pc-1.DES");
            Scanner sc = new Scanner(file);

            pc_1 = new int[56];
            while (sc.hasNextLine()) {

                pc_1[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }
        System.out.println("");

    }

    static void fill_pc_2() {
        int i = 0;
        try {
            File file = new File("pc-2.DES");
            Scanner sc = new Scanner(file);

            pc_2 = new int[48];
            while (sc.hasNextLine()) {

                pc_2[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }

    }

    static void fill_ip() {
        int i = 0;
        try {
            File file = new File("ip.DES");
            Scanner sc = new Scanner(file);

            ip = new int[64];
            while (sc.hasNextLine()) {

                ip[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }

    }

    static void fill_ip_1() {
        int i = 0;
        try {
            File file = new File("ip-1.DES");
            Scanner sc = new Scanner(file);

            ip_1 = new int[64];
            while (sc.hasNextLine()) {

                ip_1[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }

    }

    static void fill_E_bit_selection() {
        int i = 0;
        try {
            File file = new File("E_bit_selection.DES");
            Scanner sc = new Scanner(file);

            E_bit = new int[48];
            while (sc.hasNextLine()) {

                E_bit[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }

    }

    static void fill_p() {
        int i = 0;
        try {
            File file = new File("p.DES");
            Scanner sc = new Scanner(file);

            p = new int[32];
            while (sc.hasNextLine()) {

                p[i++] = sc.nextInt();

            }

        } catch (Exception ex) {

        }

    }

    static void fill_s_boxs() {

        try {
            File file = new File("s-box.DES");
            Scanner sc = new Scanner(file);

            s_box = new int[8][4][16];
            while (sc.hasNextLine()) {

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 16; k++) {
                            s_box[i][j][k] = sc.nextInt();
                        }

                    }
                }
            }

        } catch (Exception ex) {

        }
        System.out.println("");
    }

    static int[] mangler_finction(int ll[], int kk[]) {
        int L[] = new int[48];
        for (int i = 0; i < 48; i++) {
            L[i] = ll[E_bit[i] - 1];//expand
        }
        int mang[] = new int[48];
        for (int i = 0; i < 48; i++) {
            mang[i] = L[i] ^ kk[i];
        }
        return mang;
    }

    static int[] x_or(int rr[], int sbox_outt[]) {
        int xor[] = new int[32];
        for (int i = 0; i < 32; i++) {
            xor[i] = rr[i] ^ sbox_outt[i];
        }
        return xor;
    }

    static int[] sbox_out(int B[], int index) {

        String a = "";
        String b = "";
        String res = "";

        for (int i = 0, v = 0; i < 48; i++) {
            int z = 0;
            a = "";
            b = "";
            a += tostr(B[i]).toCharArray()[0];
            a += tostr(B[i + 5]).toCharArray()[0];
            i++;
            for (int j = 0; j < 4; j++) {
                b += tostr(B[i++]).toCharArray()[0];
            }
            String ax = a;
            String bx = b;
            System.out.println("ax = " + ax);
            System.out.println("bx = " + bx);
            int aa = convertToDecimal(ax);
            int bb = convertToDecimal(bx);
            int d = s_box[v++][aa][bb];
            d = convertToBinary(d);
            String h = d + "";
            switch (h.length()) {
                case 1:
                    res += "000";
                    res += h;
                    break;
                case 2:
                    res += "00";
                    res += h;
                    break;
                case 3:
                    res += "0";
                    res += h;
                    break;
                case 4:
                    res += h;
                    break;
            }

        }
        int final_res[] = new int[32];
        int final_res_p[] = new int[32];

        for (int i = 0; i < 32; i++) {

            final_res[i] = Integer.parseInt(res.toCharArray()[i] + "");
        }
        for (int i = 0; i < 32; i++) {

            final_res_p[i] = final_res[p[i] - 1];
        }

        return final_res_p;

    }

    static String tostr(int number) {
        if (number == 0) {
            return "0";
        } else {
            return "1";
        }

    }

    static int toint(String buffer) {

        int a = 0;
        for (int i = 0; i < buffer.length(); i++) {
            a = (a * 10) + (buffer.indexOf(i) - 48);
        }

        return a;

    }

    static int convertToDecimal(String x) {
        int n = Integer.parseInt(x);
        int decimalNumber = 0, i = 0, remainder;
        while (n != 0) {
            remainder = n % 10;
            n /= 10;
            decimalNumber += remainder * Math.pow(2, i);
            ++i;
        }
        return decimalNumber;
    }

    static String bin_to_hex(String bin) {
        String hex = "";
        for (int i = 0; i < bin.length();) {
            String bi = "";
            for (int j = 0; j < 4; j++) {

                bi += bin.toCharArray()[i++];
            }
            int h = convertToDecimal(bi);
            if (h >= 10) {
                char x = (char) (h - 10 + 65);
                hex += x;
            } else {
                hex += h;
            }

        }
        return hex;
    }

    static String hex_to_bin(String hex) {
        String bin = "";
        for (int i = 0; i < hex.length();) {
            String he = "";

            he += hex.toCharArray()[i++];
            String h = "";
            if (he.toCharArray()[0] >= 65) {
                h = convertToBinary(he.toCharArray()[0] - 65 + 10) + "";

                bin += h;
            } else {
                h = convertToBinary(Integer.parseInt(he)) + "";
                String res = "";
                switch (h.length()) {
                    case 1:
                        res += "000";
                        res += h;
                        break;
                    case 2:
                        res += "00";
                        res += h;
                        break;
                    case 3:
                        res += "0";
                        res += h;
                        break;
                    case 4:
                        res += h;
                        break;
                }
                bin += res;
            }

        }
        return bin;
    }

    static int convertToBinary(int n) {
        int binaryNumber = 0;
        int remainder, i = 1, step = 1;

        while (n != 0) {
            remainder = n % 2;
            //      cout << "Step " << step++ << ": " << n << "/2, Remainder = " << remainder << ", Quotient = " << n/2 << endl;
            n /= 2;
            binaryNumber += remainder * i;
            i *= 10;
        }
        return binaryNumber;
    }

    public static int strToInt(String str) {
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        while (i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0';
        }

        if (isNeg) {
            num = -num;
        }
        return num;
    }

    static void steps() {//rounds
        data = new int[64];
        l = new int[17][32];
        r = new int[17][32];
        for (int i = 0; i < 64; i++) {
            data[i] = Integer.parseInt(plain.toCharArray()[ip[i] - 1] + "");
        }
        for (int j = 0; j < 32; j++) {

            l[0][j] = data[j];
        }

        for (int j = 32, z = 0; j < 64; j++) {
            r[0][z++] = data[j];
        }

        for (int i = 1; i < 17; i++) {

            l[i] = r[i - 1];

            // memcpy(r[i], x_or(l[i - 1], reduction(mangler_finction(r[i - 1], i - 1))), sizeof(r[i]));
            int B[] = new int[48];
            B = mangler_finction(r[i - 1], keys[i - 1]);
            int sbox_outt[] = new int[32];
            sbox_outt = sbox_out(B, i);
            r[i] = x_or(l[i - 1], sbox_outt);
        }

        for (int i = 0; i < 17; i++) {

            System.out.print(" l " + i + " = ");
            for (int j = 0; j < 32; j++) {

                System.out.print(l[i][j] + "");
            }
            System.out.println("");

        }

        for (int i = 0; i < 17; i++) {

            System.out.print(" r " + i + " = ");
            for (int j = 0; j < 32; j++) {
                System.out.print(r[i][j] + "");
            }
            System.out.println("");
        }
        String cip[] = new String[64];
        for (int i = 0; i < 32; i++) {
            cip[i] = r[16][i] + "";
        }
        for (int i = 0, z = 32; i < 32; i++) {
            cip[z++] = l[16][i] + "";
        }
        for (int i = 0; i < 64; i++) {
            cipher += cip[ip_1[i] - 1] + "";
        }
        hex_cipher = bin_to_hex(cipher);
    }

    static void get_16_subkeys() {
        keys = new int[16][48];
        c0 = "";
        d0 = "";
        for (int i = 0; i < 28; i++) {
            c0 += p_key.toCharArray()[i];
        }
        for (int i = 0, z = 28; i < 28; i++, z++) {
            d0 += p_key.toCharArray()[z];
        }

        /////////////////////////////////////////////////////////////////////
        // c[17][28], d[17][28];
        c = new String[17][28];
        d = new String[17][28];
        for (int j = 0; j < 28; j++) {
            c[0][j] = c0.toCharArray()[j] + "";
            d[0][j] = d0.toCharArray()[j] + "";
        }

        for (int i = 1; i < 17; i++) {
            for (int j = 0; j < 28; j++) {
                if (i == 1 || i == 2 || i == 9 || i == 16) {
                    c[i][j] = c[i - 1][(j + 1) % 28];
                    d[i][j] = d[i - 1][(j + 1) % 28];
                } else {
                    c[i][j] = c[i - 1][(j + 2) % 28];
                    d[i][j] = d[i - 1][(j + 2) % 28];
                }
            }
        }

        for (int i = 0; i < 17; i++) {
            //cout << " c" << i << " = ";
            System.out.print(" c" + i + " = ");
            for (int j = 0; j < 28; j++) {
                //cout << c[i][j] << " ";
                System.out.print(c[i][j] + "");
            }
            System.out.println("");
        }
        for (int i = 0; i < 17; i++) {
            //  cout << " d" << i << " = ";
            System.out.print(" d" + i + " = ");
            for (int j = 0; j < 28; j++) {
                // cout << d[i][j] << " ";
                System.out.print(d[i][j] + "");
            }
            System.out.println("");
        }

        int da[] = new int[56];
        for (int i = 1; i < 17; i++) {
            int z = 0;
            for (int j = 0; j < 28; j++) {
                //                stringstream nasr(c[i][j]);
                //                nasr >>
                da[z++] = Integer.parseInt(c[i][j]);
            }
            for (int j = 0; j < 28; j++) {
                //                stringstream nasr(d[i][j]);
                //                nasr >>
                da[z++] = Integer.parseInt(d[i][j]);
            }
            // cout << "w";
            fill_pc_2();
            for (int j = 0; j < 48; j++) {
                keys[i - 1][j] = da[(pc_2[j] - 1)];
            }
        }
        for (int i = 0; i < 16; i++) {
            //  cout << " k" << i + 1 << " = ";
            System.out.print(" k" + (i + 1) + " = ");
            for (int j = 0; j < 48; j++) {
                //cout << keys[i][j] << " ";
                System.out.print(keys[i][j] + "");
            }
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        big_key = "0001001100110100010101110111100110011011101111001101111111110001";

        cipher = "";
        hex_plain = "0123456789ABCDEF";
        plain = hex_to_bin(hex_plain);
        fill_pc_1();
        produce_permutation_key();
        get_16_subkeys();
        fill_ip();
        fill_s_boxs();
        fill_E_bit_selection();
        fill_p();
        fill_ip_1();
        steps();
        System.out.println(" plain  = " + plain);

        System.out.println("hex  plain  = " + hex_plain);
        System.out.println(" cipher  = " + cipher);
        hex_cipher = bin_to_hex(cipher);
        System.out.println("hex  cipher  = " + hex_cipher);
    }
}
