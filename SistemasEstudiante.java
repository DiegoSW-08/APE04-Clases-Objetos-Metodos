import java.util.Scanner;
 
// ─────────────────────────────────────────────
// CLASE ESTUDIANTE
// ─────────────────────────────────────────────
public class SistemasEstudiante {
 
    // Atributos privados: solo accesibles dentro de la clase
    private String cedula;
    private String nombre;
    private String apellido;
    private double nota1, nota2, nota3;
    private double promedio;
    private String estado;
 
    // ── Constructor: inicializa el objeto con los datos recibidos ──
    public SistemasEstudiante(String cedula, String nombre, String apellido,
                      double nota1, double nota2, double nota3) {
        this.cedula   = cedula;
        this.nombre   = nombre;
        this.apellido = apellido;
        this.nota1    = nota1;
        this.nota2    = nota2;
        this.nota3    = nota3;
        calcularPromedio();   // Se calcula automáticamente al crear el objeto
        determinarEstado();
    }
 
    // ── Getters: lectura de atributos privados ──
    public String getCedula()   { return cedula; }
    public String getNombre()   { return nombre; }
    public String getApellido() { return apellido; }
    public double getPromedio() { return promedio; }
    public String getEstado()   { return estado; }
 
    // ── Setters: modificación de atributos privados ──
    public void setNota1(double n) { this.nota1 = n; }
    public void setNota2(double n) { this.nota2 = n; }
    public void setNota3(double n) { this.nota3 = n; }
 
    // ── Calcula el promedio de las 3 notas ──
    public void calcularPromedio() {
        promedio = (nota1 + nota2 + nota3) / 3.0;
    }
 
    // ── Determina si el estudiante aprueba (promedio >= 7.00) ──
    public void determinarEstado() {
        estado = (promedio >= 7.00) ? "Aprobado" : "Reprobado";
    }
 
    // ── Muestra la información completa del estudiante ──
    public void mostrarInfo() {
        System.out.println("┌─────────────────────────────────────┐");
        System.out.printf("  Cédula   : %s%n", cedula);
        System.out.printf("  Nombre   : %s %s%n", nombre, apellido);
        System.out.printf("  Notas    : %.2f | %.2f | %.2f%n", nota1, nota2, nota3);
        System.out.printf("  Promedio : %.2f%n", promedio);
        System.out.printf("  Estado   : %s%n", estado);
        System.out.println("└─────────────────────────────────────┘");
    }
 
    // ─────────────────────────────────────────
    // MÉTODO MAIN: punto de entrada del programa
    // ─────────────────────────────────────────
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int TOTAL = 5;                        // Mínimo 5 estudiantes
        SistemasEstudiante[] lista = new SistemasEstudiante[TOTAL]; // Arreglo de objetos
 
        System.out.println("=== REGISTRO DE ESTUDIANTES ===\n");
 
        // ── Registro de cada estudiante ──
        for (int i = 0; i < TOTAL; i++) {
            System.out.println("--- Estudiante " + (i + 1) + " ---");
 
            System.out.print("  Cédula   : "); String ced = sc.next();
            System.out.print("  Nombre   : "); String nom = sc.next();
            System.out.print("  Apellido : "); String ape = sc.next();
 
            double n1 = pedirNota(sc, "Nota 1");
            double n2 = pedirNota(sc, "Nota 2");
            double n3 = pedirNota(sc, "Nota 3");
            System.out.println();
 
            // Se crea el objeto y se guarda en el arreglo
            lista[i] = new SistemasEstudiante(ced, nom, ape, n1, n2, n3);
        }
 
        // ── Listado completo ──
        System.out.println("\n=== LISTADO DE ESTUDIANTES ===");
        int aprobados = 0, reprobados = 0;
 
        for (SistemasEstudiante e : lista) {
            e.mostrarInfo();
            // Conteo de aprobados y reprobados
            if (e.getEstado().equals("Aprobado")) aprobados++;
            else                                  reprobados++;
        }
 
        // ── Resumen final ──
        System.out.println("\n=== RESUMEN ===");
        System.out.println("  Aprobados  : " + aprobados);
        System.out.println("  Reprobados : " + reprobados);
 
        sc.close();
    }
 
    // ─────────────────────────────────────────
    // Pide una nota válida (0–10), repite si es inválida
    // ─────────────────────────────────────────
    private static double pedirNota(Scanner sc, String label) {
        double nota;
        do {
            System.out.print("  " + label + " (0-10): ");
            nota = sc.nextDouble();
            if (nota < 0 || nota > 10)
                System.out.println("  ⚠ Nota inválida. Ingrese un valor entre 0 y 10.");
        } while (nota < 0 || nota > 10);   // Repite hasta obtener valor válido
        return nota;
    }
}