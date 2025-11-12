
package org.example.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.common.CarDto;
import org.example.parkinglot.entities.Car;


@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("msg: \"findAllCars\"");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> dtos = new ArrayList<CarDto>();
        for (Car car : cars) {
            CarDto dto = new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );
            dtos.add(dto);
        }
        return dtos;
    }

}