package use_case.AddMember;

import entity.Team;

public interface AddMemberDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Team team);
}
