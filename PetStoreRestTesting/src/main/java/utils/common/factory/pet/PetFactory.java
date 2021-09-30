package utils.common.factory.pet;

import com.api.petstore.models.pet.PetModel;
import utils.common.factory.pet.types.BasePet;
import utils.common.factory.pet.types.FullPet;

import static utils.common.factory.enums.PetTypeVar.BASE_PET;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

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