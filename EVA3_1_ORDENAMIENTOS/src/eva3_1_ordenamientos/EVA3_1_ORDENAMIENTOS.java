/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eva3_1_ordenamientos;

import java.util.Arrays;

/**
 *
 * @author Perla
 */
public class EVA3_1_ORDENAMIENTOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here       
        int[] datos = new int[10000];
        int[] copiaSel = new int[datos.length];
        int[] copiaIns = new int[datos.length];
        int[] copiaBubble = new int[datos.length];
        int[] copiaQuicks = new int[datos.length];
        
        long iniT, finT;
        llenar(datos); //envio el arreglo a llenar con valores aleatorios
        
        System.out.println(" Prueba con array sort: ");
        duplicar(datos, copiaSel);
        iniT = System.nanoTime();
        Arrays.sort(copiaSel);
        finT = System.nanoTime();
        System.out.println("Tiempo en ordenar: " + (finT - iniT));
        
        System.out.println("Prueba con selection sort: ");
        duplicar(datos, copiaSel);
        //imprimir(copiaSel);//imprimir contenido
        iniT = System.nanoTime();//tiempo antes de empezar el método
        selectionSort(copiaSel);
        finT = System.nanoTime();//tiempo al terminar el método
        //imprimir(copiaSel);//imprimir contenido
        System.out.println("Tiempo en ordernar: " + (finT - iniT));
        
        System.out.println("Prueba con insertion sort: ");
        duplicar(datos, copiaIns);
        //imprimir(copiaIns);//imprimir contenido
        iniT = System.nanoTime();//tiempo antes de empezar el método
        insertionSort(copiaIns);
        finT = System.nanoTime();//tiempo al terminar el método
        //imprimir(copiaIns);//imprimir contenido
        System.out.println("Tiempo en ordernar: " + (finT - iniT));
        
        System.out.println("Prueba con bubble sort:");
        duplicar(datos, copiaBubble);
        //imprimir(copiaBubble);//imprimir contenido
        iniT = System.nanoTime();//tiempo antes de empezar el método
        bubbleSort(copiaBubble);
        finT = System.nanoTime();//tiempo al terminar el método
        //imprimir(copiaBubble);//imprimir contenido
        System.out.println("Tiempo en ordernar: " + (finT - iniT));
        
        System.out.println("Prueba con quick sort: ");
        duplicar(datos, copiaQuicks);
        //imprimir(copiaQuickS);//imprimir contenido
        iniT = System.nanoTime();//tiempo antes de empezar el método
        quickSort(copiaQuicks);
        finT = System.nanoTime();//tiempo al terminar el método
        //imprimir(copiaBubble);//imprimir contenido
        System.out.println("Tiempo en ordernar: " + (finT - iniT));
    }
    
    //llenamos el arreglo con valores aleatorios 0 - 99
    public static void llenar(int[] datos){
        for (int i = 0; i < datos.length; i++) {
            datos[i] = (int)(Math.random() * 100);
        }
    }
    //copia del arreglo (arreglos del mismo tamaño)
    public static void duplicar(int[] datos, int[] copia){
        for (int i = 0; i < datos.length; i++){
            copia[i] = datos[i];
        }
    }
    //IMPRIMIR ARREGLO
    public static void imprimir(int[] datos){
        for (int i = 0; i < datos.length; i++) {
            System.out.print("[" + datos[i] + "]");
        }
        System.out.println("");
    }
    
    //eficiencia O(N>2)
    public static void selectionSort(int[] datos){
        for(int i = 0; i < datos.length; i++){
            int iMin = i;
            for(int j = i + 1; j < datos.length; j++){//busca la posicon del valor mas pequeno
                //COMPARAR
                //valor[j] vs valor[iMin]
                if(datos[j] < datos[iMin]){//comparo actual vs el minimo actual
                    iMin = j;
                }
            }
            //INTERCAMBIO
            if(i != iMin){
                //3 Pasos                
                int iTemp = datos[i]; //respaldar un valor                
                datos[i] = datos[iMin]; //intercambiar un valor              
                datos[iMin] = iTemp;   //reponer el valor respaldado
            }
        }
    }
    
    //Eficiencia O(N>2)
    public static void insertionSort(int[] datos){
        for(int i = 1; i < datos.length; i++){
            int temp = datos[i]; //valor a insertar
            int insP = i; //posición donde vamos a insertar
            for(int prev = i - 1; prev >= 0; prev--){//buscar donde insertar
                if(datos[prev] > temp){
                    datos[insP] = datos[prev]; //movemos valor de prev a la posición de insP
                    insP--; //retrocede una posición
                }else{
                    break;
                }
            }        
            datos[insP] = temp;
        }
    }
    
    
    public static void bubbleSort(int[] datos){
        for(int i = 0; i < datos.length; i++){
            for(int j = 0; j < (datos.length - 1); j++){//compara e intercambia
                if(datos[j] > datos[j + 1]){//comparamos j vs j + 1
                    //intercambiamos
                    int temp = datos[j];
                    datos[j] = datos[j + 1];
                    datos[j + 1] = temp;
                }
            }
        }
    }
    
    //QUICKSORT DE ARRANQUE
    //O(NlogN) Logaritmo base 2
    public static void quickSort(int[] datos){
        quickSortRecu(datos, 0, datos.length - 1);//quicksort a todo el arreglo
    }
    
    private static void quickSortRecu(int[] datos, int ini, int fin){
        int iPivote; //inicio
        int too_big; //busca a los más grandes que el pivote
        int too_small; //busca a los más pequeños que el pivote
        boolean stopBig = false, stopSmall = false;
        
        //controlar la recursividad:
        int tama = fin - ini + 1;
        if(tama > 1){//cuando si, puedo realizar quicksort?
            iPivote = ini;
            too_big = ini + 1;
            too_small = fin;
            for(int i = ini + 1; i <= fin; i++){//número de veces a recorrer
                if((datos[too_big] < datos[iPivote]) && (!stopBig)){//avanzo
                    too_big++; 
                }else{
                    stopBig = true; //me detengo cuando encuentro uno más grande
                }
                if((datos[too_small] >= datos[iPivote]) && (!stopSmall)){//retrocedo
                    too_small--;
                }else{
                    stopSmall = true; //me detengo cuando encuentro uno más pequeño
                }
                //ambos se detienen (swap)
                if(stopBig && stopSmall){//ambos índices se detuvieron
                    if(too_big < too_small){//intercambio swap
                        int temp = datos[too_big];
                        datos[too_big] = datos[too_small];
                        datos[too_small] = temp;
                        stopBig = false; //seguir avanzando
                        stopSmall = false; //seguir avanzando
                    }else
                        break; //termino el ciclo
                }
            }
            //intercambio el pivote
            int temp = datos[iPivote];
            datos[iPivote] = datos[too_small];
            datos[too_small] = temp;
            iPivote = too_small; //Ttambien cambia la posicion del pivote
            //quicksort (izq)
            quickSortRecu(datos, ini, iPivote - 1);
            //quicksort (der)
            quickSortRecu(datos, iPivote + 1, fin);
        }
    }
}
