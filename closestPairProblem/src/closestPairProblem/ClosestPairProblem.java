/**
 * Условие задания 1.
 * Минимальное расстояние
* Дан набор из N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты). Найдите минимальное расстояние между двумя точками из этого набора.
*
* Пример входных данных (в качестве знака окончания ввода служит пустая строка):
* 10 10
* 20 10
* 20 15
*
* Пример выходных данных:
* 5
*
 */
package closestPairProblem;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author nadezhda
 * Основной класс, осуществляет ввод-вывод данных и вызывает расчет минимального расстояния
 */
public class ClosestPairProblem {

	/**
	 * Метод осуществляет ввод данных из потока ввода
	 * 
	 * @param inputStream - поток ввода
	 * @return - возвращает массив точек
	 */
	private static ArrayList<Point> readIntpuData(InputStream inputStream) {
		ArrayList<Point> inputPoints = new ArrayList<Point>();
		try (Scanner inFile = new Scanner(inputStream)) {
			String[] numbers = inFile.nextLine().split(" ");
			while (!numbers[0].isEmpty()) {
				inputPoints.add(new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
				numbers = inFile.nextLine().split(" ");
			}
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
		}
		return inputPoints;
	}

	/**
	 * Метод осуществляет вывод данных в поток вывода
	 * 
	 * @param d - минимальное расстояние между точками
	 * @param outputStream - поток вывода
	 */
	private static void outputResult(double d, PrintStream outputStream) {
		outputStream.print(d);
		outputStream.close();
	}

	/**
	 * основной метод программы
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// чтение входных данных из консоли
		ArrayList<Point> inputPoints = readIntpuData(new BufferedInputStream(System.in));
		// подсчет минимального расстояния между точками
		outputResult(new ClosestPair().CountClosestPairDistance(inputPoints), System.out);
	}

}
