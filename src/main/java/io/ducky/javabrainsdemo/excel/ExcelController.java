package io.ducky.javabrainsdemo.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelController {
	
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping("/txt")
	public void getText(@RequestParam("file") MultipartFile file) {
		try {
			StringBuilder resultStringBuilder = new StringBuilder();
		    BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
	        String line;
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
			System.out.println(resultStringBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/excel")
	public List<Object> getExcel(@RequestParam("file") MultipartFile file) {
		excelService.saveExcel(file);
		excelService.readExcelData();
		return excelService.returnJSON();
	}
}
