package controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Departamento;
import model.Empleado;
import model.Proyecto;
import repositorios.departamentos.DepartamentosRepository;
import repositorios.empleados.EmpleadosRepository;
import repositorios.proyectos.ProyectosRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class EmpresaController {

    private final Logger logger = Logger.getLogger(EmpresaController.class.getName());
    private final DepartamentosRepository departamentosRepository;
    private final EmpleadosRepository empleadosRepository;
    private final ProyectosRepository proyectosRepository;


    //Departamentos
    public List<Departamento> getDepartamentos(){
        logger.info("Obteniendo Departamentos");
        return departamentosRepository.findAll();
    }
    public List<Departamento> getDepartamentosByNombre(String nombre){
        logger.info("Obteniendo Departamentos que empiezan por " + nombre);
        return departamentosRepository.findByName(nombre);
    }
    public Departamento crearDepartamento(Departamento departamento) {
        logger.info("Creando Departamento");
        return departamentosRepository.create(departamento);
    }
    public Optional<Departamento> getDepartamentoId(Integer id) {
        logger.info("Obteniendo Departamento con id: " + id);
        return departamentosRepository.findById(id);
    }
    public Departamento updateTenista(Departamento departamento) {
        logger.info("Actualizando Departamento con id: " + departamento.getId());
        return departamentosRepository.create(departamento);
    }
    public Boolean deleteDepartamento(Departamento departamento) {
        logger.info("Eliminando Departamento con id: " + departamento.getId());
        return departamentosRepository.delete(departamento);
    }

    //Empleados
    public List<Empleado> getEmpleados(){
        logger.info("Obteniendo Empleados");
        return empleadosRepository.findAll();
    }
    public List<Empleado> getEmpleadosByNombre(String nombre){
        logger.info("Obteniendo Empleados que empiezan por " + nombre);
        return empleadosRepository.findByName(nombre);
    }
    public Empleado createEmpleado(Empleado empleado) {
        logger.info("Creando Empleado");
        return empleadosRepository.create(empleado);
    }
    public Optional<Empleado> getEmpleadoId(Integer id) {
        logger.info("Obteniendo Empleado con id: " + id);
        return empleadosRepository.findById(id);
    }
    public Empleado updateEmpleado(Empleado empleado) {
        logger.info("Actualizando Empleado con id: " + empleado.getId());
        return empleadosRepository.create(empleado);
    }
    public Boolean deleteEmpleado(Empleado empleado) {
        logger.info("Eliminando Empleado con id: " + empleado.getId());
        return empleadosRepository.delete(empleado);
    }
    //Proyectos
    public List<Proyecto> getProyectos(){
        logger.info("Obteniendo Proyectos");
        return proyectosRepository.findAll();
    }
    public List<Proyecto> getProyectosByNombre(String nombre){
        logger.info("Obteniendo Proyectos que empiezan por " + nombre);
        return proyectosRepository.findByName(nombre);
    }
    public Proyecto createProyecto(Proyecto proyecto) {
        logger.info("Creando Proyecto");
        return proyectosRepository.create(proyecto);
    }
    public Optional<Proyecto> getProyectoId(Integer id) {
        logger.info("Obteniendo Proyecto con id: " + id);
        return proyectosRepository.findById(id);
    }
    public Proyecto updateProyecto(Proyecto proyecto) {
        logger.info("Actualizando Proyecto con id: " + proyecto.getId());
        return proyectosRepository.create(proyecto);
    }
    public Boolean deleteProyecto(Proyecto proyecto) {
        logger.info("Eliminando Proyecto con id: " + proyecto.getId());
        return proyectosRepository.delete(proyecto);
    }
}
