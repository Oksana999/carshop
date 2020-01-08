package com.eoksana.onlinecarsshop1.controllers;

import com.eoksana.onlinecarsshop1.entity.Car;
import com.eoksana.onlinecarsshop1.entity.User;
import com.eoksana.onlinecarsshop1.repositories.CarRepository;
import com.eoksana.onlinecarsshop1.repositories.UserRepository;
import com.eoksana.onlinecarsshop1.services.CarService;
import com.eoksana.onlinecarsshop1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class CarController {
    @Autowired
    private final CarService carService;
    private final CarRepository carRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession session;

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/home")
    public String home1(){
        return "home";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/checkRegistration")
    public String checkRegistration(Model model,
                                    @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password) {
        Optional<User> user = userService.createUser(name, email, password);

        if (user.isPresent()) {
            return "login";
        } else {
            model.addAttribute("message", "User already exists or " +
                    "email not valid");
            return "error";
        }
    }

    @PostMapping("/checkLogin")
    public String checkLogin(Model model,
                             HttpSession session,
                             @RequestParam String email,
                             @RequestParam String password) {

        Optional<User> user = userService.login(email, password);
        if (user.isPresent()) {

            session.setAttribute("currentUser", user.get());
            model.addAttribute("currentUser", user.get());
           model.addAttribute("cars", carService.getAllActiveCars());

            return "home";
        } else {
            model.addAttribute("message", "User not exists or " +
                    "email or password not valid");
            return "error";
       }
    }

    @GetMapping(value = "/users")
    public String users(Model model){
        List<User> users = this.userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model, HttpSession session){

        User currentUser = (User) session.getAttribute("currentUser");
       if(currentUser != null && currentUser.getRole().equals("ADMIN")) {

           return "admin";
       }else {
            model.addAttribute("message", "You do not have permission to admin role ");

            return "error";
       }
    }

    @GetMapping(value = "/addrole")
    public String addrole(Model model){
        List<User> users = this.userService.findAllUsers();
        model.addAttribute("users", users);
        return "addrole";
    }
    @PostMapping(value="/addrole")
    public String userAddRole( User user, String role){

        User admin = this.userService.userById(String.valueOf(user.getUserId())).get();
        admin.setRole(role);
        User newAdmin = this.userRepository.save(admin);

        return "addrole";
    }

    @GetMapping(value = "/mycar")
    public String mycar(HttpSession session, Model model){
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser !=null){
        model.addAttribute("currentUser", currentUser);
        return "mycar";
    }else{
            model.addAttribute("message", "You are not login ");

            return "error";
        }
    }

    @PostMapping(value = "/mycar/{carId}")
    public String getCar(Model model, @RequestParam String carId ) {

        Car car = this.carService.getCarById(carId);
        model.addAttribute("car", car);
        return "mycar";
    }

    @PostMapping(value = "/newcar")
    public String addcar(Model model,
                         //@RequestParam Long carId,
                         @RequestParam String modell,
                         @RequestParam String mark,
                         @RequestParam int buildYear,
                         @RequestParam String imageName,
                         @RequestParam Double price) {

        Car newcar = this.carService.create(modell, mark, buildYear, price, imageName);
//        return getCar(carId, modell);
        model.addAttribute("newcar", newcar);
        return "addcar";
    }
    @PostMapping(value = "/update")
    public String updateCar(Model model,
                            @RequestParam String carId,
                            @RequestParam String modell,
                            @RequestParam String mark,
                            @RequestParam int buildYear,
                            @RequestParam String deleted,
                            @RequestParam String sold,
                            @RequestParam String imageName){
        Car updatedCar = this.carService.update(carId, modell, mark, buildYear, imageName, deleted, sold);
        model.addAttribute("car", updatedCar);
        return "updatedcar";
    }


    @PostMapping(value = "/deletecar")
    public String deletecar(Model model, @RequestParam String carId){
        Car deletedCar = this.carService.getCarById(carId);
        this.carService.delete(carId);
        model.addAttribute("car", deletedCar);
        return "deletedcar";
    }

//    @GetMapping(value = "/carmodels")
//    public String carmodels( Model model) {
//        List<Car> cars = this.carService.getAllActiveCars();
//        Set<String> models = new HashSet<>();
//        for (Car car : cars) {
//            models.add(car.getModell());
//        }
//        model.addAttribute("models", models);
//        return "carsstore";
//    }


    @GetMapping(value = "/cars")
    public String getStore(Model model){
//    public String getAllCars(String modell, Model model) {
        List<Car> cars = this.carService.getAllActiveCars();
        model.addAttribute("cars", cars);
        Set<String> models = new HashSet<>();
        for (Car car : cars) {
            models.add(car.getModell());
        }
        model.addAttribute("models", models);
        model.addAttribute("cars", cars);
//        List<Car> filteredCarsByModel = this.carService.getAllcarsByModell(modell);
//        model.addAttribute("filteredCars", filteredCarsByModel);
        return "carsstore";
    }
    @PostMapping(value = "/cars/{model}")
        public String carsByModel(@RequestParam String modell, Model model)  {
        if(modell != null) {
            List<Car> filteredCarsByModel = this.carService.getAllcarsByModell(modell);

            model.addAttribute("filteredCars", filteredCarsByModel);
        }else{
            List<Car> cars = carService.getAllActiveCars();
            model.addAttribute("cars", cars);

        }

        return "carsstore";

        }
//        @GetMapping(value = "/cars")
//    public String filteredCars(@RequestParam String modell, Model model) {
//            List<Car> filteredCarsByModel = this.carService.getAllcarsByModell(modell);
//
//            model.addAttribute("filteredCars", filteredCarsByModel);
//
//            return "carsstore";
//
//        }
}
