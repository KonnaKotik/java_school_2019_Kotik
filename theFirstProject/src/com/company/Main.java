package com.company;

import java.io.*;
import java.util.BitSet;

public class Main {

    static byte[] bitInByte(BitSet bitSet) {
        byte b[] = new byte[(bitSet.length() + 7) / 8 ];
        System.out.println(bitSet.length());
        for(int i = 0; i < bitSet.length(); i++) {
            System.out.println(bitSet.get(i));
            if(bitSet.get(i)) {
                b[(bitSet.length() - i)/ 8] |= 1 << (i%8);
                System.out.println( b[(bitSet.length() - i)/ 8]);
            }
        }
        return b;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String arrayOfString[] = new String[3];

        System.out.print("Введите имя входного файла, имя выходного файла(необязательно), тип разворота byte(побайтно)/bit(побитно) (необязательно)");

        for (int i = 0; i < arrayOfString.length; i++) {
            String name = reader.readLine();
            if (!name.equals("")) {
                arrayOfString[i] = name;
            } else {
                if (i == 1) {
                    System.out.println("Вы не ввели имя выходного файла, поэтому мы перезапишем входной файл");
                    arrayOfString[i] = arrayOfString[i - 1];
                }
                if (i == 2) {
                    System.out.println("Вы не ввели тип разворота данных, поэтому мы развернем побатно");
                    arrayOfString[i] = "побайтно";
                }
            }
        }


        try (FileInputStream inputStream = new FileInputStream(arrayOfString[0]);
             FileOutputStream outputStream = new FileOutputStream(arrayOfString[1])) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer, 0, buffer.length);
            if (arrayOfString[2].equals("byte") || arrayOfString[2].equals("побайтно")) {
                for (int i = buffer.length - 1; i >= 0; i--) {
                    outputStream.write(buffer[i]);
                }
             } else if (arrayOfString[2].equals("bit") || arrayOfString[2].equals("побитно")) {
                BitSet bits = BitSet.valueOf(buffer);
                for(int i = 0; i < buffer.length; i++) {
                    System.out.println(buffer[i]);
                }
                buffer = bitInByte(bits);

                for (int i = 0; i < buffer.length; i++) {
                    outputStream.write(buffer[i]);
                }
            }

            inputStream.close();
            outputStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }



    }
}
