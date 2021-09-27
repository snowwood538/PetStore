package utils.factory.pet.types;

import swagger.petstore.models.PetModel;
import utils.factory.pet.utils.Generator;
import utils.factory.pet.factory.IPet;

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