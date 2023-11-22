import java.util.Scanner;

public class MetodosNotas
{
    static void mostrarTabla(String[][] datos) 
    {
        System.out.println("                     ");
        System.out.println("---Datos Generales--");
        System.out.println("=================================================================================================");
        for (int i = 0; i < datos[0].length; i++) 
        {
            if (i % 3 == 0) 
            {
                System.out.print(" ");
            }
            String atributo = (datos[0][i] != null) ? datos[0][i].trim() : "No especificado";
            String valor = (datos[1][i] != null) ? datos[1][i].trim() : "No especificado";

            System.out.printf(" %-15s: %-15s", atributo, valor);
            if (i % 3 == 2 || i == datos[0].length - 1) 
            {
                System.out.println(" ");
            }
        }
    }
    // ==========================================================================
    static void Leerdatos(Scanner datos, String[][] estudiantes, int indice) 
    {

        System.out.println("Escriba un Nombre y un Apellido " + (indice + 1) + ": ");

        String ncompleto = datos.nextLine();
        String[] partes = ncompleto.split("\\s");

        
        if (partes.length > 1) 
        {
            estudiantes[indice][1] = partes[0]; 
            estudiantes[indice][2] = partes[1]; 
        } else 
        {
            System.out.println("Ingrese un nombre y un apellido. Ingrese nuevamente.");
            Leerdatos(datos, estudiantes, indice);
            return;
        }

        System.out.println("Ingrese el número de carnet del estudiante " + (indice + 1) + ": ");
        estudiantes[indice][0] = datos.next();
    
        double notaPrimerParcial = ingresarNota(datos, "Indique la nota del primer examen parcial (0 a 35) " + (indice + 1) + ": ", 0, 35);
        estudiantes[indice][3] = String.valueOf(notaPrimerParcial);

        double notaSistematicos = ingresarNota(datos, "Ingrese la nota de los sistemáticos (máximo 30.00) para el estudiante " + (indice + 1) + ": ", 0, 30);
        estudiantes[indice][4] = String.valueOf(notaSistematicos);

        System.out.println("¿La asignatura lleva proyecto de curso? (S - SI / N - NO): ");
        char incluyeProyecto = datos.next().charAt(0);


        if (incluyeProyecto == 'S' || incluyeProyecto == 's') 
        {
            double notaProyecto = ingresarNota(datos, "Ingrese la nota del proyecto (máximo 35.00) para el estudiante " + (indice + 1) + ": ", 0, 35);
            estudiantes[indice][5] = String.valueOf(notaProyecto);
            estudiantes[indice][6] = "0"; 
        } 
        else 
        {
            estudiantes[indice][5] = "0"; 
            double notaSegundoParcial = ingresarNota(datos, "Ingrese la nota del segundo parcial (máximo 35.00) para el estudiante " + (indice + 1) + ": ", 0, 35);
            estudiantes[indice][6] = String.valueOf(notaSegundoParcial);
        }

        double notaFinal = notaPrimerParcial + notaSistematicos + Double.parseDouble(estudiantes[indice][5]) + Double.parseDouble(estudiantes[indice][6]);

        if (notaFinal < 60.00) 
        {
            double examenPrimeraConvocatoria = ingresarNota(datos, "Ingrese la nota del Examen de primera convocatoria (máximo 70.00) para el estudiante " + (indice + 1) + ": ", 0, 70);
            notaFinal = notaSistematicos + examenPrimeraConvocatoria;

            
            if (notaFinal < 60.00) 
            {
                double examenSegundaConvocatoria = ingresarNota(datos, "Ingrese la nota del Examen de segunda convocatoria (máximo 100.00) para el estudiante " + (indice + 1) + ": ", 0, 100);
                notaFinal = examenSegundaConvocatoria;
            }
        }

        estudiantes[indice][7] = String.valueOf(notaFinal);

        
        if (notaFinal >= 60.00) 
        {
            estudiantes[indice][8] = "Sí";
        } 
        else 
        {
            estudiantes[indice][8] = "No"; 
        }
        datos.nextLine();

        
        System.out.println("¿Está Activo? (A - Activo / N - Desertó): ");
        char desertion = datos.next().charAt(0);
        estudiantes[indice][9] = desertion == 'A' || desertion == 'a' ? "Activo" : "Baja";
        System.out.println("                                                          ");
    }
    // ==========================================================================
    private static double ingresarNota(Scanner datos, String mensaje, double min, double max) 
    {
        double nota;
        do 
        {
            System.out.println(mensaje);
            nota = datos.nextDouble();
            if (nota < min || nota > max) 
            {
                System.out.println("Ingrese una nota válida en el rango de " + min + " a " + max + ".");
            }
        } while (nota < min || nota > max);
        return nota;
    }
    // ==========================================================================
    static String obtenerApellido(String nombresApellidos) 
    {
        String[] partes = nombresApellidos.split("\\s");
        return partes.length > 1 ? partes[1] : "";
    }

