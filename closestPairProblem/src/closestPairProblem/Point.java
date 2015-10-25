package closestPairProblem;

import java.util.Comparator;

/**
 * @author nadezhda
 * класс представляет собой описание точки с координатами x и y на плоскости
 */
public class Point {
	
	// координаты точки, имеют тип int по условию
	private int x, y;

	/**
	 * 	конструктор, создающий класс Point по двум координатам x и y
	 * @param x - координата x
	 * @param y - координата y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * метод возвращает координату y точки
	 * @return x
	 */
	public int getY() {
		return y;
	}

	/**
	 * метод устанавливает новую координату y точки
	 * @param y - новая координата y точки
	 */
	public void setY(int y) {
		this.y = y;
	}
	

	/**
	 * метод возвращает координату x точки
	 * @param x
	 */
	public int getX() {
		return x;
	}
	/**
	 * метод устанавливает новую координату y точки
	 * @param x - новая координата y точки
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * метод считает расстояние на плосткости между текущей точкой и точкой other
	 * @param other - точка, расстояние до которой от текущей надо рассчитать
	 * @return
	 */
	public double calcDistance (Point other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}

    /**
     * метод для сравнения двух точек pointOne и pointTwo по координате x
     * @return возвращает -1, если координата x первой точки меньше
     * -1 - если больше и 0 - если они равны
     */
    public static Comparator<Point> compareToX() {
    	return new Comparator<Point>() {
    		public int compare(Point pointOne, Point pointTwo) {
    			if (pointOne.x < pointTwo.x) return -1;
    			if (pointOne.x > pointTwo.x) return +1;
    			return 0;
    		}
    	};
    }

    /**
     * метод для сравнения двух точек pointOne и pointTwo по координате y
     * @return возвращает -1, если координата y первой точки меньше
     * -1 - если больше и 0 - если они равны
     */
    public static Comparator<Point> compareToY() {
    	return new Comparator<Point>() {
    		public int compare(Point pointOne, Point pointTwo) {
    			if (pointOne.y < pointTwo.y) return -1;
    			if (pointOne.y > pointTwo.y) return +1;
    			return 0;
    		}
    	};
    }
}
