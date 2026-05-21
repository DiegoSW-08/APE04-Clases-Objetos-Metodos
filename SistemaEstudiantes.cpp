#include <iostream>
#include <vector>
#include <string>
#include <iomanip> // Para dar formato a los decimales (.precision)

using namespace std;

class Estudiante {
private:
    // Atributos privados
    string cedula;
    string nombre;
    string apellido;
    double nota1, nota2, nota3;
    double promedio;
    string estado;

public:
    // Constructor
    Estudiante(string _cedula, string _nombre, string _apellido, double _n1, double _n2, double _n3) {
        cedula = _cedula;
        nombre = _nombre;
        apellido = _apellido;
        nota1 = _n1;
        nota2 = _n2;
        nota3 = _n3;
        calcularPromedio(); // Se calcula automáticamente al instanciar
        determinarEstado();   // Se determina el estado automáticamente
    }

    // Métodos Get y Set
    string getCedula() { return cedula; }
    void setCedula(string c) { cedula = c; }

    string getNombre() { return nombre; }
    void setNombre(string n) { nombre = n; }

    string getApellido() { return apellido; }
    void setApellido(string a) { apellido = a; }

    double getNota1() { return nota1; }
    void setNota1(double n1) { nota1 = n1; calcularTodo(); }

    double getNota2() { return nota2; }
    void setNota2(double n2) { nota2 = n2; calcularTodo(); }

    double getNota3() { return nota3; }
    void setNota3(double n3) { nota3 = n3; calcularTodo(); }

    double getPromedio() { return promedio; }
    string getEstado() { return estado; }

    // Métodos de cálculo internos
    void calcularPromedio() {
        promedio = (nota1 + nota2 + nota3) / 3.0;
    }

    void determinarEstado() {
        if (promedio >= 7.0) {
            estado = "Aprobado";
        } else {
            estado = "Reprobado";
        }
    }

    // Función auxiliar si se modifican notas mediante setters
    void calcularTodo() {
        calcularPromedio();
        determinarEstado();
    }

    // Método para mostrar la información del estudiante
    void mostrarInformacion() {
        cout << "Cedula: " << cedula 
             << " | Estudiante: " << apellido << " " << nombre 
             << " | Notas: [" << nota1 << ", " << nota2 << ", " << nota3 << "]"
             << " | Promedio: " << fixed << setprecision(2) << promedio 
             << " | Estado: " << estado << endl;
    }
};

// Función auxiliar para validar que la nota esté entre 0 y 10
double pedirNotaValidada(string numeroNota) {
    double nota;
    while (true) {
        cout << "Ingrese la " << numeroNota << " (0 - 10): ";
        cin >> nota;
        if (nota >= 0.0 && nota <= 10.0) {
            return nota;
        }
        cout << "¡Nota invalida! Debe estar entre 0 y 10. Intente de nuevo.\n";
    }
}

int main() {
    vector<Estudiante> listaEstudiantes;
    int cantidadEstudiantes = 5; // Requerimiento mínimo

    cout << "=== REGISTRO DE ESTUDIANTES - ALGORITMOS ===" << endl;

    for (int i = 0; i < cantidadEstudiantes; i++) {
        string cedula, nombre, apellido;
        double n1, n2, n3;

        cout << "\n--- Datos del Estudiante " << (i + 1) << " ---" << endl;
        cout << "Ingrese Cedula: ";
        cin >> cedula;
        cout << "Ingrese Nombre: ";
        cin >> nombre;
        cout << "Ingrese Apellido: ";
        cin >> apellido;

        // Validar cada nota mediante la función auxiliar
        n1 = pedirNotaValidada("Nota 1");
        n2 = pedirNotaValidada("Nota 2");
        n3 = pedirNotaValidada("Nota 3");

        // Crear el objeto y guardarlo en el vector
        Estudiante nuevoEstudiante(cedula, nombre, apellido, n1, n2, n3);
        listaEstudiantes.push_back(nuevoEstudiante);
    }

    // Reportes finales
    cout << "\n=============================================" << endl;
    cout << "           LISTADO DE ESTUDIANTES            " << endl;
    cout << "=============================================" << endl;
    
    int aprobados = 0;
    int reprobados = 0;

    for (int i = 0; i < listaEstudiantes.size(); i++) {
        listaEstudiantes[i].mostrarInformacion();
        
        if (listaEstudiantes[i].getEstado() == "Aprobado") {
            aprobados++;
        } else {
            reprobados++;
        }
    }

    cout << "\n=============================================" << endl;
    cout << "RESUMEN DE RENDIMIENTO" << endl;
    cout << "Total Estudiantes Aprobados: " << aprobados << endl;
    cout << "Total Estudiantes Reprobados: " << reprobados << endl;
    cout << "=============================================" << endl;

    return 0;
}