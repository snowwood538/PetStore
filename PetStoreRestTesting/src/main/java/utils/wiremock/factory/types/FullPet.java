package utils.wiremock.factory.types;

import swagger.petstore.models.common.Category;
import swagger.petstore.models.common.Tag;
import swagger.petstore.models.swagger.PetModel;
import utils.common.generator.Generator;
import utils.wiremock.factory.pet.IPet;

import java.util.List;
import java.util.Random;

public class FullPet extends PetModel implements IPet {

    @Override
    public PetModel createPet() {
        Category category = new Category();
        category.setId(new Random().nextInt(300));
        category.setName(Generator.getRandomString(5));

        Tag tag = new Tag();
        tag.setId(41456);
        tag.setName(Generator.getRandomString(5));

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setId(new Random().nextInt(1000));
        pet.setName(Generator.getRandomString(7));
        List<String> urls = List.of(Generator.getRandomString(15));
        pet.setPhotoUrls(urls);
        pet.setTags(List.of(tag));
        pet.setStatus(Generator.getRandomStatus());

        return pet;
    }
}