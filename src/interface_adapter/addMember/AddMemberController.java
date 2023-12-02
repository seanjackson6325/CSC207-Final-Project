package interface_adapter.addMember;

import use_case.AddMember.AddMemberInputBoundary;
import use_case.AddMember.AddMemberInputData;

public class AddMemberController {

    final AddMemberInputBoundary addMemberInteractor;

    public AddMemberController(AddMemberInputBoundary addMemberInteractor)
    {
        this.addMemberInteractor = addMemberInteractor;
    }

    public void execute(String user, String team)
    {
        AddMemberInputData input = new AddMemberInputData(user, team);
        addMemberInteractor.execute(input);
    }

}
