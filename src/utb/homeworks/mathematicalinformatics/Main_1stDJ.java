package utb.homeworks.mathematicalinformatics;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main_1stDJ {

	final static String fileName = "1st DeJong.xlsx";

	public static void main(String[] args) {

		new ExcelWriterRS();
		System.out.println("1st DeJong Random Search is done.");
		new ExcelWriterSHC();
		System.out.println("1st DeJong Stochastic Hill Climbing is done.");
		new ExcelWriterSA();
		System.out.println("1st DeJong Simulated Annealing is done.");
		try {
			Thread.sleep(1000);
			Desktop.getDesktop().open(new File(fileName));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
