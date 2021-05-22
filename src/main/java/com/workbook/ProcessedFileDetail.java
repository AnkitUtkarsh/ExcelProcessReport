package com.workbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProcessedFileDetails{

    public static void main(String[]args) throws IOException{
        new ProcessedFileDetails().filesDetail();

        System.out.println("------------- Completed");
    }
    public void filesDetail() throws IOException{
        Map<File,String> process = new HashMap<File,String>();
        File file = new File("C:\\Users\\ankit\\Downloads\\Documents");

        File[] containersFile = file.listFiles();
        System.out.println("Running in system");
        //new WorkBook().createWorkbook(containersFile);
        for (File fil : containersFile){
            process.put(fil,fil.getName()+"|"+Files.size(fil.toPath()));
        }
        int i =0;
        //new WorkBook().createWorkbook(process);
        for (File filee: process.keySet()){
           

            String ad = process.get(filee);
            String[] abc = ad.split("[|]");
            System.out.println(file.getName() + "\t"+process.get(filee)+ "\t"+ abc[0]+ "\t"+ abc[1]);
            i++;
        }
      System.out.println(i);
    }
}