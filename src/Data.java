import java.util.ArrayList;

public class Data {
	public static float[][] s_matrix;
	public static float[][] end_matrix;
	public static boolean retry = false;
	public static String x_start;
	public static String y_start;
	
	/*индикатор замены, при повторном выборе элемента запрещает его использование*/
	public static ArrayList<Integer> k = new ArrayList<Integer>();
	/*для проверки нулевых строк и ответа*/
	public static boolean result=false;
}
