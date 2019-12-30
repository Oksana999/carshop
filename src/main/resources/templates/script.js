function addCar(carDiv, car) {
    var textModel = document.createTextNode(car.model);
    var elementModel = document.createElement("div")
    elementModel.appendChild(textModel)
    carDiv.appendChild(elementModel)

    var textMark = document.createTextNode(car.mark);
    var elementMark = document.createElement("div")
    elementMark.appendChild(textMark)
    carDiv.appendChild(elementMark)

    var elementImageName = document.createElement("img")
    elementImageName.setAttribute("src", car.imageName)
    elementImageName.setAttribute("width", "300px");
    carDiv.appendChild(elementImageName)

    var textBuildYear = document.createTextNode(car.buildYear);
    var elementBuildYear = document.createElement("div")
    elementBuildYear.appendChild(textBuildYear)
    carDiv.appendChild(elementBuildYear)

    var textCarPrice = document.createTextNode(car.carPrice);
    var elementCarPrice = document.createElement("div")
    elementCarPrice.appendChild(textCarPrice)
    carDiv.appendChild(elementCarPrice)

    var textDeleteCar = document.createTextNode("Удалить");
    var elementDeleteCar = document.createElement("button")
    elementDeleteCar.appendChild(textDeleteCar)
    elementDeleteCar.setAttribute("onclick", "deleteCarButton(" + car.id + ")")
    carDiv.appendChild(elementDeleteCar)

    var textSaveCar = document.createTextNode("Change data of car");
    var elementSaveCar = document.createElement("button")
    elementSaveCar.appendChild(textSaveCar)
    elementSaveCar.setAttribute("onclick", "saveCar("+car.id+  ")")
    var link = document.getElementById("link");


      //  elementSaveCar.setAttribute("href", "#change")
     // document.getElementById("button").onclick = function(){
     //     window.location.href = "#change";
     // }

    carDiv.appendChild(elementSaveCar)
}

function jump(h) {
    var url = location.href = "#change"+h;
    history.replaceState(null, null, url);

}

function deleteCarButton(carId) {
    deleteCar(carId)
    location.reload(); // Обновление страницы F5
}

function deleteCar(carId) {
    var cars = loadCars();
    var deleteCarById = function (car, index) {
        if (carId == car.id) {
            cars.splice(index, 1)
           // car.remove();
        }
    };
    cars.forEach(deleteCarById)
    localStorage.setItem("cars", JSON.stringify(cars));
}

function loadCars() {
    var existedCars = localStorage.getItem("cars");
    if ((existedCars) && (existedCars.length > 5)) {
        return JSON.parse(existedCars);
    } else {
        return [
            {
                id: 111,
                model: "BMW",
                mark: "XT5",
                buildYear: 2005,
                carPrice: 50000,
                imageName: "https://www.cstatic-images.com/stock/900x600/235994.jpg"
            }, {
                id: 222,
                model: "BMW",
                mark: "XT5",
                buildYear: 2005,
                carPrice: 50000,
                imageName: "https://www.cstatic-images.com/stock/900x600/235994.jpg"
            },
            {
                id: 333,
                model: "Mercedes-Benz",
                mark: "W213",
                buildYear: 2019,
                carPrice: 55000,
                imageName: "https://upload.wikimedia.org/wikipedia/commons/9/9b/2019_Mercedes-Benz_E220d_SE_Automatic_2.0_Front.jpg"
            },
            {
                id: 555,
                model: "Audi",
                mark: "A8",
                buildYear: 2018,
                carPrice: 30000,
                imageName: "https://upload.wikimedia.org/wikipedia/commons/d/d9/Audi_A8_D5_IMG_0066.jpg"
            }
        ];

    }

}


function main() {
    var cars = loadCars();
    var carsDiv = document.getElementById('cars')
    var i;
    for (i = 0; i < cars.length; i++) {
        var car = cars[i];
        var carDiv = document.createElement("div");
        addCar(carDiv, car);
        carsDiv.appendChild(carDiv)
    }
}

function formAddCarOnClick() {
    var carId = Math.floor(Math.random() * 10000)
    var carsDiv = document.getElementById('cars')
    var model = document.getElementById('carModelForm').value;
    var mark = document.getElementById('carMarkForm').value;
    var buildYear = document.getElementById('carBuildYear').value;
    var carPrice = document.getElementById('carPrice').value;
    var imageName = document.getElementById('imageName').value;

    var car = {
        id: carId,
        model: model,
        mark: mark,
        buildYear: buildYear,
        carPrice: carPrice,
        imageName: imageName
    };
    var cars = loadCars();
    cars.push(car);
    localStorage.setItem("cars", JSON.stringify(cars));

    var carDiv = document.createElement("div");
    addCar(carDiv, car);
    carsDiv.appendChild(carDiv)
}


function saveCar(carId) {
     document.location = "#anchor";


    var cars = loadCars();
    cars.forEach(function (car, index) {
        if (carId == car.id) {
            document.getElementById('carIdChange').value = car.id;
            document.getElementById('carModelFormNew').value = car.model;
            document.getElementById('carMarkFormNew').value = car.mark;
            document.getElementById('carBuildYearNew').value = car.buildYear;
            document.getElementById('carPriceNew').value = car.carPrice;
            document.getElementById('imageNameNew').value = car.imageName;
        }
    });
}

function saveCarButton() {
    // var x = document.getElementById("change").href;
    // document.getElementById(saveCar).innerHTML = x;
    deleteCar(carId)
    var carId = document.getElementById('carIdChange').value;
    var model = document.getElementById('carModelFormNew').value;
    var mark = document.getElementById('carMarkFormNew').value;
    var buildYear = document.getElementById('carBuildYearNew').value;
    var carPrice = document.getElementById('carPriceNew').value;
    var imageName = document.getElementById('imageNameNew').value;
    // deleteCar(carId)
    var car = {
        id: carId,
        model: model,
        mark: mark,
        buildYear: buildYear,
        carPrice: carPrice,
        imageName: imageName
    };
    var cars = loadCars();
    cars.push(car);
    localStorage.setItem("cars", JSON.stringify(cars));
}