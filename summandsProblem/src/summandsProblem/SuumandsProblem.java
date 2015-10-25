package summandsProblem;
/**
 * Задание 2
 * Количество разбиений на k слагаемых
 * Для данных натуральных чисел n и k определите количество способов представить число n в виде суммы k натуральных слагаемых, если способы, отличающиеся только порядком слагаемых считать одинаковыми.
 * Программа получает на вход два натуральных числа n и k, не превосходящих 150.
 *
 * Пример входных данных:
 * 6 3
 *
 * Пример выходных данных:
 * 3
 */
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author nadezhda
 * класс осуществляет ввод и вывод данных и запускает расчет введенного (number) количества разбиений введенного числа (numOfSummands)
 *
 */
class SummandsProblem {
	
	/**
	 * @author nadezhda
	 * класс представляет собой пару значений для ввода: number и numOfSummands
	 */
	private final static class InputData {
		//число, количество разбиений которого рассчитывается
	    private final int number;
	    //количество слагаемых
	    private final int numOfSummands;

	    private InputData(int number, int numOfSummands) {
	        this.number = number;
	        this.numOfSummands = numOfSummands;
	    }

		public int getNumber() {
			return number;
		}

		public int getNumOfSummands() {
			return numOfSummands;
		}	    
	}
	/**
	 * Метод осуществляет ввод данных из потока ввода
	 * @param inputStream
	 *            - поток ввода
	 */
	private static InputData readIntpuData(InputStream inputStream) {
		try (Scanner inFile = new Scanner(inputStream)) {
			return new InputData(inFile.nextInt(), inFile.nextInt());
		} catch (InputMismatchException ex) {
			ex.printStackTrace();
		}
		return new InputData(-1, -1);
	}

	/**
	 * Метод осуществляет вывод данных в поток вывода
	 * 
	 * @param countSummands
	 *            - количество разбиений числа
	 * @param outputStream
	 *            - поток вывода
	 */
	private static void outputResult(int countSummands, PrintStream outputStream) {
		outputStream.print(countSummands);
		outputStream.close();
	}

	/**
	 * основной метод программы
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// чтение входных данных из консоли
		InputData inputData = readIntpuData(new BufferedInputStream(System.in));
		// подсчет количества разбиений числа number на numOfSummands слагаемых
		// и вывод результата в консоль
		// в случае невозможности подсчета количества разбиений выводится -1
		outputResult(new SummandsSolver().countNumberOfSummands(inputData.getNumber(), 
				inputData.getNumOfSummands()), System.out);
	}
}