package summandsProblem;

/**
 * @author nadezhda
 * класс находит количестов разбиений числа на заданное количество слагаемых
 */
public class SummandsSolver {
	// константа, ограничивающая значение числа
	public final static int maxNumber = 150;
	// константа, ограничивающая количество слагаемых
	public final static int maxSummandsNumber = 150;
	// массив для хранения промежуточных вычислений
	int intermediateResult[][];
	/**
	 * Метод осуществляет подготовку данных для подсчета количества разбиений числа number на
	 * numOfSummands слагаемых; 
	 * способы, отличающиеся только порядком слагаемых,
	 * считаются одинаковыми. 
	 * 
	 * @param number
	 * @param numOfSummands
	 * @return возвращает количество разбиений числа number на numOfSummands
	 *         слагаемых
	 */
	public int countNumberOfSummands(int number, int numOfSummands) {
		// проверяем входные параметры на натуральность
		// если числа не натуральные или превосходят 150 (по условию), 
		//то дальнейшее выполнение программы бессмысленно
		if (number < 1 || numOfSummands < 1 || number > maxNumber || numOfSummands > maxSummandsNumber) {
			return -1;
		}
		// если число меньше количества слагаемых, то количество разбиений равно
		// нулю
		if (number < numOfSummands) {
			return 0;
		}
		//инициализируем таблицу для хранения промежуточных рассчетов, 
		//исходя из граничных условий задачи		
		intermediateResult = new int [number][numOfSummands];
		//F(m, m) = 1, где m принадлежит интервалу [1..numOfSummands]
		for (int i = 0; i < numOfSummands; ++i) {
			intermediateResult[i][i] = 1;
		}
		//F(m, 1) = 1, где m = [1..number]
		for (int i = 0; i < number; ++i) {
			intermediateResult[i][0] = 1;
		}
		return countSummands(number, numOfSummands);
	}
	/**
	 * Метод осуществляет подсчет количества разбиений числа number на
	 * numOfSummands слагаемых способы, отличающиеся только порядком слагаемых,
	 * считаются одинаковыми. Для этого используется рекуррентная формула
	 * F(number, numOfSummands) = F(number - numOfSummands, numOfSummands) +
	 * F(number - 1, numOfSummands - 1)
	 * 
	 * @param number 
	 * @param numOfSummands
	 * @return возвращает количество разбиений числа number на numOfSummands
	 *         слагаемых
	 */
	private int countSummands(int number, int numOfSummands) {
		//если вышли за границы таблицы
		if (number < 1 || numOfSummands < 1) {
			return 0;
		}
		//вычисляем элемент таблицы (количество разбиений) по рекуррентной формуле
		if (intermediateResult[number - 1][numOfSummands - 1] == 0) {
			intermediateResult[number - 1][numOfSummands - 1] = countSummands(number - numOfSummands, 
					numOfSummands) + countSummands(number - 1, numOfSummands - 1);
		}
		
		// возвращаем итоговое количество разбиений
		return intermediateResult[number - 1][numOfSummands - 1];
	}
}
