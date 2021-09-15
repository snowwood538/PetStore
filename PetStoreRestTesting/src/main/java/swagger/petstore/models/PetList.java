package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PetList {
    @JsonProperty
    private List<Pet> listWithPets;

    public PetList() {
    }

    public List<Pet> getListWithPets() {
        return this.listWithPets;
    }

    public void setListWithPets(List<Pet> listWithPets) {
        this.listWithPets = listWithPets;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PetList)) return false;
        final PetList other = (PetList) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$listWithPets = this.getListWithPets();
        final Object other$listWithPets = other.getListWithPets();
        if (this$listWithPets == null ? other$listWithPets != null : !this$listWithPets.equals(other$listWithPets))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PetList;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $listWithPets = this.getListWithPets();
        result = result * PRIME + ($listWithPets == null ? 43 : $listWithPets.hashCode());
        return result;
    }

    public String toString() {
        return "PetList(listWithPets=" + this.getListWithPets() + ")";
    }
}
