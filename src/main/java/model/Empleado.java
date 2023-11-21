package model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "empleados")
@NamedQuery(name = "Empleado.findAll",query="select e from Empleado e")

public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Integer id;
	private String nombre;
	private Double salario;
	private LocalDate nacido;
	@OneToOne
	@Nullable
	private Departamento departamento;
	@ManyToMany(mappedBy = "empleado")
	private List<Proyecto> proyecto;

	public void addProyecto(Proyecto p){
		if (proyecto == null) {
			System.out.println("Proyecto invalido");
		}
		proyecto.add(p);
	}
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
