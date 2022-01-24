package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {
	
	private Integer rooNumber;
	private Date checkIn;
	private Date checkOut;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}

	public Reservation(Integer rooNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {
			throw new DomainException("on: Check-out date must be after check-in date");
		}
		this.rooNumber = rooNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public Integer getRooNumber() {
		return rooNumber;
	}

//	ports 7800
//	cxf tem que ta com a rota dele do openshift
	
	public void setRooNumber(Integer rooNumber) {
		this.rooNumber = rooNumber;
	}
	
//	Metodo para calcular a entrada e saida de um hotel contada em millisegundos
//	e convertida de dias
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			 throw new DomainException("Reservation dates for update must be future dates");
		}
		
		if (!checkOut.after(checkIn)) {
			throw new DomainException("on: Check-out date must be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut; 
		
//		Pra esse caso especifico indica que não retornou nenhum erro
		
	}

	@Override
	public String toString() {
		return "Room "
			+ rooNumber
			+ ", checkIn=" 
			+ sdf.format(checkIn)
			+ ", checkOut"
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
				}
	
	
	
}
