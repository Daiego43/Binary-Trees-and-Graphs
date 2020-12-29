package Ejercicio_ArbolAVL_Tweets;

import java.util.Comparator;

public class ComparaNodosEnteros implements Comparator<Object>{

	@Override
	public int compare(Object arg0, Object arg1) {
		Integer i = (Integer) arg0;
		Integer j = (Integer) arg1;
		 return i-j;
	}


}
