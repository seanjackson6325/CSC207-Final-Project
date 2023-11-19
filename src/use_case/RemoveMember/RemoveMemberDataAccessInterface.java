package use_case.RemoveMember;

import entity.Team;

public interface RemoveMemberDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Team team);
}
