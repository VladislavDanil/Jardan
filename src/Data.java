import java.util.ArrayList;
import java.util.HashMap;

public class Data {
	public static float[][] s_matrix;
	public static float[][] end_matrix;
	public static boolean retry = false;
	public static String x_start;
	public static String y_start;
	
	/*индикатор замены, при повторном выборе элемента запрещает его использование*/
	public static ArrayList<Integer> k = new ArrayList<Integer>();
	public static ArrayList<Integer> s = new ArrayList<Integer>();
	public static HashMap<Integer, Integer> saveColumn=new HashMap<Integer, Integer>();
	
	/*для проверки нулевых строк и ответа*/
	public static boolean result=false;
	public static boolean not_result=false;
	public static boolean one_result=false;
}
