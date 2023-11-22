import java.util.Arrays;
import java.util.Scanner;

public class Matricula 
{
    private static final int MAX_ASIGNATURAS = 7;
    private static final int MAX_CREDITOS_POR_ASIGNATURA = 4;
    private static final int MAX_CREDITOS_TOTALES = 28;
    private static final int MAX_FRECUENCIA_INSCRIPCION = 3;
    public static void main(String[] args) 
    {
        String[][] datos = new String[2][7]; 

        String[] atributos = 
        {
                "Nombres y apellidos",
                "Número de carnet",
                "Carrera",
                "Turno",
                "Plan de estudio",
                "Semestre",
                "Fecha de matrícula"
        };

        String[] atributosAdicionales = 
        {
                "Número de recibo",
                "Número de inscripción"
        };

        Scanner local = new Scanner(System.in);

        for (int i = 0; i < atributosAdicionales.length; i++)
        {
            System.out.print("Ingrese el valor para '" + atributosAdicionales[i] + "': ");
            datos[1][i] = local.nextLine();
        }
 
        for (int i = 0; i < atributos.length; i++) 
        {
            datos[0][i] = atributos[i];
            int indiceDatos1 = i + atributosAdicionales.length;

            if (indiceDatos1 < datos[1].length) 
            {
                System.out.print("Ingrese el valor para '" + datos[0][i] + "': ");
                datos[1][indiceDatos1] = local.nextLine();
            } 
            else 
            {
                
                System.out.print("Ingrese el valor para '" + datos[0][i] + "': ");
                datos[1] = Arrays.copyOf(datos[1], indiceDatos1 + 1);
                datos[1][indiceDatos1] = local.nextLine();
            }
        }

        int cantidadAsignaturas = MetodosMatricula.pedirEntero("Indique las asignaturas no más de 7: ", 1, MAX_ASIGNATURAS);
        String[] codigos = new String[cantidadAsignaturas];
        String[] nombres = new String[cantidadAsignaturas];
        String[] grupos = new String[cantidadAsignaturas];
        String[] aulas = new String[cantidadAsignaturas];
        int[] creditos = new int[cantidadAsignaturas];
        int[] frecuencias = new int[cantidadAsignaturas];
        boolean[] retiros = new boolean[cantidadAsignaturas];

        for (int i = 0; i < cantidadAsignaturas; i++) 
        {
            System.out.println("Complete la siguiente información " + (i + 1) + ":");
            codigos[i] = MetodosMatricula.pedirTexto("Código de la asignatura: ");
            nombres[i] = MetodosMatricula.pedirTexto("Nombre de la asignatura: ");
            grupos[i] = MetodosMatricula.pedirTexto("Grupo asignado: ");
            aulas[i] = MetodosMatricula.pedirTexto("Sección: ");
            creditos[i] = MetodosMatricula.pedirEntero("Créditos no más de 4: ", 1, MAX_CREDITOS_POR_ASIGNATURA);
            frecuencias[i] = MetodosMatricula.pedirEntero("Frecuencia de inscripción no más de 3: ", 1, MAX_FRECUENCIA_INSCRIPCION);
            retiros[i] = MetodosMatricula.pedirBoolean("¿Retirará la asignatura? (si o no): ");
        }

        if (!MetodosMatricula.validarRestricciones(creditos)) 
        {
            System.out.println("Error: La suma total de créditos no puede ser mayor que " + MAX_CREDITOS_TOTALES + ".");
            return;
        }

        System.out.println("=================================================================================================\r\n" + 
                "-----------------------------------------HOJA DE MATRICULA---------------------------------------\r\n" + 
                "-----------------------------------------AÑO ACADEMICO 2022---------------------------------------\r\n" + 
                "=================================================================================================");
        
        MetodosMatricula.mostrarTabla(atributosAdicionales, datos[1], 2);

        MetodosMatricula.mostrarTabla(atributos, datos[1], atributosAdicionales.length);

        MetodosMatricula.imprimirDatos(codigos, nombres, grupos, aulas, creditos, frecuencias, retiros);
    }
}
