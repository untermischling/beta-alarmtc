package alarm;

public class Cout{
	public static <Obj> void cout(Obj arg){
		System.out.print(String.valueOf(arg));
	}
	public static <Obj> void coutln(Obj arg){
		System.out.println(String.valueOf(arg));
		}
	public static void coutln(){
		System.out.println();
	}
	public static void coutf(String format, Object... args){
		System.out.printf(format, args);
	}
}
