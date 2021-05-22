package com.workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.metaData;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public final class WorkBook {
   
    public static void main(String[] args){

        new WorkBook().createWorkbook();
    }
    public void createWorkbook(){
        System.out.println("running in Workbook");
        try {
            //FileInputStream fis = new FileInputStream(new File ("D:\\JAVA APP\\excel1.xls"));

            Workbook wb = new HSSFWorkbook();
            String processedFilesDetail = "FileDetail"+Calendar.getInstance().getTimeInMillis();
            
            //System.out.println(wb.getActiveSheetIndex());
            Sheet sh = wb.createSheet("FileDetail");
            //Sheet sh = wb.getSheet("FileDetail1621505782577");
            //Sheet sh = getEmptySheet(wb);
            //int rowNum = getEmptyRow(sh);
           
                String[] columneHeading= {"Date", "Process Time", "FileName", "Failed / Successfully","Channel ID", "ProcessingTime (ms)"};
                System.out.println(">>> Running for Header");
                Font headerFont = wb.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short)12);
                headerFont.setColor(IndexedColors.BLACK.index);
                CellStyle headerStyle = wb.createCellStyle();
                headerStyle.setFont(headerFont);
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
    
                
                Row headerRow = sh.createRow(0);
                System.out.println(sh.getRow(0).getCell(0));
                
                for(int i=0; i<columneHeading.length; i++){
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columneHeading[i]);
                    cell.setCellStyle(headerStyle);
                    System.out.println("FOR LOOP");
                }
                

                boolean succes = metaData.addMetaData(sh);
                
                System.out.println(succes);
            /*    
            //Fill Data 

            
            //int cellCount= 0;
            int rownum=1;      
            System.out.println("ROW need to start process " + rownum);     
				
            for (File file : containersFile){
                ArrayList<String> fileDetail = createData(file);
                Row row = sh.createRow(rownum);
                //System.out.println("RownUmber "+ row.toString());
                
                
                for(int i =0 ; i< fileDetail.size();i++) {
                    //System.out.println("rownum-before"+(rownum));
                    //Row row = sh.createRow(rownum);
                    
                   //System.out.println("rownum-after"+(rownum) + "  FileDetail " + i );
                    
                    row.createCell(i).setCellValue(fileDetail.get(i));
    
                }
                rownum++;
            }
            */
            
            
            
            /*
			//Group and collapse rows
			int noOfRows = sh.getLastRowNum();
			sh.groupRow(1, noOfRows);
			sh.setRowGroupCollapsed(1, true);
			//Create a sum row
			Row sumRow = sh.createRow(rownum);
			Cell sumRowTitle = sumRow.createCell(0);
			sumRowTitle.setCellValue("Total");
			sumRowTitle.setCellStyle(headerStyle);
			
			String strFormula = "SUM(D2:D"+rownum+")";
			Cell sumcell = sumRow.createCell(3);
			sumcell.setCellFormula(strFormula);
			sumcell.setCellValue(true);*/
			
			//Autosize columns
			for(int i=0;i<columneHeading.length;i++) {
				sh.autoSizeColumn(i);
			}
			Sheet sh2 = wb.createSheet("Second");
			//Write the output to file

            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMDDyyyyMMss");
            LocalDateTime now = LocalDateTime.now(); 

            String timeStamp = String.valueOf(format.format(now));

            
            String filePath= "D:\\JAVA APP\\excel"+timeStamp+".xls";
           //System.out.println(filePath);
            File excelFile = new File(filePath);
            excelFile.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(filePath);

			wb.write(fileOut);
            //fis.close();
			fileOut.close();
			wb.close();
			System.out.println("Completed");
            
            



            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    private static ArrayList<String>createData(File file) {
        ArrayList<String> fileDetail = new ArrayList<String>();
        //fileDetail.add(file.getName(),file.getPath().toString(),"No", String.valueOf(Files.size(file.toPath())), String.valueOf(file.getTotalSpace()) );
        fileDetail.add(file.getName());
        fileDetail.add(file.getPath().toString());
        fileDetail.add("NO");
        try {
            fileDetail.add(String.valueOf(Files.size(file.toPath())));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fileDetail.add(String.valueOf(file.getTotalSpace()));
        
        
        return fileDetail;
    }
     
    public Sheet getEmptySheet(Workbook wb ){
        System.out.println("Get EMPTY SHEET");
        int sheetIndex=0;
        wb.setActiveSheet(3);
        Sheet sh = wb.createSheet();
       
        System.out.println("CELL TYPE");
        Cell c = null ;

        HSSFSheet emptySheet;
        try{
            c = sh.getRow(0).getCell(0);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(c == null || c.getCellType() == CellType.BLANK){
            System.out.println("Returning Sheet from null 0");
            return sh;

        }else{
           
        }
       
        Iterator sheet = sh.iterator();
        System.out.println("After iterating" + sheet.hasNext()) ;
        while(sheet.hasNext()){
            System.out.println("Sheet Has Next");
           // emptySheet=(HSSFSheet) sheet.next();
            System.out.println("Runnning in while Loop");
            if(c == null || c.getCellType() == CellType.BLANK){
                return sh;
    
            }else{
               continue;
            }
        }
        //wb.setActiveSheet(sheetIndex);
        //sh = wb.createSheet();
        
        
        


        return sh;
    }
    public int getEmptyRow (Sheet sh){
        System.out.println(">>> EMPTY ROW"); 
        HSSFRow row;
        Iterator it = sh.iterator();
        int rowNumber=0;
        while(it.hasNext()){
            row = (HSSFRow) it.next();
            Cell cd = row.getCell(0);
            rowNumber= row.getRowNum();
        }
        System.out.println(">>>>> ROW Number " + rowNumber);
        return rowNumber;

    }
}
