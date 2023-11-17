package com.example;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// Enum para representar el estado del vuelo
enum EstadoVuelo {
    PROGRAMADO,
    EN_AIRE,
    ATERRIZADO,
    CANCELADO
}

// Clase para representar una Torre de Control
class TorreControl {
    private int id;
    private String ubicacion;
    private String correo;
    private int telefono;
    private List<Vuelo> vuelosSupervisados;

    public TorreControl(int id, String ubicacion, String correo, int telefono) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.correo = correo;
        this.telefono = telefono;
        this.vuelosSupervisados = new ArrayList<>();
    }

    // Métodos para agregar y obtener vuelos supervisados
    public void agregarVueloSupervisado(Vuelo vuelo) {
        vuelosSupervisados.add(vuelo);
    }

    public List<Vuelo> getVuelosSupervisados() {
        return vuelosSupervisados;
    }

    // Métodos para obtener detalles de la torre
    public int getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefono() {
        return telefono;
    }
}

// Clase para representar una Aerolínea
class Aerolinea {
    private String nombre;
    private List<Vuelo> vuelosOperados;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelosOperados = new ArrayList<>();
    }

    // Métodos para agregar y obtener vuelos operados
    public void agregarVueloOperado(Vuelo vuelo) {
        vuelosOperados.add(vuelo);
    }

    public List<Vuelo> getVuelosOperados() {
        return vuelosOperados;
    }
    public String getNombre(){
        return nombre;
    }
}

// Clase para representar un Piloto
class Piloto {
    private String nombre;
    private String licencia;
    private List<Vuelo> vuelosAsignados;

    public Piloto(String nombre, String licencia) {
        this.nombre = nombre;
        this.licencia = licencia;
        this.vuelosAsignados = new ArrayList<>();
    }

    // Métodos para agregar y obtener vuelos asignados
    public void asignarVuelo(Vuelo vuelo) {
        vuelosAsignados.add(vuelo);
    }

    public List<Vuelo> getVuelosAsignados() {
        return vuelosAsignados;
    }

    // Método para obtener vuelos intervenidos por el piloto
    public List<Vuelo> getVuelosIntervenidos() {
        return vuelosAsignados.stream()
                .filter(vuelo -> vuelo.getEstado() != EstadoVuelo.CANCELADO)
                .collect(Collectors.toList());
    }
}

// Clase para representar un Vuelo
class Vuelo {
    private String terminal;
    private String puertaEmbarque;
    private String fechaDespegue;
    private String fechaAterrizaje;
    private String ruta;
    private TorreControl torreAsignada;
    private int numeroPasajeros;
    private EstadoVuelo estado;
    private String categoria;

    public Vuelo(String terminal, String puertaEmbarque, String fechaDespegue, String fechaAterrizaje,
                 String ruta, TorreControl torreAsignada, int numeroPasajeros, EstadoVuelo estado, String categoria) {
        this.terminal = terminal;
        this.puertaEmbarque = puertaEmbarque;
        this.fechaDespegue = fechaDespegue;
        this.fechaAterrizaje = fechaAterrizaje;
        this.ruta = ruta;
        this.torreAsignada = torreAsignada;
        this.numeroPasajeros = numeroPasajeros;
        this.estado = estado;
        this.categoria = categoria;
    }

    // Getter y Setter para la categoría
    
    public String getCategoria() {
        return categoria;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }
}