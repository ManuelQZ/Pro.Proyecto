package com.example;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de las clases
        TorreControl torre1 = new TorreControl(1, "Ubicacion1", "correo1", 0);
        Aerolinea aerolinea1 = new Aerolinea("Aerolinea1");
        Piloto piloto1 = new Piloto("Piloto1", "Licencia1");

        // Crear un vuelo y asignarlo a la torre, aerolínea y piloto
        Vuelo vuelo1 = new Vuelo("Terminal1", "Puerta1", "FechaDespegue1", "FechaAterrizaje1",
                "Ruta1", torre1, 100, EstadoVuelo.PROGRAMADO, "Internacional");

        torre1.agregarVueloSupervisado(vuelo1);
        aerolinea1.agregarVueloOperado(vuelo1);
        piloto1.asignarVuelo(vuelo1);

        // Ejemplo de obtener la lista de vuelos supervisados por la torre
        List<Vuelo> vuelosTorre = torre1.getVuelosSupervisados();
        System.out.println("Vuelos supervisados por la torre: " + vuelosTorre.size());

        // Ejemplo de obtener la lista de vuelos operados por la aerolínea
        List<Vuelo> vuelosAerolinea = aerolinea1.getVuelosOperados();
        System.out.println("Vuelos operados por la aerolínea: " + vuelosAerolinea.size());

        // Ejemplo de obtener la lista de vuelos asignados al piloto
        List<Vuelo> vuelosPiloto = piloto1.getVuelosAsignados();
        System.out.println("Vuelos asignados al piloto: " + vuelosPiloto.size());

        // Ejemplo de obtener el directorio de vuelos intervenidos por el piloto
        List<Vuelo> vuelosIntervenidos = piloto1.getVuelosIntervenidos();
        System.out.println("Vuelos intervenidos por el piloto: " + vuelosIntervenidos.size());

        // Resto del código para obtener el directorio de aerolíneas
    Map<String, Map<String, Long>> directorioAerolineas = new HashMap<>();

    // Obtener datos de vuelos operados, programados y cancelados por aerolínea
    for (Aerolinea aerolinea : aerolinea1) {
        List<Vuelo> vuelosOperados = aerolinea.getVuelosOperados();
        List<Vuelo> vuelosProgramados = vuelosOperados.stream()
                .filter(vuelo -> vuelo.getEstado() == EstadoVuelo.PROGRAMADO)
                .collect(Collectors.toList());
        List<Vuelo> vuelosCancelados = vuelosOperados.stream()
                .filter(vuelo -> vuelo.getEstado() == EstadoVuelo.CANCELADO)
                .collect(Collectors.toList());

        // Agregar datos al directorio
        Map<String, Long> datosAerolinea = new HashMap<>();
        datosAerolinea.put("Operados", (long) vuelosOperados.size());
        datosAerolinea.put("Programados", (long) vuelosProgramados.size());
        datosAerolinea.put("Cancelados", (long) vuelosCancelados.size());

        directorioAerolineas.put(aerolinea.getNombre(), datosAerolinea);
    }

    // Ordenar el directorio de aerolíneas de forma decreciente
    directorioAerolineas.entrySet().stream()
            .sorted((entry1, entry2) -> {
                long vuelosOperados1 = entry1.getValue().get("Operados");
                long vuelosOperados2 = entry2.getValue().get("Operados");
                return Long.compare(vuelosOperados2, vuelosOperados1);
            })
            .forEach(entry -> {
                System.out.println("Aerolínea: " + entry.getKey());
                System.out.println("  Vuelos Operados: " + entry.getValue().get("Operados"));
                System.out.println("  Vuelos Programados: " + entry.getValue().get("Programados"));
                System.out.println("  Vuelos Cancelados: " + entry.getValue().get("Cancelados"));
            });
    }

}

