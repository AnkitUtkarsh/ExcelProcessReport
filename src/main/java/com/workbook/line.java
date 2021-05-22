package com.workbook;

public class line {
    public static void main(String[] args){

        String l = "2021-05-18 09:17:49.393  [INFO ]SFTPHandler.uploadFile(SFTPHandler.java:263) - File 0021A2B1.zip transfer failed via channel 2 in 15865 msec.";
        String la = "2021-05-18 09:20:56.425  [INFO ]SFTPHandler.uploadFile(SFTPHandler.java:233) - File 0021A2D1.zip transfer successfully via channel 0 in 0 msec.";

        

        String[] ab = l.split("[ ]");
        String[] failed = la.split("[ ]");
        
        for (int i=0 ; i< ab.length ; i++){

            System.out.println( "String Name = " + ab[i]+ " \t Index --> " + i);
        }
            System.out.println("======================================================");
        for (int j = 0 ; j< failed.length ; j++){

            System.out.println( "String Name = " + failed[j]+ " \t Index --> " + j);
        }
    }
}
