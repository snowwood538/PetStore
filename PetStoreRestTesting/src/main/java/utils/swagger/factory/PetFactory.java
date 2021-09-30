package utils.swagger.factory;

import swagger.petstore.models.swagger.PetModel;
import utils.swagger.types.BasePet;
import utils.swagger.types.FullPet;

import static utils.common.generator.PetTypeVar.BASE_PET;
import static utils.common.generator.PetTypeVar.FULFILLED_PET;

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