    // ==========================================================================
    static void ImprimirActa(String[][] estudiantes) 
    {
        System.out.println("Acta de Notas");
        System.out.println(                                     );
        System.out.println("_________________________________________________________________________________________________________________________________________________");
        System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s %n",
        "N° Carnet", "Nombre", "Apellido", "1er Parcial", "Sistemáticos", "Proyecto", "2do Parcial", "Nota Final", "¿Aprobado?", "Estado");
        System.out.println("                                                                                                                                ");

        for (String[] estudiante : estudiantes) 
        {
            System.out.printf("  %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s  %n",
            estudiante[0], estudiante[1], estudiante[2], estudiante[3], estudiante[4], estudiante[5], estudiante[6], estudiante[7], estudiante[8], estudiante[9]);
        }
        System.out.println("______________________________________________________________________________________________________________________________________________________");
    }
    // ==========================================================================
    private static int calcularMatriculaEfectiva(String[][] estudiantes) 
    {
        int contador = 0;
        for (String[] estudiante : estudiantes) 
        {
            if (estudiante[9].equalsIgnoreCase("Activo")) 
            {
                contador++;
            }
        }
        return contador;
    }
    // ==========================================================================
    private static int calcularNumeroDeserciones(String[][] estudiantes) 
    {
        int contador = 0;
        for (String[] estudiante : estudiantes) 
        {
            if (estudiante[9].equalsIgnoreCase("Desertó")) 
            {
                contador++;
            }
        }
        return contador;
    }
    // ==========================================================================
    private static int contarAprobados(String[][] estudiantes) 
    {
        int contador = 0;
        for (String[] estudiante : estudiantes) 
        {
            double notaFinal = Double.parseDouble(estudiante[7]);
            if (notaFinal >= 60.0) 
            {
                contador++;
            }
        }
        return contador;
    }
    // ==========================================================================
    private static double calcularPorcentajeAprobados(String[][] estudiantes) 
    {
        int totalEstudiantes = estudiantes.length;
        int aprobados = contarAprobados(estudiantes);
        return (double) aprobados / totalEstudiantes * 100.0;
    }
    // ==========================================================================
    private static double calcularNotaMinima(String[][] estudiantes) 
    {
        double notaMinima = Double.MAX_VALUE;
        for (String[] estudiante : estudiantes) 
        {
            double notaFinal = Double.parseDouble(estudiante[7]);
            if (notaFinal < notaMinima) 
            {
                notaMinima = notaFinal;
            }
        }
        return notaMinima;
    }
    // ==========================================================================
    private static double calcularNotaMaxima(String[][] estudiantes) 
    {
        double notaMaxima = Double.MIN_VALUE;
        for (String[] estudiante : estudiantes) 
        {
            double notaFinal = Double.parseDouble(estudiante[7]);
            if (notaFinal > notaMaxima) 
            {
                notaMaxima = notaFinal;
            }
        }
        return notaMaxima;
    }
    // ==========================================================================
    private static double calcularPromedioNotas(String[][] estudiantes) 
    {
        double sumaNotas = 0;
        for (String[] estudiante : estudiantes) 
        {
            sumaNotas += Double.parseDouble(estudiante[7]);
        }
        return sumaNotas / estudiantes.length;
    }
    // ==========================================================================
    static void MostrarDatosGenerales(String[][] estudiantes) 
    {

        int matriculaInicial = estudiantes.length;
        int matriculaEfectiva = calcularMatriculaEfectiva(estudiantes);
        int numeroDeserciones = calcularNumeroDeserciones(estudiantes);
        int cantidadAprobados = contarAprobados(estudiantes);
        double porcentajeAprobados = calcularPorcentajeAprobados(estudiantes);
        int cantidadReprobados = matriculaEfectiva - cantidadAprobados;
        double porcentajeReprobados = 100.0 - porcentajeAprobados;
        double notaMinima = calcularNotaMinima(estudiantes);
        double notaMaxima = calcularNotaMaxima(estudiantes);
        double promedioNotas = calcularPromedioNotas(estudiantes);

        String[][] datosGenerales = 
        {

                {"Matrícula inicial", "Matrícula efectiva", "Número de deserciones", "Cantidad de aprobados",
                "% de aprobados", "Cantidad de reprobados", "% de reprobados", "Nota mínima", "Nota máxima",
                "Promedio de notas"},
                {String.valueOf(matriculaInicial), String.valueOf(matriculaEfectiva),
                String.valueOf(numeroDeserciones), String.valueOf(cantidadAprobados),
                String.format("%.2f%%", porcentajeAprobados), String.valueOf(cantidadReprobados),
                String.format("%.2f%%", porcentajeReprobados), String.valueOf(notaMinima),
                String.valueOf(notaMaxima), String.format("%.2f", promedioNotas)}
        };
        System.out.println("                     ");
        System.out.println("---Datos Generales--");
        System.out.println("=================================================================================================");
    
        for (int i = 0; i < datosGenerales[0].length; i++) 
        {
            String atributo = datosGenerales[0][i];
            String valor = datosGenerales[1][i];
            System.out.printf(" %-25s: %-25s", atributo, valor);
        
            if (i % 2 == 1 || i == datosGenerales[0].length - 1) 
            {
                System.out.println(" ");
            } 
            else 
            {
                System.out.print(" | ");
            }
        }
    }
}

