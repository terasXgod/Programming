package inanimateObjects;

import livingOrganism.Human;

public class Home {

    protected Human owner;

    public Home(Human owner) {
        this.owner = owner;
    }

    protected Human getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Дом, владелец которого " + owner.getName();
    }
}
