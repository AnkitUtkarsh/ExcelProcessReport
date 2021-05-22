package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class metaData {
    public static boolean addMetaData (Sheet sh){
        String pathName= "D:\\JAVA APP\\efxpush.log.bak";
        BufferedReader br = null;
        String line= null;

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(pathName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fis);
        
        int rownum=1;
        while (sc.hasNext()){

            String line1 = sc.nextLine();
            //cd ../System.out.println(line1);
            if (line1.contains("transfer successfully") || line1.contains("transfer failed")){
                String[] arrayLine = line1.split("[ ]");
                ArrayList<String> metaDataList = metaDataContainer(arrayLine);

                Row row = sh.createRow(rownum);

                for (int k=0 ; k<metaDataList.size(); k++){
                    row.createCell(k).setCellValue(metaDataList.get(k));
                 }
                 rownum++;
            }
        }


        sc.close();
        try {
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<String> metaDataContainer (String[] lineCutOut){
        
        ArrayList<String> mdContainer = new ArrayList<String>();
        
        mdContainer.add(lineCutOut[0]);
        mdContainer.add(lineCutOut[1]);
        mdContainer.add(lineCutOut[7]);
        mdContainer.add(lineCutOut[9]);
        mdContainer.add(lineCutOut[12]);
        mdContainer.add(lineCutOut[14]);        
        
        return mdContainer;
    }


}
