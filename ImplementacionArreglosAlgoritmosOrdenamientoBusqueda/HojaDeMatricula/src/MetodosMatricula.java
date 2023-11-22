import java.util.Scanner;

public class MetodosMatricula 
{
    private static final int COLUMN_WIDTH = 25;
    private static final int COLUMNS_PER_ROW = 2;
    private static final int MAX_ASIGNATURAS = 7;
    private static final int MAX_CREDITOS_POR_ASIGNATURA = 4;
    private static final int MAX_CREDITOS_TOTALES = 28;
    private static final int MAX_FRECUENCIA_INSCRIPCION = 3;

    private static final int dimencolumna = 15;
    // ==========================================================================
    public static void mostrarTabla(String[] atributos, String[] valores, int offset) 
    {
        for (int i = 0; i < atributos.length; i++) 
        {
            String header = atributos[i];
            String value = valores[i + offset];
            System.out.printf(" %-"+COLUMN_WIDTH+"s", header);

            if (i % COLUMNS_PER_ROW == COLUMNS_PER_ROW - 1 || i == atributos.length - 1) 
            {
                System.out.printf(": %-"+COLUMN_WIDTH+"s", value);
                System.out.println();
            }
        }
    }
    // ==========================================================================
    public static int pedirEntero(String mensaje, int min, int max) 
    {
        Scanner local = new Scanner(System.in);
        int valor;
        do 
        {
            System.out.print(mensaje);

            while (!local.hasNextInt()) 
            {
                System.out.println("Ingrese un número correcto");
                local.next(); 
            }
            valor = local.nextInt();
            local.nextLine(); 
        } while (valor < min || valor > max);
        return valor;
    }
    // ==========================================================================
    public static String pedirTexto(String mensaje) 
    {
        Scanner local = new Scanner(System.in);
        System.out.print(mensaje);
        return local.nextLine();
    }
    // ==========================================================================
    public static boolean pedirBoolean(String mensaje) 
    {
        Scanner local = new Scanner(System.in);
        String respuesta;
        do 
        {
            System.out.print(mensaje);
            respuesta = local.nextLine().toLowerCase();
        }while (!respuesta.equals("si") && !respuesta.equals("no"));
        return respuesta.equals("si");
    }
    // ==========================================================================
    public static boolean validarRestricciones(int[] creditos) 
    {
        int sumaCreditos = 0;
        for (int credito : creditos) 
        {
            sumaCreditos += credito;
            if (credito > MAX_CREDITOS_POR_ASIGNATURA) 
            {
                System.out.println("Error: Los créditos por asignatura no pueden ser mayores que " + MAX_CREDITOS_POR_ASIGNATURA + ".");
                return false;
            }
        }
        return sumaCreditos <= MAX_CREDITOS_TOTALES;
    }
    // ==========================================================================
    public static void imprimirDatos(String[] codigos, String[] nombres, String[] grupos, String[] aulas, int[] creditos, int[] frecuencias, boolean[] retiros) 
    {
        System.out.println("_________________________________________________________________________________________________");
        System.out.printf("%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s", 
         "N°", "N Asignatura", "ASIGNATURA", "GRUPO", "AULA", "CRED", "F", "R");
        System.out.println();

        for (int i = 0; i < codigos.length; i++) 
        {
            System.out.printf("%-"+dimencolumna+"d%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"s%-"+dimencolumna+"d%-"+dimencolumna+"d%-"+dimencolumna+"s", 
            (i + 1), codigos[i], nombres[i], grupos[i], aulas[i], creditos[i], frecuencias[i], (retiros[i] ? "Sí" : "No"));
            System.out.println();
        }
        System.out.println("__________________________________________________________________________________________________");
    }   
}
