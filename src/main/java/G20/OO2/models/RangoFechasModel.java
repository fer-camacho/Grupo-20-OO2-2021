package G20.OO2.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class RangoFechasModel {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;
	private String lugar;
	
	public RangoFechasModel() {
		super();
	}
	
	public RangoFechasModel(LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public RangoFechasModel(LocalDate fechaInicio, LocalDate fechaFin, String lugar) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.lugar = lugar;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
