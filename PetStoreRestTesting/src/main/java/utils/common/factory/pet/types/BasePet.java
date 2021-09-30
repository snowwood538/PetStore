package utils.common.factory.pet.types;

import com.api.petstore.models.pet.PetModel;
import utils.common.generator.Generator;
import utils.common.factory.pet.IPet;

import java.util.List;

public class BasePet implements IPet {

    @Override
    public PetModel createPet() {
        PetModel pet = new PetModel();
        pet.setName(Generator.getRandomString(5));
        List<String> urls = List.of(Generator.getRandomString(15));
        pet.setPhotoUrls(urls);
        return pet;
    }
}