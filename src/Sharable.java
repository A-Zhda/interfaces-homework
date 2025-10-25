public interface Sharable {
    int maxProfiles();
    void addMember(String userId);
    void removeMember(String userId);
}
