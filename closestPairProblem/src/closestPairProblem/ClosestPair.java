package closestPairProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * @author nadezhda
 * класс вычисляет наименьшее расстояние между точками на плоскости
 *
 */
public class ClosestPair {
   /**
     * Метод осуществляет вычисление наименьшего расстояния между точками из массива points на плоскости
     *
     * @param  points массив точек, среди которых вычисляется минимальное расстояние
     *
     */
    public double CountClosestPairDistance(List<Point> points) {
        int numberOfPoints = points.size();
        if (numberOfPoints <= 1) return -1;

        // отсортируем массив точек по координате X
        List<Point> sortedXPoints = new ArrayList<Point>(points);
        Collections.sort(sortedXPoints, Point.compareToX());

        // проверим на наличие совпадающих точек
        for (int i = 0; i < numberOfPoints-1; i++) {
            if (sortedXPoints.get(i).equals(sortedXPoints.get(i+1))) {
                return 0.0;
            }
        }

        // отсортируем массив по координате Y
        List<Point> sortedYPoints = new ArrayList<Point>(points);
        Collections.sort(sortedYPoints, Point.compareToY());

        return closest(sortedXPoints, sortedYPoints, 0, numberOfPoints-1);
    }
    
    /**
     * метод осуществляет сортировку входного массива arrayToSort по координате Y с учетом того, 
     * что первая часть массива [first..middle] и вторая часть [middle..last] уже отсортированы
     * @param arrayToSort - массив, значения которого по координате Y надо отсортировать
     * @param first - индекс, начиная с которого надо отсортировать
     * @param middle - индекс середины, то есть до него значения отсортированы и после него тоже
     * @param high - индекс, заканчивая которым надо отсортировать
     */
    private static void mergeSort(List<Point> arrayToSort, int first, int middle, int last) {
       //если массив уже отсортирован по координате y, то ничего не делать 
       if (arrayToSort.get(middle).getY() <= arrayToSort.get(middle + 1).getY()) return;
       //иначе сортируем, используя слияние
       List<Point> additionalArray = arrayToSort.subList(first, last + 1);
       int i = 0, k = first, j = middle - first + 1;
       while (i <= middle - first && j <= last - first) {
    	   if (additionalArray.get(i).getY() < additionalArray.get(j).getY()) {
       			arrayToSort.set(k, additionalArray.get(i));
       			i++;
    	   }
       		else {
       			arrayToSort.set(k, additionalArray.get(j));
       			j++;
       		}
       		k++;
       }
       while (i <= middle - first) {
    	   arrayToSort.set(k, additionalArray.get(i));
           k++;
           i++;
       }
    }

    
    /**
     * @param sortedXPoints - массив точек, отсортированных по координате Х
     * @param sortedYPoints - массив точек, отсортированных по координате Y
     * @param first - индекс первой рассматриваемой точки в массиве
     * @param last - индекс последней рассматриваемой точки в массиве
     * @return
     */
    private double closest(List<Point> sortedXPoints, List<Point> sortedYPoints, int first, int last) {
        if (last <= first) return Double.POSITIVE_INFINITY;
        
        //вычисляем среднюю точку
        int middle = first + (last - first) / 2;
        Point middlePoint = sortedXPoints.get(middle);

        //вычисляем минимальное расстояние между точками в левой части массива
        double minimumLeft = closest(sortedXPoints, sortedYPoints, first, middle);
        //вычисляем минимальное расстояние между точками в правой части массива
        double minimumRight = closest(sortedXPoints, sortedYPoints, middle+1, last);
        //вычисляем минимум из найденных минимальных расстояний
        double curMinimumDistance = Math.min(minimumLeft, minimumRight);

        // слиянием отсортируем массив sortedYPoints[first..last]
        mergeSort(sortedYPoints, first, middle, last);

        //stripArray - массив точек, у которых расстояние по координате X до 
        //линнии, проходящей через среднюю точку middlePoint, меньше минимального найденного расстояния curMinimumDistance
        List<Point> stripArray = new  ArrayList<Point>();
        //текущий индекс массива средних точек
        int curIndex = 0;
        for (int i = first; i <= last; i++) {
            if (Math.abs(sortedYPoints.get(i).getX() - middlePoint.getX()) < curMinimumDistance) {
            	stripArray.add(sortedYPoints.get(i));
            	curIndex++;
            }
        }

        //вычисляем минимальное расстояние между точками, попавшими в соседние массивы точек, то есть
        //между точками в массиве stripArray
        for (int i = 0; i < curIndex; i++) {
            for (int j = i+1; (j < curIndex) && (stripArray.get(i).getY() - stripArray.get(i).getY() < curMinimumDistance); j++) {
                double distance = stripArray.get(i).calcDistance(stripArray.get(j));
                if (distance < curMinimumDistance) {
                    curMinimumDistance = distance;
                }
            }
        }
        return curMinimumDistance;
    }


}
