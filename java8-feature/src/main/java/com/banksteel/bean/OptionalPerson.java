package com.banksteel.bean;

import java.util.Optional;

public class OptionalPerson {
    private Optional<OptionCar> optionCar;

    public Optional<OptionCar> getOptionCar() {
        return optionCar;
    }

    public void setOptionCar(Optional<OptionCar> optionCar) {
        this.optionCar = optionCar;
    }

}
