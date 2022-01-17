/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_2_busqueda_binaria;

import java.util.Scanner;

/**
 *
 * @author Perla
 */
public class EVA3_2_BUSQUEDA_BINARIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] datos = new int[1000];
        llenar(datos);
        //imprimir(datos);
        selectionSort(datos);
        //imprimir(datos);
        Scanner input = new Scanner(System.in);
        System.out.println("Valor a buscar: ");
        int valor = input.nextInt();
        int iResu = binarySearch(datos, valor);
        System.out.println("El elemento está en la posición: " + iResu);
    }
    
    public static void llenar(int[] datos){
        for(int i = 0; i < datos.length; i++){
            datos[i] = (int)(Math.random() * 100);
        }
    }
    
    public static void imprimir(int[] datos){
        for(int i = 0; i < datos.length; i++){
            System.out.print("[" + datos[i] + "]");
        }
        System.out.println("");
    }
    
    public static void selectionSort(int[] datos){
        for(int i = 0; i < datos.length; i++){
            int iMin = i;
            for (int j = i + 1; j < datos.length; j++){
                if(datos[j] < datos[iMin]){
                    iMin = j;
                }
            }
            if(i != iMin){
                int iTemp = datos[i];
                datos[i] = datos[iMin];
                datos[iMin] = iTemp;
            }
        }
    }

    public static int binarySearch(int[] datos, int valBuscar){
        return binarySearchRecu(datos, valBuscar, 0, datos.length - 1);
    }
    
    private static int binarySearchRecu(int[] datos, int valBuscar, int ini, int fin){
        int iMid, iResu;
        iMid = ini + ((fin - ini) / 2);//posición a la mitad de la busqueda
        iResu = -1;//si el valor no existe regresamos -1
        if(fin >= ini){//buscamos
            if(valBuscar == datos[iMid]){//el valor esta a la mitad
                iResu = iMid;//aquí esta el valor, y lo regresamos
            }else if(valBuscar < datos[iMid]){//no esta a la mitad, pero esta a la izq
                //llamamos recursivamente a la busqueda binaria
                iResu = binarySearchRecu(datos, valBuscar, ini, iMid - 1);
            }else{//no esta a la mitad, pero puede estar a la der
                iResu = binarySearchRecu(datos, valBuscar, iMid + 1, fin);
            }
        }//se detiene el proceso
        return iResu;
    }
}
