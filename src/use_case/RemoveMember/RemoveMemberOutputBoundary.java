package use_case.RemoveMember;

public interface RemoveMemberOutputBoundary {
    void successView(RemoveMemberOutputData outputData);
    void failureView(RemoveMemberOutputData outputData);
}