package controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Empleado;
import repositorios.empleados.EmpleadosRepository;
import repositorios.empleados.EmpleadosRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class EmpleadoController {

	private final Logger logger = Logger.getLogger(EmpleadoController.class.getName());
	private  EmpleadosRepository empleadosRepository =new EmpleadosRepositoryImpl();
	
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
	
}
