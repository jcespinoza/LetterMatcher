package fuentes;

import java.util.Random;
//metodos utiles
public class Ut{
	
	private Ut(){}

	public static boolean puedeConvertirse(String numS, char tipo){
		if(tipo == 'i'){
			int num;
			try{
				num = Integer.parseInt(numS);
				return true;
			}catch(Exception exc){
				return false;
			}
		}else if(tipo == 'd'){
			double num;
			try{
				num = Double.parseDouble(numS);
				return true;
			}catch(Exception exc){
				return false;
			}
		}
		return false;
	}
	
	public static String partirHasta(String cad, int max){
		String cad2 ="";
		for(int i = 0; i < max; i++){
			cad2 += cad.charAt(i);
		}
		return cad2;
	}
	
	public static int generarNumero(int max){
		Random rd = new Random();
		return rd.nextInt(max);
	}
	
	public static boolean dentroDelRangoI(int valor, int min, int max, boolean inclusivo){
		if(inclusivo){
			return (valor >= min && valor <= max)? true: false;
		}
		
		return (valor > min && valor < max)?true:false;
	}
	
	public static char generarLetra(){
		Random rd = new Random();
		return (char)(rd.nextInt(26)+65);
	}
	
	public static int darValor(String in, int porDefecto){
		if(puedeConvertirse(in, 'i')){
			return Integer.parseInt(in);
		}else{
			return porDefecto;
		}
	}
    
	public static double darValor(String in, double porDefecto){
		if(puedeConvertirse(in, 'd')){
			return Double.parseDouble(in);
		}else{
			return porDefecto;
		}
	}
	
	public static int nextIndex(int actual, int max){
		if(actual == max){
			return max;
		}else{
			return ++actual;
		}
	}
	
	public static String multEspacios(int nEspacios){
		String sp = "";
		for(int i = 0; i < nEspacios; i++){
			sp += " ";
		}
		return sp;
	}
	
	public static String encajarString(String arg, int maxLength){
		String result;
		if(arg.length() > maxLength){
			result = partirHasta(arg, maxLength);
		}else if(arg.length() < maxLength){
			int diferencia = maxLength - arg.length();
			result = arg + multEspacios(diferencia);
		}else{
			return arg;
		}
		return result;
	}
	
	public static boolean esFilaValida(int arrLength, int tFila){
		if(tFila < 0){
			return false;
		}
		if(tFila >= arrLength){
			return false;
		}
		return true;
	}
	
	public static boolean esColumnaValida(int filaLength, int tColumna){
		if(tColumna < 0){
			return false;
		}
		if(tColumna >= filaLength){
			return false;
		}
		return true;
	}
	
	public static void ordenarMatriz2D(int tabla[][], int index, boolean ascendente){
		/*EN PROGRESO*/
		
		/*int tempT[] = new int[tabla.length];
		int indexes[] = new int[tempT.length];
		for(int i = 0; i < tempT.length; i++){
			
		}*/
	}
	
	public static boolean existeEnArregloInt(int valor, int arr[]){
		return (buscarInt(valor, arr, false) > -1)?true:false;
	}
	
	public static int buscarInt(int busq, int arr[]){
		int superior, inferior, mitad;
		inferior = 0;
		superior = arr.length - 1;
		mitad = (superior - inferior)/2;
		
		while(inferior <= superior){
			if(busq == arr[mitad]){
				return mitad;
			}else if(busq < arr[mitad]){
				superior = mitad - 1;
			}else{
				inferior = mitad + 1;
			}
			mitad = ((superior - inferior)/2)+inferior;
		}
		return -1;
	}
    
    public static int buscarInt(int busq, int arr[], boolean ordenado){
        if(ordenado){
            return buscarInt(busq, arr);
        }else{
            for(int i = 0; i < arr.length; i++){
                if(busq == arr[i]){
                    return i;
                }
            }
        }
        return -1;
    }
	
	public static void ordenarMatriz1D(int tabla[], boolean ascendente){
		int temp;
		if(ascendente){
			for(int k = 0; k< tabla.length; k++){
				for(int i = 0; i < tabla.length - 1; i++){
					if(tabla[i] > tabla[i + 1]){
						temp = tabla[i];
						tabla[i] = tabla[i + 1];
						tabla[i + 1] = temp;
					}
				}
			}
		}else{
			for(int k = 0; k< tabla.length; k++){
				for(int i = 0; i < tabla.length - 1; i++){
					if(tabla[i] < tabla[i + 1]){
						temp = tabla[i];
						tabla[i] = tabla[i + 1];
						tabla[i + 1] = temp;
					}
				}
			}	
		}
	}
}