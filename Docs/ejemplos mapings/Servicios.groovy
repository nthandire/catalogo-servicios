package inr.gob.mx
import java.util.Date;


class Servicios {

	Integer noOrden
	Date fecha = new Date()
	String reporto
	String serie
	String equipo
	String marca
	String modelo
	String economico
	String nombreArea
	String extension
	CatFallas falla
	String cuerpo
	Boolean garantia
	String statusCampo
	String statusLaboratorio
	Usuarios personaElaboro
	Usuarios personaRealizo
	//String instituto
	
	static constraints = {
		statusLaboratorio(nullable:true)
    }
	
	static mapping = {
		id column:'id_serviciocampo'
		id generator: 'identity'
		personaElaboro column: 'persona_elaboro'
		personaRealizo column: 'persona_realizo'
		falla column: 'falla'	
		table 'servicios_campo'
		version false
		datasource 'soptec'
		}	
	
}
