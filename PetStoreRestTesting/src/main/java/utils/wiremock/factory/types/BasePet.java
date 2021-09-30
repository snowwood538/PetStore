package utils.wiremock.factory.types;

import swagger.petstore.models.wiremock.RequiredFieldsPet;
import utils.common.generator.Generator;
import utils.wiremock.factory.pet.IPet;

import java.util.List;

public class BasePet extends RequiredFieldsPet implements IPet {

    @Override
    public RequiredFieldsPet createPet() {
        RequiredFieldsPet pet = new RequiredFieldsPet();
        pet.setName(Generator.getRandomString(5));
        List<String> urls = List.of(Generator.getRandomString(15));
        pet.setPhotoUrls(urls);
        return pet;
    }
}