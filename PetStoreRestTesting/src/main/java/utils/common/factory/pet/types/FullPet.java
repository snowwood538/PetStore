package utils.common.factory.pet.types;

import com.api.petstore.models.pet.Category;
import com.api.petstore.models.pet.PetModel;
import com.api.petstore.models.pet.Tag;
import utils.common.factory.pet.IPet;
import utils.common.generator.Generator;

import java.util.List;
import java.util.Random;

public class FullPet implements IPet {

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