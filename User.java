public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

     class PortManager extends User {
        private Port assignedPort; // Reference to the port managed by this manager

        public PortManager(String username, String password, Port assignedPort) {
            super(username, password);
            this.assignedPort = assignedPort;
        }
    }
     class SystemAdmin extends User {
        public SystemAdmin(String username, String password){
            super(username, password);
        }
}
