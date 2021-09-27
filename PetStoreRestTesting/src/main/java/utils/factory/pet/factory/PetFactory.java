package utils.factory.pet.factory;

import swagger.petstore.models.PetModel;
import utils.factory.pet.types.BasePet;
import utils.factory.pet.types.FullPet;

import static utils.factory.pet.utils.PetTypeVar.BASE_PET;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;

public class PetFactory {
    public static PetModel createNewPet(Enum petType) {
        if (BASE_PET.equals(petType)) {
            return new BasePet().createPet();
        } else if (FULFILLED_PET.equals(petType)) {
            return new FullPet().createPet();
        }
        throw new RuntimeException(petType + " is unknown");
    }
}