/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author nasrallah
 */
public class des_decode {

    public des_decode() {
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

    void decode() {
        if (cipher.equals("") && hex_cipher.equals("")) {
            JOptionPane.showMessageDialog(null, "please provid cipher text");
        } else {

            inv_steps();
            produce_permutation_key();
            get_16_subkeys();
        }
    }
    static String big_key,//original key
            p_key//after pc-1
            , plain//plain text
            , hex_plain// plain in hex
            , cipher//cipher in binary
            , hex_cipher//cipher in hex
            , c0//28
            , d0//28;
            , c[][], d[][];
    static int keys[][];//16 48
    static int pc_1[]//56
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
    static int s_box[][][];

    static void produce_permutation_key() {
        p_key = "";
        for (int i = 0; i < 56; i++) {
            p_key += big_key.toCharArray()[pc_1[i] - 1];

        }
        // System.out.print(p_key);
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

    static int[] mangler_finction(int rr[], int kk[]) {
        int R[] = new int[48];
        for (int i = 0; i < 48; i++) {
            R[i] = rr[E_bit[i] - 1];//expand
        }
        int mang[] = new int[48];
        for (int i = 0; i < 48; i++) {
            mang[i] = R[i] ^ kk[i];
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

    static void inv_steps() {//rounds
        data = new int[64];
        l = new int[17][32];
        r = new int[17][32];
        for (int i = 0; i < 64; i++) {
            data[ip_1[i] - 1] = Integer.parseInt(cipher.toCharArray()[i] + "");
        }
        for (int j = 0; j < 32; j++) {

            r[16][j] = data[j];
        }

        for (int j = 32, z = 0; j < 64; j++) {
            l[16][z++] = data[j];
        }

        for (int i = 16; i >= 1; i--) {

            r[i - 1] = l[i];

            // memcpy(r[i], x_or(l[i - 1], reduction(mangler_finction(r[i - 1], i - 1))), sizeof(r[i]));
            int B[] = new int[48];
            B = mangler_finction(r[i - 1], keys[i - 1]);
            int sbox_outt[] = new int[32];
            sbox_outt = sbox_out(B, i);
            l[i - 1] = x_or(r[i], sbox_outt);
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
        String PL[] = new String[64];
        char ar[] = new char[64];
        for (int i = 0; i < 32; i++) {
            PL[i] = l[0][i] + "";
        }
        for (int i = 0, z = 32; i < 32; i++) {
            PL[z++] = r[0][i] + "";
        }
        for (int i = 0; i < 64; i++) {
            ar[ip[i] - 1] = PL[i].toCharArray()[0];

        }
        for (int i = 0; i < 64; i++) {
            plain += ar[i];
        }

    }

    public static void main(String[] args) {
        big_key = "0001001100110100010101110111100110011011101111001101111111110001";

        hex_plain = "";
        plain = "";
        hex_cipher = "85E813540F0AB405";
        cipher = hex_to_bin(hex_cipher);
        System.out.println(cipher);
        fill_pc_1();
        produce_permutation_key();
        get_16_subkeys();
        fill_ip();
        fill_s_boxs();
        fill_E_bit_selection();
        fill_p();
        fill_ip_1();
        inv_steps();

        hex_plain = bin_to_hex(plain);
        System.out.println("plain = " + plain);
        System.out.println("plain hex = " + hex_plain);
    }
}
