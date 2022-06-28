package ru.job4j.ood.carparking;

import java.util.ArrayList;
import java.util.List;

public class Parks implements Park {
    private int placePassenger;
    private int placeTrack;
    private List<Car> carList;

    public Parks(int placePassenger, int placeTrack, List<Car> carList) {
        this.placePassenger = placePassenger;
        this.placeTrack = placeTrack;
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return new ArrayList<>(carList);
    }

    @Override
    public boolean add(Car car) {
        boolean rsl = false;
        if (car.getVolume() == Passenger.SIZE && placePassenger > 0) {
            carList.add(car);
            placePassenger--;
            rsl = true;
        } else if (car.getVolume() > Passenger.SIZE && placeTrack > 0) {
            carList.add(car);
            placeTrack--;
            rsl = true;
        } else if (car.getVolume() > Passenger.SIZE && placeTrack == 0 && !(placePassenger < car.getVolume())) {
            carList.add(car);
            placePassenger -= car.getVolume();
            rsl = true;
        }
        return rsl;
    }
}
