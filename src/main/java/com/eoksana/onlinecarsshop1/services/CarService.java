package com.eoksana.onlinecarsshop1.services;

import com.eoksana.onlinecarsshop1.entity.Account;
import com.eoksana.onlinecarsshop1.entity.Car;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.repositories.AccountRepository;
import com.eoksana.onlinecarsshop1.repositories.CarRepository;
import com.eoksana.onlinecarsshop1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    @Autowired
    private final CarRepository carRepository;
    private final AccountRepository accountRepository;
    //private final AccountService accountService;
    private final UserRepository userRepository;

    public Car create(String carModel, String mark, int buildYear, double price,
                      String imageName) {
        Car car = new Car();

        //car.setCarId(carid);
        car.setMark(mark);
        car.setModell(carModel);
        car.setBuildYear(buildYear);
        car.setPrice(price);
        car.setImageName(imageName);
        car.setDeleted(false);
        car.setSold(false);

        Car newCar = this.carRepository.save(car);
        return newCar;
    }

    public Car getCarById(String carId) {
        return this.carRepository.findById(Long.valueOf(carId)).orElseThrow(() -> new RuntimeException("Car was not found"));
    }

    public Car update(String carId, String modell, String mark, int buildYear, String deleted,
                     String sold, String imageName) {
        Car car = getCarById(carId);
        // car.setCarId(id);
        car.setModell(modell);
        car.setMark(mark);
        car.setBuildYear(buildYear);
        car.setDeleted(Boolean.valueOf(deleted));
        car.setSold(Boolean.valueOf(sold));
        car.setImageName(imageName);
        Car updatedCar = this.carRepository.save(car);
        return updatedCar;
    }

    public void delete(String carId) {
        Car car = getCarById(carId);
        car.setDeleted(true);
        this.carRepository.save(car);
    }

    public void sell(String carId) {
        Car car = getCarById(carId);
        car.setSold(true);
        this.carRepository.save(car);
    }

//    public List<Car> getAllcars() {
//        return this.carRepository.findAll();
//    }

    public List<Car> getAllcarsByModell(String modell) {
        return this.carRepository.findAllByModell(modell);
    }

    public List<Car> getAllNotDeletedCar(){
        return this.carRepository.findAllByDeletedFalse();
    }
    public List<Car> getAllNotSoldCars(){
        return this.carRepository.findAllBySoldFalse();
    }
    public List<Car> getAllActiveCars(){
        return this.carRepository.findAllByDeletedFalseAndSoldFalse();

    }

//    public Account buyCar(String userId, String carId) {
//        User user = this.userRepository.findUserByUserId(Long.valueOf(userId));
//        int accountId = user.getAccount().getAccountId();
//        Optional<Car> carOpt = this.carRepository.findById(Long.valueOf(carId));
//        Car car = carOpt.get();
//        Optional<Account> accountOpt = this.accountService.withdrawMoney(String.valueOf(accountId), String.valueOf(car.getPrice()));
//        Account account = accountOpt.get();
//        this.delete(Long.valueOf(carId));
//        return account;
//
//    }
}