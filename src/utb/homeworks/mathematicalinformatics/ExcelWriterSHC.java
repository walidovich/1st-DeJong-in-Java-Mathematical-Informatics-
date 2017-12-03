package utb.homeworks.mathematicalinformatics;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterSHC {

	final String excelFileName = "1st DeJong.xlsx";
	final String SHC_Sheet = "1st DJ SHC";

	double BestCostFunction = 100000000, x1best, x2best;
	XSSFWorkbook wb;
	Sheet workSheet;

	public ExcelWriterSHC() {

		FileInputStream is;
		try {
			// Check if the file "1st Dejong.xlsx" already exists
			is = new FileInputStream(excelFileName);
			wb = new XSSFWorkbook(is);
		} catch (IOException e) {
		}
		// If Random Search.xlsx doesn't exist, we create it
		if (wb.getSheet("1st DJ SHC") == null)
			workSheet = wb.createSheet(SHC_Sheet);
		else
			workSheet = wb.getSheet(SHC_Sheet);
		workSheet.createRow(0);
		workSheet.createRow(1);
		workSheet.createRow(2);
		workSheet.createRow(3).createCell(0).setCellValue("Iter. #");
		for (int j = 0; j < 30; j++) {
			for (int i = 0; i < 1000; i++) {
				workSheet.getRow(0).createCell(j + 1).setCellValue("BCF #" + (j + 1));
				workSheet.getRow(3).createCell(j + 1).setCellValue("CF #" + (j + 1));
			}
		}
		for (int i = 0; i < 1000; i++) {
			workSheet.createRow(i + 4).createCell(0).setCellValue(i + 1);
		}
		writeInCells();
		FileOutputStream out;
		try {
			out = new FileOutputStream(excelFileName);
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeInCells() {
		for (int j = 0; j < 30; j++) {
			StochasticHillClimber shc = new StochasticHillClimber();
			double data[] = shc.costValueEvolution;
			for (int i = 0; i < 1000; i++) {
				workSheet.getRow(i + 4).createCell(j + 1).setCellValue(data[i]);
			}
			workSheet.getRow(1).createCell(j + 1).setCellValue(shc.bestSolution);
			if (shc.bestSolution < BestCostFunction) {
				BestCostFunction = shc.bestSolution;
				x1best = shc.x1best;
				x2best = shc.x2best;
			}
		}
		int i = 6;
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("1st DeJong SHC results:");
		i = 8;
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("X1 best");
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("X2 best");
		wb.getSheetAt(0).createRow(i++).createCell(0).setCellValue("B.C.F.");
		i = 8;
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(x1best);
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(x2best);
		wb.getSheetAt(0).getRow(i++).createCell(1).setCellValue(BestCostFunction);
	}
}
