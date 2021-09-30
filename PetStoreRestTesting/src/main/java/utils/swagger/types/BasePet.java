package utils.swagger.types;

import swagger.petstore.models.swagger.PetModel;
import utils.common.generator.Generator;
import utils.swagger.factory.IPet;

import java.util.List;

public class BasePet extends PetModel implements IPet {

    @Override
    public PetModel createPet() {
        PetModel pet = new PetModel();
        pet.setName(Generator.getRandomString(5));
        List<String> urls = List.of(Generator.getRandomString(15));
        pet.setPhotoUrls(urls);
        return pet;
    }
}