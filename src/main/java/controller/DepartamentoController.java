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
public class DepartamentoController {
	
	private final Logger logger = Logger.getLogger(DepartamentoController.class.getName());
    private final DepartamentosRepository departamentosRepository;
    
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
}
