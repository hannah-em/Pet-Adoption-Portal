package interface_adapter.manage_application;

import java.util.*;

public class ManageApplicationViewModel {

    private List<ApplicationCardViewModel> cards;
    private ApplicationDetailViewModel selected;

    private ManageApplicationState state = new ManageApplicationState();

    public List<ApplicationCardViewModel> getCards() { return cards;}

    public void setCards(List<ApplicationCardViewModel> cards) { this.cards = cards;}

    public ApplicationDetailViewModel getSelected() {return selected;}

    public void setSelected(ApplicationDetailViewModel selected) {this.selected = selected;}

    public ManageApplicationState getState() {return state;}

}
