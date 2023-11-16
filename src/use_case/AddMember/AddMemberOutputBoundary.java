package use_case.AddMember;

public interface AddMemberOutputBoundary {
    void successView(AddMemberOutputData outputData);
    void failureView(AddMemberOutputData outputData);
}