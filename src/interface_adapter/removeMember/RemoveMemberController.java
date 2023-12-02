package interface_adapter.removeMember;

import use_case.RemoveMember.RemoveMemberInputBoundary;
import use_case.RemoveMember.RemoveMemberInputData;

public class RemoveMemberController {

    final RemoveMemberInputBoundary removeMemberInteractor;

    public RemoveMemberController(RemoveMemberInputBoundary removeMemberInteractor)
    {
        this.removeMemberInteractor = removeMemberInteractor;
    }

    public void execute(String user, String team)
    {
        RemoveMemberInputData input = new RemoveMemberInputData(user, team);
        removeMemberInteractor.execute(input);
    }

}
