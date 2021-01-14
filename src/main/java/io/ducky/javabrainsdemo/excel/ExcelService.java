package io.ducky.javabrainsdemo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

	private String filelocation;
	private Map<Integer, List<String>> excelData;

	public void saveExcel(MultipartFile file) {
		try {
			InputStream in = file.getInputStream();
			File currDir = new File(".");
		    String path = currDir.getAbsolutePath();
		    filelocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
		    FileOutputStream f = new FileOutputStream(filelocation);
		    int ch = 0;
		    while ((ch = in.read()) != -1) {
		        f.write(ch);
		    }
		    f.flush();
		    f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation") // for cell.getCellTypeEnum()
	public void readExcelData() {
		// Apache POI seems to be the only way to go
		try {
			FileInputStream file = new FileInputStream(filelocation);
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);

			Map<Integer, List<String>> data = new HashMap<>();
			int i = 0;
			for (Row row : sheet) {
			    data.put(i, new ArrayList<String>());
			    for (Cell cell : row) {
			        switch (cell.getCellTypeEnum()) {
			            case STRING:
			            	data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
			            	break;
			            case NUMERIC:
			            	if (DateUtil.isCellDateFormatted(cell)) {
				                data.get(i).add(cell.getDateCellValue() + "");
				            } else {
				                data.get(i).add(cell.getNumericCellValue() + "");
				            }
			            	break;
			            case BOOLEAN:
			            	data.get(i).add(cell.getBooleanCellValue() + "");
			            	break;
			            case FORMULA:
			            	data.get(i).add(cell.getCellFormula() + "");
			            	break;
			            default: data.get(new Integer(i)).add(" ");
			        }
			    }
			    i++;
			}
		    workbook.close();
		    excelData = data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Object> returnJSON() {
		Map<String, String> jsonItem;
		List<Object> jsonData = new ArrayList<Object>();
		List<String> jsonKeys = excelData.get(0);
		
		for(int i = 1; i < excelData.size(); i++) {
			jsonItem = new HashMap<>();
			for(int j = 0; j < excelData.get(i).size(); j++) {
				jsonItem.put(jsonKeys.get(j), excelData.get(i).get(j));
				if(j == excelData.get(i).size() - 1) {
					jsonData.add(jsonItem);
				}
			}
		}
		
		return jsonData;
	}

}
