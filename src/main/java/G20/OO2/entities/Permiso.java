package G20.OO2.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="permiso")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPermiso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pedido_id", nullable=false)
	protected Persona pedido;
	
	@Column(name="fecha", nullable = false)
	protected LocalDate fecha;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="permiso_lugar", joinColumns={@JoinColumn(name="permiso_id", nullable = false)}, inverseJoinColumns={@JoinColumn(name="lugar_id", nullable = false)})
	protected Set<Lugar> desdeHasta = new HashSet<Lugar>();

	public Permiso() {
	}

	public Permiso(int idPermiso, Persona pedido, LocalDate fecha) {
		this.idPermiso = idPermiso;
		this.pedido = pedido;
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", pedido=" + pedido + ", fecha=" + fecha + ", desdeHasta="
				+ desdeHasta + "]";
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Persona getPedido() {
		return pedido;
	}

	public void setPedido(Persona pedido) {
		this.pedido = pedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<Lugar> getDesdeHasta() {
		return desdeHasta;
	}
	
	public void addLugar(Lugar lugar){
        if(this.desdeHasta == null){
            this.desdeHasta = new HashSet<>();
        }
        
        this.desdeHasta.add(lugar);
    }
	
}
