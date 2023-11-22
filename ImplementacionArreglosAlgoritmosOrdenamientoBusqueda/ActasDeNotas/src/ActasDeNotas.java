import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class ActasDeNotas 
{
    public static void main(String[] args) 
    {
        Scanner tc = new Scanner(System.in);
        boolean continuar;

        do 
        {
            String[][] datos = new String[2][6];

            System.out.println("Ingrese el nombre de su curso:");
            datos[0][0] = "Nombre del curso";
            datos[1][0] = tc.nextLine();

            System.out.println("Ingrese el nombre de su carrera:");
            datos[0][1] = "Carrera";
            datos[1][1] = tc.nextLine();

            System.out.println("Ingrese su Modalidad (D - DIURNO / M - MATURNO):");
            datos[0][2] = "Modalidad";
            datos[1][2] = String.valueOf(tc.next().charAt(0));

            System.out.println("Indique el código de su curso:");
            datos[0][3] = "Código del curso";
            datos[1][3] = String.valueOf(tc.nextInt());

            System.out.println("Indique código de su asignatura:");
            datos[0][4] = "Código de asignatura";
            datos[1][4] = String.valueOf(tc.nextInt());

            System.out.println("Código de programa de asignatura:");
            datos[0][5] = "Código de programa de asignatura";
            datos[1][5] = String.valueOf(tc.nextInt());

            MetodosNotas.mostrarTabla(datos);

            System.err.println("                                               ");
            System.out.println("Ingrese el Numero de estudiantes del Grupo: ");

            int dimen = tc.nextInt();

            tc.nextLine(); 

            String[][] std = new String[dimen][10];

    
            for (int i = 0; i < dimen; i++) 
            {
                MetodosNotas.Leerdatos(tc, std, i);
            }

            Arrays.sort(std, Comparator.comparing(estudiantes -> MetodosNotas.obtenerApellido(estudiantes[2])));
        
            MetodosNotas.ImprimirActa(std);
            MetodosNotas.MostrarDatosGenerales(std);

            System.out.println("Usar la Aplicacion nuevamente (Si-No): ");
            char respuesta = tc.next().charAt(0);
            continuar = (respuesta == 'S' || respuesta == 's');

        }while(continuar);

        System.out.println("Fin del programa.");
    }
}
