package model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "empleados")
@NamedQuery(name = "Empleado.findAll",query="select e from Empleado e")

public class Empleado {
	@Id
	private  Integer id;
	private String nombre;
	private Double salario;
	private LocalDate nacido;
	private Departamento departamento;
	@ManyToMany
	private Proyecto proyecto;

	/**
	 * Devuelve representación de un empleado
	 * 
	 * @return string
	 */
	public String show() {
		if (id == 0) {
			return "no empleado!!!";
		}

		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%2d:%-20s:%4.2f:", id, nombre, salario));
		if (nacido == null) {
			sb.append("sin fecha de nacimiento!!");
		} else {
			sb.append(String.format("%s", nacido));
		}
		sb.append(":");
		if (departamento == null || departamento.getNombre() == null) {
			sb.append("sin departamento!!");
		} else {
			sb.append(String.format("Departamento [%2d:%s]", departamento.getId(), departamento.getNombre()));
		}
		
		return sb.toString();
	}

	public boolean isNull() {
		return this==null;
	}
}
